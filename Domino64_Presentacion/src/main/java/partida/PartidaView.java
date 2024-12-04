package partida;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PosibleJugadaDTO;
import entidadesDTO.PosicionDTO;
import entidadesDTO.TurnosDTO;
import eventosPartida.ObserverPartida;
import eventosPartida.ObserverPartidaMVC;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
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
import presentacion_dibujo.DibujoTablero;

/**
 * Clase que representa la vista de la partida en la aplicación. Esta clase se
 * encarga de crear la interfaz gráfica de usuario y manejar la lógica de
 * visualización relacionada con la partida.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaView implements ObserverPartidaMVC {

    private static final Logger logger = Logger.getLogger(PartidaView.class.getName());
    private final PartidaModel modelo;
    private AnchorPane containerGameBoard;
    private HBox bottomPlayer;
//    public Deque<Canvas> trenFichasDibujos;
    private StackPane pozoIndicador;
    private StackPane surrenderButton;
    private StackPane pauseButton;
    private DibujoTablero gameBoard;
    private EventHandler<MouseEvent> evaluarFicha;
    private EventHandler<MouseEvent> colocarFicha;
    private AnchorPane panel;
    private Label contadorPozo;

    public PartidaView(PartidaModel modelo) {
        this.modelo = modelo;
////        trenFichasDibujos = new ArrayDeque<>();
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

    public void quitarFichaMazo() {
        Canvas dibujo = modelo.getDibujoSeleccionado();
        bottomPlayer.getChildren().remove(dibujo);
        modelo.quitarMapeoFichas();
    }

    private AnchorPane createGameInterface() {
        // Main container
        panel = new AnchorPane();
        panel.setId("AnchorPane");
        panel.setPrefSize(1000, 700);
        panel.setMinSize(1000, 700);
        panel.setMaxSize(1000, 700);
        panel.setStyle("-fx-background-color: #186F65;");

        // Create all components
        containerGameBoard = createGameBoard();

        createBottomPlayer();
        createDeckIndicator();
        AnchorPane topOptions = createTopOptions();
        ImageView playerAvatar = createPlayerAvatar();

        // Add all components to root
        panel.getChildren().addAll(
                containerGameBoard,
                bottomPlayer,
                pozoIndicador,
                topOptions,
                playerAvatar
        );

        return panel;
    }

    private AnchorPane createGameBoard() {
        // Game board background
        containerGameBoard = new AnchorPane();
        containerGameBoard.setLayoutX(80);
        containerGameBoard.setLayoutY(63);
        containerGameBoard.setPrefSize(840, 560);
        containerGameBoard.setStyle("-fx-background-color: fff;");

        // Inner white game board
        gameBoard = new DibujoTablero();
        gameBoard.setLayoutX(60);
        gameBoard.setLayoutY(40);

        containerGameBoard.getChildren().add(gameBoard);
        return containerGameBoard;
    }

    public void asignarProcesarJugada() {
        gameBoard.setOpcionJugada(colocarFicha);
    }

    private void createTopPlayer(CuentaDTO cuenta) {
        AnchorPane topPlayer = new AnchorPane();
        topPlayer.setId(cuenta.getUsername());
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

        ImageView playerAvatar = new ImageView(new Image(getClass().getResourceAsStream(cuenta.getAvatar().getUrl())));
        playerAvatar.setFitHeight(100);
        playerAvatar.setFitWidth(100);
        playerAvatar.setLayoutX(-64);
        playerAvatar.setLayoutY(1);
        playerAvatar.setId(cuenta.getIdCadena());

        Label cardCount = new Label(String.valueOf(modelo.cuantasFichasInicialesFueron()));
        cardCount.setAlignment(javafx.geometry.Pos.CENTER);
        cardCount.setLayoutX(167);
        cardCount.setLayoutY(22);
        cardCount.setPrefSize(60, 60);
        cardCount.setTextFill(javafx.scene.paint.Color.WHITE);
        cardCount.setFont(Font.font("Verdana Bold", 40));
        cardCount.setId(cuenta.getAvatar().getUrl());

        topPlayer.getChildren().addAll(playerDeck, playerAvatar, cardCount);
        panel.getChildren().add(topPlayer);
    }

    private void createRightPlayer(CuentaDTO cuenta) {
        AnchorPane rightPlayer = new AnchorPane();
        rightPlayer.setId(cuenta.getUsername());
        rightPlayer.setLayoutX(892);
        rightPlayer.setLayoutY(210);
        rightPlayer.setPrefSize(98, 234);
        rightPlayer.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-color: #000000; -fx-border-radius: 20;");

        Label cardCount = new Label(String.valueOf(modelo.cuantasFichasInicialesFueron()));
        cardCount.setAlignment(javafx.geometry.Pos.CENTER);
        cardCount.setLayoutX(21);
        cardCount.setLayoutY(162);
        cardCount.setPrefSize(60, 60);
        cardCount.setTextFill(javafx.scene.paint.Color.WHITE);
        cardCount.setFont(Font.font("Verdana Bold", 40));
        cardCount.setId(cuenta.getIdCadena());

        ImageView playerDeck = new ImageView(new Image(getClass().getResourceAsStream("/images/mazoJugador.png")));
        playerDeck.setFitHeight(88);
        playerDeck.setFitWidth(50);
        playerDeck.setLayoutX(24);
        playerDeck.setLayoutY(95);
        playerDeck.setRotate(-90);

        ImageView playerAvatar = new ImageView(new Image(getClass().getResourceAsStream(cuenta.getAvatar().getUrl())));
        playerAvatar.setFitHeight(114);
        playerAvatar.setFitWidth(114);
        playerAvatar.setLayoutX(-13);
        playerAvatar.setLayoutY(-34);

        rightPlayer.getChildren().addAll(cardCount, playerDeck, playerAvatar);
        panel.getChildren().add(rightPlayer);
    }

    private void createLeftPlayer(CuentaDTO cuenta) {
        AnchorPane leftPlayer = new AnchorPane();
        leftPlayer.setId(cuenta.getUsername());
        leftPlayer.setLayoutX(10);
        leftPlayer.setLayoutY(210);
        leftPlayer.setPrefSize(98, 234);
        leftPlayer.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-color: #000000; -fx-border-radius: 20;");

        ImageView playerAvatar = new ImageView(new Image(getClass().getResourceAsStream(cuenta.getAvatar().getUrl())));
        playerAvatar.setId(cuenta.getAvatar().getUrl());
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

        Label cardCount = new Label(String.valueOf(modelo.cuantasFichasInicialesFueron()));
        cardCount.setAlignment(javafx.geometry.Pos.CENTER);
        cardCount.setLayoutX(16);
        cardCount.setLayoutY(159);
        cardCount.setPrefSize(60, 60);
        cardCount.setTextFill(javafx.scene.paint.Color.WHITE);
        cardCount.setFont(Font.font("Verdana Bold", 40));
        cardCount.setId(cuenta.getIdCadena());

        leftPlayer.getChildren().addAll(playerAvatar, playerDeck, cardCount);
        panel.getChildren().add(leftPlayer);
    }

    private void createBottomPlayer() {
        bottomPlayer = new HBox();
        bottomPlayer.setAlignment(javafx.geometry.Pos.CENTER);
        bottomPlayer.setLayoutX(164);
        bottomPlayer.setLayoutY(598);
        bottomPlayer.setPrefSize(630, 92);
        bottomPlayer.setSpacing(20);
        bottomPlayer.setStyle("-fx-background-color: #B2533E; -fx-border-color: #000000; -fx-border-radius: 20; -fx-background-radius: 20;");
        bottomPlayer.setPadding(new Insets(0, 0, -12, 20));

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

        contadorPozo = new Label("X");
        contadorPozo.setTextFill(javafx.scene.paint.Color.valueOf("#790000"));
        contadorPozo.setFont(Font.font("Russo One", 23));
        contadorPozo.setUnderline(true);
        contadorPozo.setTranslateY(-30);

        pozoIndicador.getChildren().addAll(deckImage, contadorPozo);
        pozoIndicador.setCursor(Cursor.HAND);
        
    }

    private ImageView createPlayerAvatar() {
        CuentaDTO cuenta = modelo.getCuentaActual();
        ImageView playerAvatar = new ImageView(new Image(getClass().getResourceAsStream(cuenta.getAvatar().getUrl())));
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

    public void partida2Jugadores(Map<String, JugadorDTO> mapeoJugadores) {
        JugadorDTO jugadorArriba = mapeoJugadores.get(modelo.queJugadorEs(1));
        createTopPlayer(jugadorArriba.getCuenta());
    }

    public void partida3Jugadores(Map<String, JugadorDTO> mapeoJugadores) {
        JugadorDTO jugadorIzquierda = mapeoJugadores.get(modelo.queJugadorEs(1));
        JugadorDTO jugadorDerecha = mapeoJugadores.get(modelo.queJugadorEs(2));
        createRightPlayer(jugadorIzquierda.getCuenta());
        createLeftPlayer(jugadorDerecha.getCuenta());
    }

    public void partida4Jugadores(Map<String, JugadorDTO> mapeoJugadores) {
        JugadorDTO jugadorIzquierda = mapeoJugadores.get(modelo.queJugadorEs(1));
        JugadorDTO jugadorArriba = mapeoJugadores.get(modelo.queJugadorEs(2));
        JugadorDTO jugadorDerecha = mapeoJugadores.get(modelo.queJugadorEs(3));
        createTopPlayer(jugadorArriba.getCuenta());
        createRightPlayer(jugadorIzquierda.getCuenta());
        createLeftPlayer(jugadorDerecha.getCuenta());
        int fichasPozo = 28 - (4 * modelo.cuantasFichasInicialesFueron());
    }

    private void cambiarNumeroFichasJugador(CuentaDTO cuenta) {
        Label cardCountLabel = (Label) panel.lookup("#" + cuenta.getAvatar().getUrl());
        if (cardCountLabel != null) {
            cardCountLabel.setText("4");  // Update the card count
        }
    }

    //--------------------------------Eventos--------------------------------
    public void evaluarFicha(EventHandler<MouseEvent> e) {
        this.evaluarFicha = e;
    }

    public void procesarJugada(EventHandler<MouseEvent> e) {
        this.colocarFicha = e;
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
    public void dibujarFichaPrueba(FichaDTO ficha) {
        JugadaRealizadaDTO jugada;
        if (cuenta == 0) {
            jugada = new JugadaRealizadaDTO(PosicionDTO.ARRIBA, true, 30, 0, ficha);
        } else if (cuenta == 1) {
            jugada = new JugadaRealizadaDTO(PosicionDTO.ARRIBA, true, 30, 60, ficha);

        } else if (cuenta == 2) {
            jugada = new JugadaRealizadaDTO(PosicionDTO.ARRIBA, true, 30, 120, ficha);

        } else {
            jugada = new JugadaRealizadaDTO(PosicionDTO.ARRIBA, true, 30, 180, ficha);
        }
        gameBoard.dibujarFicha(jugada);
        cuenta++;
    }

    public void dibujarFicha(JugadaRealizadaDTO jugada) {
        gameBoard.dibujarFicha(jugada);

    }

    public void dibujarJugada(FichaDTO ficha, PosibleJugadaDTO jugada) {
        gameBoard.mostrarPosiblesJugadas(ficha, jugada);
    }

    public void limpiarJugadas() {
        gameBoard.limpiarJugadas();
    }

    @Override
    public void actualizarDarFichas(List<FichaDTO> fichas) {
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
    public void actualizarDarFicha(FichaDTO ficha) {
        BuilderFichaMazo dibujaFicha = new BuilderFichaMazo();
        dibujaFicha.construirVertical(ficha);
        Canvas dibujo = dibujaFicha.resultado();
        dibujo.setOnMouseClicked(evaluarFicha);
        modelo.agregarMapeoFichas(dibujo, ficha);
        agregarDominoMazo(dibujo);
    }

    private int cuenta = 0;

    @Override
    public void actualizarJugadorAbandono(CuentaDTO cuenta) {
        System.out.println("Esta cuenta se fue " + cuenta);
    }

    @Override
    public void actualizarProximaJugada(JugadaDTO jugada) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actualizarJugadorSeRindio(CuentaDTO cuenta) {
        System.out.println("Esta cuenta solicito rendirse"  + cuenta);
    }

    @Override
    public void inicializarPartida(TurnosDTO turnos) {
        colocarNumeroPozo(modelo.fichasRestantesPozoInicio());

        switch (modelo.cuantosJugadoresSon()) {
            case 2 -> {
                partida2Jugadores(turnos.getMazos());
            }
            case 3 -> {
                partida3Jugadores(turnos.getMazos());
            }
            default -> {
                partida4Jugadores(turnos.getMazos());
            }
        }

    }

    @Override
    public void actualizarTablero(JugadaRealizadaDTO jugada, CuentaDTO cuenta) {
        gameBoard.dibujarFicha(jugada);
    }

    private void colocarNumeroPozo(int fichasRestantesPozoInicio) {
        contadorPozo.setText(String.valueOf(fichasRestantesPozoInicio));
    }
}
