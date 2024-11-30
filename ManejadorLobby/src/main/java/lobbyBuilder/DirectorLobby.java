package lobbyBuilder;

import builder.DirectorEventos;
import entidades.Cuenta;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
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
    
    public EventoLobby crearEventoJugadorNuevo(LobbyDTO lobby, CuentaDTO jugador){
        builder.setIdPublicador(jugador.getId());
        builder.agregarLobby(lobby);
        builder.setPublicador(jugador);
        builder.setTipo(TipoLogicaLobby.JUGADOR_NUEVO);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoJugadorSalio(LobbyDTO lobby, CuentaDTO jugador){
        builder.agregarLobby(lobby);
        builder.setIdPublicador(jugador.getId());
        builder.setPublicador(jugador);
        builder.setTipo(TipoLogicaLobby.JUGADOR_SALIO);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoActualizarJugadoresListos(LobbyDTO lobby,CuentaDTO jugador, boolean listo){
        builder.setIdPublicador(jugador.getId());
        builder.agregarLobby(lobby);
        builder.setPublicador(jugador);
        if(listo)
            builder.setTipo(TipoLogicaLobby.JUGADOR_LISTO);
        else
            builder.setTipo(TipoLogicaLobby.JUGADOR_NO_LISTO);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoActualizarAvatares(LobbyDTO lobby,CuentaDTO cuentaActualizada){
        builder.setIdPublicador(cuentaActualizada.getId());
        builder.agregarLobby(lobby);
        builder.setPublicador(cuentaActualizada);
        builder.setTipo(TipoLogicaLobby.ACTUALIZAR_AVATARES);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoActualizarUsername(CuentaDTO cuentaActualizada){
        builder.setIdPublicador(cuentaActualizada.getId());
        builder.setCuenta(cuentaActualizada);
        builder.setTipo(TipoLogicaLobby.ACTUALIZAR_USERNAME);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoPartidaEncontrada(LobbyDTO partidaEncontrada, CuentaDTO jugador){
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(jugador);
        builder.agregarLobby(partidaEncontrada);
        builder.setTipo(TipoLogicaLobby.PARTIDA_ENCONTRADA);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoPrepararPartida(LobbyDTO partida, int idContexto){
        builder.setIdPublicador(idPublicador);
        builder.setIdContexto(idContexto);
        builder.agregarLobby(partida);
        builder.setTipo(TipoLogicaLobby.PREPARAR_PARTIDA);
        return builder.construirEvento();
    }
    
    
}
