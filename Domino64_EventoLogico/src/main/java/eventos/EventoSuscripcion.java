/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import tiposLogicos.TipoSuscripcion;
import tiposLogicos.TiposJugador;

/**
 *
 * @author luisa M
 */
public class EventoSuscripcion extends EventoLogico<Enum<?>> {
    private Enum<?> evento;
    private Enum<?> tipo;
    
    public EventoSuscripcion(){}
    
    public EventoSuscripcion(TipoSuscripcion tipo){
        this.tipo = tipo;
    }
    
    @Override
    public void agregarInfo(Enum<?> info) {
        evento = info;
    }

    @Override
    public Enum<?> getTipo() {
        return tipo;
    }

    @Override
    public Enum getInfo() {
        return evento;
    }
    
}
