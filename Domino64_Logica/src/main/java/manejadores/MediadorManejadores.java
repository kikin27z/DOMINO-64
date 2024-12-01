package manejadores;

import domino64.eventos.base.Evento;
import entidadesDTO.CuentaDTO;
import logicaLobby.ManejadorCuenta;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.UnirseDTO;
import eventos.EventoLobby;
import eventoss.EventoMVCDisplay;
import eventoss.EventoMVCJugador;
import eventosLobby.ObserverLobby;
import eventosOpcionesPartida.ObserverOpcionesPartida;
import eventosPartida.ObserverPartida;
import eventoss.TipoJugadorMVC;
import java.util.List;
import java.util.concurrent.ExecutorService;
import javafx.application.Platform;
import listener.ControlEventos;
import logicaPartida.ManejadorJugador;
import presentacion_utilities.ControladorComunicacion;
import presentacion_utilities.INotificadorEvento;
import presentacion_utilities.MediadorModelos;
import presentacion_utilities.NotificadorEvento;
import tiposLogicos.TipoLogicaLobby;
import tiposLogicos.TipoLogicaPartida;
import tiposLogicos.TiposJugador;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class MediadorManejadores {
    private static MediadorManejadores instance;
    private static ManejadorDisplay display;
    private static ManejadorCuenta cuenta;
    private static ManejadorJugador jugador;
    //private final INotificadorEvento notificador;
    //private final ExecutorService hiloPrincipal;
    private final MediadorModelos mediadorModelos;
    private final ObserverPartida observerPartida;
    private final ObserverOpcionesPartida observerOpciones;
    private final ObserverLobby observerLobby;

    private MediadorManejadores() {
        cuenta = new ManejadorCuenta();
        display = new ManejadorDisplay();
        jugador = new ManejadorJugador();
        //cuenta.setManejadorDisplay(display);
        
        mediadorModelos = MediadorModelos.getInstance();
        //notificador = NotificadorEvento.getInstance();
        //hiloPrincipal = Control.getHiloPrincipal();
        
        observerOpciones = new  ControlEventosOpcionesPartida();
        mediadorModelos.agregarObserverOpciones(observerOpciones);
        
        observerPartida = new  ControlEventosPartida();
        mediadorModelos.agregarObserverPartida(observerPartida);
        
        observerLobby = new ControlEventosLobby();
        mediadorModelos.agregarObserverLobby(observerLobby);
        
        setConsumersManejadorCuenta();
        setConsumersManejadorJugador();
        setConsumersManejadorDisplay();
    }
    
    private void setConsumersManejadorCuenta() {
        ControlEventos.agregarConsumerLogica(TipoJugadorMVC.CREAR_PARTIDA, cuenta::crearPartida);
        ControlEventos.agregarConsumerLogica(TipoJugadorMVC.UNIRSE_PARTIDA, cuenta::buscarPartida);
        ControlEventos.agregarConsumerLogica(TipoJugadorMVC.ABANDONAR_LOBBY, cuenta::abandonarLobby);
        ControlEventos.agregarConsumerLogica(TipoJugadorMVC.JUGADOR_LISTO, cuenta::actualizarJugadorListo);
        ControlEventos.agregarConsumerLogica(TipoJugadorMVC.JUGADOR_NO_LISTO, cuenta::actualizarJugadorListo);
        ControlEventos.agregarConsumerLogica(TipoJugadorMVC.CAMBIAR_AVATAR, cuenta::actualizarAvatar);
        ControlEventos.agregarConsumerLogica(TipoJugadorMVC.CAMBIAR_CONFIG_PARTIDA, cuenta::actualizarConfigPartida);
    }
    
    private void setConsumersManejadorJugador() {
        ControlEventos.agregarConsumerLogica(TipoJugadorMVC.COLOCAR_FICHA,jugador::colocarFicha);
        ControlEventos.agregarConsumerLogica(TipoJugadorMVC.ABANDONAR_PARTIDA, jugador::abandonarPartida);
        ControlEventos.agregarConsumerLogica(TipoJugadorMVC.PASAR_TURNO, jugador::pasarTurno);
        ControlEventos.agregarConsumerLogica(TipoJugadorMVC.PETICION_RENDIRSE, jugador::peticionRendirse);
        
    }
    
    private void setConsumersManejadorDisplay() {
        ControlEventos.agregarConsumerPresentacion(TipoLogicaLobby.PARTIDA_ENCONTRADA, display::mostrarLobby);
        ControlEventos.agregarConsumerPresentacion(TipoLogicaLobby.JUGADOR_NUEVO, display::agregarJugador);
        ControlEventos.agregarConsumerPresentacion(TipoLogicaLobby.JUGADOR_SALIO, display::removerJugador);
        ControlEventos.agregarConsumerPresentacion(TipoLogicaLobby.JUGADOR_NO_LISTO, display::actualizarJugadorListo);
        ControlEventos.agregarConsumerPresentacion(TipoLogicaLobby.JUGADOR_LISTO, display::actualizarJugadorListo);
        ControlEventos.agregarConsumerPresentacion(TiposJugador.ABANDONAR_PARTIDA, display::abandonarPartida);
        ControlEventos.agregarConsumerPresentacion(TiposJugador.JUGADOR_LISTO, display::actualizarJugadorActualListo);
        ControlEventos.agregarConsumerPresentacion(TiposJugador.JUGADOR_NO_LISTO, display::actualizarJugadorActualListo);
        ControlEventos.agregarConsumerPresentacion(TiposJugador.CAMBIAR_AVATAR, display::actualizarAvatarJugadorActual);
        ControlEventos.agregarConsumerPresentacion(TipoLogicaPartida.INICIO_PARTIDA, display::entrarAPartida);
        ControlEventos.agregarConsumerPresentacion(TipoLogicaPartida.BUSCAR_PRIMERA_MULA, display::buscarMula);
        ControlEventos.agregarConsumerLogica(TipoJugadorMVC.IR_OPCIONES_PARTIDA, display::updateDisplay);
        ControlEventos.agregarConsumerLogica(TipoJugadorMVC.IR_INICIO, display::updateDisplay);
    }
    
    public static synchronized MediadorManejadores getInstance(){
        if(instance == null){
            instance = new MediadorManejadores();
        }
        return instance;
    }
    
    public static ManejadorCuenta getManejadorCuenta(){
        return cuenta;
    }
    
    public static ManejadorJugador getManejadorJugador(){
        return jugador;
    }
    
    public static ManejadorDisplay getManejadorDisplay(){
        return display;
    }
    
    public static void enviarADisplay(Evento evento){
        display.recibirEventoLogico(evento);
    }
    
    public static void enviarACuenta(Evento evento){
        //cuenta.recibirEventoPresentacion(evento);
    }
    
    public void crearObserverOpcionesPartida() {
//        observerOpciones = new  ControlEventosOpcionesPartida();
//        notificador.asignarObserverOpcionesPartida(observerOpciones);
    }
    public void crearObserverPartida() {
//        observerPartida = new  ControlEventosPartida();
//        notificador.asignarObserverPartida(observerPartida);
    }
    public void crearObserverLobby() {
//        observerLobby = new ControlEventosLobby();
//        notificador.asignarObserverLobby(observerLobby);
    }

    private class ControlEventosOpcionesPartida implements ObserverOpcionesPartida {

        @Override
        public void crearPartida() {
            EventoMVCJugador evento = new EventoMVCJugador();
            evento.setTipo(TipoJugadorMVC.CREAR_PARTIDA);
            ControladorComunicacion.enviarEventoALogica(evento);
//            hiloPrincipal.execute(() -> {
//                cuenta.crearPartida();
//            });
        }

        @Override
        public void buscarPartida(UnirseDTO unirse) {
            EventoMVCJugador evento = new EventoMVCJugador();
            evento.setTipo(TipoJugadorMVC.UNIRSE_PARTIDA);
            evento.setLobby(new LobbyDTO(unirse.getCodigoPartida()));
            ControladorComunicacion.enviarEventoALogica(evento);
//            hiloPrincipal.execute(() -> {
//                cuenta.buscarPartida(codigoPartida);
//            });
        }
    }

    private class ControlEventosPartida implements ObserverPartida {
        
        @Override
        public void avisarJugadaRealizada(JugadaRealizadaDTO jugadaDTO) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void avisarFichaSeleccionada(FichaDTO contexto) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void avisarDarFichas(List<FichaDTO> fichas) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void avisarDarFicha(FichaDTO ficha) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }

    private class ControlEventosLobby implements ObserverLobby {
        
        @Override
        public void avisarJugadorListo() {
            EventoMVCJugador evento = new EventoMVCJugador(TipoJugadorMVC.JUGADOR_LISTO);
            ControladorComunicacion.enviarEventoALogica(evento);
        }

        @Override
        public void avisarJugadorNoListo() {
            EventoMVCJugador evento = new EventoMVCJugador(TipoJugadorMVC.JUGADOR_NO_LISTO);
            ControladorComunicacion.enviarEventoALogica(evento);
        }

        @Override
        public void avisarIniciarPartida() {
        }

        @Override
        public void avisarAbandonar(LobbyDTO lobby) {
            EventoMVCJugador evento = new EventoMVCJugador(TipoJugadorMVC.ABANDONAR_PARTIDA);
            ControladorComunicacion.enviarEventoALogica(evento);
            
            //Platform.runLater(()->{
//            System.out.println(Thread.currentThread().getName());
//                Control.obtenerManejadorDisplay().avisarMostrarOpcionesPartida();
//            //});
//            hiloPrincipal.execute(() -> {
//                cuenta.abandonarPartida(lobby);
//            });
        }

        @Override
        public void avisarCambioAvatar(CuentaDTO cuentaActualizada) {
            EventoMVCJugador evento = new EventoMVCJugador(TipoJugadorMVC.CAMBIAR_AVATAR);
            evento.setPublicador(cuentaActualizada);
            ControladorComunicacion.enviarEventoALogica(evento);
        }

        @Override
        public void avisarCambioConfig(LobbyDTO lobbyAct) {
            EventoMVCJugador evento = new EventoMVCJugador(TipoJugadorMVC.CAMBIAR_CONFIG_PARTIDA);
            evento.setLobby(lobbyAct);
            ControladorComunicacion.enviarEventoALogica(evento);
        }

    }

}
