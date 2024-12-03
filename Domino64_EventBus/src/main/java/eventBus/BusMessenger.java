package eventBus;

import eventoBaseError.TipoError;
import eventoBaseError.EventoError;
import eventoBase.Evento;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Clase encargada de notificar los eventos a los suscriptores de cada evento
 * especifico
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class BusMessenger {

    private Map<Enum, Set<Subscriber>> subscribers;

    public BusMessenger() {
        this.subscribers = new ConcurrentHashMap<>();
    }

    /**
     * Metodo para notificar la publicacion del evento a los suscriptores de
     * dicho evento.
     *
     * Este metodo recorre el mapeo de (evento, suscriptores), buscando a
     * aquellos suscriptores que esten mapeados al tipo de evento enviado en el
     * parametro. En caso de que el publicador del evento tambien esté suscrito
     * a los eventos de este tipo, la notificacion del evento no será enviada a
     * él para evitar la redundancia.
     *
     * @param tipoEvento el tipo de evento a notificar.
     * @param event Evento a notificar a los suscriptores del mismo
     */
    public void notifyEvent(Enum tipoEvento, Evento event) {
        Set<Subscriber> subs = subscribers.get(tipoEvento);
        System.out.println("evento en el bus messenger: " + event);
        subs.forEach(sub -> {
            if (sub.getSubscriberId() != event.getIdPublicador()) {
                sub.recibirEvento(event);
            }
        });
    }

    /**
     * metodo para notificar los errores a los suscriptores. Este metodo se debe
     * usar cuando ocurre un error logico, por lo tanto, lo deben llamar los
     * manejadores logicos. Si al manejar un evento generado por el usuario
     * ocurre un error, se le debe notificar al usuario, (o tambien a otros
     * manejadores) por lo tanto, el manejador donde se genero el error debera
     * notificarlo
     *
     * @param error Error a notificar a los suscriptores interesados
     */
    public void notifyError(EventoError error) {
        Set<Subscriber> subs = subscribers.get(error.getTipo());
        if (error.getTipo().equals(TipoError.ERROR_LOGICO)) {
            for (Subscriber sub : subs) {
                if (sub.getSubscriberId() == error.getIdPublicador()) {
                    sub.recibirEvento(error);
                    break;
                }
            }
        } else if (error.getTipo().equals(TipoError.ERROR_DE_SERVIDOR)) {
            subs.forEach(sub -> {
                if (sub.getSubscriberId() != error.getIdPublicador()) {
                    sub.recibirEvento(error);
                }
            });
        }

    }

    /**
     * Verifica si el evento ya ha sido registrado
     *
     * @param eventType Tipo de evento a verificar si esta registrado
     * @return true si existe un mapeo con el tipo de evento del parametro
     */
    public boolean hasEventType(Enum eventType) {
        return subscribers.containsKey(eventType);
    }

    /**
     * Agrega un suscriptor para el evento del parametro
     *
     * @param sub Suscriptor a agregar
     * @param tipoEvento tipo de evento al cual se le va a agregar el suscriptor
     */
    public void addSubscriber(Subscriber sub, Enum tipoEvento) {
        Set<Subscriber> subs = subscribers.getOrDefault(tipoEvento, new ConcurrentSkipListSet<>());
        subs.add(sub);
        subscribers.put(tipoEvento, subs);
    }

    /**
     * Remueve el suscriptor especificado del evento del parametro.
     *
     * @param sub El suscriptor a eliminar
     * @param tipoEvento Tipo de evento del cual se quiere eliminar el
     * suscriptor
     */
    public void removeSubscriber(Subscriber sub, Enum tipoEvento) {
        subscribers.computeIfPresent(tipoEvento, (tipo, subs) -> {
            if (subs.contains(sub)) {
                subs.remove(sub);
            }
            return subs;
        });
    }
}
