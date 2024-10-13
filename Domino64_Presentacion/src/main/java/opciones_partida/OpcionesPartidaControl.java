package opciones_partida;

import javafx.scene.input.MouseEvent;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class OpcionesPartidaControl {
    private OpcionesPartidaView view;
    private OpcionesPartidaModel modelo;

    public OpcionesPartidaControl() {
    }

    
    
    public OpcionesPartidaControl(OpcionesPartidaView view, OpcionesPartidaModel modelo) {
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
        view.crearNuevaPartida(this::crearNuevaPartida);  // Asigna el evento para el modo Offline.
        view.unirsePartida(this::unirsePartida);    // Asigna el evento para el modo Online.
    }


    private void crearNuevaPartida(MouseEvent e) {
        System.out.println("Nueva partida");
    }


    private void unirsePartida(MouseEvent e) {
        view.mostrarVentanaBuscarPartida();
    }
}
