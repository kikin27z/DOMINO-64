package eventos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.TurnosDTO;
import tiposLogicos.TipoLogicaTurno;

/**
 *
 * @author luisa M
 */
public class EventoTurno extends EventoLogico{
    private TipoLogicaTurno tipo;
    private TurnosDTO turnos;
    private CuentaDTO cuenta;
    private JugadaDTO jugada;
    
    public EventoTurno(){}
    
    public EventoTurno(TipoLogicaTurno tipo){
        super();
        this.tipo = tipo;
    }
   
    @Override
    public TipoLogicaTurno getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoLogicaTurno tipo){
        this.tipo = tipo;
    }

    public void setTurnos(TurnosDTO turnos) {
        this.turnos = turnos;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }

    public JugadaDTO getJugada() {
        return jugada;
    }

    public void setJugada(JugadaDTO jugada) {
        this.jugada = jugada;
    }
    
    public TurnosDTO getTurnos() {
        return turnos;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }
    
}
