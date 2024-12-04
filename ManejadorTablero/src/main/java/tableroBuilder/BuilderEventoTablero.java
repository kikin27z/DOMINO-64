package tableroBuilder;

import builder.EventBuilder;
import entidadesDTO.JugadaDTO;
import eventoBase.Evento;
import entidadesDTO.JugadaRealizadaDTO;
import eventos.EventoTablero;
import tiposLogicos.TipoLogicaTablero;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class BuilderEventoTablero implements EventBuilder<TipoLogicaTablero>{//,JugadaRealizadaDTO>{
    private EventoTablero evento;

    public BuilderEventoTablero() {
        this.evento = new EventoTablero();
    }
    
    @Override
    public void setIdPublicador(int idPublicador) {
        evento.setIdPublicador(idPublicador);
    }

    @Override
    public void setIdContexto(int idContexto) {
        evento.setIdContexto(idContexto);
    }
    
    public void setJugadaDTO(JugadaDTO jugada){
        evento.setJugada(jugada);
    }

    @Override
    public EventoTablero construirEvento() {
        EventoTablero resultado = evento;
        reiniciar();
        return resultado;
    }

    @Override
    public void reiniciar() {
        evento = new EventoTablero();
    }

    @Override
    public void setTipo(TipoLogicaTablero tipo) {
        evento.setTipo(tipo);
    }

    @Override
    public void setIdDestinatario(int idDestinatario) {
        evento.setIdDestinatario(idDestinatario);
    }
    
    
}
