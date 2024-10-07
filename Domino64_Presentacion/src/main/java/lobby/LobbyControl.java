package lobby;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
    private INavegacion navegacion;

    public LobbyControl() {
        navegacion = Navegacion.getInstance();
    }

    
    
    public LobbyControl(LobbyView view, LobbyModel modelo) {
        this.view = view;
        this.modelo = modelo;
        navegacion = Navegacion.getInstance();
    }
    
    
     public void iniciarPartida(ActionEvent e){
        navegacion.cambiarPartida();
     }
     
     public void abandonarPartida(ActionEvent e){
         
     }
    
     public void abrirAjustes(ActionEvent e){
         
     }
}
