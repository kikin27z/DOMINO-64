package eventos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.PartidaDTO;
import java.util.ArrayList;
import java.util.List;
import tiposLogicos.TipoLogicaTurno;

/**
 *
 * @author luisa M
 */
public class EventoTurno extends EventoLogico{
    private List<CuentaDTO> jugadores;
    private CuentaDTO jugador;
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
     * @param cuenta Cuenta del contexto de este evento
     */
    public void agregarCuenta(CuentaDTO cuenta){
        this.jugador = cuenta;
    }

    /**
     * Obtiene la informacion de la cuenta del contexto
     * de este evento
     * @return La cuenta como parte del contexto
     */
    public CuentaDTO getCuenta() {
        return jugador;
    }
    /**
     * Metodo que obtiene la lista de jugadores del contexto 
     * de este evento. 
     * Este metodo fue pensado para los eventos de turno para
     * designar los turnos. Debido a que cuando se designen los turnos
     * se quiere obtener la lista de jugadores en orden segun como se 
     * designaron los turnos.
     * @return La lista de jugadores como contexto del evento
     */
    public List<CuentaDTO> getJugadores(){
        return jugadores;
    }

    public void setPartida(PartidaDTO partida){
        this.partida = partida;
    }
    
    public PartidaDTO getPartida(){
        return partida;
    }
    
    @Override
    public TipoLogicaTurno getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoLogicaTurno tipo){
        this.tipo = tipo;
    }
}
