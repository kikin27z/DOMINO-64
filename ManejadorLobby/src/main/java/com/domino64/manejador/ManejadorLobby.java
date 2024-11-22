package com.domino64.manejador;

import abstraccion.ICliente;
import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import implementacion.Client;
import entidades.Cuenta;
import entidades.Partida;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import domino64.eventos.base.error.TipoError;
import entidades.Lobby;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import eventos.EventoJugador;
import eventos.EventoLobby;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import lobbyBuilder.BuilderEventoLobby;
import lobbyBuilder.DirectorLobby;
import tiposLogicos.TiposJugador;

/**
 *
 * @author luisa M
 */
public class ManejadorLobby extends ObservadorLobby implements Runnable {

    private static DirectorLobby director;
    private static int id;
    private ICliente cliente;
    private AdaptadorEntidad adaptador;
    private AdaptadorDTO adaptadorDTO;
    private Map<Lobby, List<Cuenta>> jugadoresPartidas;
    //private Map<Partida, List<Cuenta>> jugadoresPartidas;
    private Map<Lobby, List<Cuenta>> jugadoresListos;
    private AtomicBoolean running;
    private static ExecutorService ejecutorEventos;

    public ManejadorLobby() {
        jugadoresPartidas = new ConcurrentHashMap<>();
        jugadoresListos = new ConcurrentHashMap<>();
        //jugadoresListos = new CopyOnWriteArrayList<>();
        adaptador = new AdaptadorEntidad();
        adaptadorDTO = new AdaptadorDTO();
        setConsumers();
        ejecutorEventos = Executors.newFixedThreadPool(4);
        running = new AtomicBoolean(true);
    }

    public ManejadorLobby(AtomicBoolean running) {
        this.running = running;
        adaptador = new AdaptadorEntidad();
        adaptadorDTO = new AdaptadorDTO();
        jugadoresPartidas = new ConcurrentHashMap<>();
        jugadoresListos = new ConcurrentHashMap<>();
        //jugadoresListos = new CopyOnWriteArrayList<>();
        setConsumers();
    }

    protected void setIdManejador(int idManejador) {
        id = idManejador;
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                System.out.println("before take");
                Evento nextEvent = colaEventos.take();
                System.out.println("event took: "+nextEvent);
                Consumer<Evento> cons = consumers.get(nextEvent.getTipo());
                if (cons != null) {
                    cons.accept(nextEvent);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                Logger.getLogger(ObservadorLobby.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
                break;
            }
        }
    }

    protected int getIdManejador() {
        return id;
    }

    protected void setDirector(DirectorLobby directorLobby) {
        director = directorLobby;
    }

