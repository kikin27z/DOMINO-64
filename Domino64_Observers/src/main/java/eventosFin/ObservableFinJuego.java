package eventosFin;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObservableFinJuego {
    public void agregarObserver(ObserverFinJuego observador);

    /**
     * Elimina un observador que ya no desea recibir notificaciones sobre los cambios en la vista de inicio.
     * 
     * @param observador el observador que desea ser eliminado.
     */
    public void quitarObserver(ObserverFinJuego observador);
    
    public void avisarIrInicio();
}
