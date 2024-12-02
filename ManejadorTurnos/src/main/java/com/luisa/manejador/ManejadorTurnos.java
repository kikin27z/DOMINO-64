/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.luisa.manejador;

import abstraccion.ICliente;
import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Jugador;
import domino64.eventos.base.Evento;
import domino64.eventos.base.suscripcion.TipoSuscripcion;
import domino64.eventos.base.suscripcion.EventoSuscripcion;
import entidades.Ficha;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import eventos.EventoJugador;
import eventos.EventoPozo;
import eventos.EventoTurno;
import implementacion.Client;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import tiposLogicos.TipoLogicaPozo;
import tiposLogicos.TipoLogicaTurno;

/**
 *
 * @author luisa M
 */
public class ManejadorTurnos extends ObservadorTurno implements Runnable{
    private int id;
    private Map<Integer, Jugador> jugadoresEnTurno;
    private Map<Integer, List<Jugador>> jugPartidas;
    private ICliente cliente;
    private AtomicBoolean running;
    private AdaptadorEntidad adaptador;
    private AdaptadorDTO adaptadorDTO;
    private static ExecutorService ejecutorEventos;
    
    public ManejadorTurnos(){
        this.jugadoresEnTurno = new ConcurrentHashMap<>();
        this.jugPartidas = new ConcurrentHashMap<>();
        ejecutorEventos = Executors.newSingleThreadExecutor();
        setConsumers();
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
                Logger.getLogger(ManejadorTurnos.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
                break;
            }
        }
    }
    
    protected void vincularCliente(Client _cliente) {
        this.cliente = _cliente;
        cliente.establecerSuscripciones(eventos);
        _cliente.iniciar();
        id = _cliente.getClientId();
        ejecutorEventos.submit(this);
    }
    
    private Jugador buscarPrimerTurno(List<Jugador> jugadores){
        List<Ficha> mulasJugadores = new ArrayList<>();
        List<Jugador> jugadoresConMula = new ArrayList<>();
        
        for(Jugador jugador: jugadores){
            Ficha mayorMula = jugador.obtenerMayorMula();
            if(mayorMula!=null){
                jugadoresConMula.add(jugador);
                mulasJugadores.add(mayorMula);
            }
        }
        
        if(!mulasJugadores.isEmpty()){
            int index = mulasJugadores.indexOf(Collections.max(mulasJugadores));
            return jugadoresConMula.get(index);
        } return null;
    }

    private List<Jugador> designarOtrosTurnos(List<Jugador> jugadores, Jugador primerTurno){
        List<Jugador> copiaJugadores = jugadores;
        copiaJugadores.remove(primerTurno);
        
        Collections.shuffle(copiaJugadores);
        
        copiaJugadores.addFirst(primerTurno);
        
        return copiaJugadores;
    }
    
    /**
     * metodo para cambiar el turno en una partida especifica.
     * se usa el codigo de la partida para obtener los jugadores 
     * que estan dentro de la partida con el codigo del parametro
     * @param codigoPartida Codigo de la partida en la cual se hara el
     * cambio de turno
     * @return el jugador con el turno actual
     */
    private Jugador cambiarTurno(int codigoPartida){        
        jugadoresEnTurno.compute(codigoPartida, (codigo,j) -> {
            List<Jugador> jugadores = jugPartidas.get(codigo);
            int index = jugadores.indexOf(j);

            if (index == jugadores.size() - 1) {
                index = -1;
            }
            j = jugadores.get(index+1);
            return j;
        });
        
        return jugadoresEnTurno.get(codigoPartida);
    }
    
    private void verificarFichaObtenida(Evento evento){
        
    }
    
    private void buscarPrimeraMula(int idContexto, Jugador primerJugador){
        jugadoresEnTurno.put(idContexto, primerJugador);
        JugadorDTO jugadorDTO = adaptador.adaptarEntidadJugador(primerJugador);
        
        EventoTurno buscarMula = new EventoTurno(TipoLogicaTurno.JUGADORES_SIN_MULAS);
        buscarMula.agregarJugador(jugadorDTO);
        buscarMula.setIdContexto(idContexto);
        buscarMula.setIdPublicador(id);
        
        cliente.enviarEvento(buscarMula);
    }
    
    @Override
    public void manejarError(Evento evento) {
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void designarTurnos(Evento evento) {
        EventoPozo eventoPozo = (EventoPozo)evento;
        List<JugadorDTO> jugadoresDTO = eventoPozo.getJugadoresConFichas();
        List<Jugador> jugadores = adaptadorDTO.adaptarJugadoresDTO(jugadoresDTO);
        
        jugPartidas.put(eventoPozo.getIdContexto(),jugadores);
        
        Jugador primerJugador = buscarPrimerTurno(jugadores);
        EventoTurno turnosDesignados = new EventoTurno();
        
        if(primerJugador != null){
            jugadores = designarOtrosTurnos(jugadores, primerJugador);
            turnosDesignados.setTipo(TipoLogicaTurno.TURNOS_DESIGNADOS);
        }else{
            turnosDesignados.setTipo(TipoLogicaTurno.JUGADORES_SIN_MULAS);
        }
        
        turnosDesignados.agregarJugadores(adaptador.adaptarJugadores(jugadores));
        turnosDesignados.setIdContexto(eventoPozo.getIdContexto());
        turnosDesignados.setIdPublicador(id);
        
        cliente.enviarEvento(turnosDesignados);
    }

    @Override
    public void cambiarTurno(Evento evento) {
        EventoJugador eventoJ = (EventoJugador)evento;
        int codigoPartida = eventoJ.getIdContexto();
        Jugador jugEnTurno = cambiarTurno(codigoPartida);
        
        EventoTurno cambioTurno = new EventoTurno(TipoLogicaTurno.CAMBIO_TURNO);
        cambioTurno.setIdContexto(eventoJ.getIdContexto());
        cambioTurno.agregarJugador(adaptador.adaptarEntidadJugador(jugEnTurno));
        cambioTurno.setIdPublicador(id);
        
        cliente.enviarEvento(cambioTurno);
    }

    @Override
    public void reacomodarTurnos(Evento evento) {
        
    }
    
}
