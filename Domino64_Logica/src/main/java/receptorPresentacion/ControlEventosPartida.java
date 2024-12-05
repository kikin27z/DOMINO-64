package receptorPresentacion;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import eventosPartida.ObserverPartida;
import java.util.concurrent.ExecutorService;
import manejadores.Control;
import manejadores.ManejadorDisplay;
import manejadores.ManejadorNotificador;

/**
 *
 * @author karim
 */
public class ControlEventosPartida extends ControlEventos implements ObserverPartida{
    private final ManejadorNotificador manejadorNotificador;

    public ControlEventosPartida(ExecutorService hiloPrincipal, ManejadorDisplay display) {
        super(hiloPrincipal, display);
                this.manejadorNotificador = Control.obtenerNotificador();

    }

    @Override
    public void avisarJugadaRealizada(JugadaRealizadaDTO jugadaDTO) {
        hiloPrincipal.execute(() -> {
            manejadorNotificador.enviarJugadaRealizada(jugadaDTO);
        });
    }

    @Override
    public void avisarJalarFichaPozo() {
        hiloPrincipal.execute(() -> {
            manejadorNotificador.jalarFichaPozo();
        });
    }

    @Override
    public void avisarAbandonarPartida() {
        hiloPrincipal.execute(() -> {
            manejadorNotificador.abandonarPartida();
        });
    }

    @Override
    public void avisarPeticionRendirse() {
        hiloPrincipal.execute(() -> {
            manejadorNotificador.peticionRendirse();
        });
    }

}
