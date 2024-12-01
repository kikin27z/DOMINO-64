package receptorPresentacion;

import entidadesDTO.UnirseDTO;
import eventosOpcionesPartida.ObserverOpcionesPartida;
import java.util.concurrent.ExecutorService;
import manejadores.Control;
import manejadores.ManejadorDisplay;
import manejadores.ManejadorNotificador;

/**
 *
 * @author karim
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
