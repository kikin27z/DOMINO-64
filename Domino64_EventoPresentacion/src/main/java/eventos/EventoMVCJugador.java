/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import entidadesDTO.JugadorDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import tiposEventos.TipoJugadorMVC;

/**
 *
 * @author luisa M
 */
public class EventoMVCJugador extends EventoMVC<JugadorDTO> {
    private TipoJugadorMVC tipo;
    private JugadorDTO contexto;
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
    
    @Override
    public void agregarContexto(JugadorDTO contexto) {
        this.contexto = contexto;
    }

    @Override
    public TipoJugadorMVC getTipo() {
        return tipo;
    }

    @Override
    public JugadorDTO getInfo() {
        return contexto;
    }

    @Override
    public int getIdContexto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
