package vinculacionModeloLogica;

import eventosCreditos.ObservableCreditos;
import eventosFin.ObservableFinJuego;
import eventosInicio.ObservableInicio;
import eventosLobby.ObservableLobby;
import eventosOpcionesPartida.ObservableOpcionesPartida;
import eventosPartida.ObservablePartida;

/**
 *
 * @author karim
 */
public interface IMediadorModelo {
    public void agregarObserver(NotificarLogica observador);

    public void quitarObserver(NotificarLogica observador);
    
    
    public void vincularModeloInicio(ObservableInicio observable);
    public void vincularModeloOpciones(ObservableOpcionesPartida observable);
    public void vincularModeloLobby(ObservableLobby observable);
    public void vincularModeloPartida(ObservablePartida observable);
    public void vincularModeloFin(ObservableFinJuego observable);
    public void vincularCreditos(ObservableCreditos observable);
}
