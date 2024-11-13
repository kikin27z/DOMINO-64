/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import tiposLogicos.TipoSuscripcion;

/**
 *
 * @author luisa M
 */
public class EventoSuscripcion extends EventoLogico<Enum<?>> {
    private Enum<?> evento;
    private TipoSuscripcion tipo;
    
    public EventoSuscripcion(){}
    
    public EventoSuscripcion(TipoSuscripcion tipo){
        this.tipo = tipo;
    }
    
    /**
     * establece el tipo de evento de suscripcion.
     * Indica si el evento es para suscribir o desuscribir de un evento
     * @param tipo Tipo del evento de suscripcion (suscripcion o desuscripcion)
     */
    public void setTipo(TipoSuscripcion tipo){
        this.tipo = tipo;
    }
    
    /**
     * establece el tipo de evento al que se quiere suscribir o desuscribir.
     * @param info Enum indicando el tipo de evento especifico
     * del cual se quiere suscribir o desuscribir
     */
    @Override
    public void agregarInfo(Enum<?> info) {
        evento = info;
    }

    @Override
    public TipoSuscripcion getTipo() {
        return tipo;
    }

    /**
     * obtiene el tipo de evento al que se quiere suscribir o desuscribirse
     * @return El enum con el tipo de evento al cual se quiere suscribir o desuscribir
     */
    @Override
    public Enum<?> getInfo() {
        return evento;
    }
    
}
