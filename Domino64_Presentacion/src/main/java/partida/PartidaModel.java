package partida;

import entidades.Ficha;
import java.util.List;
import entidades.Jugador;
import entidades.Partida;
import entidades.Tablero;
//import exceptions.DominioException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import utilities.Observable;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaModel extends Observable{
    private Jugador jugador;
    private Partida partida;
    private final double externalPanelWidth = 1000;
    private final double externalPanelHeight = 700;
    private final String externalPanelStyle = "-fx-background-color: #186F65;";
    
    private final double internalPanelWidth = 1200;
    private final double internalPanelHeight = 900;
    private final String internalPanelStyle = "-fx-background-color: #B5CB99;";
    
    private final double scrollPanelLayoutX = 85;
    private final double scrollPanelLayoutY = 75;
    private final double scrollPanelWidth = 830;
    private final double scrollPanelHeight = 550;
    private final String scrollPanelStyle = "-fx-background-color: transparent; "
            + "-fx-background: transparent;";
    private final ScrollBarPolicy scrollPanelHbarPolicy = ScrollBarPolicy.ALWAYS;
    private final ScrollBarPolicy scrollPanelVbarPolicy = ScrollBarPolicy.ALWAYS;
    
    private final double imageViewWidth = 106.0;
    private final double imageViewLayoutX = 547.0;
    private final double imageViewLayoutY = 419.0;
    private final boolean imgViewpickOnBounds = true;
    private final boolean imgViewpreserveRatio = true;
    private final String imageViewResourceName = "/dominos/0-2.png";
    
    private final String buttonText = "Clickea";
    private final double buttonLayoutX = 20;
    private final double buttonLayoutY = 20;
    
    private final double player1PanelLayoutX = 164.0;
    private final double player1PanelLayoutY = 598.0;
    private final double player1PanelWidth = 630.0;
    private final double player1PanelHeight = 92.0;
    private final String player1PanelStyle ="-fx-background-color: #B2533E;"
            + " -fx-background-radius: 20;"
            + " -fx-border-color: #000000;"
            + " -fx-border-radius: 20;";
    
    private final double imageViewBottomWidth = 90.0;
    private final double imageViewBottomHeight = 150.0;
    private final double imageViewBottomLayoutX = 81.0;
    private final double imageViewBottomLayoutY = 14.0;
    private final boolean imgViewBttmpickOnBounds = true;
    private final boolean imgViewBttmpreserveRatio = true;
    private final double imgViewBttmRotate = 90.0;
    private final String imgViewBttmResourceName = "/dominos/0-5.png";

    public Partida getPartida() {
        return partida;
    }

    private void ponerFichaEnTablero(Ficha ficha){
        Tablero tablero = partida.getTablero();
        try {
            if(tablero.tableroVacio()){
                partida.getTablero().insertarFicha(ficha);
            }else{
                partida.getTablero().insertarFichaDer(ficha);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setGame(Partida partida) {
        this.partida = partida;
    }

    public EventHandler<MouseEvent> getEventHandler(){
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                System.out.println("ficha seelccionada");
            }
        };
        return event;
    }
    
    public Jugador getJugador() {
        return jugador;
    }
    
    public void colocarFicha(){
        Ficha ultimaFicha = getFichasDelJugador().getLast();
        jugador.removerFicha(ultimaFicha);
        ponerFichaEnTablero(ultimaFicha);
        double imgViewLayoutX = imageViewLayoutX+50;
        //double imgViewLayoutY = imageViewLayoutY+50;
        this.notifyObservers(imgViewLayoutX);
    }
    
    public List<Ficha> getFichasDelJugador(){
        return jugador.getFichas();
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public double getExternalPanelWidth() {
        return externalPanelWidth;
    }

    public double getExternalPanelHeight() {
        return externalPanelHeight;
    }

    public String getExternalPanelStyle() {
        return externalPanelStyle;
    }

    public double getInternalPanelWidth() {
        return internalPanelWidth;
    }

    public double getInternalPanelHeight() {
        return internalPanelHeight;
    }

    public String getInternalPanelStyle() {
        return internalPanelStyle;
    }

    public double getScrollPanelLayoutX() {
        return scrollPanelLayoutX;
    }

    public double getScrollPanelLayoutY() {
        return scrollPanelLayoutY;
    }

    public double getScrollPanelWidth() {
        return scrollPanelWidth;
    }

    public double getScrollPanelHeight() {
        return scrollPanelHeight;
    }

    public String getScrollPanelStyle() {
        return scrollPanelStyle;
    }

    public ScrollBarPolicy getScrollPanelHbarPolicy() {
        return scrollPanelHbarPolicy;
    }

    public ScrollBarPolicy getScrollPanelVbarPolicy() {
        return scrollPanelVbarPolicy;
    }

    public double getImageViewWidth() {
        return imageViewWidth;
    }

    public double getImageViewLayoutX() {
        return imageViewLayoutX;
    }

    public double getImageViewLayoutY() {
        return imageViewLayoutY;
    }

    public boolean isImgViewPickedOnBounds() {
        return imgViewpickOnBounds;
    }

    public boolean isImgViewRatioPreserved() {
        return imgViewpreserveRatio;
    }

    public String getImageViewResourceName() {
        return imageViewResourceName;
    }

    public String getButtonText() {
        return buttonText;
    }

    public double getButtonLayoutX() {
        return buttonLayoutX;
    }

    public double getButtonLayoutY() {
        return buttonLayoutY;
    }

    public double getPlayer1PanelLayoutX() {
        return player1PanelLayoutX;
    }

    public double getPlayer1PanelLayoutY() {
        return player1PanelLayoutY;
    }

    public double getPlayer1PanelWidth() {
        return player1PanelWidth;
    }

    public double getPlayer1PanelHeight() {
        return player1PanelHeight;
    }

    public String getPlayer1PanelStyle() {
        return player1PanelStyle;
    }

    public double getImageViewBottomWidth() {
        return imageViewBottomWidth;
    }

    public double getImageViewBottomHeight() {
        return imageViewBottomHeight;
    }

    public double getImageViewBottomLayoutX() {
        return imageViewBottomLayoutX;
    }

    public double getImageViewBottomLayoutY() {
        return imageViewBottomLayoutY;
    }

    public boolean isImgViewBttmPickedOnBounds() {
        return imgViewBttmpickOnBounds;
    }

    public boolean isImgViewBttmRatioPreserved() {
        return imgViewBttmpreserveRatio;
    }

    public double getImgViewBttmRotate() {
        return imgViewBttmRotate;
    }

    public String getImgViewBttmResourceName() {
        return imgViewBttmResourceName;
    }
    
    
}
