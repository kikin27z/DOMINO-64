package creditos;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author karim
 */
public class CreditosView {

    private Button btnVolver;
    private AnchorPane panel;
    private final CreditosModel modelo;

    /**
     * Constructor que recibe el modelo asociado a la vista.
     *
     * @param modelo el modelo de creditos que maneja la lógica de la vista.
     */
    public CreditosView(CreditosModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Inicializa la escena de inicio y la muestra en el escenario (Stage). Crea
     * los componentes visuales y los añade al escenario principal.
     *
     * @param fondo el escenario principal donde se desplegará la vista.
     * @throws IOException si ocurre un error al cargar los recursos, como
     * imágenes.
     */
    public void iniciarEscena(Stage fondo) throws IOException {
        crearComponentes();  // Método que construye los componentes gráficos.
        Scene scene = new Scene(panel);  // Crear la escena con el panel.
        fondo.setScene(scene);  // Asignar la escena al escenario.
        fondo.setTitle("Créditos");
        fondo.show();  // Mostrar el escenario con la nueva escena.
        
    }

    private void crearComponentes() {
        panel = new AnchorPane();
        panel.setStyle("-fx-background-color: #186F65;");
        panel.setPrefSize(1000, 700);

        // Button
        btnVolver = new Button("Volver inicio");
        btnVolver.setId("btnOnline");
        btnVolver.setLayoutX(303);
        btnVolver.setLayoutY(579);
        btnVolver.setPrefSize(447, 75);
        btnVolver.setStyle("-fx-padding: 0 0; -fx-background-color: #B2533E;");
        btnVolver.setTextFill(javafx.scene.paint.Color.WHITE);
        btnVolver.setFont(Font.font("Lucida Console", 43));

        // AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setLayoutX(149);
        anchorPane.setLayoutY(197);
        anchorPane.setPrefSize(702, 306);

        // Label
        Label label = new Label("Créditos");
        label.setLayoutX(415);
        label.setLayoutY(84);
        label.setFont(Font.font(46));

        panel.getChildren().addAll(btnVolver, anchorPane, label);

        
    }

    
    public void irInicio(EventHandler<MouseEvent> e) {
        btnVolver.setOnMouseClicked(e);
    }
}
