package utilities;

import builder.DirectorEventos;
import entidades.Cuenta;
import entidades.Ficha;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
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
    
    public EventoJugador crearEventoColocarFicha(Ficha ficha, Cuenta publicador){
        builder.setInfo(ficha);
        builder.setIdPublicador(publicador.getId());
        builder.setPublicador(publicador);
        builder.setTipo(TiposJugador.COLOCAR_FICHA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoUnirsePartida(Partida partidaNueva, Cuenta nuevoJugador){
        builder.setPartida(partidaNueva);
        builder.setPublicador(nuevoJugador);
        builder.setIdPublicador(nuevoJugador.getId());
        builder.setTipo(TiposJugador.UNIRSE_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoCrearPartida(Partida partida, Cuenta creador){
        builder.setPartida(partida);
        builder.setPublicador(creador);
        builder.setIdPublicador(creador.getId());
        builder.setTipo(TiposJugador.CREAR_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoJugador irEventoLobby(CuentaDTO cuenta, int id){
        builder.setCuentaDTO(cuenta);
        builder.setIdPublicador(id);
        builder.setTipo(TiposJugador.CREAR_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoJalarFicha(Cuenta publicador){
        builder.setPublicador(publicador);
        builder.setIdPublicador(publicador.getId());
        builder.setTipo(TiposJugador.JALAR_FICHA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoAbandonarPartida(Partida partida,Cuenta publicador){
        builder.setPartida(partida);
        builder.setPublicador(publicador);
        builder.setIdPublicador(publicador.getId());
        builder.setTipo(TiposJugador.ABANDONAR_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoCambiarAvatar(Partida partida, Cuenta cuentaActualizada){
        builder.setPartida(partida);
        builder.setPublicador(cuentaActualizada);
        builder.setIdPublicador(cuentaActualizada.getId());
        builder.setTipo(TiposJugador.CAMBIAR_AVATAR);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoCambiarUsername(Partida partida, Cuenta cuentaActualizada){
        builder.setPartida(partida);
        builder.setPublicador(cuentaActualizada);
        builder.setIdPublicador(cuentaActualizada.getId());
        builder.setTipo(TiposJugador.CAMBIAR_USERNAME);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoPeticionRendirse(Cuenta publicador){
        builder.setPublicador(publicador);
        builder.setIdPublicador(publicador.getId());
        builder.setTipo(TiposJugador.PETICION_RENDIRSE);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoPasarTurno(Cuenta publicador){
        builder.setPublicador(publicador);
        builder.setIdPublicador(publicador.getId());
        builder.setTipo(TiposJugador.PASAR_TURNO);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoJugadorListo(Cuenta publicador){
        builder.setPublicador(publicador);
        builder.setIdPublicador(publicador.getId());
        builder.setTipo(TiposJugador.JUGADOR_LISTO);
        return builder.construirEvento();
    }
}
