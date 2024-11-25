package eventosLobby;

import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;

/**
 *
 * @author karim
 */
public interface ObservableLobbyMVC {
    public void agregarObserver(ObserverLobbyMVC observador);
    public void quitarObserver(ObserverLobbyMVC observador);
    public void inicializarLobby(LobbyDTO lobby);
    public void actualizarNuevoJugador(CuentaDTO cuenta);
    public void actualizarQuitarJugador(CuentaDTO cuenta);
    public void actualizarAvatarJugador(CuentaDTO cuenta);
    public void actualizarJugadorListo(CuentaDTO cuenta);
    public void actualizarJugadorNoListo(CuentaDTO cuenta);
    
}
