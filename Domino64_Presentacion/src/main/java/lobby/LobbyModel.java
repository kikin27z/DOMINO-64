package lobby;

//import entidades.Partida;

import com.mycompany.patrones.command.Accion;
import com.mycompany.patrones.observer.Observable;
import entidadesDTO.JugadorDTO;
import java.util.ArrayList;
import java.util.List;
import presentacion_utilities.NotificadorPresentacion;

/**
 *
 * @author karim
 */
public class LobbyModel extends Observable<LobbyModel>{
    //private Partida partida;
    private List<Accion> acciones;
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
        notificador.notificar(this);
    }

    public JugadorDTO getJugador(){
        return jugador;
    }
    
    public void setAcciones(List<Accion> acciones){
        this.acciones = acciones;
    }
    
    public List<Accion> getAcciones(){
        return acciones;
    }
}
