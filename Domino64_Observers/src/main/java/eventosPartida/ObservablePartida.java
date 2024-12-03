package eventosPartida;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObservablePartida {
    
    public void agregarObserver(ObserverPartida observador);
    public void quitarObserver(ObserverPartida observador);
    public void avisarJalarFichaPozo();
    public void avisarAbandonarPartida();
    public void avisarPeticionRendirse();
}
