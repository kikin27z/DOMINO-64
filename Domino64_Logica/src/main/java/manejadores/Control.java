package manejadores;

import abstraccion.ICliente;
import implementacion.Client;
import java.util.Scanner;
import logicaLobby.ManejadorCuenta;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que actúa como controlador central del juego. Es responsable de la
 * inicialización de los manejadores y la gestión del hilo principal donde se
 * ejecutan las operaciones lógicas del juego.
 *
 * Se implementa el patrón Singleton, asegurando que solo haya una instancia del
 * controlador en toda la ejecución de la aplicación.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class Control {

    private static final Logger LOGGER = Logger.getLogger(Control.class.getName());

    private static ManejadorCuenta cuenta;
    private static ManejadorDisplay display;
    private static MediadorManejadores modelo;
    private static Control principal;
    private static ExecutorService hiloPrincipal;
    private static ICliente cliente;

    /**
     * Constructor privado que inicializa los manejadores del juego y el hilo
     * principal que ejecutará las operaciones lógicas. Este método se ejecuta
     * en un solo hilo creado específicamente para la lógica principal del
     * juego.
     */
    private Control() {
        // Crear un único hilo que actuará como nuestro hilo principal
        hiloPrincipal = crearHiloPrincipal();
        hiloPrincipal.execute(() -> {
            try {
                // Inicializar los manejadores

                cuenta = new ManejadorCuenta();
                modelo = new MediadorManejadores();
                display = new ManejadorDisplay();
                cuenta.setManejadorDisplay(display);

                subscribirManejadores();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void subscribirManejadores() {
        String ip = pedirIP();
        Client client = Client.getClient(ip, 5000);

        for (Enum<?> evento : cuenta.getEventos()) {
            client.addObserver(evento, cuenta);
        }

        cuenta.init(client);
        client.iniciar();
        cuenta.setClientId(client.getClientId());

        display.iniciarJuego();

    }

    /**
     * Obtiene la instancia única de la clase {@link Control}. Si la instancia
     * no existe, se crea una nueva.
     *
     * @return La instancia de {@link Control}.
     */
    public static Control iniciarJuego() {
        if (principal == null) {
            principal = new Control(); // Crea la instancia si no existe
        }
        return principal;
    }

    /**
     * Obtiene la instancia principal del controlador.
     *
     * @return La instancia principal del controlador.
     */
    public static Control obtenerPrincipal() {
        return principal;
    }

    /**
     * Obtiene el manejador de cuentas.
     *
     * @return El manejador de cuentas.
     */
    public static ManejadorCuenta obtenerManejadorCuenta() {
        return cuenta;
    }

    /**
     * Obtiene el manejador de la visualización (display).
     *
     * @return El manejador de display.
     */
    public static ManejadorDisplay obtenerManejadorDisplay() {
        return display;
    }

    /**
     * Obtiene el mediador de los manejadores, el cual coordina las
     * interacciones entre los diferentes componentes lógicos del juego.
     *
     * @return El mediador de manejadores.
     */
    public static MediadorManejadores obtenerManejadorModelo() {
        return modelo;
    }

    public static ICliente obtenerCliente() {
        return cliente;
    }

    /**
     * Obtiene el servicio de ejecución que maneja el hilo principal del juego.
     *
     * @return El servicio de ejecución para el hilo principal.
     */
    public static ExecutorService getHiloPrincipal() {
        return hiloPrincipal;
    }

    private ExecutorService crearHiloPrincipal() {
        return Executors.newSingleThreadExecutor(r -> {
            Thread thread = new Thread(r);
            thread.setName("MainLogicThread");

//            // Establecemos el nivel mínimo de logging
//            LOGGER.setLevel(Level.ALL);
//            LOGGER.log(Level.INFO, "Prueba chaval{0}", getClass());
            return thread;
        });
    }

    private void solicitarCrearCliente() {
        System.out.println("");
    }

    private static String pedirIP() {
        Scanner input = new Scanner(System.in);
        System.out.print("Escribe la ip del servidor: ");
        String ip = input.nextLine();

        if (ip.isBlank()) {
            return "localhost";
        }
        return ip;

    }
}
