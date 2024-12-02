/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino64.eventos.base.suscripcion;

import domino64.eventos.base.Evento;


/**
 *
 * @author luisa M
 */
public class EventoSuscripcion implements Evento{
    private int idPublicador;
    private int idContexto;
    private Enum evento;
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
     * @param tipoEvento Enum indicando el tipo de evento especifico
     * del cual se quiere suscribir o desuscribir
     */
    public void agregarEvento(Enum tipoEvento) {
        evento = tipoEvento;
    }

    @Override
    public TipoSuscripcion getTipo() {
        return tipo;
    }

    /**
     * obtiene el tipo de evento al que se quiere suscribir o desuscribirse
     * @return El enum con el tipo de evento al cual se quiere suscribir o desuscribir
     */
    public Enum getEventoSuscripcion() {
        return evento;
    }

    public void setIdPublicador(int idPublicador) {
        this.idPublicador = idPublicador;
    }

    public void setIdContexto(int idContexto) {
        this.idContexto = idContexto;
    }

    /**
     * establece el tipo de evento al que se quiere suscribir
     * o desuscribir.
     * @param evento El enum con el tipo de evento al cual se quiere
     * suscribir o desuscribir
     */
    public void setEventoSuscripcion(Enum evento) {
        this.evento = evento;
    }

    @Override
    public int getIdPublicador() {
        return idPublicador;
    }

    @Override
    public int getIdContexto() {
        return idContexto;
    }
    
}
