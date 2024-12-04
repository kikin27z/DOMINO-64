package utilities;

import builder.DirectorEventos;
import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import eventos.EventoJugadorFicha;
import tiposLogicos.TipoJugadorFicha;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class DirectorJugadorFicha extends DirectorEventos<BuilderEventoJugadorFicha>{
    private final int idPublicador;
    public DirectorJugadorFicha( int idPublicador) {
        super(new BuilderEventoJugadorFicha());
        this.idPublicador = idPublicador;
    }
    
    public EventoJugadorFicha crearEventoJugadaRealizada(JugadaRealizadaDTO jugada, CuentaDTO cuenta){
        builder.asignarJugadaRealizada(jugada);
        builder.setCuenta(cuenta);
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TipoJugadorFicha.JUGADA_REALIZADA);
        return builder.construirEvento();
    }
    
    public EventoJugadorFicha crearEventoJalarPozo(CuentaDTO cuenta){
        builder.setCuenta(cuenta);
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TipoJugadorFicha.JALAR_FICHA);
        return builder.construirEvento();
    }
}
