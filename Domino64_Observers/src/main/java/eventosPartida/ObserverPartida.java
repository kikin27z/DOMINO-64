package eventosPartida;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import java.util.List;

/**
 *
 * @author karim
 */
public interface ObserverPartida {
    public void avisarJugadaRealizada(JugadaRealizadaDTO jugadaDTO);
    public void avisarFichaSeleccionada(FichaDTO contexto);
    public void avisarDarFichas(List<FichaDTO> fichas);
    public void avisarDarFicha(FichaDTO ficha);
}
