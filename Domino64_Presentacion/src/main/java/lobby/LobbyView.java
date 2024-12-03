package lobby;

import entidadesDTO.AvatarDTO;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import eventosLobby.ObserverLobbyMVC;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import observer.Observable;

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
public class LobbyView extends Observable<EventoLobbyMVC> implements ObserverLobbyMVC {

    private final LobbyModel modelo;
    private Stage fondo;
    private AnchorPane panel;
    private StackPane btnAjustes;
    private TextField txtUsuario;
    private AnchorPane fondoConfiguracionPartida;
    private ChoiceBox<String> tilesChoiceBox;
    private Label lblJugadoresListos;
    private Label lblMensaje;
    private ImageView ajustesImg;
    private List<ImageView> avatares;
    private List<AnchorPane> panelesJugadores;
    private ImageView btnAvatar;
    private Button btnConfirmarCambios;
    private Button btnCancelarCambios;
    private Button btnAbandonar;
    private Button btnIniciar;
    private Button btnCerrarAvatares;
    private HBox jugadoresContainer;
    private Stage ventanaConfiguracion;
    private Stage ventanaAvatares;
    private AnchorPane fondoAvatares;
    private Label lblCodigo;
    private EventHandler seleccionAvatar;

    /**
     * Constructor de la clase LobbyView. Inicializa el modelo asociado a esta
     * vista.
     *
     * @param modelo El modelo del lobby que contiene los datos y la lógica
     * relacionada con la partida.
     */
    public LobbyView(LobbyModel modelo) {
        this.modelo = modelo;
        modelo.agregarObserver(this);
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
        panelesJugadores = new ArrayList<>();
        Scene scene = new Scene(panel);
        this.fondo = fondo;
        fondo.setScene(scene);
        fondo.show();
        cargarConfiguracion();
        cargarAvatares();
        //crearJugadores();
        System.out.println("se acabo el iniciar escena");
    }

    //------------GUI------------\\
    private void inicializarVista() {
        // Configuración básica del AnchorPane
        panel = new AnchorPane();
        panel.setMaxSize(1000, 700);
        panel.setPrefSize(1000, 700);
        panel.setStyle("-fx-background-color: #B2533E;");

        // Label para jugadores listos
        int jugadoresListos = modelo.getJugadoresListos().size();
        int cantidadJugadores = modelo.getCuentas().size();
        String str = jugadoresListos + "/" + cantidadJugadores + " listos para iniciar";

        lblJugadoresListos = new Label(str);
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
        btnIniciar = new Button("Estoy listo");
        btnIniciar.setFont(Font.font("Russo One", 37));
        btnIniciar.setStyle("-fx-background-color: #D9D9D9; -fx-background-radius: 20;");
        btnIniciar.setTextFill(Color.valueOf("#2f1c1c"));
        btnIniciar.setPrefWidth(420);
        btnIniciar.setMaxHeight(70);
        btnIniciar.setCursor(Cursor.HAND);
        if (modelo.getCuentas().size() == 1) {
            btnIniciar.setDisable(true);
        }
        AnchorPane.setLeftAnchor(btnIniciar, 507.0);
        AnchorPane.setTopAnchor(btnIniciar, 562.0);
        panel.getChildren().add(btnIniciar);

        // Botón de ajustes
        
        if(modelo.getCuentaActual().esAdmin()){
            btnAjustes = crearBotonAjustes();
            AnchorPane.setRightAnchor(btnAjustes, 66.0);
            AnchorPane.setTopAnchor(btnAjustes, 46.0);
            panel.getChildren().add(btnAjustes);
        }
            
        cargarBtnModal();
        cargarCodigoPartida(modelo.getLobbyDTO());
    }

