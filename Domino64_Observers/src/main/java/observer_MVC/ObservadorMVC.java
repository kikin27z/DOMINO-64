package observer_MVC;

/**
 * Interfaz que representa un observador en el patrón Observer dentro de una arquitectura MVC.
 * Los componentes que implementan esta interfaz pueden ser notificados
 * de eventos y cambios de estado en los componentes observables, como el Modelo, Vista o el Control.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObservadorMVC {
    /**
     * Método que se ejecuta cuando el observable notifica un cambio.
     * 
     * @param evento el evento que contiene los datos y el contexto del cambio ocurrido
     */
    void actualizar(EventoMVC<?> evento);
}
