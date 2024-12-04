package partidaBuilder;

import builder.DirectorEventos;
import entidadesDTO.PartidaIniciadaDTO;
import entidadesDTO.ResultadosDTO;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.PosibleJugadaDTO;
import entidadesDTO.ReglasDTO;
import eventos.EventoPartida;
import java.util.List;
import java.util.Map;
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
    
    public EventoPartida crearEventoPartida(TurnosDTO turnos){
        builder.setIdPublicador(idPublicador);
        builder.setTurnos(turnos);
        builder.setTipo(TipoLogicaPartida.INICIO_PARTIDA);
        return builder.construirEvento();
    }
    
    public EventoPartida crearEventoTuTurno(JugadaDTO jugada,CuentaDTO cuenta){
        builder.setTipo(TipoLogicaPartida.JUGADOR_EN_TURNO);
        builder.setJugadaDTO(jugada);
        builder.setIdPublicador(idPublicador);
        builder.setCuenta(cuenta);
        return builder.construirEvento();
    }
    
    public EventoPartida crearEventoSinJugadas(CuentaDTO cuenta){
        builder.setIdPublicador(idPublicador);
        builder.setCuenta(cuenta);
        builder.setTipo(TipoLogicaPartida.SIN_JUGADAS);
        return builder.construirEvento();
    }
    
    public EventoPartida crearEventoSolicitarSiguienteTurno(){
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TipoLogicaPartida.SIGUIENTE_TURNO);
        return builder.construirEvento();
    }
    
}
