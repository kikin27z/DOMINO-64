package manejadores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author karim
 */
public class Control {

    private static ManejadorCuenta cuenta;
    private static ManejadorDisplay display;
    private static ManejadorModelo modelo;
    private static Control principal;
    private static ExecutorService hiloPrincipal;

    // Constructor privado para evitar instanciación externa
    private Control() {
        // Crear un único hilo que actuará como nuestro hilo principal
        hiloPrincipal = Executors.newSingleThreadExecutor(r -> {
            Thread thread = new Thread(r);
            thread.setName("MainLogicThread");
            
            return thread;
        });
        hiloPrincipal.execute(() -> {
            try {
                cuenta = new ManejadorCuenta();
                modelo = new ManejadorModelo();
                display = new ManejadorDisplay();
                System.out.println("Manejadores iniciados en: " + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Obtiene la instancia única de Navegacion.
     *
     * @return La instancia de Navegacion.
     */
    public static Control iniciarJuego() {
        if (principal == null) {
            principal = new Control(); // Crea la instancia si no existe
        }
        return principal;
    }

    public static Control obtenerPrincipal() {
        return principal;
    }

    public static ManejadorCuenta obtenerManejadorCuenta() {
        return cuenta;
    }

    public static ManejadorDisplay obtenerManejadorDisplay() {
        return display;
    }

    public static ManejadorModelo obtenerManejadorModelo() {
        return modelo;
    }

    public static ExecutorService getHiloPrincipal() {
        return hiloPrincipal;
    }

    public static void setHiloPrincipal(ExecutorService hiloPrincipal) {
        Control.hiloPrincipal = hiloPrincipal;
    }
}
