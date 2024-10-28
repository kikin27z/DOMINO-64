package observer_logica;

/**
 * Interfaz que representa un observador en el patrón Observer para la capa de lógica.
 * Los componentes de la presentación pueden implementar esta interfaz para recibir notificaciones de eventos de la lógica.
 * 
 * @param <T> el tipo de datos que acompaña al evento.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObservadorLogica<T> {
    /**
     * Método que se ejecuta cuando la lógica notifica un evento.
     * 
     * @param evento el evento que contiene los datos necesarios para que el observador actualice su estado.
     */
    void notificar(EventoLogica<T> evento);
}
