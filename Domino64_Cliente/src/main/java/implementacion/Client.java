package implementacion;

import abstraccion.ICliente;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import domino64.eventos.base.error.TipoError;
import domino64.eventos.base.suscripcion.EventoSuscripcion;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import observer.Observable;
import observer.Observer;

/**
 * Clase Client que maneja la conexión con el servidor, la gestión de eventos y
 * las suscripciones a eventos. Esta clase implementa la interfaz ICliente y usa
 * el patrón Observer para notificar a los observadores sobre los eventos
 * recibidos desde el servidor.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class Client extends Observable<Evento> implements ICliente {

    private static Client cliente; // Instancia única del cliente
    private String host; // IP del servidor
    private final int port; // Puerto del servidor
    private int clientId; // ID del cliente asignado por el servidor
    private final ScheduledExecutorService ejecutorReconexion; // Executor para tareas programadas, como reconexión
    private final ExecutorService ejecutorEventos; // Executor para procesar eventos entrantes
    private Socket socket; // Socket de conexión con el servidor
    private ObjectOutputStream output; // Flujo de salida para enviar eventos al servidor
    private ObjectInputStream input; // Flujo de entrada para recibir eventos desde el servidor
    private volatile boolean running; // Estado del cliente, indica si está en ejecución
    private volatile boolean connected; // Estado de la conexión con el servidor
    private List<Enum> suscripcionesEventos; // Lista de eventos a los que está suscrito el cliente
    private BlockingQueue<Evento> colaEventos; // Cola para almacenar los eventos pendientes de ser enviados

    /**
     * Constructor privado para inicializar el cliente con los valores
     * predeterminados.
     */
    private Client() {
        String ip = pedirIP();
        this.host = ip;
        this.port = 5000;
        this.ejecutorReconexion = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r);
            return t;
        });

        this.ejecutorEventos = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            return t;
        });

        suscripcionesEventos = new ArrayList<>();
        colaEventos = new LinkedBlockingQueue();
    }

    /**
     * Método estático para iniciar la comunicación con el servidor. Garantiza
     * que solo haya una instancia del cliente.
     *
     * @return La instancia del cliente.
     */
    public static synchronized Client iniciarComunicacion() {
        if (cliente == null) {
            cliente = new Client();
        }
        return cliente;
    }

    /**
     * Obtiene el ID del cliente.
     *
     * @return El ID del cliente.
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Inicia el proceso de conexión y la gestión de eventos.
     */
    public void iniciar() {
        running = true;
        conectarCliente();

        new Thread(this::procesarColaEventos).start();

        new Thread(this::listenForEvent).start();
    }

    /**
     * Conecta el cliente al servidor. Inicializa los flujos de entrada y
     * salida.
     */
    private void conectarCliente() {
        try {
            socket = new Socket(host, port);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            connected = true;
            enviarSuscripciones();

            //recibir el id del cliente
            this.clientId = input.readInt();
            System.out.println("Se conecto al servidor id otorgado: " + clientId);

        } catch (IOException ex) {
            System.out.println("Error al conectar cliente " + clientId);
            manejarDesconexion();
        }
    }

    /**
     * Envía las suscripciones de eventos al servidor.
     */
    private void enviarSuscripciones() {
        try {
            if (suscripcionesEventos != null && !suscripcionesEventos.isEmpty()) {
                if (connected) {
                    synchronized (output) {
                        //output.reset();
                        output.writeObject(suscripcionesEventos);
                        output.flush();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("error al enviar suscripciones: " + e.getMessage());
            manejarDesconexion();
        }
    }

    /**
     * Envia los eventos al servidor. Va tomando los eventos que se vayan
     * agregando a la cola y los envia segun vayan llegando
     */
    private void procesarColaEventos() {
        while (running) {
            try {
                Evento e = colaEventos.take();
                if (connected) {
                    try {
                        synchronized (output) {
                            output.reset();
                            System.out.println("Envio evento; " + e);
                            output.writeObject(e);//envia msj al servidor
                            output.flush();
                        }
                    } catch (IOException ex) {
                        System.out.println("error al enviar evento: " + ex.getMessage());
                        manejarDesconexion();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("error al tomar el evento de la cola de eventos: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @Override
    public void enviarEvento(Evento event) {
        colaEventos.offer(event);
    }

    @Override
    public void establecerSuscripciones(List<Enum> subs) {
        for (Enum sub : subs) {
            suscripcionesEventos.add(sub);
        }
    }

   /**
     * Escucha los eventos que llegan del servidor y los notifica a los observadores.
     */
    private void listenForEvent() {
        while (running) {
            if (connected && input != null) {
                Evento evento;
                try {
                    evento = (Evento) input.readObject();
                    System.out.println("evento en lstn 4 ev: " + evento);
                    ejecutorEventos.submit(() -> {
                        manejarEvento(evento);
                    });
                } catch (IOException e) {
                    manejarDesconexion();
                } catch (ClassNotFoundException ex) {
                    System.err.println("Error al recibir un evento: " + ex.getMessage());
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    /**
     * Maneja el evento recibido y lo notifica a los observadores.
     * 
     * @param evento El evento recibido del servidor.
     */
    private void manejarEvento(Evento evento) {
        System.out.println("mensaje recibido " + evento);

        notifyObservers(evento.getTipo(), evento);
        System.out.println("se notifico a observers");
    }

    /**
     * Maneja la desconexión del cliente, cerrando los recursos y notificando el error.
     */
    private void manejarDesconexion() {
        connected = false;
        running = false;
        System.out.println("en falso");
        Evento error = new EventoError(TipoError.ERROR_DE_SERVIDOR, "se desconecto el cliente");
        notifyObservers(TipoError.ERROR_DE_SERVIDOR, error);
        System.out.println("se notifico el error");
        ejecutorEventos.shutdown();

        try {
            if (!ejecutorEventos.awaitTermination(3, TimeUnit.SECONDS)) {
                ejecutorEventos.shutdownNow();
            }
        } catch (InterruptedException e) {
            ejecutorEventos.shutdownNow();
            Thread.currentThread().interrupt();
        }

        try {
            if (socket != null) {
                socket.close();
            }
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
            System.out.println("cliente desconectadooo");
        } catch (IOException e) {
        }

        //reconexionProgramada();
    }

    @Override
    public void agregarSuscripcion(Evento evento, Observer ob) {
        EventoSuscripcion suscripcion = (EventoSuscripcion) evento;
        suscripcionesEventos.add(suscripcion.getEventoSuscripcion());
        addObserver(suscripcion.getEventoSuscripcion(), ob);
        enviarEvento(evento);
    }

    @Override
    public void removerSuscripcion(Evento evento, Observer ob) {
        EventoSuscripcion suscripcion = (EventoSuscripcion) evento;
        suscripcionesEventos.remove(suscripcion.getEventoSuscripcion());
        removeObserver(suscripcion.getEventoSuscripcion(), ob);
        enviarEvento(evento);
    }

    /**
     * Método para pedir la dirección IP del servidor.
     * 
     * @return La dirección IP proporcionada por el usuario.
     */
    private String pedirIP() {
        Scanner lectura = new Scanner(System.in);
        System.out.print("Escribe la ip del servidor: ");
        String ip = lectura.nextLine();

        if (ip.isBlank()) {
            return "localhost";
        }
        return ip;
    }
}
