package eventos;

//import categorias.CategoriaEvento;
import entidades.Ficha;
import entidades.Cuenta;
import java.util.ArrayList;
import java.util.List;
import tiposLogicos.TipoLogicaPozo;
/**
 *
 * @author luisa M
 */
public class EventoPozo extends EventoLogico<Ficha>{
    private List<Ficha> fichas;
    private Ficha ficha;
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
    public void agregarInfo(Ficha info) {
        if(flag){
            fichas.add(info);
        }else{
            ficha = info;
        }
    }

    @Override
    public Ficha getInfo() {
        return ficha;
    }
    
    
    public List<Ficha> getFichas() {
        if(flag)
            return fichas;
        return null;
    }

    @Override
    public TipoLogicaPozo getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoLogicaPozo tipo){
        this.tipo = tipo;
    }
}
