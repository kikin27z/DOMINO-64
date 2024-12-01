package creditos;

import javafx.scene.input.MouseEvent;

/**
 *
 * @author karim
 */
public class CreditosControl {
        private final CreditosView view;
    private final CreditosModel modelo;

    /**
     * Constructor que inicializa el controlador con una vista y un modelo.
     * Configura los eventos de la vista que son manejados por esta clase.
     * 
     * @param view la vista de la pantalla de inicio
     * @param modelo el modelo asociado a la pantalla de inicio
     */
    public CreditosControl(CreditosView view, CreditosModel modelo) {
        this.view = view;
        this.modelo = modelo;
        cargarEventos(); 
    }

    /**
     * Asigna los eventos de la vista a sus respectivos métodos manejadores,
     * permitiendo que el controlador gestione la lógica cuando ocurren.
     */
    private void cargarEventos() {
        view.irInicio(this::irInicio);
    }

    /**
     * Maneja el evento de iniciar el modo de juego. Llama al modelo para 
     * notificar que se debe activar el modo de juego.
     * 
     * @param e el evento de ratón que activa este método
     */
    private void irInicio(MouseEvent e) {
        this.modelo.avisarIrInicio();
    }

}
