/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventBus;

import event.Event;

/**
 *
 * @author luisa M
 */
public abstract class Publisher {
    private final BusCore bus;
    
    public Publisher(BusCore bus){
        this.bus = bus;
    }
    
    public void sendEvent(Event newEvent){
        bus.postEvent(newEvent.getType(), newEvent);
    }
    
}
