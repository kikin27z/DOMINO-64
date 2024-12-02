/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import entidadesDTO.FichaDTO;
import tiposLogicos.TipoLogicaTablero;

/**
 *
 * @author luisa M
 */
public class EventoTablero extends EventoLogico{
    private FichaDTO ficha;
    private TipoLogicaTablero tipo;
    
    public EventoTablero(){}
    
    public EventoTablero(TipoLogicaTablero tipo) {
        super();
        this.tipo = tipo;
    }

//    @Override
//    public void agregarInfo(FichaDTO info) {
//        ficha = info;
//    }

    public FichaDTO getFicha() {
        return ficha;
    }

    public void setFicha(FichaDTO ficha){
        this.ficha = ficha;
    }
    
    @Override
    public TipoLogicaTablero getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoLogicaTablero tipo){
        this.tipo = tipo;
    }
    
}
