package lobby;

import dtos.cuenta.CuentaDTO;
import com.mycompany.patrones.command.Accion;
import presentacion_utilities.NotificadorPresentacion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.AnchorPane;
import presentacion_observers.ObservableLobby;

/**
 * Modelo para el lobby del juego.
 * Se encarga de manejar la lógica de negocio, incluyendo la gestión de cuentas de los jugadores,
 * la validación de nombres de usuario y la notificación de cambios a los observadores.
 * Extiende de ObservableLobby para permitir la observación de cambios en el estado del lobby.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class LobbyModel extends ObservableLobby {
    private List<Accion> acciones;
    private NotificadorPresentacion notificador;
    private CuentaDTO cuentaActual;  // Cuenta del jugador actual
    private List<CuentaDTO> cuentasJugadoresOnline;  // Lista de cuentas de jugadores en línea
    private Map<Integer, AnchorPane> panelesJugadores;  // Mapa de paneles de jugadores, indexados por ID
    private String mensaje;  // Mensaje de error o información
    private String encabezadoLobby;  // Encabezado del lobby
    private boolean esOnline;  // Estado de conexión (online/offline)
    private String[] avatares;  // Lista de avatares disponibles

    /**
     * Constructor del modelo del lobby.
     * Inicializa los datos y carga la configuración inicial.
     */
    public LobbyModel() {
        cargarDatos();  // Carga los datos iniciales
    }

    /**
     * Método que carga los datos iniciales para el lobby.
     * Incluye el encabezado, el estado de conexión y los avatares disponibles.
     */
    public void cargarDatos() {
        encabezadoLobby = "Bienvenidos";  
        mensaje = null;  
        esOnline = true;  
        avatares = new String[]{
            "/avatar/ave.png",
            "/avatar/gato.png",
            "/avatar/jaguar.png",
            "/avatar/kiwi.png",
            "/avatar/mariposa.png",
            "/avatar/panda.png",
            "/avatar/serpiente.png",
            "/avatar/tortuga.png",
            "/avatar/venado.png"
        };  // Lista de avatares
        panelesJugadores = new HashMap<>();  
        cuentaActual = new CuentaDTO(0, "/avatar/venado.png", mensaje);  
    }

    //--------------Métodos notificadores-------------------

    /**
     * Asigna el nombre de usuario actual y notifica a los observadores.
     *
     * @param nombre el nombre de usuario a asignar.
     */
    public void asignarNombreUsuarioActual(String nombre) {
        cuentaActual.setNombre(nombre);  // Asigna el nombre a la cuenta actual
        // Aquí se debe agregar la lógica para notificar a los demás jugadores
    }

    /**
     * Actualiza el nombre de otro jugador en la vista.
     *
     * @param cuenta la cuenta del jugador cuyo nombre se actualizará.
     */
    public void actualizarNombreOtroJugador(CuentaDTO cuenta) {
        notificarActualizarNombreOtroJugador(cuenta);  // Notifica a los observadores sobre el cambio
    }

    //--------------Validadores-------------------

    /**
     * Valida si el nombre del jugador contiene caracteres válidos.
     * 
     * @param nombre el nombre a validar.
     */
    public void sonCaracteresValidosNombre(String nombre) {
        String regex = "^([a-zA-Z0-9]*( [a-zA-Z0-9]*)?){1,}$"; 
        if (nombre.isBlank()) {
            setMensaje("El nombre del jugador esta vacio");
        } else if (!nombre.matches(regex)) {
            setMensaje("Solo se aceptan números y letras");
        } else if (nombre.length() > 14) {
            setMensaje("El nombre no puede superar los 14 caracteres"); 
        } else {
            this.mensaje = null;  
        }
    }

    /**
     * Valida que el nombre de usuario no esté repetido entre los jugadores en línea.
     * 
     * @param nombre el nombre a validar.
     */
    public void validarNombreNoRepetido(String nombre) {
        for (CuentaDTO cuentaDTO : cuentasJugadoresOnline) {
            if (cuentaDTO.getNombre().equalsIgnoreCase(nombre)) {
                setMensaje("Ya tiene un usuario ese nombre");
                return;
            }
        }
        this.mensaje = null;  
    }

    //--------------GETTERS && SETTERS-------------------

    /**
     * Devuelve el nombre del usuario actual.
     * 
     * @return el nombre del usuario actual.
     */
    public String devolverNombreUsuarioActual() {
        return this.cuentaActual.getNombre();  
    }

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
        this.notificarActualizarMensaje();  // Notifica a los observadores sobre el cambio
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
     * Obtiene la cuenta actual del jugador.
     * 
     * @return la cuenta actual.
     */
    public CuentaDTO getCuentaActual() {
        return cuentaActual;
    }

    /**
     * Establece la cuenta actual del jugador.
     * 
     * @param cuentaActual la cuenta a establecer como actual.
     */
    public void setCuentaActual(CuentaDTO cuentaActual) {
        this.cuentaActual = cuentaActual;
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
     * Obtiene el ID de la cuenta actual.
     * 
     * @return el ID de la cuenta actual.
     */
    public Integer obtenerIdActual() {
        return cuentaActual.getId(); 
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
     * Indica si el lobby está en línea.
     * 
     * @return true si está en línea, false en caso contrario.
     */
    public boolean isEsOnline() {
        return esOnline;  
    }

    /**
     * Establece el estado de conexión del lobby.
     * 
     * @param esOnline el nuevo estado de conexión a establecer.
     */
    public void setEsOnline(boolean esOnline) {
        this.esOnline = esOnline; 
    }

    /**
     * Obtiene la lista de avatares disponibles.
     * 
     * @return un array con las rutas de los avatares.
     */
    public String[] getAvatares() {
        return avatares;  
    }
}
