package tableroBuilder;

import builder.DirectorEventos;
import entidadesDTO.JugadaDTO;
import eventos.EventoTablero;
import tiposLogicos.TipoLogicaTablero;

/**
 * Esta clase construye los eventos que envia el manejador
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class DirectorTablero extends DirectorEventos<BuilderEventoTablero> {

    private int idPublicador;

    /**
     *
     * @param builder
     */
    public DirectorTablero(BuilderEventoTablero builder, int idPublicador) {
        super(builder);
        this.idPublicador = idPublicador;
    }

    public EventoTablero crearEventoProximaJugada(JugadaDTO jugada) {
        builder.setIdPublicador(idPublicador);
        builder.setJugadaDTO(jugada);
        builder.setTipo(TipoLogicaTablero.OBTENER_JUGADA);
        return builder.construirEvento();
    }
}
