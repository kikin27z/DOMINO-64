package entidadesDTO;

import java.util.List;

/**
 *
 * @author karim
 */
public class PartidaOfflineDTO {
    private CuentaDTO cuentaActual;
    private CuentaDTO cpu;
    private PozoDTO pozo;

    public CuentaDTO getCuentaActual() {
        return cuentaActual;
    }

    public void setCuentaActual(CuentaDTO cuentaActual) {
        this.cuentaActual = cuentaActual;
    }

    public CuentaDTO getCpu() {
        return cpu;
    }

    public void setCpu(CuentaDTO cpu) {
        this.cpu = cpu;
    }
    
    public List<FichaDTO> obtenerFichasCPU(){
        return cpu.getJugador().getFichas();
    }
    
    public List<FichaDTO> obtenerFichasJugadorActual(){
        return cuentaActual.getJugador().getFichas();
    }
    
    public void asignarFichaCPU(List<FichaDTO> fichas){
        cpu.getJugador().setFichas(fichas);
    }
    public void asignarFichaJugadorActual(List<FichaDTO> fichas){
        cuentaActual.getJugador().setFichas(fichas);
    }

    public PozoDTO getPozo() {
        return pozo;
    }

    public void setPozo(PozoDTO pozo) {
        this.pozo = pozo;
    }
    
}
