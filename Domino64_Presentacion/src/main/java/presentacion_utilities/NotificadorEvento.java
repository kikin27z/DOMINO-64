package presentacion_utilities;
import eventosOpcionesPartida.EventosOpcionesPartida;
import eventosOpcionesPartida.ObserverOpcionesPartida;
import eventosPantallas.EventosPantallas;
import eventosPantallas.ObserverPantalla;
import eventosPartida.EventosPartida;
import eventosPartida.ObserverPartida;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author karim
 */
public class NotificadorEvento implements EventosPantallas{
    private static NotificadorEvento notificarEvento; // Instancia única de Navegacion
    private List<ObserverPantalla> observersDisplay;
    
    private EventosOpcionesPartida opciones;
    private EventosPartida partida;

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
        for(var observer : observersDisplay){
            observer.avisarMostrarInicio();
        }
    }

    @Override
    public void avisarMostrarPartida() {
        for(var observer : observersDisplay){
            observer.avisarMostrarPartida();
        }
    }

    @Override
    public void avisarMostrarOpcionesPartida() {
        for(var observer : observersDisplay){
            observer.avisarMostrarOpcionesPartida();
        }
    }

    public EventosOpcionesPartida getOpciones() {
        return opciones;
    }

    public void setOpciones(EventosOpcionesPartida opciones) {
        this.opciones = opciones;
    }

    public EventosPartida getPartida() {
        return partida;
    }

    public void setPartida(EventosPartida partida) {
        this.partida = partida;
    }
    
    public void asignarObserverOpcionesPartida(ObserverOpcionesPartida observer){
        opciones.agregarObserver(observer);
    }
    
    public void asignarObserverPartida(ObserverPartida observer){
        partida.agregarObserver(observer);
    }
}
