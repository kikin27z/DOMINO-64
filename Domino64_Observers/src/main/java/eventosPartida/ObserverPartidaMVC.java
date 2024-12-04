package eventosPartida;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.TurnosDTO;
import java.util.List;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObserverPartidaMVC {
    public void actualizarJugadorAbandono(CuentaDTO cuenta);
    public void actualizarJugadorSeRindio(CuentaDTO cuenta);
    public void actualizarDarFichas(List<FichaDTO> fichas);
    public void actualizarDarFicha(FichaDTO ficha);
    public void actualizarTurno(JugadaDTO jugada);
    public void inicializarPartida(TurnosDTO turnos);

    public void actualizarTablero(JugadaRealizadaDTO jugada);

}
