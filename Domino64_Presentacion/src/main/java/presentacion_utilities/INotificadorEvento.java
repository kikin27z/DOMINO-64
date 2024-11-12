package presentacion_utilities;

import eventosLobby.ObservableLobby;
import eventosLobby.ObserverLobby;
import eventosOpcionesPartida.ObservableOpcionesPartida;
import eventosOpcionesPartida.ObserverOpcionesPartida;
import eventosPartida.ObservablePartida;
import eventosPartida.ObserverPartida;

/**
 * Interfaz que define los métodos para asignar los observables y observers
 * correspondientes a las diferentes entidades dentro del sistema.
 *
 * Los métodos permiten que los observables y sus respectivos observers se
 * conecten, facilitando la comunicación entre las diferentes partes del
 * sistema.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface INotificadorEvento {

    /**
     * Asigna el observable de opciones de partida.
     *
     * @param observable el objeto observable de opciones de partida.
     */
    void asignarObservableOpcionesPartida(ObservableOpcionesPartida observable);

    /**
     * Asigna el observable de partida.
     *
     * @param observable el objeto observable de partida.
     */
    void asignarObservablePartida(ObservablePartida observable);

    /**
     * Asigna el observable del lobby.
     *
     * @param observable el objeto observable del lobby.
     */
    void asignarObservableLobby(ObservableLobby observable);

    /**
     * Asigna el observer de opciones de partida.
     *
     * @param observer el objeto observer de opciones de partida.
     */
    void asignarObserverOpcionesPartida(ObserverOpcionesPartida observer);

    /**
     * Asigna el observer de partida.
     *
     * @param observer el objeto observer de partida.
     */
    void asignarObserverPartida(ObserverPartida observer);

    /**
     * Asigna el observer del lobby.
     *
     * @param observer el objeto observer del lobby.
     */
    void asignarObserverLobby(ObserverLobby observer);
}
