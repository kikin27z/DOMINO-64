package manejadores;

import comunicadores_logica.IReceptorEventosLogica;
import comunicadores_logica.ReceptorLogica;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import receptorPresentacion.MediadorManejadores;
import vinculacionModeloLogica.NotificarLogica;

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
    private static ManejadorCuenta cuenta;
    private static ManejadorDisplay display;
    private static Control principal;
    private static ExecutorService hiloPrincipal;
    private static IReceptorEventosLogica receptor;
    private static NotificarLogica mediador;
    private static ManejadorNotificador notificador;

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
                receptor = new ReceptorLogica();
                receptor.iniciaConexion();
                cuenta = new ManejadorCuenta();
                notificador = new ManejadorNotificador();
                display = new ManejadorDisplay();
                receptor.vincularCuenta();
                receptor.vincularDisplay();
                mediador = new MediadorManejadores();
                
                display.iniciarJuego();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
    public static IReceptorEventosLogica obtenerReceptor() {
        return receptor;
    }
    public static ManejadorNotificador obtenerNotificador() {
        return notificador;
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

            return thread;
        });
    }

}
