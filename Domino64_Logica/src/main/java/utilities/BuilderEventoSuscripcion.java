package utilities;

import builder.EventBuilder;
import domino64.eventos.base.suscripcion.EventoSuscripcion;
import domino64.eventos.base.suscripcion.TipoSuscripcion;

/**
 *
 * @author luisa M
 */
public class BuilderEventoSuscripcion implements EventBuilder<TipoSuscripcion>{
    private EventoSuscripcion evento;
    
    public BuilderEventoSuscripcion(){
        this.evento = new EventoSuscripcion();
    }
    
    @Override
    public void setTipo(TipoSuscripcion tipo) {
        evento.setTipo(tipo);
    }

    @Override
    public void setIdPublicador(int idPublicador) {
        evento.setIdPublicador(idPublicador);
    }

    @Override
    public void setIdContexto(int idContexto) {
        evento.setIdContexto(idContexto);
    }

    public void setEventoSuscripcion(Enum tipoEvento){
        evento.setEventoSuscripcion(tipoEvento);
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
