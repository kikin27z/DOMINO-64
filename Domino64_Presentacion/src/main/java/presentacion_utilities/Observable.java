package presentacion_utilities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisa M
 */
public abstract class Observable {

    private List<Observador> observadores;

    public Observable() {
        this.observadores = new ArrayList<>();
    }

    public void addObserver(Observador observador) {
        observadores.add(observador);
    }

    public void removeObserver(Observador ob) {
        this.observadores.remove(ob);
    }

    public void notifyObservers() {
        for(Observador observador: observadores){
            observador.notificar();
        }
    }
}
