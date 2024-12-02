/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import builder.DirectorEventos;
import domino64.eventos.base.suscripcion.*;

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
    
    public EventoSuscripcion crearEventoSuscribirse(Enum tipoEvento){
        builder.setIdPublicador(idPublicador);
        builder.setEventoSuscripcion(tipoEvento);
        builder.setTipo(TipoSuscripcion.SUSCRIBIR);
        return builder.construirEvento();
    }
    
    public EventoSuscripcion crearEventoDesuscribirse(Enum tipoEvento){
        builder.setIdPublicador(idPublicador);
        builder.setEventoSuscripcion(tipoEvento);
        builder.setTipo(TipoSuscripcion.DESUSCRIBIR);
        return builder.construirEvento();
    }
    
    public EventoSuscripcion crearEventoEstablecerIdContexto(int id){
        builder.setIdPublicador(idPublicador);
        builder.setIdContexto(id);
        builder.setTipo(TipoSuscripcion.ESTABLECER_ID_CONTEXTO);
        return builder.construirEvento();
    }
    
    public EventoSuscripcion crearEventoRemoverIdContexto(){
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TipoSuscripcion.REMOVER_ID_CONTEXTO);
        return builder.construirEvento();
    }
}
