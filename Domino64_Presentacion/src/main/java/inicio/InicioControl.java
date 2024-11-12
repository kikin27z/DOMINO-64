package inicio;

import javafx.scene.input.MouseEvent;

/**
 * Controlador para la pantalla de inicio que gestiona los eventos de interacción
 * del usuario, como iniciar el modo de juego o mostrar los créditos.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class InicioControl {
    private final InicioView view;
    private final InicioModel modelo;

    /**
     * Constructor que inicializa el controlador con una vista y un modelo.
     * Configura los eventos de la vista que son manejados por esta clase.
     * 
     * @param view la vista de la pantalla de inicio
     * @param modelo el modelo asociado a la pantalla de inicio
     */
    public InicioControl(InicioView view, InicioModel modelo) {
        this.view = view;
        this.modelo = modelo;
        cargarEventos(); 
    }

    /**
     * Asigna los eventos de la vista a sus respectivos métodos manejadores,
     * permitiendo que el controlador gestione la lógica cuando ocurren.
     */
    private void cargarEventos() {
        view.modoJugar(this::modoJugar);
        view.mostrarCreditos(this::mostrarCreditos);
    }

    /**
     * Maneja el evento de iniciar el modo de juego. Llama al modelo para 
     * notificar que se debe activar el modo de juego.
     * 
     * @param e el evento de ratón que activa este método
     */
    private void modoJugar(MouseEvent e) {
        this.modelo.avisarModoJugar();
    }

    /**
     * Maneja el evento de mostrar los créditos. Llama al modelo para notificar
     * que se deben mostrar los créditos en la vista.
     * 
     * @param e el evento de ratón que activa este método
     */
    private void mostrarCreditos(MouseEvent e) {
        this.modelo.avisarMostrarCreditos();
    }
}
