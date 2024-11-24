/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class PartidaDTO implements Serializable {
    private List<JugadorDTO> jugadores;
    private int fichasPorJugador;
    private String codigoPartida;

    public PartidaDTO(){
        this.jugadores = new ArrayList<>();
    }
    
    public PartidaDTO(String codigoPartida){
        this.codigoPartida = codigoPartida;
        jugadores = new ArrayList<>();
    }
    
    public String getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public int getFichasPorJugador() {
        return fichasPorJugador;
    }

    public void setFichasPorJugador(int fichasPorJugador) {
        this.fichasPorJugador = fichasPorJugador;
    }
    
    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PartidaDTO{");
        sb.append("jugadores=").append(jugadores).append("\n");
        sb.append(", fichasPorJugador=").append(fichasPorJugador).append("\n");
        sb.append(", codigoPartida=").append(codigoPartida).append("\n");
        sb.append('}');
        return sb.toString();
    }
    
    
}
