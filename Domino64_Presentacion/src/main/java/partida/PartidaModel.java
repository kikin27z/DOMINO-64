package partida;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.JugadaValidaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import eventosPartida.ObserverPartida;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import presentacion_utilities.NotificadorEvento;
import java.util.logging.Logger;
import eventosPartida.ObservablePartida;
import eventosPartida.ObservablePartidaMVC;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaModel extends ObservablePartidaMVC implements ObservablePartida {
    protected final int DERECHA = 0;
    protected final int IZQUIERDA = 1;
    protected final int ARRIBA = 2;
    protected final int ABAJO = 3;
    private final String URL_MAZO_JUGADOR = "/images/mazoJugador.png";
    private final String PLAYER_STYLE = "-fx-background-color: #B2533E; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #000000;";
    private final double PLAYER_DECK_HEIGHT = 88;
    private final double PLAYER_DECK_WIDTH = 50;
    private final javafx.scene.text.Font CARD_COUNT_FONT = javafx.scene.text.Font.font("Verdana Bold", 40);
    private final javafx.scene.paint.Color CARD_COUNT_COLOR = javafx.scene.paint.Color.WHITE;
    private final javafx.geometry.Pos CARD_COUNT_ALIGNMENT = javafx.geometry.Pos.CENTER;
    private final double[] CARD_COUNT_PREF_SIZE = {60, 60};
    //--------------------------------------------
    private final double TOP_PLAYER_LAYOUT_X = 366;
    private final double TOP_PLAYER_LAYOUT_Y = 0;
    private final double[] TOP_PLAYER_PREF_SIZE = {268, 98};
    
    private final double TOP_PLAYER_DECK_LAYOUT_X = 109;
    private final double TOP_PLAYER_DECK_LAYOUT_Y = 7;
    private final double TOP_PLAYER_DECK_ROTATE =180;
    
    private final double TOP_PLAYER_AVATAR_HEIGHT = 199;
    private final double TOP_PLAYER_AVATAR_WIDTH = 100;
    private final double TOP_PLAYER_AVATAR_LAYOUT_X = -64;
    private final double TOP_PLAYER_AVATAR_LAYOUT_Y = 1;
    
    private final double TOP_PLAYER_CARD_COUNT_LAYOUT_X = 167;
    private final double TOP_PLAYER_CARD_COUNT_LAYOUT_Y = 22;
    
    private final double RIGTH_PLAYER_LAYOUT_X = 892;
    private final double RIGTH_PLAYER_LAYOUT_Y = 210;
    private final double[] RIGTH_PLAYER_PREF_SIZE = {98, 234};
    
    private final double RIGTH_PLAYER_DECK_LAYOUT_X = 24;
    private final double RIGTH_PLAYER_DECK_LAYOUT_Y = 95;
    private final double RIGTH_PLAYER_DECK_ROTATE = -90;
    
    
    private final double RIGTH_PLAYER_AVATAR_HEIGHT = 114;
    private final double RIGTH_PLAYER_AVATAR_WIDTH = 114;
    private final double RIGTH_PLAYER_AVATAR_LAYOUT_X = -13;
    private final double RIGTH_PLAYER_AVATAR_LAYOUT_Y = -34;
    
    private final double RIGTH_PLAYER_CARD_COUNT_LAYOUT_X = 21;
    private final double RIGTH_PLAYER_CARD_COUNT_LAYOUT_Y = 162;
    
    private final double LEFT_PLAYER_LAYOUT_X = 10;
    private final double LEFT_PLAYER_LAYOUT_Y = 210;
    private final double[] LEFT_PLAYER_PREF_SIZE = {98, 234};

    private final double LEFT_PLAYER_DECK_LAYOUT_X = 25;
    private final double LEFT_PLAYER_DECK_LAYOUT_Y = 86;
    private final double LEFT_PLAYER_DECK_ROTATE = 90;

    private final double LEFT_PLAYER_AVATAR_HEIGHT = 114;
    private final double LEFT_PLAYER_AVATAR_WIDTH = 114;
    private final double LEFT_PLAYER_AVATAR_LAYOUT_X = -6;
    private final double LEFT_PLAYER_AVATAR_LAYOUT_Y = -34;

    private final double LEFT_PLAYER_CARD_COUNT_LAYOUT_X = 16;
    private final double LEFT_PLAYER_CARD_COUNT_LAYOUT_Y =159;
    
    private final double BOTTOM_PLAYER_AVATAR_HEIGHT = 140;
    private final double BOTTOM_PLAYER_AVATAR_WIDTH = 140;
    private final double BOTTOM_PLAYER_AVATAR_LAYOUT_X = 856;
    private final double BOTTOM_PLAYER_AVATAR_LAYOUT_Y = 553;
    
    private final javafx.geometry.Pos BOTTOM_PLAYER_ALIGNMENT = javafx.geometry.Pos.CENTER;
    private final double BOTTOM_PLAYER_LAYOUT_X = 164;
    private final double BOTTOM_PLAYER_LAYOUT_Y = 598;
    private final double[] BOTTOM_PLAYER_PREF_SIZE = {630, 92};
    private final double BOTTOM_PLAYER_SPACING = 20;
    private final javafx.geometry.Insets BOTTOM_PLAYER_PADDING = new javafx.geometry.Insets(0,0,-12,20);
    
    private static final Logger logger = Logger.getLogger(PartidaModel.class.getName());
    private JugadorDTO jugador;
    private PartidaDTO partida;
    private List<CuentaDTO> jugadores;
    private Map<Canvas, FichaDTO> mapeoFichas;
    private final NotificadorEvento notificador;
    private final List<ObserverPartida> vistaObservers;

    public boolean esMiTurno;
    public boolean primeraJugadaHecha;
    public JugadaDTO jugada;
    
    public FichaDTO fichaSeleccionada;
    public JugadaValidaDTO jugadaValida;


    public PartidaModel() {
        notificador = NotificadorEvento.getInstance();
        mapeoFichas = new HashMap<>();
        this.esMiTurno = true;
        this.jugada = new JugadaDTO(3, 3);
        vistaObservers = new ArrayList<>();
        jugadores = new ArrayList<>();
    }
    //---------------------------getters y setters de config para view----------------------
    
    protected String getURL_MAZO_JUGADOR() {
        return URL_MAZO_JUGADOR;
    }
    
    protected String getPlayerStyle(){
        return PLAYER_STYLE;
    }

    protected double getPLAYER_DECK_HEIGHT() {
        return PLAYER_DECK_HEIGHT;
    }

    protected double getPLAYER_DECK_WIDTH() {
        return PLAYER_DECK_WIDTH;
    }

    protected Font getCARD_COUNT_FONT() {
        return CARD_COUNT_FONT;
    }

    protected Color getCARD_COUNT_COLOR() {
        return CARD_COUNT_COLOR;
    }

    protected Pos getCARD_COUNT_ALIGNMENT() {
        return CARD_COUNT_ALIGNMENT;
    }

    protected double[] getCARD_COUNT_PREF_SIZE() {
        return CARD_COUNT_PREF_SIZE;
    }

    protected Pos getBOTTOM_PLAYER_ALIGNMENT() {
        return BOTTOM_PLAYER_ALIGNMENT;
    }

    protected double getBOTTOM_PLAYER_SPACING() {
        return BOTTOM_PLAYER_SPACING;
    }

    protected Insets getBOTTOM_PLAYER_PADDING() {
        return BOTTOM_PLAYER_PADDING;
    }
    
    protected double getPlayerLayoutX(int posicion){
        double layoutX = 0;
        switch(posicion){
            case ARRIBA -> layoutX = TOP_PLAYER_LAYOUT_X;
            case ABAJO -> layoutX = BOTTOM_PLAYER_LAYOUT_X;
            case IZQUIERDA ->layoutX = LEFT_PLAYER_LAYOUT_X;
            case DERECHA -> layoutX = RIGTH_PLAYER_LAYOUT_X;
        }
        return layoutX;
    }
    
    protected double getPlayerLayoutY(int posicion){
        double layoutY = 0;
        switch(posicion){
            case ARRIBA -> layoutY = TOP_PLAYER_LAYOUT_Y;
            case ABAJO -> layoutY = BOTTOM_PLAYER_LAYOUT_Y;
            case IZQUIERDA ->layoutY = LEFT_PLAYER_LAYOUT_Y;
            case DERECHA -> layoutY = RIGTH_PLAYER_LAYOUT_Y;
        }
        return layoutY;
    }
    
    protected double[] getPlayerPrefSize(int posicion){
        double[] size = {0,0};
        switch(posicion){
            case ARRIBA -> size = TOP_PLAYER_PREF_SIZE;
            case ABAJO -> size = BOTTOM_PLAYER_PREF_SIZE;
            case IZQUIERDA ->size = LEFT_PLAYER_PREF_SIZE;
            case DERECHA -> size = RIGTH_PLAYER_PREF_SIZE;
        }
        return size;
    }
    
    protected double getPlayerDeckLayoutY(int posicion){
        double layoutY = 0;
        switch(posicion){
            case ARRIBA -> layoutY = TOP_PLAYER_DECK_LAYOUT_Y;
            case IZQUIERDA ->layoutY = LEFT_PLAYER_DECK_LAYOUT_Y;
            case DERECHA -> layoutY = RIGTH_PLAYER_DECK_LAYOUT_Y;
        }
        return layoutY;
    }
    
    protected double getPlayerDeckLayoutX(int posicion){
        double layoutX = 0;
        switch(posicion){
            case ARRIBA -> layoutX = TOP_PLAYER_DECK_LAYOUT_X;
            case IZQUIERDA ->layoutX = LEFT_PLAYER_DECK_LAYOUT_X;
            case DERECHA -> layoutX = RIGTH_PLAYER_DECK_LAYOUT_X;
        }
        return layoutX;
    }
    
    protected double getPlayerDeckRotate(int posicion){
        double rotate = 0;
        switch(posicion){
            case ARRIBA -> rotate = TOP_PLAYER_DECK_ROTATE;
            case IZQUIERDA ->rotate = LEFT_PLAYER_DECK_ROTATE;
            case DERECHA -> rotate = RIGTH_PLAYER_DECK_ROTATE;
        }
        return rotate;
    }
    
    protected double getPlayerAvatarLayoutX(int posicion){
        double layoutX = 0;
        switch(posicion){
            case ARRIBA -> layoutX = TOP_PLAYER_AVATAR_LAYOUT_X;
            case ABAJO -> layoutX = BOTTOM_PLAYER_AVATAR_LAYOUT_X;
            case IZQUIERDA ->layoutX = LEFT_PLAYER_AVATAR_LAYOUT_X;
            case DERECHA -> layoutX = RIGTH_PLAYER_AVATAR_LAYOUT_X;
        }
        return layoutX;
    }
    
    protected double getPlayerAvatarLayoutY(int posicion){
        double layoutY = 0;
        switch(posicion){
            case ARRIBA -> layoutY = TOP_PLAYER_AVATAR_LAYOUT_Y;
            case ABAJO -> layoutY = BOTTOM_PLAYER_AVATAR_LAYOUT_Y;
            case IZQUIERDA ->layoutY = LEFT_PLAYER_AVATAR_LAYOUT_Y;
            case DERECHA -> layoutY = RIGTH_PLAYER_AVATAR_LAYOUT_Y;
        }
        return layoutY;
    }
    
    protected double getPlayerAvatarHeight(int posicion){
        double height = 0;
        switch(posicion){
            case ARRIBA -> height = TOP_PLAYER_AVATAR_HEIGHT;
            case ABAJO -> height = BOTTOM_PLAYER_AVATAR_HEIGHT;
            case IZQUIERDA ->height = LEFT_PLAYER_AVATAR_HEIGHT;
            case DERECHA -> height = RIGTH_PLAYER_AVATAR_HEIGHT;
        }
        return height;
    }
    
    protected double getPlayerAvatarWidth(int posicion){
        double width = 0;
        switch(posicion){
            case ARRIBA -> width = TOP_PLAYER_AVATAR_WIDTH;
            case ABAJO -> width = BOTTOM_PLAYER_AVATAR_WIDTH;
            case IZQUIERDA ->width = LEFT_PLAYER_AVATAR_WIDTH;
            case DERECHA -> width = RIGTH_PLAYER_AVATAR_WIDTH;
        }
        return width;
    }
    
    protected double getPlayerCardCountLayoutX(int posicion){
        double layoutX = 0;
        switch(posicion){
            case ARRIBA -> layoutX = TOP_PLAYER_CARD_COUNT_LAYOUT_X;
            case IZQUIERDA ->layoutX = LEFT_PLAYER_CARD_COUNT_LAYOUT_X;
            case DERECHA -> layoutX = RIGTH_PLAYER_CARD_COUNT_LAYOUT_X;
        }
        return layoutX;
    }
    
    protected double getPlayerCardCountLayoutY(int posicion){
        double layoutY = 0;
        switch(posicion){
            case ARRIBA -> layoutY = TOP_PLAYER_CARD_COUNT_LAYOUT_Y;
            case IZQUIERDA ->layoutY = LEFT_PLAYER_CARD_COUNT_LAYOUT_Y;
            case DERECHA -> layoutY = RIGTH_PLAYER_CARD_COUNT_LAYOUT_Y;
        }
        return layoutY;
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
    public void agregarMapeoFichas(Canvas dibujo, FichaDTO ficha) {
        mapeoFichas.put(dibujo, ficha);
    }

    public void setPartida(PartidaDTO partida){
        this.partida = partida;
    }
    
    public PartidaDTO getPartida(){
        return partida;
    }
    
    public FichaDTO obtenerFicha(Canvas dibujo) {
        this.fichaSeleccionada = mapeoFichas.get(dibujo);
        return fichaSeleccionada;
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

    public void setJugadores(List<CuentaDTO> jugadores){
        this.jugadores = jugadores;
    }
    
    public List<CuentaDTO> getJugadores(){
        return jugadores;
    }
    
    public int getCantidadJugadores(){
        return jugadores.size();
    }
    
    public boolean esMiTurno() {
        return esMiTurno;
    }

    public void setEsMiTurno(boolean esMiTurno) {
        this.esMiTurno = esMiTurno;
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
    
    //---------------------------------------------
    
    public void removerJugador(JugadorDTO jugador){
        
    }
}
