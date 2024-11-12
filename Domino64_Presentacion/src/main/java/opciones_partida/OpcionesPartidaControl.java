package opciones_partida;

import javafx.scene.input.MouseEvent;

/**
 * Controlador de la vista de opciones de partida en el juego.
 * Gestiona la comunicación entre la vista y el modelo, asigna eventos a los botones 
 * y controla las acciones del usuario al crear, unirse o buscar partidas.
 * 
 * @autor Luisa Fernanda Morales Espinoza - 00000233450
 * @autor José Karim Franco Valencia - 00000245138
 */
public class OpcionesPartidaControl {
    private OpcionesPartidaView view;
    private OpcionesPartidaModel modelo;

    /**
     * Constructor por defecto de OpcionesPartidaControl.
     */
    public OpcionesPartidaControl() {
    }

    /**
     * Constructor que inicializa el controlador con una vista y un modelo específicos.
     * Asigna los eventos de los botones mediante el método cargarEventos.
     * 
     * @param view la vista que gestiona la interfaz de usuario de las opciones de partida
     * @param modelo el modelo que maneja la lógica de las opciones de partida
     */
    public OpcionesPartidaControl(OpcionesPartidaView view, OpcionesPartidaModel modelo) {
        this.view = view;
        this.modelo = modelo;
        cargarEventos();
    }

    /**
     * Asigna los eventos de los botones de la vista a sus respectivos métodos de control.
     */
    private void cargarEventos() {
        view.crearNuevaPartida(this::crearNuevaPartida);      // Asigna el evento para crear una partida.
        view.unirsePartida(this::unirsePartida);              // Asigna el evento para unirse a una partida.
        view.volverInicio(this::mostrarInicio);               // Asigna el evento para volver a la pantalla de inicio.
        view.buscarPartida(this::buscarPartida);              // Asigna el evento para buscar una partida.
        view.cancelarBuscarPartida(this::cancelarBuscarPartida);  // Asigna el evento para cancelar la búsqueda de partida.
    }

    /**
     * Llama al modelo para iniciar el proceso de creación de una nueva partida.
     * 
     * @param e el evento de clic de ratón asociado al botón
     */
    private void crearNuevaPartida(MouseEvent e) {
        modelo.avisarCrearPartida();
    }

    /**
     * Muestra la ventana de búsqueda de partida en la vista.
     * 
     * @param e el evento de clic de ratón asociado al botón
     */
    private void unirsePartida(MouseEvent e) {
        view.mostrarVentanaBuscarPartida();
    }

    /**
     * Llama al modelo para mostrar la pantalla de inicio.
     * 
     * @param e el evento de clic de ratón asociado al botón
     */
    private void mostrarInicio(MouseEvent e) {
        modelo.avisarMostrarInicio();
    }

    /**
     * Obtiene el código de la partida ingresado por el usuario y llama al modelo para buscar la partida.
     * 
     * @param e el evento de clic de ratón asociado al botón
     */
    private void buscarPartida(MouseEvent e) {
        modelo.setCodigoPartida(view.obtenerCodigo());
        modelo.avisarBuscarPartida();
    }

    /**
     * Cierra la ventana de búsqueda de partida en la vista.
     * 
     * @param e el evento de clic de ratón asociado al botón
     */
    private void cancelarBuscarPartida(MouseEvent e) {
        view.cerrarVentanaBuscarPartida();
    }
}
