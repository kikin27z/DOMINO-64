package partida;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.JugadaValidaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import entidadesDTO.PosicionDTO;
import eventosPartida.ObserverPartida;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presentacion_dibujo.BuilderFichaMazo;
import presentacion_dibujo.DibujoTablero2;

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
    private AnchorPane containerGameBoard;
    private HBox bottomPlayer;
    public Deque<Canvas> trenFichasDibujos;
    private StackPane pozoIndicador;
    private StackPane surrenderButton;
    private StackPane pauseButton;
    private DibujoTablero2 gameBoard;
    private EventHandler<MouseEvent> evaluarFicha;
    private EventHandler<MouseEvent> colocarFicha;

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
        Scene scene = new Scene(createGameInterface()); // Usamos panelExterior como la raíz
        fondo.setScene(scene);
        fondo.show();
    }

    public void agregarDominoMazo(Canvas ficha) {
        ficha.setVisible(true);
        bottomPlayer.getChildren().add(ficha);
    }

    public void quitarFichaMazo(Canvas ficha) {
        bottomPlayer.getChildren().remove(ficha);
    }

    private AnchorPane createGameInterface() {
        // Main container
        AnchorPane root = new AnchorPane();
        root.setId("AnchorPane");
        root.setPrefSize(1000, 700);
        root.setMinSize(1000, 700);
        root.setMaxSize(1000, 700);
        root.setStyle("-fx-background-color: #186F65;");

        // Create all components
        containerGameBoard = createGameBoard();
        AnchorPane topPlayer = null;
        AnchorPane rightPlayer = null;
        AnchorPane leftPlayer = null;
        
        int cantidadJugadores = modelo.getCantidadJugadores();
        PartidaDTO partida = modelo.getPartida();
        List<CuentaDTO> jugadores = modelo.getJugadores();
        CuentaDTO cuentaActual = modelo.getJugador().getCuenta();
        jugadores.removeIf(cuenta -> cuenta.getId() == cuentaActual.getId());
        
        switch (cantidadJugadores) {
            case 2 -> topPlayer = pintarJugador(jugadores.get(0),partida.getFichasPorJugador(), modelo.ARRIBA);
            case 3 -> {
                topPlayer = pintarJugador(jugadores.get(0),partida.getFichasPorJugador(), modelo.ARRIBA);
                rightPlayer = pintarJugador(jugadores.get(1),partida.getFichasPorJugador(), modelo.DERECHA);
            }
            case 4 -> {
                topPlayer = pintarJugador(jugadores.get(0),partida.getFichasPorJugador(), modelo.ARRIBA);
                rightPlayer = pintarJugador(jugadores.get(1),partida.getFichasPorJugador(), modelo.DERECHA);
                leftPlayer = pintarJugador(jugadores.get(2),partida.getFichasPorJugador(), modelo.IZQUIERDA);
            }
        }

        createBottomPlayer();
        AnchorPane topOptions = createTopOptions();
        createDeckIndicator();
        ImageView playerAvatar = createPlayerAvatar();

        // Add all components to root
        switch(cantidadJugadores){
            case 2 -> {
                root.getChildren().addAll(
                        containerGameBoard,
                        topPlayer,
                        bottomPlayer,
                        topOptions,
                        pozoIndicador,
                        playerAvatar
                );
            }
            case 3 -> {
                root.getChildren().addAll(
                        containerGameBoard,
                        topPlayer,
                        rightPlayer,
                        bottomPlayer,
                        topOptions,
                        pozoIndicador,
                        playerAvatar
                );
            }
            case 4 -> {
                root.getChildren().addAll(
                        containerGameBoard,
                        topPlayer,
                        rightPlayer,
                        leftPlayer,
                        bottomPlayer,
                        topOptions,
                        pozoIndicador,
                        playerAvatar
                );
            }
        }
        return root;
    }

    private AnchorPane createGameBoard() {
        // Game board background
        containerGameBoard = new AnchorPane();
        containerGameBoard.setLayoutX(80);
        containerGameBoard.setLayoutY(63);
        containerGameBoard.setPrefSize(840, 560);
        containerGameBoard.setStyle("-fx-background-color: fff;");

        // Inner white game board
        gameBoard = new DibujoTablero2(evaluarFicha);
        gameBoard.setLayoutX(60);
        gameBoard.setLayoutY(40);

        containerGameBoard.getChildren().add(gameBoard);
        return containerGameBoard;
    }

    private AnchorPane pintarJugador(CuentaDTO jugador, int cantidadFichas, int posicion){
        AnchorPane panelJugador = new AnchorPane();
        panelJugador.setId(jugador.getIdCadena());
        panelJugador.setLayoutX(modelo.getPlayerLayoutX(posicion));
        panelJugador.setLayoutY(modelo.getPlayerLayoutY(posicion));
        double w = modelo.getPlayerPrefSize(posicion)[0];
        double h = modelo.getPlayerPrefSize(posicion)[1];
        panelJugador.setPrefSize(w,h);
        panelJugador.setStyle(modelo.getPlayerStyle());
        
        ImageView playerDeck = new ImageView(new Image(getClass().getResourceAsStream(modelo.getURL_MAZO_JUGADOR())));
        playerDeck.setFitHeight(modelo.getPLAYER_DECK_HEIGHT());
        playerDeck.setFitWidth(modelo.getPLAYER_DECK_WIDTH());
        playerDeck.setLayoutX(modelo.getPlayerDeckLayoutX(posicion));
        playerDeck.setLayoutY(modelo.getPlayerDeckLayoutY(posicion));
        playerDeck.setRotate(modelo.getPlayerDeckRotate(posicion));

        ImageView playerAvatar = new ImageView(new Image(getClass().getResourceAsStream(jugador.getAvatar().getUrl())));
        playerAvatar.setFitHeight(modelo.getPlayerAvatarHeight(posicion));
        playerAvatar.setFitWidth(modelo.getPlayerAvatarHeight(posicion));
        playerAvatar.setLayoutX(modelo.getPlayerAvatarLayoutX(posicion));
        playerAvatar.setLayoutY(modelo.getPlayerAvatarLayoutY(posicion));
        
        Label cardCount = new Label(String.valueOf(cantidadFichas));
        cardCount.setAlignment(modelo.getCARD_COUNT_ALIGNMENT());
        cardCount.setLayoutX(modelo.getPlayerCardCountLayoutX(posicion));
        cardCount.setLayoutY(modelo.getPlayerCardCountLayoutY(posicion));
        double ccw = modelo.getCARD_COUNT_PREF_SIZE()[0];
        double cch = modelo.getCARD_COUNT_PREF_SIZE()[1];
        cardCount.setPrefSize(ccw, cch);
        cardCount.setTextFill(modelo.getCARD_COUNT_COLOR());
        cardCount.setFont(modelo.getCARD_COUNT_FONT());
        
        panelJugador.getChildren().addAll(playerDeck, playerAvatar, cardCount);
        return panelJugador;
    }
    
    private AnchorPane createTopPlayer(JugadorDTO jugador, int cantidadFichas) {
        AnchorPane topPlayer = new AnchorPane();
        topPlayer.setId("jugador3");
        topPlayer.setLayoutX(366);
        topPlayer.setLayoutY(0);
        topPlayer.setPrefSize(268, 98);
        topPlayer.setMinSize(268, 98);
        topPlayer.setMaxSize(268, 98);
        topPlayer.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #000000;");

        ImageView playerDeck = new ImageView(new Image(getClass().getResourceAsStream("/images/mazoJugador.png")));
        playerDeck.setFitHeight(88);
        playerDeck.setFitWidth(50);
        playerDeck.setLayoutX(109);
        playerDeck.setLayoutY(7);
        playerDeck.setRotate(180);
        
        CuentaDTO cuenta = jugador.getCuenta();
        ImageView playerAvatar = new ImageView(new Image(getClass().getResourceAsStream(cuenta.getAvatar().getUrl())));
        playerAvatar.setFitHeight(100);
        playerAvatar.setFitWidth(100);
        playerAvatar.setLayoutX(-64);
        playerAvatar.setLayoutY(1);

        Label cardCount = new Label(String.valueOf(cantidadFichas));
        cardCount.setAlignment(javafx.geometry.Pos.CENTER);
        cardCount.setLayoutX(167);
        cardCount.setLayoutY(22);
        cardCount.setPrefSize(60, 60);
        cardCount.setTextFill(javafx.scene.paint.Color.WHITE);
        cardCount.setFont(Font.font("Verdana Bold", 40));

        topPlayer.getChildren().addAll(playerDeck, playerAvatar, cardCount);
        return topPlayer;
    }

    private AnchorPane createRightPlayer() {
        AnchorPane rightPlayer = new AnchorPane();
        rightPlayer.setId("jugador4");
        rightPlayer.setLayoutX(892);
        rightPlayer.setLayoutY(210);
        rightPlayer.setPrefSize(98, 234);
        rightPlayer.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-color: #000000; -fx-border-radius: 20;");

        Label cardCount = new Label("6");
        cardCount.setAlignment(javafx.geometry.Pos.CENTER);
        cardCount.setLayoutX(21);
        cardCount.setLayoutY(162);
        cardCount.setPrefSize(60, 60);
        cardCount.setTextFill(javafx.scene.paint.Color.WHITE);
        cardCount.setFont(Font.font("Verdana Bold", 40));

        ImageView playerDeck = new ImageView(new Image(getClass().getResourceAsStream("/images/mazoJugador.png")));
        playerDeck.setFitHeight(88);
        playerDeck.setFitWidth(50);
        playerDeck.setLayoutX(24);
        playerDeck.setLayoutY(95);
        playerDeck.setRotate(-90);

        ImageView playerAvatar = new ImageView(new Image(getClass().getResourceAsStream("/avatar/ave.png")));
        playerAvatar.setFitHeight(114);
        playerAvatar.setFitWidth(114);
        playerAvatar.setLayoutX(-13);
        playerAvatar.setLayoutY(-34);

        rightPlayer.getChildren().addAll(cardCount, playerDeck, playerAvatar);
        return rightPlayer;
    }

    private AnchorPane createLeftPlayer() {
        AnchorPane leftPlayer = new AnchorPane();
        leftPlayer.setId("jugador3");
        leftPlayer.setLayoutX(10);
        leftPlayer.setLayoutY(210);
        leftPlayer.setPrefSize(98, 234);
        leftPlayer.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-color: #000000; -fx-border-radius: 20;");

        ImageView playerAvatar = new ImageView(new Image(getClass().getResourceAsStream("/avatar/ave.png")));
        playerAvatar.setId("jugador3");
        playerAvatar.setFitHeight(114);
        playerAvatar.setFitWidth(114);
        playerAvatar.setLayoutX(-6);
        playerAvatar.setLayoutY(-34);

        ImageView playerDeck = new ImageView(new Image(getClass().getResourceAsStream("/images/mazoJugador.png")));
        playerDeck.setFitHeight(88);
        playerDeck.setFitWidth(50);
        playerDeck.setLayoutX(25);
        playerDeck.setLayoutY(86);
        playerDeck.setRotate(90);

        Label cardCount = new Label("6");
        cardCount.setAlignment(javafx.geometry.Pos.CENTER);
        cardCount.setLayoutX(16);
        cardCount.setLayoutY(159);
        cardCount.setPrefSize(60, 60);
        cardCount.setTextFill(javafx.scene.paint.Color.WHITE);
        cardCount.setFont(Font.font("Verdana Bold", 40));

        leftPlayer.getChildren().addAll(playerAvatar, playerDeck, cardCount);
        return leftPlayer;
    }

    private void createBottomPlayer() {
        bottomPlayer = new HBox();
        bottomPlayer.setAlignment(modelo.getBOTTOM_PLAYER_ALIGNMENT());
        bottomPlayer.setLayoutX(modelo.getPlayerLayoutX(modelo.ABAJO));
        bottomPlayer.setLayoutY(modelo.getPlayerLayoutY(modelo.ABAJO));
        double w = modelo.getPlayerPrefSize(modelo.ABAJO)[0];
        double h = modelo.getPlayerPrefSize(modelo.ABAJO)[1];
        bottomPlayer.setPrefSize(w, h);
        bottomPlayer.setSpacing(modelo.getBOTTOM_PLAYER_SPACING());
        bottomPlayer.setStyle(modelo.getPlayerStyle());
        bottomPlayer.setPadding(modelo.getBOTTOM_PLAYER_PADDING());
        
        CuentaDTO cuenta = modelo.getJugador().getCuenta();
        
        ImageView playerAvatar = new ImageView(new Image(getClass().getResourceAsStream(cuenta.getAvatar().getUrl())));
        playerAvatar.setFitHeight(modelo.getPlayerAvatarHeight(modelo.ABAJO));
        playerAvatar.setFitWidth(modelo.getPlayerAvatarWidth(modelo.ABAJO));
        playerAvatar.setLayoutX(modelo.getPlayerAvatarLayoutX(modelo.ABAJO));
        playerAvatar.setLayoutY(modelo.getPlayerAvatarLayoutY(modelo.ABAJO));

    }

    private AnchorPane createTopOptions() {
        AnchorPane topOptions = new AnchorPane();

        // Surrender button
        surrenderButton = createIconButton("/images/rendirse.png", 15, 12);

        // Pause button
        pauseButton = createIconButton("/images/btnPausa.png", 930, 12);

        topOptions.getChildren().addAll(surrenderButton, pauseButton);
        return topOptions;
    }

    private void createDeckIndicator() {
        pozoIndicador = new StackPane();
        pozoIndicador.setLayoutX(23);
        pozoIndicador.setLayoutY(546);

        ImageView deckImage = new ImageView(new Image(getClass().getResourceAsStream("/images/pozoIndicador.png")));
        deckImage.setFitHeight(130);
        deckImage.setFitWidth(78);

        Label deckLabel = new Label("jo");
        deckLabel.setTextFill(javafx.scene.paint.Color.valueOf("#790000"));
        deckLabel.setFont(Font.font("Russo One", 23));
        deckLabel.setUnderline(true);
        deckLabel.setTranslateY(-30);

        pozoIndicador.getChildren().addAll(deckImage, deckLabel);
        pozoIndicador.setCursor(Cursor.HAND);
    }

    private ImageView createPlayerAvatar() {
        ImageView playerAvatar = new ImageView(new Image(getClass().getResourceAsStream("/avatar/jaguar.png")));
        playerAvatar.setFitHeight(140);
        playerAvatar.setFitWidth(140);
        playerAvatar.setLayoutX(856);
        playerAvatar.setLayoutY(553);
        return playerAvatar;
    }

    private StackPane createIconButton(String imageUrl, double x, double y) {
        StackPane buttonContainer = new StackPane();
        buttonContainer.setLayoutX(x);
        buttonContainer.setLayoutY(y);
        buttonContainer.setPrefSize(60, 60);
        buttonContainer.setCursor(Cursor.HAND);

        Button button = new Button();
        button.setPrefSize(60, 60);
        button.setStyle("-fx-background-radius: 100; -fx-background-color: #d9d9d9; -fx-border-color: #2F1C1C; -fx-border-radius: 90;");

        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(imageUrl)));
        icon.setFitHeight(40);
        icon.setFitWidth(40);

        buttonContainer.getChildren().addAll(button, icon);
        return buttonContainer;
    }

    //--------------------------------Eventos--------------------------------
    public void evaluarFicha(EventHandler<MouseEvent> e) {
        this.evaluarFicha = e;
    }
    public void procesarJugada(EventHandler<MouseEvent> e) {
        colocarFicha = e;
    }

    public void clicPozo(EventHandler<MouseEvent> e) {
        pozoIndicador.setOnMouseClicked(e);
    }

    public void clicPausa(EventHandler<MouseEvent> e) {
        pauseButton.setOnMouseClicked(e);
    }

    public void clicRendirse(EventHandler<MouseEvent> e) {
        surrenderButton.setOnMouseClicked(e);
    }

    //--------------------------------Metodos Observador--------------------------------
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
        BuilderFichaMazo dibujaFicha = new BuilderFichaMazo();
        dibujaFicha.construirVertical(ficha);
        Canvas dibujo = dibujaFicha.resultado();
        dibujo.setOnMouseClicked(evaluarFicha);
        modelo.agregarMapeoFichas(dibujo, ficha);
        agregarDominoMazo(dibujo);
    }
    
    public void dibujarFicha(FichaDTO ficha){
        gameBoard.dibujarFicha(ficha, 300, 270, PosicionDTO.DERECHA);
    }
}
