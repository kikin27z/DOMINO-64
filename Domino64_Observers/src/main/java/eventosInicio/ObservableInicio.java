package eventosInicio;

/**
 * Interfaz que define los métodos que deben implementar los objetos observables de la vista de inicio.
 * Esta interfaz permite gestionar a los observadores que reaccionan a las acciones realizadas en la vista de inicio,
 * como el inicio de un juego o la visualización de los créditos.
 * 
 * Los objetos que implementen esta interfaz serán responsables de agregar, quitar y notificar
 * a los observadores sobre los cambios en el estado de la vista de inicio.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObservableInicio {

    /**
     * Agrega un observador que reaccionará a los cambios en el estado de la vista de inicio.
     * 
     * @param observador el observador que desea recibir notificaciones.
     */
    void agregarObserver(ObserverInicio observador);

    /**
     * Elimina un observador que ya no desea recibir notificaciones sobre los cambios en la vista de inicio.
     * 
     * @param observador el observador que desea ser eliminado.
     */
    void quitarObserver(ObserverInicio observador);

    /**
     * Notifica a todos los observadores que el usuario ha elegido iniciar el modo de juego.
     * Este método debe ser invocado cuando el usuario decide comenzar una partida.
     */
    void avisarModoJugar();

    /**
     * Notifica a todos los observadores que el usuario ha elegido mostrar los créditos.
     * Este método debe ser invocado cuando el usuario desea ver los créditos del juego.
     */
    void avisarMostrarCreditos();
}
