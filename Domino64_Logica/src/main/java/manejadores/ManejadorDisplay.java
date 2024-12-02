package manejadores;

import domino64.eventos.base.Evento;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import entidadesDTO.PartidaIniciadaDTO;
import entidadesDTO.PosicionDTO;
import eventos.EventoJugador;
import eventos.EventoLobby;
import eventos.EventoPartida;
import eventos.EventoPozo;
import eventos.EventoTablero;
import eventos.EventoTurno;
import eventoss.EventoMVCDisplay;
import eventoss.EventoMVCJugador;
import eventosPantallas.ObserverPantalla;
import eventoss.TipoDisplayMVC;
import eventoss.TipoJugadorMVC;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import listener.ControlEventos;
import presentacion_utilities.ControladorComunicacion;
import presentacion_utilities.FachadaPresentacion;
import presentacion_utilities.INavegacion;
import presentacion_utilities.Navegacion;
import presentacion_utilities.NotificadorEvento;
import tiposLogicos.TipoLogicaLobby;
import tiposLogicos.TipoLogicaPartida;
import tiposLogicos.TipoLogicaTablero;
import tiposLogicos.TiposJugador;

/**
 *
 * @author karim
 */
public final class ManejadorDisplay implements ObserverPantalla{
//    private final NotificadorEvento notificador;
//    private final MediadorManejadores modeloLogica;
//    private FachadaPresentacion fachada;
//    private INavegacion navegacion;
   // private static final BlockingQueue<Evento> colaEventosAPresentacion = new LinkedBlockingQueue<>();
   // private static final Map<Enum, Consumer<Evento>> consumers = new ConcurrentHashMap<>();
    private CuentaDTO cuentaActual;
    private List<FichaDTO> fichasTablero;
    private List<FichaDTO> fichasJugador;
    
    public ManejadorDisplay() {
//        fachada = FachadaPresentacion.getInstance();
//        notificador = NotificadorEvento.getInstance();
//        modeloLogica = Control.obtenerManejadorModelo();
//        notificador.agregarObserver(this);
        fichasTablero = new ArrayList<>();
//        setConsumers();
        //procesarEventosAPresentacion();
    }

