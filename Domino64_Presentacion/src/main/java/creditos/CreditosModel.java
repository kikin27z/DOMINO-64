package creditos;

import eventosPantallas.ObservablePantallas;
import presentacion_utilities.NotificadorEvento;

/**
 *
 * @author karim
 */
public class CreditosModel {
    private final ObservablePantallas notificador;

    /**
     * Constructor que inicializa el modelo de la pantalla de inicio.
     * Obtiene la instancia del notificador de eventos para comunicarse con
     * otros componentes de la aplicación.
     */
    public CreditosModel() {
        notificador = NotificadorEvento.getInstance();
    }
    
    
    public void avisarMostrarInicio() {
        notificador.avisarMostrarInicio();
    }
}
