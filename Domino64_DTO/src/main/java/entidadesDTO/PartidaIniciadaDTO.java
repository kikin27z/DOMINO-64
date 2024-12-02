/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author luisa M
 */
public class PartidaIniciadaDTO {
    private Map<String,JugadorDTO> mapaJugadores;
    
    public PartidaIniciadaDTO(){
        this.mapaJugadores = new HashMap<>();
    }
    
    public void setJugadores(List<JugadorDTO> jugadores){
        CuentaDTO cuenta;
        for (JugadorDTO jugador : jugadores) {
            cuenta = jugador.getCuenta();
            mapaJugadores.put(cuenta.getIdCadena(), jugador);
        }
    }
    
    public List<CuentaDTO> getTurnos(){
        List<CuentaDTO> turnos = new ArrayList<>();
        for (Map.Entry<String, JugadorDTO> entry : mapaJugadores.entrySet()) {
            JugadorDTO jugador = entry.getValue();
            turnos.add(jugador.getCuenta());
        }
        return turnos;
    }
    
    public JugadorDTO getJugador(String idCadena) {
        return mapaJugadores.get(idCadena);
    }

    public CuentaDTO getPrimerTurno(){
        CuentaDTO cuenta = null;
        for (Map.Entry<String, JugadorDTO> entry : mapaJugadores.entrySet()) {
            JugadorDTO jugador = entry.getValue();
            cuenta = jugador.getCuenta();
            break;
        }
        return cuenta;
    }
    
}
