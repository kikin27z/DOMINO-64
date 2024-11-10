package manejadores;

import eventosOpcionesPartida.ObserverOpcionesPartida;
import java.util.concurrent.ExecutorService;
import presentacion_utilities.NotificadorEvento;

/**
 *
 * @author karim
 */
public class ManejadorModelo {
    private final NotificadorEvento notificador;
    private final ExecutorService hiloPrincipal;
    
    public ManejadorModelo() {
        notificador = NotificadorEvento.getInstance();
        hiloPrincipal = Control.getHiloPrincipal();
    }

    
    public void crearObserverOpcionesPartida(){
        notificador.asignarObserverOpcionesPartida(new ControlEventosOpcionesPartida());
    }
    
    private class ControlEventosOpcionesPartida implements ObserverOpcionesPartida{
        @Override
        public void crearPartida() {
            System.out.println("Modo");
        }

        @Override
        public void buscarPartida(String codigoPartida) {
            hiloPrincipal.execute(() -> {
                System.out.println("Modo");
                System.out.println("Ejecutando en: " + Thread.currentThread());
            });
        }
    }
}
