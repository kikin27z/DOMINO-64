package inicio;

import javafx.scene.input.MouseEvent;

/**
 * La clase InicioControl actúa como el controlador del patrón MVC (Modelo-Vista-Controlador) 
 * para la pantalla de inicio del juego de dominó. Su función principal es gestionar la 
 * interacción entre la vista (InicioView) y el modelo (InicioModel), manejando los eventos 
 * de los botones que permiten seleccionar el modo de juego.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class InicioControl {
    private InicioView view;
    private InicioModel modelo;

    /**
     * Constructor que inicializa el controlador con la vista y el modelo.
     * También carga los eventos asociados a la vista.
     * 
     * @param view la vista de inicio que será controlada.
     * @param modelo el modelo de datos asociado a la vista.
     */
    public InicioControl(InicioView view, InicioModel modelo) {
        this.view = view;
        this.modelo = modelo;
        cargarEventos();  // Configura los eventos de los botones.
    }

    /**
     * Método privado que configura los eventos para los botones de la vista.
     * Asocia las acciones de "modo Offline" y "modo Online" con los métodos
     * correspondientes del controlador.
     */
    private void cargarEventos() {
        view.modoOffline(this::irModoOffline);  // Asigna el evento para el modo Offline.
        view.modoOnline(this::irModoOnline);    // Asigna el evento para el modo Online.
    }

    /**
     * Método que maneja el evento cuando se selecciona el modo "Offline".
     * Invoca el método correspondiente en el modelo.
     * 
     * @param e el evento de ratón que indica que se ha hecho clic en el botón.
     */
    private void irModoOffline(MouseEvent e) {
        this.modelo.irModoOffline();

    }

    /**
     * Método que maneja el evento cuando se selecciona el modo "Online".
     * Invoca el método correspondiente en el modelo.
     * 
     * @param e el evento de ratón que indica que se ha hecho clic en el botón.
     */
    private void irModoOnline(MouseEvent e) {
        this.modelo.irModoOnline();
    }
}
