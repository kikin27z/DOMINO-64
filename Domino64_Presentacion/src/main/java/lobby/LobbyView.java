package lobby;

import dtos.cuenta.CuentaDTO;
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
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentacion_observers.ObservadorLobby;

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
public class LobbyView implements ObservadorLobby {

    private final LobbyModel modelo;
    private AnchorPane panel;
    private Button btnAbandonar;
    private Button btnIniciar;
    private StackPane btnConfiguracion;
    private TextField txtUsuario;
    private HBox jugadoresContenedor;
    private Stage fondo;
    private Stage ventanaConfiguracion;
    private Stage ventanaAvatares;
    private AnchorPane fondoConfiguracionPartida;
    private Label lblEncabezado;
    private Label lblMensaje;
    private ImageView avatarUsuarioActual;
    private ImageView ajustesImg;
    private Button btnConfirmarCambios;
    private Button btnCancelarCambios;
    private AnchorPane fondoAvatares;

    
    /**
     * Constructor de la clase LobbyView. Inicializa el modelo asociado a esta
     * vista.
     *
     * @param modelo El modelo del lobby que contiene los datos y la lógica
     *               relacionada con la partida.
     */
    public LobbyView(LobbyModel modelo) {
        this.modelo = modelo;
    }

     /**
     * Inicia la escena de la pantalla de lobby.
     *
     * @param fondo El escenario principal donde se mostrará la pantalla del lobby.
     * @throws IOException Si ocurre un error durante la carga de los recursos.
     */
    public void iniciarEscena(Stage fondo) throws IOException {
        crearComponentes();
        Scene scene = new Scene(panel);
        this.fondo = fondo;
        fondo.setScene(scene);
        fondo.show();
        modelo.addObserver(this);
    }

    //------------GUI------------\\
     /**
     * Crea todos los componentes de la interfaz gráfica (botones, etiquetas, etc.)
     * y los añade al panel principal.
     */
    private void crearComponentes() {
        panel = new AnchorPane();
        panel.setMaxSize(1000, 700);
        panel.setPrefSize(1000, 700);
        panel.setStyle("-fx-background-color: #B2533E;");

        lblEncabezado = new Label(modelo.getEncabezadoLobby());
        lblEncabezado.setAlignment(javafx.geometry.Pos.CENTER);
        lblEncabezado.setLayoutX(60);
        lblEncabezado.setLayoutY(100);
        lblEncabezado.setPrefWidth(855);
        lblEncabezado.setTextFill(javafx.scene.paint.Color.web("#2f1c1c"));
        lblEncabezado.setFont(Font.font("Verdana Bold", 48));

        lblMensaje = new Label(modelo.getMensaje());
        lblMensaje.setAlignment(javafx.geometry.Pos.CENTER);
        lblMensaje.setLayoutX(72);
        lblMensaje.setLayoutY(496);
        lblMensaje.setPrefWidth(855);
        lblMensaje.setTextFill(javafx.scene.paint.Color.web("#2e1c1cb9"));
        lblMensaje.setFont(Font.font("Verdana Bold", 33));

        jugadoresContenedor = new HBox(18);
        jugadoresContenedor.setLayoutX(30);
        jugadoresContenedor.setLayoutY(216);
        jugadoresContenedor.setPrefSize(942, 278);

        colocarJugadorLocal(modelo.getCuentaActual());

        agregarBotones();

        Label codeLabel = new Label("xxx-xxx");
        codeLabel.setAlignment(javafx.geometry.Pos.CENTER);
        codeLabel.setLayoutX(30);
        codeLabel.setLayoutY(46);
        codeLabel.setPrefSize(192, 30);
        codeLabel.setStyle("-fx-background-color: #D9D9D9; -fx-background-radius: 20;");
        codeLabel.setTextFill(javafx.scene.paint.Color.web("#2f1c1c"));
        codeLabel.setFont(Font.font("Russo One", 20));

        Label codeTitle = new Label("Código de la partida");
        codeTitle.setAlignment(javafx.geometry.Pos.CENTER);
        codeTitle.setLayoutX(30);
        codeTitle.setLayoutY(21);
        codeTitle.setPrefSize(192, 30);
        codeTitle.setTextFill(javafx.scene.paint.Color.web("#2f1c1c"));
        codeTitle.setFont(Font.font("Russo One", 15));

        panel.getChildren().addAll(lblEncabezado, btnAbandonar, lblMensaje, jugadoresContenedor,
                btnIniciar, btnConfiguracion, codeLabel, codeTitle);

        cargarConfiguracion();
        cargarAvatares();
    }

