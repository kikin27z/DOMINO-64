package manejadorLobby;

import implementacion.Client;
import eventoBase.Evento;
import eventoBaseError.EventoError;
import eventoBaseError.TipoError;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.ReglasDTO;
import entidadesDTO.UnirseDTO;
import eventos.EventoJugador;
import eventos.EventoLobby;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import lobbyBuilder.BuilderEventoLobby;
import lobbyBuilder.DirectorLobby;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ControlLobby extends IControlLobby implements Runnable {

    private int id;
    private DirectorLobby director;
    private final AtomicBoolean running;
    private final ManejadorLobby manejador;
    private final ExecutorService ejecutorEventos;

    public ControlLobby() {
        this.manejador = new ManejadorLobby();
        ejecutorEventos = Executors.newSingleThreadExecutor();
        running = new AtomicBoolean(true);
        setConsumers();
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
                Logger.getLogger(ControlLobby.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
                break;
            }
        }
    }

    /**
     * metodo para quitar un jugador de la partida. Si aun hay jugadores en la
     * partida, se envia un evento para notificar que el jugador salio. Si el
     * jugador era el ultimo que quedaba, se elimina la partida para que ya no
     * la manipule el manejador
     *
     * @param jugador Jugador a remover
     */
    @Override
    public void abandonoCuenta(Evento evento) {
        EventoJugador eventoRecibido = (EventoJugador) evento;
        int idDestinatario = evento.getIdPublicador();

        CuentaDTO cuentaAbandono = eventoRecibido.getCuenta();
        EventoLobby eventoEnviar;
        if (cuentaAbandono.esAdmin()) {
            System.out.println("Admin quiere salirse");
            manejador.abandonoAdmin();
            eventoEnviar = director.crearEventoAbandonoAdmin();
        } else {
            System.out.println("Un plebeyo se saldra");
            manejador.abandonoCuenta(cuentaAbandono);
            eventoEnviar = director.crearEventoCuentaAbandono(cuentaAbandono, idDestinatario);
        }
        cliente.enviarEvento(eventoEnviar);
    }

    @Override
    public void crearPartida(Evento evento) {
        System.out.println("en crear partida");
        EventoJugador eventoRecibido = (EventoJugador) evento;

        manejador.iniciarLobby();
        CuentaDTO cuentaDTO = manejador.unirCuenta(eventoRecibido.getCuenta());
        LobbyDTO lobby = manejador.devolverLobby();

        EventoLobby ev = director.crearEventoPartidaCreada(lobby, cuentaDTO);
        enviarEvento(ev);

    }

    @Override
    public void unirsePartida(Evento evento) {
        EventoJugador eventoJ = (EventoJugador) evento;
        CuentaDTO cuentaDTO = eventoJ.getCuenta();
        UnirseDTO unirse = eventoJ.getUnirse();
        String mensaje = manejador.verificacionCodigo(unirse);
        int idDestinatario = evento.getIdPublicador();
        System.out.println("Lo envio el jugador " + idDestinatario);
        if (mensaje == null) {
            unirJugador(cuentaDTO, idDestinatario);
        }else{
            notificarError(TipoError.ERROR_LOGICO, mensaje);
        }
    }
    
    private void notificarError(TipoError tipo, String msj){
        EventoError error = new EventoError(tipo, msj);
        cliente.enviarEvento(error);
    }

    private void unirJugador(CuentaDTO cuentaDTO, int destinatario) {
        CuentaDTO aux = manejador.unirCuenta(cuentaDTO);
        LobbyDTO lobby = manejador.devolverLobby();
        EventoLobby jugadorNuevo = director.crearEventoJugadorNuevo(lobby, aux, destinatario);
        cliente.enviarEvento(jugadorNuevo);
        
        lobby.setCuentaActual(aux);
        EventoLobby partidaEnc = director.crearEventoPartidaEncontrada(lobby, aux, destinatario);
        cliente.enviarEvento(partidaEnc);

    }

    @Override
    public void cambiarAvatar(Evento evento) {
        EventoJugador evJ = (EventoJugador) evento;
        CuentaDTO jActualizado = evJ.getCuenta();
        manejador.actualizarCambioAvatar(jActualizado);
        
        EventoLobby evLobby = director.crearEventoActualizarAvatar(jActualizado, evJ.getIdPublicador());
        enviarEvento(evLobby);
    }

    @Override
    public void manejarError(Evento evento) {
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

    private void vincularCliente(Client _cliente) {
        this.cliente = _cliente;
        cliente.establecerSuscripciones(eventos);
        _cliente.iniciar();
        id = _cliente.getClientId();
        director = new DirectorLobby(new BuilderEventoLobby(), id);
        ejecutorEventos.submit(this);
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
    public int devolverIdCliente() {
        return id;
    }

    @Override
    public void cuentaLista(Evento evento) {
        EventoJugador eventoRecibido = (EventoJugador) evento;
        int idDestinatario = evento.getIdPublicador();

        CuentaDTO cuentaLista = eventoRecibido.getCuenta();
        EventoLobby eventoEnviar;

        System.out.println("Un plebeyo se alisto");
        manejador.agregarJugadorListo(cuentaLista);
        eventoEnviar = director.crearEventoActualizarJugadoresListos(manejador.devolverLobby(), cuentaLista, idDestinatario, true);

        cliente.enviarEvento(eventoEnviar);

        if(manejador.todosListos()){
            ReglasDTO reglas = manejador.iniciarPartida();
            EventoLobby iniciarPartida = director.crearEventoPrepararPartida(reglas);
            cliente.enviarEvento(iniciarPartida);
        }
    }

    @Override
    public void cuentaNoLista(Evento evento) {
        EventoJugador eventoRecibido = (EventoJugador) evento;
        int idDestinatario = evento.getIdPublicador();

        CuentaDTO cuentaNoLista = eventoRecibido.getCuenta();
        EventoLobby eventoEnviar;

        System.out.println("Un plebeyo se se alisto");
        manejador.removerJugadorListo(cuentaNoLista);
        eventoEnviar = director.crearEventoActualizarJugadoresListos(manejador.devolverLobby(),
                cuentaNoLista, idDestinatario, false);

        cliente.enviarEvento(eventoEnviar);
    }

    @Override
    public void cambiarReglas(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
