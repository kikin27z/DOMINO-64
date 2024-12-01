package receptorPresentacion;

import eventosCreditos.ObserverCreditos;
import java.util.concurrent.ExecutorService;
import manejadores.ManejadorDisplay;

/**
 *
 * @author karim
 */
public class ControlEventosCreditos extends ControlEventos implements ObserverCreditos {
    
    public ControlEventosCreditos(ExecutorService hiloPrincipal, ManejadorDisplay display) {
        super(hiloPrincipal, display);
    }

  
    
}
