/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventBus;

/**
 *
 * @author luisa M
 */
public class BusCore {
    private static BusCore core;
    private final BusStore store;
    private final BusMessenger messenger;
    
    private BusCore(){
        this.store = new BusStore();
        this.messenger = new BusMessenger();
    }
    
    public static synchronized BusCore getInstance(){
        if(core == null)
            core = new BusCore();
        return core;
    }
    
    public void postEvent(EventType type, Event event){
        messenger.notifyEvent(type,event);
        
    }
    
    public void addSub(Subscriber sub, EventType eventType){
        messenger.addSubscriber(sub, eventType);
    }
}
