package abstraccion;

import domino64.eventos.base.Evento;
import java.util.List;
import observer.Observer;

/**
 * Interfaz que representa un cliente que interactúa con el sistema de eventos.
 * Permite enviar eventos y gestionar suscripciones de eventos.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ICliente {

    /**
     * Envía un evento al sistema.
     * @param evento El evento que se desea enviar.
     */
    public void enviarEvento(Evento evento);

    /**
     * Establece las suscripciones del cliente a eventos específicos.
     * @param suscripciones Lista de eventos a los cuales el cliente está suscrito.
     */
    public void establecerSuscripciones(List<Enum> suscripciones);

    /**
     * Agrega una suscripción de un observador a un evento específico.
     * @param evento El evento al que se quiere suscribir el observador.
     * @param ob El observador que va a recibir las notificaciones de ese evento.
     */
    public void agregarSuscripcion(Evento evento, Observer ob);

    /**
     * Remueve una suscripción de un observador a un evento específico.
     * @param evento El evento del cual el observador se va a desuscribir.
     * @param ob El observador que se desuscribirá del evento.
     */
    public void removerSuscripcion(Evento evento, Observer ob);

    //public void enviarTiposEventos(List<Enum> tiposEvento);
    //public void agregarTipoEvento(Enum tipo);
}
