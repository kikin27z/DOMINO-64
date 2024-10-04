package lobby;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class LobbyView {
    public void iniciarEscena(Stage fondo) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/lobby.fxml"));
        Scene scene = new Scene(root);
        fondo.setScene(scene);
        fondo.show();
    }
}
