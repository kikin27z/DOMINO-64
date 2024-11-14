package entidadesDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 *
 * @author karim
 */
public class LobbyDTO implements Serializable{
    private List<CuentaDTO> cuentas;
    private String codigoPartida;
    private CuentaDTO cuentaActual;

    public LobbyDTO(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }
    public LobbyDTO(CuentaDTO cuentaActual) {
    }
    

    public List<CuentaDTO> getCuentas() {
        return cuentas;
    }
    public void generarCodigo(){
        setCodigo();
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
    
    
    private void setCodigo(){
        Random rnd = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if(i==3)
                builder.append('-');
            builder.append(rnd.nextInt(10));
        }
        codigoPartida = builder.toString();
    }
}
