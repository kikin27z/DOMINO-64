package lobby;

import entidadesDTO.AvatarDTO;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import eventoss.EventoMVCJugador;
import eventosLobby.ObservableLobby;
import eventosLobby.ObservableLobbyMVC;
import eventosLobby.ObserverLobby;
import eventosLobby.ObserverLobbyMVC;
import eventoss.TipoJugadorMVC;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.AnchorPane;
import presentacion_utilities.ControladorComunicacion;

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
    private static LobbyModel instance;
    private List<CuentaDTO> cuentasJugadoresOnline;  // Lista de cuentas de jugadores en línea
    private Map<String, AnchorPane> panelesJugadores;  // Mapa de paneles de jugadores, indexados por ID
    private String mensaje;  // Mensaje de error o información
    private String encabezadoLobby;  // Encabezado del lobby
    private List<AvatarDTO> avatares;  // Lista de avatares disponibles
    private PartidaDTO partidaDTO;
    private List<ObserverLobby> observersLogica;
    private ObserverLobbyMVC observerMVC;
    private LobbyDTO lobbyDTO;
    private CuentaDTO cuentaActual;
    private List<CuentaDTO> jugadoresListos;
    private boolean jugadorActualListo;
    protected final int ACTUALIZACION_CANTIDAD_JUGADORES = 2;
    protected final int ACTUALIZACION_JUGADORES_LISTOS = 0;
    protected final int REMOVER = 0;
    protected final int AGREGAR = 0;

    /**
     * Constructor del modelo del lobby. Inicializa los datos y carga la
     * configuración inicial.
     */
    private LobbyModel() {
        cargarDatos();  // Carga los datos iniciales
        jugadoresListos = new ArrayList<>();
        observersLogica = new ArrayList<>();
        //observerMVC = new ArrayList<>();
    }

    public static synchronized LobbyModel getInstance(){
        if(instance == null)
            instance = new LobbyModel();
        return instance;
    }
    
    /**
     * Método que carga los datos iniciales para el lobby. Incluye el
     * encabezado, el estado de conexión y los avatares disponibles.
     */
    public void cargarDatos() {
        encabezadoLobby = "Bienvenidos";
        mensaje = "";
        avatares = new ArrayList<>(List.of(AvatarDTO.AVE,AvatarDTO.GATO,AvatarDTO.JAGUAR,AvatarDTO.KIWI,AvatarDTO.MARIPOSA,
                AvatarDTO.PANDA,AvatarDTO.SERPIENTE,AvatarDTO.TORTUGA,AvatarDTO.VENADO));
//        avatares = new String[]{
//            "/avatar/ave.png",
//            "/avatar/gato.png",
//            "/avatar/panda.png",
//            "/avatar/jaguar.png",
//            "/avatar/kiwi.png",
//            "/avatar/mariposa.png",
//            "/avatar/serpiente.png",
//            "/avatar/tortuga.png",
//            "/avatar/venado.png"
//        };  // Lista de avatares
        panelesJugadores = new HashMap<>();
        cuentasJugadoresOnline = new ArrayList<>();
    }

    //---------------------Eventos Modelo a vista--------------------------------
    @Override
    public void agregarObserver(ObserverLobbyMVC observador) {
        observerMVC = observador;
    }

    @Override
    public void quitarObserver(ObserverLobbyMVC observador) {
        observador = null;
    }

    @Override
    public void inicializarLobby(LobbyDTO lobby){
        this.lobbyDTO = lobby;
        System.out.println("cantidad de fichas en modelo: "+lobbyDTO.getCantidadFichas());
        this.jugadoresListos = lobby.getJugadoresListos();
        if(jugadoresListos.isEmpty())
            System.out.println("no hay jugadores listos");
        this.cuentaActual = lobby.getCuentaActual();
        cuentasJugadoresOnline = lobby.getCuentas();
    }
    
    @Override
    public void actualizarNuevoJugador(CuentaDTO cuenta) {
        this.cuentasJugadoresOnline.add(cuenta);
        this.panelesJugadores.put(cuenta.getIdCadena(), null);
        observerMVC.actualizarNuevoJugador(cuenta);
    }

    @Override
    public void actualizarQuitarJugador(CuentaDTO cuenta) {
        this.cuentasJugadoresOnline.removeIf(c -> c.getId() == cuenta.getId());
        this.panelesJugadores.remove(cuenta.getIdCadena());
        observerMVC.actualizarQuitarJugador(cuenta);
    }

    @Override
    public void actualizarAvatarJugador(CuentaDTO cuenta) {
        if(cuenta.equals(cuentaActual)){
            cuentaActual.setAvatar(cuenta.getAvatar());
            cuenta.setIdCadena(cuentaActual.getIdCadena());
        }
        observerMVC.actualizarAvatarJugador(cuenta);
    }

    @Override
    public void actualizarJugadorListo(CuentaDTO cuenta) {
        if(cuenta.getId() == cuentaActual.getId()){
            jugadorActualListo = true;
        }
        jugadoresListos.add(cuenta);
        observerMVC.actualizarJugadorListo(cuenta);
    }

    @Override
    public void actualizarJugadorNoListo(CuentaDTO cuenta) {
        if(cuenta.getId() == cuentaActual.getId()){
            jugadorActualListo = false;
        }
        jugadoresListos.remove(cuenta);
        observerMVC.actualizarJugadorNoListo(cuenta);
    }

    //---------------------Eventos Modelo a lógica--------------------------------
    @Override
    public void agregarObserver(ObserverLobby observador) {
        this.observersLogica.add(observador);
    }

    @Override
    public void quitarObserver(ObserverLobby observador) {
    }

    @Override
    public void avisarCambioAvatar(CuentaDTO cuentaActualizada){
        for (ObserverLobby observerLobby : observersLogica) {
            observerLobby.avisarCambioAvatar(cuentaActualizada);
        }
    }
    
    @Override
    public void avisarJugadorListo() {
        for (ObserverLobby observerLobby : observersLogica) {
            observerLobby.avisarJugadorListo();
        }
    }

    @Override
    public void avisarJugadorNoListo() {
         for (ObserverLobby observerLobby : observersLogica) {
            observerLobby.avisarJugadorNoListo();
        }
    }

    @Override
    public void avisarIniciarPartida() {
    }

    @Override
    public void avisarAbandonar() {
//        System.out.println("avisar");
        for (ObserverLobby observerLobby : observersLogica) {
            System.out.println("observer?");
            observerLobby.avisarAbandonar(lobbyDTO);
        }
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
    public AnchorPane obtenerPanelJugador(String id) {
        System.out.println("");
        System.out.println("");
        System.out.println("anchorPane: "+panelesJugadores.get(id));
        return panelesJugadores.get(id);
    }

    /**
     * Agrega un panel de jugador al mapa de paneles.
     *
     * @param id el ID del jugador.
     * @param panelJugador el panel a agregar.
     */
    public void agregarPanelJugador(String id, AnchorPane panelJugador) {
        panelesJugadores.replace(id,null, panelJugador);
        System.out.println("");
        System.out.println("panelesss: "+panelesJugadores);
        System.out.println("");
        //panelesJugadores.put(id, panelJugador);
        //panelesJugadores.compute(id, (idC, pane) -> pane = panelJugador);
    }
    
    /**
     * Remueve del mapeo de paneles el panel del jugador
     * con el id del parametro.
     * Este metodo se usa cuando un jugador sale del lobby;
     * el panel del jugador se remueve visualmente, por lo tanto
     * tambien debe de quitar el mapeo
     *
     * @param id el ID del jugador
     */
    public void removerPanelJugador(String id) {
        //panelesJugadores.remove(id);
    }

    /**
     * Obtiene el mapa de paneles de jugadores.
     *
     * @return el mapa de paneles de jugadores.
     */
    public Map<String, AnchorPane> getPanelesJugadores() {
        return panelesJugadores;
    }

    /**
     * Establece un nuevo mapa de paneles de jugadores.
     *
     * @param panelesJugadores el nuevo mapa de paneles a establecer.
     */
    public void setPanelesJugadores(Map<String, AnchorPane> panelesJugadores) {
        this.panelesJugadores = panelesJugadores;
        System.out.println("");
        System.out.println("");
        System.out.println("paneles en modelo: "+this.panelesJugadores);
        System.out.println("");
        System.out.println("");
    }

    protected AvatarDTO getAvatarPorAnimal(String nombreAnimal){
        for (AvatarDTO avatar : avatares) {
            if(avatar.getAnimal().equals(nombreAnimal))
                return avatar;
        }
        return null;
    }
    
    /**
     * Obtiene la lista de avatares disponibles.
     *
     * @return un array con las rutas de los avatares.
     */
    public List<AvatarDTO> getAvatares() {
        return avatares;
    }

    public List<CuentaDTO> getCuentas(){
        return cuentasJugadoresOnline;
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
    
    public CuentaDTO getCuentaActual(){
        return cuentaActual;
    }    
    
    public String obtenerCodigoPartida(){
        return lobbyDTO.getCodigoPartida();
    }
    
    protected List<CuentaDTO> getJugadoresListos(){
        return jugadoresListos;
    }
    
    protected void actualizarJugadorListo(){
        jugadorActualListo = !jugadorActualListo;
        if(jugadorActualListo){
            avisarJugadorListo();
        }else
            avisarJugadorNoListo();
    }
    
    protected boolean jugadorActualListo(){
        return jugadorActualListo;
    }
    
    protected int getCantidadFichas(){
        return lobbyDTO.getCantidadFichas();
    }
    
    protected void setCantidadFichas(int cantidadFichas){
        lobbyDTO.setCantidadFichas(cantidadFichas);
        for (ObserverLobby observerLobby : observersLogica) {
            observerLobby.avisarCambioConfig(lobbyDTO);
        }
    }
}
