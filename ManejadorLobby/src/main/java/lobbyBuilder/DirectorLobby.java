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
    public DirectorLobby(BuilderEventoLobby builder, int idPublicador){
        super(builder);
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
    
    public EventoLobby crearEventoActualizarJugadoresListos(Cuenta jugador){
        builder.setIdPublicador(jugador.getId());
        builder.setInfo(jugador);
        builder.setTipo(TipoLogicaLobby.ACTUALIZAR_JUGADORES_LISTO);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoActualizarAvatares(Cuenta cuentaActualizada){
        builder.setIdPublicador(cuentaActualizada.getId());
        builder.setInfo(cuentaActualizada);
        builder.setTipo(TipoLogicaLobby.ACTUALIZAR_AVATARES);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoActualizarUsername(Cuenta cuentaActualizada){
        builder.setIdPublicador(cuentaActualizada.getId());
        builder.setInfo(cuentaActualizada);
        builder.setTipo(TipoLogicaLobby.ACTUALIZAR_USERNAME);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoPartidaEncontrada(Partida partidaEncontrada){
        builder.setIdPublicador(idPublicador);
        builder.agregarPartida(partidaEncontrada);
        builder.setTipo(TipoLogicaLobby.PARTIDA_ENCONTRADA);
        return builder.construirEvento();
    }
    
    
}
