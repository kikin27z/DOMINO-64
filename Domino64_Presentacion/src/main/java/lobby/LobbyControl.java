package lobby;

import entidadesDTO.CuentaDTO;
import entidadesDTO.ReglasDTO;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import observer.Observer;

/**
 * Controlador para la pantalla del lobby del juego. Se encarga de manejar la
 * interacción entre la vista y el modelo, y gestionar los eventos generados por
 * la interfaz de usuario. Implementa el patrón MVC (Modelo-Vista-Controlador).
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class LobbyControl implements Observer<EventoLobbyMVC>{

    private LobbyView view;  // Referencia a la vista (LobbyView)
    private LobbyModel modelo;  // Referencia al modelo (LobbyModel)

    /**
     * Constructor del controlador del lobby. Inicializa la vista y el modelo, y
     * carga los eventos de la interfaz de usuario.
     *
     * @param view la vista del lobby.
     * @param modelo el modelo que contiene la lógica del lobby.
     */
    public LobbyControl(LobbyView view, LobbyModel modelo) {
        this.view = view;
        this.modelo = modelo;
        this.view.addObserver(TipoLobbyMVC.NUEVO_PANEL_JUGADOR,this);
        this.view.addObserver(TipoLobbyMVC.QUITAR_PANEL_JUGADOR,this);
        this.view.addObserver(TipoLobbyMVC.CAMBIAR_AVATAR,this);
        view.cargarAvatares();
        view.crearJugadores();
        cargarEventos();  // Carga todos los eventos de la interfaz
    }

    /**
     * Método que carga los eventos de la interfaz y los asocia a los métodos
     * correspondientes. Cada evento se asocia a un manejador de eventos
     * específico.
     */
    private void cargarEventos() {
        if(modelo.getCuentaActual().esAdmin()){
            view.mostrarConfiguracion(this::abrirConfiguracion);
        }  // Evento para abrir la configuración
        view.mostrarAvatares(this::abrirAvatares);  // Evento para abrir la selección de avatares
        view.abandonarPartida(this::abandonarPartida);  // Evento para abandonar la partida
        view.iniciarPartida(this::actualizarJugadorListo);  // Evento para iniciar la partida
        view.confirmarCambiosPartida(this::guardarConfiguracionPartida);  // Evento para guardar cambios en la configuración
        view.cancelarCambiosPartida(this::cancelarConfiguracionPartida);  // Evento para cancelar cambios en la configuración
        view.cerrarAvatares(this::cerrarAvatares);  // Evento para cancelar cambios en la configuración
    }

    //-------------------Eventos-------------------
    /**
     * Evento que se ejecuta al guardar la configuración de la partida.
     * Actualmente imprime un mensaje en la consola, pero puede extenderse para
     * guardar los cambios en el modelo.
     *
     * @param e el evento de ratón que activa esta acción.
     */
    private void guardarConfiguracionPartida(MouseEvent e) {
        System.out.println("button del mouse event: " + e.getButton().toString());
        ReglasDTO reglas = new ReglasDTO(view.getChoiceBoxSelected());
        modelo.avisarActualizarReglas(reglas);
        view.cerrarVentanaConfiguracion();
        //modelo.setCantidadFichas(0);
    }

    /**
     * Evento que se ejecuta al cancelar los cambios de la configuración de la
     * partida. Cierra la ventana de configuración.
     *
     * @param e el evento de ratón que activa esta acción.
     */
    private void cancelarConfiguracionPartida(MouseEvent e) {
        view.cerrarVentanaConfiguracion();  // Cierra la ventana de configuración
    }

    /**
     * Evento que se ejecuta al iniciar la partida. Actualmente imprime un
     * mensaje en la consola, pero puede extenderse para iniciar la lógica del
     * juego.
     *
     * @param e el evento de ratón que activa esta acción.
     */
    private void iniciarPartida(MouseEvent e) {
        System.out.println("Iniciar");   //-------------------------------------------------Le falta aun
    }

    /**
     * Evento que se ejecuta al abandonar la partida. Actualmente imprime un
     * mensaje en la consola, pero puede extenderse para gestionar la salida de
     * la partida.
     *
     * @param e el evento de ratón que activa esta acción.
     */
    private void abandonarPartida(MouseEvent e) {
        modelo.avisarAbandonar();
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

    private void setEventoAvatares(MouseEvent e) {
        System.out.println("??");
        System.out.println(e.getButton());
    }

    private void cerrarAvatares(MouseEvent e) {
        view.cerrarVentanaAvatares();
    }

    private void actualizarJugadorListo(MouseEvent e) {
        if (modelo.estaListaCuenta()) {
            modelo.avisarCuentaNoLista();
        } else {
            modelo.avisarCuentaLista();
        }
    }

    @Override
    public void update(EventoLobbyMVC observable) {
        switch (observable.getTipo()) {
            case NUEVO_PANEL_JUGADOR ->                 {
                    AnchorPane pane = (AnchorPane)observable.getElemento();
                    modelo.agregarPanelJugador(pane.getId(),pane);
                }
            case QUITAR_PANEL_JUGADOR ->                 {
                    AnchorPane pane = (AnchorPane)observable.getElemento();
                    modelo.removerPanelJugador(pane.getId());
                }
            case CAMBIAR_AVATAR -> {
                ImageView imgV = (ImageView)observable.getElemento();
                String idImg = imgV.getId();
//                AvatarDTO avatar = modelo.getAvatarPorAnimal(idImg);
//                modelo.setAvatar(avatar);
//                modelo.avisarCambioAvatar(modelo.getCuentaActual());
            }
        }
    }
}
