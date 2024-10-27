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
 * La clase InicioView representa la vista inicial del juego de dominó, donde
 * se ofrecen opciones para jugar en modo "Offline" (solo) o "Online". Utiliza
 * JavaFX para la creación de la interfaz gráfica.
 * 
 * Los elementos principales incluyen imágenes decorativas y dos botones que 
 * permiten seleccionar el modo de juego. 
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
    private Button btnOnline;
    private Button btnOffline;
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
        panel.getChildren().addAll(imgDomino, imgTitulo, btnOffline, btnOnline);
    }

    /**
     * Crea y configura los botones de la vista de inicio: "Jugar solo" y "Jugar online".
     */
    private void crearBotones() {
        btnOffline = new Button("Jugar solo");
        btnOffline.setLayoutX(276);
        btnOffline.setLayoutY(493);
        btnOffline.setPrefSize(447, 75);
        btnOffline.setStyle("-fx-padding: 0 0; -fx-background-color: #B2533E;");
        btnOffline.setTextFill(javafx.scene.paint.Color.WHITE);
        btnOffline.setFont(Font.font("Lucida Console", 43));

        btnOnline = new Button("Jugar online");
        btnOnline.setLayoutX(276);
        btnOnline.setLayoutY(591);
        btnOnline.setPrefSize(447, 75);
        btnOnline.setStyle("-fx-padding: 0 0; -fx-background-color: #B2533E;");
        btnOnline.setTextFill(javafx.scene.paint.Color.WHITE);
        btnOnline.setFont(Font.font("Lucida Console", 43));
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
     * Asigna el evento de clic para el botón de modo Offline.
     * 
     * @param e el manejador de eventos que se ejecutará al hacer clic en el botón.
     */
    public void modoOffline(EventHandler<MouseEvent> e){
        btnOffline.setOnMouseClicked(e);
    }

    /**
     * Asigna el evento de clic para el botón de modo Online.
     * 
     * @param e el manejador de eventos que se ejecutará al hacer clic en el botón.
     */
    public void modoOnline(EventHandler<MouseEvent> e){
        btnOnline.setOnMouseClicked(e);
    }
}
