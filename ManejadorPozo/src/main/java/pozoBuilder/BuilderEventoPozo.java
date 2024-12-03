package pozoBuilder;

import builder.EventBuilder;
import domino64.eventos.base.Evento;
import entidadesDTO.FichaDTO;
import entidadesDTO.MazosDTO;
import eventos.EventoPozo;
import java.util.List;
import tiposLogicos.TipoLogicaPozo;

/**
 *
 * @author karim
 */
public class BuilderEventoPozo implements EventBuilder<TipoLogicaPozo>{
    private EventoPozo evento;

    public BuilderEventoPozo() {
        evento = new EventoPozo();
    }
    
    @Override
    public void setTipo(TipoLogicaPozo tipo) {
        evento.setTipo(tipo);
    }

    @Override
    public void setIdPublicador(int idPublicador) {
        evento.setIdPublicador(idPublicador);
    }

    @Override
    public void setIdDestinatario(int idDestinatario) {
        evento.setIdDestinatario(idDestinatario);
    }

    public void setFicha(FichaDTO ficha){
        evento.setFicha(ficha);
    }
    
    public void setFichas(List<FichaDTO> fichas){
        evento.setFichas(fichas);
    }
    
    public void setMazos(MazosDTO mazos){
        evento.setMazos(mazos);
    }
    
    @Override
    public void setIdContexto(int idContexto) {
        evento.setIdContexto(idContexto);
    }

    @Override
    public EventoPozo construirEvento() {
        EventoPozo resultado = evento;
        reiniciar();
        return resultado;
    }

    @Override
    public void reiniciar() {
        evento = new EventoPozo();
    }
    
}
