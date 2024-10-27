/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverClient;

import eventBus.Event;
import eventBus.EventType;
import eventBus.Subscriber;

/**
 *
 * @author luisa M
 */
public class NewClientEvent extends Event<String> {
    
    public NewClientEvent(Subscriber sub){
        this.setType(EventType.NEW_CLIENT);
        this.setPublisherName(sub.getName());
        this.setInfo("Se conecto un nuevo cliente. Nombre del cliente: "+this.getPublisherName());
    }
    
    public String getEventDetails(){
        return this.getInfo();
    }
    
}
