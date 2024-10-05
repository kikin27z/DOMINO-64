package partida;

import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Clase que representa la vista de la partida en la aplicación.
 * Esta clase se encarga de crear la interfaz gráfica de usuario y
 * manejar la lógica de visualización relacionada con la partida.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaView {

    private AnchorPane panelExterior; // Panel exterior que contiene todos los elementos visuales
    private AnchorPane panelInterior;  // Panel interno que se desplaza dentro del ScrollPane
    private AnchorPane panelJugador1;
    private ScrollPane scrollPanel;     // Panel con capacidad de desplazamiento

    /**
     * Inicializa la escena de la partida y muestra la ventana.
     * 
     * @param fondo La ventana principal (Stage) donde se mostrará la escena.
     * @throws IOException Si ocurre un error al cargar los recursos necesarios.
     */
    public void iniciarEscena(Stage fondo) throws IOException {
        crearComponentes(); // Aseguramos que los componentes sean creados antes de iniciar la escena
        Scene scene = new Scene(panelExterior); // Usamos panelExterior como la raíz
        fondo.setScene(scene);
        fondo.show();

        // Ajustamos la posición inicial del ScrollPane
        Platform.runLater(() -> {
            scrollPanel.setHvalue(0.5); // Centrar horizontalmente
            scrollPanel.setVvalue(0.5); // Centrar verticalmente
        });
    }

    /**
     * Crea y configura los componentes de la interfaz de usuario.
     * Esto incluye el AnchorPane principal, el ScrollPane y los elementos internos.
     */
    private void crearComponentes() {
        // Creando el AnchorPane exterior
        panelExterior = new AnchorPane();
        panelExterior.setPrefSize(1000, 700);
        panelExterior.setStyle("-fx-background-color: #186F65;");

        // Creando el ScrollPane
        scrollPanel = new ScrollPane();
        scrollPanel.setLayoutX(85);
        scrollPanel.setLayoutY(75);
        scrollPanel.setPrefSize(830, 550);
        scrollPanel.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        scrollPanel.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Mostrar barra horizontal
        scrollPanel.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Mostrar barra vertical
        scrollPanel.setHvalue(0.5); // Valor horizontal inicial
        scrollPanel.setVvalue(0.5); // Valor vertical inicial

        // Creando el AnchorPane interno dentro del ScrollPane
        panelInterior = new AnchorPane();
        panelInterior.setMinSize(1200, 900); // Establecemos el tamaño mínimo
        panelInterior.setStyle("-fx-background-color: #B5CB99;");

        // Creando el ImageView para la imagen dentro del ScrollPane
        ImageView imageView = new ImageView();
        imageView.setFitWidth(106.0);
        imageView.setLayoutX(547.0);
        imageView.setLayoutY(419.0);
        imageView.setPickOnBounds(true); // Permite que la imagen sea seleccionable
        imageView.setPreserveRatio(true); // Mantiene la proporción de la imagen
        imageView.setImage(new Image(getClass().getResourceAsStream("/dominos/0-2.png"))); // Ruta de la imagen

        // Añadir el ImageView al panel interior
        panelInterior.getChildren().add(imageView);

        // Asignando el contenido del ScrollPane
        scrollPanel.setContent(panelInterior);

        // Añadiendo el ScrollPane al AnchorPane principal
        panelExterior.getChildren().add(scrollPanel);

        // Creando el segundo AnchorPane para la parte inferior
        panelJugador1 = new AnchorPane();
        panelJugador1.setLayoutX(164.0);
        panelJugador1.setLayoutY(598.0);
        panelJugador1.setPrefSize(630.0, 92.0);
        panelJugador1.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-color: #000000; -fx-border-radius: 20;");

        // Crear el ImageView para la segunda imagen
        ImageView imageViewBottom = new ImageView();
        imageViewBottom.setFitHeight(55.0);
        imageViewBottom.setFitWidth(90.0);
        imageViewBottom.setLayoutX(81.0);
        imageViewBottom.setLayoutY(14.0);
        imageViewBottom.setPickOnBounds(true); // Permite que la imagen sea seleccionable
        imageViewBottom.setPreserveRatio(true); // Mantiene la proporción de la imagen
        imageViewBottom.setRotate(90.0); // Rota la imagen 90 grados
        imageViewBottom.setImage(new Image(getClass().getResourceAsStream("/dominos/0-5.png"))); // Ruta de la imagen

        // Añadir el ImageView al segundo AnchorPane
        panelJugador1.getChildren().add(imageViewBottom);
        
        // Añadir el segundo AnchorPane al AnchorPane principal
        panelExterior.getChildren().add(panelJugador1);
    }
}
