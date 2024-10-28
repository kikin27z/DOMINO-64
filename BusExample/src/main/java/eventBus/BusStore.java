/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventBus;

import event.Event;
import event.EventType;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author luisa M
 */
public class BusStore {
    private Map<EventType, Queue<Event>> events;
    
    public BusStore(){
        this.events = new ConcurrentSkipListMap<>();
    }
    
    public void saveEvent(EventType type, Event event){
        if(events.containsKey(type)){
            events.get(type).offer(event);
        }else{
            Queue<Event> queue = new ConcurrentLinkedQueue<>();
            queue.offer(event);
            events.put(type, queue);
        }
    }
}
