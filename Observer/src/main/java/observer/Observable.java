/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer;

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
public class Observable<T extends Evento> {
    private final Map<Enum<?>, List<Observer>> observers;
    //private boolean estado;

    public Observable() {
        this.observers = new ConcurrentHashMap<>();
    }

    public void addObserver(Enum<?> tipo, Observer ob) {
        observers.merge(tipo, new ArrayList<>(List.of(ob)), (lista, nuevaLista) -> {
            lista.add(ob);
            return lista;
        });
    }

    public void removeObserver(Enum<?> tipo, Observer ob) {
        observers.computeIfPresent(tipo, (cat, obs) -> {
            obs.remove(ob);
            return obs;
        });
    }

    public void notifyObservers(Enum<?> tipo, T o){
        if (!this.observers.isEmpty()) {
            if(observers.containsKey(tipo))
                observers.get(tipo).forEach(ob -> ob.update(o));
        }
    }
}
