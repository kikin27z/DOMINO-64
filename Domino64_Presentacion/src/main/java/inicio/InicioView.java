package inicio;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presentacion_utilities.NotificadorPresentacion;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class InicioView {
    
    private InicioModel model;
    private ImageView imgDomino;
    private ImageView imgTitulo;
    private Button btnJugar;
    private Button btnOnline;
    @FXML
    private Parent root;
    //private InicioModel model;
    
    public InicioView(InicioModel model){
        this.model = model;
    }
    
    public void initComponents(Stage stage) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(1000, 700);
        anchorPane.setStyle("-fx-background-color: #186F65;");

        // Imagen del dominó
        imgDomino = new ImageView();
        imgDomino.setFitHeight(500);
        imgDomino.setFitWidth(500);
        imgDomino.setLayoutX(259);
        imgDomino.setLayoutY(72);
        imgDomino.setPickOnBounds(true);
        imgDomino.setPreserveRatio(true);
        imgDomino.setRotate(-7.1);
        imgDomino.setImage(new Image(getClass().getResourceAsStream("/images/dominoImg.png")));

        // Imagen del título
        imgTitulo = new ImageView();
        imgTitulo.setFitHeight(125);
        imgTitulo.setFitWidth(627);
        imgTitulo.setLayoutX(214);
        imgTitulo.setLayoutY(79);
        imgTitulo.setPickOnBounds(true);
        imgTitulo.setPreserveRatio(true);
        imgTitulo.setImage(new Image(getClass().getResourceAsStream("/images/logoTitulo.png")));

        // Botón Jugar solo
        btnJugar = new Button("Jugar solo");
        btnJugar.setLayoutX(276);
        btnJugar.setLayoutY(493);
        btnJugar.setPrefSize(447, 75);
        btnJugar.setStyle("-fx-padding: 0 0; -fx-background-color: #B2533E;");
        btnJugar.setTextFill(javafx.scene.paint.Color.WHITE);
        btnJugar.setFont(new Font("Lucida Console", 43));

        // Botón Jugar online
        btnOnline = new Button("Jugar online");
        btnOnline.setLayoutX(276);
        btnOnline.setLayoutY(591);
        btnOnline.setPrefSize(447, 75);
        btnOnline.setStyle("-fx-padding: 0 0; -fx-background-color: #B2533E;");
        btnOnline.setTextFill(javafx.scene.paint.Color.WHITE);
        btnOnline.setFont(new Font("Lucida Console", 43));

        // Añadir todos los componentes al AnchorPane
        anchorPane.getChildren().addAll(imgDomino, imgTitulo, btnJugar, btnOnline);

        // Configurar la escena
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }
    
    public void setOnActionSolo(EventHandler<ActionEvent> handler){
        btnJugar.setOnAction(handler);
    }
    public void setOnActionOnline(EventHandler<ActionEvent> handler){
        btnOnline.setOnAction(handler);
    }
    
    public void iniciarEscena(Stage fondo) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/fxml/inicio.fxml"));
        Scene scene = new Scene(root);
        
        btnOnline = new Button();
        btnOnline.setLayoutX(276.0);
        btnOnline.setLayoutY(591.0);
        //btnOnline.setOnAction();// "#irPartida" 
        btnOnline.setPrefHeight(75);
        btnOnline.setPrefWidth(447);
        btnOnline.setStyle("-fx-padding: 0 0; -fx-background-color: #B2533E;");
        btnOnline.setText("Jugar online");
        btnOnline.setTextFill(Paint.valueOf("WHITE"));
        
//        btnOnline = (Button) root.lookup("#btnOnline");
//        btnOnline.setStyle("-fx-background-color: #0000FF;");

        fondo.setScene(scene);
        fondo.show();
    }
    
}
