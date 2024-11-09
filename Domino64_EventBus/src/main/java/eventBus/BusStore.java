/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventBus;

import domino64.eventos.base.*;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author luisa M
 * @deprecated Esta clase ya no esta en uso
 */
public class BusStore {
    private Map<Enum<?>, Queue<Subscriber>> subsOnStandBy;
    
    public BusStore(){
        this.subsOnStandBy = new ConcurrentSkipListMap<>();
    }
    
    /**
     * Agrega el suscriptor a una cola de espera para 
     * el evento dado en el parametro
     * @param sub Suscriptor a poner en espera
     * @param event Evento que estara esperando el suscriptor
     */
    public void putOnStandBy(Subscriber sub, Evento event){
        subsOnStandBy.merge(event.getTipo(), 
                new ConcurrentLinkedQueue<>(List.of(sub)),
                (queue,newQueue)->{
                    queue.offer(sub);
                    return queue;
                });
    }
    
    /**
     * Elimina a un suscriptor de la cola de espera
     * del evento especificado.
     * @param sub
     * @param event 
     */
    public void removeFromQueue(Subscriber sub, Evento event){
        subsOnStandBy.computeIfPresent(event.getTipo(),
                (t,queue)->{
                    queue.poll();
                    return queue;
                });
        System.out.println("se removio de la cola");
    }
    
    /**
     * Verifica si el evento especificado tiene suscriptores
     * a la espera
     * @param evento Evento a verificar
     * @return El suscriptor que esta al inicio de la cola de espera,
     * si es que hay. Si no hay suscriptores, retorna null
     */
    public Subscriber hasSubOnStandBy(Evento evento){
        Queue<Subscriber> subs = subsOnStandBy.get(evento.getTipo());
        if(subs!=null)
            return subs.peek();
        return null;
    }
}
