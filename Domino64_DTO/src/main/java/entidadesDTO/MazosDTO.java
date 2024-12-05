package entidadesDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa los mazos de fichas asociados a las cuentas de los jugadores.
 * Permite manejar la asignación y representación de los mazos de fichas en el contexto del juego.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class MazosDTO implements Serializable{
    private List<List<FichaDTO>> mazos;
    private List<CuentaDTO> cuentas;
    private List<JugadorDTO> jugadores;

    /**
     * Constructor por defecto que inicializa la lista de mazos.
     */
    public MazosDTO() {
        mazos = new ArrayList<>();
    }

    /**
     * Obtiene la lista de cuentas asociadas.
     * 
     * @return una lista de {@code CuentaDTO}.
     */
    public List<CuentaDTO> getCuentas() {
        return cuentas;
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * Establece la lista de cuentas asociadas.
     * 
     * @param cuentas una lista de {@code CuentaDTO}.
     */
    public void setCuentas(List<CuentaDTO> cuentas) {
        this.cuentas = cuentas;
    }

    /**
     * Obtiene la lista de mazos.
     * 
     * @return una lista de listas de {@code FichaDTO}.
     */
    public List<List<FichaDTO>> getMazos() {
        return mazos;
    }

    /**
     * Agrega un nuevo mazo de fichas a la lista de mazos.
     * 
     * @param fichas una lista de {@code FichaDTO} que representa un mazo.
     */
    public void agregarUnMazo(List<FichaDTO> fichas) {
        mazos.add(fichas);
    }

    /**
     * Representación en forma de cadena del contenido de la clase,
     * mostrando las cuentas y los mazos asociados.
     * 
     * @return una cadena que describe las cuentas y sus mazos.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (cuentas != null) {
            for (int i = 0; i < cuentas.size(); i++) {
                sb.append("Cuenta con id: ").append(cuentas.get(i).getIdCadena()).append("\n");
            }
        }

        sb.append("\n");
        for (int i = 0; i < mazos.size(); i++) {
            sb.append("Mazo #").append(i + 1).append(": ").append(mazos.get(i)).append("\n");
        }

        return sb.toString();
    }
}
