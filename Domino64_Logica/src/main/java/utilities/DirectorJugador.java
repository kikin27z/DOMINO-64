package utilities;

import builder.DirectorEventos;
import entidades.Cuenta;
import entidades.Ficha;
import entidades.Partida;
import eventos.EventoJugador;
import tiposLogicos.TiposJugador;

/**
 * Clase directora que se encarga de crear los eventos del jugador.
 * Esta clase la va a usar el manejador de turnos para crear los eventos
 * que el jugador puede hacer. 
 * Cada metodo es una rutina de creacion de un evento especifico, solo piden
 * los parametros necesarios para asignarle el contexto al evento.
 * @author luisa M
 */
public class DirectorJugador extends DirectorEventos<BuilderEventoJugador>{
    private Cuenta jugador;
    private Partida partida;
    
    public DirectorJugador(BuilderEventoJugador builder, Cuenta jugador) {
        super(builder);
        this.jugador = jugador;
    }
    public DirectorJugador(BuilderEventoJugador builder) {
        super(builder);
    }
    
    public void setPartida(Partida partida){
        this.partida = partida;
    }
    
    public EventoJugador crearEventoColocarFicha(Ficha ficha){
        builder.setInfo(ficha);
        builder.setIdPublicador(jugador.getId());
        builder.setPartida(partida);
        builder.setPublicador(jugador);
        builder.setTipo(TiposJugador.COLOCAR_FICHA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoUnirsePartida(Partida partida){
        builder.setPartida(partida);
        builder.setPublicador(jugador);
        builder.setIdPublicador(jugador.getId());
        builder.setTipo(TiposJugador.UNIRSE_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoCrearPartida(Partida partida){
        builder.setPartida(partida);
        builder.setPublicador(jugador);
        builder.setIdPublicador(jugador.getId());
        builder.setTipo(TiposJugador.CREAR_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoJalarFicha(){
        builder.setPublicador(jugador);
        builder.setIdPublicador(jugador.getId());
        builder.setTipo(TiposJugador.JALAR_FICHA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoAbandonarPartida(){
        builder.setPartida(partida);
        builder.setPublicador(jugador);
        builder.setIdPublicador(jugador.getId());
        builder.setTipo(TiposJugador.ABANDONAR_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoCambiarAvatar(Cuenta cuentaActualizada){
        builder.setPartida(partida);
        builder.setPublicador(cuentaActualizada);
        builder.setIdPublicador(jugador.getId());
        builder.setTipo(TiposJugador.CAMBIAR_AVATAR);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoCambiarUsername(Cuenta cuentaActualizada){
        builder.setPartida(partida);
        builder.setPublicador(cuentaActualizada);
        builder.setIdPublicador(jugador.getId());
        builder.setTipo(TiposJugador.CAMBIAR_USERNAME);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoPeticionRendirse(){
        builder.setPublicador(jugador);
        builder.setIdPublicador(jugador.getId());
        builder.setTipo(TiposJugador.PETICION_RENDIRSE);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoPasarTurno(){
        builder.setPublicador(jugador);
        builder.setIdPublicador(jugador.getId());
        builder.setTipo(TiposJugador.PASAR_TURNO);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoJugadorListo(){
        builder.setPublicador(jugador);
        builder.setIdPublicador(jugador.getId());
        builder.setTipo(TiposJugador.JUGADOR_LISTO);
        return builder.construirEvento();
    }
}
