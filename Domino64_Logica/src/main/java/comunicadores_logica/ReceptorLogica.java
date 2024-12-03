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
import tiposLogicos.TipoLogicaLobby;
import utilities.BuilderEventoSuscripcion;
import utilities.DirectorSuscripcion;

/**
 *
 * @author karim
 */
public class ReceptorLogica extends IReceptorEventosLogica implements Runnable {
    private int id;
    private static ExecutorService ejecutorEventos;
    private ManejadorDisplay display;
    private DistribuidorEventosModelo distribuidor;
    private AtomicBoolean running;
    private ManejadorCuenta manejadorCuenta;

    public ReceptorLogica() {
        super();
        distribuidor = DistribuidorEventosModelo.getInstance();
        //setConsumers();
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
        directorSuscripcion = new DirectorSuscripcion(id);
        ejecutorEventos.submit(this);
        setConsumers();
    }

    @Override
    public void iniciaConexion() {
        Client c = Client.iniciarComunicacion();

        for (Enum suscripcion : eventos) {
            c.addObserver(suscripcion, this);
        }

        this.vincularCliente(c);
    }




    @Override
    public void actualizarAvatares(Evento evento) {
        
        System.out.println("Jugador actualizo su avatar");
        EventoLobby eventoL = (EventoLobby)evento;
        CuentaDTO cuenta = eventoL.getPublicador();
        distribuidor.actualizarAvatarCuenta(cuenta);
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
//        if(evento.getIdDestinatario() != id){
//            return;
//        }
        LobbyDTO lobby = eventoRecibido.obtenerLobby();
        System.out.println("\ncodigo partida: "+lobby.getCodigo()+"\n");
        System.out.println("cuentas--"+ lobby.getCuentas());
        
        removerSuscripcion(TipoLogicaLobby.PARTIDA_CREADA);
        CuentaDTO aux = eventoRecibido.getPublicador();
        manejadorCuenta.asignarCuenta(aux);
        CuentaDTO cuentaDTO = manejadorCuenta.getCuenta();
        lobby.setCuentaActual(cuentaDTO);
        display.mostrarLobby(cuentaDTO, lobby);
        
//        distribuidor.inicializarLobby(lobby);
    }

    @Override
    public void partidaEncontrada(Evento evento) {
        EventoLobby eventoRecibido = (EventoLobby) evento;
        
        System.out.println("\n");
        System.out.println("Evento a logica partida creada " + eventoRecibido);
        LobbyDTO lobby = eventoRecibido.obtenerLobby();
        CuentaDTO aux = eventoRecibido.getPublicador();
        //ya no va a recibir los eventos de partida encontrada
        removerSuscripcion(TipoLogicaLobby.PARTIDA_ENCONTRADA);
        
        manejadorCuenta.asignarCuenta(aux);
        CuentaDTO cuenta = manejadorCuenta.getCuenta();
        lobby.setCuentaActual(cuenta);
        display.mostrarLobby(cuenta, lobby);
//        distribuidor.inicializarLobby(lobby);
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
        EventoLobby evLobby = (EventoLobby)evento;
        
        CuentaDTO cuentaNueva = evLobby.getPublicador();
        distribuidor.actualizarCuentaLista(cuentaNueva);
    }

    @Override
    public void cuentaNoLista(Evento evento) {
        EventoLobby evLobby = (EventoLobby)evento;
        
        CuentaDTO cuentaNueva = evLobby.getPublicador();
        distribuidor.actualizarCuentaNoLista(cuentaNueva);
    }

    @Override
    public void cuentaEntro(Evento evento) {
        EventoLobby evLobby = (EventoLobby)evento;
        
        CuentaDTO cuentaNueva = evLobby.getPublicador();
        distribuidor.actualizarNuevoJugador(cuentaNueva);
    }
    
}