    private void cargarCodigoPartida(LobbyDTO lobby) {
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

        lblCodigo = new Label(lobby.getCodigo());
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

    protected void crearJugadores() {
        List<CuentaDTO> cuentas = modelo.getCuentas();
        Map<String, AnchorPane> paneles = new HashMap<>();
        for (var cuenta : cuentas) {
            if (cuenta.getIdCadena().equalsIgnoreCase(modelo.obtenerIdCuentaActual())) {
                paneles.put(cuenta.getIdCadena(), ponerJugadorActual(cuenta));
            } else {
                paneles.put(cuenta.getIdCadena(), ponerJugadorOtro(cuenta));
            }
        }
    }

    private AnchorPane ponerJugadorActual(CuentaDTO cuenta) {
//        boolean listo = modelo.getJugadoresListos().contains(cuenta);
        AnchorPane panelJugadorAct = crearPanelJugadorActual(cuenta);
        panelesJugadores.add(panelJugadorAct);
        jugadoresContainer.getChildren().add(panelJugadorAct);
        EventoLobbyMVC evento = new EventoLobbyMVC(TipoLobbyMVC.NUEVO_PANEL_JUGADOR);
        evento.setNodo(panelJugadorAct);
        notifyObservers(evento.getTipo(), evento);
        return panelJugadorAct;
    }

    private AnchorPane ponerJugadorOtro(CuentaDTO cuenta) {
        boolean listo = modelo.getJugadoresListos().contains(cuenta);
        AnchorPane panelJugadorAct = crearPanelOtroJugador(cuenta, listo);
        panelesJugadores.add(panelJugadorAct);
        jugadoresContainer.getChildren().add(panelJugadorAct);
        
        EventoLobbyMVC evento = new EventoLobbyMVC(TipoLobbyMVC.NUEVO_PANEL_JUGADOR);
        evento.setNodo(panelJugadorAct);
        notifyObservers(evento.getTipo(), evento);
        return panelJugadorAct;
    }

    private ImageView pintarIconoListo() {

        ImageView iconoListo = new ImageView(new Image(getClass().getResourceAsStream("/images/listo.png")));
        iconoListo.setId("iconoListo");
        iconoListo.setFitHeight(150);
        iconoListo.setFitWidth(40);
        iconoListo.setPreserveRatio(true);
        AnchorPane.setLeftAnchor(iconoListo, 148.0);
        AnchorPane.setTopAnchor(iconoListo, 8.0);

        //panelJugador.getChildren().add(iconoListo);
        return iconoListo;
    }

    private AnchorPane crearPanelJugadorActual(CuentaDTO cuenta) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId(cuenta.getIdCadena());
        anchorPane.setMaxSize(222, 278);
        anchorPane.setMinSize(222, 278);
        anchorPane.setPrefSize(222, 278);

        // Avatar del jugador
        btnAvatar.setImage(new Image(getClass().getResourceAsStream(cuenta.getAvatar().getUrl())));
        btnAvatar.setId(cuenta.getAvatar().getAnimal());
        AnchorPane.setLeftAnchor(btnAvatar, 41.0);
        AnchorPane.setTopAnchor(btnAvatar, 15.0);

//        btnAvatar = cambiarFiltroAvatar(cuenta, btnAvatar);
        // Icono de listo
//        ImageView iconoListo = null;
//        if (listo) {
//            iconoListo = pintarIconoListo();
//        }
        // Nombre del jugador
        Label lblNombre = new Label(cuenta.getUsername());
        lblNombre.setId("nombre");
        lblNombre.setFont(Font.font("Russo One", 20));
        lblNombre.setTextFill(Color.valueOf("#2f1c1c"));
        lblNombre.setAlignment(Pos.CENTER);
        lblNombre.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 20; -fx-padding: 6 4;");
        lblNombre.setMaxWidth(192);
        lblNombre.setMinWidth(192);
        lblNombre.setPrefWidth(192);
        AnchorPane.setLeftAnchor(lblNombre, 15.0);
        AnchorPane.setTopAnchor(lblNombre, 180.0);

        anchorPane.getChildren().addAll(btnAvatar, lblNombre);

        return anchorPane;
    }

    private AnchorPane crearPanelOtroJugador(CuentaDTO cuenta, boolean listo) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId(cuenta.getIdCadena());
        anchorPane.setMaxSize(222, 278);
        anchorPane.setMinSize(222, 278);
        anchorPane.setPrefSize(222, 278);

