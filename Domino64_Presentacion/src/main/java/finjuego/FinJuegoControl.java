package finjuego;

import javafx.scene.input.MouseEvent;


/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class FinJuegoControl {
    private FinJuegoModel modelo;
    private FinJuegoView view;

    public FinJuegoControl(FinJuegoView view, FinJuegoModel modelo) {
        this.modelo = modelo;
        this.view = view;
        cargarEventos();
    }

    private void cargarEventos() {
        view.abandonar(this::abandonar); 
    }
    
    private void abandonar(MouseEvent e) {
        modelo.avisarIrInicio();
    }
}
