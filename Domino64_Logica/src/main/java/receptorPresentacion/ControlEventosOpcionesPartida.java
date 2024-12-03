package receptorPresentacion;

import entidadesDTO.UnirseDTO;
import eventosOpcionesPartida.ObserverOpcionesPartida;
import java.util.concurrent.ExecutorService;
import manejadores.Control;
import manejadores.ManejadorDisplay;
import manejadores.ManejadorNotificador;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ControlEventosOpcionesPartida extends ControlEventos implements ObserverOpcionesPartida {

    private final ManejadorNotificador manejadorNotificador;

    public ControlEventosOpcionesPartida(ExecutorService hiloPrincipal, ManejadorDisplay display) {
        super(hiloPrincipal, display);
        this.manejadorNotificador = Control.obtenerNotificador();

    }

    @Override
    public void crearPartida() {
        hiloPrincipal.execute(() -> {
            manejadorNotificador.crearPartida();
        });
    }

    @Override
    public void buscarPartida(UnirseDTO unirse) {
        hiloPrincipal.execute(() -> {
            manejadorNotificador.unirsePartida(unirse);
        });
    }

    @Override
    public void avisarIrInicio() {
        hiloPrincipal.execute(()->{
            
            display.iniciarJuego();
        });
    }
}
