package presentacion_utilities;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class App extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Navegacion.getInstance().setFondo(stage);
        Navegacion.getInstance().cambiarPartida();
        Image icon = new Image(getClass().getResourceAsStream("/images/logo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Domino 64");
        stage.setResizable(false);
    }
}
