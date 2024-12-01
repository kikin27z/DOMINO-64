package eventosPartida;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import java.util.List;

/**
 *
 * @author karim
 */
public interface ObserverPartidaMVC {
    public void actualizarJugadorAbandono(CuentaDTO cuenta);
    public void actualizarJugadorSeRindio(CuentaDTO cuenta);
    public void actualizarDarFichas(List<FichaDTO> fichas);
    public void actualizarDarFicha(FichaDTO ficha);
    public void actualizarTurno(JugadaDTO jugada);
}
