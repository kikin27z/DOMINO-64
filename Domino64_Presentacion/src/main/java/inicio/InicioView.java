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
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class InicioView {
    private AnchorPane panel;
    private ImageView imgDomino;
    private ImageView imgTitulo;
    // Botones para seleccionar el modo de juego.
    private Button btnCreditos;
    private Button btnJugar;
    private InicioModel modelo;

    /**
     * Constructor que recibe el modelo asociado a la vista.
     * 
     * @param modelo el modelo del inicio
     */
    public InicioView(InicioModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Inicializa la escena de inicio y la muestra en el escenario (Stage).
     * 
     * @param fondo el escenario principal donde se desplegará la vista.
     * @throws IOException si ocurre un error al cargar los recursos.
     */
    public void iniciarEscena(Stage fondo) throws IOException {
        crearComponentes();
        Scene scene = new Scene(panel); 
        fondo.setScene(scene);
        fondo.show();
    }

    //------------GUI------------
    
    /**
     * Crea y configura los componentes visuales, incluyendo imágenes y botones,
     * añadiéndolos al panel principal.
     */
    private void crearComponentes() {
        panel = new AnchorPane();
        panel.setPrefSize(1000, 700);
        panel.setStyle("-fx-background-color: #186F65;");  // Color de fondo del panel.

        agregarImagenes();
        crearBotones();

        // Se añaden todos los componentes al panel.
        panel.getChildren().addAll(imgDomino, imgTitulo, btnJugar, btnCreditos);
    }

    /**
     * Crea y configura los botones de la vista de inicio: "Jugar solo" y "Jugar online".
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
     * Agrega y configura las imágenes decorativas que se muestran en la pantalla
     * de inicio.
     */
    private void agregarImagenes() {
        // Imagen decorativa del dominó.
        imgDomino = new ImageView(new Image(getClass().getResourceAsStream("/images/dominoImg.png")));
        imgDomino.setFitHeight(500);
        imgDomino.setFitWidth(500);
        imgDomino.setLayoutX(259);
        imgDomino.setLayoutY(72);
        imgDomino.setPickOnBounds(true);
        imgDomino.setPreserveRatio(true);
        imgDomino.setRotate(-7.1);  // Rotación para un efecto visual.

        // Imagen del título.
        imgTitulo = new ImageView(new Image(getClass().getResourceAsStream("/images/logoTitulo.png")));
        imgTitulo.setFitHeight(125);
        imgTitulo.setFitWidth(627);
        imgTitulo.setLayoutX(214);
        imgTitulo.setLayoutY(79);
        imgTitulo.setPickOnBounds(true);
        imgTitulo.setPreserveRatio(true);
    }
    
    //------------EVENTOS------------
    
    /**
     * Asigna el evento de clic para el botón de modo Jugar.
     * 
     * @param e el manejador de eventos que se ejecutará al hacer clic en el botón.
     */
    public void modoJugar(EventHandler<MouseEvent> e){
        btnJugar.setOnMouseClicked(e);
    }

    public void mostrarCreditos(EventHandler<MouseEvent> e){
        btnCreditos.setOnMouseClicked(e);
    }
}
