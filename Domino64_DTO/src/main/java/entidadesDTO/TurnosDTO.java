package entidadesDTO;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Clase DTO (Data Transfer Object) que representa la información de los turnos en un juego.
 * Incluye los mazos de los jugadores y el orden de los turnos.
 * 
 * <p>Proporciona métodos de acceso para gestionar los datos relacionados con el estado de los turnos.</p>
 * 
 * <p>Esta clase implementa {@link Serializable} para permitir la serialización y deserialización 
 * de los objetos, lo cual es útil en el contexto de transferencia de datos entre diferentes capas de la aplicación.</p>
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class TurnosDTO implements Serializable {
    
    /**
     * Mapa que asocia el identificador de cada jugador (como una cadena) con su respectivo objeto {@link JugadorDTO}.
     */
    private Map<String, JugadorDTO> mazos;
    private List<JugadorDTO> jugadores;

    /**
     * Lista enlazada que define el orden de los turnos de los jugadores.
     * Cada elemento es el identificador de un jugador.
     */
    private LinkedList<String> orden;

    /**
     * Constructor por defecto.
     * Inicializa un objeto `TurnosDTO` sin valores específicos.
     */
    public TurnosDTO() {
    }

    /**
     * Constructor que inicializa un objeto `TurnosDTO` con un mapa de mazos y un orden de turnos.
     * 
     * @param mazos mapa que asocia identificadores de jugadores con sus objetos {@link JugadorDTO}.
     * @param orden lista enlazada que representa el orden de turnos de los jugadores.
     */
    public TurnosDTO(Map<String, JugadorDTO> mazos, LinkedList<String> orden) {
        this.mazos = mazos;
        this.orden = orden;
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * Obtiene el mapa de mazos de los jugadores.
     * 
     * @return el mapa que asocia identificadores de jugadores con sus objetos {@link JugadorDTO}.
     */
    public Map<String, JugadorDTO> getMazos() {
        return mazos;
    }

    /**
     * Establece el mapa de mazos de los jugadores.
     * 
     * @param mazos el nuevo mapa que asocia identificadores de jugadores con sus objetos {@link JugadorDTO}.
     */
    public void setMazos(Map<String, JugadorDTO> mazos) {
        this.mazos = mazos;
    }

    /**
     * Obtiene el orden de los turnos de los jugadores.
     * 
     * @return una lista enlazada que contiene los identificadores de los jugadores en el orden de los turnos.
     */
    public LinkedList<String> getOrden() {
        return orden;
    }

    /**
     * Establece el orden de los turnos de los jugadores.
     * 
     * @param orden una nueva lista enlazada que contiene los identificadores de los jugadores en el orden de los turnos.
     */
    public void setOrden(LinkedList<String> orden) {
        this.orden = orden;
    }
}
