/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Luisa Morales
 */
public class PartidaIniciadaDTO implements Serializable {
    private Map<String,JugadorDTO> jugadores;
    private List<CuentaDTO> cuentas;
    private LinkedList<String> orden;
    private JugadorDTO jugadorActual;
    
    
    public PartidaIniciadaDTO(TurnosDTO turnos){
        this.jugadores = turnos.getMazos();
        this.orden = turnos.getOrden();
        initCuentas();
    }
    
    private JugadorDTO obtenerJugador(String idJugador){
        for (Map.Entry<String, JugadorDTO> entry : jugadores.entrySet()) {
            if(idJugador.equals(entry.getKey()))
                return entry.getValue();
        }
        return null;
    }
    
    public void setJugadorActual(String idJugador){
        this.jugadorActual = obtenerJugador(idJugador);
    }
    
    public JugadorDTO getJugadorActual(){
        return jugadorActual;
    }
    
    public List<String> obtenerTurnos(){
        return orden;
    }
    
    private void initCuentas(){
        cuentas = new ArrayList<>();
        for (Map.Entry<String, JugadorDTO> entry : jugadores.entrySet()) {
            cuentas.add(entry.getValue().getCuenta());
        }
    }
    
    public List<CuentaDTO> getJugadores(){
        return cuentas;
    }
    
    public int getCantidadJugadores(){
        return jugadores.size();
    }
    
    
}