     /**
     * Agrega los botones de "Iniciar partida" y "Abandonar partida" al panel.
     */
    private void agregarBotones() {
        btnIniciar = new Button("Iniciar partida");
        btnIniciar.setLayoutX(507);
        btnIniciar.setLayoutY(562);
        btnIniciar.setMaxHeight(70);
        btnIniciar.setMinHeight(70);
        btnIniciar.setPrefWidth(420);
        btnIniciar.setStyle("-fx-background-color: #D9D9D9; -fx-background-radius: 20;");
        btnIniciar.setTextFill(javafx.scene.paint.Color.web("#2f1c1c"));
        btnIniciar.setFont(Font.font("Russo One", 37));
        btnIniciar.setCursor(Cursor.HAND);

        btnAbandonar = new Button("Abandonar partida");
        btnAbandonar.setLayoutX(49);
        btnAbandonar.setLayoutY(562);
        btnAbandonar.setMaxHeight(70);
        btnAbandonar.setMinHeight(70);
        btnAbandonar.setPrefWidth(420);
        btnAbandonar.setStyle("-fx-background-color: #2F1C1C; -fx-background-radius: 20;");
        btnAbandonar.setTextFill(javafx.scene.paint.Color.WHITE);
        btnAbandonar.setFont(Font.font("Russo One", 37));
        btnAbandonar.setCursor(Cursor.HAND);

        crearBtnConfiguracion();
    }

    /**
     * Coloca el panel del jugador local en la interfaz del lobby.
     *
     * @param cuenta La cuenta del usuario local que se añadirá al panel.
     */
    private void colocarJugadorLocal(CuentaDTO cuenta) {
        AnchorPane panelJugador = new AnchorPane();
        panelJugador.setPrefSize(222, 278);
        avatarUsuarioActual = ponerAvatar(cuenta.getAvatar());
        avatarUsuarioActual.setCursor(Cursor.HAND);

        txtUsuario = new TextField();
        txtUsuario.setAlignment(javafx.geometry.Pos.CENTER);
        txtUsuario.setLayoutX(15);
        txtUsuario.setLayoutY(180);
        txtUsuario.setPrefSize(192, 40);
        txtUsuario.setPromptText("Username");
        txtUsuario.setStyle("-fx-background-radius: 20;");
        txtUsuario.setFont(Font.font("Verdana", 20));
        txtUsuario.setCursor(Cursor.HAND);

        panelJugador.getChildren().addAll(txtUsuario, avatarUsuarioActual);
        jugadoresContenedor.getChildren().add(panelJugador);
        modelo.agregarPanelJugador(modelo.obtenerIdActual(), panelJugador);
    }

    /**
     * Coloca el panel de un jugador en línea en la interfaz del lobby.
     *
     * @param cuenta La cuenta del jugador en línea que se añadirá al panel.
     */
    private void colocarJugadorOnline(CuentaDTO cuenta) {
        AnchorPane panelJugador = new AnchorPane();
        panelJugador.setPrefSize(222, 278);

        ImageView avatarView = ponerAvatar(cuenta.getAvatar());

        Label lblUsuario = new Label("...");
        lblUsuario.setAlignment(javafx.geometry.Pos.CENTER);
        lblUsuario.setLayoutX(15);
        lblUsuario.setLayoutY(180);
        lblUsuario.setPrefSize(192, 40);
        lblUsuario.setStyle("-fx-background-color: #D9D9D9; -fx-background-radius: 20; -fx-padding: 6 4;");
        lblUsuario.setTextFill(javafx.scene.paint.Color.web("#2f1c1c"));
        lblUsuario.setFont(Font.font("Russo One", 20));
        lblUsuario.setId("nombre");

        panelJugador.getChildren().addAll(avatarView, lblUsuario);
        jugadoresContenedor.getChildren().add(panelJugador);
        modelo.getPanelesJugadores().put(cuenta.getId(), panelJugador);
    }

    /**
     * Coloca el avatar correspondiente dependiendo cual se envie como parámetro.
     * @param url Url del avatar a colocar.
     * @return Devuelve la imagen del avatar deseado.
     */
    private ImageView ponerAvatar(String url) {
        ImageView avatarView = new ImageView(new Image(getClass().getResourceAsStream(url)));
        avatarView.setFitHeight(150);
        avatarView.setFitWidth(150);
        avatarView.setLayoutX(41);
        avatarView.setLayoutY(15);
        avatarView.setId("avatar");
        return avatarView;
    }

    /**
     * Crea visualmente el boton de configuración de partida.
     */
    private void crearBtnConfiguracion() {
        btnConfiguracion = new StackPane();
        btnConfiguracion.setLayoutX(874);
        btnConfiguracion.setLayoutY(46);
        btnConfiguracion.setPrefSize(60, 60);

        Button settingsButton = new Button();
        settingsButton.setMaxSize(60, 60);
        settingsButton.setMinSize(60, 60);
        settingsButton.setStyle("-fx-border-color: #000000; -fx-border-radius: 100; -fx-background-radius: 100;");
        settingsButton.setEffect(new DropShadow());

        ajustesImg = new ImageView(new Image(getClass().getResourceAsStream("/images/iconAjustes.png")));
        ajustesImg.setFitHeight(40);
        ajustesImg.setFitWidth(40);

        btnConfiguracion.getChildren().addAll(settingsButton, ajustesImg);
        btnConfiguracion.setCursor(Cursor.HAND);

    }

