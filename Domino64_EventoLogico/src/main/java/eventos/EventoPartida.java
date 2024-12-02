package eventos;

import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaIniciadaDTO;
import java.util.List;
import tiposLogicos.TipoLogicaPartida;

/**
 *
 * @author luisa M
 */
public class EventoPartida extends EventoLogico {
    private JugadorDTO jugador;
    private PartidaIniciadaDTO partida;
    private List<JugadorDTO> jugadores;
    private TipoLogicaPartida tipo;
    
    public EventoPartida(){}
    
    public EventoPartida(TipoLogicaPartida tipo){
        this.tipo = tipo;
    }

    public void setJugadores(List<JugadorDTO> jugadores){
        this.jugadores = jugadores;
    }
    
    public List<JugadorDTO> getJugadores(){
        return jugadores;
    }

    public void setJugador(JugadorDTO jugador){
        this.jugador = jugador;
    }
    
    public JugadorDTO getJugador(){
        return jugador;
    }

    public PartidaIniciadaDTO getPartida() {
        return partida;
    }

    public void setPartida(PartidaIniciadaDTO partida) {
        this.partida = partida;
    }

    @Override
    public TipoLogicaPartida getTipo() {
        return tipo;
    }

    public void setTipo(TipoLogicaPartida tipo) {
        this.tipo = tipo;
    }

}
