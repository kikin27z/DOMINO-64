package partida;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.PosibleJugadaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaIniciadaDTO;
import entidadesDTO.TurnosDTO;
import eventosPartida.ObserverPartida;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import java.util.logging.Logger;
import eventosPartida.ObservablePartida;
import eventosPartida.ObservablePartidaMVC;
import eventosPartida.ObserverPartidaMVC;
import java.util.LinkedList;
import presentacion_dibujo.DibujoJugada;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaModel implements ObservablePartidaMVC, ObservablePartida {

    private static final Logger logger = Logger.getLogger(PartidaModel.class.getName());
    private Map<Canvas, FichaDTO> mapeoFichas;
    private Map<FichaDTO, PosibleJugadaDTO> jugadasPosibles;
    private final List<ObserverPartida> logicaObservers;
    private final List<ObserverPartidaMVC> viewObservers;
    private FichaDTO mulaAlta = new FichaDTO(-1, -1);
    private boolean esMiTurno;
    private boolean primeraJugadaHecha;
    private JugadaDTO jugadaActual;
    private FichaDTO fichaSeleccionada;
    private Canvas dibujoSeleccionado;
    private final CuentaDTO cuentaActual;
    private int numFichasIniciales;
    private LinkedList<String> ordenInicial;
    
    private List<CuentaDTO> jugadores;
    private PartidaIniciadaDTO partida;
    private JugadorDTO jugador;
    private int contadorFichasPozo;

    public PartidaModel(CuentaDTO cuenta) {
        cuentaActual = cuenta;
        mapeoFichas = new HashMap<>();
        this.esMiTurno = false;
        this.primeraJugadaHecha = false;
        logicaObservers = new ArrayList<>();
        viewObservers = new ArrayList<>();
        ordenInicial = new LinkedList<>();
        contadorFichasPozo = 28;
    }

    public CuentaDTO getCuentaActual() {
        return cuentaActual;
    }

    // ------------------------------Notificadores a Vista-----------------------------------------------------
    @Override
    public void agregarObserver(ObserverPartida observador) {
        this.logicaObservers.add(observador);
    }

    @Override
    public void quitarObserver(ObserverPartida observador) {
        this.logicaObservers.remove(observador);
    }

    //--------------Métodos notificadores de logica-------------------
    public JugadaRealizadaDTO crearJugadaRealizada(DibujoJugada dibujo) {
        JugadaRealizadaDTO jugada = new JugadaRealizadaDTO();
        jugada.setCoordenadaX(dibujo.getLayoutX());
        jugada.setCoordenadaY(dibujo.getLayoutY());
        jugada.setPosicion(dibujo.getPosicion());
        jugada.setFicha(fichaSeleccionada);
        jugada.setExtremoIzq(dibujo.isExtremoIzq());
        return jugada;
    }

    public boolean esLaMulaAlta(FichaDTO ficha) {
        return mulaAlta.esLaMismaFicha(ficha);
    }
    
    public void setPartida(PartidaIniciadaDTO partida){
        this.partida = partida;
        this.jugador = partida.getJugadorActual();
        jugadores = partida.getJugadores();
        numFichasIniciales = jugador.getFichas().size();
        contadorFichasPozo-= (jugadores.size()*numFichasIniciales);
        if(partida.esPrimerTurno(cuentaActual)){
            esMiTurno = true;
        }
        jugadores.remove(jugador.getCuenta());
    }
//    
    public List<CuentaDTO> getJugadores(){
        return jugadores;
    }
    
    public int getCantidadJugadores(){
        return partida.getCantidadJugadores();
    }
    
    public int getCantidadFichasPorJugador(){
        return numFichasIniciales;
    }
    
    public JugadorDTO getJugador(){
        return jugador;
    }

    public PosibleJugadaDTO obtenerPosibleJugada(FichaDTO ficha) {
        
        return jugadaActual.determinarJugada(ficha);
    }

    public void agregarMapeoFichas(Canvas dibujo, FichaDTO ficha) {
        mapeoFichas.put(dibujo, ficha);
    }

    public FichaDTO obtenerFicha(Canvas dibujo) {
        this.dibujoSeleccionado = dibujo;
        this.fichaSeleccionada = mapeoFichas.get(dibujo);
        return fichaSeleccionada;
    }

    public void quitarMapeoFichas() {
        mapeoFichas.remove(dibujoSeleccionado);
        fichaSeleccionada = null;
        dibujoSeleccionado = null;
    }

    public JugadaDTO getJugadaActual() {
        return jugadaActual;
    }

    public List<Canvas> obtenerDibujos() {
        List<Canvas> dibujosFicha = new ArrayList<>(mapeoFichas.keySet());
        return dibujosFicha;
    }

    public FichaDTO obtenerFichaExacta(Canvas dibujo) {
        return mapeoFichas.get(dibujo);
    }

    public Canvas getDibujoSeleccionado() {
        return dibujoSeleccionado;
    }

    public void setDibujoSeleccionado(Canvas dibujoSeleccionado) {
        this.dibujoSeleccionado = dibujoSeleccionado;
    }

    public Map<Canvas, FichaDTO> getMapeoFichas() {
        return mapeoFichas;
    }

    public void setMapeoFichas(Map<Canvas, FichaDTO> mapeoFichas) {
        this.mapeoFichas = mapeoFichas;
    }

    public boolean esMiTurno() {
        return esMiTurno;
    }

    public boolean esPrimeraJugadaHecha() {
        return primeraJugadaHecha;
    }

    public void primeraJugadaHecha(){
        primeraJugadaHecha = true;
    }
    
    public String queJugadorEs(int i) {
        return ordenInicial.get(i);
    }

    private void establecerOrdenVisual(LinkedList<String> idCuentas) {
        int indice = idCuentas.indexOf(cuentaActual.getIdCadena());

        while (indice > 0) {
            String primerElemento = idCuentas.removeFirst();
            idCuentas.addLast(primerElemento);
            indice--;
        }
        ordenInicial = idCuentas;
    }

    public int cuantosJugadoresSon() {
        return ordenInicial.size();
    }

    public int cuantasFichasInicialesFueron() {
        return numFichasIniciales;
    }

    @Override
    public void agregarObserver(ObserverPartidaMVC observador) {
        this.viewObservers.add(observador);
    }

    @Override
    public void quitarObserver(ObserverPartidaMVC observador) {
        this.viewObservers.remove(observador);
    }

    @Override
    public void actualizarDarFichas(List<FichaDTO> fichas) {
        for (var observer : viewObservers) {
            observer.actualizarDarFichas(fichas);
        }
    }

    @Override
    public void actualizarDarFicha(FichaDTO ficha) {
        for (var observer : viewObservers) {
            observer.actualizarDarFicha(ficha);
        }
    }

    public void designarMulaAlta() {
        mapeoFichas.values();
        for (Map.Entry<Canvas, FichaDTO> entry : mapeoFichas.entrySet()) {
            FichaDTO ficha = entry.getValue();
            if (ficha.esMula() && mulaAlta.sumaPuntos() < ficha.sumaPuntos()) {
                mulaAlta = ficha;
            }
        }
    }

    public int fichasRestantesPozoInicio() {
        return contadorFichasPozo;
//        return (28 - (ordenInicial.size() * numFichasIniciales));
    }

    @Override
    public void actualizarProximaJugada(JugadaDTO jugada) {
//        esMiTurno = true;
        this.jugadaActual = jugada;
        if(!primeraJugadaHecha){
            primeraJugadaHecha = true;
        }
        for (var observer : viewObservers) {
            observer.actualizarProximaJugada(jugada);
        }
        for (var observer : viewObservers) {
            observer.actualizarProximaJugada(jugada);
        }
    }

    @Override
    public void actualizarJugadorEnTurno() {
        esMiTurno = true;
//        for (var observer : viewObservers) {
//            observer.actualizarProximaJugada(jugada);
//        }
    }

    @Override
    public void actualizarJalarFicha() {
        System.out.println("Tienes que jalar ficha");
    }

    @Override
    public void actualizarJugadorEnTurno() {
        esMiTurno = true;
//        for (var observer : viewObservers) {
//            observer.actualizarProximaJugada(jugada);
//        }
    }

    @Override
    public void actualizarJalarFicha() {
        esMiTurno = true;
        System.out.println("Tienes que jalar ficha");
    }

    @Override
    public void actualizarJugadorAbandono(CuentaDTO cuenta) {
        System.out.println("Esta cuenta abandono " + cuenta);
    }

    @Override
    public void actualizarJugadorSeRindio(CuentaDTO cuenta) {
        System.out.println("Esta cuenta solicito rendirse" + cuenta);
    }

    @Override
    public void inicializarPartida(TurnosDTO turnos) {
        Map<String, JugadorDTO> mapeoJugadores = turnos.getMazos();
        JugadorDTO jugador = mapeoJugadores.get(cuentaActual.getIdCadena());
        List<FichaDTO> fichas = jugador.getFichas();
        
        
        this.numFichasIniciales = fichas.size();
        System.out.println("Las ficha de la cuenta " + cuentaActual);
        System.out.println("Son " + fichas);
        actualizarDarFichas(fichas);
        empiezaJugadorActual(turnos.getOrden());

        establecerOrdenVisual(turnos.getOrden());

        for (var observer : viewObservers) {
            observer.inicializarPartida(turnos);
        }
    }

    @Override
    public void avisarJalarFichaPozo() {
        for (ObserverPartida logicaObserver : logicaObservers) {
            logicaObserver.avisarJalarFichaPozo();
        }
    }

    @Override
    public void avisarAbandonarPartida() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void avisarPeticionRendirse() {
        for (ObserverPartida logicaObserver : logicaObservers) {
            logicaObserver.avisarPeticionRendirse();
        }
    }

    @Override
    public void actualizarTablero(JugadaRealizadaDTO jugada, CuentaDTO cuenta) {
        for (var observer : viewObservers) {
            observer.actualizarTablero(jugada);
        }
    }

    private void empiezaJugadorActual(LinkedList<String> orden) {
        String primerJugador = orden.getFirst();
        if (cuentaActual.getIdCadena().equalsIgnoreCase(primerJugador)) {
            esMiTurno = true;
            primeraJugadaHecha = false;
            designarMulaAlta();
        }
    }

    @Override
    public void avisarJugadaRealizada(JugadaRealizadaDTO jugada) {
        if(!primeraJugadaHecha){
            primeraJugadaHecha = true;
        }
        if(contadorFichasPozo >0)
            contadorFichasPozo--;
        esMiTurno = false;
        for(var observer : logicaObservers){
            observer.avisarJugadaRealizada(jugada);
        }
        
        for (var observer : viewObservers) {
            observer.actualizarTablero(jugada);
        }
    }
}
