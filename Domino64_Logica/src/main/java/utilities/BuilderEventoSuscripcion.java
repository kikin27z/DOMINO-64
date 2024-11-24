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
        this.evento = new EventoSuscripcion();
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
    public void setIdContexto(int idContexto) {
        evento.setIdContexto(idContexto);
    }

    @Override
    public void setContexto(Enum<?> info) {
        evento.agregarInfo(info);
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
