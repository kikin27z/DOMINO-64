package turnosBuilder;

import builder.EventBuilder;
import eventoBase.Evento;
import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.TurnosDTO;
import eventos.EventoTurno;
import tiposLogicos.TipoLogicaTurno;

/**
 *
 * @author karim
 */
public class BuilderEventoTurnos implements EventBuilder<TipoLogicaTurno>{
    private EventoTurno evento;

    public BuilderEventoTurnos() {
        this.evento =  new EventoTurno();
    }
    
    public void setTurnos(TurnosDTO turnos){
        evento.setTurnos(turnos);
    }
    
    public void setCuenta(CuentaDTO cuenta){
        evento.setCuenta(cuenta);
    }
    
    @Override
    public void setTipo(TipoLogicaTurno tipo) {
        evento.setTipo(tipo);
    }

    @Override
    public void setIdPublicador(int idPublicador) {
        evento.setIdPublicador(idPublicador);
    }

    @Override
    public void setIdDestinatario(int idDestinatario) {
        evento.setIdDestinatario(idDestinatario);
    }

    @Override
    public void setIdContexto(int idContexto) {
        evento.setIdContexto(idContexto);
    }
    
    public void setJugada(JugadaDTO jugada){
        evento.setJugada(jugada);
    }

    @Override
    public EventoTurno construirEvento() {
        EventoTurno resultado = evento;
        reiniciar();
        return resultado;
    }

    @Override
    public void reiniciar() {
        evento = new EventoTurno();
    }
    
}
