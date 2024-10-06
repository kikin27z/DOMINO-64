package partida;

import entidades.Ficha;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import utilities.Observable;
import utilities.Observador;

/**
 * Clase que representa la vista de la partida en la aplicación.
 * Esta clase se encarga de crear la interfaz gráfica de usuario y
 * manejar la lógica de visualización relacionada con la partida.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaView implements Observador{
    private PartidaModel modelo;
    private AnchorPane panelExterior; // Panel exterior que contiene todos los elementos visuales
    private AnchorPane panelInterior;  // Panel interno que se desplaza dentro del ScrollPane
    private AnchorPane panelJugador1;
    private ScrollPane scrollPanel;     // Panel con capacidad de desplazamiento
    private Button btnEjemplo;
    private Map<Ficha,ImageView> mapeoFichas;

    public PartidaView(PartidaModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Inicializa la escena de la partida y muestra la ventana.
     * 
     * @param fondo La ventana principal (Stage) donde se mostrará la escena.
     * @throws IOException Si ocurre un error al cargar los recursos necesarios.
     */
    public void iniciarEscena(Stage fondo) throws IOException {
        crearComponentes(); // Aseguramos que los componentes sean creados antes de iniciar la escena
        Scene scene = new Scene(panelExterior); // Usamos panelExterior como la raíz
        fondo.setScene(scene);
        fondo.show();

        // Ajustamos la posición inicial del ScrollPane
        Platform.runLater(() -> {
            scrollPanel.setHvalue(0.5); // Centrar horizontalmente
            scrollPanel.setVvalue(0.5); // Centrar verticalmente
        });
    }

    /**
     * Crea y configura los componentes de la interfaz de usuario.
     * Esto incluye el AnchorPane principal, el ScrollPane y los elementos internos.
     */
    private void crearComponentes() {
        // Creando el AnchorPane exterior
        panelExterior = new AnchorPane();
        panelExterior.setPrefSize(modelo.getExternalPanelWidth(), modelo.getExternalPanelHeight());
        panelExterior.setStyle(modelo.getExternalPanelStyle());

        // Creando el ScrollPane
        scrollPanel = new ScrollPane();
        scrollPanel.setLayoutX(modelo.getScrollPanelLayoutX());
        scrollPanel.setLayoutY(modelo.getScrollPanelLayoutY());
        scrollPanel.setPrefSize(modelo.getScrollPanelWidth(), modelo.getScrollPanelHeight());
        scrollPanel.setStyle(modelo.getScrollPanelStyle());
        scrollPanel.setHbarPolicy(modelo.getScrollPanelHbarPolicy()); // Mostrar barra horizontal
        scrollPanel.setVbarPolicy(modelo.getScrollPanelVbarPolicy()); // Mostrar barra vertical

        // Creando el AnchorPane interno dentro del ScrollPane
        panelInterior = new AnchorPane();
        panelInterior.setMinSize(modelo.getInternalPanelWidth(), modelo.getInternalPanelHeight()); // Establecemos el tamaño mínimo
        panelInterior.setStyle(modelo.getInternalPanelStyle());

//         Creando el ImageView para la imagen dentro del ScrollPane
        ImageView imageView = new ImageView();
        imageView.setFitWidth(modelo.getImageViewWidth());
        imageView.setLayoutX(modelo.getImageViewLayoutX());
        imageView.setLayoutY(modelo.getImageViewLayoutY());
        imageView.setPickOnBounds(modelo.isImgViewPickedOnBounds()); // Permite que la imagen sea seleccionable
        imageView.setPreserveRatio(modelo.isImgViewRatioPreserved()); // Mantiene la proporción de la imagen
        imageView.setImage(new Image(getClass().getResourceAsStream(modelo.getImageViewResourceName()))); // Ruta de la imagen

        btnEjemplo = new Button();
        btnEjemplo.setText(modelo.getButtonText());
        btnEjemplo.setLayoutX(modelo.getButtonLayoutX());
        btnEjemplo.setLayoutY(modelo.getButtonLayoutY());
        
////        new Button().setOnAction((EventHandler<ActionEvent>) new ActionEvent());
//        // Añadir el ImageView al panel interior
        panelInterior.getChildren().add(imageView);

        // Asignando el contenido del ScrollPane
        scrollPanel.setContent(panelInterior);

        // Añadiendo el ScrollPane al AnchorPane principal
        panelExterior.getChildren().add(scrollPanel);
        panelExterior.getChildren().add(btnEjemplo);

        // Creando el segundo AnchorPane para la parte inferior
        panelJugador1 = new AnchorPane();
        panelJugador1.setLayoutX(modelo.getPlayer1PanelLayoutX());
        panelJugador1.setLayoutY(modelo.getPlayer1PanelLayoutY());
        panelJugador1.setPrefSize(modelo.getPlayer1PanelWidth(), modelo.getPlayer1PanelHeight());
        panelJugador1.setStyle(modelo.getPlayer1PanelStyle());

        // Crear el ImageView para la segunda imagen
//        ImageView imageViewBottom = new ImageView();
//        imageViewBottom.setFitHeight(modelo.getImageViewBottomHeight());
//        imageViewBottom.setFitWidth(modelo.getImageViewBottomWidth());
//        imageViewBottom.setLayoutX(modelo.getImageViewBottomLayoutX());
//        imageViewBottom.setLayoutY(modelo.getImageViewBottomLayoutY());
//        imageViewBottom.setPickOnBounds(modelo.isImgViewBttmPickedOnBounds()); // Permite que la imagen sea seleccionable
//        imageViewBottom.setPreserveRatio(modelo.isImgViewBttmRatioPreserved()); // Mantiene la proporción de la imagen
//        imageViewBottom.setRotate(modelo.getImgViewBttmRotate()); // Rota la imagen 90 grados
//        imageViewBottom.setImage(new Image(getClass().getResourceAsStream(modelo.getImgViewBttmResourceName()))); // Ruta de la imagen
//
//        // Añadir el ImageView al segundo AnchorPane
//        panelJugador1.getChildren().add(imageViewBottom);
        
        // Añadir el segundo AnchorPane al AnchorPane principal
        panelExterior.getChildren().add(panelJugador1);
    }
    
    public void ponerFichaEnTablero(double layoutX){
        ImageView fichaJugada = (ImageView)panelJugador1.getChildren().removeLast();
        fichaJugada.setLayoutX(layoutX);
        panelInterior.getChildren().add(fichaJugada);
    }
    
    public Map<Ficha,ImageView> addTile(){
        // Crear el ImageView para la segunda imagen
        ImageView imageViewBottom;
        double rotation = modelo.getImgViewBttmRotate();
        double contador = modelo.getImageViewBottomLayoutX();
        mapeoFichas = new HashMap<>();
        for(Ficha f: modelo.getFichasDelJugador()){
            imageViewBottom = new ImageView();
            imageViewBottom.setFitHeight(modelo.getImageViewBottomHeight());
            imageViewBottom.setFitWidth(modelo.getImageViewBottomWidth());
            imageViewBottom.setLayoutX(contador);
            contador+=50;
            imageViewBottom.setLayoutY(modelo.getImageViewBottomLayoutY());
            imageViewBottom.setRotate(rotation);
            rotation+=90;
            imageViewBottom.setOnMouseClicked(modelo.getEventHandler());
            imageViewBottom.setPickOnBounds(modelo.isImgViewBttmPickedOnBounds()); // Permite que la imagen sea seleccionable
            imageViewBottom.setPreserveRatio(modelo.isImgViewBttmRatioPreserved()); // Mantiene la proporción de la imagen
            imageViewBottom.setImage(new Image(getClass().getResourceAsStream(f.getImgUrl()))); // Ruta de la imagen
            mapeoFichas.put(f, imageViewBottom);
            panelJugador1.getChildren().add(imageViewBottom);
        }
        return mapeoFichas;
    }
    
    public void btnEjemploEvento(EventHandler<ActionEvent> evento){
        btnEjemplo.setOnAction(evento);
    }

    @Override
    public void update(Observable ob, Object ... context) {
        double layoutX = 0;
        for (Object object : context) {
            layoutX = (double) object;
        }
        ponerFichaEnTablero(layoutX);
    }
}
