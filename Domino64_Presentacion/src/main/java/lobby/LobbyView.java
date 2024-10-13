package lobby;

import com.mycompany.patrones.observer.Observer;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class LobbyView implements Observer<LobbyModel>{
    private LobbyModel modelo;
    
    private StackPane ajustesStackPane;
    private ImageView iconAjustes;
    private ImageView avatarImageView;
    private TextField usernameTextField;
    private Button iniciarPartidaButton;
    private Button abandonarPartidaButton;
    private Label jugandoLabel;

    public LobbyView(LobbyModel modelo) {
        this.modelo = modelo;
    }
    
    public void initComponents(Stage stage) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(1000, 700);
        anchorPane.setStyle("-fx-background-color: #B2533E;");

        // StackPane para ajustes
        ajustesStackPane = new StackPane();
        ajustesStackPane.setLayoutX(874);
        ajustesStackPane.setLayoutY(46);
        ajustesStackPane.setPrefSize(60, 60);

        // Rectángulo de ajustes
        Rectangle rect = new Rectangle(60, 60);
        rect.setArcHeight(100.0);
        rect.setArcWidth(100.0);
        rect.setFill(javafx.scene.paint.Color.valueOf("#eae8e8"));
        rect.setStroke(javafx.scene.paint.Color.BLACK);

        // Imagen de ajustes
        iconAjustes = new ImageView();
        iconAjustes.setFitHeight(44);
        iconAjustes.setFitWidth(44);
        iconAjustes.setPreserveRatio(true);
        iconAjustes.setImage(new Image(getClass().getResourceAsStream("/images/iconAjustes.png")));

        // Añadir componentes al StackPane de ajustes
        ajustesStackPane.getChildren().addAll(rect, iconAjustes);

        // Imagen de avatar
        avatarImageView = new ImageView();
        avatarImageView.setFitHeight(150);
        avatarImageView.setFitWidth(200);
        avatarImageView.setLayoutX(399);
        avatarImageView.setLayoutY(192);
        avatarImageView.setPreserveRatio(true);
        avatarImageView.setImage(new Image(getClass().getResourceAsStream("/avatar/gato.png")));

        // Campo de texto para el nombre de usuario
        usernameTextField = new TextField();
        usernameTextField.setAlignment(javafx.geometry.Pos.CENTER);
        usernameTextField.setLayoutX(370);
        usernameTextField.setLayoutY(342);
        usernameTextField.setPrefSize(208, 41);
        usernameTextField.setPromptText("Username");
        usernameTextField.setStyle("-fx-padding: 10 0;");
        usernameTextField.setFont(new Font("Lucida Console", 18));

        // Botón Iniciar partida
        iniciarPartidaButton = new Button("Iniciar partida");
        iniciarPartidaButton.setLayoutX(701);
        iniciarPartidaButton.setLayoutY(542);

        // Botón Abandonar partida
        abandonarPartidaButton = new Button("Abandonar partida");
        abandonarPartidaButton.setLayoutX(250);
        abandonarPartidaButton.setLayoutY(542);

        // Label Jugando contra la máquina
        jugandoLabel = new Label("Jugando contra la máquina");
        jugandoLabel.setLayoutX(250);
        jugandoLabel.setLayoutY(106);
        jugandoLabel.setFont(new Font("Verdana Bold", 27));

        // Añadir todos los componentes al AnchorPane
        anchorPane.getChildren().addAll(ajustesStackPane, avatarImageView, usernameTextField, iniciarPartidaButton, abandonarPartidaButton, jugandoLabel);

        // Configurar la escena
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }
    
    protected void setOnActionAbandondar(EventHandler<ActionEvent> handler){
        abandonarPartidaButton.setOnAction(handler);
    }
    protected void setOnActionIniciar(EventHandler<ActionEvent> handler){
        iniciarPartidaButton.setOnAction(handler);
    }
    protected void setOnDragAjustes(EventHandler<MouseEvent> handler){
        ajustesStackPane.setOnDragDetected(handler);
    }
    
    protected String getUsername(){
        String texto = this.usernameTextField.getText();
        return texto;
    }
    
    public void iniciarEscena(Stage fondo) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/lobby.fxml"));
        Scene scene = new Scene(root);
        fondo.setScene(scene);
        fondo.show();
    }

    @Override
    public void update(LobbyModel observable, Object ... context) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
