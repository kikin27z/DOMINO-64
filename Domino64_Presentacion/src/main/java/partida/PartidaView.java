package partida;

//import entidades.Ficha;
import entidadesDTO.FichaDTO;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
import presentacion_observers.DominoMazo;
import presentacion_observers.DominoTablero;
import presentacion_observers.ObservadorPartida;

/**
 * Clase que representa la vista de la partida en la aplicación. Esta clase se
 * encarga de crear la interfaz gráfica de usuario y manejar la lógica de
 * visualización relacionada con la partida.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaView implements ObservadorPartida {

    private PartidaModel modelo;
    private AnchorPane panelExterior; // Panel exterior que contiene todos los elementos visuales
    private AnchorPane panelInterior;  // Panel interno que se desplaza dentro del ScrollPane
    private HBox panelJugadorActual;
    private ScrollPane scrollPanel;     // Panel con capacidad de desplazamiento
    private Button btnEjemplo;
    private EventHandler<MouseEvent> eventoFicha;
    public Deque<Canvas> trenFichasDibujos;

    public PartidaView(PartidaModel modelo) {
        this.modelo = modelo;
        trenFichasDibujos = new ArrayDeque<>();
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
        modelo.addObserver(this);
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

        insertarMesaAba(panelExterior);
        insertarMesaArr(panelExterior);
    }

    public void agregarDominoMazo(Canvas ficha) {
        ficha.setVisible(true);
        panelJugadorActual.getChildren().add(ficha);
    }

    public void agregarDominoTablero(Canvas ficha) {
        panelInterior.getChildren().add(ficha);
    }

    private void insertarMesaAba(AnchorPane panelExterior) {
        panelJugadorActual = new HBox();
        panelJugadorActual.setSpacing(10);
        panelJugadorActual.setLayoutX(modelo.getPlayer1PanelLayoutX());
        panelJugadorActual.setLayoutY(modelo.getPlayer1PanelLayoutY());
        panelJugadorActual.setPrefSize(modelo.getPlayer1PanelWidth(), modelo.getPlayer1PanelHeight());
        panelJugadorActual.setMinSize(modelo.getPlayer1PanelWidth(), modelo.getPlayer1PanelHeight());
        panelJugadorActual.setMaxSize(modelo.getPlayer1PanelWidth(), modelo.getPlayer1PanelHeight());
        panelJugadorActual.setPadding(new Insets(-12.0, 0, 0, 20.0));
        panelJugadorActual.setStyle(modelo.getPlayer1PanelStyle());
        ImageView catImageView = new ImageView(new Image(getClass().getResourceAsStream("/avatar/gato.png")));
        catImageView.setFitHeight(150);
        catImageView.setFitWidth(150);
        catImageView.setLayoutX(835);
        catImageView.setLayoutY(536);
        catImageView.setPickOnBounds(true);
        catImageView.setPreserveRatio(true);

        panelExterior.getChildren().add(catImageView);
        panelExterior.getChildren().add(panelJugadorActual);
    }

    public void ponerFichaEnTablero(double layoutX) {
        ImageView fichaJugada = (ImageView) panelJugadorActual.getChildren().removeLast();
        fichaJugada.setLayoutX(layoutX);
        panelInterior.getChildren().add(fichaJugada);
    }

    public void btnEjemploEvento(EventHandler<MouseEvent> e) {
        btnEjemplo.setOnMouseClicked(e);
    }

    public void getEventHandler() {

    }

    private void insertarMesas(AnchorPane panelExterior) {
        AnchorPane topPanel;
        AnchorPane rightPanel;
        AnchorPane leftPanel;

        // Top Panel (Player 3)
        topPanel = new AnchorPane();
        topPanel.setId("jugador3");
        topPanel.setLayoutX(366);
        topPanel.setLayoutY(10);
        topPanel.setPrefSize(268, 98);
        topPanel.setMinSize(268, 98);
        topPanel.setMaxSize(268, 98);
        topPanel.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #000000;");

        ImageView deckImageView = new ImageView(new Image(getClass().getResourceAsStream("/images/mazoJugador.png")));
        deckImageView.setFitHeight(100);
        deckImageView.setFitWidth(58);
        deckImageView.setLayoutX(93);
        deckImageView.setLayoutY(5);
        deckImageView.setPickOnBounds(true);
        deckImageView.setPreserveRatio(true);
        deckImageView.setRotate(180);
        topPanel.getChildren().add(deckImageView);

        ImageView birdImageView = new ImageView(new Image(getClass().getResourceAsStream("/avatar/ave.png")));
        birdImageView.setFitHeight(114);
        birdImageView.setFitWidth(114);
        birdImageView.setLayoutX(-64);
        birdImageView.setLayoutY(-5);
        birdImageView.setPickOnBounds(true);
        birdImageView.setPreserveRatio(true);
        topPanel.getChildren().add(birdImageView);

        Label playerCountLabel = new Label("6");
        playerCountLabel.setAlignment(Pos.CENTER);
        playerCountLabel.setLayoutX(167);
        playerCountLabel.setLayoutY(27);
        playerCountLabel.setPrefSize(60, 60);
        playerCountLabel.setMinSize(60, 60);
        playerCountLabel.setMaxSize(60, 60);
        playerCountLabel.setTextFill(Color.WHITE);
        playerCountLabel.setFont(new Font("Verdana Bold", 40));
        topPanel.getChildren().add(playerCountLabel);

        // Right Panel (Player 4)
        rightPanel = new AnchorPane();
        rightPanel.setId("jugador4");
        rightPanel.setLayoutX(892);
        rightPanel.setLayoutY(210);
        rightPanel.setPrefSize(98, 234);
        rightPanel.setMinSize(98, 234);
        rightPanel.setMaxSize(98, 234);
        rightPanel.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-color: #000000; -fx-border-radius: 20;");

        Label rightPlayerCountLabel = new Label("6");
        rightPlayerCountLabel.setAlignment(Pos.CENTER);
        rightPlayerCountLabel.setLayoutX(21);
        rightPlayerCountLabel.setLayoutY(162);
        rightPlayerCountLabel.setPrefSize(60, 60);
        rightPlayerCountLabel.setMinSize(60, 60);
        rightPlayerCountLabel.setMaxSize(60, 60);
        rightPlayerCountLabel.setTextFill(Color.WHITE);
        rightPlayerCountLabel.setFont(new Font("Verdana Bold", 40));
        rightPanel.getChildren().add(rightPlayerCountLabel);

        ImageView rightDeckImageView = new ImageView(new Image(getClass().getResourceAsStream("/images/mazoJugador.png")));
        rightDeckImageView.setFitHeight(100);
        rightDeckImageView.setFitWidth(58);
        rightDeckImageView.setLayoutX(10);
        rightDeckImageView.setLayoutY(86);
        rightDeckImageView.setPickOnBounds(true);
        rightDeckImageView.setPreserveRatio(true);
        rightDeckImageView.setRotate(-90);
        rightPanel.getChildren().add(rightDeckImageView);

        ImageView rightBirdImageView = new ImageView(new Image(getClass().getResourceAsStream("/avatar/ave.png")));
        rightBirdImageView.setFitHeight(114);
        rightBirdImageView.setFitWidth(114);
        rightBirdImageView.setLayoutX(-13);
        rightBirdImageView.setLayoutY(-34);
        rightBirdImageView.setPickOnBounds(true);
        rightBirdImageView.setPreserveRatio(true);
        rightPanel.getChildren().add(rightBirdImageView);

        // Left Panel (Player 2)
//        mazoIzq = new AnchorPane();
//        mazoIzq.setId("jugador3");
//        mazoIzq.setLayoutX(10);
//        mazoIzq.setLayoutY(210);
//        mazoIzq.setPrefSize(98, 234);
//        mazoIzq.setMinSize(98, 234);
//        mazoIzq.setMaxSize(98, 234);
//        mazoIzq.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-color: #000000; -fx-border-radius: 20;");
        ImageView leftBirdImageView = new ImageView(new Image(getClass().getResourceAsStream("/avatar/ave.png")));
        leftBirdImageView.setId("jugador3");
        leftBirdImageView.setFitHeight(114);
        leftBirdImageView.setFitWidth(114);
        leftBirdImageView.setLayoutX(-6);
        leftBirdImageView.setLayoutY(-34);
        leftBirdImageView.setPickOnBounds(true);
        leftBirdImageView.setPreserveRatio(true);
//        mazoIzq.getChildren().add(leftBirdImageView);

        ImageView leftDeckImageView = new ImageView(new Image(getClass().getResourceAsStream("/images/mazoJugador.png")));
        leftDeckImageView.setFitHeight(100);
        leftDeckImageView.setFitWidth(58);
        leftDeckImageView.setLayoutX(31);
        leftDeckImageView.setLayoutY(86);
        leftDeckImageView.setPickOnBounds(true);
        leftDeckImageView.setPreserveRatio(true);
        leftDeckImageView.setRotate(90);
//        mazoIzq.getChildren().add(leftDeckImageView);

        Label leftPlayerCountLabel = new Label("6");
        leftPlayerCountLabel.setAlignment(Pos.CENTER);
        leftPlayerCountLabel.setLayoutX(12);
        leftPlayerCountLabel.setLayoutY(159);
        leftPlayerCountLabel.setPrefSize(60, 60);
        leftPlayerCountLabel.setMinSize(60, 60);
        leftPlayerCountLabel.setMaxSize(60, 60);
        leftPlayerCountLabel.setTextFill(Color.WHITE);
        leftPlayerCountLabel.setFont(new Font("Verdana Bold", 40));
//        mazoIzq.getChildren().add(leftPlayerCountLabel);
//
//        panelExterior.getChildren().add(mazoIzq);
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

    private void insertarAlTablero(double coordenaX, double coordenadaY, Canvas dibujo, FichaDTO ficha) {
        dibujo.setLayoutX(coordenaX);
        dibujo.setLayoutY(coordenadaY);
        panelInterior.getChildren().add(dibujo);

        modelo.getTablero().setExtremoIzq(ficha);
        trenFichasDibujos.offerFirst(dibujo);
        System.out.println(trenFichasDibujos.size());
    }

    private void insertarPrimeraFicha(FichaDTO fichaDTO) {
        DominoTablero dibujaFicha = new DominoTablero();
        dibujaFicha.construirVertical(fichaDTO);
        Canvas dibujo = dibujaFicha.resultado();

        insertarAlTablero(555, 336, dibujo, fichaDTO);

    }

    private void insertarFichaDespuesMula(FichaDTO fichaDTO) {
        DominoTablero dibujar = new DominoTablero();
        Canvas fichaDibujoIzq = trenFichasDibujos.peekFirst();
        FichaDTO fichaIzq = modelo.getTablero().getExtremoIzq();
        Canvas fichaDibujo;
        double coordenadaX, coordenadaY;

        if (fichaIzq.getOrientacion() == 0) {
            dibujar.construirHorizontal(fichaDTO);
            fichaDibujo = dibujar.resultado();
            coordenadaX = fichaDibujoIzq.getLayoutX() - 106;
            coordenadaY = fichaDibujoIzq.getLayoutY() + 23;
        } else {
            dibujar.construirVertical(fichaDTO);
            fichaDibujo = dibujar.resultado();
            coordenadaX = fichaDibujoIzq.getLayoutX() - 60;
            coordenadaY = fichaDibujoIzq.getLayoutY() - 23;
        }
        insertarAlTablero(coordenadaX, coordenadaY, fichaDibujo, fichaDTO);
    }

    private void insertarFichaExtremoIzq(FichaDTO fichaDTO) {
        System.out.println(fichaDTO);
        DominoTablero dibujar = new DominoTablero();
        Canvas fichaDibujoIzq = trenFichasDibujos.peekFirst();
        FichaDTO fichaIzq = modelo.getTablero().getExtremoIzq();
        Canvas fichaDibujo;
        double coordenadaX, coordenadaY;

        if (fichaDTO.esMula()) {
            if (fichaIzq.getOrientacion() == 0) {
                dibujar.construirHorizontal(fichaDTO);
                fichaDibujo = dibujar.resultado();
                coordenadaX = fichaDibujoIzq.getLayoutX() - 106;
                coordenadaY = fichaDibujoIzq.getLayoutY() + 23;
            } else {
                dibujar.construirVertical(fichaDTO);
                fichaDibujo = dibujar.resultado();
                coordenadaX = fichaDibujoIzq.getLayoutX() - 60;
                coordenadaY = fichaDibujoIzq.getLayoutY() - 23;
            }
        } else {
            if (fichaIzq.getOrientacion() == 0) {
                dibujar.construirVertical(fichaDTO);
                fichaDibujo = dibujar.resultado();
                coordenadaX = fichaDibujoIzq.getLayoutX();
                coordenadaY = fichaDibujoIzq.getLayoutY() - 106;
            } else {
                dibujar.construirHorizontal(fichaDTO);
                fichaDibujo = dibujar.resultado();
                coordenadaX = fichaDibujoIzq.getLayoutX() - 106;
                coordenadaY = fichaDibujoIzq.getLayoutY();
            }
        }

        insertarAlTablero(coordenadaX, coordenadaY, fichaDibujo, fichaDTO);
    }

    private void insertarFichaExtremoDer(FichaDTO fichaDTO) {
    }

    //--------------------------------Eventos--------------------------------
    public void eventoFicha(EventHandler<MouseEvent> e) {
        eventoFicha = e;
    }

    //--------------------------------Metodos Observador--------------------------------
    @Override
    public void agregarFichaMazo(FichaDTO ficha) {
        DominoMazo dibujaFicha = new DominoMazo();
        dibujaFicha.construirVertical(ficha);
        Canvas dibujo = dibujaFicha.resultado();
        modelo.agregarMapeoFichas(dibujo, ficha);
        agregarDominoMazo(dibujo);
    }

    @Override
    public void quitarFichaMazo(Canvas ficha) {
        panelJugadorActual.getChildren().remove(ficha);
    }

    @Override
    public void actualizarPozo() {
        modelo.getPartida().getPozo().getFichas().capacity();
    }

    @Override
    public void agregarFichaTablero(FichaDTO ficha, boolean izquierda) {
        if (modelo.getTablero().tableroVacio()) {
            insertarPrimeraFicha(ficha);
        } else if (izquierda) {
            if(modelo.getTablero().)
            insertarFichaExtremoIzq(ficha);
        } else {
            insertarFichaExtremoDer(ficha);
        }
    }

    @Override
    public void inhabilitarJugador() {
    }

    @Override
    public void actualizarNumFichasJugador() {
    }

    @Override
    public void agregarFichasMazo(List<FichaDTO> fichas) {
        DominoMazo dibujaFicha = new DominoMazo();

        for (FichaDTO ficha : fichas) {
            dibujaFicha.construirVertical(ficha);
            Canvas dibujo = dibujaFicha.resultado();
            modelo.agregarMapeoFichas(dibujo, ficha);
            agregarDominoMazo(dibujo);
        }
        activarEventoFicha();
    }

    @Override
    public void activarEventoFicha() {
        for (Canvas dibujo : modelo.obtenerDibujos()) {
            dibujo.setCursor(Cursor.HAND);
            dibujo.setOnMouseClicked(eventoFicha);
        }
    }

    @Override
    public void desactivarEventoFicha() {
        for (Canvas dibujo : modelo.obtenerDibujos()) {
            dibujo.setCursor(Cursor.DEFAULT);
            dibujo.setOnMouseClicked(null);
        }
    }

}
