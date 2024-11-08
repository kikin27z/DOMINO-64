package manejadores;

import eventosPantallas.ObserverPantalla;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion_utilities.INavegacion;
import presentacion_utilities.Navegacion;
import presentacion_utilities.NotificadorEvento;

/**
 *
 * @author karim
 */
public final class ManejadorDisplay implements ObserverPantalla{
    private final NotificadorEvento notificador;
    private INavegacion navegacion;
    //private ICliente cliente;

    public ManejadorDisplay() {
        notificador = NotificadorEvento.getInstance();
        notificador.agregarObserver(this);
        iniciarJuego();
    }

    public void iniciarJuego(){
        navegacion = Navegacion.getInstance();
        navegacion.iniciarApp();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ManejadorDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void avisarMostrarInicio() {
        navegacion.cambiarInicio();
    }

    @Override
    public void avisarMostrarPartida() {
        navegacion.cambiarPartida();
    }

    @Override
    public void avisarMostrarOpcionesPartida() {
        navegacion.cambiarOpcionesPartida();
    }
    
}
