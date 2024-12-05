package receptorPresentacion;

import eventosFin.ObserverFinJuego;
import java.util.concurrent.ExecutorService;
import manejadores.ManejadorDisplay;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ControlEventosFinJuego extends ControlEventos implements ObserverFinJuego{
    public ControlEventosFinJuego(ExecutorService hiloPrincipal, ManejadorDisplay display) {
        super(hiloPrincipal, display);
    }

    @Override
    public void avisarIrInicio() {
        hiloPrincipal.execute(()->{
            display.iniciarJuego();
        });
    }
    
}
