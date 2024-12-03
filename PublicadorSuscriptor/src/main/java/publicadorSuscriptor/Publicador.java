package publicadorSuscriptor;

import eventoBase.Evento;

/**
 * Interfaz para representar a un Publicador de eventos
 * de cualquier tipo.
 * 
 * @param <T> Tipo generico que va a representar cualquier tipo 
 * especifico de evento
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface Publicador<T extends Evento> {
    /**
     * Método para suscribir un suscriptor a un tipo de evento.
     *
     * @param tipoEvento El tipo de evento al que se va a suscribir.
     * @param suscriptor El suscriptor que se va a agregar.
     */
    public void suscribir(Enum tipoEvento, Suscriptor suscriptor);

    /**
     * Método para desuscribir un suscriptor de un tipo de evento.
     *
     * @param tipoEvento El tipo de evento del cual se va a desuscribir.
     * @param suscriptor El suscriptor que se va a eliminar.
     */
    public void desuscribir(Enum tipoEvento, Suscriptor suscriptor);

    /**
     * Método para publicar un evento de un tipo específico.
     *
     * @param tipoEvento El tipo de evento que se va a publicar.
     * @param evento El evento específico que se va a publicar.
     */
    public void publicarEvento(Enum tipoEvento, T evento);
}
