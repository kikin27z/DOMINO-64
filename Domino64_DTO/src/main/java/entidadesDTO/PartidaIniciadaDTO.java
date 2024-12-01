/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesDTO;

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
    
    public JugadorDTO getJugador(String idCadena) {
        return mapaJugadores.get(idCadena);
    }

}
