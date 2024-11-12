package eventosPantallas;

/**
 *
 * @author karim
 */
public interface ObservablePantallas {
    public void agregarObserver(ObserverPantalla observador);
    public void quitarObserver(ObserverPantalla observador);
    public void avisarMostrarInicio();
    public void avisarMostrarCreditos();
    public void avisarMostrarPartida();
    public void avisarMostrarLobby();
    public void avisarMostrarFinJuego();
    public void avisarMostrarOpcionesPartida();
}
