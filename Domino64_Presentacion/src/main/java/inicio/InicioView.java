package inicio;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Vista de la pantalla de inicio que contiene los elementos gráficos de la interfaz
 * para que el usuario pueda interactuar, como los botones de jugar y ver créditos.
 * 
 * @autor Luisa Fernanda Morales Espinoza - 00000233450
 * @autor José Karim Franco Valencia - 00000245138
 */
public class InicioView {
    private AnchorPane panel;
    private ImageView imgDomino;
    private ImageView imgTitulo;
    private Button btnCreditos;
    private Button btnJugar;
    private final InicioModel modelo;

    /**
     * Constructor que recibe el modelo asociado a la vista.
     * 
     * @param modelo el modelo del inicio que maneja la lógica de la vista.
     */
    public InicioView(InicioModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Inicializa la escena de inicio y la muestra en el escenario (Stage).
     * Crea los componentes visuales y los añade al escenario principal.
     * 
     * @param fondo el escenario principal donde se desplegará la vista.
     * @throws IOException si ocurre un error al cargar los recursos, como imágenes.
     */
    public void iniciarEscena(Stage fondo) throws IOException {
        crearComponentes();  // Método que construye los componentes gráficos.
        Scene scene = new Scene(panel);  // Crear la escena con el panel.
        fondo.setScene(scene);  // Asignar la escena al escenario.
        fondo.show();  // Mostrar el escenario con la nueva escena.
    }

    //------------GUI (Interfaz gráfica)------------

    /**
     * Crea los componentes gráficos de la vista, incluyendo el panel, las imágenes y los botones.
     */
    private void crearComponentes() {
        panel = new AnchorPane();
        panel.setPrefSize(1000, 700);
        panel.setStyle("-fx-background-color: #186F65;");  // Color de fondo del panel.

        agregarImagenes();  // Método que agrega las imágenes decorativas.
        crearBotones();  // Método que crea los botones de la interfaz.

        // Añadir todos los componentes al panel.
        panel.getChildren().addAll(imgDomino, imgTitulo, btnJugar, btnCreditos);
    }

    /**
     * Crea los botones de la interfaz, configurando sus características visuales.
     */
    private void crearBotones() {
        btnJugar = new Button("Jugar partida");
        btnJugar.setLayoutX(276);
        btnJugar.setLayoutY(493);
        btnJugar.setPrefSize(447, 75);
        btnJugar.setStyle("-fx-padding: 0 0; -fx-background-color: #B2533E;");
        btnJugar.setTextFill(javafx.scene.paint.Color.WHITE);
        btnJugar.setFont(Font.font("Lucida Console", 43));

        btnCreditos = new Button("Mostrar créditos");
        btnCreditos.setLayoutX(276);
        btnCreditos.setLayoutY(591);
        btnCreditos.setPrefSize(447, 75);
        btnCreditos.setStyle("-fx-padding: 0 0; -fx-background-color: #B2533E;");
        btnCreditos.setTextFill(javafx.scene.paint.Color.WHITE);
        btnCreditos.setFont(Font.font("Lucida Console", 43));
    }

    /**
     * Agrega las imágenes decorativas al panel, incluyendo el logo y la imagen de fondo.
     */
    private void agregarImagenes() {
        imgDomino = new ImageView(new Image(getClass().getResourceAsStream("/images/dominoImg.png")));
        imgDomino.setFitHeight(500);
        imgDomino.setFitWidth(500);
        imgDomino.setLayoutX(259);
        imgDomino.setLayoutY(72);
        imgDomino.setPickOnBounds(true);
        imgDomino.setPreserveRatio(true);
        imgDomino.setRotate(-7.1);  // Rotación para un efecto visual.

        imgTitulo = new ImageView(new Image(getClass().getResourceAsStream("/images/logoTitulo.png")));
        imgTitulo.setFitHeight(125);
        imgTitulo.setFitWidth(627);
        imgTitulo.setLayoutX(214);
        imgTitulo.setLayoutY(79);
        imgTitulo.setPickOnBounds(true);
        imgTitulo.setPreserveRatio(true);
    }

    //------------EVENTOS (Manejo de Interacciones)------------

    /**
     * Configura el evento para el botón "Jugar partida".
     * 
     * @param e el manejador de eventos que se asignará al botón.
     */
    public void modoJugar(EventHandler<MouseEvent> e) {
        btnJugar.setOnMouseClicked(e);
    }

    /**
     * Configura el evento para el botón "Mostrar créditos".
     * 
     * @param e el manejador de eventos que se asignará al botón.
     */
    public void mostrarCreditos(EventHandler<MouseEvent> e) {
        btnCreditos.setOnMouseClicked(e);
    }
}
