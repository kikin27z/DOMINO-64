package entidadesDTO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author karim
 */
public class LobbyDTO implements Serializable {
    private List<CuentaDTO> cuentas;
    private String codigoPartida;
    private CuentaDTO cuentaActual;

    public LobbyDTO(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public List<CuentaDTO> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaDTO> cuentas) {
        this.cuentas = cuentas;
    }

    public String getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public void asignarIdJugadorActual(String id){
        cuentaActual.setIdCadena(id);
    }

    public CuentaDTO getCuentaActual() {
        return cuentaActual;
    }

    public void setCuentaActual(CuentaDTO cuentaActual) {
        this.cuentaActual = cuentaActual;
    }
    
    
    
}
