package receptorPresentacion;

import java.util.concurrent.ExecutorService;
import manejadores.ManejadorDisplay;

/**
 *
 * @author karim
 */
public abstract class ControlEventos {
    protected final ExecutorService hiloPrincipal;
    protected final ManejadorDisplay display;

    public ControlEventos(ExecutorService hiloPrincipal, ManejadorDisplay display) {
        this.hiloPrincipal = hiloPrincipal;
        this.display = display;
    }
    
}
