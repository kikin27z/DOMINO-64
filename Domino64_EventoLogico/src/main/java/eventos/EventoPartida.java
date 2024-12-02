package eventos;

import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import tiposLogicos.TipoLogicaPartida;

/**
 *
 * @author luisa M
 */
public class EventoPartida extends EventoLogico {
    private JugadorDTO jugador;
    private PartidaDTO partida;
    private TipoLogicaPartida tipo;
    
    public EventoPartida(){}
    
    public EventoPartida(TipoLogicaPartida tipo){
        this.tipo = tipo;
    }
    
//    @Override
//    public void agregarInfo(PartidaDTO partida) {
//        contexto = partida;
//    }
    
    public void agregarPartida(PartidaDTO partida){
        this.partida = partida;
    }

    public void setJugador(JugadorDTO jugador){
        this.jugador = jugador;
    }
    
    public JugadorDTO getJugador(){
        return jugador;
    }
    
    public PartidaDTO getPartida() {
        return partida;
    }

    @Override
    public TipoLogicaPartida getTipo() {
        return tipo;
    }

    public void setTipo(TipoLogicaPartida tipo) {
        this.tipo = tipo;
    }

}
