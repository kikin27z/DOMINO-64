package eventos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.MazosDTO;
import java.util.List;
import tiposLogicos.TipoLogicaPozo;
/**
 *
 * @author luisa M
 */
public class EventoPozo extends EventoLogico{
    private List<FichaDTO> fichas;
    private FichaDTO ficha;
    private CuentaDTO jugador;
    private TipoLogicaPozo tipo;
    private MazosDTO mazos;
    
    public EventoPozo(){}
    
    public EventoPozo( TipoLogicaPozo tipo){
        super();
        this.tipo = tipo;
    }

    public CuentaDTO getJugador() {
        return jugador;
    }

    public void setJugador(CuentaDTO jugador) {
        this.jugador = jugador;
    }

    public MazosDTO getMazos() {
        return mazos;
    }

    public void setMazos(MazosDTO mazos) {
        this.mazos = mazos;
    }

    public FichaDTO getFicha() {
        return ficha;
    }

    public void setFicha(FichaDTO ficha) {
        this.ficha = ficha;
    }

    
    public void setFichas(List<FichaDTO> fichas) {
        this.fichas = fichas;
    }

    public List<FichaDTO> getFichas(){
        return fichas;
    }
    
    @Override
    public TipoLogicaPozo getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoLogicaPozo tipo){
        this.tipo = tipo;
    }

}
