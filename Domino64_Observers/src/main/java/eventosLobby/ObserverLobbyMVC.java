package eventosLobby;

import entidadesDTO.CuentaDTO;

/**
 *
 * @author karim
 */
public interface ObserverLobbyMVC {
    public void actualizarNuevoJugador(CuentaDTO cuenta);
    public void actualizarQuitarJugador(CuentaDTO cuenta);
    public void actualizarAvatarJugador(CuentaDTO cuenta);
    public void actualizarJugadorListo(CuentaDTO cuenta);
    public void actualizarJugadorNoListo(CuentaDTO cuenta);
}
