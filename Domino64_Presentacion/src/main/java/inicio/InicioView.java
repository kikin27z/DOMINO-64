package inicio;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class InicioView {
    @FXML
    private Parent root;
    private Button btnOnline;
    
    
    public void iniciarEscena(Stage fondo) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/fxml/inicio.fxml"));
        Scene scene = new Scene(root);
        
//        btnOnline = (Button) root.lookup("#btnOnline");
//        btnOnline.setStyle("-fx-background-color: #0000FF;");

        fondo.setScene(scene);
        fondo.show();
    }
}
