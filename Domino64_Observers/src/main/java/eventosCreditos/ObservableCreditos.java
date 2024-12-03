package eventosCreditos;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObservableCreditos {
    public void agregarObserver(ObserverCreditos observador);
    public void quitarObserver(ObserverCreditos observador);
    public void avisarIrInicio();
}
