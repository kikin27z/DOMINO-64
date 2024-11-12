package com.domino64.manejador;

import abstraccion.ICliente;
import implementacion.Client;
import entidades.Cuenta;
import entidades.Partida;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import domino64.eventos.base.error.TipoError;
import eventos.EventoJugador;
import eventos.EventoLobby;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import lobbyBuilder.BuilderEventoLobby;
import lobbyBuilder.DirectorLobby;
import tiposLogicos.TipoLogicaLobby;

/**
 *
 * @author luisa M
 */
public class ManejadorLobby extends ObservadorLobby{
    private List<Partida> partidas;
    private static DirectorLobby director;
    private Partida partida;
    private static int id;
    private ICliente cliente;
    private List<Cuenta> jugadoresListos;
    private List<Cuenta> jugadoresPartida;
    
    public ManejadorLobby() {
        partidas = new CopyOnWriteArrayList<>();
        consumers = new ConcurrentHashMap<>();
        jugadoresListos = new CopyOnWriteArrayList<>();
        jugadoresPartida = new CopyOnWriteArrayList<>();
        setConsumers();
    }
    
    private void init(ICliente client){
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
        if(this.partida != null){
            //se quita el jugador de la partida
            partida.removerJugador(jugador);
            //si ya era el ultimo jugador que quedaba
            if(partida.getJugadores().isEmpty()){
                //el lobby deja de manejar la partida
                //dejando espacio para que otro jugador cree otra partida
                partida = null;
            }else{
                /*si aun hay jugadores en la partida,
                crea un evento de que el jugador salio para notificar al
                resto de jugadores*/
                System.out.println("jugadores en partida: "+partida.getJugadores());
                EventoLobby ev = director.crearEventoJugadorSalio(jugador);
                cliente.enviarEvento(ev);
            }
        }
    }

    
    @Override
    public void crearPartida(Evento evento) {
        EventoJugador eventoJ = (EventoJugador)evento;
        if(this.partida == null){
            System.out.println("partida creada");
            this.partida = eventoJ.getPartida();
            jugadoresPartida.add(eventoJ.getJugador());
            System.out.println("jugadores: "+partida.getJugadores());
            System.out.println("codigo: "+partida.getCodigoPartida());
        }
    }

    @Override
    public void unirsePartida(Evento evento) {
        EventoJugador eventoJ= (EventoJugador)evento; 
        Cuenta jugador =eventoJ.getJugador();
        if(this.partida != null){
            Partida p = eventoJ.getPartida();
            if(partida.getCodigoPartida().equals(p.getCodigoPartida())){
                
                partida.agregarJugador(jugador);
                EventoLobby ev = director.crearEventoPartidaEncontrada(partida);

                cliente.enviarEvento(ev);
                System.out.println("se unio a partida");
                
                EventoLobby ev2 = director.crearEventoJugadorNuevo(jugador);
                cliente.enviarEvento(ev2);
                System.out.println("se notifico a otros jugadores");
            }else{
                notificarError(TipoError.ERROR_LOGICO, jugador.getId(), "No hay una partida con ese codigo");
            }
        }else{
            notificarError(TipoError.ERROR_LOGICO, jugador.getId(), "No hay una partida iniciada");
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
        Client c = Client.getClient(5000);
        ManejadorLobby manejador = new ManejadorLobby();
        
        for (Enum<?> suscripcion : eventos) {
            c.addObserver(suscripcion, manejador);
        }
        
        //cliente = c;
        manejador.init(c);
        
        c.iniciar();
        
        id = c.getClientId();
        
        director = new DirectorLobby(new BuilderEventoLobby(), id);
    }

    @Override
    public void cambiarUsername(Evento evento) {
        EventoJugador evJ = (EventoJugador)evento;
        Cuenta jActualizado = evJ.getJugador();
        System.out.println("jAct = "+jActualizado);
        Cuenta j = partida.buscarJugador(jActualizado);
        System.out.println("j: "+j);
        if(j != null){
            j.setUsername(jActualizado.getUsername());
            System.out.println("jAc2= "+j);
            partida.actualizarJugador(j);
            System.out.println("jugadores de partida: "+partida.getJugadores());
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
