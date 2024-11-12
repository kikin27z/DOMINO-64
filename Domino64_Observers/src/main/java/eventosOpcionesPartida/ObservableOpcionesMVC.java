package eventosOpcionesPartida;

/**
 * Interfaz que define los métodos que deben implementar los objetos observables relacionados con las opciones del MVC (Modelo-Vista-Controlador).
 * Esta interfaz permite gestionar a los observadores que reaccionan a los cambios relacionados con las opciones de la partida.
 * 
 * Los objetos que implementen esta interfaz son responsables de agregar, quitar y notificar
 * a los observadores sobre los cambios que ocurren en las opciones del juego, como la actualización de un mensaje de aviso.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObservableOpcionesMVC {

    /**
     * Agrega un observador que reaccionará a los cambios en las opciones de la partida.
     * 
     * @param observador el observador que desea recibir notificaciones sobre los cambios.
     */
    void agregarObserver(ObserverOpcionesMVC observador);

    /**
     * Elimina un observador que ya no desea recibir notificaciones sobre los cambios en las opciones de la partida.
     * 
     * @param observador el observador que desea ser eliminado.
     */
    void quitarObserver(ObserverOpcionesMVC observador);

    /**
     * Actualiza a los observadores con un nuevo mensaje de aviso.
     * Este método es utilizado para notificar a los observadores sobre cambios
     * que requieran mostrar un mensaje de advertencia o información.
     * 
     * @param mensaje el mensaje de aviso que debe ser enviado a los observadores.
     */
    void actualizarMensajeAviso(String mensaje);
}
