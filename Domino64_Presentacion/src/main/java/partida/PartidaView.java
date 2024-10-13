package partida;

import entidades.Ficha;
import java.io.IOException;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presentacion_utilities.Observable;
import presentacion_utilities.Observador;

/**
 * Clase que representa la vista de la partida en la aplicación. Esta clase se
 * encarga de crear la interfaz gráfica de usuario y manejar la lógica de
 * visualización relacionada con la partida.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaView  {

    private PartidaModel modelo;
    private AnchorPane panelExterior; // Panel exterior que contiene todos los elementos visuales
    private AnchorPane panelInterior;  // Panel interno que se desplaza dentro del ScrollPane
    private HBox panelJugador1;
    private ScrollPane scrollPanel;     // Panel con capacidad de desplazamiento
    private Button btnEjemplo;
    private List<Canvas> mazo;

    public PartidaView(PartidaModel modelo) {
        this.modelo = modelo;
    }

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
     * Crea y configura los componentes de la interfaz de usuario. Esto incluye
     * el AnchorPane principal, el ScrollPane y los elementos internos.
     */
    private void crearComponentes() {
        // Creando el AnchorPane exterior
        panelExterior = new AnchorPane();
        panelExterior.setPrefSize(modelo.getExternalPanelWidth(), modelo.getExternalPanelHeight());
        panelExterior.setStyle(modelo.getExternalPanelStyle());

        // Creando el ScrollPane
        scrollPanel = new ScrollPane();
        scrollPanel.setLayoutX(modelo.getScrollPanelLayoutX());
        scrollPanel.setLayoutY(modelo.getScrollPanelLayoutY());
        scrollPanel.setPrefSize(modelo.getScrollPanelWidth(), modelo.getScrollPanelHeight());
        scrollPanel.setStyle(modelo.getScrollPanelStyle());
        scrollPanel.setHbarPolicy(modelo.getScrollPanelHbarPolicy()); // Mostrar barra horizontal
        scrollPanel.setVbarPolicy(modelo.getScrollPanelVbarPolicy()); // Mostrar barra vertical

        // Creando el AnchorPane interno dentro del ScrollPane
        panelInterior = new AnchorPane();
        panelInterior.setMinSize(modelo.getInternalPanelWidth(), modelo.getInternalPanelHeight()); // Establecemos el tamaño mínimo
        panelInterior.setStyle(modelo.getInternalPanelStyle());

        // Creando el ImageView para la imagen dentro del ScrollPane
        ImageView imageView = new ImageView();
        imageView.setFitWidth(modelo.getImageViewWidth());
        imageView.setLayoutX(modelo.getImageViewLayoutX());
        imageView.setLayoutY(modelo.getImageViewLayoutY());
        imageView.setPickOnBounds(modelo.isImgViewPickedOnBounds()); // Permite que la imagen sea seleccionable
        imageView.setPreserveRatio(modelo.isImgViewRatioPreserved()); // Mantiene la proporción de la imagen
        imageView.setImage(new Image(getClass().getResourceAsStream(modelo.getImageViewResourceName()))); // Ruta de la imagen

        btnEjemplo = new Button();
        btnEjemplo.setText(modelo.getButtonText());
        btnEjemplo.setLayoutX(modelo.getButtonLayoutX());
        btnEjemplo.setLayoutY(modelo.getButtonLayoutY());

        // Añadir el ImageView al panel interior
        // Asignando el contenido del ScrollPane
        scrollPanel.setContent(panelInterior);

        // Añadiendo el ScrollPane al AnchorPane principal
        panelExterior.getChildren().add(scrollPanel);
        panelExterior.getChildren().add(btnEjemplo);

        // Creando el segundo AnchorPane para la parte inferior
//        panelJugador1 = new HBox();
//        panelJugador1.setSpacing(20);
//        panelJugador1.setLayoutX(modelo.getPlayer1PanelLayoutX());
//        panelJugador1.setLayoutY(modelo.getPlayer1PanelLayoutY());
//        panelJugador1.setPrefSize(modelo.getPlayer1PanelWidth(), modelo.getPlayer1PanelHeight());
//        panelJugador1.setMinSize(modelo.getPlayer1PanelWidth(), modelo.getPlayer1PanelHeight());
//        panelJugador1.setMaxSize(modelo.getPlayer1PanelWidth(), modelo.getPlayer1PanelHeight());
//        panelJugador1.setPadding(new Insets(-12.0, 0, 0, 20.0));
//        panelJugador1.setStyle(modelo.getPlayer1PanelStyle());
//        ImageView catImageView = new ImageView(new Image(getClass().getResourceAsStream("/avatar/gato.png")));
//        catImageView.setFitHeight(150);
//        catImageView.setFitWidth(150);
//        catImageView.setLayoutX(835);
//        catImageView.setLayoutY(536);
//        catImageView.setPickOnBounds(true);
//        catImageView.setPreserveRatio(true);
//
//        panelExterior.getChildren().add(catImageView);

        insertarMesaAba(panelExterior);
        insertarMesaArr(panelExterior);
    }

    public Canvas crearDomino(int izquierda, int derecha, EventHandler<MouseEvent> evento) {
        Canvas ficha = DominoDraw.dibujarFicha(izquierda, derecha, DominoDraw.Orientation.VERTICAL);
        ficha.setOnMouseClicked(evento);
        return ficha;
    }

    public void agregarDominoMazo(Canvas ficha) {
        ficha.setVisible(true);
        panelJugador1.getChildren().add(ficha);
    }

    public void agregarDominoTablero(Canvas ficha) {
        panelInterior.getChildren().add(ficha);
    }

    public void quitarDominoMazo(int izquierda, int derecha) {
        Canvas ficha = DominoDraw.dibujarFicha(izquierda, derecha, DominoDraw.Orientation.VERTICAL);
        panelJugador1.getChildren().add(ficha);
    }

    public Map<Canvas, Ficha> addTile(EventHandler<MouseEvent> evento) {
        // Crear el ImageView para la segunda imagen
        for (Ficha ficha : modelo.getFichasDelJugador()) {
            Canvas fichaDibujo = crearDomino(ficha.getIzquierda(), ficha.getDerecha(), evento);
            modelo.getMapeoFichas().put(fichaDibujo, ficha);

            agregarDominoMazo(fichaDibujo);
        }
        return modelo.getMapeoFichas();
    }
    
    private void insertarMesaAba(AnchorPane panelExterior){
        panelJugador1 = new HBox();
        panelJugador1.setSpacing(20);
        panelJugador1.setLayoutX(modelo.getPlayer1PanelLayoutX());
        panelJugador1.setLayoutY(modelo.getPlayer1PanelLayoutY());
        panelJugador1.setPrefSize(modelo.getPlayer1PanelWidth(), modelo.getPlayer1PanelHeight());
        panelJugador1.setMinSize(modelo.getPlayer1PanelWidth(), modelo.getPlayer1PanelHeight());
        panelJugador1.setMaxSize(modelo.getPlayer1PanelWidth(), modelo.getPlayer1PanelHeight());
        panelJugador1.setPadding(new Insets(-12.0, 0, 0, 20.0));
        panelJugador1.setStyle(modelo.getPlayer1PanelStyle());
        ImageView catImageView = new ImageView(new Image(getClass().getResourceAsStream("/avatar/gato.png")));
        catImageView.setFitHeight(150);
        catImageView.setFitWidth(150);
        catImageView.setLayoutX(835);
        catImageView.setLayoutY(536);
        catImageView.setPickOnBounds(true);
        catImageView.setPreserveRatio(true);

        panelExterior.getChildren().add(catImageView);
        panelExterior.getChildren().add(panelJugador1);
    }
    
    private void insertarMesaIzq(AnchorPane panelExterior) {
        AnchorPane mazoIzq;

        // Left Panel (Player 2)
        mazoIzq = new AnchorPane();
        mazoIzq.setId("jugador3");
        mazoIzq.setLayoutX(10);
        mazoIzq.setLayoutY(210);
        mazoIzq.setPrefSize(98, 234);
        mazoIzq.setMinSize(98, 234);
        mazoIzq.setMaxSize(98, 234);
        mazoIzq.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-color: #000000; -fx-border-radius: 20;");

        ImageView leftBirdImageView = new ImageView(new Image(getClass().getResourceAsStream("/avatar/ave.png")));
        leftBirdImageView.setId("jugador3");
        leftBirdImageView.setFitHeight(114);
        leftBirdImageView.setFitWidth(114);
        leftBirdImageView.setLayoutX(-6);
        leftBirdImageView.setLayoutY(-34);
        leftBirdImageView.setPickOnBounds(true);
        leftBirdImageView.setPreserveRatio(true);
        mazoIzq.getChildren().add(leftBirdImageView);

        ImageView leftDeckImageView = new ImageView(new Image(getClass().getResourceAsStream("/images/mazoJugador.png")));
        leftDeckImageView.setFitHeight(100);
        leftDeckImageView.setFitWidth(58);
        leftDeckImageView.setLayoutX(31);
        leftDeckImageView.setLayoutY(86);
        leftDeckImageView.setPickOnBounds(true);
        leftDeckImageView.setPreserveRatio(true);
        leftDeckImageView.setRotate(90);
        mazoIzq.getChildren().add(leftDeckImageView);

        Label leftPlayerCountLabel = new Label("6");
        leftPlayerCountLabel.setAlignment(Pos.CENTER);
        leftPlayerCountLabel.setLayoutX(12);
        leftPlayerCountLabel.setLayoutY(159);
        leftPlayerCountLabel.setPrefSize(60, 60);
        leftPlayerCountLabel.setMinSize(60, 60);
        leftPlayerCountLabel.setMaxSize(60, 60);
        leftPlayerCountLabel.setTextFill(Color.WHITE);
        leftPlayerCountLabel.setFont(new Font("Verdana Bold", 40));
        mazoIzq.getChildren().add(leftPlayerCountLabel);

        panelExterior.getChildren().add(mazoIzq);
    }

    private void insertarMesaDer(AnchorPane panelExterior) {
        AnchorPane mazoDer = new AnchorPane();
        mazoDer.setId("jugador4");
        mazoDer.setLayoutX(892);
        mazoDer.setLayoutY(210);
        mazoDer.setPrefSize(98, 234);
        mazoDer.setMinSize(98, 234);
        mazoDer.setMaxSize(98, 234);
        mazoDer.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-color: #000000; -fx-border-radius: 20;");

        Label rightPlayerCountLabel = new Label("6");
        rightPlayerCountLabel.setAlignment(Pos.CENTER);
        rightPlayerCountLabel.setLayoutX(21);
        rightPlayerCountLabel.setLayoutY(162);
        rightPlayerCountLabel.setPrefSize(60, 60);
        rightPlayerCountLabel.setMinSize(60, 60);
        rightPlayerCountLabel.setMaxSize(60, 60);
        rightPlayerCountLabel.setTextFill(Color.WHITE);
        rightPlayerCountLabel.setFont(new Font("Verdana Bold", 40));
        mazoDer.getChildren().add(rightPlayerCountLabel);

        ImageView rightDeckImageView = new ImageView(new Image(getClass().getResourceAsStream("/images/mazoJugador.png")));
        rightDeckImageView.setFitHeight(100);
        rightDeckImageView.setFitWidth(58);
        rightDeckImageView.setLayoutX(10);
        rightDeckImageView.setLayoutY(86);
        rightDeckImageView.setPickOnBounds(true);
        rightDeckImageView.setPreserveRatio(true);
        rightDeckImageView.setRotate(-90);
        mazoDer.getChildren().add(rightDeckImageView);

        ImageView rightBirdImageView = new ImageView(new Image(getClass().getResourceAsStream("/avatar/ave.png")));
        rightBirdImageView.setFitHeight(114);
        rightBirdImageView.setFitWidth(114);
        rightBirdImageView.setLayoutX(-13);
        rightBirdImageView.setLayoutY(-34);
        rightBirdImageView.setPickOnBounds(true);
        rightBirdImageView.setPreserveRatio(true);
        mazoDer.getChildren().add(rightBirdImageView);

        panelExterior.getChildren().add(mazoDer);
    }

    private void insertarMesaArr(AnchorPane panelExterior) {
        AnchorPane mazoArr = new AnchorPane();
        mazoArr.setId("jugador3");
        mazoArr.setLayoutX(366);
        mazoArr.setLayoutY(10);
        mazoArr.setPrefSize(268, 98);
        mazoArr.setMinSize(268, 98);
        mazoArr.setMaxSize(268, 98);
        mazoArr.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #000000;");

        ImageView deckImageView = new ImageView(new Image(getClass().getResourceAsStream("/images/mazoJugador.png")));
        deckImageView.setFitHeight(100);
        deckImageView.setFitWidth(58);
        deckImageView.setLayoutX(93);
        deckImageView.setLayoutY(5);
        deckImageView.setPickOnBounds(true);
        deckImageView.setPreserveRatio(true);
        deckImageView.setRotate(180);
        mazoArr.getChildren().add(deckImageView);

        ImageView birdImageView = new ImageView(new Image(getClass().getResourceAsStream("/avatar/ave.png")));
        birdImageView.setFitHeight(114);
        birdImageView.setFitWidth(114);
        birdImageView.setLayoutX(-64);
        birdImageView.setLayoutY(-5);
        birdImageView.setPickOnBounds(true);
        birdImageView.setPreserveRatio(true);
        mazoArr.getChildren().add(birdImageView);

        Label playerCountLabel = new Label("6");
        playerCountLabel.setAlignment(Pos.CENTER);
        playerCountLabel.setLayoutX(167);
        playerCountLabel.setLayoutY(27);
        playerCountLabel.setPrefSize(60, 60);
        playerCountLabel.setMinSize(60, 60);
        playerCountLabel.setMaxSize(60, 60);
        playerCountLabel.setTextFill(Color.WHITE);
        playerCountLabel.setFont(new Font("Verdana Bold", 40));
        mazoArr.getChildren().add(playerCountLabel);

        panelExterior.getChildren().add(mazoArr);
    }
}
