package lobby;

import entidadesDTO.CuentaDTO;
import java.io.IOException;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * La clase LobbyView gestiona la interfaz gráfica (GUI) de la pantalla de lobby
 * de una partida multijugador. Permite a los usuarios visualizar los jugadores
 * conectados, cambiar configuraciones de la partida y gestionar la selección de
 * avatares.
 *
 * Esta clase implementa el patrón Observer, y se suscribe a los cambios del
 * modelo {@link LobbyModel}.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class LobbyView {

    private final LobbyModel modelo;
    private AnchorPane panel;
    private StackPane btnAjustes;
    private TextField txtUsuario;
    private HBox jugadoresContenedor;
    private Stage fondo;
    private Stage ventanaConfiguracion;
    private Stage ventanaAvatares;
    private AnchorPane fondoConfiguracionPartida;
    private Label lblEncabezado;
    private Label lblMensaje;
    private ImageView ajustesImg;
    private Button btnConfirmarCambios;
    private Button btnCancelarCambios;
    private ImageView btnAvatar;
    private AnchorPane fondoAvatares;

    private Button btnAbandonar;
    private Button btnIniciar;
    private HBox jugadoresContainer;

    /**
     * Constructor de la clase LobbyView. Inicializa el modelo asociado a esta
     * vista.
     *
     * @param modelo El modelo del lobby que contiene los datos y la lógica
     * relacionada con la partida.
     */
    public LobbyView(LobbyModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Inicia la escena de la pantalla de lobby.
     *
     * @param fondo El escenario principal donde se mostrará la pantalla del
     * lobby.
     * @throws IOException Si ocurre un error durante la carga de los recursos.
     */
    public void iniciarEscena(Stage fondo) throws IOException {
        inicializarVista();
        Scene scene = new Scene(panel);
        this.fondo = fondo;
        fondo.setScene(scene);
        fondo.show();
        cargarConfiguracion();
        cargarAvatares();
    }

    //------------GUI------------\\
    private void inicializarVista() {
        // Configuración básica del AnchorPane
        panel = new AnchorPane();
        panel.setMaxSize(1000, 700);
        panel.setPrefSize(1000, 700);
        panel.setStyle("-fx-background-color: #B2533E;");

        // Label para jugadores listos
        Label lblJugadoresListos = new Label("1/3 listos para iniciar");
        lblJugadoresListos.setFont(Font.font("Verdana Bold", 48));
        lblJugadoresListos.setTextFill(Color.valueOf("#2f1c1c"));
        lblJugadoresListos.setPrefWidth(855);
        lblJugadoresListos.setAlignment(Pos.CENTER);
        AnchorPane.setLeftAnchor(lblJugadoresListos, 60.0);
        AnchorPane.setTopAnchor(lblJugadoresListos, 100.0);
        panel.getChildren().add(lblJugadoresListos);

        // Contenedor de jugadores
        jugadoresContainer = new HBox(18);
        jugadoresContainer.setMaxSize(942, 278);
        jugadoresContainer.setMinSize(942, 278);
        jugadoresContainer.setPrefSize(942, 278);
        AnchorPane.setLeftAnchor(jugadoresContainer, 30.0);
        AnchorPane.setTopAnchor(jugadoresContainer, 216.0);

        // Agregar 4 paneles de jugadores
        ponerJugadorActual();
        ponerJugadorOtro();
        ponerJugadorOtro();
        ponerJugadorOtro();
        panel.getChildren().add(jugadoresContainer);

        // Label de fin de juego
        Label lblFinJuego = new Label("Fin del juego");
        lblFinJuego.setFont(Font.font("Verdana Bold", 33));
        lblFinJuego.setTextFill(Color.valueOf("#2e1c1cb9"));
        lblFinJuego.setPrefWidth(855);
        lblFinJuego.setAlignment(Pos.CENTER);
        AnchorPane.setLeftAnchor(lblFinJuego, 72.0);
        AnchorPane.setTopAnchor(lblFinJuego, 496.0);
        panel.getChildren().add(lblFinJuego);

        // Botón Abandonar
        btnAbandonar = new Button("Abandonar partida");
        btnAbandonar.setFont(Font.font("Russo One", 37));
        btnAbandonar.setStyle("-fx-background-color: #2F1C1C; -fx-background-radius: 20;");
        btnAbandonar.setTextFill(Color.WHITE);
        btnAbandonar.setPrefWidth(420);
        btnAbandonar.setMaxHeight(70);
        btnAbandonar.setCursor(Cursor.HAND);
        AnchorPane.setLeftAnchor(btnAbandonar, 49.0);
        AnchorPane.setTopAnchor(btnAbandonar, 562.0);
        panel.getChildren().add(btnAbandonar);

        // Botón Iniciar
        btnIniciar = new Button("Iniciar partida");
        btnIniciar.setFont(Font.font("Russo One", 37));
        btnIniciar.setStyle("-fx-background-color: #D9D9D9; -fx-background-radius: 20;");
        btnIniciar.setTextFill(Color.valueOf("#2f1c1c"));
        btnIniciar.setPrefWidth(420);
        btnIniciar.setMaxHeight(70);
        btnIniciar.setCursor(Cursor.HAND);
        AnchorPane.setLeftAnchor(btnIniciar, 507.0);
        AnchorPane.setTopAnchor(btnIniciar, 562.0);
        panel.getChildren().add(btnIniciar);

        // Botón de ajustes
        btnAjustes = crearBotonAjustes();
        AnchorPane.setRightAnchor(btnAjustes, 66.0);
        AnchorPane.setTopAnchor(btnAjustes, 46.0);
        panel.getChildren().add(btnAjustes);

        // Labels del código de partida
        Label lblCodigoTitulo = new Label("Código de la partida");
        lblCodigoTitulo.setFont(Font.font("Russo One", 15));
        lblCodigoTitulo.setTextFill(Color.valueOf("#2f1c1c"));
        lblCodigoTitulo.setAlignment(Pos.CENTER);
        lblCodigoTitulo.setMaxSize(192, 30);
        lblCodigoTitulo.setMinWidth(192);
        AnchorPane.setLeftAnchor(lblCodigoTitulo, 30.0);
        AnchorPane.setTopAnchor(lblCodigoTitulo, 21.0);
        panel.getChildren().add(lblCodigoTitulo);

        Label lblCodigo = new Label("xxx-xxx");
        lblCodigo.setFont(Font.font("Russo One", 20));
        lblCodigo.setTextFill(Color.valueOf("#2f1c1c"));
        lblCodigo.setAlignment(Pos.CENTER);
        lblCodigo.setStyle("-fx-background-color: #D9D9D9; -fx-background-radius: 20;");
        lblCodigo.setMaxSize(192, 30);
        lblCodigo.setMinWidth(192);
        AnchorPane.setLeftAnchor(lblCodigo, 30.0);
        AnchorPane.setTopAnchor(lblCodigo, 46.0);
        panel.getChildren().add(lblCodigo);
    }

    private void ponerJugadorActual() {
        jugadoresContainer.getChildren().add(crearPanelJugadorActual());
    }

    private void ponerJugadorOtro() {
        jugadoresContainer.getChildren().add(crearPanelOtroJugador());

    }

    private AnchorPane crearPanelJugadorActual() {
        AnchorPane panel = new AnchorPane();
        panel.setMaxSize(222, 278);
        panel.setMinSize(222, 278);
        panel.setPrefSize(222, 278);

        // Avatar del jugador
        btnAvatar = new ImageView(new Image(getClass().getResourceAsStream("/avatar/ave.png")));
        btnAvatar.setFitHeight(150);
        btnAvatar.setFitWidth(200);
        btnAvatar.setPreserveRatio(true);
        AnchorPane.setLeftAnchor(btnAvatar, 41.0);
        AnchorPane.setTopAnchor(btnAvatar, 15.0);

        // Icono de listo
        ImageView iconoListo = new ImageView(new Image(getClass().getResourceAsStream("/images/listo.png")));
        iconoListo.setFitHeight(150);
        iconoListo.setFitWidth(40);
        iconoListo.setPreserveRatio(true);
        AnchorPane.setLeftAnchor(iconoListo, 148.0);
        AnchorPane.setTopAnchor(iconoListo, 8.0);

        // Nombre del jugador
        Label lblNombre = new Label("Karim D Luffy");
        lblNombre.setFont(Font.font("Russo One", 20));
        lblNombre.setTextFill(Color.valueOf("#2f1c1c"));
        lblNombre.setAlignment(Pos.CENTER);
        lblNombre.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 20; -fx-padding: 6 4;");
        lblNombre.setMaxWidth(192);
        lblNombre.setMinWidth(192);
        lblNombre.setPrefWidth(192);
        AnchorPane.setLeftAnchor(lblNombre, 15.0);
        AnchorPane.setTopAnchor(lblNombre, 180.0);

        panel.getChildren().addAll(btnAvatar, iconoListo, lblNombre);
        return panel;
    }

    private AnchorPane crearPanelOtroJugador() {
        AnchorPane panel = new AnchorPane();
        panel.setMaxSize(222, 278);
        panel.setMinSize(222, 278);
        panel.setPrefSize(222, 278);

        // Avatar del jugador
        ImageView avatar = new ImageView(new Image(getClass().getResourceAsStream("/avatar/ave.png")));
        avatar.setFitHeight(150);
        avatar.setFitWidth(200);
        avatar.setPreserveRatio(true);
        AnchorPane.setLeftAnchor(avatar, 41.0);
        AnchorPane.setTopAnchor(avatar, 15.0);

        // Icono de listo
        ImageView iconoListo = new ImageView(new Image(getClass().getResourceAsStream("/images/listo.png")));
        iconoListo.setFitHeight(150);
        iconoListo.setFitWidth(40);
        iconoListo.setPreserveRatio(true);
        AnchorPane.setLeftAnchor(iconoListo, 148.0);
        AnchorPane.setTopAnchor(iconoListo, 8.0);

        // Nombre del jugador
        Label lblNombre = new Label("Karim D Luffy");
        lblNombre.setFont(Font.font("Russo One", 20));
        lblNombre.setTextFill(Color.valueOf("#2f1c1c"));
        lblNombre.setAlignment(Pos.CENTER);
        lblNombre.setStyle("-fx-background-color: #D9D9D9; -fx-background-radius: 20; -fx-padding: 6 4;");
        lblNombre.setMaxWidth(192);
        lblNombre.setMinWidth(192);
        lblNombre.setPrefWidth(192);
        AnchorPane.setLeftAnchor(lblNombre, 15.0);
        AnchorPane.setTopAnchor(lblNombre, 180.0);

        panel.getChildren().addAll(avatar, iconoListo, lblNombre);
        return panel;
    }

    private StackPane crearBotonAjustes() {
        StackPane stackPane = new StackPane();
        stackPane.setMaxSize(60, 60);
        stackPane.setPrefSize(60, 60);

        Button btn = new Button();
        btn.setMaxSize(60, 60);
        btn.setMinSize(60, 60);
        btn.setStyle("-fx-border-color: #000000; -fx-border-radius: 100; -fx-background-radius: 100;");
        btn.setEffect(new DropShadow());

        ImageView icono = new ImageView(new Image(getClass().getResourceAsStream("/images/iconAjustes.png")));
        icono.setFitHeight(60);
        icono.setFitWidth(60);
        icono.setPreserveRatio(true);
        icono.setStyle("-fx-fit-height: 40; -fx-fit-width: 40;");

        stackPane.getChildren().addAll(btn, icono);
        stackPane.setCursor(Cursor.HAND);

        return stackPane;
    }

    // Getters para los botones para poder manejar eventos
    public Button getBtnAbandonar() {
        return btnAbandonar;
    }

    public Button getBtnIniciar() {
        return btnIniciar;
    }

    //------------GUI actualizar con modelo-------------
    /**
     * Quita el panel del jugador enviado como parámetro.
     *
     * @param panelJugador Panel del jugador a quitar de la GUI.
     */
    private void quitarJugadorOnline(AnchorPane panelJugador) {
        jugadoresContenedor.getChildren().remove(panelJugador);
    }

    /**
     * Actualiza el avatar de un jugador en el panel correspondiente.
     *
     * @param panelJugador El panel del jugador donde se mostrará el avatar.
     * @param url La URL de la imagen del nuevo avatar.
     */
    private void actualizarAvatarJugador(AnchorPane panelJugador, String url) {
        ImageView avatar = (ImageView) panelJugador.lookup("#avatar");
        Image nuevaImagen = new Image(getClass().getResourceAsStream(url));
        avatar.setImage(nuevaImagen);
    }

    /**
     * Actualiza el nombre de un jugador en el panel correspondiente.
     *
     * @param panelJugador El panel del jugador donde se mostrará el nombre.
     * @param nombre El nuevo nombre del jugador.
     */
    private void actualizarNombreOtroJugador(AnchorPane panelJugador, String nombre) {
        Label lblUsuario = (Label) panelJugador.lookup("#nombre");
        lblUsuario.setText(nombre);
    }

    /**
     * Muestra que un jugador está listo añadiendo un icono al panel del
     * jugador.
     *
     * @param panelJugador El panel del jugador que se marcará como listo.
     */
    private void ponerListoJugador(AnchorPane panelJugador) {
        ImageView imgListo = new ImageView(new Image(getClass().getResourceAsStream("/images/listo.png")));
        imgListo.setFitHeight(40);
        imgListo.setFitWidth(40);
        imgListo.setLayoutX(148);
        imgListo.setLayoutY(8);
        imgListo.setId("listo");
        panelJugador.getChildren().add(imgListo);
    }

    /**
     * Elimina el icono de "listo" de un jugador en el panel correspondiente.
     *
     * @param panelJugador El panel del jugador al que se le eliminará el icono
     * de listo.
     */
    private void quitarListoJugador(AnchorPane panelJugador) {
        Node nodoAQuitar = (ImageView) panelJugador.lookup("#listo");
        panelJugador.getChildren().remove(nodoAQuitar);
    }

    /**
     * Activa un avatar en la ventana de selección de avatares, haciéndolo
     * seleccionable.
     *
     * @param avatar El ImageView del avatar a activar.
     */
    private void activarAvatar(ImageView avatar) {
        avatar.setCursor(Cursor.HAND);
//        avatar.setOnMouseClicked(value);
    }

    /**
     * Inhabilita un avatar en la ventana de selección de avatares, haciéndolo
     * no seleccionable.
     *
     * @param avatar El ImageView del avatar a inhabilitar.
     */
    private void inhabilitarAvatar(ImageView avatar) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-0.76);
        avatar.setEffect(colorAdjust);
    }

    //----------Modal windows----------
    /**
     * Carga la ventana modal de configuración de la partida.
     */
    private void cargarConfiguracion() {
        fondoConfiguracionPartida = new AnchorPane();
        fondoConfiguracionPartida.setPrefHeight(400);
        fondoConfiguracionPartida.setPrefWidth(640);
        fondoConfiguracionPartida.setMaxHeight(400);
        fondoConfiguracionPartida.setMaxWidth(640);
        fondoConfiguracionPartida.setStyle("-fx-background-color: D9D9D9;");

        // Top banner
        AnchorPane topBanner = new AnchorPane();
        topBanner.setPrefHeight(107);
        topBanner.setPrefWidth(640);
        topBanner.setStyle("-fx-background-color: #FCE09B;");

        Label titleLabel = new Label("Configura partida");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setLayoutX(52);
        titleLabel.setLayoutY(24);
        titleLabel.setPrefHeight(63);
        titleLabel.setPrefWidth(498);
        titleLabel.setTextFill(javafx.scene.paint.Color.web("#2e1c1cc4"));
        titleLabel.setFont(new Font("Verdana Bold", 48));

        topBanner.getChildren().add(titleLabel);

        // Buttons
        btnCancelarCambios = new Button("Cancelar");
        btnCancelarCambios.setLayoutX(89);
        btnCancelarCambios.setLayoutY(327);
        btnCancelarCambios.setPrefWidth(200);
        btnCancelarCambios.setStyle("-fx-background-color: #5C4033; -fx-background-radius: 20;");
        btnCancelarCambios.setTextFill(javafx.scene.paint.Color.WHITE);
        btnCancelarCambios.setFont(new Font("Russo One", 23));
        btnCancelarCambios.setCursor(Cursor.HAND);

        btnConfirmarCambios = new Button("Confirmar");
        btnConfirmarCambios.setLayoutX(352);
        btnConfirmarCambios.setLayoutY(327);
        btnConfirmarCambios.setPrefWidth(200);
        btnConfirmarCambios.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 20;");
        btnConfirmarCambios.setTextFill(javafx.scene.paint.Color.WHITE);
        btnConfirmarCambios.setFont(new Font("Russo One", 23));
        btnConfirmarCambios.setCursor(Cursor.HAND);

        // Labels
        Label playersLabel = new Label("Cantidad de jugadores");
        playersLabel.setAlignment(Pos.CENTER);
        playersLabel.setLayoutX(57);
        playersLabel.setLayoutY(123);
        playersLabel.setPrefHeight(32);
        playersLabel.setPrefWidth(518);
        playersLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        playersLabel.setFont(new Font("Russo One", 25));

        Label tilesLabel = new Label("Cantidad de fichas a repartir");
        tilesLabel.setAlignment(Pos.CENTER);
        tilesLabel.setLayoutX(57);
        tilesLabel.setLayoutY(222);
        tilesLabel.setPrefHeight(32);
        tilesLabel.setPrefWidth(518);
        tilesLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        tilesLabel.setFont(new Font("Russo One", 25));

        // Choice box
        ChoiceBox<String> tilesChoiceBox = new ChoiceBox<>();
        tilesChoiceBox.setLayoutX(244);
        tilesChoiceBox.setLayoutY(261);
        tilesChoiceBox.setPrefWidth(150);
        tilesChoiceBox.setStyle("-fx-background-color: #FFF;");
        tilesChoiceBox.setCursor(Cursor.HAND);
        tilesChoiceBox.getItems().addAll("2", "3", "4", "5", "6", "7");
        tilesChoiceBox.setValue("7");

        // Radio buttons
        ToggleGroup playerToggleGroup = new ToggleGroup();

        RadioButton twoPlayersRadio = new RadioButton("2");
        twoPlayersRadio.setLayoutX(205);
        twoPlayersRadio.setLayoutY(172);
        twoPlayersRadio.setTextFill(javafx.scene.paint.Color.WHITE);
        twoPlayersRadio.setFont(new Font("Russo One", 20));
        twoPlayersRadio.setToggleGroup(playerToggleGroup);
        twoPlayersRadio.setCursor(Cursor.HAND);

        RadioButton threePlayersRadio = new RadioButton("3");
        threePlayersRadio.setLayoutX(289);
        threePlayersRadio.setLayoutY(172);
        threePlayersRadio.setTextFill(javafx.scene.paint.Color.WHITE);
        threePlayersRadio.setFont(new Font("Russo One", 20));
        threePlayersRadio.setToggleGroup(playerToggleGroup);
        threePlayersRadio.setCursor(Cursor.HAND);

        RadioButton fourPlayersRadio = new RadioButton("4");
        fourPlayersRadio.setLayoutX(378);
        fourPlayersRadio.setLayoutY(172);
        fourPlayersRadio.setTextFill(javafx.scene.paint.Color.WHITE);
        fourPlayersRadio.setFont(new Font("Russo One", 20));
        fourPlayersRadio.setToggleGroup(playerToggleGroup);
        fourPlayersRadio.setCursor(Cursor.HAND);
        fourPlayersRadio.setSelected(true);

        // Add all elements to the root AnchorPane
        fondoConfiguracionPartida.getChildren().addAll(
                topBanner, btnCancelarCambios, btnConfirmarCambios, playersLabel, tilesLabel,
                tilesChoiceBox, twoPlayersRadio, threePlayersRadio, fourPlayersRadio
        );

        // Crear una nueva ventana (Stage) modal
        ventanaConfiguracion = new Stage();

        // Establecer la modalidad para la ventana
        ventanaConfiguracion.initModality(Modality.APPLICATION_MODAL);

        // Definir la ventana padre para la modalidad
        ventanaConfiguracion.initOwner(fondo);

        // Título y contenido de la ventana modal
        ventanaConfiguracion.setTitle("Configuración partida");
        Scene scene = new Scene(fondoConfiguracionPartida, 640, 400);
        ventanaConfiguracion.setScene(scene);
    }

    /**
     * Muestra la ventana de configuración de la partida. La ventana es modal,
     * lo que significa que bloquea la interacción con otras ventanas hasta que
     * se cierre.
     */
    public void mostrarVentanaConfiguracion() {

        ventanaConfiguracion.setResizable(false);
        ventanaConfiguracion.showAndWait();  // showAndWait hace que sea modal
    }

    /**
     * Cierra la ventana de configuración de la partida.
     */
    public void cerrarVentanaConfiguracion() {
        ventanaConfiguracion.close();
    }

    /**
     * Carga la ventana modal de selección de avatares.
     */
    public void cargarAvatares() {
        ventanaAvatares = new Stage();

        // Crear el AnchorPane principal
        fondoAvatares = new AnchorPane();
        fondoAvatares.setStyle("-fx-background-color: D9D9D9;");
//        fondoAvatares.setPrefSize(656, 554);

        // Crear el GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setLayoutX(105);
        gridPane.setLayoutY(54);

        // Array de nombres de avatares
        String[] avatars = {
            "ave", "gato", "panda",
            "jaguar", "kiwi", "mariposa",
            "serpiente", "tortuga", "venado"
        };

        // Crear y añadir ImageViews al GridPane
        for (int i = 0; i < avatars.length; i++) {
            ImageView imageView = createImageView(avatars[i]);

            // Añadir evento de click
            final String avatarName = avatars[i];
//            imageView.setOnMouseClicked(event -> {
//                v.close();
//            });

            gridPane.add(imageView, i % 3, i / 3);
        }

        // Crear botón de cerrar
        Button closeButton = new Button("X");
        closeButton.setLayoutX(25);
        closeButton.setLayoutY(24);
        closeButton.setStyle("-fx-background-color: #2F1C1C; -fx-background-radius: 20;");
        closeButton.setTextFill(Color.WHITE);
        closeButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/RussoOne-Regular.ttf"), 30));
        closeButton.setOnAction(e -> ventanaAvatares.close());

        // Añadir elementos al AnchorPane
        fondoAvatares.getChildren().addAll(gridPane, closeButton);
        
        // Crear una nueva ventana (Stage) modal
        ventanaAvatares = new Stage();

        // Establecer la modalidad para la ventana
        ventanaAvatares.initModality(Modality.APPLICATION_MODAL);

        // Definir la ventana padre para la modalidad
        ventanaAvatares.initOwner(fondo);

        // Título y contenido de la ventana modal
        ventanaAvatares.setTitle("Selección de avatares");
        Scene scene = new Scene(fondoAvatares, 656, 554);
        ventanaAvatares.setScene(scene);
    }

    private ImageView createImageView(String avatarName) {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(135);
        imageView.setFitWidth(135);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);

        // Cargar la imagen
        Image image = new Image(getClass().getResourceAsStream("/avatar/" + avatarName + ".png"));
        imageView.setImage(image);

        // Añadir cursor de mano
        imageView.setCursor(Cursor.HAND);

        // Si es el panda, añadir efecto de ajuste de color
        if (avatarName.equals("panda")) {
            imageView.setEffect(new ColorAdjust());
        }

        return imageView;
    }

    /**
     * Muestra la ventana de selección de avatares. La ventana es modal, lo que
     * significa que bloquea la interacción con otras ventanas hasta que se
     * cierre.
     */
    public void mostrarVentanaAvatares() {
        ventanaAvatares.setResizable(false);
        ventanaAvatares.showAndWait();  // showAndWait hace que sea modal
    }

    //------------EVENTOS------------
    /**
     * Asigna el evento de clic para el botón de modo Offline.
     *
     * @param e el manejador de eventos que se ejecutará al hacer clic en el
     * botón.
     */
    public void mostrarConfiguracion(EventHandler<MouseEvent> e) {
        btnAjustes.setOnMouseClicked(e);
    }

    /**
     * Asigna un evento de clic para mostrar la ventana de selección de
     * avatares.
     *
     * @param e El manejador de eventos que se ejecutará al hacer clic en el
     * avatar.
     */
    public void mostrarAvatares(EventHandler<MouseEvent> e) {
        btnAvatar.setOnMouseClicked(e);//---------------------------Falta terminar----------
    }

    /**
     * Asigna un evento de clic y de salida del texto al campo de nombre de
     * usuario.
     *
     * @param e El manejador de eventos que se ejecutará al interactuar con el
     * campo de texto.
     */
    public void confirmarTexto(EventHandler<MouseEvent> e) {
        txtUsuario.setOnMouseExited(e);
        txtUsuario.setOnMouseClicked(e);
    }

    /**
     * Asigna un evento de clic para abandonar la partida.
     *
     * @param e El manejador de eventos que se ejecutará al hacer clic en el
     * botón de abandonar.
     */
    public void abandonarPartida(EventHandler<MouseEvent> e) {
        btnAbandonar.setOnMouseClicked(e);//---------------------------Falta terminar----------
        //Que se actualice en los demas jugadores
        //Si es el admin que se acabe la partida y saque a todos
    }

    /**
     * Asigna un evento de clic para iniciar la partida.
     *
     * @param e El manejador de eventos que se ejecutará al hacer clic en el
     * botón de iniciar.
     */
    public void iniciarPartida(EventHandler<MouseEvent> e) {
        btnIniciar.setOnMouseClicked(e);//---------------------------Falta terminar----------
        //Indicar cuantos llistos hay
        //Si ya acompletaron la sala y todos tienen nombre deberian estar listos todos
    }

    /**
     * Asigna un evento de clic para confirmar los cambios en la configuración
     * de la partida.
     *
     * @param e El manejador de eventos que se ejecutará al hacer clic en el
     * botón de confirmar cambios.
     */
    public void confirmarCambiosPartida(EventHandler<MouseEvent> e) {
        btnConfirmarCambios.setOnMouseClicked(e);
    }

    /**
     * Asigna un evento de clic para cancelar los cambios en la configuración de
     * la partida.
     *
     * @param e El manejador de eventos que se ejecutará al hacer clic en el
     * botón de cancelar cambios.
     */
    public void cancelarCambiosPartida(EventHandler<MouseEvent> e) {
        btnCancelarCambios.setOnMouseClicked(e);
    }

    //--------------Metodos Observer-----------------------
    /**
     * Actualiza el mensaje de aviso en la interfaz de usuario.
     */
