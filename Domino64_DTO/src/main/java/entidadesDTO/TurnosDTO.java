package entidadesDTO;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class TurnosDTO implements Serializable{
    private Map<String, JugadorDTO> mazos;
    private LinkedList<String> orden;

    public TurnosDTO() {
    }
    
    public TurnosDTO(Map<String, JugadorDTO> mazos, LinkedList<String> orden) {
        this.mazos = mazos;
        this.orden = orden;
    }

    public Map<String, JugadorDTO> getMazos() {
        return mazos;
    }

    public void setMazos(Map<String, JugadorDTO> mazos) {
        this.mazos = mazos;
    }

    public LinkedList<String> getOrden() {
        return orden;
    }

    public void setOrden(LinkedList<String> orden) {
        this.orden = orden;
    }
}
