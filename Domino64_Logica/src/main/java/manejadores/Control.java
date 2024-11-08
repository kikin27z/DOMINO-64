package manejadores;


/**
 *
 * @author karim
 */
public class Control {
    private static ManejadorCuenta cuenta;
    private static ManejadorDisplay display;
    private static Control principal;
    
    // Constructor privado para evitar instanciación externa
    private Control() {
        cuenta = new ManejadorCuenta();
        display = new ManejadorDisplay();
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
    
    public static Control obtenerPrincipal(){
        return principal;
    }
    
    public static ManejadorCuenta obtenerManejadorCuenta(){
        return cuenta;
    }
    
    public static ManejadorDisplay obtenerManejadorDisplay(){
        return display;
    }
}
