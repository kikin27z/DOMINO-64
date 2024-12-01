package receptorPresentacion;

import eventosInicio.ObserverInicio;
import java.util.concurrent.ExecutorService;
import manejadores.ManejadorDisplay;

/**
 *
 * @author karim
 */
class ControlEventosInicio extends ControlEventos implements ObserverInicio {

    public ControlEventosInicio(ExecutorService hiloPrincipal, ManejadorDisplay display) {
        super(hiloPrincipal, display);
    }
    

    @Override
    public void avisarModoJugar() {
        hiloPrincipal.execute(() -> {
            display.mostrarOpcionesPartida();
        });
    }

    @Override
    public void avisarMostrarCreditos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
