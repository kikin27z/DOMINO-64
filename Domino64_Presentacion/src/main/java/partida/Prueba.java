package partida;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class Prueba {

    private Parent root;

    public void iniciarEscena(Stage fondo) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/partida.fxml"));
        Scene scene = new Scene(root);


        
   
        fondo.setScene(scene);
        fondo.show();
    }
}
