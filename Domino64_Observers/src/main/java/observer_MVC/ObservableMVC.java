package observer_MVC;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que representa un componente observable dentro del patrón Observer en una arquitectura MVC.
 * Los componentes del Modelo, View o el Control en MVC pueden heredar de esta clase para implementar 
 * la capacidad de notificar cambios a los observadores registrados.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public abstract class ObservableMVC {
    private final List<ObservadorMVC> observadores = new ArrayList<>();

    /**
     * Registra un nuevo observador para recibir notificaciones de eventos.
     * 
     * @param observador el observador que se va a agregar a la lista de observadores
     */
    public void agregarObservador(ObservadorMVC observador) {
        observadores.add(observador);
    }

    /**
     * Elimina un observador previamente registrado para que no reciba más notificaciones.
     * 
     * @param observador el observador que se va a eliminar de la lista de observadores
     */
    public void eliminarObservador(ObservadorMVC observador) {
        observadores.remove(observador);
    }

    /**
     * Notifica a todos los observadores registrados de un cambio al enviar un evento específico.
     * Cada observador recibe el mismo evento para procesarlo de acuerdo a su lógica.
     * 
     * @param evento el evento que contiene los datos necesarios para que el observador
     *               actualice su estado o realice alguna acción en respuesta al cambio.
     */
    public void notificarObservadores(EventoMVC<?> evento) {
        for (ObservadorMVC observador : observadores) {
            observador.actualizar(evento);
        }
    }
}
