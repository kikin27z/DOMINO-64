/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import builder.DirectorEventos;
import eventos.EventoSuscripcion;
import tiposLogicos.TipoSuscripcion;

/**
 *
 * @author luisa M
 */
public class DirectorSuscripcion extends DirectorEventos<BuilderEventoSuscripcion> {
    private int idPublicador;
    
    public DirectorSuscripcion(BuilderEventoSuscripcion builder, int idPublicador) {
        super(builder);
        this.idPublicador = idPublicador;
    }
    
    public EventoSuscripcion crearEventoSuscribirse(Enum<?> tipoEvento){
        builder.setIdPublicador(idPublicador);
        builder.setInfo(tipoEvento);
        builder.setTipo(TipoSuscripcion.SUSCRIBIR);
        return builder.construirEvento();
    }
    
    public EventoSuscripcion crearEventoDesuscribirse(Enum<?> tipoEvento){
        builder.setIdPublicador(idPublicador);
        builder.setInfo(tipoEvento);
        builder.setTipo(TipoSuscripcion.DESUSCRIBIR);
        return builder.construirEvento();
    }
    
}
