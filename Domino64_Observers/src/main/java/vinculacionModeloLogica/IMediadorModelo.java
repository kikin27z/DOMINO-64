package vinculacionModeloLogica;

import eventosCreditos.ObservableCreditos;
import eventosFin.ObservableFinJuego;
import eventosInicio.ObservableInicio;
import eventosLobby.ObservableLobby;
import eventosOpcionesPartida.ObservableOpcionesPartida;
import eventosPartida.ObservablePartida;

/**
 * Interfaz para el mediador que vincula los distintos modelos y permite
 * la interacción entre observadores y eventos.
 */
public interface IMediadorModelo {

    /**
     * Agrega un observador que notificará la lógica del sistema.
     * 
     * @param observador El objeto que implementa NotificarLogica que recibirá las notificaciones.
     */
    public void agregarObserver(NotificarLogica observador);

    /**
     * Quita un observador que ya no notificará la lógica del sistema.
     * 
     * @param observador El objeto que implementa NotificarLogica que dejará de recibir notificaciones.
     */
    public void quitarObserver(NotificarLogica observador);
    
    /**
     * Vincula el modelo de inicio con el mediador.
     * 
     * @param observable El objeto ObservableInicio que será vinculado.
     */
    public void vincularModeloInicio(ObservableInicio observable);

    /**
     * Vincula el modelo de opciones de partida con el mediador.
     * 
     * @param observable El objeto ObservableOpcionesPartida que será vinculado.
     */
    public void vincularModeloOpciones(ObservableOpcionesPartida observable);

    /**
     * Vincula el modelo de lobby con el mediador.
     * 
     * @param observable El objeto ObservableLobby que será vinculado.
     */
    public void vincularModeloLobby(ObservableLobby observable);

    /**
     * Vincula el modelo de partida con el mediador.
     * 
     * @param observable El objeto ObservablePartida que será vinculado.
     */
    public void vincularModeloPartida(ObservablePartida observable);

    /**
     * Vincula el modelo de fin de juego con el mediador.
     * 
     * @param observable El objeto ObservableFinJuego que será vinculado.
     */
    public void vincularModeloFin(ObservableFinJuego observable);

    /**
     * Vincula el modelo de créditos con el mediador.
     * 
     * @param observable El objeto ObservableCreditos que será vinculado.
     */
    public void vincularCreditos(ObservableCreditos observable);
}
