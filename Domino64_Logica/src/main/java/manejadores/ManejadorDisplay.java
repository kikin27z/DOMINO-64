package manejadores;

import entidadesDTO.CuentaDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion_utilities.DistribuidorEventosModelo;
import presentacion_utilities.INavegacion;
import presentacion_utilities.Navegacion;

/**
 *
 * @author karim
 */
public final class ManejadorDisplay {
    private INavegacion navegacion;
    private DistribuidorEventosModelo distribuidor;
    
    public ManejadorDisplay() {
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
    public void mostrarInicio(){
        navegacion.cambiarOpcionesPartida();
    }
    public void mostrarOpcionesPartida(){
        navegacion.cambiarOpcionesPartida();
    }

    public void mostrarLobby(CuentaDTO cuenta){
        navegacion.cambiarLobby(cuenta);
    }
    
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
//        Control.agregarConsumer(TipoJugadorMVC.IR_OPCIONES_PARTIDA, this::updateDisplay);
//        Control.agregarConsumer(TipoJugadorMVC.IR_INICIO, this::updateDisplay);
//    }
    
//    private void actualizarAvatarJugadorActual(Evento evento){
//        EventoJugador ev = (EventoJugador)evento;
//        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
//        eventoDisplay.setTipo(TipoDisplayMVC.ACTUALIZAR_AVATARES);
//        eventoDisplay.setCuenta(ev.getCuenta());
//        fachada.actualizarLobby(eventoDisplay);
//    }
//    
//    private void updateDisplay(EventoMVCJugador evento){
//        TipoJugadorMVC tipo = evento.getTipo();
//        switch (tipo) {
//            case IR_INICIO -> mostrarInicio();
//            case IR_OPCIONES_PARTIDA -> mostrarOpcionesPartida();
//        }
//    }
//    
//    private void agregarJugador(Evento evento){
//        EventoLobby ev = (EventoLobby)evento;
//        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
//        eventoDisplay.setTipo(TipoDisplayMVC.AGREGAR_JUGADOR);
//        eventoDisplay.setCuenta(ev.getPublicador());
//        fachada.actualizarLobby(eventoDisplay);
//    }
//    
//    private void actualizarJugadorActualListo(Evento evento){
//        EventoJugador ev = (EventoJugador)evento;
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
//     }
//    
//    private void actualizarJugadorListo(Evento evento){
//        EventoLobby ev = (EventoLobby)evento;
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
//    }
//    
//    private void removerJugador(Evento evento){
//        EventoLobby ev = (EventoLobby)evento;
//        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
//        eventoDisplay.setTipo(TipoDisplayMVC.REMOVER_JUGADOR);
//        eventoDisplay.setCuenta(ev.getPublicador());
//        fachada.actualizarLobby(eventoDisplay);
//    }
//    
//    private void mostrarLobby(Evento evento){
//        EventoLobby ev = (EventoLobby)evento;
//        EventoMVCDisplay eventoDisplay = new EventoMVCDisplay();
//        eventoDisplay.setTipo(TipoDisplayMVC.IR_LOBBY);
//        eventoDisplay.setLobby(ev.obtenerLobby());
//        System.out.println("lobby del display: "+eventoDisplay.getLobby());
//        fachada.cambiarPantalla(eventoDisplay);
//    }
//    
//    private void abandonarPartida(Evento evento){
//        mostrarOpcionesPartida();
//    }
//    
//    private void mostrarInicio(){
//        navegacion.cambiarInicio();
//    }
    
   
    
}
