package comunicadores_logica;

import domino64.eventos.base.Evento;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import eventos.EventoLobby;
import implementacion.Client;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadores.Control;
import manejadores.ManejadorCuenta;
import manejadores.ManejadorDisplay;
import presentacion_utilities.DistribuidorEventosModelo;
import utilities.BuilderEventoSuscripcion;
import utilities.DirectorSuscripcion;

/**
 *
 * @author karim
 */
public class ReceptorLogica extends IReceptorEventosLogica implements Runnable {

    private int id;
    private static ExecutorService ejecutorEventos;
    private DirectorSuscripcion directorSuscripciones;
    private ManejadorDisplay display;
    private DistribuidorEventosModelo distribuidor;
    private AtomicBoolean running;
    private ManejadorCuenta manejadorCuenta;

    public ReceptorLogica() {
        super();
        distribuidor = DistribuidorEventosModelo.getInstance();
        setConsumers();
        ejecutorEventos = Executors.newSingleThreadExecutor();
        running = new AtomicBoolean(true);
    }

    @Override
    public void vincularDisplay() {
        display = Control.obtenerManejadorDisplay();
    }

    public void vincularCliente(Client _cliente) {
        this.cliente = _cliente;
        cliente.establecerSuscripciones(eventos);
        _cliente.iniciar();
        id = _cliente.getClientId();
        directorSuscripciones = new DirectorSuscripcion(new BuilderEventoSuscripcion(), id);
        ejecutorEventos.submit(this);
    }

    @Override
    public void iniciaConexion() {
        Client c = Client.iniciarComunicacion();

        for (Enum<?> suscripcion : eventos) {
            c.addObserver(suscripcion, this);
        }

        this.vincularCliente(c);
    }

    @Override
    public void recibirPartida(Evento evento) {
//        System.out.println("partida recibida");
//        Enum<?> tipo = evento.getTipo();
//        if (tipo.equals(TipoLogicaLobby.PARTIDA_ENCONTRADA)) {
//            EventoLobby eventoLobby = (EventoLobby) evento;
//            System.out.println("evento: " + eventoLobby);
//
//            lobbyDTO = eventoLobby.obtenerLobby();
//            manejador.asignarCuenta(lobbyDTO.getCuentaActual());
//
//            System.out.println("lobbyDTO: " + lobbyDTO);
////            MediadorManejadores.enviarADisplay(eventoLobby);
//
//            removerSuscripcion(TipoLogicaLobby.PARTIDA_ENCONTRADA);
//        }
    }



    @Override
    public void actualizarAvatares(Evento evento) {
        
        System.out.println("Jugador actualizo su avatar");
//        EventoLobby ev = esEventoDeEsteLobby(evento);
//        if( ev != null){
//            CuentaDTO jugadorEvento = ev.getPublicador();
//            
//            for (CuentaDTO cuentaDTO : jugadoresLobby) {
//                if(cuentaDTO.equals(jugadorEvento)){
//                    cuentaDTO.setAvatar(jugadorEvento.getAvatar());
//                    MediadorManejadores.enviarADisplay(ev);
//                    break;
//                }
//            }
//        }
    }
    @Override
    public void manejarError(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int devolverIdCliente() {
        return id;
    }

    @Override
    public void partidaCreada(Evento evento) {
        EventoLobby eventoRecibido = (EventoLobby) evento;
        if(evento.getIdDestinatario() != id){
            return;
        }
        LobbyDTO lobby = eventoRecibido.obtenerLobby();
        
        System.out.println("cuentas--"+ lobby.getCuentas());
        
        CuentaDTO aux = eventoRecibido.getPublicador();
        manejadorCuenta.asignarCuenta(aux);
        CuentaDTO cuentaDTO = manejadorCuenta.getCuenta();
        display.mostrarLobby(cuentaDTO);
        
        distribuidor.inicializarLobby(lobby);
    }

    @Override
    public void partidaEncontrada(Evento evento) {
        EventoLobby eventoRecibido = (EventoLobby) evento;
//        if(evento.getIdDestinatario() != id){
//            return;
//        }
        
        System.out.println("\n");
        System.out.println("Evento a logica partida creada " + eventoRecibido);
        LobbyDTO lobby = eventoRecibido.obtenerLobby();
        CuentaDTO aux = eventoRecibido.getPublicador();
        
        if(eventoRecibido.getIdDestinatario() == id){
            manejadorCuenta.asignarCuenta(aux);
            CuentaDTO cuenta = manejadorCuenta.getCuenta();
            display.mostrarLobby(cuenta);
        }
        distribuidor.inicializarLobby(lobby);
    }

    @Override
    public void errorUnirse(Evento evento) {
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                Evento nextEvent = colaEventos.take();
                Consumer<Evento> cons = consumers.get(nextEvent.getTipo());
                if (cons != null) {
                    cons.accept(nextEvent);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                Logger.getLogger(ReceptorLogica.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
                break;
            }
        }
    }



    @Override
    public void vincularCuenta() {
        manejadorCuenta = Control.obtenerManejadorCuenta();
    }

    @Override
    public void adminAbandono(Evento evento) {
        EventoLobby eventoLobby  = (EventoLobby) evento;
        System.out.println(eventoLobby);
        manejadorCuenta.borrarPerfil();
        display.mostrarInicio();
    }

    @Override
    public void cuentaAbandono(Evento evento) {
        EventoLobby eventoLobby  = (EventoLobby) evento;
        CuentaDTO cuentaAbandono = eventoLobby.getPublicador();
        System.out.println(eventoLobby);
        
        if(eventoLobby.getIdDestinatario() == id){
            manejadorCuenta.borrarPerfil();
            display.mostrarInicio();
        }else{
            distribuidor.actualizarQuitarCuenta(cuentaAbandono);
        }
        
    }

    @Override
    public void cuentaLista(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cuentaNoLista(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
