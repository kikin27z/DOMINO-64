package pozoBuilder;

import builder.DirectorEventos;
import entidadesDTO.FichaDTO;
import entidadesDTO.MazosDTO;
import eventos.EventoPozo;
import java.util.List;
import tiposLogicos.TipoLogicaPozo;

/**
 *
 * @author karim
 */
public class DirectorPozo extends DirectorEventos<BuilderEventoPozo> {
    private int idPublicador;

    public DirectorPozo(BuilderEventoPozo builder, int idPublicador) {
        super(builder);
        this.idPublicador = idPublicador;
    }

    public EventoPozo crearEventoFichaJalada(FichaDTO ficha) {
        builder.setFicha(ficha);
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TipoLogicaPozo.FICHA_OBTENIDA);
        return builder.construirEvento();
    }

    public EventoPozo crearEventoFichaObtenidas(List<FichaDTO> fichas) {
        builder.setFichas(fichas);
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TipoLogicaPozo.FICHAS_OBTENIDA);
        return builder.construirEvento();
    }
    
    public EventoPozo crearEventoPozoConFichas() {
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TipoLogicaPozo.POZO_CON_FICHAS);
        return builder.construirEvento();
    }
    public EventoPozo crearEventoPozoVacio() {
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TipoLogicaPozo.POZO_VACIO);
        return builder.construirEvento();
    }
    
    public EventoPozo crearEventoDesignarTurnos(MazosDTO mazos){
        builder.setMazos(mazos);
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TipoLogicaPozo.REPARTIR_FICHAS);
        return builder.construirEvento();
    }

}
