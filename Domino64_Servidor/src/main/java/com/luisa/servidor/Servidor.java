package com.luisa.servidor;

import com.domino64.base.Publicador;
import eventBus.BusCore;
import eventBus.Publisher;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de crear el servidor del juego, que a su vez crea y maneja el
 * bus de eventos, pero la manera en que se comunica con el bus es mediante la
 * interfaz de Publicador. Tiene una instancia de Publicador, pero la inicializa
 * como un Publisher, el cual es la clase de publicador concreta que proporciona
 * el bus para que puedan publicar eventos en el bus. Tiene dos mapas, uno para
 * los jugadores conectados y otro para los componentes conectados. Para manejar
 * el tema de los hilos para cada cliente se usa la interfaz ExecutorService,
 * creando threadPools para controlar la creacion y uso de los hilos: -Para los
 * componentes, se usa un FixedThreadPool con la cantidad especifica de hilos
 * correspondiente a la cantidad de componentes a recibir. -Para los jugadores,
 * se usa un CachedThreadPool para crear hilos segun se vayan conectando los
 * jugadores.
 *
 * @author luisa M
 */
public class Servidor {

    //private static BusCore bus;
    private static Publicador publicador;
    private final int port;
    private ServerSocket servidor;
    private int jugadorId = 0;
    private int componenteId = 10;
    private static Map<Integer, HiloJugador> clientesJugadores;
    private static Map<Integer, HiloComponente> clientesComponentes;
    private final ExecutorService threadPoolComponentes;
    private final ExecutorService threadPoolJugadores;

    public Servidor(int port, BusCore core) {
        this.port = port;
        publicador = new Publisher(core);
        clientesJugadores = new ConcurrentHashMap<>();
        clientesComponentes = new ConcurrentHashMap<>();
        threadPoolComponentes = Executors.newFixedThreadPool(5);
        threadPoolJugadores = Executors.newFixedThreadPool(10);
    }

    /**
     * metodo para cerrar todos los sockets conectados
     */
    private void desconectarClientes() {
        clientesJugadores.forEach((id, cliente) -> desconectarJugador(id));
        clientesComponentes.forEach((id, cliente) -> desconectarComponente(id));
    }

    /**
     * metodo para desconectar un jugador usando su id de cliente proporcionado
     * en el parametro. Primero busca si existe un cliente con el id
     * proporcionado, si lo encuentra, obtiene el hilo que maneja el cliente,
     * luego obtiene el socket que maneja ese hilo, y posteriormente libera los
     * recursos del socket y quita al jugador del mapeo.
     *
     * @param idCliente Id del cliente del jugador a desconectar
     */
    protected static void desconectarJugador(int idCliente) {
        if (clientesJugadores.containsKey(idCliente)) {
            HiloJugador jugador = clientesJugadores.get(idCliente);
            Socket socket = jugador.getSocket();
            try {
                if(!socket.isClosed()){
                    socket.getInputStream().close();
                    socket.getOutputStream().close();
                    socket.close();
                }
            } catch (IOException e) {
                Logger.getLogger(HiloJugador.class.getName()).log(Level.SEVERE, null, e);
            }
            clientesJugadores.remove(idCliente);
        }
    }

    /**
     * metodo para desconectar un componente usando su id de cliente
     * proporcionado en el parametro. Primero busca si existe un cliente con el
     * id proporcionado, si lo encuentra, obtiene el hilo que maneja el cliente,
     * luego obtiene el socket que maneja ese hilo, y posteriormente libera los
     * recursos del socket y quita al componente del mapeo.
     *
     * @param idCliente Id del cliente del componente a desconectar
     */
    protected static void desconectarComponente(int idCliente) {
        if (clientesComponentes.containsKey(idCliente)) {
            HiloComponente componente = clientesComponentes.get(idCliente);
            Socket socket = componente.getSocket();
            try {
                if(!socket.isClosed()){
                    socket.getInputStream().close();
                    socket.getOutputStream().close();
                    socket.close();
                }
            } catch (IOException e) {
                Logger.getLogger(HiloJugador.class.getName()).log(Level.SEVERE, null, e);
            }
            clientesComponentes.remove(idCliente);
        }
    }

    /**
     * metodo para recibir las conexiones con los clientes de los componentes,
     * por cada componente que se va contectando le asigna un id que es un
     * contador de clientes. El contador de los componentes inicia en 10, esto
     * es para evitar que un componente tenga el mismo id que un jugador. Por
     * ejemplo, al tercer componente que se conecte se le va a asignar el id 30,
     * y al tercer jugador se le va a asignar el id de 3
     */
    private void recibirComponentes() {
        Socket socket;
        try {
            while (clientesComponentes.isEmpty()) {
                socket = servidor.accept();
                componenteId++;
                HiloComponente componente = new HiloComponente(publicador, socket, componenteId);
                threadPoolComponentes.execute(componente);
                clientesComponentes.putIfAbsent(componenteId, componente);
            }
        } catch (IOException e) {
            desconectarComponente(componenteId);
        }
    }

    /**
     * metodo para recibir las conexiones con los clientes de los jugadores, por
     * cada jugador que se va contectando le asigna un id que es un contador de
     * clientes. El contador de los jugadores inicia en 1, esto es para evitar
     * que un jugador tenga el mismo id que un componente.
     *
     * Por ejemplo, al tercer componente que se conecte se le va a asignar el id
     * 30, y al tercer jugador se le va a asignar el id de 3
     */
    private void recibirJugadores() {
        Socket socket;
        try {
            while (clientesJugadores.size() < 10) {
                socket = servidor.accept();
                jugadorId++;
                HiloJugador jugador = new HiloJugador(publicador, socket, jugadorId);
                clientesJugadores.putIfAbsent(jugadorId, jugador);
                threadPoolJugadores.execute(jugador);
            }
        } catch (IOException e) {
            desconectarJugador(jugadorId);
        }
    }

    /**
     * Metodo para iniciar el servidor. Primero se reciben las conexiones con
     * los componentes y despues se reciben las conexiones con los jugadores. El
     * orden en que se reciben es importante para evitar que se conecten
     * jugadores cuando aun no estan todos los componentes conectados y
     * funcionando.
     *
     * En caso de que ocurra un error al iniciar el servidor, se van a
     * desconectar a todos los clientes y se va a cerrar el servidor.
     */
    public void initServer() {
        try {
            servidor = new ServerSocket(port);

            imprimirIP();
            recibirComponentes();
            System.out.println("componentes conectados");
            recibirJugadores();
        } catch (IOException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
            desconectarClientes();
            closeServer();
        }
    }

    private void closeServer() {
        try {
            servidor.close();
            threadPoolComponentes.shutdown();
            if (!(threadPoolComponentes.awaitTermination(5, TimeUnit.SECONDS))) {
                threadPoolComponentes.shutdownNow();
            }

            threadPoolJugadores.shutdown();
            if (!(threadPoolJugadores.awaitTermination(5, TimeUnit.SECONDS))) {
                threadPoolJugadores.shutdownNow();
            }

            System.out.println("se cerro el server");
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        BusCore core = BusCore.getInstance();
        Servidor s = new Servidor(5000, core);
        s.initServer();
    }

    private void imprimirIP(){
        try {
            System.out.println("Servidor iniciado en puerto: " + port);
            System.out.println("IPs disponibles:");
            System.out.println("  IP servidor: " + Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
        }
    }
}
