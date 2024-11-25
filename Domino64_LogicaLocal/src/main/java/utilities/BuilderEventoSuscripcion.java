/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import builder.EventBuilder;
import eventos.EventoSuscripcion;
import tiposLogicos.TipoSuscripcion;

/**
 *
 * @author luisa M
 */
public class BuilderEventoSuscripcion implements EventBuilder<Enum<?>>{
    private EventoSuscripcion evento;
    
    public BuilderEventoSuscripcion(){
        evento = new EventoSuscripcion();
    }
    
    @Override
    public void setTipo(Enum<?> tipo) {
        evento.setTipo((TipoSuscripcion)tipo);
    }

    @Override
    public void setIdPublicador(int idPublicador) {
        evento.setIdPublicador(idPublicador);
    }

    @Override
    public void setContexto(Enum<?> contexto) {
        evento.agregarInfo(contexto);
    }

    @Override
    public EventoSuscripcion construirEvento() {
        EventoSuscripcion resultado = evento;
        reiniciar();
        return resultado;
    }

    @Override
    public void reiniciar() {
        evento = new EventoSuscripcion();
    }
    
}
