package creditos;

import eventosCreditos.ObservableCreditos;
import eventosCreditos.ObserverCreditos;

/**
 *
 * @author karim
 */
public class CreditosModel implements ObservableCreditos{

    /**
     * Constructor que inicializa el modelo de la pantalla de inicio.
     * Obtiene la instancia del notificador de eventos para comunicarse con
     * otros componentes de la aplicación.
     */
    public CreditosModel() {
    }
    
    


    @Override
    public void agregarObserver(ObserverCreditos observador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void quitarObserver(ObserverCreditos observador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void avisarIrInicio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
