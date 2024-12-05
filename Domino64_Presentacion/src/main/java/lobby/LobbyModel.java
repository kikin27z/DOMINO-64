package lobby;

import entidadesDTO.AvatarDTO;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.ReglasDTO;
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
    private Map<String, AnchorPane> panelesJugadores;  // Mapa de paneles de jugadores, indexados por ID
    private String mensaje;  // Mensaje de error o información
    private String encabezadoLobby;  // Encabezado del lobby
    private List<AvatarDTO> avatares;  // Lista de avatares disponibles
    private final List<ObserverLobby> observersLogica;
    private List<ObserverLobbyMVC> observersMVC;
    private LobbyDTO lobbyDTO;
    private CuentaDTO cuentaActual;
    private List<CuentaDTO> jugadoresListos;
    private boolean cuentaLista;
    protected final int ACTUALIZACION_CANTIDAD_JUGADORES = 2;
    protected final int ACTUALIZACION_JUGADORES_LISTOS = 0;
    protected final int REMOVER = 0;
    protected final int AGREGAR = 0;
    private boolean jugadorActualListo;
    private ReglasDTO reglas;

    /**
     * Constructor del modelo del lobby. Inicializa los datos y carga la
     * configuración inicial.
     */
    public LobbyModel(CuentaDTO cuenta, LobbyDTO lobby) {
        cuentaActual = cuenta;
        lobbyDTO = lobby;
        reglas = lobbyDTO.getReglas();
        cuentasJugadoresOnline = lobbyDTO.getCuentas();
        cuentaLista = false;
        System.out.println("Tu cuenta inicializada es" + cuentaActual);
        cargarDatos();  // Carga los datos iniciales
        jugadoresListos = new ArrayList<>();
        observersLogica = new ArrayList<>();
        observersMVC = new ArrayList<>();
    }

    /**
     * Método que carga los datos iniciales para el lobby. Incluye el
     * encabezado, el estado de conexión y los avatares disponibles.
     */
    public void cargarDatos() {
        encabezadoLobby = "Bienvenidos";
        mensaje = "";
        avatares = new ArrayList<>(List.of(AvatarDTO.AVE, AvatarDTO.GATO, AvatarDTO.JAGUAR, AvatarDTO.KIWI, AvatarDTO.MARIPOSA,
                AvatarDTO.PANDA, AvatarDTO.SERPIENTE, AvatarDTO.TORTUGA, AvatarDTO.VENADO));
        panelesJugadores = new HashMap<>();
        for (CuentaDTO cuentaDTO : cuentasJugadoresOnline) {
            panelesJugadores.put(cuentaDTO.getIdCadena(), null);
        }
        //cuentasJugadoresOnline = new ArrayList<>();
    }

    //---------------------Eventos Modelo a vista--------------------------------
    @Override
    public void agregarObserver(ObserverLobbyMVC observador) {
        this.observersMVC.add(observador);
    }

    @Override
    public void quitarObserver(ObserverLobbyMVC observador) {
        this.observersMVC.remove(observador);
    }

    @Override
    public void inicializarLobby(LobbyDTO lobby) {
        this.lobbyDTO = lobby;
        System.out.println("en vista de lobby" + lobbyDTO);

        for (ObserverLobbyMVC observerLobby : observersMVC) {
            observerLobby.inicializarLobby(lobby);
        }
    }

    @Override
    public void actualizarNuevoJugador(CuentaDTO cuenta) {
        this.cuentasJugadoresOnline.add(cuenta);
        this.panelesJugadores.put(cuenta.getIdCadena(), null);
        for (ObserverLobbyMVC ob : observersMVC) {
            ob.actualizarNuevoJugador(cuenta);
        }
       
    }

    @Override
    public void actualizarQuitarCuenta(CuentaDTO cuenta) {
        System.out.println("Quitar esta cuenta " + cuenta);
        this.cuentasJugadoresOnline.removeIf(c -> c.getIdCadena().equals(cuenta.getIdCadena()));
        this.panelesJugadores.remove(cuenta.getIdCadena());
        for (ObserverLobbyMVC ob : observersMVC) {
            ob.actualizarQuitarCuenta(cuenta);
        }
    }

    @Override
    public void actualizarAvatarCuenta(CuentaDTO cuenta) {
        for (ObserverLobbyMVC ob : observersMVC) {
            ob.actualizarAvatarCuenta(cuenta);
        }
//        observerMVC.actualizarAvatarCuenta(cuenta);
    }

    @Override
    public void actualizarCuentaLista(CuentaDTO cuenta) {
        System.out.println("Cuenta esta lista" + cuenta);
        if(cuenta.getIdCadena().equals(cuentaActual.getIdCadena())){
            jugadorActualListo = true;
        }
        jugadoresListos.add(cuenta);
        for (ObserverLobbyMVC ob : observersMVC) {
            ob.actualizarCuentaLista(cuenta);
        }
        //observerMVC.actualizarCuentaLista(cuenta);
    }

    @Override
    public void actualizarCuentaNoLista(CuentaDTO cuenta) {
        System.out.println("Cuenta no esta lista" + cuenta);

        if(cuenta.getIdCadena().equals(cuentaActual.getIdCadena())){
            jugadorActualListo = false;
        }
        jugadoresListos.remove(cuenta);
        for (ObserverLobbyMVC ob : observersMVC) {
            ob.actualizarCuentaNoLista(cuenta);
        }
//        observerMVC.actualizarCuentaNoLista(cuenta);
    }

    @Override
    public void mostrarVentanaAvatares(List<AvatarDTO> avataresUsados) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void avisarActualizarReglas(ReglasDTO reglas) {
        this.reglas = reglas;
        for (ObserverLobby observerLobby : observersLogica) {
            observerLobby.avisarActualizarReglas(reglas);
        }
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
    public void avisarCambioAvatar(CuentaDTO cuentaActualizada) {
        for (ObserverLobby observerLobby : observersLogica) {
            observerLobby.avisarCambioAvatar(cuentaActualizada);
        }
        for (ObserverLobbyMVC ob : observersMVC) {
            ob.actualizarAvatarCuenta(cuentaActualizada);
        }
        
    }

    @Override
    public void avisarCuentaLista() {
        this.cuentaLista = true;
        jugadoresListos.add(cuentaActual);
        for (ObserverLobby observerLobby : observersLogica) {
            observerLobby.avisarCuentaLista();
        }
        for (ObserverLobbyMVC ob : observersMVC) {
            ob.actualizarCuentaLista(cuentaActual);
        }
    }

    @Override
    public void avisarCuentaNoLista() {
        this.cuentaLista = false;
        jugadoresListos.remove(cuentaActual);
        for (ObserverLobby observerLobby : observersLogica) {
            observerLobby.avisarCuentaNoLista();
        }
        for (ObserverLobbyMVC ob : observersMVC) {
            ob.actualizarCuentaNoLista(cuentaActual);
        }
    }

    @Override
    public void avisarIniciarPartida() {
    }

    @Override
    public void avisarAbandonar() {
        for (ObserverLobby observerLobby : observersLogica) {
            observerLobby.avisarAbandonar();
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

    public void setAvatar(AvatarDTO avatar){
        this.cuentaActual.setAvatar(avatar);
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
        System.out.println("anchorPane: " + panelesJugadores.get(id));
        return panelesJugadores.get(id);
    }

    /**
     * Agrega un panel de jugador al mapa de paneles.
     *
     * @param id el ID del jugador.
     * @param panelJugador el panel a agregar.
     */
    public void agregarPanelJugador(String id, AnchorPane panelJugador) {
        panelesJugadores.replace(id, null, panelJugador);
        System.out.println("");
        System.out.println("panelesss: " + panelesJugadores);
        System.out.println("");
        //panelesJugadores.put(id, panelJugador);
        //panelesJugadores.compute(id, (idC, pane) -> pane = panelJugador);
    }

    /**
     * Remueve del mapeo de paneles el panel del jugador con el id del
     * parametro. Este metodo se usa cuando un jugador sale del lobby; el panel
     * del jugador se remueve visualmente, por lo tanto tambien debe de quitar
     * el mapeo
     *
     * @param id el ID del jugador
     */
    public void removerPanelJugador(String id) {
        panelesJugadores.remove(id);
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
        System.out.println("paneles en modelo: " + this.panelesJugadores);
        System.out.println("");
        System.out.println("");
    }

    protected AvatarDTO getAvatarPorAnimal(String nombreAnimal) {
        for (AvatarDTO avatar : avatares) {
            if (avatar.getAnimal().equals(nombreAnimal)) {
                return avatar;
            }
        }
        return null;
    }

    protected void setReglasDTO(ReglasDTO reglas){
        this.reglas = reglas;
    }
    
    /**
     * Obtiene la lista de avatares disponibles.
     *
     * @return un array con las rutas de los avatares.
     */
    public List<AvatarDTO> getAvatares() {
        return avatares;
    }

    public List<CuentaDTO> getCuentas() {
//        return lobbyDTO.getCuentas();
        return cuentasJugadoresOnline;
    }

    public LobbyDTO getLobbyDTO() {
        return lobbyDTO;
    }

    public void setLobbyDTO(LobbyDTO lobbyDTO) {
        this.lobbyDTO = lobbyDTO;
        this.cuentaActual = lobbyDTO.getCuentaActual();
    }

    public String obtenerIdCuentaActual() {
        return cuentaActual.getIdCadena();
    }

    public CuentaDTO getCuentaActual() {
        return cuentaActual;
    }

    public String obtenerCodigoPartida() {
        return lobbyDTO.getCodigo();
    }

    protected List<CuentaDTO> getJugadoresListos() {
        return jugadoresListos;
    }

    public void setJugadorListo(boolean listo) {
        cuentaLista = listo;

    }

    protected boolean estaListaCuenta() {
        return cuentaLista;
    }

    protected int getCantidadFichas() {
        return reglas.getCantidadFichas();
    }
////
//    protected void setCantidadFichas(int cantidadFichas) {
//        lobbyDTO.setCantidadFichas(cantidadFichas);
//        for (ObserverLobby observerLobby : observersLogica) {
//            observerLobby.avisarCambioConfig(lobbyDTO);
//        }
//    }

    public boolean esCuentaActual(CuentaDTO cuenta) {
        System.out.println("Comparar ids: " + cuentaActual.getIdCadena()  +"-"+ cuenta.getIdCadena());
        return cuenta.getIdCadena().equals(cuentaActual.getIdCadena());
    }

}
