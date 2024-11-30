package builder;

/**
 * Clase abstracta que actúa como director en el patrón de diseño Builder para construir eventos.
 * 
 * <p>
 * El propósito de esta clase es dirigir el proceso de construcción de eventos utilizando un 
 * objeto builder proporcionado. Esto permite separar la lógica de construcción del evento 
 * de su representación específica.
 * </p>
 * 
 * @param <T> Tipo genérico que extiende de {@link EventBuilder}, representando el builder
 *            utilizado para construir los eventos.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public abstract class DirectorEventos<T extends EventBuilder> {

    /**
     * Builder utilizado para construir los eventos.
     */
    public final T builder;

    /**
     * Constructor que inicializa el director con un builder específico.
     * 
     * @param builder el builder que será utilizado para construir los eventos.
     */
    public DirectorEventos(T builder) {
        this.builder = builder;
    }
}
