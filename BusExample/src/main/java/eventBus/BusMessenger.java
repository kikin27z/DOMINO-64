/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventBus;

import event.Event;
import event.EventType;
import exceptions.BusException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 * @author luisa M
 */
public class BusMessenger {
    Map<EventType,Set<Subscriber>> subscribers;
    
    public BusMessenger(){
        this.subscribers = new ConcurrentHashMap<>();
    }
    
    /**
     * Metodo para notificar la publicacion del evento 
     * a los suscriptores de dicho evento.
     * 
     * Este metodo recorre el mapeo de (evento, suscriptores), 
     * buscando a aquellos suscriptores que esten mapeados 
     * al tipo de evento enviado en el parametro. En caso
     * de que el publicador del evento tambien esté suscrito
     * a los eventos de este tipo, la notificacion del evento no
     * será enviada a él para evitar la redundancia.
     * @param eventType
     * @param event Evento a notificar a los suscriptores del mismo
     */
    public void notifyEvent(EventType eventType, Event event){
        Set<Subscriber> subs = subscribers.get(eventType);
        subs.forEach(sub ->{
            if(!sub.getName().equalsIgnoreCase(
                    event.getPublisherName())){
                sub.catchEvent(event);
            }
        });
    }
    
    /**
     * Verifica si el evento ya ha sido registrado 
     * @param eventType Tipo de evento a verificar si esta registrado
     * @return true si existe un mapeo con el tipo de evento del parametro
     */
    public boolean hasEvent(EventType eventType){
        return subscribers.containsKey(eventType);
    }
    
    /**
     * Agrega un suscriptor para el evento del parametro
     * @param sub Suscriptor a agregar
     * @param eventType tipo de evento al cual se le va a agregar el suscriptor
     */
    public void addSubscriber(Subscriber sub, EventType eventType){
        Set<Subscriber> subs= subscribers.getOrDefault(eventType, new ConcurrentSkipListSet<>());
        subs.add(sub);
        subscribers.put(eventType, subs);
    }
    
    /**
     * Remueve el suscriptor especificado del evento del parametro.
     * @param sub El suscriptor a eliminar
     * @param event El evento del cual se quiere eliminar el suscriptor
     * @throws BusException en caso de que el evento no tenga ese suscriptor registrado
     */
    public void removeSubscriber(Subscriber sub, EventType type)throws BusException{
        if (!subscribers.get(type).remove(sub))
            throw new BusException("El suscriptor no es suscriptor del evento");
    }
}
