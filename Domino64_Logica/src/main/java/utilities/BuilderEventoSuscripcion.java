package utilities;

import eventoBaseSuscripcion.EventoSuscripcion;
import eventoBaseSuscripcion.TipoSuscripcion;
import builder.EventBuilder;
/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
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

    public void setEventoSuscripcion(Enum info) {
        evento.agregarEventoSuscripcion(info);
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

    @Override
    public void setIdDestinatario(int idDestinatario) {
    }
    
}
