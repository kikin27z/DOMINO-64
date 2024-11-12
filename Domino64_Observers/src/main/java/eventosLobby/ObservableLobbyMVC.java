package eventosLobby;

import entidadesDTO.CuentaDTO;

/**
 *
 * @author karim
 */
public interface ObservableLobbyMVC {
    public void agregarObserver(ObservableLobbyMVC observador);
    public void quitarObserver(ObservableLobbyMVC observador);
    public void actualizarNuevoJugador(CuentaDTO cuenta);
    public void actualizarQuitarJugador(CuentaDTO cuenta);
    public void actualizarAvatarJugador(CuentaDTO cuenta);
    public void actualizarJugadorListo(CuentaDTO cuenta);
    public void actualizarJugadorNoListo(CuentaDTO cuenta);
    
}
