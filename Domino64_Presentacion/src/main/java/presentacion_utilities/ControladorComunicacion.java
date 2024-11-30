package presentacion_utilities;

import eventoss.EventoMVCJugador;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;

/**
 *
 * @author luisa M
 */
public class ControladorComunicacion {
    public static final BlockingQueue<EventoMVCJugador> colaEventosALogica = new LinkedBlockingQueue<>();
    
    public static void iniciarHiloPresentacion() {
        System.out.println("en el iniciar hilo presentacion");
        new Thread(() -> Application.launch(App.class)).start();   
    }
    
    public static void enviarEventoALogica(EventoMVCJugador evento){
        try {
            colaEventosALogica.put(evento);
        } catch (InterruptedException e) {
            Logger.getLogger(ControladorComunicacion.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
}
