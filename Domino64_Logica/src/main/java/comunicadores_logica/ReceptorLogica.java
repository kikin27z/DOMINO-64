package comunicadores_logica;

import eventoBase.Evento;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaIniciadaDTO;
import entidadesDTO.PosibleJugadaDTO;
import eventoBaseError.EventoError;
import eventoBaseError.TipoError;
import eventos.EventoLobby;
import eventos.EventoPartida;
import entidadesDTO.TurnosDTO;
import eventos.EventoJugadorFicha;
import eventos.EventoPozo;
import eventos.EventoTablero;
import eventos.EventoTurno;
import implementacion.Client;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadores.Control;
import manejadores.ManejadorCuenta;
import manejadores.ManejadorDisplay;
import manejadores.ManejadorJugador;
import presentacion_utilities.DistribuidorEventosModelo;
import tiposLogicos.TipoLogicaLobby;
import tiposLogicos.TipoLogicaPartida;
import tiposLogicos.TipoLogicaPozo;
import utilities.DirectorSuscripcion;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ReceptorLogica extends IReceptorEventosLogica implements Runnable {
    private int id;
    private static ExecutorService ejecutorEventos;
    private ManejadorDisplay display;
    private DistribuidorEventosModelo distribuidor;
    private AtomicBoolean running;
    private ManejadorCuenta manejadorCuenta;
    private ManejadorJugador manejadorJugador;

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

    @Override
    public void vincularJugador() {
        manejadorJugador = Control.obtenerManejadorJugador();
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
    }
    
    @Override
    public void manejarError(Evento evento) {
        EventoError error = (EventoError)evento;
        if(error.getTipo().equals(TipoError.ERROR_DE_SERVIDOR)){
            manejarErrorServidor(error);
        }else{
            errorUnirse(error);
        }
        
    }
    
    private void manejarErrorServidor(EventoError error){
        System.out.println("error: "+error.getMensaje());
        distribuidor.mostrarMensajeError("Ha ocurrido un error en el servidor");
        
        running.set(false);
        ejecutorEventos.shutdown();

        try {
            if (!ejecutorEventos.awaitTermination(5, TimeUnit.SECONDS)) {
                ejecutorEventos.shutdownNow();
            }
        } catch (InterruptedException e) {
            ejecutorEventos.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void errorUnirse(EventoError error) {
        distribuidor.mostrarMensajeError(error.getMensaje());
    }
    
    @Override
    public int devolverIdCliente() {
        return id;
    }

    @Override
    public void lobbyCreado(Evento evento) {
        EventoLobby eventoRecibido = (EventoLobby) evento;
        LobbyDTO lobby = eventoRecibido.obtenerLobby();
        System.out.println("\ncodigo partida: "+lobby.getCodigo()+"\n");
        System.out.println("cuentas--"+ lobby.getCuentas());
        
        agregarSuscripcion(TipoLogicaPartida.INICIO_PARTIDA, this::inicializarPartida);
        removerSuscripcion(TipoLogicaLobby.LOBBY_CREADO);
        CuentaDTO aux = eventoRecibido.getPublicador();
        manejadorCuenta.asignarCuenta(aux);
        CuentaDTO cuentaDTO = manejadorCuenta.getCuenta();
        lobby.setCuentaActual(cuentaDTO);
        display.mostrarLobby(cuentaDTO, lobby);
        
//        distribuidor.inicializarLobby(lobby);
    }

    @Override
    public void lobbyEncontrado(Evento evento) {
        EventoLobby eventoRecibido = (EventoLobby) evento;
        
        System.out.println("\n");
        System.out.println("Evento a logica partida creada " + eventoRecibido);
        LobbyDTO lobby = eventoRecibido.obtenerLobby();
        CuentaDTO aux = eventoRecibido.getPublicador();
        //ya no va a recibir los eventos de partida encontrada
        agregarSuscripcion(TipoLogicaPartida.INICIO_PARTIDA, this::inicializarPartida);
        removerSuscripcion(TipoLogicaLobby.LOBBY_ENCONTRADO);
        
        manejadorCuenta.asignarCuenta(aux);
        CuentaDTO cuenta = manejadorCuenta.getCuenta();
        lobby.setCuentaActual(cuenta);
        display.mostrarLobby(cuenta, lobby);
//        distribuidor.inicializarLobby(lobby);
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
        display.mostrarOpcionesPartida();
    }

    @Override
    public void cuentaAbandono(Evento evento) {
        EventoLobby eventoLobby  = (EventoLobby) evento;
        CuentaDTO cuentaAbandono = eventoLobby.getPublicador();
        System.out.println(eventoLobby);
        distribuidor.actualizarQuitarCuenta(cuentaAbandono);
//        if(eventoLobby.getIdDestinatario() == id){
//            manejadorCuenta.borrarPerfil();
//            display.mostrarInicio();
//        }else{
//            distribuidor.actualizarQuitarCuenta(cuentaAbandono);
//        }
        
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

    @Override
    public void jugadaRealizada(Evento evento) {
        EventoJugadorFicha er = (EventoJugadorFicha) evento;
        
        JugadaRealizadaDTO jugada = er.getJugada();
        CuentaDTO cuenta = er.getCuenta();
        distribuidor.jugadaRealizada(jugada, cuenta);
    }

    @Override
    public void jalarFicha(Evento evento) {
        distribuidor.actualizarJalarFicha();
    }

    @Override
    public void inicializarPartida(Evento evento) {
        EventoPartida er = (EventoPartida) evento;
        TurnosDTO turnos = er.getTurnos();
        
        CuentaDTO cuentaDTO = manejadorCuenta.getCuenta();
        display.mostrarPartida(cuentaDTO);
        distribuidor.inicializarPartida(turnos);
    }

    @Override
    public void proximaJugada(Evento evento) {
        EventoPartida prox = (EventoPartida)evento;
        JugadaDTO jugada = prox.getJugada();
        
        distribuidor.actualizarProximaJugada(jugada);
        
        if(manejadorCuenta.getCuenta().equals(prox.getCuenta())){
            
        }
    }
    
    @Override
    public void pasarTurno(Evento evento){
        distribuidor.mostrarMensajeError("El pozo no tiene fichas. Pasas turno");
    }

    @Override
    public void fichaObtenida(Evento evento) {
        EventoPozo fichaOb = (EventoPozo)evento;
        FichaDTO ficha = fichaOb.getFicha();
        distribuidor.actualizarDarFicha(ficha);
        removerSuscripcion(TipoLogicaPozo.FICHA_OBTENIDA);
        removerSuscripcion(TipoLogicaPozo.POZO_VACIO);
    }
    
}
