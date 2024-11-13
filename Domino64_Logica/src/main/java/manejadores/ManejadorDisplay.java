package manejadores;

import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
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
    private final MediadorManejadores modeloLogica;
    private INavegacion navegacion;

    public ManejadorDisplay() {
        notificador = NotificadorEvento.getInstance();
        modeloLogica = Control.obtenerManejadorModelo();
        
        
        notificador.agregarObserver(this);
        iniciarJuego();
    }

    public void iniciarJuego(){
        System.out.println(Thread.currentThread());
        navegacion = Navegacion.getInstance();
        navegacion.iniciarApp();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ManejadorDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //------------------------Eventos de cambiar pantallas--------------------
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
        modeloLogica.crearObserverOpcionesPartida();
    }

    @Override
    public void avisarMostrarCreditos() {
    }

    @Override
    public void avisarMostrarFinJuego() {
    }

    @Override
    public void avisarMostrarLobby(LobbyDTO lobby) {
        navegacion.cambiarLobby(lobby);
    }
    
}
