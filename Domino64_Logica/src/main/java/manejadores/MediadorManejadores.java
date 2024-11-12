package manejadores;

import eventosOpcionesPartida.ObserverOpcionesPartida;
import java.util.concurrent.ExecutorService;
import presentacion_utilities.NotificadorEvento;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class MediadorManejadores {
    private final NotificadorEvento notificador;
    private final ExecutorService hiloPrincipal;
    
    public MediadorManejadores() {
        notificador = NotificadorEvento.getInstance();
        hiloPrincipal = Control.getHiloPrincipal();
    }

    
    public void crearObserverOpcionesPartida(){
        notificador.asignarObserverOpcionesPartida(new ControlEventosOpcionesPartida());
    }
    
    private class ControlEventosOpcionesPartida implements ObserverOpcionesPartida{
        private ManejadorCuenta cuenta;
        
        public ControlEventosOpcionesPartida() {
            cuenta = Control.obtenerManejadorCuenta();
        }
        
        
        @Override
        public void crearPartida() {
            System.out.println("Modo");
        }

        @Override
        public void buscarPartida(String codigoPartida) {
            hiloPrincipal.execute(() -> {
                cuenta.contruisr(codigoPartida);
            });
        }
    }
}
