package eventosLobby;

import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;

/**
 *
 * @author karim
 */
public interface ObserverLobby {
    public void avisarCambioAvatar(CuentaDTO cuentaActualizada);
    public void avisarJugadorListo();
    public void avisarJugadorNoListo();
    public void avisarIniciarPartida();
    public void avisarAbandonar(LobbyDTO lobby);
}
