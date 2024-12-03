package entidadesDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un jugador en el juego. Cada jugador está asociado a
 * una cuenta y tiene un conjunto de fichas. Puede seleccionar una ficha
 * específica de su conjunto para jugar.
 *
 * @author luisa M
 */
public class JugadorDTO implements Serializable {

    private CuentaDTO cuenta;
    private List<FichaDTO> fichas;
    private FichaDTO fichaSeleccionada;

    public JugadorDTO(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }

    public JugadorDTO() {
        fichas = new ArrayList<>();
    }

    public List<FichaDTO> getFichas() {
        return fichas;
    }

    public void setFichas(List<FichaDTO> fichas) {
        this.fichas = fichas;
    }

    public FichaDTO getFichaSeleccionada() {
        return fichaSeleccionada;
    }

    public void setFichaSeleccionada(FichaDTO fichaSeleccionada) {
        this.fichaSeleccionada = fichaSeleccionada;
    }

    public void removerFicha(FichaDTO ficha) {
        this.fichas.remove(ficha);
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador{");
        sb.append("username=").append(cuenta.getUsername());
        sb.append('}');
        return sb.toString();
    }
}
