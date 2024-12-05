/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidaBuilder;

import eventoBaseSuscripcion.EventoSuscripcion;
import eventoBaseSuscripcion.TipoSuscripcion;
import builder.DirectorEventos;
/**
 *
 * @author luisa M
 */
public class DirectorSuscripcion extends DirectorEventos<BuilderEventoSuscripcion> {
    private final int idPublicador;
    
    public DirectorSuscripcion( int idPublicador) {
        super(new BuilderEventoSuscripcion());
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
        builder.setIdContexto(id);
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TipoSuscripcion.ESTABLECER_ID_CONTEXTO);
        return builder.construirEvento();
    }
    
    public EventoSuscripcion crearEventoRemoverIdContexto(){
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TipoSuscripcion.REMOVER_ID_CONTEXTO);
        return builder.construirEvento();
    }
    
    
}
