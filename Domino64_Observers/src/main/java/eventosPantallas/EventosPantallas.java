package eventosPantallas;

/**
 *
 * @author karim
 */
public interface EventosPantallas {
    public void agregarObserver(ObserverPantalla observador);
    public void quitarObserver(ObserverPantalla observador);
    public void avisarMostrarInicio();
    public void avisarMostrarPartida();
    public void avisarMostrarOpcionesPartida();
}
