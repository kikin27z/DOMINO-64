package turnosBuilder;

import builder.DirectorEventos;
import entidadesDTO.CuentaDTO;
import entidadesDTO.TurnosDTO;
import eventos.EventoTurno;
import tiposLogicos.TipoLogicaTurno;

/**
 *
 * @author karim
 */
public class DirectorTurnos extends DirectorEventos<BuilderEventoTurnos> {
   private int idPublicador;

    public DirectorTurnos(BuilderEventoTurnos builder, int idPublicador) {
        super(builder);
        this.idPublicador = idPublicador;
    }
    
    public EventoTurno crearEventoTurnoDesignados(TurnosDTO turnos){
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TipoLogicaTurno.TURNOS_DESIGNADOS);
        builder.setTurnos(turnos);
        return builder.construirEvento();
    }

    public EventoTurno crearEventoTurnoActual(CuentaDTO cuenta,JugadaDTO jugada){
        builder.setIdPublicador(idPublicador);
        builder.setCuenta(cuenta);
        builder.setJugada(jugada);
        builder.setTipo(TipoLogicaTurno.TURNO_ACTUAL);
        return builder.construirEvento();
    }
    
    public EventoTurno crearEventoPasarTurno(CuentaDTO cuenta) {
        builder.setIdPublicador(idPublicador);
        builder.setCuenta(cuenta);
//        builder.setJugada(jugada);
        builder.setTipo(TipoLogicaTurno.PASAR_TURNO);
        return builder.construirEvento();
    }
    
    public EventoTurno crearEventoFinJuego(){
        builder.setIdPublicador(idPublicador);
        //builder.setTurnos(turnos);
        builder.setTipo(TipoLogicaTurno.FIN_JUEGO);
        return builder.construirEvento();
    }
   
}
