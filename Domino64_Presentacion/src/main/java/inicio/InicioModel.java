package inicio;

/**
 * La clase InicioModel representa el modelo en el patrón MVC (Modelo-Vista-Controlador) 
 * para la pantalla de inicio del juego de dominó. Su función es gestionar la lógica 
 * del juego cuando se selecciona uno de los modos (Offline u Online).
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class InicioModel {

    /**
     * Método que representa la acción de entrar en el modo Offline (jugador solo).
     * Actualmente, imprime un mensaje en la consola, pero se puede extender para
     * implementar la lógica de inicialización del modo Offline.
     */
    public void irModoOffline() {
        System.out.println("Habla con lógica");  // Placeholder para lógica del modo Offline.
    }

    /**
     * Método que representa la acción de entrar en el modo Online (multijugador).
     * Actualmente, está vacío, pero se puede extender para implementar la lógica
     * de conexión y configuración del juego en línea.
     */
    public void irModoOnline() {
        // Lógica para iniciar el modo Online.
    }
}
