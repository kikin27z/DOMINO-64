/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class Observable<T> {
    private List<Observer<T>> observers;
    //private boolean estado;

    public Observable() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer<T> ob) {
        this.observers.add(ob);
    }

    public void removeObserver(Observer<T> ob) {
        this.observers.remove(ob);
    }

    public void notifyObservers(T o){
        if (!this.observers.isEmpty()) {
            this.observers.forEach(ob -> {
                ob.update(o);
            });
        }
    }
}
