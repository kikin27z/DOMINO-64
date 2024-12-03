package publicadorSuscriptor;

import eventoBase.Evento;

/**
 * Interfaz para representar a los suscriptores de cualquier tipo de evento.
 * Estos suscriptores pueden estar recibiendo diferentes tipos de eventos,
 * dependiendo de los eventos a los que se les haya suscrito
 * Esta interfaz cuenta con un solo metodo para recibir los eventos que 
 * el publicador notifique (publique)
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 * @param <T> Tipo generico que puede representar cualquier tipo de evento especifico
 */
public interface Suscriptor<T extends Evento> {
    /**
     * Método que notifica al suscriptor a que evento se suscribio.
     * @param evento Evento a recibir.
     */
    public void recibirEvento(T evento);
}
