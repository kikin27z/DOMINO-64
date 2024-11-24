package eventos;

import entidadesDTO.FichaDTO;
import java.util.ArrayList;
import java.util.List;
import tiposLogicos.TipoLogicaPozo;
/**
 *
 * @author luisa M
 */
public class EventoPozo extends EventoLogico{
    private Object contexto;
    private List<FichaDTO> fichas;
    private FichaDTO ficha;
    private TipoLogicaPozo tipo;
    
    public EventoPozo(){}
    
    public EventoPozo( TipoLogicaPozo tipo){
        super();
        this.tipo = tipo;
    }
    
    @Override
    public void agregarInfo(Object info) {
        this.contexto = info;
    }

    @Override
    public Object getInfo() {
        return contexto;
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
