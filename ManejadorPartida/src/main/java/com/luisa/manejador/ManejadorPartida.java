package com.luisa.manejador;

import abstraccion.ICliente;
import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import entidades.Jugador;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.PartidaIniciadaDTO;
import eventos.EventoJugador;
import eventos.EventoLobby;
import eventos.EventoPartida;
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
    private Map<Integer, List<Jugador>> jugadoresPartidas;
    private Map<Integer, Partida> partidas;
    private Map<Integer, List<Jugador>> peticionesRendirse;
    private ICliente cliente;
    private AtomicBoolean running;
    private AdaptadorEntidad adaptador;
    private AdaptadorDTO adaptadorDTO;
    private static ExecutorService ejecutorEventos;
    
    public ManejadorPartida(){
        this.partidas = new ConcurrentHashMap<>();
        this.jugadoresPartidas = new ConcurrentHashMap<>();
        this.peticionesRendirse = new ConcurrentHashMap<>();
        adaptador = new AdaptadorEntidad();
        adaptadorDTO = new AdaptadorDTO();
        ejecutorEventos = Executors.newSingleThreadExecutor();
        running = new AtomicBoolean(true);
        setConsumers();
    }
    
    /**
     * vincula el cliente con el manejador
     * @param _cliente el cliente a establecerle al manejador
     */
    public void vincularCliente(Client _cliente) {
        _cliente.iniciar();
        this.cliente = _cliente;
        id = _cliente.getClientId();
        ejecutorEventos.submit(this);
    }
    
    protected void setIdManejador(int idManejador) {
        id = idManejador;
    }

    @Override
    public void run(){
        while (running.get()) {
            try {
                
                System.out.println("antes del take ");
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
    
    private void terminarPartida(int codigoPartida){
        jugadoresPartidas.compute(codigoPartida, (p, j) -> {
            j.removeAll(j);
            return j;
        });
        EventoPartida terminoPartida = new EventoPartida(TipoLogicaPartida.TERMINO_PARTIDA);
        terminoPartida.setIdContexto(codigoPartida);
        terminoPartida.setIdPublicador(id);
        
        jugadoresPartidas.remove(codigoPartida);
        partidas.remove(codigoPartida);
    }
    
    @Override
    protected void removerJugador(Evento evento) {
        EventoJugador eventoJ = (EventoJugador)evento;
        System.out.println("ME LLEGO EL EVENTO DE ABANDONAR PARTIDA!!!");
        int codigoPartida = eventoJ.getIdContexto();
        
        if(partidas.containsKey(codigoPartida)){
            List<Jugador> jugadoresActuales = jugadoresPartidas.get(codigoPartida);
            if(jugadoresActuales.size() == 2){
                terminarPartida(codigoPartida);
            }else{
                Jugador exJugador = adaptadorDTO.adaptarJugadorDTO(eventoJ.getJugador());
                jugadoresPartidas.compute(codigoPartida, (c, j) -> {
                    j.remove(exJugador);
                    return j;
                });
                
                EventoPartida jugadorSalio = new EventoPartida(TipoLogicaPartida.JUGADOR_SALIO);
                jugadorSalio.setJugador(adaptador.adaptarEntidadJugador(exJugador));
                jugadorSalio.setIdContexto(codigoPartida);
                jugadorSalio.setIdPublicador(id);
                
            }
        }
    }
    
    private boolean suficientesPeticiones(int codigoPartida){
        int cantidadJugadores  = jugadoresPartidas.get(codigoPartida).size();
        int mitad = (int)cantidadJugadores/2;
        return peticionesRendirse.get(codigoPartida).size() > mitad;
    }

    @Override
    protected void recibirPeticion(Evento evento) {
        EventoJugador eventoJ = (EventoJugador)evento;
        System.out.println("ME LLEGO EL EVENTO DE PETICION RENDIRSE");
        Jugador jugadorPeticion = adaptadorDTO.adaptarJugadorDTO(eventoJ.getJugador());
        int codigoPartida = eventoJ.getIdContexto();
        
        if(eventoJ.getCuenta() != null){
            if (peticionesRendirse.containsKey(codigoPartida)) {
                peticionesRendirse.compute(codigoPartida, (c, jug) -> {
                    jug.remove(jugadorPeticion);
                    return jug;
                });
            } else {
                peticionesRendirse.put(codigoPartida,
                        new ArrayList<>(List.of(jugadorPeticion)));
            }
        }
        if(suficientesPeticiones(codigoPartida)){
            terminarPartida(codigoPartida);
            peticionesRendirse.remove(codigoPartida);
        }
    }

    @Override
    protected void manejarError(Evento evento) {
        EventoError error = (EventoError)evento;
        System.out.println("error: "+error);
    }

    @Override
    protected void recibirPartida(Evento evento) {
        EventoLobby evLobby = (EventoLobby)evento;
        System.out.println("ME LLEGO EL EVENTO DE PREPARAR PARTIDA");
        int codigoPartida = evLobby.getIdContexto();
        Partida partidaNueva = adaptadorDTO.adaptarPartidaDTO(
                evLobby.obtenerLobby().getPartida());
        
        partidas.put(codigoPartida, partidaNueva);
    }

    @Override
    protected void iniciarPartida(Evento evento) {
        PartidaIniciadaDTO partida = recibirJugadores(evento);
        
        EventoPartida inicioPartida = new EventoPartida(TipoLogicaPartida.INICIO_PARTIDA);
        inicioPartida.setIdContexto(evento.getIdContexto());
        inicioPartida.setIdPublicador(id);
        inicioPartida.setPartida(partida);
        
        cliente.enviarEvento(inicioPartida);
    }

    private PartidaIniciadaDTO recibirJugadores(Evento evento){
        EventoTurno eventoTurno = (EventoTurno)evento;
        List<Jugador> jugadores = adaptadorDTO.adaptarJugadoresDTO(eventoTurno.getJugadores());
        
        jugadoresPartidas.compute(eventoTurno.getIdContexto(), (p,j) -> j = jugadores);
        
//        for (Jugador jugador : jugadores) {
//            jugador.setFichas(new ArrayList<>());
//        }
//        
        PartidaIniciadaDTO partidaAEnviar = new PartidaIniciadaDTO();
        partidaAEnviar.setJugadores(adaptador.adaptarJugadores(jugadores));
        
        return partidaAEnviar;
    }
    
    @Override
    protected void iniciarBusquedaPrimeraMula(Evento evento){
        System.out.println("ME LLEGO EL EVENTO DE BUSCAR PRIMERA MULA");
        PartidaIniciadaDTO partida = recibirJugadores(evento);
        
        EventoPartida buscarMula = new EventoPartida(TipoLogicaPartida.BUSCAR_PRIMERA_MULA);
        buscarMula.setIdContexto(evento.getIdContexto());
        buscarMula.setIdPublicador(id);
        buscarMula.setPartida(partida);
        
        cliente.enviarEvento(buscarMula);
    }
}
