
package cliente_suscripciones;

import domino64.eventos.base.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author luisa M
 * @param <T>
 */
public class ObservableCliente<T extends Evento> {
    private final Map<Enum, List<ObserverCliente>> observers;
    //private boolean estado;

    public ObservableCliente() {
        this.observers = new ConcurrentHashMap<>();
    }

    public void addObserver(Enum tipo, ObserverCliente ob) {
        observers.merge(tipo, new ArrayList<>(List.of(ob)), (lista, nuevaLista) -> {
            lista.add(ob);
            return lista;
        });
    }

    public void removeObserver(Enum tipo, ObserverCliente ob) {
        observers.computeIfPresent(tipo, (cat, obs) -> {
            obs.remove(ob);
            return obs;
        });
    }

    public void notifyObservers(Enum tipo, T o){
        if (!this.observers.isEmpty()) {
            if(observers.containsKey(tipo))
                observers.get(tipo).forEach(ob -> ob.update(o));
        }
    }
}