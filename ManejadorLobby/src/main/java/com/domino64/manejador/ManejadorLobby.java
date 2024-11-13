package com.domino64.manejador;

import abstraccion.ICliente;
import implementacion.Client;
import entidades.Cuenta;
import entidades.Partida;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import domino64.eventos.base.error.TipoError;
import entidadesDTO.LobbyDTO;
import eventos.EventoJugador;
import eventos.EventoLobby;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import lobbyBuilder.BuilderEventoLobby;
import lobbyBuilder.DirectorLobby;
import tiposLogicos.TipoLogicaLobby;

/**
 *
 * @author luisa M
 */
public class ManejadorLobby extends ObservadorLobby implements Runnable{
    private static DirectorLobby director;
    private Partida partida;
    private static int id;
    private ICliente cliente;
    private Map<Cuenta, Boolean> jugadoresL;
    private Map<Partida, List<Cuenta>> jugadoresPartidas;
    private List<Cuenta> jugadoresListos;
    private List<Cuenta> jugadoresPartida;
    private AtomicBoolean running;
    private static ExecutorService ejecutorEventos;
    
    
    public ManejadorLobby(){
        jugadoresPartidas = new ConcurrentHashMap<>();
        jugadoresListos = new CopyOnWriteArrayList<>();
        jugadoresPartida = new CopyOnWriteArrayList<>();
        setConsumers();
        ejecutorEventos = Executors.newFixedThreadPool(4);
        running = new AtomicBoolean(true);
    }
    
    public ManejadorLobby(AtomicBoolean running) {
        this.running = running;
        jugadoresPartidas = new ConcurrentHashMap<>();
        jugadoresListos = new CopyOnWriteArrayList<>();
        jugadoresPartida = new CopyOnWriteArrayList<>();
        setConsumers();
    }
    
    protected void setIdManejador(int idManejador){
        id = idManejador;
    }
    