    protected void setCliente(ICliente client) {
        this.cliente = client;
        cliente.establecerSuscripciones(eventos);
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
    public void removerJugador(Evento eventoNuevo) {
        EventoJugador eventoJ = (EventoJugador) eventoNuevo;
        CuentaDTO jugadorDTO = eventoJ.getJugador();
        Cuenta jugador = adaptadorDTO.adaptarEntidadCuenta(jugadorDTO);
        
        LobbyDTO partidaDTO = eventoJ.getLobby();
        Lobby partidaEvento = new Lobby(partidaDTO.getCodigoPartida());
        //partidaEvento.agregarCuenta(jugador);
        
        if (jugadoresPartidas.containsKey(partidaEvento)) {
            jugadoresPartidas.compute(partidaEvento, (p, j) -> {
                j.remove(jugador);
                return j;
            });
            
            jugadoresListos.compute(partidaEvento, (p, j) -> {
                if(!j.isEmpty())
                    j.remove(jugador);
                return j;
            });
            
            if (jugadoresPartidas.get(partidaEvento).isEmpty()) {
                jugadoresPartidas.remove(partidaEvento);
                jugadoresListos.remove(partidaEvento);
            } else {
                System.out.println("jugadores en partida: " + jugadoresPartidas.get(partidaEvento));
                EventoLobby ev = director.crearEventoJugadorSalio(partidaDTO, jugadorDTO);
                cliente.enviarEvento(ev);
            }
        }
    }

    @Override
    public void crearPartida(Evento evento) {
        EventoJugador eventoJ = (EventoJugador)evento;
        Lobby nuevaPartida = new Lobby();
        CuentaDTO cuentaDTO = eventoJ.getJugador();
        Cuenta creadorPartida = adaptadorDTO.adaptarEntidadCuenta(cuentaDTO);
        creadorPartida = nuevaPartida.asignarAvatar(creadorPartida);
        nuevaPartida.agregarCuenta(creadorPartida);
        
        /**
         * agregando la partida a los mapeos.
         * Se agrega al mapeo de partidas-jugadores
         * y al mapeo de partidas-jugadores listos.
         * La lista de jugadores listos inicialmente debe estar vacia
         */
        List<Cuenta> players = new CopyOnWriteArrayList<>();
        players.add(creadorPartida);
        jugadoresPartidas.put(nuevaPartida, players);
        jugadoresListos.put(nuevaPartida, new CopyOnWriteArrayList<>());
        
        LobbyDTO lobbyCreado = adaptador.adaptarEntidadLobby(nuevaPartida);
        CuentaDTO creadorDTO = adaptador.adaptarEntidadCuenta(creadorPartida);
        lobbyCreado.setCuentaActual(creadorDTO);
        
        EventoLobby ev = director.crearEventoPartidaEncontrada(
                lobbyCreado, 
                creadorDTO);
        
        System.out.println("evento lobby: "+ev);
        cliente.enviarEvento(ev);
        
        System.out.println("partida creada. Codigo: " + nuevaPartida.getCodigoPartida());
    }

    private void unirse(EventoJugador evento, Cuenta jugador) {
        LobbyDTO lobbyDTO = evento.getLobby();
        System.out.println("partida buscada: " + lobbyDTO);
        Lobby lobbyBuscado = new Lobby(lobbyDTO.getCodigoPartida());
        
        if (jugadoresPartidas.containsKey(lobbyBuscado)) {
            System.out.println("se encontro la partida");
            System.out.println("jugadores actuales: "+jugadoresPartidas.get(lobbyBuscado));
            lobbyBuscado.agregarCuentas(jugadoresPartidas.get(lobbyBuscado));
            
            Cuenta jugadorNuevo = lobbyBuscado.asignarAvatar(jugador);
            System.out.println("jugador a agregar: "+jugadorNuevo);
            agregarJugadorMapeo(lobbyBuscado, jugadorNuevo);
            
            lobbyDTO = inicializarLobbyDTO(lobbyDTO, lobbyBuscado);
            System.out.println("lobbydTO : "+lobbyDTO );
            notificarJugadores(lobbyDTO, adaptador.adaptarEntidadCuenta(jugadorNuevo));

        } else {
            notificarError(TipoError.ERROR_LOGICO, jugador.getId(), "No hay una partida con ese codigo");
        }
    }

    private LobbyDTO inicializarLobbyDTO(LobbyDTO lobbyDTO, Lobby lobby){
        List<Cuenta> jugadoresActuales = jugadoresPartidas.get(lobby);
        lobbyDTO.setCuentas(adaptador.adaptarCuentas(jugadoresActuales));

        List<Cuenta> jL = jugadoresListos.get(lobby);
        System.out.println("");
        System.out.println("");
        System.out.println("jugadores listos en el mapa; "+jL);
        System.out.println("");
        System.out.println("");
        if (!jL.isEmpty())
            lobbyDTO.agregarJugadoresListos(adaptador.adaptarCuentas(jL));
        return lobbyDTO;
    }
    
    private void agregarJugadorMapeo(Lobby lobby, Cuenta jugador){
        jugadoresPartidas.compute(lobby, (p, j) -> {
            j.add(jugador);
            return j;
        });

    }
    
    private void notificarJugadores(LobbyDTO partida, CuentaDTO jugador) {
        EventoLobby ev2 = director.crearEventoJugadorNuevo(partida, jugador);
        cliente.enviarEvento(ev2);
        System.out.println("se notifico a otros jugadores");
        
        partida.setCuentaActual(jugador);
        EventoLobby ev = director.crearEventoPartidaEncontrada(partida, jugador);
        cliente.enviarEvento(ev);
        System.out.println("se unio a partida");
    }

    @Override
    public void unirsePartida(Evento evento) {
        EventoJugador eventoJ = (EventoJugador) evento;
        CuentaDTO jugadorDTO = eventoJ.getJugador();
        
        Cuenta jugador = adaptadorDTO.adaptarEntidadCuenta(jugadorDTO);
        
        if (jugadoresPartidas.isEmpty()) {
            notificarError(TipoError.ERROR_LOGICO, jugadorDTO.getId(), "No hay una partida iniciada");
        } else {
            unirse(eventoJ, jugador);
        }
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
    public void cambiarUsername(Evento evento) {
//        EventoJugador evJ = (EventoJugador) evento;
//        CuentaDTO jActualizadoDTO = evJ.getJugador();
//        Cuenta jugadorActualizado = adaptadorDTO.adaptarEntidadCuenta(jActualizadoDTO);
//        Cuenta j = partida.buscarJugador(jActualizado);
//        if (j != null) {
////            j.setNombre(jActualizado.getNombre());
//            partida.actualizarJugador(j);
//        } else {
//            System.out.println("null en lobby");
//        }
//
//        EventoLobby evLobby = director.crearEventoActualizarUsername(j);
//        cliente.enviarEvento(evLobby);
    }

    @Override
    public void cambiarAvatar(Evento evento) {
        EventoJugador evJ = (EventoJugador) evento;
        CuentaDTO jActualizado = evJ.getJugador();
        LobbyDTO lobbyDTO = evJ.getLobby();
        Lobby lobby = new Lobby(lobbyDTO.getCodigoPartida());
        
        if(jugadoresPartidas.containsKey(lobby)){
            jugadoresPartidas.compute(lobby, (l,j)->{
                for (Cuenta cuenta : j) {
                    if(cuenta.getId() == jActualizado.getId()){
                        cuenta.setAvatar(adaptadorDTO.adaptarAvatar(jActualizado.getAvatar()));
                        break;
                    }
                }
                return j;
            });
            
            EventoLobby evLobby = director.crearEventoActualizarAvatares(lobbyDTO,jActualizado);
            cliente.enviarEvento(evLobby);

        }
    }

    @Override
    public void actualizarJugadorListo(Evento evento) {
        EventoJugador evJ = (EventoJugador) evento;
        LobbyDTO lobbyDTO = evJ.getLobby();
        Lobby lobby = adaptadorDTO.adaptarEntidadLobby(lobbyDTO);
        
        CuentaDTO jugadorDTO = evJ.getJugador();
        Cuenta jugadorListo = adaptadorDTO.adaptarEntidadCuenta(jugadorDTO);
        
        boolean listo = evJ.getTipo().equals(TiposJugador.JUGADOR_LISTO);
        
        if (jugadoresListos.containsKey(lobby)) {
            jugadoresListos.compute(lobby, (l,j)->{
                if(listo){
                    j.add(jugadorListo);
                }else{
                    j.remove(jugadorListo);
                }
                return j;
            });
            
        } else {
            jugadoresListos.put(lobby, new CopyOnWriteArrayList<>(List.of(jugadorListo)));
        }

        EventoLobby evL = director.crearEventoActualizarJugadoresListos(lobbyDTO,jugadorDTO, listo);
        cliente.enviarEvento(evL);
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
            System.out.println("shutdownNow? " + ejecutorEventos.isShutdown());
            Thread.currentThread().interrupt();
        }
    }

    public void vincularCliente(Client _cliente){
        this.cliente = _cliente;
        cliente.establecerSuscripciones(eventos);
        _cliente.iniciar();
        id = _cliente.getClientId();
        director = new DirectorLobby(new BuilderEventoLobby(), id);
        ejecutorEventos.submit(this);
    }
}