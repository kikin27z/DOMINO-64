package manejadores;

import logicaLobby.ManejadorCuenta;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.UnirseDTO;
import eventosLobby.ObserverLobby;
import eventosOpcionesPartida.ObserverOpcionesPartida;
import eventosPartida.ObserverPartida;
import java.util.List;
import java.util.concurrent.ExecutorService;
import presentacion_utilities.INotificadorEvento;
import presentacion_utilities.NotificadorEvento;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class MediadorManejadores {

    private final INotificadorEvento notificador;
    private final ExecutorService hiloPrincipal;
    private ObserverPartida observerPartida;
    private ObserverOpcionesPartida observerOpciones;
    private ObserverLobby observerLobby;

    public MediadorManejadores() {
        notificador = NotificadorEvento.getInstance();
        hiloPrincipal = Control.getHiloPrincipal();
    }

    public void crearObserverOpcionesPartida() {
        observerOpciones = new  ControlEventosOpcionesPartida();
        notificador.asignarObserverOpcionesPartida(observerOpciones);
    }
    public void crearObserverPartida() {
        observerPartida = new  ControlEventosPartida();
        notificador.asignarObserverPartida(observerPartida);
    }
    public void crearObserverLobby() {
        observerLobby = new ControlEventosLobby();
        notificador.asignarObserverLobby(observerLobby);
    }

    private class ControlEventosOpcionesPartida implements ObserverOpcionesPartida {
        private ManejadorCuenta cuenta;

        public ControlEventosOpcionesPartida() {
            cuenta = Control.obtenerManejadorCuenta();
        }

        @Override
        public void crearPartida() {
            hiloPrincipal.execute(() -> {
                cuenta.crearPartida();
            });
        }

        @Override
        public void buscarPartida(UnirseDTO codigoPartida) {
            hiloPrincipal.execute(() -> {
                cuenta.buscarPartida(codigoPartida);
            });
        }
    }

    private class ControlEventosPartida implements ObserverPartida {

        @Override
        public void avisarJugadaRealizada(JugadaRealizadaDTO jugadaDTO) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void avisarFichaSeleccionada(FichaDTO contexto) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void avisarDarFichas(List<FichaDTO> fichas) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void avisarDarFicha(FichaDTO ficha) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }

    private class ControlEventosLobby implements ObserverLobby {

        @Override
        public void avisarJugadorListo() {
        }

        @Override
        public void avisarJugadorNoListo() {
        }

        @Override
        public void avisarIniciarPartida() {
        }

        @Override
        public void avisarAbandonar() {
        }

    }

}
