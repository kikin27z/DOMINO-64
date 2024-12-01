package lobbyBuilder;

import builder.DirectorEventos;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
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

    
    public EventoLobby crearEventoCuentaAbandono(CuentaDTO cuenta, int idDestinatario){
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(cuenta);
        builder.setIdDestinatario(idDestinatario);
        builder.setTipo(TipoLogicaLobby.CUENTA_ABANDONO);
        return builder.construirEvento();
    }

    public EventoLobby crearEventoCuentaLista(CuentaDTO cuenta, int idDestinatario){
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(cuenta);
        builder.setIdDestinatario(idDestinatario);
        builder.setTipo(TipoLogicaLobby.CUENTA_LISTO);
        return builder.construirEvento();
    }
    public EventoLobby crearEventoCuentaNoLista(CuentaDTO cuenta, int idDestinatario){
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(cuenta);
        builder.setIdDestinatario(idDestinatario);
        builder.setTipo(TipoLogicaLobby.CUENTA_NO_LISTO);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoAbandonoAdmin(){
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TipoLogicaLobby.ABANDONO_ADMIN);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoPartidaCreada(LobbyDTO partidaEncontrada, CuentaDTO cuenta, int idDestinatario){
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(cuenta);
        builder.agregarLobby(partidaEncontrada);
        builder.setIdDestinatario(idDestinatario);
        builder.setTipo(TipoLogicaLobby.PARTIDA_CREADA);
        EventoLobby eventoLobby = builder.construirEvento();
        System.out.println(eventoLobby);
        return eventoLobby;
    }
    public EventoLobby crearEventoPartidaEncontrada(LobbyDTO partidaEncontrada, CuentaDTO cuenta, int idDestinatario){
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(cuenta);
        builder.agregarLobby(partidaEncontrada);
        builder.setIdDestinatario(idDestinatario);
        builder.setTipo(TipoLogicaLobby.PARTIDA_ENCONTRADA);
        EventoLobby eventoLobby = builder.construirEvento();
        System.out.println(eventoLobby);
        return eventoLobby;
    }
    
    
}
