package eventos;

import entidadesDTO.FichaDTO;
import java.util.ArrayList;
import java.util.List;
import tiposLogicos.TipoLogicaPozo;
/**
 *
 * @author luisa M
 */
public class EventoPozo extends EventoLogico<FichaDTO>{
    private List<FichaDTO> fichas;
    private FichaDTO ficha;
    private boolean flag;
    private TipoLogicaPozo tipo;
    
    public EventoPozo(){}
    
    public EventoPozo( TipoLogicaPozo tipo){
        super();
        this.tipo = tipo;
        if(tipo.equals(TipoLogicaPozo.REPARTIR_FICHAS)){
            fichas = new ArrayList<>();
            flag = true;
        }
    }
    
    @Override
    public void agregarInfo(FichaDTO info) {
        if(flag){
            fichas.add(info);
        }else{
            ficha = info;
        }
    }

    @Override
    public FichaDTO getInfo() {
        return ficha;
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
