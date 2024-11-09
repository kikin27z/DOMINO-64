/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

//import categorias.CategoriaEvento;
import com.luisa.entidades.Cuenta;
import com.luisa.entidades.Ficha;
import com.luisa.excepcionesLogica.LogicException;
import java.util.ArrayList;
import java.util.List;
import tiposLogicos.TipoLogicaTablero;

/**
 *
 * @author luisa M
 */
public class EventoTablero extends EventoLogico<Ficha>{
    private List<Ficha> fichas;
    private Ficha ficha;
    private boolean flag;
    private TipoLogicaTablero tipo;
    
    public EventoTablero(){}
    
    public EventoTablero(TipoLogicaTablero tipo) {
        super();
        this.tipo = tipo;
        if(tipo.equals(TipoLogicaTablero.OBTENER_EXTREMOS)){
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
    
    public List<Ficha> getExtremos() throws LogicException{
        if(flag)
            return fichas;
        throw new LogicException("ERROR: Accion invalida para este tipo de evento de tablero");
    }

    @Override
    public TipoLogicaTablero getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoLogicaTablero tipo){
        this.tipo = tipo;
    }
    
}
