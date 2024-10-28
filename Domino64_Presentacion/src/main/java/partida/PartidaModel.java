package partida;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import entidadesDTO.PozoDTO;
import entidadesDTO.TableroDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import observer_logica.EventoLogica;
import observer_logica.ObservableLogica;
import observer_logica.ObservadorLogica;
import observer_MVC.EventoMVC;
import observer_MVC.ObservableMVC;


/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaModel extends ObservableMVC implements ObservableLogica{
    private List<ObservadorLogica> observadores = new ArrayList<>();
    private JugadorDTO jugador;
    private PartidaDTO partida;
    private TableroDTO tablero = new TableroDTO();
    private PozoDTO pozo;
    private Map<Canvas, FichaDTO> mapeoFichas;
    
    public final int JUGADOR_ACTUALIZADO=0;
    public final int PARTIDA_ACTUALIZADA=1;
    public final int FICHA_SELECCIONADA=2;
    
    private final double externalPanelWidth = 1000;
    private final double externalPanelHeight = 700;
    private final String externalPanelStyle = "-fx-background-color: #186F65;";
    
    private final double internalPanelWidth = 1800;
    private final double internalPanelHeight = 1500;
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
    
    
    public PartidaModel() {
        mapeoFichas = new HashMap<>();
        List<FichaDTO> fichas = new ArrayList<>();
        fichas.add(new FichaDTO(3,2));
        
    }


    public PartidaModel(JugadorDTO jugador, PartidaDTO partida) {
        mapeoFichas = new HashMap<>();
//        mapeoFichasJugadas = new HashMap<>();
        this.jugador = jugador;
        this.partida = partida;
        List<FichaDTO> fichas = new ArrayList<>();
        fichas.add(new FichaDTO(3,2));
        
        this.agregarFichas(fichas);
    }
    

    
     //--------------Métodos notificadores-------------------
    public void agregarFichas(List<FichaDTO> fichas){
        EventoMVC<List<FichaDTO>> evento = new EventoMVC<>("fichasMazo", fichas);
        this.notificarObservadores(evento);
    }
    public void agregarFicha(FichaDTO ficha){
        EventoMVC<FichaDTO> evento = new EventoMVC<>("fichaMazo", ficha);
        this.notificarObservadores(evento);
    }
    
    public void agregarFichaAlTablero(FichaDTO ficha, TableroDTO tablero, boolean izquierda){
        this.tablero = tablero;
//        notificarAgregarFichaAlTablero(ficha, izquierda);
    }
    
    
    
     //-----------------------------------------------------
    
    public void insertarIzqTablero(FichaDTO ficha){
//        tablero.setExtremoIzq(ficha);
    }
    
    
    public void agregarMapeoFichas(Canvas dibujo, FichaDTO ficha){
        mapeoFichas.put(dibujo, ficha);
    }
    
    public boolean es1raFichaDespuesDeMulaIzq(){
//        return tablero.getExtremoIzq() == null;
return false;
    }
    public boolean es1raFichaDespuesDeMulaDer(){
//        return tablero.getExtremoDer() == null;
return false;
    }
    
    public void quitarMapeoFichas(Canvas dibujo){
        mapeoFichas.remove(dibujo);
    }
    
    
    public PartidaDTO getPartida() {
        return partida;
    }
    
    
    public String obtenerNumeroFichaCuenta(CuentaDTO cuenta){
//        return String.valueOf(cuenta.getJugador().numFichas());
return null;
    }
    public List<Canvas> obtenerDibujos(){
        List<Canvas> dibujosFicha = new ArrayList<>(mapeoFichas.keySet());
        return dibujosFicha;
    }
    
    public FichaDTO obtenerFichaExacta(Canvas dibujo){
        return mapeoFichas.get(dibujo);
    }
    
    public Map<Canvas, FichaDTO> getMapeoFichas() {
        return mapeoFichas;
    }

    public void setMapeoFichas(Map<Canvas, FichaDTO> mapeoFichas) {
        this.mapeoFichas = mapeoFichas;
    }
    
    public void setGame(PartidaDTO partida) {
        this.partida = partida;
//        this.notifyObservers(this, PARTIDA_ACTUALIZADA);
    }
    
    public JugadorDTO getJugador() {
        return jugador;
    }

    public TableroDTO getTablero() {
        return tablero;
    }
    
    
    
    
    public List<FichaDTO> getFichasDelJugador(){
        return jugador.getFichas();
    }

    public void setJugador(JugadorDTO jugador) {
        this.jugador = jugador;
//        this.notifyObservers(this, JUGADOR_ACTUALIZADO);
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

    
    // Notificaciones a lógica
    @Override
    public void agregarObservador(ObservadorLogica observador) {
        observadores.add(observador);
    }

    @Override
    public void eliminarObservador(ObservadorLogica observador) {
         observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(EventoLogica<?> evento) {
        for (ObservadorLogica observador : observadores) {
            observador.notificar(evento);
        }
    }
}