    @Override
    public void run(){
        while(running.get()){
            try {
                Evento nextEvent = colaEventos.take();
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
    
    protected int getIdManejador(){
        return id;
    }
    
    protected void setDirector(DirectorLobby directorLobby){
        director =directorLobby;
    }
    
    protected void setCliente(ICliente client){
        this.cliente = client;
        cliente.establecerSuscripciones(eventos);
    }
    
    /**
     * metodo para quitar un jugador de la partida.
     * Si aun hay jugadores en la partida, se envia un evento
     * para notificar que el jugador salio. Si el jugador era el ultimo que quedaba,
     * se elimina la partida para que ya no la manipule el manejador
     * @param jugador Jugador a remover
     */
    @Override
    public void removerJugador(Evento eventoNuevo){
        EventoJugador eventoJ = (EventoJugador)eventoNuevo;
        Cuenta jugador  = eventoJ.getJugador();
        /*si el manejador ya esta manejando una partida
        o sea si un jugador ya creo una partida
        */
        Partida partidaEvento = eventoJ.getPartida();
        
        if(jugadoresPartidas.containsKey(partidaEvento)){
            jugadoresPartidas.compute(partidaEvento, (p,j)->{
                j.remove(jugador);
                return j;
            });
            
            if(jugadoresPartidas.get(partidaEvento).isEmpty()){
                jugadoresPartidas.remove(partidaEvento);
            }else{
                System.out.println("jugadores en partida: "+jugadoresPartidas.get(partidaEvento));
                EventoLobby ev = director.crearEventoJugadorSalio(partidaEvento, jugador);
                cliente.enviarEvento(ev);
            }
        }
//        if(this.partida != null){
//            //se quita el jugador de la partida
//            //partida.removerJugador(jugador);
//            jugadoresPartida.remove(jugador);
//            //si ya era el ultimo jugador que quedaba
//            if(jugadoresPartida.isEmpty()){
//                //el lobby deja de manejar la partida
//                //dejando espacio para que otro jugador cree otra partida
//                partida = null;
//            }else{
//                /*si aun hay jugadores en la partida,
//                crea un evento de que el jugador salio para notificar al
//                resto de jugadores*/
//                System.out.println("jugadores en partida: "+partida.getJugadores());
//                EventoLobby ev = director.crearEventoJugadorSalio(partida, jugador);
//                cliente.enviarEvento(ev);
//            }
//        }
    }

    
    @Override
    public void crearPartida(Evento evento) {
        EventoJugador eventoJ = (EventoJugador)evento;
        Partida nuevaPartida = eventoJ.getPartida();
        Cuenta creadorPartida = eventoJ.getJugador();
        nuevaPartida.agregarJugador(creadorPartida);
        
        List<Cuenta> players = new CopyOnWriteArrayList<>();
        players.add(creadorPartida);
        jugadoresPartidas.put(nuevaPartida, players);
        System.out.println("partida creada. Codigo: "+nuevaPartida.getCodigoPartida());
        System.out.println("jugador: "+jugadoresPartidas.get(nuevaPartida));
    }

    private void unirse(EventoJugador evento, Cuenta jugador){
        Partida partidaBuscada = evento.getPartida();
        System.out.println("partida buscada: "+ partidaBuscada);

        if (jugadoresPartidas.containsKey(partidaBuscada)) {
            System.out.println("se encontro la partida");
            jugadoresPartidas.compute(partidaBuscada, (p, j) -> {
                j.add(jugador);
                return j;
            });
            List<Cuenta> jugadoresActuales = jugadoresPartidas.get(partidaBuscada);
            partidaBuscada.setJugadores(jugadoresActuales);
            
            notificarJugadores(partidaBuscada, jugador);
            
        } else {
            notificarError(TipoError.ERROR_LOGICO, jugador.getId(), "No hay una partida con ese codigo");
        }
    }
    
    private void notificarJugadores(Partida partida, Cuenta jugador){
        EventoLobby ev = director.crearEventoPartidaEncontrada(partida);
        cliente.enviarEvento(ev);
        System.out.println("se unio a partida");
        
        EventoLobby ev2 = director.crearEventoJugadorNuevo(jugador);
        cliente.enviarEvento(ev2);
        System.out.println("se notifico a otros jugadores");
    }
    
    @Override
    public void unirsePartida(Evento evento) {
        EventoJugador eventoJ= (EventoJugador)evento; 
        Cuenta jugador =eventoJ.getJugador();
        if(jugadoresPartidas.isEmpty())
            notificarError(TipoError.ERROR_LOGICO, jugador.getId(), "No hay una partida iniciada");
        else{
            unirse(eventoJ, jugador);
        }
    }

    /**
     * metodo para notificar errores
     * @param tipo El tipo de error a notificar
     * @param idJugador Id del suscriptor al que se va a notificar
     */
    private void notificarError(TipoError tipo, int idJugador, String msjError){
        System.out.println("no se pudo unir a la partida");
        EventoError error = new EventoError(tipo,msjError);
        error.setIdPublicador(idJugador);
        
       cliente.enviarEvento(error);
    }

    public static void main(String[] args){
//        
        Client c = Client.getClient("10.202.68.69",5000);
        ManejadorLobby manejador = new ManejadorLobby();
        
        for (Enum<?> suscripcion : eventos) {
            c.addObserver(suscripcion, manejador);
        }
        
        //cliente = c;
        manejador.setCliente(c);
        
        c.iniciar(false);
        
        id = c.getClientId();
        
        director = new DirectorLobby(new BuilderEventoLobby(), id);
        
        ejecutorEventos.submit(manejador);
    }

    @Override
    public void cambiarUsername(Evento evento) {
        EventoJugador evJ = (EventoJugador)evento;
        Cuenta jActualizado = evJ.getJugador();
        Cuenta j = partida.buscarJugador(jActualizado);
        if(j != null){
            j.setUsername(jActualizado.getUsername());
            partida.actualizarJugador(j);
        }else
            System.out.println("null en lobby");
        
        EventoLobby evLobby = director.crearEventoActualizarUsername(j);
        cliente.enviarEvento(evLobby);
    }

    @Override
    public void cambiarAvatar(Evento evento) {
        EventoJugador evJ = (EventoJugador)evento;
        Cuenta jActualizado = evJ.getJugador();
        
        Cuenta j = partida.buscarJugador(jActualizado);
        if(j != null){
            j.setAvatarUrl(jActualizado.getAvatarUrl());
        }
        System.out.println("avatar nuevo: "+j.getAvatarUrl());
        System.out.println("jugadores en partida con jugador act: "+partida.getJugadores());
        
        EventoLobby evLobby = director.crearEventoActualizarAvatares(j);
        cliente.enviarEvento(evLobby);
    }

    @Override
    public void jugadorListo(Evento evento) {
        EventoJugador evJ = (EventoJugador)evento;
        Cuenta jugadorL = evJ.getJugador();
        if(jugadoresListos.contains(jugadorL))
            jugadoresListos.remove(jugadorL);
        else 
            jugadoresListos.add(jugadorL);
        
        EventoLobby evL = director.crearEventoActualizarJugadoresListos(jugadorL);
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
            System.out.println("shutdownNow? "+ejecutorEventos.isShutdown());
            Thread.currentThread().interrupt();
        }
    }

//    @Override
//    public void irLobby(Evento evento) {
//        EventoJugador eventoJ = (EventoJugador)evento;
//        LobbyDTO lobbyNuevo = eventoJ.getLobby();
//        Cuenta creadorPartida = eventoJ.getJugador();
//        
//        List<Cuenta> players = new CopyOnWriteArrayList<>();
//        players.add(creadorPartida);
//        System.out.println("partida creada. Codigo: "+lobbyNuevo.getCodigoPartida());
//        System.out.println("jugador: "+ lobbyNuevo.getCuentaActual().getAvatar().getUrl());
//    }
    
}
