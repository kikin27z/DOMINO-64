/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luiis
 */
public class Observable {
    private List<Observer> observadores;
    //private boolean estado;

    public Observable() {
        this.observadores = new ArrayList<>();
    }

    public void addObserver(Observer ob) {
        if (this.observadores.add(ob)) {
            System.out.println("observador agregado");
        } else {
            System.out.println("observador no agregado");
        }
    }

    public void removeObserver(Observer ob) {
        if (observadores.contains(ob)) {
            this.observadores.remove(ob);
        } else {
            System.out.println("el observador no esta en la lista");
        }
    }

    public void notifyObservers(Object context) {
        if (!this.observadores.isEmpty()) {
            this.observadores.forEach((o) -> {
                System.out.println("notificacion");
                o.update(this, context);
            });
        }
    }
}
