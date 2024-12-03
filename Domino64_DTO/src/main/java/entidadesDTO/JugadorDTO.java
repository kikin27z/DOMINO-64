package entidadesDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un jugador en el juego. Cada jugador está asociado a
 * una cuenta y tiene un conjunto de fichas. Puede seleccionar una ficha
 * específica de su conjunto para jugar.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class JugadorDTO implements Serializable {

    // Cuenta asociada al jugador
    private CuentaDTO cuenta;

    // Conjunto de fichas del jugador
    private List<FichaDTO> fichas;

    // Ficha seleccionada por el jugador para jugar
    private FichaDTO fichaSeleccionada;

    /**
     * Constructor que inicializa el jugador con una cuenta dada.
     * 
     * @param cuenta La cuenta del jugador
     */
    public JugadorDTO(CuentaDTO cuenta) {
        this.cuenta = cuenta;
        this.fichas = new ArrayList<>();
    }

    /**
     * Constructor vacío que inicializa la lista de fichas del jugador.
     */
    public JugadorDTO() {
        fichas = new ArrayList<>();
    }

    /**
     * Obtiene las fichas del jugador.
     * 
     * @return La lista de fichas del jugador
     */
    public List<FichaDTO> getFichas() {
        return fichas;
    }

    /**
     * Establece las fichas del jugador.
     * 
     * @param fichas La lista de fichas a establecer
     */
    public void setFichas(List<FichaDTO> fichas) {
        this.fichas = fichas;
    }

    /**
     * Obtiene la ficha seleccionada por el jugador.
     * 
     * @return La ficha seleccionada
     */
    public FichaDTO getFichaSeleccionada() {
        return fichaSeleccionada;
    }

    /**
     * Establece la ficha seleccionada por el jugador.
     * 
     * @param fichaSeleccionada La ficha a seleccionar
     */
    public void setFichaSeleccionada(FichaDTO fichaSeleccionada) {
        this.fichaSeleccionada = fichaSeleccionada;
    }

    /**
     * Remueve una ficha del conjunto de fichas del jugador.
     * 
     * @param ficha La ficha a remover
     */
    public void removerFicha(FichaDTO ficha) {
        this.fichas.remove(ficha);
    }

    /**
     * Obtiene la cuenta del jugador.
     * 
     * @return La cuenta asociada al jugador
     */
    public CuentaDTO getCuenta() {
        return cuenta;
    }

    /**
     * Establece la cuenta del jugador.
     * 
     * @param cuenta La cuenta a asociar con el jugador
     */
    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Representa al jugador como una cadena de texto, mostrando su nombre de usuario.
     * 
     * @return Una representación en cadena del jugador
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador{");
        sb.append("username=").append(cuenta.getUsername());
        sb.append('}');
        return sb.toString();
    }
}
