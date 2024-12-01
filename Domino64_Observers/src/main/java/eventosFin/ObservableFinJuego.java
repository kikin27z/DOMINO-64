package eventosFin;

/**
 *
 * @author karim
 */
public interface ObservableFinJuego {
    public void agregarObserver(ObserverFinJuego observador);

    /**
     * Elimina un observador que ya no desea recibir notificaciones sobre los cambios en la vista de inicio.
     * 
     * @param observador el observador que desea ser eliminado.
     */
    public void quitarObserver(ObserverFinJuego observador);
}
