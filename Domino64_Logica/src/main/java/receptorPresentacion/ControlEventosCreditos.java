package receptorPresentacion;

import eventosCreditos.ObserverCreditos;
import java.util.concurrent.ExecutorService;
import manejadores.ManejadorDisplay;

/**
 *
* @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ControlEventosCreditos extends ControlEventos implements ObserverCreditos {
    
    public ControlEventosCreditos(ExecutorService hiloPrincipal, ManejadorDisplay display) {
        super(hiloPrincipal, display);
    }

    @Override
    public void avisarIrInicio() {
         hiloPrincipal.execute(()->{
            display.iniciarJuego();
        });
    }
}
