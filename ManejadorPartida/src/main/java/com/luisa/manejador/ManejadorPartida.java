package com.luisa.manejador;

import abstraccion.ICliente;
import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import domino64.eventos.base.Evento;
import entidades.Jugador;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.PartidaDTO;
import eventos.EventoJugador;
import eventos.EventoLobby;
import eventos.EventoPartida;
import eventos.EventoPozo;
import eventos.EventoTurno;
import implementacion.Client;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import tiposLogicos.TipoLogicaPartida;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisa M
 */
public class ManejadorPartida extends ObservadorPartida implements Runnable{
    private int id;
    private Map<Partida, List<Jugador>> partidas;
    private Map<Partida, Integer> idsContextos;
    private Map<Partida, Integer> peticionesRendirse;
    private ICliente cliente;
    private AtomicBoolean running;
    private AdaptadorEntidad adaptador;
    private AdaptadorDTO adaptadorDTO;
    private static ExecutorService ejecutorEventos;
    
    public ManejadorPartida(){
        this.partidas = new ConcurrentHashMap<>();
        this.idsContextos = new ConcurrentHashMap<>();
        this.peticionesRendirse = new ConcurrentHashMap<>();
        adaptador = new AdaptadorEntidad();
        adaptadorDTO = new AdaptadorDTO();
        ejecutorEventos = Executors.newSingleThreadExecutor();
        running = new AtomicBoolean(true);
    }
    
    public void vincularCliente(Client client) {
        this.cliente = client;
        cliente.establecerSuscripciones(eventos);
        client.iniciar();
        id = client.getClientId();
        ejecutorEventos.submit(this);
    }
    
    protected void setIdManejador(int idManejador) {
        id = idManejador;
    }

    @Override
    public void run(){
        while (running.get()) {
            try {
                Evento nextEvent = colaEventos.take();
                Consumer<Evento> cons = consumers.get(nextEvent.getTipo());
                if (cons != null) {
                    cons.accept(nextEvent);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                Logger.getLogger(ManejadorPartida.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
                break;
            }
        }
    }
    
    private void terminarPartida(Partida partida){
        partidas.compute(partida, (p, j) -> {
            j.removeAll(j);
            return j;
        });
        EventoPartida terminoPartida = new EventoPartida(TipoLogicaPartida.TERMINO_PARTIDA);
        terminoPartida.setIdContexto(idsContextos.get(partida));
        terminoPartida.setIdPublicador(id);
        terminoPartida.agregarPartida(adaptador.adaptarEntidadPartida(partida));
        
        partidas.remove(partida);
        idsContextos.remove(partida);
    }
    
    @Override
    protected void removerJugador(Evento evento) {
//        EventoJugador eventoJ = (EventoJugador)evento;
//        Partida partida = adaptadorDTO.adaptarPartidaDTO(eventoJ.getPartida());
//        
//        if(partidas.containsKey(partida)){
//            List<Jugador> jugadoresActuales = partidas.get(partida);
//            if(jugadoresActuales.size() == 2){
//                terminarPartida(partida);
//            }else{
//                Jugador exJugador = adaptadorDTO.adaptarJugadorDTO(eventoJ.getJugador());
//                partidas.compute(partida, (p, j) -> {
//                    j.remove(exJugador);
//                    return j;
//                });
//                
//                EventoPartida jugadorSalio = new EventoPartida(TipoLogicaPartida.JUGADOR_SALIO);
//                jugadorSalio.agregarPartida(adaptador.adaptarEntidadPartida(partida));
//                jugadorSalio.setJugador(adaptador.adaptarEntidadJugador(exJugador));
//                jugadorSalio.setIdContexto(idsContextos.get(partida));
//                jugadorSalio.setIdPublicador(id);
//                
//                
//            }
//        }
    }
    
    private boolean suficientesPeticiones(Partida partida){
        int cantidadJugadores  = partidas.get(partida).size();
        int mitad = (int)cantidadJugadores/2;
        return peticionesRendirse.get(partida) > mitad;
    }

    @Override
    protected void recibirPeticion(Evento evento) {
//        EventoJugador eventoJ = (EventoJugador)evento;
//        Partida partida = adaptadorDTO.adaptarPartidaDTO(eventoJ.getPartida());
//        
//        if(eventoJ.getCuenta() != null){
//            if (peticionesRendirse.containsKey(partida)) {
//                peticionesRendirse.compute(partida, (p, pet) -> pet++);
//            } else {
//                peticionesRendirse.put(partida, 1);
//            }
//        }
//        if(suficientesPeticiones(partida)){
//            terminarPartida(partida);
//            peticionesRendirse.remove(partida);
//        }
    }

    @Override
    protected void manejarError(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void recibirPartida(Evento evento) {
//        EventoLobby evLobby = (EventoLobby)evento;
//        Partida partida = adaptadorDTO.adaptarPartidaDTO(
//                evLobby.obtenerLobby().getPartida());
//        
//        partidas.put(partida, partida.getJugadores());
//        idsContextos.put(partida, evLobby.getIdContexto());
    }

    @Override
    protected void asignarTurnos(Evento evento) {
        EventoTurno eventoTurno = (EventoTurno)evento;
        
        Partida partida = adaptadorDTO.adaptarPartidaDTO(eventoTurno.getPartida());
        List<Jugador> jugadoresOrdenados = partida.getJugadores();
        
        partidas.compute(partida, (p,j) -> j = jugadoresOrdenados);
    }
    
    @Override
    protected void iniciarBusquedaPrimeraMula(Evento evento){
        
    }
}
