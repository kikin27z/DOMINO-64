package lobby;

import entidadesDTO.JugadorDTO;
import java.util.ArrayList;
import java.util.List;
import observer.Observable;
import presentacion_utilities.NotificadorPresentacion;

/**
 *
 * @author karim
 */
public class LobbyModel extends Observable{
    //private Partida partida;
    private JugadorDTO jugador;
    NotificadorPresentacion notificador;
    
    public LobbyModel(NotificadorPresentacion notificador) {
        this.notificador = notificador;
    }
   
    public void cargarDatos(){
        //partida = new Partida("", null, 7, 2);
    }
    
    public void setJugador(JugadorDTO jugador){
        this.jugador = jugador;
        
        //notificador.notificarEvento(this);
    }

    public JugadorDTO getJugador(){
        return jugador;
    }
    
}
