/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author luiis
 */
public class Dominio {

//    public static void main(String[] args) {
        
//        List<String> personas = new ArrayList<>();
//        personas.add("luisa");
//        personas.add("martha");
//        personas.add("karina");
//        personas.add("sofia");
//        
////        for (String persona : personas) {
////            System.out.println(persona);
////        }
////        
//        Collections.shuffle(personas);
//        System.out.println();
//        for (String persona : personas) {
//            System.out.println(persona);
//        }
        
//        Stack<Integer> stack = new Stack();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//        
//        for(Integer element: stack){
//            System.out.println(element);
//        }
//        
//        System.out.println("peak: "+stack.peek());
//        System.out.println("first: "+stack.firstElement());
//        System.out.println("pop: "+stack.pop());
//        System.out.println("peak: "+stack.peek());
//        System.out.println("pop: "+stack.pop());
//        System.out.println("pop: "+stack.pop());
//    }
    
    public static void main(String[] args) {
        Observable1 observable = new Observable1();

        Observer1 observer1 = new Observer1();
        Observer2 observer2 = new Observer2();

        observable.addObserver(observer1);
        observable.addObserver(observer2);

        Event evento = new Event();
        evento.setType(1);
        observable.setEvento(evento);

//        INavegacion navegacion = Navegacion.getInstance();
//        navegacion.iniciarApp();
    }
}

class Observable1 extends Observablee<Observable1> {

    Event evento;
    private String contexto;
    
    public void setContexto(String contexto){
        this.contexto = contexto;
    }
    
    public String getContexto(){
        return this.contexto;
    }
    
    public void setEvento(Event evento) {
        this.evento = evento;
        this.notifyObservers(this, evento);
    }

    public Event getEvento() {
        return this.evento;
    }

}

class Observer1 implements Observer<Observable1> {

    @Override
    public void update(Observable1 ob, Event e) {
        if(e.getType()==1){
            System.out.println(ob.getContexto());
        }
    }

}

class Observer2 implements Observer<Observable1> {

    @Override
    public void update(Observable1 ob, Event e) {
        if(e.getType()==2)
            System.out.println(ob.getContexto());
    }

}

class Event{
    private int type;
    
    public void setType(int type){
        this.type = type;
    }
   
    public int getType(){
        return this.type;
    }
}

class Observablee<T> {

    private List<Observer<T>> observadores;
    //private boolean estado;

    public Observablee() {
        this.observadores = new ArrayList<>();
    }

    public void addObserver(Observer<T> ob) {
        if (this.observadores.add(ob)) {
            System.out.println("observador agregado");
        } else {
            System.out.println("observador no agregado");
        }
    }

    public void removeObserver(Observer<T> ob) {
        if (observadores.contains(ob)) {
            this.observadores.remove(ob);
        } else {
            System.out.println("el observador no esta en la lista");
        }
    }

    public void notifyObservers(T o, Event event) {
        if (!this.observadores.isEmpty()) {
            this.observadores.forEach(ob -> {
                ob.update(o, event);
            });
        }
    }
}

interface Observer<T> {

    public void update(T ob, Event event);
}
