/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class Observable {
    private List<Observador> observadores;
    //private boolean estado;
    
    public Observable(){
        this.observadores=new ArrayList<>();
    }
    public void addObserver(Observador ob){
        if(this.observadores.add(ob))
            System.out.println("observador agregado");
        else 
            System.out.println("observador no agregado");
    }
    
    public void removeObserver(Observador ob){
        if(observadores.contains(ob))
            this.observadores.remove(ob);
        else
            System.out.println("el observador no esta en la lista");
    }
    
    public void notifyObservers(Object ... context){
        if (!this.observadores.isEmpty()) {
            this.observadores.forEach((o) -> {
                o.actualizarTablero(this, context);
            });
        }
    }
}
