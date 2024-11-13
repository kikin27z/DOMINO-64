package lobby;

import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import eventosLobby.ObservableLobby;
import eventosLobby.ObservableLobbyMVC;
import eventosLobby.ObserverLobby;
import eventosLobby.ObserverLobbyMVC;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.AnchorPane;

/**
 * Modelo para el lobby del juego. Se encarga de manejar la lógica de negocio,
 * incluyendo la gestión de cuentas de los jugadores, la validación de nombres
 * de usuario y la notificación de cambios a los observadores. Extiende de
 * ObservableLobby para permitir la observación de cambios en el estado del
 * lobby.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class LobbyModel implements ObservableLobbyMVC, ObservableLobby {

    private List<CuentaDTO> cuentasJugadoresOnline;  // Lista de cuentas de jugadores en línea
    private Map<Integer, AnchorPane> panelesJugadores;  // Mapa de paneles de jugadores, indexados por ID
    private String mensaje;  // Mensaje de error o información
    private String encabezadoLobby;  // Encabezado del lobby
    private String[] avatares;  // Lista de avatares disponibles
    private PartidaDTO partidaDTO;
    private List<ObserverLobby> observersLogica;
    private List<ObserverLobbyMVC> observerMVC;
    private LobbyDTO lobbyDTO;
    private CuentaDTO cuentaActual;
    

    /**
     * Constructor del modelo del lobby. Inicializa los datos y carga la
     * configuración inicial.
     */
    public LobbyModel() {
        cargarDatos();  // Carga los datos iniciales
        observersLogica = new ArrayList<>();
        observerMVC = new ArrayList<>();
    }

    /**
     * Método que carga los datos iniciales para el lobby. Incluye el
     * encabezado, el estado de conexión y los avatares disponibles.
     */
    public void cargarDatos() {
        encabezadoLobby = "Bienvenidos";
        mensaje = "";
        avatares = new String[]{
            "/avatar/ave.png",
            "/avatar/gato.png",
            "/avatar/panda.png",
            "/avatar/jaguar.png",
            "/avatar/kiwi.png",
            "/avatar/mariposa.png",
            "/avatar/serpiente.png",
            "/avatar/tortuga.png",
            "/avatar/venado.png"
        };  // Lista de avatares
        panelesJugadores = new HashMap<>();
        cuentasJugadoresOnline = new ArrayList<>();
    }

    //---------------------Eventos Modelo a vista--------------------------------
    @Override
    public void agregarObserver(ObservableLobbyMVC observador) {
    }

    @Override
    public void quitarObserver(ObservableLobbyMVC observador) {
    }

    @Override
    public void actualizarNuevoJugador(CuentaDTO cuenta) {
    }

    @Override
    public void actualizarQuitarJugador(CuentaDTO cuenta) {
    }

    @Override
    public void actualizarAvatarJugador(CuentaDTO cuenta) {
    }

    @Override
    public void actualizarJugadorListo(CuentaDTO cuenta) {
    }

    @Override
    public void actualizarJugadorNoListo(CuentaDTO cuenta) {
    }

    //---------------------Eventos Modelo a lógica--------------------------------
    @Override
    public void agregarObserver(ObserverLobby observador) {
    }

    @Override
    public void quitarObserver(ObserverLobby observador) {
    }

    @Override
    public void avisarJugadorListo() {
    }

    @Override
    public void avisarJugadorNoListo() {
    }

    @Override
    public void avisarIniciarPartida() {
    }

    @Override
    public void avisarAbandonar() {
    }

    //--------------GETTERS && SETTERS-------------------
    /**
     * Obtiene el mensaje de error o información actual.
     *
     * @return el mensaje actual.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece un nuevo mensaje y notifica a los observadores.
     *
     * @param mensaje el nuevo mensaje a establecer.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
//////////////////////////////        this.notificarActualizarMensaje();  // Notifica a los observadores sobre el cambio
    }

    /**
     * Obtiene el encabezado del lobby.
     *
     * @return el encabezado del lobby.
     */
    public String getEncabezadoLobby() {
        return encabezadoLobby;  // Retorna el encabezado del lobby
    }


    /**
     * Establece un nuevo encabezado para el lobby.
     *
     * @param encabezadoLobby el nuevo encabezado a establecer.
     */
    public void setEncabezadoLobby(String encabezadoLobby) {
        this.encabezadoLobby = encabezadoLobby;
    }


    /**
     * Obtiene el panel del jugador correspondiente a un ID.
     *
     * @param id el ID del jugador.
     * @return el panel del jugador correspondiente, o null si no existe.
     */
    public AnchorPane obtenerPanelJugador(Integer id) {
        return panelesJugadores.get(id);
    }

    /**
     * Agrega un panel de jugador al mapa de paneles.
     *
     * @param id el ID del jugador.
     * @param panelJugador el panel a agregar.
     */
    public void agregarPanelJugador(Integer id, AnchorPane panelJugador) {
        panelesJugadores.put(id, panelJugador);
    }

    /**
     * Obtiene el mapa de paneles de jugadores.
     *
     * @return el mapa de paneles de jugadores.
     */
    public Map<Integer, AnchorPane> getPanelesJugadores() {
        return panelesJugadores;
    }

    /**
     * Establece un nuevo mapa de paneles de jugadores.
     *
     * @param panelesJugadores el nuevo mapa de paneles a establecer.
     */
    public void setPanelesJugadores(Map<Integer, AnchorPane> panelesJugadores) {
        this.panelesJugadores = panelesJugadores;
    }

    /**
     * Obtiene la lista de avatares disponibles.
     *
     * @return un array con las rutas de los avatares.
     */
    public String[] getAvatares() {
        return avatares;
    }

    public PartidaDTO getPartidaDTO() {
        return partidaDTO;
    }

    public void setPartidaDTO(PartidaDTO partidaDTO) {
        this.partidaDTO = partidaDTO;
    }

    public LobbyDTO getLobbyDTO() {
        return lobbyDTO;
    }

    public void setLobbyDTO(LobbyDTO lobbyDTO) {
        this.lobbyDTO = lobbyDTO;
        this.cuentaActual = lobbyDTO.getCuentaActual();
    }
    
    public String obtenerIdCuentaActual(){
        return cuentaActual.getIdCadena();
    }
    
    public String obtenerCodigoPartida(){
        return lobbyDTO.getCodigoPartida();
    }
}
