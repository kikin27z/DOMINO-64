/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lobbyBuilder;

import builder.DirectorEventos;
import com.luisa.entidades.Cuenta;
import com.luisa.entidades.Partida;
import eventos.EventoLobby;
import tiposLogicos.TipoLogicaLobby;

/**
 *
 * @author luisa M
 */
public class DirectorLobby extends DirectorEventos<BuilderEventoLobby> {
    private int idPublicador;
    
    /**
     * 
     * @param builder 
     */
    public DirectorLobby(BuilderEventoLobby builder){
        super(builder);
    }
    
    public void setIdPublicador(int idPublicador){
        this.idPublicador = idPublicador;
    }
    
    public EventoLobby crearEventoJugadorNuevo(Cuenta jugador){
        builder.setIdPublicador(jugador.getId());
        builder.setInfo(jugador);
        builder.setTipo(TipoLogicaLobby.JUGADOR_NUEVO);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoJugadorSalio(Cuenta jugador){
        builder.setIdPublicador(jugador.getId());
        builder.setInfo(jugador);
        builder.setTipo(TipoLogicaLobby.JUGADOR_SALIO);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoJugadorListo(Cuenta jugador){
        builder.setIdPublicador(jugador.getId());
        builder.setInfo(jugador);
        builder.setTipo(TipoLogicaLobby.JUGADOR_LISTO);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoActualizarAvatares(Cuenta cuentaActualizada, Cuenta publicador){
        builder.setIdPublicador(publicador.getId());
        builder.setInfo(cuentaActualizada);
        builder.setPublicador(publicador);
        builder.setTipo(TipoLogicaLobby.ACTUALIZAR_AVATARES);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoActualizarUsername(Cuenta cuentaActualizada, Cuenta publicador){
        builder.setIdPublicador(publicador.getId());
        builder.setInfo(cuentaActualizada);
        builder.setPublicador(publicador);
        builder.setTipo(TipoLogicaLobby.ACTUALIZAR_USERNAME);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoPartidaEncontrada(Partida partidaEncontrada, Cuenta jugador){
        builder.setIdPublicador(idPublicador);
        builder.agregarPartida(partidaEncontrada);
        builder.setTipo(TipoLogicaLobby.PARTIDA_ENCONTRADA);
        return builder.construirEvento();
    }
    
    
}
