/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesDTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class PartidaDTO {
    private List<JugadorDTO> jugadores;
    private int cantidadJugadores;
    private int fichasPorJugador;
    private String codigoPartida;
    private TableroDTO tablero;
    private PozoDTO pozo;

    public PartidaDTO(){
        this.pozo = new PozoDTO();
        this.tablero = new TableroDTO();
        this.jugadores = new ArrayList<>();
    }
    
    public PozoDTO getPozo() {
        return pozo;
    }

    public void setPozo(PozoDTO pozo) {
        this.pozo = pozo;
    }

    public TableroDTO getTablero() {
        return tablero;
    }

    public void setTablero(TableroDTO tablero) {
        this.tablero = tablero;
    }

    public String getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
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
        sb.append(", cantidadJugadores=").append(cantidadJugadores).append("\n");
        sb.append(", fichasPorJugador=").append(fichasPorJugador).append("\n");
        sb.append(", codigoPartida=").append(codigoPartida).append("\n");
        sb.append(", tablero=").append(tablero).append("\n");
        sb.append(", pozo=").append(pozo).append("\n");
        sb.append('}');
        return sb.toString();
    }
    
    
}
