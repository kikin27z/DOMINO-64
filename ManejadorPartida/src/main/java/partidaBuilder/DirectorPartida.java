package partidaBuilder;

import builder.DirectorEventos;
import entidadesDTO.PartidaIniciadaDTO;
import entidadesDTO.ReglasDTO;
import entidadesDTO.ResultadosDTO;
import eventos.EventoPartida;
import tiposLogicos.TipoLogicaPartida;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class DirectorPartida extends DirectorEventos<BuilderEventoPartida> {
    private final int idPublicador;

    public DirectorPartida(BuilderEventoPartida builder, int idPublicador) {
        super(builder);
        this.idPublicador = idPublicador;
    }
    
    
    public EventoPartida crearEventoRepartirFichas(ReglasDTO reglas){
        builder.setIdPublicador(idPublicador);
        builder.setReglas(reglas);
        builder.setTipo(TipoLogicaPartida.REPARTIR_FICHAS);
        return builder.construirEvento();
    }
    
    public EventoPartida crearEventoTerminoPartida(ResultadosDTO resultados){
        return builder.construirEvento();
    }
    
    public EventoPartida crearEventoInicioPartida(PartidaIniciadaDTO partida){
        builder.setIdPublicador(idPublicador);
        builder.setPartidaIniciada(partida);
        builder.setTipo(TipoLogicaPartida.INICIO_PARTIDA);
        return builder.construirEvento();
    }
}
