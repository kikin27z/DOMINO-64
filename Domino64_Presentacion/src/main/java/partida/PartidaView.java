package partida;

//import entidades.Ficha;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.JugadaValidaDTO;
import eventosPartida.ObserverPartida;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presentacion_dibujo.DibujoTablero;
import presentacion_dibujo.BuilderFichaMazo;
import presentacion_dibujo.DibujoJugada;

/**
 * Clase que representa la vista de la partida en la aplicación. Esta clase se
 * encarga de crear la interfaz gráfica de usuario y manejar la lógica de
 * visualización relacionada con la partida.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaView implements ObserverPartida {

    private static final Logger logger = Logger.getLogger(PartidaView.class.getName());
    private final PartidaModel modelo;
    private AnchorPane panelExterior;
    private AnchorPane panelInterior;
    private HBox panelJugadorActual;
    private ScrollPane scrollPanel;
    private Button btnEjemplo;
    public Deque<Canvas> trenFichasDibujos;
    private StackPane pozoIndicator;
    private DibujoTablero dibujoTablero;
    private EventHandler<MouseEvent> evaluarFicha;

    public PartidaView(PartidaModel modelo) {
        this.modelo = modelo;
        trenFichasDibujos = new ArrayDeque<>();

        this.modelo.agregarObserver(this);

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
        scrollPanel.setFitToWidth(true);  // Ajustar el ancho del contenido al ScrollPane
        scrollPanel.setFitToHeight(true);
        scrollPanel.setStyle(modelo.getScrollPanelStyle());
        scrollPanel.setHbarPolicy(modelo.getScrollPanelHbarPolicy()); // Mostrar barra horizontal
        scrollPanel.setVbarPolicy(modelo.getScrollPanelVbarPolicy()); // Mostrar barra vertical

        // Creando el AnchorPane interno dentro del ScrollPane
//        panelInterior = new AnchorPane();
//        panelInterior.setMinSize(modelo.getInternalPanelWidth(), modelo.getInternalPanelHeight()); // Establecemos el tamaño mínimo
//        panelInterior.setStyle(modelo.getInternalPanelStyle());
        dibujoTablero = new DibujoTablero(evaluarFicha, modelo.getInternalPanelWidth(), modelo.getInternalPanelHeight());
//        dibujoTablero.setPrefSize(modelo.getInternalPanelWidth(), modelo.getInternalPanelHeight()); // Establecemos el tamaño mínimo
        dibujoTablero.setStyle(modelo.getInternalPanelStyle());
        dibujoTablero.prefWidthProperty().bind(scrollPanel.widthProperty());
        dibujoTablero.prefHeightProperty().bind(scrollPanel.heightProperty());
        
        
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
//        scrollPanel.setContent(panelInterior);
        scrollPanel.setContent(dibujoTablero);
        crearPozo();

        // Añadiendo el ScrollPane al AnchorPane principal
        panelExterior.getChildren().addAll(btnEjemplo, scrollPanel, pozoIndicator);
    }

    private void crearPozo() {
        pozoIndicator = new StackPane();
        pozoIndicator.setLayoutX(23);
        pozoIndicator.setLayoutY(546);

        ImageView pozoImage = new ImageView(new Image(getClass().getResourceAsStream("/images/pozoIndicador.png")));
        pozoImage.setFitHeight(130);
        pozoImage.setFitWidth(78);

        Label pozoLabel = new Label("14");
        pozoLabel.setTextFill(Color.web("#790000"));
        pozoLabel.setFont(new Font("Russo One", 23));
        pozoLabel.setUnderline(true);
        pozoLabel.setTranslateY(-30);

        pozoIndicator.getChildren().addAll(pozoImage, pozoLabel);
        pozoIndicator.setCursor(Cursor.HAND);
    }

    public void agregarDominoMazo(Canvas ficha) {
        ficha.setVisible(true);
        panelJugadorActual.getChildren().add(ficha);
    }

    public void agregarDominoTablero(Canvas ficha) {
        panelInterior.getChildren().add(ficha);
    }


    private void insertarMesaIzq(CuentaDTO cuenta) {
        AnchorPane mazoIzq;
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

        Label leftPlayerCountLabel = new Label(cuenta.getAvatar());
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

    private void insertarMesaDer(CuentaDTO cuenta) {
        AnchorPane mazoDer = new AnchorPane();
        mazoDer.setId("jugador4");
        mazoDer.setLayoutX(892);
        mazoDer.setLayoutY(210);
        mazoDer.setPrefSize(98, 234);
        mazoDer.setMinSize(98, 234);
        mazoDer.setMaxSize(98, 234);
        mazoDer.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-color: #000000; -fx-border-radius: 20;");

        Label rightPlayerCountLabel = new Label(modelo.obtenerNumeroFichaCuenta(cuenta));
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

        ImageView rightBirdImageView = new ImageView(new Image(getClass().getResourceAsStream(cuenta.getAvatar())));
        rightBirdImageView.setFitHeight(114);
        rightBirdImageView.setFitWidth(114);
        rightBirdImageView.setLayoutX(-13);
        rightBirdImageView.setLayoutY(-34);
        rightBirdImageView.setPickOnBounds(true);
        rightBirdImageView.setPreserveRatio(true);
        mazoDer.getChildren().add(rightBirdImageView);

        panelExterior.getChildren().add(mazoDer);
    }

    private void insertarMesaArr(CuentaDTO cuenta) {
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

        ImageView birdImageView = new ImageView(new Image(getClass().getResourceAsStream(cuenta.getAvatar())));
        birdImageView.setFitHeight(114);
        birdImageView.setFitWidth(114);
        birdImageView.setLayoutX(-64);
        birdImageView.setLayoutY(-5);
        birdImageView.setPickOnBounds(true);
        birdImageView.setPreserveRatio(true);
        mazoArr.getChildren().add(birdImageView);

        Label playerCountLabel = new Label(modelo.obtenerNumeroFichaCuenta(cuenta));
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

    public void insertarMesaAba(CuentaDTO cuenta) {
        panelJugadorActual = new HBox();
        panelJugadorActual.setSpacing(10);
        panelJugadorActual.setLayoutX(modelo.getPlayer1PanelLayoutX());
        panelJugadorActual.setLayoutY(modelo.getPlayer1PanelLayoutY());
        panelJugadorActual.setPrefSize(modelo.getPlayer1PanelWidth(), modelo.getPlayer1PanelHeight());
        panelJugadorActual.setMinSize(modelo.getPlayer1PanelWidth(), modelo.getPlayer1PanelHeight());
        panelJugadorActual.setMaxSize(modelo.getPlayer1PanelWidth(), modelo.getPlayer1PanelHeight());
        panelJugadorActual.setPadding(new Insets(-12.0, 0, 0, 20.0));
        panelJugadorActual.setStyle(modelo.getPlayer1PanelStyle());
        ImageView avatarImg = new ImageView(new Image(getClass().getResourceAsStream(cuenta.getAvatar())));
        avatarImg.setFitHeight(150);
        avatarImg.setFitWidth(150);
        avatarImg.setLayoutX(835);
        avatarImg.setLayoutY(536);
        avatarImg.setPickOnBounds(true);
        avatarImg.setPreserveRatio(true);

        panelExterior.getChildren().add(avatarImg);
        panelExterior.getChildren().add(panelJugadorActual);
    }

    public void insertarAlTablero(double coordenaX, double coordenadaY, Canvas dibujo, FichaDTO ficha) {
        dibujo.setLayoutX(coordenaX);
        dibujo.setLayoutY(coordenadaY);
        panelInterior.getChildren().add(dibujo);

        System.out.println(trenFichasDibujos.size());
    }

    //--------------------------------Eventos--------------------------------
    public void eventoFicha(EventHandler<MouseEvent> e) {
    }

    public void clicPozo(EventHandler<MouseEvent> e) {
        pozoIndicator.setOnMouseClicked(e);
    }

    //--------------------------------Metodos Observador--------------------------------
    public void quitarFichaMazo(Canvas ficha) {
        panelJugadorActual.getChildren().remove(ficha);
    }

////////////////////////////////////

    private void seleccionarFicha(MouseEvent e) {
        if (!modelo.esMiTurno) {
            return;
        }
        Canvas dibujo = (Canvas) e.getSource();
//        EventoMVC<Canvas> evento = new EventoMVC<>("evaluarFichaSeleccionada", dibujo);
//        notificarObservadores(evento);
        modelo.agregarObserver(this);
    }

    
    public void dibujarJugadas(JugadaValidaDTO jugada, FichaDTO ficha){
        this.dibujoTablero.colocarJugadas(jugada, ficha);
    }
//////////////////////////////////
    private void addFichaMazo() {
        FichaDTO ficha = null;
        BuilderFichaMazo dibujaFicha = new BuilderFichaMazo();

        dibujaFicha.construirVertical(ficha);
        Canvas dibujo = dibujaFicha.resultado();
        modelo.agregarMapeoFichas(dibujo, ficha);
        agregarDominoMazo(dibujo);

    }

    private void addFichasMazo() {
        List<FichaDTO> fichas = null;
        BuilderFichaMazo dibujaFicha = new BuilderFichaMazo();

        for (FichaDTO ficha : fichas) {
            dibujaFicha.construirVertical(ficha);
            Canvas dibujo = dibujaFicha.resultado();
            dibujo.setOnMouseClicked(this::seleccionarFicha);
            modelo.agregarMapeoFichas(dibujo, ficha);
            agregarDominoMazo(dibujo);
        }

    }
    
    public void dibujar(){
        FichaDTO ficha = new FichaDTO(4, 4);
        dibujoTablero.dibujarXCosa(ficha);
        
    }
    
    public void btnEjemplo(EventHandler<MouseEvent> e){
        btnEjemplo.setOnMouseClicked(e);
    }
    public void evaluarFicha(EventHandler<MouseEvent> e){
        this.evaluarFicha = e;
    }

    @Override
    public void avisarJugadaRealizada(JugadaRealizadaDTO jugadaDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void avisarFichaSeleccionada(FichaDTO contexto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void avisarDarFichas(List<FichaDTO> fichas) {
        System.out.println("Dibuja");
        BuilderFichaMazo dibujaFicha = new BuilderFichaMazo();
        for (FichaDTO ficha : fichas) {
            dibujaFicha.construirVertical(ficha);
            Canvas dibujo = dibujaFicha.resultado();
            dibujo.setOnMouseClicked(evaluarFicha);
            modelo.agregarMapeoFichas(dibujo, ficha);
            agregarDominoMazo(dibujo);
        }
    }

    @Override
    public void avisarDarFicha(FichaDTO ficha) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
