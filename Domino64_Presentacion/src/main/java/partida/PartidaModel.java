package partida;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.JugadaValidaDTO;
import entidadesDTO.JugadorDTO;
import eventosPartida.EventosPartida;
import eventosPartida.ObserverPartida;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import presentacion_utilities.NotificadorEvento;
import java.util.logging.Logger;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaModel implements EventosPartida {

    private static final Logger logger = Logger.getLogger(PartidaModel.class.getName());
    private JugadorDTO jugador;
    private Map<Canvas, FichaDTO> mapeoFichas;
    private NotificadorEvento notificador;
    private final List<ObserverPartida> vistaObservers;

    public boolean esMiTurno;
    public boolean primeraJugadaHecha;
    public JugadaDTO jugada;
    
    public FichaDTO fichaSeleccionada;
    public JugadaValidaDTO jugadaValida;

    public final int JUGADOR_ACTUALIZADO = 0;
    public final int PARTIDA_ACTUALIZADA = 1;
    public final int FICHA_SELECCIONADA = 2;

    private final double externalPanelWidth = 1000;
    private final double externalPanelHeight = 700;
    private final String externalPanelStyle = "-fx-background-color: #186F65;";

    private final double internalPanelWidth = 830;
    private final double internalPanelHeight = 550;
    private final String internalPanelStyle = "-fx-background-color: #B5CB99;";

    private final double scrollPanelLayoutX = 85;
    private final double scrollPanelLayoutY = 75;
    private final double scrollPanelWidth = 830;
    private final double scrollPanelHeight = 550;
    private final String scrollPanelStyle = "-fx-background-color: transparent; "
            + "-fx-background: transparent;";
    private final ScrollBarPolicy scrollPanelHbarPolicy = ScrollBarPolicy.NEVER;
    private final ScrollBarPolicy scrollPanelVbarPolicy = ScrollBarPolicy.NEVER;

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
    private final String player1PanelStyle = "-fx-background-color: #B2533E;"
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
        notificador = NotificadorEvento.getInstance();
        mapeoFichas = new HashMap<>();
        this.esMiTurno = true;
        this.jugada = new JugadaDTO(6, 0);
        vistaObservers = new ArrayList<>();
    }
    // ------------------------------Notificadores a Vista-----------------------------------------------------
    
    @Override
    public void agregarObserver(ObserverPartida observador) {
        this.vistaObservers.add(observador);
    }

    @Override
    public void quitarObserver(ObserverPartida observador) {
        this.vistaObservers.remove(observador);
    }

    @Override
    public void avisarJugadaRealizada(JugadaRealizadaDTO jugadaDTO) {
        for(var observer : vistaObservers){
            observer.avisarJugadaRealizada(jugadaDTO);
        }
    }

    @Override
    public void avisarFichaSeleccionada(FichaDTO ficha) {
        for(var observer : vistaObservers){
            observer.avisarFichaSeleccionada(ficha);
        }
    }

    @Override
    public void avisarDarFichas(List<FichaDTO> fichas) {
        for(var observer : vistaObservers){
            observer.avisarDarFichas(fichas);
        }
    }

    @Override
    public void avisarDarFicha(FichaDTO ficha) {
        for(var observer : vistaObservers){
            observer.avisarDarFicha(ficha);
        }
    }
    //--------------Métodos notificadores de logica-------------------
     
    ///---------------------------------Eventos para logica-----------------------------------------------------------------
    
    
    
    
    //-----------------------------------------------------
    public void insertarIzqTablero(FichaDTO ficha) {
//        tablero.setExtremoIzq(ficha);
    }

    public void agregarMapeoFichas(Canvas dibujo, FichaDTO ficha) {
        mapeoFichas.put(dibujo, ficha);
    }

    public FichaDTO obtenerFicha(Canvas dibujo) {
        this.fichaSeleccionada = mapeoFichas.get(dibujo);
        return fichaSeleccionada;
    }

    public boolean es1raFichaDespuesDeMulaIzq() {
//        return tablero.getExtremoIzq() == null;
        return false;
    }

    public boolean es1raFichaDespuesDeMulaDer() {
//        return tablero.getExtremoDer() == null;
        return false;
    }

    public void quitarMapeoFichas(Canvas dibujo) {
        mapeoFichas.remove(dibujo);
    }

    public JugadaDTO getJugada() {
        return jugada;
    }

    public void setJugada(JugadaDTO jugada) {
        this.jugada = jugada;
    }

    public boolean esMiTurno() {
        return esMiTurno;
    }

    public void setEsMiTurno(boolean esMiTurno) {
        this.esMiTurno = esMiTurno;
    }

    public String obtenerNumeroFichaCuenta(CuentaDTO cuenta) {
//        return String.valueOf(cuenta.getJugador().numFichas());
        return null;
    }

    public List<Canvas> obtenerDibujos() {
        List<Canvas> dibujosFicha = new ArrayList<>(mapeoFichas.keySet());
        return dibujosFicha;
    }

    public FichaDTO obtenerFichaExacta(Canvas dibujo) {
        return mapeoFichas.get(dibujo);
    }

    public Map<Canvas, FichaDTO> getMapeoFichas() {
        return mapeoFichas;
    }

    public void setMapeoFichas(Map<Canvas, FichaDTO> mapeoFichas) {
        this.mapeoFichas = mapeoFichas;
    }

    public JugadorDTO getJugador() {
        return jugador;
    }

    public List<FichaDTO> getFichasDelJugador() {
        return jugador.getFichas();
    }

    public void setJugador(JugadorDTO jugador) {
        this.jugador = jugador;
    }

    public double getExternalPanelWidth() {
        return externalPanelWidth;
    }

    public double getExternalPanelHeight() {
        return externalPanelHeight;
    }

    
    public void actualizarJugada(int num, int opcion){
        if(opcion == 0){
            this.jugada.setIzquierda(num);
            this.jugada.setDerecha(num);
        }else if(opcion == 1){
            this.jugada.setIzquierda(num);
        }else{
            this.jugada.setDerecha(num);
        }
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
