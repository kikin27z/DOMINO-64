package presentacion_utilities;

import eventosLobby.ObservableLobby;
import eventosLobby.ObserverLobby;
import eventosOpcionesPartida.ObserverOpcionesPartida;
import eventosPantallas.ObserverPantalla;
import eventosPartida.ObserverPartida;
import java.util.ArrayList;
import java.util.List;
import eventosOpcionesPartida.ObservableOpcionesPartida;
import eventosPantallas.ObservablePantallas;
import eventosPartida.ObservablePartida;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class NotificadorEvento implements INotificadorEvento, ObservablePantallas {
    private static NotificadorEvento notificarEvento; // Instancia única de Navegacion
    private final List<ObserverPantalla> observersDisplay;
    private ObservableOpcionesPartida opciones;
    private ObservablePartida partida;
    private ObservableLobby lobby;

    // Constructor privado para evitar instanciación externa
    private NotificadorEvento() {
        observersDisplay = new ArrayList<>();
    }

    /**
     * Obtiene la instancia única de Navegacion.
     *
     * @return La instancia de Navegacion.
     */
    public static NotificadorEvento getInstance() {
        if (notificarEvento == null) {
            notificarEvento = new NotificadorEvento(); // Crea la instancia si no existe
        }
        return notificarEvento;
    }

    @Override
    public void agregarObserver(ObserverPantalla observador) {
        observersDisplay.add(observador);
    }

    @Override
    public void quitarObserver(ObserverPantalla observador) {
        observersDisplay.remove(observador);
    }

    @Override
    public void avisarMostrarInicio() {
        for (var observer : observersDisplay) {
            observer.avisarMostrarInicio();
        }
    }

    @Override
    public void avisarMostrarCreditos() {
        for (var observer : observersDisplay) {
            observer.avisarMostrarCreditos();
        }
    }

    @Override
    public void avisarMostrarPartida() {
        for (var observer : observersDisplay) {
            observer.avisarMostrarPartida();
        }
    }

    @Override
    public void avisarMostrarOpcionesPartida() {
        for (var observer : observersDisplay) {
            observer.avisarMostrarOpcionesPartida();
        }
    }

    @Override
    public void avisarMostrarFinJuego() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void avisarMostrarLobby() {
    
    }
    //-------------------Metodos de INotificarEventos---------------------
    @Override
    public void asignarObservableOpcionesPartida(ObservableOpcionesPartida observable) {
        this.opciones = observable;
    }

    @Override
    public void asignarObservablePartida(ObservablePartida observable) {
        this.partida = observable;
    }

    @Override
    public void asignarObservableLobby(ObservableLobby observable) {
        this.lobby = observable;
    }

    @Override
    public void asignarObserverLobby(ObserverLobby observer) {
        lobby.agregarObserver(observer);
    }

    @Override
    public void asignarObserverOpcionesPartida(ObserverOpcionesPartida observer) {
        opciones.agregarObserver(observer);
    }

    @Override
    public void asignarObserverPartida(ObserverPartida observer) {
        partida.agregarObserver(observer);
    }
}
