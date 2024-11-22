package eventosLobby;

import entidadesDTO.CuentaDTO;

/**
 *
 * @author karim
 */
public interface ObservableLobby {
    public void agregarObserver(ObserverLobby observador);
    public void quitarObserver(ObserverLobby observador);
    public void avisarCambioAvatar(CuentaDTO cuentaActualizada);
    public void avisarJugadorListo();
    public void avisarJugadorNoListo();
    public void avisarIniciarPartida();
    public void avisarAbandonar();
    
}
