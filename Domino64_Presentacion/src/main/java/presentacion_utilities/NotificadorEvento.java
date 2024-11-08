package presentacion_utilities;
import eventosPantallas.EventosPantallas;
import eventosPantallas.ObserverPantalla;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author karim
 */
public class NotificadorEvento implements EventosPantallas{
    private static NotificadorEvento notificarEvento; // Instancia única de Navegacion
    private List<ObserverPantalla> observers;

    // Constructor privado para evitar instanciación externa
    private NotificadorEvento() {
        observers = new ArrayList<>();
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
        observers.add(observador);
    }

    @Override
    public void quitarObserver(ObserverPantalla observador) {
        observers.remove(observador);
    }

    @Override
    public void avisarMostrarInicio() {
        for(var observer : observers){
            observer.avisarMostrarInicio();
        }
    }

    @Override
    public void avisarMostrarPartida() {
        for(var observer : observers){
            observer.avisarMostrarPartida();
        }
    }

    @Override
    public void avisarMostrarOpcionesPartida() {
        for(var observer : observers){
            observer.avisarMostrarOpcionesPartida();
        }
    }
    
  

}
