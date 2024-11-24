package implementacion;

import abstraccion.ICliente;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import domino64.eventos.base.error.TipoError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import observer.Observable;
import observer.Observer;

/**
 *
 * @author luisa M
 */
public class Client extends Observable<Evento> implements ICliente{
    private static Client cliente;
    private String host;
    private final int port;
    private int clientId;
    private final ScheduledExecutorService ejecutorReconexion;
    private final ExecutorService ejecutorEventos;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private volatile boolean running;
    private volatile boolean connected;
    private List<Enum<?>> suscripcionesEventos;
    private BlockingQueue<Evento> colaEventosAEnviar;
    private BlockingQueue<Evento> colaEventosRecibidos;
    
    /**
     * para conexion en equipos diferentes (diferentes ip)
     *
     * @param host
     * @param port
     */
    private Client(String host, int port) {
        this.host = host;
        this.port = port;
        
        this.ejecutorReconexion = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r);
            return t;
        });
        
        this.ejecutorEventos = Executors.newFixedThreadPool(3);
        
        suscripcionesEventos = new ArrayList<>();
        colaEventosAEnviar = new LinkedBlockingQueue();
        colaEventosRecibidos = new LinkedBlockingQueue();
    }

    /**
     * para conexion en el mismo equipo
     *
     * @param port
     */
    private Client(int port) {
        this.port = port;
        
        this.ejecutorReconexion = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r);
            return t;
        });
        
        this.ejecutorEventos = Executors.newFixedThreadPool(3);
        
        suscripcionesEventos = new ArrayList<>();
        colaEventosAEnviar = new LinkedBlockingQueue();
        colaEventosRecibidos = new LinkedBlockingQueue();
    }
    
    public static synchronized Client getClient(int port){
        if(cliente == null)
            cliente = new Client(port);
        return cliente;
    }
    
    public static synchronized Client getClient(String host, int port){
        if(cliente == null)
            cliente = new Client(host, port);
        return cliente;
    }
    
    public int getClientId(){
        return clientId;
    }
    
    public void iniciar(){
        running = true;
        conectarCliente();
        
        ejecutorEventos.submit(this::procesarColaEventos);
        ejecutorEventos.submit(this::manejarEvento);
        ejecutorEventos.submit(this::listenForEvent);
        
        //new Thread(this::procesarColaEventos).start();
        
        //new Thread(this::listenForEvent).start();
    }
    
    private void conectarCliente(){
        try {
//            
                
            socket = new Socket(host, port);
            System.out.println("en el run");
            
            output = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("se inicio");
            
            input = new ObjectInputStream(socket.getInputStream());
            
            connected = true;
            
            //recibir el id del cliente
            clientId = input.readInt();
            System.out.println("id cliente: "+clientId);
            
            enviarSuscripciones();
            
        }catch (IOException ex){
            System.out.println("Error al conectar cliente "+clientId);
            manejarDesconexion();
        }
    }
    
//    private void reconexionProgramada(){
//        if(running && !connected){ 
//            ejecutorReconexion.schedule(() -> {
//                System.out.println("intentando reconectar...");
//                conectarCliente();
//            }, 5, TimeUnit.SECONDS);
//        }
//    }
    
    private void enviarSuscripciones(){
        if(suscripcionesEventos != null && !suscripcionesEventos.isEmpty()){
            if(connected){
                try {
                    synchronized (output) {
                        output.reset();
                        output.writeObject(suscripcionesEventos);
                        output.flush();
                    }
                } catch (IOException e) {
                    System.out.println("error al enviar suscripciones: " + e.getMessage());
                    manejarDesconexion();
                }
            }
        }
    }
    
    /**
     * envia los eventos al servidor.
     * Va tomando los eventos que se vayan agregando a la cola
     * y los envia segun vayan llegando
     */
    private Thread procesarColaEventos(){
        return new Thread(() -> {
            while (running) {
                try {
                    Evento e = colaEventosAEnviar.take();
                    if (connected) {
                        try {
                            synchronized (output) {
                                output.reset();
                                System.out.println("en el enviar evento; " + e);
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
                    manejarDesconexion();
//                    Thread.currentThread().interrupt();
//                    break;
                }
            }
        });
        
    }
    
    @Override
    public void enviarEvento(Evento event) {
        colaEventosAEnviar.offer(event);
    }

    @Override
    public void establecerSuscripciones(List<Enum<?>> subs){
        for (Enum<?> sub : subs) {
            suscripcionesEventos.add(sub);
        }
    }
    
    /**
     * escucha eventos que le llegan del servidor
     * y se los notifica al componente observador
     */
    private void listenForEvent() {
        while(running){
            if(connected && input != null){
                try {
                    Evento evento = (Evento)input.readObject();
                    colaEventosRecibidos.offer(evento);
                    System.out.println("evento en lstn 4 ev: "+evento);
//                    ejecutorEventos.submit(() -> {
//                        manejarEvento(evento);
//                    });
                } catch (IOException e) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, e.getLocalizedMessage());
                }catch (ClassNotFoundException ex){
                    System.err.println("Error al recibir un evento: "+ex.getMessage());
                }finally{
                    manejarDesconexion();
                }
            }else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    manejarDesconexion();
//                    Thread.currentThread().interrupt();
//                    break;
                }
            }
        }
    }
    
    private Thread manejarEvento(){
        return new Thread(() -> {
            while(running){
                try {
                    Evento evento = colaEventosRecibidos.take();
                    System.out.println("mensaje recibido " + evento);

                    notifyObservers(evento.getTipo(), evento);
                    System.out.println("se notifico a observers");
                } catch (InterruptedException ex) {
                    System.out.println("error al tomar el evento de la cola de eventos: " + ex.getMessage());
                    manejarDesconexion();
                    //Thread.currentThread().interrupt();
                    //break;
                }
            }
        });
        
    }
    
    private void manejarDesconexion(){
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
            if (socket != null)socket.close();
            if(input != null)input.close();
            if(output != null)output.close();
            System.out.println("cliente desconectadooo");
        } catch (IOException e) {}
        
        //reconexionProgramada();
    }
    

    @Override
    public void agregarSuscripcion(Evento evento, Observer ob) {
        suscripcionesEventos.add((Enum<?>)evento.getInfo());
        addObserver((Enum<?>)evento.getInfo(), ob);
        enviarEvento(evento);
    }

    @Override
    public void removerSuscripcion(Evento evento, Observer ob) {
        suscripcionesEventos.remove((Enum<?>)evento.getInfo());
        removeObserver((Enum<?>)evento.getInfo(), ob);
        enviarEvento(evento);
    }

}
