package lobbyBuilder;

import builder.DirectorEventos;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.ReglasDTO;
import eventos.EventoLobby;
import tiposLogicos.TipoLogicaLobby;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
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

    public EventoLobby crearEventoPrepararPartida(ReglasDTO reglas) {
        builder.setIdPublicador(idPublicador);
        builder.setReglas(reglas);
        builder.setTipo(TipoLogicaLobby.PREPARAR_PARTIDA);
        EventoLobby e = builder.construirEvento();
        return e;
    }
    
    public EventoLobby crearEventoCuentaAbandono(CuentaDTO cuenta, int idDestinatario){
        builder.setIdPublicador(idDestinatario);
        builder.setPublicador(cuenta);
        builder.setTipo(TipoLogicaLobby.CUENTA_ABANDONO);
        return builder.construirEvento();
    }

    public EventoLobby crearEventoJugadorNuevo(LobbyDTO lobby, CuentaDTO jugador, int idJugador) {
        builder.setIdPublicador(idJugador);
        builder.agregarLobby(lobby);
        builder.setPublicador(jugador);
        builder.setTipo(TipoLogicaLobby.CUENTA_ENTRO);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoActualizarJugadoresListos(LobbyDTO lobby, CuentaDTO jugador, int idJugador, boolean listo) {
        builder.setIdPublicador(idJugador);
        builder.agregarLobby(lobby);
        builder.setPublicador(jugador);
        if (listo) {
            builder.setTipo(TipoLogicaLobby.CUENTA_LISTO);
        } else {
            builder.setTipo(TipoLogicaLobby.CUENTA_NO_LISTO);
        }
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
    
    public EventoLobby crearEventoPartidaCreada(LobbyDTO partidaEncontrada, CuentaDTO cuenta){
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(cuenta);
        builder.agregarLobby(partidaEncontrada);
        builder.setTipo(TipoLogicaLobby.PARTIDA_CREADA);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoPartidaEncontrada(LobbyDTO partidaEncontrada, CuentaDTO cuenta, int idDestinatario){
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(cuenta);
        builder.agregarLobby(partidaEncontrada);
        //builder.setIdDestinatario(idDestinatario);
        builder.setTipo(TipoLogicaLobby.PARTIDA_ENCONTRADA);
        return builder.construirEvento();
    }
    
    public EventoLobby crearEventoActualizarAvatar(CuentaDTO cuenta, int idDestinatario){
        builder.setIdPublicador(idDestinatario);
        builder.setPublicador(cuenta);
        builder.setTipo(TipoLogicaLobby.AVATAR_ACTUALIZADO);
        return builder.construirEvento();
    }
    
    
}
