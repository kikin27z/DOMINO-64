/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesDTO;

import java.util.List;

/**
 *
 * @author karim
 */
public class PartidaOnlineDTO {
    private CuentaDTO cuentaActual;
    private List<CuentaDTO> otrosJugadores;
    private PozoDTO pozo;

    public CuentaDTO getCuentaActual() {
        return cuentaActual;
    }

    public void setCuentaActual(CuentaDTO cuentaActual) {
        this.cuentaActual = cuentaActual;
    }

    
    public List<FichaDTO> obtenerFichasJugadorActual(){
        return cuentaActual.getJugador().getFichas();
    }
    
    public void asignarFichaOtroJugador(Integer index, List<FichaDTO> fichas){
        otrosJugadores.get(index).getJugador().setFichas(fichas);
    }
    
    public List<FichaDTO> obtenerFichasOtroJugador(Integer index){
        return otrosJugadores.get(index).getJugador().getFichas();
    }
    
    public CuentaDTO obtenerJugador(Integer index){
        return otrosJugadores.get(index);
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
