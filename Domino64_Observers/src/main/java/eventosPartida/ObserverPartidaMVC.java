package eventosPartida;

import entidadesDTO.FichaDTO;
import java.util.List;

/**
 *
 * @author karim
 */
public interface ObserverPartidaMVC {
    public void actualizarDarFichas(List<FichaDTO> fichas);
    public void actualizarDarFicha(FichaDTO ficha);
    public void actualizarTurno();
}
