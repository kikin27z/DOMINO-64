package entidadesDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class LobbyDTO implements Serializable{
    private List<CuentaDTO> cuentas;
    private Map<CuentaDTO, Boolean> jugadoresListos;
    private CuentaDTO cuentaActual;
    private String codigo;

    public LobbyDTO() {
        cuentas = new ArrayList<>();
    }
    
    
    
    public LobbyDTO(String codigoPartida) {
        jugadoresListos = new HashMap<>();
        cuentas = new ArrayList<>();
    }
    
    public LobbyDTO(CuentaDTO cuentaActual) {
        jugadoresListos = new HashMap<>();
        cuentas = new ArrayList<>();
    }
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
    }

    @Override
    public String toString() {
        return "LobbyDTO{" + "cuentas=" + cuentas + ", cuentaActual=" + cuentaActual + ", codigo=" + codigo + '}';
    }

 

    
    
    
}
