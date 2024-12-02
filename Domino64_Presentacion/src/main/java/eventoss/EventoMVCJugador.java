/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventoss;

import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;

/**
 *
 * @author luisa M
 */
public class EventoMVCJugador extends EventoMVC {
    private TipoJugadorMVC tipo;
    private JugadaRealizadaDTO jugada;
    private PartidaDTO partida;
    private LobbyDTO lobby;
    
    public EventoMVCJugador(){}
    
    public EventoMVCJugador(TipoJugadorMVC tipo){
        this.tipo = tipo;
    }
    
    public void setLobby(LobbyDTO lobby){
        this.lobby = lobby;
    }
    
    public LobbyDTO getLobby(){
        return lobby;
    }
    
    public void setTipo(TipoJugadorMVC tipo){
        this.tipo = tipo;
    }
    
    public void setPartida(PartidaDTO partida){
        this.partida = partida;
    }
    
    public PartidaDTO getPartida(){
        return partida;
    }
    
    public void setJugada(JugadaRealizadaDTO jugada) {
        this.jugada = jugada;
    }

    public JugadaRealizadaDTO getJugada(){
        return jugada;
    }
    
    @Override
    public TipoJugadorMVC getTipo() {
        return tipo;
    }
    
}
