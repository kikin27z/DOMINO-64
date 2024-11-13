package lobby;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Controlador para la pantalla del lobby del juego.
 * Se encarga de manejar la interacción entre la vista y el modelo, y gestionar los eventos generados por la interfaz de usuario.
 * Implementa el patrón MVC (Modelo-Vista-Controlador).
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class LobbyControl {
    private final LobbyView view;  // Referencia a la vista (LobbyView)
    private final LobbyModel modelo;  // Referencia al modelo (LobbyModel)

    /**
     * Constructor del controlador del lobby.
     * Inicializa la vista y el modelo, y carga los eventos de la interfaz de usuario.
     *
     * @param view la vista del lobby.
     * @param modelo el modelo que contiene la lógica del lobby.
     */
    public LobbyControl(LobbyView view, LobbyModel modelo) {
        this.view = view;
        this.modelo = modelo;
        cargarEventos();  // Carga todos los eventos de la interfaz
    }
    
    /**
     * Método que carga los eventos de la interfaz y los asocia a los métodos correspondientes.
     * Cada evento se asocia a un manejador de eventos específico.
     */
    private void cargarEventos() {
        view.mostrarConfiguracion(this::abrirConfiguracion);  // Evento para abrir la configuración
        view.mostrarAvatares(this::abrirAvatares);  // Evento para abrir la selección de avatares
        view.abandonarPartida(this::abandonarPartida);  // Evento para abandonar la partida
        view.iniciarPartida(this::iniciarPartida);  // Evento para iniciar la partida
        view.confirmarCambiosPartida(this::guardarConfiguracionPartida);  // Evento para guardar cambios en la configuración
        view.cancelarCambiosPartida(this::cancelarConfiguracionPartida);  // Evento para cancelar cambios en la configuración
        view.cerrarAvatares(this::cerrarAvatares);  // Evento para cancelar cambios en la configuración
    }
    
    //-------------------Eventos-------------------
    /**
     * Evento que se ejecuta al guardar la configuración de la partida.
     * Actualmente imprime un mensaje en la consola, pero puede extenderse para guardar los cambios en el modelo.
     *
     * @param e el evento de ratón que activa esta acción.
     */
    private void guardarConfiguracionPartida(MouseEvent e) {
    }
    
    /**
     * Evento que se ejecuta al cancelar los cambios de la configuración de la partida.
     * Cierra la ventana de configuración.
     *
     * @param e el evento de ratón que activa esta acción.
     */
    private void cancelarConfiguracionPartida(MouseEvent e) {
        view.cerrarVentanaConfiguracion();  // Cierra la ventana de configuración
    }
    
    /**
     * Evento que se ejecuta al iniciar la partida.
     * Actualmente imprime un mensaje en la consola, pero puede extenderse para iniciar la lógica del juego.
     *
     * @param e el evento de ratón que activa esta acción.
     */
    private void iniciarPartida(MouseEvent e) {
        System.out.println("Iniciar");   //-------------------------------------------------Le falta aun
    }
    
    /**
     * Evento que se ejecuta al abandonar la partida.
     * Actualmente imprime un mensaje en la consola, pero puede extenderse para gestionar la salida de la partida.
     *
     * @param e el evento de ratón que activa esta acción.
     */
    private void abandonarPartida(MouseEvent e) {
        System.out.println("Abandonar");   //-------------------------------------------------Le falta aun
    }
    
    /**
     * Evento que se ejecuta al abrir la ventana de configuración de la partida.
     * Muestra la ventana modal de configuración.
     *
     * @param e el evento de ratón que activa esta acción.
     */
    private void abrirConfiguracion(MouseEvent e) {
        view.mostrarVentanaConfiguracion();  //-------------------------------------------------Le falta aun
    }
    
    /**
     * Evento que se ejecuta al abrir la ventana de selección de avatares.
     * Muestra la ventana modal de selección de avatares.
     *
     * @param e el evento de ratón que activa esta acción.
     */
    private void abrirAvatares(MouseEvent e) {
        view.mostrarVentanaAvatares();
    }
    private void cerrarAvatares(MouseEvent e) {
        view.cerrarVentanaAvatares();
    }
    

}
