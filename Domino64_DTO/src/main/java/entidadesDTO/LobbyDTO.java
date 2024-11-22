package entidadesDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author karim
 */
public class LobbyDTO implements Serializable{
    private List<CuentaDTO> cuentas;
    private Map<CuentaDTO, Boolean> jugadoresListos;
    private String codigoPartida;
    private CuentaDTO cuentaActual;
    
    public LobbyDTO(String codigoPartida) {
        this.codigoPartida = codigoPartida;
        jugadoresListos = new HashMap<>();
        cuentas = new ArrayList<>();
    }
    
    public LobbyDTO(CuentaDTO cuentaActual) {
        jugadoresListos = new HashMap<>();
        cuentas = new ArrayList<>();
    }
    
    public void agregarJugadoresListos(List<CuentaDTO> listos){
        
        for (CuentaDTO cuenta : cuentas) {
            jugadoresListos.put(cuenta, listos.contains(cuenta));
        }
    }
    
    public void actualizarJugadoresListos(CuentaDTO cuenta){
        jugadoresListos.computeIfPresent(cuenta, (c, b) -> (b) ? !b : b);
    }
    
    public Map<CuentaDTO, Boolean> getMapaJugadoresListos(){
        return jugadoresListos;
    }
    
    public List<CuentaDTO> getJugadoresListos(){
        List<CuentaDTO> jugadores= new ArrayList<>();
        this.jugadoresListos.forEach((c, b) ->{
            if(b)
                jugadores.add(c);
        });
        return jugadores;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LobbyDTO{");
        sb.append("cuentas=").append(cuentas);
        sb.append(", codigoPartida=").append(codigoPartida);
        sb.append(", cuentaActual=").append(cuentaActual);
        sb.append(", jugadoresListos=").append(jugadoresListos);
        sb.append('}');
        return sb.toString();
    }
    
    
}