        // Avatar del jugador
        ImageView avatar = new ImageView(new Image(getClass().getResourceAsStream(cuenta.getAvatar().getUrl())));
        avatar.setId(cuenta.getAvatar().getAnimal());
        avatar.setFitHeight(150);
        avatar.setFitWidth(200);
        avatar.setPreserveRatio(true);
        AnchorPane.setLeftAnchor(avatar, 41.0);
        AnchorPane.setTopAnchor(avatar, 15.0);

        //cambiarFiltroAvatar(cuenta, avatar);
        // Icono de listo
        ImageView iconoListo = null;
        if (listo) {
            iconoListo = pintarIconoListo();
        }

        // Nombre del jugador
        //CuentaDTO jugador = cuenta;
        Label lblNombre = new Label(cuenta.getUsername());
        lblNombre.setId("nombre");
        lblNombre.setFont(Font.font("Russo One", 20));
        lblNombre.setTextFill(Color.valueOf("#2f1c1c"));
        lblNombre.setAlignment(Pos.CENTER);
        lblNombre.setStyle("-fx-background-color: #D9D9D9; -fx-background-radius: 20; -fx-padding: 6 4;");
        lblNombre.setMaxWidth(192);
        lblNombre.setMinWidth(192);
        lblNombre.setPrefWidth(192);
        AnchorPane.setLeftAnchor(lblNombre, 15.0);
        AnchorPane.setTopAnchor(lblNombre, 180.0);

        anchorPane.getChildren().addAll(avatar, lblNombre);

        return anchorPane;
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

    private void removerPanelJugador(CuentaDTO cuenta) {
        AnchorPane panelExJugador = modelo.obtenerPanelJugador(cuenta.getIdCadena());
        panelesJugadores.remove(panelExJugador);
        jugadoresContainer.getChildren().remove(panelExJugador);
//        jugadoresContainer.getChildren().removeIf(p -> p.getId().equals(cuenta.getIdCadena()));
    }

    //--------------------------------------------------------Modal windows--------------------------------------------------------
    private void cargarBtnModal() {
        btnAvatar = new ImageView();
        btnAvatar.setFitHeight(150);
        btnAvatar.setFitWidth(200);
        btnAvatar.setPreserveRatio(true);

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

        // Crear botón de cerrar
        btnCerrarAvatares = new Button("X");
        btnCerrarAvatares.setLayoutX(25);
        btnCerrarAvatares.setLayoutY(24);
        btnCerrarAvatares.setStyle("-fx-background-color: #2F1C1C; -fx-background-radius: 20;");
        btnCerrarAvatares.setTextFill(Color.WHITE);
    }

    public ImageView cambiarFiltroAvatar(CuentaDTO cuenta, ImageView imgView){
        Node node = fondoAvatares.getChildren().getFirst();
        String animalAvatar = cuenta.getAvatar().getAnimal();
        if (node != null && node instanceof GridPane gridP) {
            FilteredList<Node> listAv = gridP.getChildren().filtered(avatar -> avatar.getId().equals(animalAvatar));
            ImageView avatar = (ImageView) listAv.getFirst();
            return setEffect(avatar);
        }
        return null;
        
    }
    protected void removeEffect(ImageView view) {
        if (view.getEffect() != null) {
            view.setEffect(null);
        }
    }

    protected ImageView setEffect(ImageView imgView) {
        if (imgView.getEffect() == null) {
            ColorAdjust filtro = new ColorAdjust();
            filtro.setSaturation(-1);
            imgView.setEffect(filtro);
        }
        return imgView;
    }

    /**
     * Carga la ventana modal de selección de avatares.
     */
    public void cargarAvatares() {
        avatares = new ArrayList<>();
        fondoAvatares = new AnchorPane();
        fondoAvatares.setStyle("-fx-background-color: D9D9D9;");

        // Crear el GridPane
        GridPane gridPane = new GridPane();
        gridPane.setId("gridAvatares");
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
            ImageView imageView = crearIconoAvatar(avatars[i]);
            imageView.setOnMouseClicked(seleccionarAvatar(avatars[i], imageView));
            avatares.add(imageView);
            // Añadir evento de click
            final String avatarName = avatars[i];
//            imageView.setOnMouseClicked(event -> {
//                v.close();
//            });

            gridPane.add(imageView, i % 3, i / 3);

        }

