package receptorPresentacion;

import eventosFin.ObserverFinJuego;
import java.util.concurrent.ExecutorService;
import manejadores.ManejadorDisplay;

/**
 *
 * @author karim
 */
public class ControlEventosFinJuego extends ControlEventos implements ObserverFinJuego{
    
    public ControlEventosFinJuego(ExecutorService hiloPrincipal, ManejadorDisplay display) {
        super(hiloPrincipal, display);
    }
    
}
