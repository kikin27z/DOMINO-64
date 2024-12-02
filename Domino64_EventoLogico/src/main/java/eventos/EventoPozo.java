package eventos;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import java.util.ArrayList;
import java.util.List;
import tiposLogicos.TipoLogicaPozo;
/**
 *
 * @author luisa M
 */
public class EventoPozo extends EventoLogico{
    private List<JugadorDTO> jugadoresConFichas;
    private FichaDTO ficha;
    private TipoLogicaPozo tipo;
    
    public EventoPozo(){}
    
    public EventoPozo( TipoLogicaPozo tipo){
        super();
        this.tipo = tipo;
    }

    public FichaDTO getFicha() {
        return ficha;
    }

    public void setFicha(FichaDTO ficha) {
        this.ficha = ficha;
    }

    public void setJugadoresConFichas(List<JugadorDTO> jugadores){
        this.jugadoresConFichas = jugadores;
    }
    
    public List<JugadorDTO> getJugadoresConFichas(){
        return jugadoresConFichas;
    }

    @Override
    public TipoLogicaPozo getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoLogicaPozo tipo){
        this.tipo = tipo;
    }
}
