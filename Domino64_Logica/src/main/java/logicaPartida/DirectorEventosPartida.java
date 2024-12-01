package logicaPartida;

import logicaLobby.*;
import utilities.*;
import builder.DirectorEventos;
import entidades.Cuenta;
import entidades.Ficha;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
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
public class DirectorEventosPartida extends DirectorEventos<BuilderEventoJugador>{
    private int idContextoPartida;
    private CuentaDTO publicador;
    
    public DirectorEventosPartida(BuilderEventoJugador builder, CuentaDTO publicador) {
        super(builder);
        this.publicador = publicador;
    }

    protected void setIdPartida(int id){
        this.idContextoPartida = id;
    }
    
    public EventoJugador crearEventoColocarFicha(JugadaRealizadaDTO jugada){
        builder.setContexto(jugada);
        builder.setIdContexto(idContextoPartida);
        builder.setIdPublicador(publicador.getId());
        builder.setPublicador(publicador);
        builder.setTipo(TiposJugador.COLOCAR_FICHA);
        return builder.construirEvento();
    }
    
    public EventoJugador irEventoLobby(CuentaDTO cuenta, int id){
        builder.setPublicador(cuenta);
        builder.setIdPublicador(id);
        builder.setTipo(TiposJugador.CREAR_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoJalarFicha(){
        builder.setPublicador(publicador);
        builder.setIdContexto(idContextoPartida);
        builder.setIdPublicador(publicador.getId());
        builder.setTipo(TiposJugador.JALAR_FICHA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoAbandonarPartida() {
        builder.setPublicador(publicador);
        builder.setIdContexto(idContextoPartida);
        builder.setIdPublicador(publicador.getId());
        builder.setTipo(TiposJugador.ABANDONAR_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoPeticionRendirse(){
        builder.setPublicador(publicador);
        builder.setIdContexto(idContextoPartida);
        builder.setIdPublicador(publicador.getId());
        builder.setTipo(TiposJugador.PETICION_RENDIRSE);
        return builder.construirEvento();
    }
    
    public EventoJugador crearEventoPasarTurno(){
        builder.setIdContexto(idContextoPartida);
        builder.setPublicador(publicador);
        builder.setIdPublicador(publicador.getId());
        builder.setTipo(TiposJugador.PASAR_TURNO);
        return builder.construirEvento();
    }
}
