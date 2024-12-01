package receptorPresentacion;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import eventosPartida.ObserverPartida;
import java.util.concurrent.ExecutorService;
import manejadores.ManejadorDisplay;

/**
 *
 * @author karim
 */
public class ControlEventosPartida extends ControlEventos implements ObserverPartida{

    public ControlEventosPartida(ExecutorService hiloPrincipal, ManejadorDisplay display) {
        super(hiloPrincipal, display);
    }

    @Override
    public void avisarJugadaRealizada(JugadaRealizadaDTO jugadaDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void avisarFichaSeleccionada(FichaDTO contexto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
