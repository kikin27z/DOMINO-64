package salir;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author karim
 */
public class SalirPartidaView {
    public void iniciarEscena(Stage fondo) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/salir.fxml"));
        Scene scene = new Scene(root);
        fondo.setScene(scene);
        fondo.show();
    }
}
