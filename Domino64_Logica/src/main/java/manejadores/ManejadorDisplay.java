package manejadores;

import domino64.eventos.base.Evento;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import eventos.EventoJugador;
import eventos.EventoLobby;
import eventoss.EventoMVCDisplay;
import eventoss.EventoMVCJugador;
import eventosPantallas.ObserverPantalla;
import eventoss.TipoDisplayMVC;
import eventoss.TipoJugadorMVC;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion_utilities.ControladorComunicacion;
import presentacion_utilities.FachadaPresentacion;
import presentacion_utilities.INavegacion;
import presentacion_utilities.Navegacion;
import presentacion_utilities.NotificadorEvento;
import tiposLogicos.TipoLogicaLobby;
import tiposLogicos.TiposJugador;

/**
 *
 * @author karim
 */
public final class ManejadorDisplay implements ObserverPantalla{
    private final NotificadorEvento notificador;
    private final MediadorManejadores modeloLogica;
    private FachadaPresentacion fachada;
    private INavegacion navegacion;
    private static final BlockingQueue<Evento> colaEventosAPresentacion = new LinkedBlockingQueue<>();
    private static final Map<Enum<?>, Consumer<Evento>> consumers = new ConcurrentHashMap<>();
    
    public ManejadorDisplay() {
        fachada = FachadaPresentacion.getInstance();
        notificador = NotificadorEvento.getInstance();
        modeloLogica = Control.obtenerManejadorModelo();
        notificador.agregarObserver(this);
        setConsumers();
        procesarEventosAPresentacion();
    }

    public void iniciarJuego(){
        navegacion = Navegacion.getInstance();
        navegacion.iniciarApp();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ManejadorDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //------------------------Eventos de cambiar pantallas--------------------
    
    private void procesarEventosAPresentacion(){
        new Thread(()->{
            while(true){
                try {
                    Evento mensaje = colaEventosAPresentacion.take();
                    if (!consumers.isEmpty()) {
                        Consumer<Evento> consumer = consumers.get(mensaje.getTipo());
                        if (consumer != null) {
                            consumer.accept(mensaje);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    public void recibirEventoLogico(Evento evento){
        colaEventosAPresentacion.offer(evento);
    }
    
    private void setConsumers(){
        consumers.putIfAbsent(TipoLogicaLobby.PARTIDA_ENCONTRADA, this::mostrarLobby);
        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_NUEVO, this::agregarJugador);
        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_SALIO, this::removerJugador);
        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_NO_LISTO, this::actualizarJugadorListo);
        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_LISTO, this::actualizarJugadorListo);
        consumers.putIfAbsent(TiposJugador.ABANDONAR_PARTIDA, this::abandonarPartida);
        consumers.putIfAbsent(TiposJugador.JUGADOR_LISTO, this::actualizarJugadorActualListo);
        consumers.putIfAbsent(TiposJugador.JUGADOR_NO_LISTO, this::actualizarJugadorActualListo);
        consumers.putIfAbsent(TiposJugador.CAMBIAR_AVATAR, this::actualizarAvatarJugadorActual);
        Control.agregarConsumer(TipoJugadorMVC.IR_OPCIONES_PARTIDA, this::updateDisplay);
        Control.agregarConsumer(TipoJugadorMVC.IR_INICIO, this::updateDisplay);
    }
    
    private void actualizarAvatarJugadorActual(Evento evento){
        EventoJugador ev = (EventoJugador)evento;
        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
        eventoDisplay.setTipo(TipoDisplayMVC.ACTUALIZAR_AVATARES);
        eventoDisplay.setCuenta(ev.getCuenta());
        fachada.actualizarLobby(eventoDisplay);
    }
    
    private void updateDisplay(EventoMVCJugador evento){
        TipoJugadorMVC tipo = evento.getTipo();
        switch (tipo) {
            case IR_INICIO -> mostrarInicio();
            case IR_OPCIONES_PARTIDA -> mostrarOpcionesPartida();
        }
    }
    
    private void agregarJugador(Evento evento){
        EventoLobby ev = (EventoLobby)evento;
        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
        eventoDisplay.setTipo(TipoDisplayMVC.AGREGAR_JUGADOR);
        eventoDisplay.setCuenta(ev.getPublicador());
        fachada.actualizarLobby(eventoDisplay);
    }
    
    private void actualizarJugadorActualListo(Evento evento){
        EventoJugador ev = (EventoJugador)evento;
        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
        TiposJugador tipo = ev.getTipo();
        if(tipo.equals(TiposJugador.JUGADOR_LISTO)){
            eventoDisplay.setTipo(TipoDisplayMVC.AGREGAR_JUGADOR_LISTO);
        }else {
            eventoDisplay.setTipo(TipoDisplayMVC.REMOVER_JUGADOR_LISTO);
        }
        
        eventoDisplay.setCuenta(ev.getCuenta());
        fachada.actualizarLobby(eventoDisplay);
     }
    
    private void actualizarJugadorListo(Evento evento){
        EventoLobby ev = (EventoLobby)evento;
        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
        TipoLogicaLobby tipo = ev.getTipo();
        
        if(tipo.equals(TipoLogicaLobby.JUGADOR_LISTO)){
            eventoDisplay.setTipo(TipoDisplayMVC.AGREGAR_JUGADOR_LISTO);
        }else if(tipo.equals(TipoLogicaLobby.JUGADOR_NO_LISTO))
            eventoDisplay.setTipo(TipoDisplayMVC.REMOVER_JUGADOR_LISTO);
            
        eventoDisplay.setCuenta(ev.getPublicador());
        fachada.actualizarLobby(eventoDisplay);
    }
    
    private void removerJugador(Evento evento){
        EventoLobby ev = (EventoLobby)evento;
        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
        eventoDisplay.setTipo(TipoDisplayMVC.REMOVER_JUGADOR);
        eventoDisplay.setCuenta(ev.getPublicador());
        fachada.actualizarLobby(eventoDisplay);
    }
    
    private void mostrarLobby(Evento evento){
        EventoLobby ev = (EventoLobby)evento;
        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
        eventoDisplay.setTipo(TipoDisplayMVC.IR_LOBBY);
        eventoDisplay.setLobby(ev.obtenerLobby());
        System.out.println("lobby del display: "+eventoDisplay.getLobby());
        fachada.cambiarPantalla(eventoDisplay);
    }
    
    private void abandonarPartida(Evento evento){
        mostrarOpcionesPartida();
    }
    
    private void mostrarInicio(){
        navegacion.cambiarInicio();
    }
    
    private void mostrarOpcionesPartida(){
        fachada.cambiarPantalla(new EventoMVCDisplay(TipoDisplayMVC.IR_OPCIONES_PARTIDA));
    }
    
    @Override
    public void avisarMostrarInicio() {
        fachada.cambiarPantalla(new EventoMVCDisplay(TipoDisplayMVC.IR_INICIO));
        //navegacion.cambiarInicio();
    }

    @Override
    public void avisarMostrarPartida() {
        fachada.cambiarPantalla(new EventoMVCDisplay(TipoDisplayMVC.IR_PARTIDA));
        //navegacion.cambiarPartida();
    }

    @Override
    public void avisarMostrarOpcionesPartida() {
        fachada.cambiarPantalla(new EventoMVCDisplay(TipoDisplayMVC.IR_OPCIONES_PARTIDA));
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
        fachada.cambiarPantalla(new EventoMVCDisplay(TipoDisplayMVC.IR_LOBBY));
        //navegacion.cambiarLobby();
        //modeloLogica.crearObserverLobby();
    }
    
}