//    @Override
    public void actualizarMensajeAviso() {
        String mensaje = modelo.getMensaje();
        if (mensaje != null) {
            lblMensaje.setText(modelo.getMensaje());
        }
//        txtUsuario.setText(modelo.devolverNombreUsuarioActual());
    }

    /**
     * Actualiza el encabezado del lobby. Muestra cuántos jugadores están
     * listos.
     */
//    @Override
    public void actualizarEncabezado() {
        lblEncabezado.setText(modelo.getEncabezadoLobby());//---------------------------Falta terminar----------
        //Aparezca cuantos esten listos
    }

    /**
     * Agrega un nuevo jugador al lobby.
     *
     * @param cuenta La cuenta del jugador a agregar.
     */
//    @Override
    public void agregarOtroJugador(CuentaDTO cuenta) {
//        colocarJugadorOnline(cuenta);
    }

    /**
     * Quita un jugador del lobby.
     *
     * @param cuenta La cuenta del jugador a quitar.
     */
//    @Override
    public void quitarOtroJugador(CuentaDTO cuenta) {
        AnchorPane panelJugador = modelo.obtenerPanelJugador(cuenta.getId());
        quitarJugadorOnline(panelJugador);
    }

    /**
     * Marca un jugador como listo en el lobby.
     *
     * @param cuenta La cuenta del jugador que está listo.
     */