    public void iniciarJuego(){
//        navegacion = Navegacion.getInstance();
//        navegacion.iniciarApp();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ManejadorDisplay.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    //------------------------Eventos de cambiar pantallas--------------------
    
//    private void procesarEventosAPresentacion(){
//        new Thread(()->{
//            while(true){
//                try {
//                    Evento mensaje = colaEventosAPresentacion.take();
//                    if (!consumers.isEmpty()) {
//                        Consumer<Evento> consumer = consumers.get(mensaje.getTipo());
//                        if (consumer != null) {
//                            consumer.accept(mensaje);
//                        }
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//    
//    public void recibirEventoLogico(Evento evento){
//        colaEventosAPresentacion.offer(evento);
//    }
//    
//    private void setConsumers(){
//        consumers.putIfAbsent(TipoLogicaLobby.PARTIDA_ENCONTRADA, this::mostrarLobby);
//        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_NUEVO, this::agregarJugador);
//        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_SALIO, this::removerJugador);
//        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_NO_LISTO, this::actualizarJugadorListo);
//        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_LISTO, this::actualizarJugadorListo);
//        consumers.putIfAbsent(TiposJugador.ABANDONAR_PARTIDA, this::abandonarPartida);
//        consumers.putIfAbsent(TiposJugador.JUGADOR_LISTO, this::actualizarJugadorActualListo);
//        consumers.putIfAbsent(TiposJugador.JUGADOR_NO_LISTO, this::actualizarJugadorActualListo);
//        consumers.putIfAbsent(TiposJugador.CAMBIAR_AVATAR, this::actualizarAvatarJugadorActual);
//        consumers.putIfAbsent(TipoLogicaPartida.INICIO_PARTIDA, this::entrarAPartida);
//        consumers.putIfAbsent(TipoLogicaPartida.BUSCAR_PRIMERA_MULA, this::buscarMula);
//        consumers.putIfAbsent(TiposJugador.COLOCAR_FICHA, this::actualizarTablero);
//        consumers.putIfAbsent(TipoLogicaTablero.FICHA_COLOCADA, this::actualizarTablero);
//        Control.agregarConsumer(TipoJugadorMVC.IR_OPCIONES_PARTIDA, this::updateDisplay);
//        Control.agregarConsumer(TipoJugadorMVC.IR_INICIO, this::updateDisplay);
//    }
   
    public void cambioTurno(Evento evento){
        EventoTurno evTurno = (EventoTurno)evento;
        JugadorDTO jugador = evTurno.getJugador();
        
        if(jugador.getFichas()!= null){
            ControlEventos.mensajesEnTurno(jugador.getFichas().isEmpty());
        }
    }
    
    public void fichaObtenida(Evento evento){
        EventoPozo evPozo = (EventoPozo)evento;
        FichaDTO ficha = evPozo.getFicha();
        fichasJugador.add(ficha);
        ControlEventos.mensajeFichasActualizadas(fichasJugador);
    }
    
    public void actualizarTablero(Evento evento){
        JugadaRealizadaDTO jugada;
        if(evento instanceof EventoTablero evTablero){
            evTablero = (EventoTablero)evento;
            jugada = evTablero.getJugada();
        }else{
            EventoJugador eventoJugador = (EventoJugador)evento;
            jugada = eventoJugador.getJugada();
        }
        FichaDTO ficha = jugada.getFicha();
        
        if(fichasTablero.isEmpty()){
            fichasTablero.add(ficha);
        }else{
            PosicionDTO posicion = jugada.getPosicion();
            if(posicion.equals(PosicionDTO.DERECHA))
                fichasTablero.addLast(ficha);
            else
                fichasTablero.addFirst(ficha);
        }
        ControlEventos.mensajeTableroActualizado(fichasTablero);
    }
    
    public void entrarAPartida(Evento evento){
        EventoPartida partidaIniciada = (EventoPartida)evento;
        PartidaIniciadaDTO dto = partidaIniciada.getPartida();
        
        JugadorDTO jugador = partidaIniciada.getJugador();
        fichasJugador = jugador.getFichas();
        
        ControlEventos.mensajeMostrarTurnos(dto.getTurnos());
        ControlEventos.mensajeFichasActualizadas(fichasJugador);
        
        String idCadena = dto.getPrimerTurno().getIdCadena();
        if (!idCadena.equals(cuentaActual.getIdCadena())) {
            List<FichaDTO> fichasValidas = new ArrayList<>();
            for (FichaDTO ficha : fichasJugador) {
                if (ficha.esMula()) {
                    fichasValidas.add(ficha);
                }
            }
            JugadaRealizadaDTO jugada = ControlEventos.mensajeColocarPrimeraFicha(fichasValidas);
            FichaDTO ficha = jugada.getFicha();
            fichasJugador.remove(ficha);
            ControlEventos.mensajeFichasActualizadas(fichasJugador);
            ControlEventos.mensajeTableroActualizado(fichasJugador);
        }
        
        ControlEventos.mensajesEnPartida();
        
//        EventoMVCDisplay irPartida = new EventoMVCDisplay(TipoDisplayMVC.IR_PARTIDA);
//        irPartida.setJugador(partidaIniciada.getJugador());
//        fachada.cambiarPantalla(irPartida);
    }
    
    
    public void buscarMula(Evento evento){
        ControlEventos.mensajesBuscarMula(cuentaActual, true);
    }
    
    public void actualizarAvatarJugadorActual(Evento evento){
        EventoJugador ev = (EventoJugador)evento;
//        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
//        eventoDisplay.setTipo(TipoDisplayMVC.ACTUALIZAR_AVATARES);
//        eventoDisplay.setCuenta(ev.getCuenta());
//        fachada.actualizarLobby(eventoDisplay);
    }
    
    public void updateDisplay(EventoMVCJugador evento){
        TipoJugadorMVC tipo = evento.getTipo();
        switch (tipo) {
            case IR_INICIO -> mostrarInicio();
            case IR_OPCIONES_PARTIDA -> mostrarOpcionesPartida();
        }
    }
    
    public void agregarJugador(Evento evento){
        EventoLobby ev = (EventoLobby)evento;
//        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
//        eventoDisplay.setTipo(TipoDisplayMVC.AGREGAR_JUGADOR);
//        eventoDisplay.setCuenta(ev.getPublicador());
//        fachada.actualizarLobby(eventoDisplay);
    }
    
    public void actualizarJugadorActualListo(Evento evento){
        EventoJugador ev = (EventoJugador)evento;
//        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
//        TiposJugador tipo = ev.getTipo();
//        if(tipo.equals(TiposJugador.JUGADOR_LISTO)){
//            eventoDisplay.setTipo(TipoDisplayMVC.AGREGAR_JUGADOR_LISTO);
//        }else {
//            eventoDisplay.setTipo(TipoDisplayMVC.REMOVER_JUGADOR_LISTO);
//        }
//        
//        eventoDisplay.setCuenta(ev.getCuenta());
//        fachada.actualizarLobby(eventoDisplay);
     }
    
    public void actualizarJugadorListo(Evento evento){
        EventoLobby ev = (EventoLobby)evento;
//        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
//        TipoLogicaLobby tipo = ev.getTipo();
//        
//        if(tipo.equals(TipoLogicaLobby.JUGADOR_LISTO)){
//            eventoDisplay.setTipo(TipoDisplayMVC.AGREGAR_JUGADOR_LISTO);
//        }else if(tipo.equals(TipoLogicaLobby.JUGADOR_NO_LISTO))
//            eventoDisplay.setTipo(TipoDisplayMVC.REMOVER_JUGADOR_LISTO);
//            
//        eventoDisplay.setCuenta(ev.getPublicador());
//        fachada.actualizarLobby(eventoDisplay);
    }
    
    public void removerJugador(Evento evento){
        EventoLobby ev = (EventoLobby)evento;
//        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
//        eventoDisplay.setTipo(TipoDisplayMVC.REMOVER_JUGADOR);
//        eventoDisplay.setCuenta(ev.getPublicador());
//        fachada.actualizarLobby(eventoDisplay);
    }
    
    public void mostrarLobby(Evento evento){
        EventoLobby eventoLobby = (EventoLobby) evento;
        System.out.println("\nevento: " + eventoLobby);

        LobbyDTO lobbyDTO = eventoLobby.obtenerLobby();
        cuentaActual = lobbyDTO.getCuentaActual();
        ControlEventos.mensajesLobby(false);
        
//        EventoLobby ev = (EventoLobby)evento;
//        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
//        eventoDisplay.setTipo(TipoDisplayMVC.IR_LOBBY);
//        eventoDisplay.setLobby(ev.obtenerLobby());
//        System.out.println("lobby del display: "+eventoDisplay.getLobby());
//        fachada.cambiarPantalla(eventoDisplay);
    }
    
    public void abandonarPartida(Evento evento){
        mostrarOpcionesPartida();
    }
    
    public void mostrarInicio(){
//        navegacion.cambiarInicio();
    }
    
    public void mostrarOpcionesPartida(){
//        fachada.cambiarPantalla(new EventoMVCDisplay(TipoDisplayMVC.IR_OPCIONES_PARTIDA));
    }
    
    @Override
    public void avisarMostrarInicio() {
//        fachada.cambiarPantalla(new EventoMVCDisplay(TipoDisplayMVC.IR_INICIO));
        //navegacion.cambiarInicio();
    }

    @Override
    public void avisarMostrarPartida() {
//        fachada.cambiarPantalla(new EventoMVCDisplay(TipoDisplayMVC.IR_PARTIDA));
        //navegacion.cambiarPartida();
    }

    @Override
    public void avisarMostrarOpcionesPartida() {
//        fachada.cambiarPantalla(new EventoMVCDisplay(TipoDisplayMVC.IR_OPCIONES_PARTIDA));
        //navegacion.cambiarOpcionesPartida();
        //modeloLogica.crearObserverOpcionesPartida();
    }

    @Override
    public void avisarMostrarCreditos() {
    }

    @Override
    public void avisarMostrarFinJuego() {
    }

    @Override
    public void avisarMostrarLobby(LobbyDTO lobby) {
//        fachada.cambiarPantalla(new EventoMVCDisplay(TipoDisplayMVC.IR_LOBBY));
        //navegacion.cambiarLobby();
        //modeloLogica.crearObserverLobby();
    }
    
}
