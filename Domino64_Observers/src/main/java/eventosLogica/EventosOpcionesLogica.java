package eventosLogica;

/**
 *
 * @author karim
 */
public interface EventosOpcionesLogica {
    public void agregarObserver(ObserverOpcionesLogica observador);
    public void quitarObserver(ObserverOpcionesLogica observador);
    public void avisarValidarCodigoPartida(String codigo);
}
