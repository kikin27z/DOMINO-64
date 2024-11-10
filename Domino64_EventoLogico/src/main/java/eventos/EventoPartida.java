package eventos;

import entidades.Jugador;
import tiposLogicos.TipoLogicaPartida;

/**
 *
 * @author luisa M
 */
public class EventoPartida extends EventoLogico<Jugador> {
    private Jugador jugador;
    private TipoLogicaPartida tipo;
    
    public EventoPartida(){}
    
    public EventoPartida( TipoLogicaPartida tipo){
        this.tipo = tipo;
    }
    
    @Override
    public void agregarInfo(Jugador info) {
        jugador = info;
    }

    @Override
    public Jugador getInfo() {
        return jugador;
    }

    @Override
    public TipoLogicaPartida getTipo() {
        return tipo;
    }

    public void setTipo(TipoLogicaPartida tipo) {
        this.tipo = tipo;
    }

}
