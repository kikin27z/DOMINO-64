package partidaBuilder;

import builder.DirectorEventos;

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
    
    
}