    //------------GUI actualizar con modelo-------------
    /**
     * Quita el panel del jugador enviado como parámetro.
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
    * Muestra que un jugador está listo añadiendo un icono al panel del jugador.
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
    * @param panelJugador El panel del jugador al que se le eliminará el icono de listo.
    */
    private void quitarListoJugador(AnchorPane panelJugador) {
        Node nodoAQuitar = (ImageView) panelJugador.lookup("#listo");
        panelJugador.getChildren().remove(nodoAQuitar);
    }

    /**
    * Pone los avatares en la ventana de selección de avatares, inhabilitando 
    * aquellos que ya han sido seleccionados.
    * 
    * @param seleccionados Lista de índices de avatares seleccionados.
    */
    private void ponerAvataresVentana(List<Integer> seleccionados) {
        String[] urlAvatares = modelo.getAvatares();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setLayoutX(105);
        gridPane.setLayoutY(54);

        Image urlAvatar;
        int k = 0;
        // Create and add ImageViews to the GridPane
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                urlAvatar = new Image(getClass().getResourceAsStream(urlAvatares[k]));
                ImageView imageView = new ImageView(urlAvatar);
                imageView.setFitHeight(135);
                imageView.setFitWidth(135);
                imageView.setPreserveRatio(true);

                //ac
                if(seleccionados.contains(k)){
                    inhabilitarAvatar(imageView);
                }else{
                    activarAvatar(imageView);
                }
                k++;
                gridPane.add(imageView, col, row);
            }
        }
    }

    
    /**
    * Activa un avatar en la ventana de selección de avatares, haciéndolo seleccionable.
    * 
    * @param avatar El ImageView del avatar a activar.
    */
    private void activarAvatar(ImageView avatar){
        avatar.setCursor(Cursor.HAND);
//        avatar.setOnMouseClicked(value);
    }
    /**
    * Inhabilita un avatar en la ventana de selección de avatares, haciéndolo no seleccionable.
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
    * Muestra la ventana de configuración de la partida.
    * La ventana es modal, lo que significa que bloquea la interacción con otras ventanas hasta que se cierre.
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
    private void cargarAvatares() {
        fondoAvatares = new AnchorPane();
        fondoAvatares.setPrefHeight(554);
        fondoAvatares.setPrefWidth(656);
        fondoAvatares.setStyle("-fx-background-color: D9D9D9;");

        // Create close button
        Button closeButton = new Button("X");
        closeButton.setLayoutX(25);
        closeButton.setLayoutY(24);
        closeButton.setStyle("-fx-background-color: #2F1C1C; -fx-background-radius: 20;");
        closeButton.setTextFill(javafx.scene.paint.Color.WHITE);
        closeButton.setFont(new Font("Russo One", 30));

        // Add GridPane and Button to the root AnchorPane
        fondoAvatares.getChildren().add(closeButton);
        // Crear una nueva ventana (Stage) modal
        ventanaAvatares = new Stage();

        // Establecer la modalidad para la ventana
        ventanaAvatares.initModality(Modality.APPLICATION_MODAL);

        // Definir la ventana padre para la modalidad
        ventanaAvatares.initOwner(fondo);

        // Título y contenido de la ventana modal
        ventanaAvatares.setTitle("Configuración partida");
        Scene scene = new Scene(fondoAvatares, 656, 554);
        ventanaAvatares.setScene(scene);
    }

    
    /**
    * Muestra la ventana de selección de avatares.
    * La ventana es modal, lo que significa que bloquea la interacción con otras ventanas hasta que se cierre.
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
        ajustesImg.setOnMouseClicked(e);
    }

    /**
    * Asigna un evento de clic para mostrar la ventana de selección de avatares.
    * 
    * @param e El manejador de eventos que se ejecutará al hacer clic en el avatar.
    */
    public void mostrarAvatares(EventHandler<MouseEvent> e) {
        avatarUsuarioActual.setOnMouseClicked(e);//---------------------------Falta terminar----------
        //Que valide que avatares ya se usaron
        //que muestre como seleccionables lo disponibles
    }

    /**
    * Asigna un evento de clic y de salida del texto al campo de nombre de usuario.
    * 
    * @param e El manejador de eventos que se ejecutará al interactuar con el campo de texto.
    */
    public void confirmarTexto(EventHandler<MouseEvent> e) {
        txtUsuario.setOnMouseExited(e);
        txtUsuario.setOnMouseClicked(e);
    }

    /**
    * Asigna un evento de clic para abandonar la partida.
    * 
    * @param e El manejador de eventos que se ejecutará al hacer clic en el botón de abandonar.
    */
    public void abandonarPartida(EventHandler<MouseEvent> e) {
        btnAbandonar.setOnMouseClicked(e);//---------------------------Falta terminar----------
        //Que se actualice en los demas jugadores
        //Si es el admin que se acabe la partida y saque a todos
    }

    /**
    * Asigna un evento de clic para iniciar la partida.
    * 
    * @param e El manejador de eventos que se ejecutará al hacer clic en el botón de iniciar.
    */
    public void iniciarPartida(EventHandler<MouseEvent> e) {
        btnIniciar.setOnMouseClicked(e);//---------------------------Falta terminar----------
        //Indicar cuantos llistos hay
        //Si ya acompletaron la sala y todos tienen nombre deberian estar listos todos
    }

    /**
    * Asigna un evento de clic para confirmar los cambios en la configuración de la partida.
    * 
    * @param e El manejador de eventos que se ejecutará al hacer clic en el botón de confirmar cambios.
    */
    public void confirmarCambiosPartida(EventHandler<MouseEvent> e) {
        btnConfirmarCambios.setOnMouseClicked(e);
    }

    /**
    * Asigna un evento de clic para cancelar los cambios en la configuración de la partida.
    * 
    * @param e El manejador de eventos que se ejecutará al hacer clic en el botón de cancelar cambios.
    */
    public void cancelarCambiosPartida(EventHandler<MouseEvent> e) {
        btnCancelarCambios.setOnMouseClicked(e);
    }

    //--------------Metodos Observer-----------------------
    /**
    * Actualiza el mensaje de aviso en la interfaz de usuario.
    */
    @Override
    public void actualizarMensajeAviso() {
        String mensaje = modelo.getMensaje();
        if (mensaje != null) {
            lblMensaje.setText(modelo.getMensaje());
        }
        txtUsuario.setText(modelo.devolverNombreUsuarioActual());
    }

    /**
    * Actualiza el encabezado del lobby.
    * Muestra cuántos jugadores están listos.
    */
    @Override
    public void actualizarEncabezado() {
        lblEncabezado.setText(modelo.getEncabezadoLobby());//---------------------------Falta terminar----------
        //Aparezca cuantos esten listos
    }

    /**
    * Agrega un nuevo jugador al lobby.
    * 
    * @param cuenta La cuenta del jugador a agregar.
    */
    @Override
    public void agregarOtroJugador(CuentaDTO cuenta) {
        colocarJugadorOnline(cuenta);
    }

    /**
    * Quita un jugador del lobby.
    * 
    * @param cuenta La cuenta del jugador a quitar.
    */
    @Override
    public void quitarOtroJugador(CuentaDTO cuenta) {
        AnchorPane panelJugador = modelo.obtenerPanelJugador(cuenta.getId());
        quitarJugadorOnline(panelJugador);
    }

    /**
    * Marca un jugador como listo en el lobby.
    * 
    * @param cuenta La cuenta del jugador que está listo.
    */
    @Override
    public void ponerListoJugador(CuentaDTO cuenta) {
        AnchorPane panelJugador = modelo.obtenerPanelJugador(cuenta.getId());
        ponerListoJugador(panelJugador);
    }

    /**
    * Marca un jugador como no listo en el lobby.
    * 
    * @param cuenta La cuenta del jugador que no está listo.
    */
    @Override
    public void quitarListoJugador(CuentaDTO cuenta) {
        AnchorPane panelJugador = modelo.obtenerPanelJugador(cuenta.getId());
        quitarListoJugador(panelJugador);
    }

    /**
    * Actualiza el avatar de un jugador en el lobby.
    * 
    * @param cuenta La cuenta del jugador cuyo avatar será actualizado.
    */
    @Override
    public void actualizarAvatarJugador(CuentaDTO cuenta) {
        AnchorPane panelJugador = modelo.obtenerPanelJugador(cuenta.getId());
        actualizarAvatarJugador(panelJugador, cuenta.getAvatar());
    }

    /**
    * Actualiza el nombre de un jugador en el lobby.
    * 
    * @param cuenta La cuenta del jugador cuyo nombre será actualizado.
    */
    @Override
    public void actualizarNombreOtroJugador(CuentaDTO cuenta) {
        AnchorPane panelJugador = modelo.obtenerPanelJugador(cuenta.getId());
        actualizarNombreOtroJugador(panelJugador, cuenta.getNombre());
    }

    @Override
    public void update(LobbyModel observable, Object ... context) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