        // Añadir elementos al AnchorPane
        fondoAvatares.getChildren().addAll(gridPane, btnCerrarAvatares);

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

    private ImageView crearIconoAvatar(String avatarName) {
        ImageView imageView = new ImageView();
        imageView.setId(avatarName);
        imageView.setFitHeight(135);
        imageView.setFitWidth(135);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);

        // Cargar la imagen
        Image image = new Image(getClass().getResourceAsStream("/avatar/" + avatarName + ".png"));
        imageView.setImage(image);

        // Si es el panda, añadir efecto de ajuste de color
        if (avatarName.equals("panda")) {
            imageView.setEffect(new ColorAdjust());
        }

        AvatarDTO avatarJugActual;
        for (CuentaDTO cuenta : modelo.getCuentas()) {
            avatarJugActual = cuenta.getAvatar();
            if (avatarName.equals(avatarJugActual.getAnimal())) {
                imageView = setEffect(imageView);
                break;
            }
        }
        imageView.setOnMouseClicked(seleccionarAvatar(avatarName, imageView));
        // Añadir cursor de mano
        imageView.setCursor(Cursor.HAND);

        return imageView;
    }

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
        tilesChoiceBox = new ChoiceBox<>();
        tilesChoiceBox.setLayoutX(244);
        tilesChoiceBox.setLayoutY(261);
        tilesChoiceBox.setPrefWidth(150);
        tilesChoiceBox.setStyle("-fx-background-color: #FFF;");
        tilesChoiceBox.setCursor(Cursor.HAND);
        tilesChoiceBox.getItems().addAll("2", "3", "4", "5", "6", "7");
