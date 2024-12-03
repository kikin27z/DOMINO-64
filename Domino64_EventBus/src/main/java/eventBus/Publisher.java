package eventBus;

import publicadorSuscriptor.Publicador;
import publicadorSuscriptor.Suscriptor;
import eventoBase.Evento;

/**
 * Clase para representar a quienes pueden publicar eventos en el bus. Cada
 * publicador tiene un id que lo identifica, este id se le va a asignar a los
 * eventos que publique.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class Publisher implements Publicador {

    // Identificador único del publicador
    private int idPublicador;

    // Constructor por defecto
    public Publisher() {
    }

    // Método para establecer el id del publicador
    public void setId(int id) {
        this.idPublicador = id;
    }

    // Método para obtener el id del publicador
    public int getIdPublicador() {
        return idPublicador;
    }

    /**
     * Implementación del método para suscribir a un suscriptor a un tipo de
     * evento. Se usa el bus de eventos para agregar al suscriptor a la lista de
     * suscriptores para el evento correspondiente.
     */
    @Override
    public void suscribir(Enum tipoEvento, Suscriptor suscriptor) {
        // Añade el suscriptor al bus para el tipo de evento específico
        BusCore.getInstance().addSub((Subscriber) suscriptor, tipoEvento);
        System.out.println("Se suscribió a evento: " + tipoEvento.toString());
    }

    /**
     * Implementación del método para desuscribir a un suscriptor de un evento.
     * El suscriptor es removido del bus de eventos para el tipo de evento
     * específico.
     */
    @Override
    public void desuscribir(Enum tipoEvento, Suscriptor suscriptor) {
        // Remueve el suscriptor del bus de eventos para el tipo de evento específico
        BusCore.getInstance().removeSub((Subscriber) suscriptor, tipoEvento);
    }

    /**
     * Implementación del método para publicar un evento en el bus de eventos.
     * El evento es enviado a todos los suscriptores del tipo de evento
     * especificado.
     */
    @Override
    public void publicarEvento(Enum tipoEvento, Evento evento) {
        System.out.println("Publicando Evento");
        // Publica el evento en el bus para que todos los suscriptores lo reciban
        BusCore.getInstance().postEvent(tipoEvento, evento);
    }
}
