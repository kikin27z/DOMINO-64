package eventosLobby;

import entidadesDTO.AvatarDTO;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import java.util.List;

/**
 *
 * @author karim
 */
public interface ObservableLobbyMVC {
    public void agregarObserver(ObserverLobbyMVC observador);
    public void quitarObserver(ObserverLobbyMVC observador);
    public void inicializarLobby(LobbyDTO lobby);
    public void actualizarNuevoJugador(CuentaDTO cuenta);
    public void actualizarQuitarCuenta(CuentaDTO cuenta);
    public void actualizarAvatarCuenta(CuentaDTO cuenta);
    public void actualizarCuentaLista(CuentaDTO cuenta);
    public void actualizarCuentaNoLista(CuentaDTO cuenta);
    public void mostrarVentanaAvatares(List<AvatarDTO> avataresUsados);
}
