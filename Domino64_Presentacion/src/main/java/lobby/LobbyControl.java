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
    private LobbyView view;  // Referencia a la vista (LobbyView)
    private LobbyModel modelo;  // Referencia al modelo (LobbyModel)

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
        view.confirmarTexto(this::seleccionNombreUsuario);  // Evento para confirmar el nombre de usuario
        view.mostrarAvatares(this::abrirAvatares);  // Evento para abrir la selección de avatares
        view.abandonarPartida(this::abandonarPartida);  // Evento para abandonar la partida
        view.iniciarPartida(this::iniciarPartida);  // Evento para iniciar la partida
        view.confirmarCambiosPartida(this::guardarConfiguracionPartida);  // Evento para guardar cambios en la configuración
        view.cancelarCambiosPartida(this::cancelarConfiguracionPartida);  // Evento para cancelar cambios en la configuración
    }
    
    //-------------------Eventos-------------------
    // protected EventHandler<ActionEvent> getHandlerIniciar() {
    //     EventHandler<ActionEvent> handler = (ActionEvent t) -> {
    //         Task<Void> longTask = new Task<Void>() {
    //             @Override
    //             protected Void call() throws Exception {
    //                 String username = view.getUsername();
    //                 JugadorDTO jugador = new JugadorDTO(username);
    //                 modelo.setJugador(jugador);
                    
    //                 Platform.runLater(() -> {
    //                     modelo.getAcciones().get(0).ejecutarAccion();
    //                 });
                    
    //                 modelo.getAcciones().get(1).ejecutarAccion();
    //                 return null;   
    //             }
                
    //         };
            
    //         new Thread(longTask).start();
    //     };
    //     return handler;
    // }
    /**
     * Evento que se ejecuta al guardar la configuración de la partida.
     * Actualmente imprime un mensaje en la consola, pero puede extenderse para guardar los cambios en el modelo.
     *
     * @param e el evento de ratón que activa esta acción.
     */
    private void guardarConfiguracionPartida(MouseEvent e) {
        System.out.println("GuardarCambios");   //-------------------------------------------------Le falta aun
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
        System.out.println("Avatares"); //-------------------------------------------------Le falta aun
    }
    
    /**
     * Evento que se ejecuta al seleccionar el nombre de usuario.
     * Registra un evento adicional para confirmar el cambio del nombre cuando el usuario hace clic fuera del campo de texto.
     *
     * @param e el evento de ratón que activa esta acción.
     */
    private void seleccionNombreUsuario(MouseEvent e) {
        if (e.getEventType() == MouseEvent.MOUSE_CLICKED) {
            TextField txtUsuario = (TextField) e.getSource();
            txtUsuario.setOnMouseExited(this::cambioNombreUsuario);
        }
    }
    
    /**
     * Evento que se ejecuta cuando el usuario sale del campo de texto del nombre de usuario.
     * Valida y actualiza el nombre de usuario en el modelo.
     *
     * @param e el evento de ratón que activa esta acción.
     */
    private void cambioNombreUsuario(MouseEvent e) {
        if (e.getEventType() == MouseEvent.MOUSE_EXITED) {
            TextField txtUsuario = (TextField) e.getSource();  // Obtiene el campo de texto
            txtUsuario.getParent().requestFocus();  // Quita el foco del campo de texto
            txtUsuario.setOnMouseExited(null);  // Desactiva el evento de salida del ratón
            logicaNombreUsuario(txtUsuario.getText());  // Llama a la lógica de validación del nombre
        }
    }

    //-------------------Lógica de control-------------------
    
    /**
     * Lógica que valida y actualiza el nombre de usuario.
     * Verifica si los caracteres son válidos, si el nombre no está repetido, y si es válido lo asigna en el modelo.
     *
     * @param nombre el nombre de usuario ingresado por el usuario.
     */
    private void logicaNombreUsuario(String nombre) {
        modelo.sonCaracteresValidosNombre(nombre);  // Valida los caracteres del nombre
        if (modelo.getMensaje() == null) {
            modelo.validarNombreNoRepetido(nombre);  // Verifica que el nombre no esté repetido
        }
        if (modelo.getMensaje() == null) {
            modelo.asignarNombreUsuarioActual(nombre);  // Asigna el nombre si es válido
        }
    }
}
