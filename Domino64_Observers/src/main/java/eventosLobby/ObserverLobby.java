package eventosLobby;

/**
 *
 * @author karim
 */
public interface ObserverLobby {
    public void avisarJugadorListo();
    public void avisarJugadorNoListo();
    public void avisarIniciarPartida();
    public void avisarAbandonar();
}
