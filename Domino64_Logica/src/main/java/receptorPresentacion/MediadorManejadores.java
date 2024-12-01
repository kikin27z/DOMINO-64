package receptorPresentacion;

import eventosCreditos.ObservableCreditos;
import eventosCreditos.ObserverCreditos;
import eventosFin.ObservableFinJuego;
import eventosFin.ObserverFinJuego;
import eventosInicio.ObservableInicio;
import eventosInicio.ObserverInicio;
import eventosLobby.ObservableLobby;
import eventosLobby.ObserverLobby;
import eventosOpcionesPartida.ObservableOpcionesPartida;
import eventosOpcionesPartida.ObserverOpcionesPartida;
import eventosPartida.ObservablePartida;
import eventosPartida.ObserverPartida;
import java.util.concurrent.ExecutorService;
import manejadores.Control;
import manejadores.ManejadorDisplay;
import presentacion_utilities.MediadorModelos;
import vinculacionModeloLogica.IMediadorModelo;
import vinculacionModeloLogica.NotificarLogica;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class MediadorManejadores implements NotificarLogica {
    private final ManejadorDisplay display;
    private final ExecutorService hiloPrincipal;

    public MediadorManejadores() {
        this.display = Control.obtenerManejadorDisplay();
        this.hiloPrincipal = Control.getHiloPrincipal();
        IMediadorModelo mediador = MediadorModelos.getInstance();
        mediador.agregarObserver(this);
    }

    @Override
    public void vincularModeloInicio(ObservableInicio observable) {
        ObserverInicio controlInicio = new ControlEventosInicio(hiloPrincipal, display);
        observable.agregarObserver(controlInicio);
    }

    @Override
    public void vincularModeloOpciones(ObservableOpcionesPartida observable) {
        ObserverOpcionesPartida controlInicio = new ControlEventosOpcionesPartida(hiloPrincipal, display);
        observable.agregarObserver(controlInicio);
    }

    @Override
    public void vincularModeloLobby(ObservableLobby observable) {
        ObserverLobby controlLobby = new ControlEventosLobby(hiloPrincipal, display);
        observable.agregarObserver(controlLobby);

    }

    @Override
    public void vincularModeloPartida(ObservablePartida observable) {
        ObserverPartida controlPartida = new ControlEventosPartida(hiloPrincipal, display);
        observable.agregarObserver(controlPartida);
    }

    @Override
    public void vincularModeloFin(ObservableFinJuego observable) {
        ObserverFinJuego controlFin = new ControlEventosFinJuego(hiloPrincipal, display);
        observable.agregarObserver(controlFin);
    }

    @Override
    public void vincularCreditos(ObservableCreditos observable) {
        ObserverCreditos controlCreditos = new ControlEventosCreditos(hiloPrincipal, display);
        observable.agregarObserver(controlCreditos);
    }
}
