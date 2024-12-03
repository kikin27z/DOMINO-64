package eventos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.TurnosDTO;
import tiposLogicos.TipoLogicaTurno;

/**
 *
 * @author luisa M
 */
public class EventoTurno extends EventoLogico<CuentaDTO>{
    private TipoLogicaTurno tipo;
    private TurnosDTO turnos;
    private CuentaDTO cuenta;
    
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

    @Override
    public Object getInfo() {
        return null;
    }

    public void setTurnos(TurnosDTO turnos) {
        this.turnos = turnos;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }

    public TurnosDTO getTurnos() {
        return turnos;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }
    
}
