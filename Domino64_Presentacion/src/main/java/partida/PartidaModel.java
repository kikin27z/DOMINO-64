package partida;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.JugadaValidaDTO;
import entidadesDTO.JugadorDTO;
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

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaModel implements ObservablePartidaMVC, ObservablePartida {

    private static final Logger logger = Logger.getLogger(PartidaModel.class.getName());
    private JugadorDTO jugador;
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
}
