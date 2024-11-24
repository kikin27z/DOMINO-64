/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.JugadaValidaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import tiposEventos.TipoDisplayMVC;

/**
 *
 * @author luisa M
 */
public class EventoMVCDisplay extends EventoMVC {
    private TipoDisplayMVC tipo;
    private Object dtoContexto;
    private CuentaDTO cuenta;
    private LobbyDTO lobby;
    private FichaDTO ficha;
    private JugadorDTO jugador;
    private PartidaDTO partida;
    private JugadaRealizadaDTO jugadaRealizada;
    private JugadaValidaDTO jugadaValida;
    
    @Override
    public void agregarContexto(Object contexto) {
        dtoContexto = contexto;
    }

    public void setTipo(TipoDisplayMVC tipo){
        this.tipo = tipo;
    }
    
    @Override
    public TipoDisplayMVC getTipo(){
        return tipo;
    }
    
    @Override
    public Object getInfo() {
        return dtoContexto;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }

    public LobbyDTO getLobby() {
        return lobby;
    }

    public void setLobby(LobbyDTO lobby) {
        this.lobby = lobby;
    }

    public FichaDTO getFicha() {
        return ficha;
    }

    public void setFicha(FichaDTO ficha) {
        this.ficha = ficha;
    }

    public JugadorDTO getJugador() {
        return jugador;
    }

    public void setJugador(JugadorDTO jugador) {
        this.jugador = jugador;
    }

    public PartidaDTO getPartida() {
        return partida;
    }

    public void setPartida(PartidaDTO partida) {
        this.partida = partida;
    }

    public JugadaRealizadaDTO getJugadaRealizada() {
        return jugadaRealizada;
    }

    public void setJugadaRealizada(JugadaRealizadaDTO jugadaRealizada) {
        this.jugadaRealizada = jugadaRealizada;
    }

    public JugadaValidaDTO getJugadaValida() {
        return jugadaValida;
    }

    public void setJugadaValida(JugadaValidaDTO jugadaValida) {
        this.jugadaValida = jugadaValida;
    }

    @Override
    public int getIdContexto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
