package eventos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import java.util.List;
import tiposLogicos.TipoLogicaTurno;

/**
 *
 * @author luisa M
 */
public class EventoTurno extends EventoLogico{
    private JugadorDTO jugador;
    private List<JugadorDTO> jugadores;
    private PartidaDTO partida;
    private TipoLogicaTurno tipo;
    
    public EventoTurno(){}
    
    public EventoTurno(TipoLogicaTurno tipo){
        super();
        this.tipo = tipo;
    }
    
    /**
     * Agrega la cuenta referente para este evento de turno.
     * Normalmente cuando se envie un evento de este tipo,
     * el contexto que se va a agregar es el jugador que esta en turno.
     * @param jugador Jugador del contexto de este evento
     */
    public void agregarJugador(JugadorDTO jugador) {
        this.jugador = jugador;
    }

    public void agregarJugadores(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * Obtiene la informacion de la cuenta del contexto
     * de este evento
     * @return El jugador como parte del contexto
     */
    public JugadorDTO getJugador() {
        return jugador;
    }
    
    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }
    
    @Override
    public TipoLogicaTurno getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoLogicaTurno tipo){
        this.tipo = tipo;
    }
}
