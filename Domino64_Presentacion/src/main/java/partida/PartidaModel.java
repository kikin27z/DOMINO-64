package partida;

//import entidades.Ficha;
import com.mycompany.patrones.observer.Observable;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import entidadesDTO.TableroDTO;
import java.util.List;
//import entidades.Jugador;
//import entidades.Partida;
//import entidades.Tablero;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import static java.util.Map.entry;
import java.util.Set;
//import exceptions.DominioException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import presentacion_utilities.NotificadorPresentacion;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaModel extends Observable<PartidaModel>{
    private JugadorDTO jugador;
    private PartidaDTO partida;
    private boolean jugadorEnTurno;
    private List<FichaDTO> fichasValidas;
    private Map<Canvas, FichaDTO> mapeoFichas;
    private Map<Canvas, FichaDTO> mapeoFichasJugadas;
    private FichaDTO fichaSeleccionada;
    NotificadorPresentacion notificador;
    public final int JUGADOR_ACTUALIZADO=0;
    public final int PARTIDA_ACTUALIZADA=1;
    public final int FICHA_SELECCIONADA=2;
    
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


    public PartidaModel(NotificadorPresentacion notificador) {
        this.notificador = notificador;
        mapeoFichas = new HashMap<>();
        mapeoFichasJugadas = new HashMap<>();
        jugador = new JugadorDTO();
        partida = new PartidaDTO();
    }

    public PartidaModel(JugadorDTO jugador, PartidaDTO partida) {
        mapeoFichas = new HashMap<>();
        mapeoFichasJugadas = new HashMap<>();
        this.jugador = jugador;
        this.partida = partida;
    }

    public void setFichasValidas(List<FichaDTO> fichasValidas){
        if(jugadorEnTurno()){
            this.fichasValidas = fichasValidas;
            this.notifyObservers(this);
        }
    }
    
    protected EventHandler<MouseEvent> getEventHandler() {
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Canvas canvas = (Canvas) t.getSource();
                FichaDTO fichaSeleccionada = getFichaSeleccionada(canvas);
                System.out.println("ficha selec: " + fichaSeleccionada);
                setFichaSeleccionada(fichaSeleccionada);
//                Platform.runLater(()->{
//                    view.iluminarFicha(canvas);
//                });
            }
        };
        return event;
    }
    
    public void setJugadorEnTurno(boolean flag){
        this.jugadorEnTurno = flag;
    }
    
    public boolean jugadorEnTurno(){
        return jugadorEnTurno;
    }
    
    public PartidaDTO getPartida() {
        return partida;
    }

    /**
     * actualiza el mapeo de las fichas con su correspondiente
     * componente ImageView. 
     * @param mapeo a establecer en la variable de la clase
     */
    public void actualizarMapeo(Map<Canvas,FichaDTO> mapeo){
        mapeoFichas= mapeo;
    }
    
    public FichaDTO obtenerFichaSeleccionada(){
        return fichaSeleccionada;
//        FichaDTO ficha = null;
//        if(jugador.getFichaSeleccionada() != null){
//            ficha  = jugador.getFichaSeleccionada();
//        }
//        return ficha;
    }
    
    public FichaDTO obtenerPrimeraMulaTablero(){
        TableroDTO tablero = partida.getTablero();
        FichaDTO ficha = null;
        if(!tablero.tableroVacio()){
            ficha = tablero.obtenerFichaDer();
        }
        return ficha;
    }
    
    /**
     * metodo para obtener la ficha seleccionada.
     * Este metodo se llama cada vez que se selecciona una ficha
     * del panel del jugador. Busca entre los mapeos Ficha-ImageView
     * para encontrar el valor de la ficha del imageView que genero 
     * el evento, es decir, el imageView que se selecciono
     * @param canvas seleccionado
     * @return la ficha mapeada al imageView del parametro
     */
    public FichaDTO getFichaSeleccionada(Canvas canvas){
        FichaDTO fichaSeleccionada = mapeoFichas.get(canvas);
        return fichaSeleccionada;
    }
    
    private void ponerFichaEnTablero(FichaDTO ficha){
        TableroDTO tablero = partida.getTablero();
        try {
            if(tablero.tableroVacio()){
                partida.getTablero().insertarFicha(ficha);
            }else{
                partida.getTablero().insertarFichaLadoDerecho(ficha);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Map<Canvas, FichaDTO> getMapeoFichas() {
        return mapeoFichas;
    }
    public Map<Canvas, FichaDTO> getMapeoFichasJugadas() {
        return mapeoFichasJugadas;
    }

    public void setMapeoFichas(Map<Canvas, FichaDTO> mapeoFichas) {
        this.mapeoFichas = mapeoFichas;
    }
    
    public void setGame(PartidaDTO partida) {
        this.partida = partida;
        this.notifyObservers(this, PARTIDA_ACTUALIZADA);
    }
    
    public JugadorDTO getJugador() {
        return jugador;
    }
    
    public void colocarFicha(){
        FichaDTO ultimaFicha = getFichasDelJugador().getLast();
        jugador.removerFicha(ultimaFicha);
        ponerFichaEnTablero(ultimaFicha);
        double imgViewLayoutX = imageViewLayoutX+50;
        //double imgViewLayoutY = imageViewLayoutY+50;
        this.notifyObservers(this,PARTIDA_ACTUALIZADA);
    }
    
    public List<FichaDTO> getFichasDelJugador(){
        return jugador.getFichas();
    }

    public void setJugador(JugadorDTO jugador) {
        this.jugador = jugador;
        this.notifyObservers(this, JUGADOR_ACTUALIZADO);
    }
    
    
    public void setFichaSeleccionada(FichaDTO ficha){
        fichaSeleccionada = ficha;
        notificador.notificar(this);
        this.notifyObservers(this, FICHA_SELECCIONADA);
    }
    
    public void actualizarMapeoFichasJugadas(Entry<Canvas,FichaDTO> fichasJugadas) {
        this.mapeoFichasJugadas.put(fichasJugadas.getKey(),fichasJugadas.getValue());
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
