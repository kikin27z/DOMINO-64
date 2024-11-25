package eventosPartida;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;

/**
 *
 * @author karim
 */
public interface ObserverPartida {
    public void avisarJugadaRealizada(JugadaRealizadaDTO jugadaDTO);
    public void avisarFichaSeleccionada(FichaDTO contexto);
}
