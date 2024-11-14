package eventos;

import entidadesDTO.JugadorDTO;
import tiposLogicos.TipoLogicaPartida;

/**
 *
 * @author luisa M
 */
public class EventoPartida extends EventoLogico {
    private JugadorDTO jugador;
    private Object contexto;
    private TipoLogicaPartida tipo;
    
    public EventoPartida(){}
    
    public EventoPartida(TipoLogicaPartida tipo){
        this.tipo = tipo;
    }
    
    @Override
    public void agregarInfo(Object info) {
        contexto = info;
    }

    public void setJugador(JugadorDTO jugador){
        this.jugador = jugador;
    }
    
    public JugadorDTO getJugador(){
        return jugador;
    }
    
    @Override
    public Object getInfo() {
        return contexto;
    }

    @Override
    public TipoLogicaPartida getTipo() {
        return tipo;
    }

    public void setTipo(TipoLogicaPartida tipo) {
        this.tipo = tipo;
    }

}
