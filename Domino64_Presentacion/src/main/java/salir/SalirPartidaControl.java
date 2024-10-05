package salir;

import javafx.event.ActionEvent;
import utilities.INavegacion;
import utilities.Navegacion;

/**
 * FXML Controller class
 *
 * @author karim
 */
public class SalirPartidaControl{

    private INavegacion navegacion;

    public SalirPartidaControl() {
        this.navegacion = Navegacion.getInstance();
    }

    public void diHola(ActionEvent e){
        System.out.println("Hola tu");
    }
    
}
