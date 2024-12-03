package entidadesDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Clase que representa un lobby para un juego.
 * Contiene información sobre las cuentas de los jugadores, su estado de disponibilidad, 
 * y el código de la partida.
 * Implementa la interfaz Serializable para permitir la serialización del objeto.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class LobbyDTO implements Serializable {
    private List<CuentaDTO> cuentas;
    private Map<CuentaDTO, Boolean> jugadoresListos;
    private CuentaDTO cuentaActual;
    private String codigo;

    /**
     * Constructor vacío que inicializa las listas de cuentas y jugadores listos.
     */
    public LobbyDTO() {
        cuentas = new ArrayList<>();
    }
    
    /**
     * Constructor que recibe el código de la partida e inicializa las estructuras necesarias.
     * 
     * @param codigoPartida El código único de la partida
     */
    public LobbyDTO(String codigoPartida) {
        jugadoresListos = new HashMap<>();
        cuentas = new ArrayList<>();
    }
    
    /**
     * Constructor que recibe la cuenta del jugador actual.
     * 
     * @param cuentaActual La cuenta del jugador actual en el lobby
     */
    public LobbyDTO(CuentaDTO cuentaActual) {
        jugadoresListos = new HashMap<>();
        cuentas = new ArrayList<>();
    }

    /**
     * Obtiene el código de la partida.
     * 
     * @return El código de la partida
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de la partida.
     * 
     * @param codigo El código a establecer
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    /**
     * Agrega los jugadores listos a la lista de jugadores en el lobby.
     * Actualiza el estado de cada jugador (si está listo o no).
     * 
     * @param listos Lista de cuentas de jugadores listos para jugar
     */
    public void agregarJugadoresListos(List<CuentaDTO> listos){
        for (CuentaDTO cuenta : cuentas) {
            jugadoresListos.put(cuenta, listos.contains(cuenta));
        }
    }
    
    /**
     * Actualiza el estado de un jugador (si está listo o no).
     * 
     * @param cuenta La cuenta del jugador cuyo estado se actualizará
     */
    public void actualizarJugadoresListos(CuentaDTO cuenta){
        jugadoresListos.computeIfPresent(cuenta, (c, b) -> (b) ? !b : b);
    }
    
    /**
     * Obtiene el mapa que indica si cada jugador está listo.
     * 
     * @return El mapa de jugadores con su estado de listo
     */
    public Map<CuentaDTO, Boolean> getMapaJugadoresListos(){
        return jugadoresListos;
    }
    
    /**
     * Obtiene la lista de jugadores que están listos.
     * 
     * @return Lista de jugadores que están listos
     */
    public List<CuentaDTO> getJugadoresListos(){
        List<CuentaDTO> jugadores = new ArrayList<>();
        this.jugadoresListos.forEach((c, b) -> {
            if (b) {
                jugadores.add(c);
            }
        });
        return jugadores;
    }
    
    /**
     * Obtiene la lista de cuentas de jugadores en el lobby.
     * 
     * @return La lista de cuentas de los jugadores
     */
    public List<CuentaDTO> getCuentas() {
        return cuentas;
    }
    
    /**
     * Genera un código único para la partida.
     */
    public void generarCodigo(){
        setCodigo();
    }
    
    /**
     * Establece la lista de cuentas de los jugadores.
     * 
     * @param cuentas La lista de cuentas de los jugadores
     */
    public void setCuentas(List<CuentaDTO> cuentas) {
        this.cuentas = cuentas;
    }

    /**
     * Asigna un ID único a la cuenta del jugador actual.
     * 
     * @param id El ID a asignar a la cuenta del jugador actual
     */
    public void asignarIdJugadorActual(String id){
        cuentaActual.setIdCadena(id);
    }

    /**
     * Obtiene la cuenta del jugador actual.
     * 
     * @return La cuenta del jugador actual
     */
    public CuentaDTO getCuentaActual() {
        return cuentaActual;
    }

    /**
     * Establece la cuenta del jugador actual.
     * 
     * @param cuentaActual La cuenta del jugador actual
     */
    public void setCuentaActual(CuentaDTO cuentaActual) {
        this.cuentaActual = cuentaActual;
    }
    
    /**
     * Método privado que genera el código de la partida.
     * El código consta de 6 caracteres numéricos, separados por un guion en la tercera posición.
     */
    private void setCodigo(){
        Random rnd = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (i == 3) {
                builder.append('-');
            }
            builder.append(rnd.nextInt(10));
        }
        this.codigo = builder.toString();
    }

    /**
     * Método que devuelve una representación en cadena de la instancia de LobbyDTO.
     * 
     * @return Una cadena con la información del lobby
     */
    @Override
    public String toString() {
        return "LobbyDTO{" + "cuentas=" + cuentas + ", cuentaActual=" + cuentaActual + ", codigo=" + codigo + '}';
    }
}
