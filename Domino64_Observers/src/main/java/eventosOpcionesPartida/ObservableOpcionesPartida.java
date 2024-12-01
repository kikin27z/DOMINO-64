package eventosOpcionesPartida;

/**
 * Interfaz que define los métodos para un objeto observable en el contexto de las opciones de la partida.
 * Los objetos que implementan esta interfaz permiten agregar o quitar observadores y notificarles sobre eventos relacionados con las opciones de la partida,
 * como la creación de una nueva partida o la búsqueda de partidas existentes.
 * 
 * La interfaz sigue el patrón de diseño de Observador, donde el objeto observable mantiene una lista de observadores que se actualizan cuando ocurre un evento.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObservableOpcionesPartida {

    /**
     * Agrega un observador que será notificado cuando ocurran eventos relacionados con las opciones de la partida.
     * 
     * @param observador el observador que debe ser agregado. Este debe implementar la interfaz {@link ObserverOpcionesPartida}.
     */
    void agregarObserver(ObserverOpcionesPartida observador);

    /**
     * Elimina un observador que previamente había sido agregado.
     * 
     * @param observador el observador que debe ser eliminado. Este debe implementar la interfaz {@link ObserverOpcionesPartida}.
     */
    void quitarObserver(ObserverOpcionesPartida observador);

    /**
     * Notifica a todos los observadores que se debe crear una nueva partida.
     * Los observadores reaccionarán a este evento según lo definido en su implementación del método correspondiente.
     */
    void avisarCrearPartida();

    /**
     * Notifica a todos los observadores que se debe buscar una partida existente.
     * Los observadores reaccionarán a este evento según lo definido en su implementación del método correspondiente.
     */
    void avisarBuscarPartida();
    
    public void avisarIrInicio();
}
