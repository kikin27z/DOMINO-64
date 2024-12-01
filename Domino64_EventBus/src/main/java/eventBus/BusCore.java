package eventBus;

import domino64.eventos.base.*;
import domino64.eventos.base.error.EventoError;

/**
 * Clase principal del EventBus. 
 * Aqui se reciben los eventos y las suscripciones a eventos
 * @author luisa M
 * @author karim F
 */
public class BusCore {
    private static BusCore core;
    private final BusMessenger messenger;
    
    private BusCore(){
        this.messenger = new BusMessenger();
    }
    
    public static synchronized BusCore getInstance(){
        if(core == null)
            core = new BusCore();
        return core;
    }
    
    /**
     * Metodo para publicar eventos.
     * En caso de que el evento sea de tipo EventoError se notifica
     * el error a los suscriptores involucrados.
     * En el caso de los eventos normales, primero se verifica si 
     * ya existe un mapeo con ese evento. Es decir, si alguien ya
     * se suscribio para ese evento.
     * @param tipo El tipo de evento a publicar
     * @param evento El evento a publicar
     */
    public void postEvent(Enum tipo, Evento evento){
        if(evento instanceof EventoError eventoError){
            messenger.notifyError(eventoError);
        }else{
            if (messenger.hasEventType(tipo))
                messenger.notifyEvent(tipo, evento);
        }
    }
    
    /**
     * Agrega un suscriptor para un tipo especifico de evento
     * @param sub El suscriptor a agregar al conjunto de suscriptores para dicho evento
     * @param eventType El tipo de evento al cual se quiere suscribir
     */
    public void addSub(Subscriber sub, Enum eventType){
        messenger.addSubscriber(sub, eventType);
        System.out.println("se agergo sub "+sub.getSubscriberId()+" a categoria: "+eventType.toString());
    }
    
    /**
     * Elimina un suscriptor para un tipo especifico de evento
     * @param sub El suscriptor a eliminar del conjunto de suscriptores para dicho evento
     * @param eventType El tipo de evento del cual se quiere desuscribir
     */
    public void removeSub(Subscriber sub, Enum eventType){
        messenger.removeSubscriber(sub, eventType);
    }
}
