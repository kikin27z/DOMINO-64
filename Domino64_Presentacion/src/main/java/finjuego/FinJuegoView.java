package finjuego;

import entidadesDTO.CuentaDTO;
import entidadesDTO.ResultadosDTO;
import eventosFin.ObserverFinJuegoMVC;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class FinJuegoView implements ObserverFinJuegoMVC {

    private final FinJuegoModel modelo;
    private AnchorPane panel;
    private Stage fondo;
    private HBox contenedorJugadores;
    private Button btnAbandonar;

    public FinJuegoView(FinJuegoModel modelo) {
        this.modelo = modelo;
        this.modelo.agregarObserver(this);
    }

    public void iniciarEscena(Stage fondo) throws IOException {
        crearComponentes();
        Scene scene = new Scene(panel);
        fondo.setScene(scene);
        fondo.show();
        this.fondo = fondo;
    }

    // Create Main Container (AnchorPane)
    private void crearComponentes() {
        panel = new AnchorPane();
        panel.setPrefSize(1000, 700);
        panel.setStyle("-fx-background-color: #B2533E;");

        crearBtnAbandonar();

        // Add components
        panel.getChildren().addAll(
                crearTituloPrincipal(),
                crearContenedorJugadores(),
                btnAbandonar,
                crearSubtitulo()
        );

    }

    // Title Label
    private Label crearTituloPrincipal() {
        Label titulo = new Label("Fin del juego");
        titulo.setLayoutX(60);
        titulo.setLayoutY(100);
        titulo.setPrefWidth(855);
        titulo.setTextFill(javafx.scene.paint.Color.web("#2f1c1c"));
        titulo.setFont(Font.font("Verdana Bold", 48));
        titulo.setAlignment(javafx.geometry.Pos.CENTER);

        return titulo;
    }

    // Player Container with HBox
    private HBox crearContenedorJugadores() {
        contenedorJugadores = new HBox(18);
        contenedorJugadores.setLayoutX(29);
        contenedorJugadores.setLayoutY(206);
        contenedorJugadores.setPrefSize(942, 278);
        contenedorJugadores.setMaxSize(942, 278);
        contenedorJugadores.setMinSize(942, 278);

        return contenedorJugadores;
    }

    private void crearPanelJugador(CuentaDTO cuenta, int puntos) {
        AnchorPane panelJugador = new AnchorPane();
        panelJugador.setPrefSize(222, 278);
        panelJugador.setMaxSize(222, 278);
        panelJugador.setMinSize(222, 278);

        // Avatar Image
        ImageView avatarImg = new ImageView(new Image(getClass().getResourceAsStream(cuenta.getAvatar().getUrl())));
        avatarImg.setFitHeight(150);
        avatarImg.setFitWidth(200);
        avatarImg.setLayoutX(41);
        avatarImg.setLayoutY(15);

        // Player Name Label
        Label lblNombre = new Label(cuenta.getUsername());
        lblNombre.setLayoutX(15);
        lblNombre.setLayoutY(180);
        lblNombre.setPrefWidth(192);
        lblNombre.setStyle("-fx-background-color: #D9D9D9; -fx-background-radius: 20; -fx-padding: 6 4;");
        lblNombre.setFont(Font.font("Russo One", 20));
        lblNombre.setAlignment(javafx.geometry.Pos.CENTER);

        // Points Label
        Label lblPuntos = new Label(puntos + " puntos");
        lblPuntos.setLayoutX(15);
        lblPuntos.setLayoutY(234);
        lblPuntos.setPrefWidth(192);
        lblPuntos.setFont(Font.font("Verdana Bold", 20));
        lblPuntos.setAlignment(javafx.geometry.Pos.CENTER);

        if (!modelo.isGanadorPuesto()) {
            ImageView winnerImage = new ImageView(new Image(getClass().getResourceAsStream("/images/ganador.png")));
            winnerImage.setFitHeight(150);
            winnerImage.setFitWidth(50);
            winnerImage.setLayoutX(86);
            winnerImage.setLayoutY(-18);
            modelo.setGanadorPuesto(false);
            panelJugador.getChildren().addAll(avatarImg, winnerImage, lblNombre, lblPuntos);
        } else {
            panelJugador.getChildren().addAll(avatarImg, lblNombre, lblPuntos);
        }
        contenedorJugadores.getChildren().add(panelJugador);
    }

    private void crearBtnAbandonar() {
        btnAbandonar = new Button("Abandonar partida");
        btnAbandonar.setLayoutX(290);
        btnAbandonar.setLayoutY(560);
        btnAbandonar.setPrefWidth(420);
        btnAbandonar.setMaxHeight(70);
        btnAbandonar.setStyle("-fx-background-color: #2F1C1C; -fx-background-radius: 20;");
        btnAbandonar.setTextFill(javafx.scene.paint.Color.WHITE);
        btnAbandonar.setFont(Font.font("Russo One", 37));
        btnAbandonar.setCursor(javafx.scene.Cursor.HAND);

    }

    private Label crearSubtitulo() {
        Label lblSubtitulo = new Label("Fin del juego");
        lblSubtitulo.setLayoutX(72);
        lblSubtitulo.setLayoutY(496);
        lblSubtitulo.setPrefWidth(855);
        lblSubtitulo.setTextFill(javafx.scene.paint.Color.web("#2e1c1cb9"));
        lblSubtitulo.setFont(Font.font("Verdana Bold", 33));
        lblSubtitulo.setAlignment(javafx.geometry.Pos.CENTER);

        return lblSubtitulo;
    }

    public void abandonar(EventHandler<MouseEvent> e) {
        btnAbandonar.setOnMouseClicked(e);
    }

    @Override
    public void agregarPanelJugador(CuentaDTO cuenta, int puntos) {
        crearPanelJugador(cuenta, puntos);
    }

    @Override
    public void inicializarFinJuego(ResultadosDTO resultados) {
        System.out.println("Feliz navidad");
    }
}
