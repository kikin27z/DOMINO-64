package com.domino64.manejador;

import abstraccion.ICliente;
import implementacion.Client;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import domino64.eventos.base.error.TipoError;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
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
 * @author luisa M
 */
public class ControlLobby extends IControlLobby implements Runnable {

    private static DirectorLobby director;
    private static int id;
    private final AtomicBoolean running;
    private static ExecutorService ejecutorEventos;
    private final ManejadorLobby manejador;

    public ControlLobby() {
        this.manejador = new ManejadorLobby();
        setConsumers();
        ejecutorEventos = Executors.newSingleThreadExecutor();
        running = new AtomicBoolean(true);
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
        EventoJugador eventoRecibido = (EventoJugador) evento;
        int idDestinatario = evento.getIdPublicador();

        manejador.iniciarLobby();
        CuentaDTO cuentaDTO = manejador.unirCuenta(eventoRecibido.getCuenta());
        LobbyDTO lobby = manejador.devolverLobby();

        EventoLobby ev = director.crearEventoPartidaCreada(lobby, cuentaDTO, idDestinatario);
        cliente.enviarEvento(ev);

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
        }

    }

    private void unirJugador(CuentaDTO cuentaDTO, int destinatario) {
        CuentaDTO aux = manejador.unirCuenta(cuentaDTO);
        LobbyDTO lobby = manejador.devolverLobby();
        EventoLobby ev = director.crearEventoPartidaEncontrada(lobby, aux, destinatario);
        cliente.enviarEvento(ev);

    }

    /**
     * metodo para notificar errores
     *
     * @param tipo El tipo de error a notificar
     * @param idJugador Id del suscriptor al que se va a notificar
     */
    private void notificarError(TipoError tipo, int idJugador, String msjError) {
        System.out.println("no se pudo unir a la partida");
        EventoError error = new EventoError(tipo, msjError);
        error.setIdPublicador(idJugador);

        cliente.enviarEvento(error);
    }

    @Override
    public void cambiarAvatar(Evento evento) {
//        EventoJugador evJ = (EventoJugador) evento;
//        CuentaDTO jActualizado = evJ.getCuenta();
//        LobbyDTO lobbyDTO = evJ.getLobby();
//        Lobby lobby = new Lobby(lobbyDTO.getCodigoPartida());
//        
//        if(jugadoresPartidas.containsKey(lobby)){
//            jugadoresPartidas.compute(lobby, (l,j)->{
//                for (Cuenta cuenta : j) {
//                    if(cuenta.getId() == jActualizado.getId()){
//                        cuenta.setAvatar(adaptadorDTO.adaptarAvatarDTO(jActualizado.getAvatar()));
//                        break;
//                    }
//                }
//                return j;
//            });
//            
//            EventoLobby evLobby = director.crearEventoActualizarAvatares(lobbyDTO,jActualizado);
//            cliente.enviarEvento(evLobby);
//
//        }
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

    public void vincularCliente(Client _cliente) {
        this.cliente = _cliente;
        cliente.establecerSuscripciones(eventos);
        _cliente.iniciar();
        id = _cliente.getClientId();
        director = new DirectorLobby(new BuilderEventoLobby(), id);
        ejecutorEventos.submit(this);
    }

    public void iniciaConexion() {
        Client c = Client.iniciarComunicacion();

        for (Enum<?> suscripcion : eventos) {
            c.addObserver(suscripcion, this);
        }

        this.vincularCliente(c);
    }

    @Override
    public int devolverIdCliente() {
        return id;
    }

    @Override
    public void mostrarAvatares(Evento evento) {

    }

    @Override
    public void cuentaLista(Evento evento) {
        EventoJugador eventoRecibido = (EventoJugador) evento;
        int idDestinatario = evento.getIdPublicador();

        CuentaDTO cuentaLista = eventoRecibido.getCuenta();
        EventoLobby eventoEnviar;

        System.out.println("Un plebeyo se alisto");
        manejador.abandonoCuenta(cuentaLista);
        eventoEnviar = director.crearEventoCuentaLista(cuentaLista, idDestinatario);

        cliente.enviarEvento(eventoEnviar);

    }

    @Override
    public void cuentaNoLista(Evento evento) {
        EventoJugador eventoRecibido = (EventoJugador) evento;
        int idDestinatario = evento.getIdPublicador();

        CuentaDTO cuentaNoLista = eventoRecibido.getCuenta();
        EventoLobby eventoEnviar;

        System.out.println("Un plebeyo se se alisto");
        manejador.abandonoCuenta(cuentaNoLista);
        eventoEnviar = director.crearEventoCuentaNoLista(cuentaNoLista, idDestinatario);

        cliente.enviarEvento(eventoEnviar);
    }

}
