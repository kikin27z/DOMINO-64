package observer_MVC;

/**
 * Clase que representa un evento en el patrón Observer dentro de una arquitectura MVC.
 *
 * @param <T> el tipo de datos que acompaña al evento, proporcionando contexto adicional sobre el cambio ocurrido
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class EventoMVC<T> {
    private final String tipo;
    private final T data;

    /**
     * Constructor que inicializa un nuevo evento con un tipo y datos asociados.
     * 
     * @param tipo el tipo de evento que describe el cambio, como una cadena de texto que identifica
     *             la naturaleza del evento
     * @param data los datos asociados con el evento, proporcionando detalles adicionales sobre el cambio
     */
    public EventoMVC(String tipo, T data) {
        this.tipo = tipo;
        this.data = data;
    }

    /**
     * Devuelve el tipo del evento.
     * 
     * @return el tipo del evento como cadena de texto
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Devuelve los datos asociados con el evento.
     * 
     * @return los datos del evento de tipo {@code T}
     */
    public T getData() {
        return data;
    }
}
