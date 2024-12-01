package presentacion_utilities;

import eventosCreditos.ObservableCreditos;
import eventosFin.ObservableFinJuego;
import eventosInicio.ObservableInicio;
import eventosLobby.ObservableLobby;
import eventosOpcionesPartida.ObservableOpcionesPartida;
import eventosPartida.ObservablePartida;
import vinculacionModeloLogica.IMediadorModelo;
import vinculacionModeloLogica.NotificarLogica;


/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class MediadorModelos implements IMediadorModelo {
    private NotificarLogica logica;
    private static MediadorModelos mediador;

    private MediadorModelos() {
    }
    
    public static MediadorModelos getInstance(){
        if (mediador == null) {
            mediador = new MediadorModelos(); // Crea la instancia si no existe
        }
        return mediador;
    }

    @Override
    public void agregarObserver(NotificarLogica observador) {
        logica = observador;
    }

    @Override
    public void quitarObserver(NotificarLogica observador) {
        logica = null;
    }

    @Override
    public void vincularModeloInicio(ObservableInicio observable) {
        logica.vincularModeloInicio(observable);
    }

    @Override
    public void vincularModeloOpciones(ObservableOpcionesPartida observable) {
        logica.vincularModeloOpciones(observable);
    }

    @Override
    public void vincularModeloLobby(ObservableLobby observable) {
        logica.vincularModeloLobby(observable);
    }

    @Override
    public void vincularModeloPartida(ObservablePartida observable) {
        logica.vincularModeloPartida(observable);
    }

    @Override
    public void vincularModeloFin(ObservableFinJuego observable) {
        logica.vincularModeloFin(observable);
    }

    @Override
    public void vincularCreditos(ObservableCreditos observable) {
        logica.vincularCreditos(observable);
    }
}
