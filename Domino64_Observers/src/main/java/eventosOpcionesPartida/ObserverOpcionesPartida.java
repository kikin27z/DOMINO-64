package eventosOpcionesPartida;

/**
 *
 * @author karim
 */
public interface ObserverOpcionesPartida {
    public void agregarObserver(ObserverOpcionesPartida observador);
    public void quitarObserver(ObserverOpcionesPartida observador);
    public void avisarCrearPartida();
    public void avisarBuscarPartida(String codigoPartida);
}
