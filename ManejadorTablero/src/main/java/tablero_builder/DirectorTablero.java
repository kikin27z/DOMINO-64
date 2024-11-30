package tablero_builder;

import builder.DirectorEventos;
import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.LobbyDTO;
import eventos.EventoTablero;
import tiposLogicos.TipoLogicaTablero;

/**
 * Esta clase construye los eventos que envia el manejador
 * @author karim
 */
public class DirectorTablero extends DirectorEventos<BuilderEventoTablero> {
    private int idPublicador;
    
    /**
     * 
     * @param builder 
     */
    public DirectorTablero(BuilderEventoTablero builder, int idPublicador){
        super(builder);
        this.idPublicador = idPublicador;
    }
    
    public EventoTablero crearEventoJugadorNuevo(JugadaDTO jugada, CuentaDTO jugador){
        builder.setIdPublicador(jugador.getId());
//        builder.agregarLobby(lobby);
//        builder.setPublicador(jugador);
        builder.setTipo(TipoLogicaTablero.OBTENER_EXTREMOS);
        return builder.construirEvento();
    }
}
