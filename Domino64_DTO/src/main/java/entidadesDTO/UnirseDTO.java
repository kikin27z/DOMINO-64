package entidadesDTO;

import java.io.Serializable;

/**
 *
 * @author karim
 */
public class UnirseDTO implements Serializable {
    private String codigoPartida;
    private CuentaDTO cuenta;

    public UnirseDTO(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public String getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }
    
    
}