//    @Override
    public void ponerListoJugador(CuentaDTO cuenta) {
        AnchorPane panelJugador = modelo.obtenerPanelJugador(cuenta.getId());
        ponerListoJugador(panelJugador);
    }

    /**
     * Marca un jugador como no listo en el lobby.
     *
     * @param cuenta La cuenta del jugador que no está listo.
     */
//    @Override
    public void quitarListoJugador(CuentaDTO cuenta) {
        AnchorPane panelJugador = modelo.obtenerPanelJugador(cuenta.getId());
        quitarListoJugador(panelJugador);
    }

    /**
     * Actualiza el avatar de un jugador en el lobby.
     *
     * @param cuenta La cuenta del jugador cuyo avatar será actualizado.
     */
//    @Override
    public void actualizarAvatarJugador(CuentaDTO cuenta) {
        AnchorPane panelJugador = modelo.obtenerPanelJugador(cuenta.getId());
//        actualizarAvatarJugador(panelJugador, cuenta.getAvatar());
    }

    /**
     * Actualiza el nombre de un jugador en el lobby.
     *
     * @param cuenta La cuenta del jugador cuyo nombre será actualizado.
     */
//    @Override
    public void actualizarNombreOtroJugador(CuentaDTO cuenta) {
        AnchorPane panelJugador = modelo.obtenerPanelJugador(cuenta.getId());
//        actualizarNombreOtroJugador(panelJugador, cuenta.getNombre());
    }
}
