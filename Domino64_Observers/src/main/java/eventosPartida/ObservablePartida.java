package eventosPartida;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import java.util.List;

/**
 *
 * @author karim
 */
public interface ObservablePartida {
    
    public void agregarObserver(ObserverPartida observador);
    public void quitarObserver(ObserverPartida observador);
    public void avisarJugadaRealizada(JugadaRealizadaDTO jugadaDTO);
    public void avisarFichaSeleccionada(FichaDTO contexto);
}
