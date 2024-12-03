package vinculacionModeloLogica;

import eventosCreditos.ObservableCreditos;
import eventosFin.ObservableFinJuego;
import eventosInicio.ObservableInicio;
import eventosLobby.ObservableLobby;
import eventosOpcionesPartida.ObservableOpcionesPartida;
import eventosPartida.ObservablePartida;

/**
 * Interfaz que define los métodos para notificar a la lógica del sistema
 * sobre los cambios o eventos que ocurren en los distintos modelos del juego.
 */
public interface NotificarLogica {
    
    /**
     * Vincula el modelo de inicio al sistema de notificación.
     * 
     * @param observable El objeto ObservableInicio que notificará cambios.
     */
    public void vincularModeloInicio(ObservableInicio observable);
    
    /**
     * Vincula el modelo de opciones de partida al sistema de notificación.
     * 
     * @param observable El objeto ObservableOpcionesPartida que notificará cambios.
     */
    public void vincularModeloOpciones(ObservableOpcionesPartida observable);

    /**
     * Vincula el modelo de lobby al sistema de notificación.
     * 
     * @param observable El objeto ObservableLobby que notificará cambios.
     */
    public void vincularModeloLobby(ObservableLobby observable);

    /**
     * Vincula el modelo de partida al sistema de notificación.
     * 
     * @param observable El objeto ObservablePartida que notificará cambios.
     */
    public void vincularModeloPartida(ObservablePartida observable);

    /**
     * Vincula el modelo de fin de juego al sistema de notificación.
     * 
     * @param observable El objeto ObservableFinJuego que notificará cambios.
     */
    public void vincularModeloFin(ObservableFinJuego observable);

    /**
     * Vincula el modelo de créditos al sistema de notificación.
     * 
     * @param observable El objeto ObservableCreditos que notificará cambios.
     */
    public void vincularCreditos(ObservableCreditos observable);
}
