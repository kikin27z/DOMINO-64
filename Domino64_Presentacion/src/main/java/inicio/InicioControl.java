package inicio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import utilities.INavegacion;
import utilities.Navegacion;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class InicioControl {

    private INavegacion navegacion;

    public InicioControl() {
        this.navegacion = Navegacion.getInstance();
    }

    public void irPartida(ActionEvent e) {
        navegacion.cambiarPartida();
    }
    
  

}
