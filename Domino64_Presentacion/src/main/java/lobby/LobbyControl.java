package lobby;

import entidadesDTO.JugadorDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import presentacion_utilities.INavegacion;
import presentacion_utilities.Navegacion;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class LobbyControl  {
    private LobbyView view;
    private LobbyModel modelo;

    public LobbyControl() {
    }

    public LobbyControl(LobbyView view, LobbyModel modelo) {
        this.view = view;
        this.modelo = modelo;
        this.view.setOnActionIniciar(getHandlerIniciar());
    }
//    
//    public void iniciarPartida(ActionEvent e){
//        navegacion.cambiarPartida();
//    }
//     
    public void abandonarPartida(ActionEvent e){
         
    }
    
    protected EventHandler<ActionEvent> getHandlerIniciar() {
        EventHandler<ActionEvent> handler = (ActionEvent t) -> {
            Task<Void> longTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    String username = view.getUsername();
                    JugadorDTO jugador = new JugadorDTO(username);
                    modelo.setJugador(jugador);
                    
                    Platform.runLater(() -> {
                        modelo.getAcciones().get(0).ejecutarAccion();
                    });
                    
                    modelo.getAcciones().get(1).ejecutarAccion();
                    return null;   
                }
                
            };
            
            new Thread(longTask).start();
        };
        return handler;
    }
    
     public void abrirAjustes(ActionEvent e){
         
     }
}
