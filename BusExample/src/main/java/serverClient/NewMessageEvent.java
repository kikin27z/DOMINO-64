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
public class NewMessageEvent extends Event<String> {
    private final String message;
    
    public NewMessageEvent(Subscriber sub, String message) {
        this.setType(EventType.NEW_MESSAGE);
        this.setPublisherName(sub.getName());
        this.message = message;
        setEventDetails();
        
    }

    private void setEventDetails(){
        StringBuilder info = new StringBuilder("El cliente ");
        info.append(this.getPublisherName()).append(" ");
        info.append("envio un nuevo mensaje:\n");
        info.append(message);
        
        this.setInfo(info.toString());
    }
    
    public String getEventDetails() {
        return this.getInfo();
    }

}
