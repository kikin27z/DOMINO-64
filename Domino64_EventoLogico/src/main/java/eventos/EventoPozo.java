package eventos;

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
    private TipoLogicaPozo tipo;
    private MazosDTO mazos;
    
    public EventoPozo(){}
    
    public EventoPozo( TipoLogicaPozo tipo){
        super();
        this.tipo = tipo;
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

    @Override
    public Object getInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
