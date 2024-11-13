package listener;

import abstraccion.ICliente;
import domino64.eventos.base.Evento;
import entidades.Cuenta;
import logicaLobby.ObservadorLobbyLocal;
import manejadores.Control;
import manejadores.ManejadorDisplay;
import presentacion_utilities.MediadorModelos;
import utilities.BuilderEventoJugador;
import utilities.DirectorJugador;
import utilities.DirectorSuscripcion;

/**
 *
 * @author karim
 */
public class ControlEventos {
    private ICliente cliente;
    private MediadorModelos mediador;
    private DirectorJugador directorEventos;
    private DirectorSuscripcion directorSuscripciones;
    private ManejadorDisplay manejadorDisplay;
    private int idCliente;
    private ControlManejadorLobby lobby;

    public ControlEventos() {
        mediador = MediadorModelos.getInstance();
        manejadorDisplay = Control.obtenerManejadorDisplay();
    }

    public ControlManejadorLobby getLobby() {
        return lobby;
    }
    
    
    
    
    private class ControlManejadorLobby extends ObservadorLobbyLocal {

        public ControlManejadorLobby() {
            super();
            cliente = Control.obtenerCliente();
            mediador = MediadorModelos.getInstance();
            directorEventos = new DirectorJugador(new BuilderEventoJugador());
            manejadorDisplay = Control.obtenerManejadorDisplay();
        }

        @Override
        public void recibirPartida(Evento evento) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void actualizarJugadoresListos(Evento evento) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void actualizarJugadores(Evento evento) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void actualizarAvatares(Evento evento) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void actualizarUsernames(Evento evento) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void manejarError(Evento evento) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }
}
