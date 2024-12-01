package eventos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.PartidaDTO;
import tiposLogicos.TipoLogicaTurno;

/**
 *
 * @author luisa M
 */
public class EventoTurno extends EventoLogico<CuentaDTO>{
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
     * @param info Cuenta del contexto de este evento
     */
    @Override
    public void agregarInfo(CuentaDTO info) {
        jugador = info;
    }

    /**
     * Obtiene la informacion de la cuenta del contexto
     * de este evento
     * @return La cuenta como parte del contexto
     */
    @Override
    public CuentaDTO getInfo() {
        return jugador;
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