//        tilesChoiceBox.setValue(String.valueOf(modelo.getCantidadFichas()));

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
        cargarConfiguracion();
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
     * Cierra la ventana de configuración de la partida.
     */
    public void cerrarVentanaAvatares() {
        ventanaAvatares.close();
    }

    /**
     * Muestra la ventana de selección de avatares. La ventana es modal, lo que
     * significa que bloquea la interacción con otras ventanas hasta que se
     * cierre.
     */
    public void mostrarVentanaAvatares() {
        cargarAvatares();
        ventanaAvatares.setResizable(false);
        ventanaAvatares.showAndWait();  // showAndWait hace que sea modal
    }

    //-------------------------------------EVENTOS Control-------------------------------------------------
    public void abandonarPartida(EventHandler<MouseEvent> e) {
        btnAbandonar.setOnMouseClicked(e);//---------------------------Falta terminar----------
        //Que se actualice en los demas jugadores
        //Si es el admin que se acabe la partida y saque a todos
    }

    public void iniciarPartida(EventHandler<MouseEvent> e) {
        btnIniciar.setOnMouseClicked(e);//---------------------------Falta terminar----------
    }

    public void mostrarAvatares(EventHandler<MouseEvent> e) {
        btnAvatar.setOnMouseClicked(e);
    }

    public void cerrarAvatares(EventHandler<MouseEvent> e) {
        btnCerrarAvatares.setOnMouseClicked(e);
    }

    public void mostrarConfiguracion(EventHandler<MouseEvent> e) {
        if(modelo.getCuentaActual().esAdmin())
            btnAjustes.setOnMouseClicked(e);
    }

    public void confirmarCambiosPartida(EventHandler<MouseEvent> e) {
        btnConfirmarCambios.setOnMouseClicked(e);
    }

    protected int getChoiceBoxSelected() {
        return Integer.parseInt(tilesChoiceBox.getValue());
    }

    public void cancelarCambiosPartida(EventHandler<MouseEvent> e) {
        btnCancelarCambios.setOnMouseClicked(e);
    }

    private EventHandler<MouseEvent> seleccionarAvatar(String nombreAvatar, ImageView avatar) {
        EventHandler<MouseEvent> handler = (MouseEvent t) -> {
            System.out.println("se selecciono el avatar: "+nombreAvatar);
            System.out.println("efecto : "+avatar.getEffect());
            if(avatar.getEffect() == null || nombreAvatar.equals("panda")){
                EventoLobbyMVC evento = new EventoLobbyMVC(TipoLobbyMVC.CAMBIAR_AVATAR);
                evento.setNodo(avatar);
                notifyObservers(evento.getTipo(), evento);
            }
            
        };
        return handler;
    }

    //----------------------Eventos de Modelo------------------------------------
    private AnchorPane actualizarPanel(AnchorPane pane, AvatarDTO avatarNuevo) {
        FilteredList list = pane.getChildren().filtered(node -> node.getId().equals("iconoListo"));
        ImageView iconoListo = null;
        if(list != null && !list.isEmpty()){
            iconoListo= (ImageView)list.get(0);
        }
        ObservableList<Node> nodes = pane.getChildren();
        Image imgAvatar = new Image(avatarNuevo.getUrl());
        ImageView avatar = ((ImageView) nodes.get(0));
        avatar.setImage(imgAvatar);
        Label nombre = (Label) nodes.getLast();
        nombre.setText(avatarNuevo.getNombre());

        pane.getChildren().clear();

        if (iconoListo != null) {
            pane.getChildren().addAll(avatar, iconoListo, nombre);
        } else {
            pane.getChildren().addAll(avatar, nombre);
        }

        return pane;
    }

    private void cambiarAvatar(AvatarDTO avatarDTO, boolean agregarEfecto) {
        for (ImageView avatar : avatares) {
            if (avatar.getId().equals(avatarDTO.getAnimal())) {
                if (agregarEfecto) {
                    setEffect(avatar);
                } else {
                    removeEffect(avatar);
                }
                break;
            }
        }
//        FilteredList<Node> nodes = fondoAvatares.getChildren().filtered(node -> node.getId().equals("grid"));
//        if(nodes != null){
//            GridPane grid = (GridPane)nodes.get(0);
//            ImageView avatar = (ImageView)grid.getChildren().filtered(node -> node.getId().equals(nombreAnimal)).get(0);
//            int colIndex = GridPane.getColumnIndex(avatar);
//            int rowIndex = GridPane.getRowIndex(avatar);
//            grid.getChildren().removeIf(node -> node.getId().equals(nombreAnimal));
//            grid.add(avatar, colIndex, rowIndex);
//            
//            fondoAvatares.getChildren().removeAll();
//            fondoAvatares.getChildren().addAll(grid, btnCerrarAvatares);
//        }
    }

    @Override
    public void actualizarAvatarCuenta(CuentaDTO cuenta) {
        AnchorPane pane = modelo.obtenerPanelJugador(cuenta.getIdCadena());
        
        ImageView iconoAv = (ImageView)pane.getChildren().get(0);
        
        String idAvatar = iconoAv.getId();
        cambiarAvatar(modelo.getAvatarPorAnimal(idAvatar), false);
        cambiarAvatar(cuenta.getAvatar(), true);
//        ObservableList<Node> nodes = pane.getChildren().filtered(node -> node.getId().equals("avatar"));
        AnchorPane panelAct = actualizarPanel(pane, cuenta.getAvatar());
        if(panelAct != null){
            int index = jugadoresContainer.getChildren().indexOf(pane);
            //jugadoresContainer.getChildren().remove(pane);
            jugadoresContainer.getChildren().set(index,panelAct);
        }
        else{
            System.out.println("nmo se pudo");
        }
    }

    
    
    @Override
    public void actualizarNuevoJugador(CuentaDTO cuenta) {
        AnchorPane nuevoPanel = ponerJugadorOtro(cuenta);
        cambiarAvatar(cuenta.getAvatar(), true);
        actualizarEncabezado(modelo.ACTUALIZACION_CANTIDAD_JUGADORES, true);
        btnIniciar.setDisable(false);
    }

    @Override
    public void actualizarQuitarCuenta(CuentaDTO cuenta) {
        removerPanelJugador(cuenta);
        if (modelo.getCuentas().size() == 1) {
            btnIniciar.setDisable(true);
        }
        cambiarAvatar(cuenta.getAvatar(), false);
        actualizarEncabezado(modelo.ACTUALIZACION_CANTIDAD_JUGADORES, false);
    }

    @Override
    public void actualizarCuentaLista(CuentaDTO cuenta) {
        ImageView icono = pintarIconoListo();
        actualizarEncabezado(modelo.ACTUALIZACION_JUGADORES_LISTOS, true);
        AnchorPane pane = modelo.obtenerPanelJugador(cuenta.getIdCadena());
        pane.getChildren().add(icono);
        btnIniciar.setText("No listo");

    }

    @Override
    public void actualizarCuentaNoLista(CuentaDTO cuenta) {
        actualizarEncabezado(modelo.ACTUALIZACION_JUGADORES_LISTOS, false);
        AnchorPane pane = modelo.obtenerPanelJugador(cuenta.getIdCadena());
        pane.getChildren().removeIf(node -> node.getId().equals("iconoListo"));
        btnIniciar.setText("Estoy listo");
    }

    //--------------------Getters-------------------------
    public Button getBtnAbandonar() {
        return btnAbandonar;
    }

    public Button getBtnIniciar() {
        return btnIniciar;
    }

    public List<ImageView> getAvatares() {
        return avatares;
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
    public void actualizarEncabezado(int tipoActualizacion, boolean agregar) {
        char numJugadoresAnt = lblJugadoresListos.getText().charAt(tipoActualizacion);
        int num = Integer.parseInt(String.valueOf(numJugadoresAnt));
        char numNuevo;
        if (agregar) {
            numNuevo = String.valueOf(num + 1).charAt(0);
        } else {
            numNuevo = String.valueOf(num - 1).charAt(0);
        }

        String oldTxt = lblJugadoresListos.getText();
        String newTxt = oldTxt.replace(numJugadoresAnt, numNuevo);

        lblJugadoresListos.setText(newTxt);
        //lblEncabezado.setText(modelo.getEncabezadoLobby());//---------------------------Falta terminar----------
        //Aparezca cuantos esten listos
    }

    /**
     * Marca un jugador como listo en el lobby.
     *
     * @param cuenta La cuenta del jugador que está listo.
     */
//    @Override
    public void ponerListoJugador(CuentaDTO cuenta) {
        AnchorPane panelJugador = modelo.obtenerPanelJugador(cuenta.getIdCadena());
        ponerListoJugador(panelJugador);
    }

    /**
     * Marca un jugador como no listo en el lobby.
     *
     * @param cuenta La cuenta del jugador que no está listo.
     */
//    @Override
    public void quitarListoJugador(CuentaDTO cuenta) {
        AnchorPane panelJugador = modelo.obtenerPanelJugador(cuenta.getIdCadena());
        quitarListoJugador(panelJugador);
    }
//-----------------------------------------------------

    @Override
    public void inicializarLobby(LobbyDTO lobby) {
        if (lblCodigo == null) {
            cargarCodigoPartida(lobby);

        }
        for (CuentaDTO cuenta : lobby.getCuentas()) {
            System.out.println("cuenta-- " + cuenta);
            if (!yaEstaCuenta(cuenta)) {
                verificarCuenta(cuenta);
            }
        }
    }

    private boolean yaEstaCuenta(CuentaDTO cuenta) {
        boolean flag = false;

        for (AnchorPane panel : panelesJugadores) {
            System.out.println("si busca");
            if (panel.getId().equalsIgnoreCase(cuenta.getIdCadena())) {
                System.out.print("Panel id: " + panel.getId());
                System.out.println(" ID cuenta: " + cuenta.getIdCadena());
                return true;
            }
        }
        return flag;
    }

    private void verificarCuenta(CuentaDTO cuenta) {
        if (modelo.esCuentaActual(cuenta)) {
            ponerJugadorActual(cuenta);
        } else {
            ponerJugadorOtro(cuenta);
        }
    }

    @Override
    public void mostrarVentanaAvatares(List<AvatarDTO> avataresUsados) {
        System.out.println("Se muestran que avares ya se usaron");
        for (AvatarDTO avatar : avataresUsados) {
            System.out.println(avatar);
        }
    }
}
