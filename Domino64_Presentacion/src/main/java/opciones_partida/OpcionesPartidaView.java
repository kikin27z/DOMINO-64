package opciones_partida;

import eventosOpcionesPartida.ObserverOpcionesMVC;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

/**
 * Clase OpcionesPartidaView representa la interfaz gráfica para las opciones de
 * partida en el juego. Proporciona botones para iniciar una nueva partida,
 * unirse a una existente, y volver a la pantalla principal. Incluye la
 * capacidad de mostrar una ventana modal para ingresar un código de partida.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class OpcionesPartidaView implements ObserverOpcionesMVC {
    // Modelo asociado para la vista
    private final OpcionesPartidaModel modelo;

    // Componentes de la interfaz gráfica
    private AnchorPane panel;
    private Button btnNuevaPartida;
    private Button btnUnirsePartida;
    private ImageView btnVolver;
    private TextField txtCodigo;
    private AnchorPane fondoBuscarPartida;
    private Stage ventanaBuscarPartida;
    private Stage fondo;
    private Button btnCancelarBuscarPartida;
    private Button btnBuscarPartida;
    private Label lblMensajes;

    /**
     * Constructor de la clase OpcionesPartidaView.
     * Configura la vista y registra la instancia como observador del modelo.
     * 
     * @param modelo Instancia del modelo OpcionesPartidaModel asociada a la vista.
     */
    public OpcionesPartidaView(OpcionesPartidaModel modelo) {
        this.modelo = modelo;
        this.modelo.agregarObserver(this);
    }

    /**
     * Inicializa la escena y la muestra en la ventana principal.
     *
     * @param fondo Escenario principal donde se mostrará la interfaz.
     * @throws IOException Si ocurre un error al cargar recursos externos.
     */
    public void iniciarEscena(Stage fondo) throws IOException {
        crearComponentes();
        Scene scene = new Scene(panel);
        fondo.setScene(scene);
        fondo.show();
        this.fondo = fondo;
    }

    /**
     * Crea y organiza los componentes visuales de la vista, incluyendo
     * los botones de "Nueva partida" y "Unirse a partida".
     */
    private void crearComponentes() {
        panel = new AnchorPane();
        panel.setPrefHeight(700);
        panel.setPrefWidth(1000);
        panel.setStyle("-fx-background-color: #186F65;");

        // Back button
        btnVolver = new ImageView(new Image(getClass().getResourceAsStream("/images/btnVolver.png")));
        btnVolver.setFitHeight(70);
        btnVolver.setFitWidth(70);
        btnVolver.setLayoutX(36);
        btnVolver.setLayoutY(20);
        btnVolver.setPreserveRatio(true);
        btnVolver.setCursor(Cursor.HAND);

        // Main HBox
        HBox mainHBox = new HBox();
        mainHBox.setLayoutX(116);
        mainHBox.setLayoutY(150);
        mainHBox.setMaxHeight(480);
        mainHBox.setMaxWidth(772);
        mainHBox.setPrefHeight(480);
        mainHBox.setPrefWidth(772);
        mainHBox.setSpacing(31);

        // New Game Pane
        btnNuevaPartida = new Button("Nueva partida");
        AnchorPane newGamePane = crearPanelOpcion("/images/nuevaPartida.png", "/images/mas.png", btnNuevaPartida);

        // Empty Pane
        AnchorPane emptyPane = new AnchorPane();
        emptyPane.setMaxWidth(370);
        emptyPane.setPrefHeight(200);
        emptyPane.setPrefWidth(370);

        // Join Game Pane
        btnUnirsePartida = new Button("Unirse a partida");
        AnchorPane joinGamePane = crearPanelOpcion("/images/unirsePartida.png", null, btnUnirsePartida);

        mainHBox.getChildren().addAll(newGamePane, emptyPane, joinGamePane);

        // Tiles image
        ImageView tilesImage = new ImageView(new Image(getClass().getResourceAsStream("/images/fichas.png")));
        tilesImage.setFitHeight(150);
        tilesImage.setFitWidth(150);
        tilesImage.setLayoutX(863);
        tilesImage.setLayoutY(15);
        tilesImage.setRotate(180);

        panel.getChildren().addAll(btnVolver, mainHBox, tilesImage);
        cargarBtnModal();
    }

    /**
     * Crea un panel de opción visual con una imagen y un botón.
     * 
     * @param imgPrincipal Ruta de la imagen principal del panel.
     * @param imgSecundaria Ruta de la imagen secundaria del panel (opcional).
     * @param btnPanel Botón que representa la acción de la opción.
     * @return Panel de opción configurado.
     */
    private AnchorPane crearPanelOpcion(String imgPrincipal, String imgSecundaria, Button btnPanel) {
        AnchorPane pane = new AnchorPane();
        pane.setMaxWidth(370);
        pane.setPrefHeight(200);
        pane.setPrefWidth(370);

        Circle circle = new Circle(178, 201, 147);
        circle.setFill(Color.web("#D9D9D9"));
        circle.setStroke(Color.web("#5C4033"));
        circle.setStrokeWidth(3);
        circle.setEffect(new BoxBlur());

        btnPanel.setLayoutX(5);
        btnPanel.setLayoutY(362);
        btnPanel.setMaxWidth(360);
        btnPanel.setPrefWidth(360);
        btnPanel.setStyle("-fx-background-color: #B2533E; -fx-background-radius: 10;");
        btnPanel.setTextFill(Color.WHITE);
        btnPanel.setFont(new Font("Russo One", 35));
        btnPanel.setCursor(Cursor.HAND);

        ImageView mainImage = new ImageView(new Image(getClass().getResourceAsStream(imgPrincipal)));
        mainImage.setFitHeight(280);
        mainImage.setFitWidth(280);
        mainImage.setLayoutX(53);
        mainImage.setLayoutY(57);

        pane.getChildren().addAll(circle, btnPanel, mainImage);

        if (imgSecundaria != null) {
            ImageView secondaryImage = new ImageView(new Image(getClass().getResourceAsStream(imgSecundaria)));
            secondaryImage.setFitHeight(140);
            secondaryImage.setFitWidth(140);
            secondaryImage.setLayoutX(29);
            secondaryImage.setLayoutY(10);
            pane.getChildren().add(secondaryImage);
        }

        return pane;
    }

        /**
     * Crea una etiqueta con estilo personalizado.
     *
     * @param texto Texto de la etiqueta.
     * @param posX Posición X de la etiqueta.
     * @param posY Posición Y de la etiqueta.
     * @param width Ancho de la etiqueta.
     * @param height Altura de la etiqueta.
     * @return Etiqueta configurada.
     */
    private Label crearEtiqueta(String text, double x, double y, double width, double height) {
        Label label = new Label(text);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setPrefHeight(height);
        label.setPrefWidth(width);
        label.setTextFill(Color.web("#2e1c1cc7"));
        label.setFont(new Font("Verdana Bold", 48));
        return label;
    }

    /**
     * Crea un botón con estilo personalizado.
     *
     * @param texto Texto del botón.
     * @param posX Posición X del botón.
     * @param posY Posición Y del botón.
     * @param width Ancho del botón.
     * @param color Color de fondo del botón.
     * @return Botón configurado.
     */
    private Button crearBoton(String text, double x, double y, double width, String color) {
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setMaxHeight(65);
        button.setMinHeight(Double.NEGATIVE_INFINITY);
        button.setPrefWidth(width);
        button.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 20;");
        button.setTextFill(Color.WHITE);
        button.setFont(new Font("Russo One", 23));
        button.setCursor(Cursor.HAND);
        return button;
    }

    //--------------------------------------------------------Modal windows--------------------------------------------------------
    /**
     * Configura los botones de la ventana modal para buscar una partida.
     */
    private void cargarBtnModal() {
        btnCancelarBuscarPartida = crearBoton("Cancelar", 58, 361, 200, "#5C4033");
        btnBuscarPartida = crearBoton("Buscar", 321, 361, 200, "#B2533E");
        btnCancelarBuscarPartida.setId("cancelar");
        btnBuscarPartida.setId("buscar");
    }

    /**
     * Crea y configura la ventana modal para ingresar el código de partida.
     */
    private void cargarBuscarPartida() {
        fondoBuscarPartida = new AnchorPane();
        fondoBuscarPartida.setPrefHeight(570);
        fondoBuscarPartida.setPrefWidth(640);
        fondoBuscarPartida.setStyle("-fx-background-color: D9D9D9;");

        AnchorPane innerPane = new AnchorPane();
        innerPane.setLayoutX(31);
        innerPane.setLayoutY(48);
        innerPane.setPrefHeight(475);
        innerPane.setPrefWidth(578);
        innerPane.setStyle("-fx-background-color: #FCE09B; -fx-background-radius: 50;");

        Label titleLabel1 = crearEtiqueta("Ingresa el código", 42, 33, 498, 63);
        Label titleLabel2 = crearEtiqueta("de la partida:", 78, 96, 425, 63);

        txtCodigo = new TextField();
        txtCodigo.setAlignment(javafx.geometry.Pos.CENTER);
        txtCodigo.setLayoutX(44);
        txtCodigo.setLayoutY(199);
        txtCodigo.setPrefHeight(60);
        txtCodigo.setPrefWidth(490);
        txtCodigo.setPromptText("Escribe el código de la partida");
        txtCodigo.setStyle("-fx-background-color: #fff; -fx-background-radius: 20; -fx-padding: 0 10;");
        txtCodigo.setFont(new Font("Russo One", 18));
        txtCodigo.setId("codigo");

        lblMensajes = new Label("****");
        lblMensajes.setAlignment(javafx.geometry.Pos.CENTER);
        lblMensajes.setLayoutX(21);
        lblMensajes.setLayoutY(266);
        lblMensajes.setPrefWidth(534);
        lblMensajes.setStyle("-fx-padding: 10 0;");
        lblMensajes.setTextFill(Color.web("#2e1c1cc7"));
        lblMensajes.setFont(new Font("Verdana Bold", 18));

        innerPane.getChildren().addAll(titleLabel1, titleLabel2, btnCancelarBuscarPartida, btnBuscarPartida, txtCodigo, lblMensajes);
        fondoBuscarPartida.getChildren().add(innerPane);

        // Crear una nueva ventana (Stage) modal
        ventanaBuscarPartida = new Stage();

        // Establecer la modalidad para la ventana
        ventanaBuscarPartida.initModality(Modality.APPLICATION_MODAL);

        // Definir la ventana padre para la modalidad
        ventanaBuscarPartida.initOwner(fondo);

        // Título y contenido de la ventana modal
        ventanaBuscarPartida.setTitle("Configuración partida");
        Scene scene = new Scene(fondoBuscarPartida, 640, 570);
        ventanaBuscarPartida.setScene(scene);
    }


    /**
     * Muestra la ventana modal para buscar una partida.
     */
    public void mostrarVentanaBuscarPartida() {
        cargarBuscarPartida();
        ventanaBuscarPartida.setResizable(false);
        ventanaBuscarPartida.showAndWait();
    }

    /**
     * Cierra la ventana modal de búsqueda de partida.
     */
    public void cerrarVentanaBuscarPartida() {
        ventanaBuscarPartida.close();
    }

    //------------EVENTOS Control------------
    /**
     * Asigna un evento de clic al botón de "Nueva Partida".
     *
     * @param e Evento de tipo MouseEvent a asignar al botón.
     */
    public void crearNuevaPartida(EventHandler<MouseEvent> e) {
        btnNuevaPartida.setOnMouseClicked(e);
    }

    /**
     * Asigna un evento de clic al botón de "Unirse a Partida".
     *
     * @param e Evento de tipo MouseEvent a asignar al botón.
     */
    public void unirsePartida(EventHandler<MouseEvent> e) {
        btnUnirsePartida.setOnMouseClicked(e);
    }

    /**
     * Asigna un evento de clic al botón de "Volver" para regresar al inicio.
     *
     * @param e Evento de tipo MouseEvent a asignar al botón.
     */
    public void volverInicio(EventHandler<MouseEvent> e) {
        btnVolver.setOnMouseClicked(e);
    }

    /**
     * Asigna un evento de clic al botón de "Buscar Partida".
     *
     * @param e Evento de tipo MouseEvent a asignar al botón.
     */
    public void buscarPartida(EventHandler<MouseEvent> e) {
        btnBuscarPartida.setOnMouseClicked(e);
    }

    /**
     * Asigna un evento de clic al botón de "Cancelar" en la ventana de búsqueda
     * de partida.
     *
     * @param e Evento de tipo MouseEvent a asignar al botón.
     */
    public void cancelarBuscarPartida(EventHandler<MouseEvent> e) {
        btnCancelarBuscarPartida.setOnMouseClicked(e);
    }

//----------------------Eventos de Modelo------------------------------------
    /**
     * Actualiza el mensaje de aviso en la interfaz.
     *
     * @param mensaje Texto del mensaje que se mostrará en la etiqueta de
     * mensajes.
     */
    @Override
    public void actualizarMensajeAviso(String mensaje) {
        lblMensajes.setText(mensaje);
    }

//--------------------Getters-------------------------
    /**
     * Obtiene el código ingresado en el campo de texto.
     *
     * @return String que representa el texto ingresado en el campo de código.
     */
    public String obtenerCodigo() {
        return txtCodigo.getText();
    }

}
