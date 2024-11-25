package eventos;

import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import tiposLogicos.TipoLogicaPartida;

/**
 *
 * @author luisa M
 */
public class EventoPartida extends EventoLogico<PartidaDTO> {
    private JugadorDTO jugador;
    private PartidaDTO contexto;
    private TipoLogicaPartida tipo;
    
    public EventoPartida(){}
    
    public EventoPartida(TipoLogicaPartida tipo){
        this.tipo = tipo;
    }
    
    @Override
    public void agregarInfo(PartidaDTO partida) {
        contexto = partida;
    }

    public void setJugador(JugadorDTO jugador){
        this.jugador = jugador;
    }
    
    public JugadorDTO getJugador(){
        return jugador;
    }
    
    @Override
    public PartidaDTO getInfo() {
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
