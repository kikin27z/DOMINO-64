package observer_logica;

/**
 * Interfaz que define los métodos necesarios para implementar el patrón Observer en la lógica del sistema.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObservableLogica {

    /**
     * Registra un nuevo observador para recibir notificaciones de eventos generados por la lógica.
     * 
     * @param observador el observador a agregar a la lista de observadores.
     */
    public void agregarObservador(ObservadorLogica observador);

    /**
     * Elimina un observador de la lista de observadores registrados, dejando de notificarle eventos.
     * 
     * @param observador el observador a eliminar de la lista de observadores.
     */
    public void eliminarObservador(ObservadorLogica observador);

    /**
     * Notifica a todos los observadores registrados de un evento específico ocurrido en la lógica.
     * 
     * @param evento el evento que contiene el tipo y los datos relevantes que se enviarán a cada observador.
     */
    public void notificarObservadores(EventoLogica<?> evento);
}
