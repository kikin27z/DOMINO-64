package utilities;

import builder.DirectorEventos;
import entidades.Cuenta;
import entidades.Ficha;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.PartidaDTO;
import entidadesDTO.LobbyDTO;
import eventos.EventoJugador;
import tiposLogicos.TiposJugador;

/**
 * Clase directora que se encarga de crear los eventos del jugador.
 * Esta clase la va a usar el manejador de turnos para crear los eventos
 * que el jugador puede hacer. 
 * Cada metodo es una rutina de creacion de un evento especifico, solo piden
 * los parametros necesarios para asignarle el contexto al evento.
* @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class DirectorJugador extends DirectorEventos<BuilderEventoJugador>{

    public DirectorJugador(BuilderEventoJugador builder) {
        super(builder);
    }

    public EventoJugador crearEventoColocarFicha(FichaDTO ficha, CuentaDTO publicador){
        builder.setContexto(ficha);
        builder.setIdPublicador(publicador.getId());
        builder.setPublicador(publicador);
        builder.setTipo(TiposJugador.COLOCAR_FICHA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoUnirsePartida(LobbyDTO partidaNueva, CuentaDTO nuevoJugador){
        builder.setLobbyDTO(partidaNueva);
        builder.setPublicador(nuevoJugador);
        builder.setIdPublicador(nuevoJugador.getId());
        builder.setTipo(TiposJugador.UNIRSE_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoCrearPartida(CuentaDTO creador){
        builder.setPublicador(creador);
        builder.setIdPublicador(creador.getId());
        builder.setTipo(TiposJugador.CREAR_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoJugador irEventoLobby(CuentaDTO cuenta, int id){
        builder.setPublicador(cuenta);
        builder.setIdPublicador(id);
        builder.setTipo(TiposJugador.CREAR_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoJalarFicha(CuentaDTO publicador){
        builder.setPublicador(publicador);
        builder.setIdPublicador(publicador.getId());
        builder.setTipo(TiposJugador.JALAR_FICHA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoAbandonarPartida(LobbyDTO lobby,CuentaDTO publicador){
        builder.setLobbyDTO(lobby);
        builder.setPublicador(publicador);
        builder.setIdPublicador(publicador.getId());
        builder.setTipo(TiposJugador.ABANDONAR_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoCambiarAvatar(LobbyDTO lobby, CuentaDTO cuentaActualizada){
        builder.setLobbyDTO(lobby);
        builder.setPublicador(cuentaActualizada);
        builder.setIdPublicador(cuentaActualizada.getId());
        builder.setTipo(TiposJugador.CAMBIAR_AVATAR);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoCambiarUsername(LobbyDTO lobby, CuentaDTO cuentaActualizada){
        builder.setLobbyDTO(lobby);
        builder.setPublicador(cuentaActualizada);
        builder.setIdPublicador(cuentaActualizada.getId());
        builder.setTipo(TiposJugador.CAMBIAR_USERNAME);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoPeticionRendirse(CuentaDTO publicador){
        builder.setPublicador(publicador);
        builder.setIdPublicador(publicador.getId());
        builder.setTipo(TiposJugador.PETICION_RENDIRSE);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoPasarTurno(CuentaDTO publicador){
        builder.setPublicador(publicador);
        builder.setIdPublicador(publicador.getId());
        builder.setTipo(TiposJugador.PASAR_TURNO);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoActualizarJugadorListo(LobbyDTO lobby, CuentaDTO publicador, boolean listo){
        builder.setLobbyDTO(lobby);
        builder.setPublicador(publicador);
        builder.setIdPublicador(publicador.getId());
        if(listo)
            builder.setTipo(TiposJugador.JUGADOR_LISTO);
        else
            builder.setTipo(TiposJugador.JUGADOR_NO_LISTO);
        return builder.construirEvento();
    }
}
