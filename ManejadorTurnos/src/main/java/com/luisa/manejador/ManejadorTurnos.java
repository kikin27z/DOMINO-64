package com.luisa.manejador;

import abstraccion.ICliente;
import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Jugador;
import domino64.eventos.base.Evento;
import entidades.Ficha;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import eventos.EventoJugador;
import eventos.EventoPozo;
import eventos.EventoSuscripcion;
import eventos.EventoTurno;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import tiposLogicos.TipoLogicaPozo;
import tiposLogicos.TipoLogicaTurno;
import tiposLogicos.TipoSuscripcion;

/**
 *
 * @author luisa M
 */
public class ManejadorTurnos extends ObservadorTurno{
    private int id;
    private Map<Partida, List<Jugador>> jugadoresPartidas;
    private Map<Partida, Jugador> jugadoresEnTurno;
    private Map<Integer, Partida> idsContextos;
    private ICliente cliente;
    private AtomicBoolean running;
    private AdaptadorEntidad adaptador;
    private AdaptadorDTO adaptadorDTO;
    private static ExecutorService ejecutorEventos;
    
    public ManejadorTurnos(){
        this.jugadoresPartidas = new ConcurrentHashMap<>();
        this.jugadoresEnTurno = new ConcurrentHashMap<>();
        this.idsContextos = new ConcurrentHashMap<>();
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
    
    private Jugador cambiarTurno(Partida partida){        
        jugadoresEnTurno.compute(partida, (p,j) -> {
            List<Jugador> jugadores = jugadoresPartidas.get(p);
            int index = jugadores.indexOf(j);

            if (index == jugadores.size() - 1) {
                index = -1;
            }
            j = jugadores.get(index+1);
            return j;
        });
        
        return jugadoresEnTurno.get(partida);
    }
    
    private void verificarFichaObtenida(Evento evento){
        EventoPozo eventoPozo = (EventoPozo)evento;
        Ficha ficha = (Ficha)eventoPozo.getInfo();
        
        Partida partida = idsContextos.get(eventoPozo.getIdContexto());
        
        if(ficha.esMula()){
            EventoSuscripcion desuscripcion = new EventoSuscripcion(TipoSuscripcion.DESUSCRIBIR);
            desuscripcion.setIdPublicador(id);
            desuscripcion.agregarInfo(TipoLogicaPozo.FICHA_OBTENIDA);
            cliente.agregarSuscripcion(desuscripcion, this);
            removerEvento(desuscripcion.getInfo());
            
            
            
        }else{
            
        }
    }
    
    private void buscarPrimeraMula(int idContexto, Jugador primerJugador){
        jugadoresEnTurno.put(idsContextos.get(idContexto), primerJugador);
        CuentaDTO cuentaDTO = adaptador.adaptarEntidadCuenta(primerJugador.getCuenta());
        
        EventoTurno buscarMula = new EventoTurno(TipoLogicaTurno.JUGADORES_SIN_MULAS);
        buscarMula.agregarCuenta(cuentaDTO);
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
        PartidaDTO partidaDTO = (PartidaDTO)eventoPozo.getInfo();
        Partida partida = adaptadorDTO.adaptarPartidaDTO(partidaDTO);
        
        idsContextos.put(eventoPozo.getIdContexto(),partida);
        
        List<Jugador> jugadores = partida.getJugadores();
        
        Jugador primerJugador = buscarPrimerTurno(jugadores);
        EventoTurno turnosDesignados = new EventoTurno();
        
        if(primerJugador != null){
            jugadores = designarOtrosTurnos(jugadores, primerJugador);
            turnosDesignados.setTipo(TipoLogicaTurno.TURNOS_DESIGNADOS);
            partida.setJugadores(jugadores);
        }else{
            turnosDesignados.setTipo(TipoLogicaTurno.JUGADORES_SIN_MULAS);
        }
        jugadoresPartidas.put(partida, jugadores);
        
        partidaDTO = adaptador.adaptarEntidadPartida(partida);
        
        for (JugadorDTO jugadorDTO : partidaDTO.getJugadores()) {
            turnosDesignados.agregarCuenta(jugadorDTO.getCuenta());
        }
        turnosDesignados.setPartida(partidaDTO);
        turnosDesignados.setIdContexto(eventoPozo.getIdContexto());
        turnosDesignados.setIdPublicador(id);
        
        cliente.enviarEvento(turnosDesignados);
    }

    @Override
    public void cambiarTurno(Evento evento) {
        EventoJugador eventoJ = (EventoJugador)evento;
        Partida partida = adaptadorDTO.adaptarPartidaDTO(eventoJ.getPartida());
        Jugador jugEnTurno = cambiarTurno(partida);
        CuentaDTO jugadorEnTurnoDTO = adaptador.adaptarEntidadCuenta(jugEnTurno.getCuenta());
        
        EventoTurno cambioTurno = new EventoTurno(TipoLogicaTurno.CAMBIO_TURNO);
        cambioTurno.setIdContexto(eventoJ.getIdContexto());
        cambioTurno.agregarCuenta(jugadorEnTurnoDTO);
        cambioTurno.setIdPublicador(id);
        
        cliente.enviarEvento(cambioTurno);
    }

    @Override
    public void reacomodarTurnos(Evento evento) {
        
    }
    
}
