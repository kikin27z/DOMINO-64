package manejadores;

import eventosLogica.EventosOpcionesLogica;
import eventosLogica.ObserverOpcionesLogica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author karim
 */
public class ManejadorEventosLogica {
    
    
    
    private class NotificarEventosOpcionesPartida implements EventosOpcionesLogica{
        private List<ObserverOpcionesLogica> observers;
        public NotificarEventosOpcionesPartida() {
            observers = new ArrayList<>();
        }

        
        
        @Override
        public void agregarObserver(ObserverOpcionesLogica observador) {
            observers.add(observador);
        }

        @Override
        public void quitarObserver(ObserverOpcionesLogica observador) {
            observers.add(observador);
        }

        @Override
        public void avisarValidarCodigoPartida(String codigo) {
            
        }
        
    }
}
