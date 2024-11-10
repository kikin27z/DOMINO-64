package opciones_partida;

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
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class OpcionesPartidaView {

    private OpcionesPartidaModel modelo;
    private AnchorPane panel;
    private Button btnNuevaPartida;
    private Button btnUnirsePartida;
    private ImageView btnVolver;
    private TextField txtCodigo ;
    private AnchorPane fondoBuscarPartida;
    private Stage ventanaBuscarPartida;
    private Stage fondo;
    private Button btnCancelarBuscarPartida;
    private Button btnBuscarPartida;
    private Label lblMensajes;

    public OpcionesPartidaView(OpcionesPartidaModel modelo) {
        this.modelo = modelo;
    }

    public void iniciarEscena(Stage fondo) throws IOException {
        crearComponentes();
        Scene scene = new Scene(panel);
        fondo.setScene(scene);
        fondo.show();
        this.fondo = fondo;
    }

    public void crearComponentes() {
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
        
        cargarBuscarPartida();
    }

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

    //--------------------------------------------------------Modal windows--------------------------------------------------------
    public void cargarBuscarPartida() {
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

        btnCancelarBuscarPartida = crearBoton("Cancelar", 58, 361, 200, "#5C4033");
        btnBuscarPartida = crearBoton("Buscar", 321, 361, 200, "#B2533E");
        btnCancelarBuscarPartida.setId("cancelar");
        btnBuscarPartida.setId("buscar");

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

    public void mostrarVentanaBuscarPartida() {
        ventanaBuscarPartida.setResizable(false);
        txtCodigo.setText("");
        lblMensajes.setText("****");
        ventanaBuscarPartida.showAndWait();  // showAndWait hace que sea modal
    }

    /**
    * Cierra la ventana de unirse de la partida.
    */
    public void cerrarVentanaBuscarPartida() {
        ventanaBuscarPartida.close();
    }
    
    //------------EVENTOS------------
    public void crearNuevaPartida(EventHandler<MouseEvent> e) {
        btnNuevaPartida.setOnMouseClicked(e);
    }

    public void unirsePartida(EventHandler<MouseEvent> e) {
        btnUnirsePartida.setOnMouseClicked(e);
    }
    
    public void volverInicio(EventHandler<MouseEvent> e){
        btnVolver.setOnMouseClicked(e);
    }
    public void buscarPartida(EventHandler<MouseEvent> e){
        btnBuscarPartida.setOnMouseClicked(e);
    }
    public void cancelarBuscarPartida(EventHandler<MouseEvent> e){
        btnCancelarBuscarPartida.setOnMouseClicked(e);
    }
    
    public String obtenerCodigo(){
        return txtCodigo.getText();
    }
}
