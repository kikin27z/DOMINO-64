package eventos;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import tiposLogicos.TipoLogicaTablero;

/**
 *
 * @author luisa M
 */
public class EventoTablero extends EventoLogico{
    private TipoLogicaTablero tipo;
    private JugadaDTO jugada;
    
    public EventoTablero(){}
    
    public EventoTablero(TipoLogicaTablero tipo) {
        super();
        this.tipo = tipo;
    }

    public JugadaDTO getJugada() {
        return jugada;
    }

    public void setJugada(JugadaDTO jugada) {
        this.jugada = jugada;
    }

    @Override
    public TipoLogicaTablero getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoLogicaTablero tipo){
        this.tipo = tipo;
    }
    
}
