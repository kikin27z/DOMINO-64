package eventosCreditos;

/**
 *
 * @author karim
 */
public interface ObservableCreditos {
    public void agregarObserver(ObserverCreditos observador);

    public void quitarObserver(ObserverCreditos observador);
    public void avisarIrInicio();
}
