package eventosOpcionesPartida;

/**
 *
 * @author karim
 */
public interface EventosOpcionesPartida {
    public void agregarObserver(ObserverOpcionesPartida observador);
    public void quitarObserver(ObserverOpcionesPartida observador);
    public void avisarCrearPartida();
    public void avisarBuscarPartida();
}
