/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaLobby;

import abstraccion.ICliente;
import com.domino64.base.Suscriptor;
import com.luisa.entidades.Cuenta;
import com.luisa.entidades.Partida;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.TipoError;
import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import eventos.EventoJugador;
import eventos.EventoLobby;
import eventos.EventoMVC;
import eventos.EventoMVCJugador;
import eventos.EventoMVCLobby;
import eventos.EventoSuscripcion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import logicaNotificacionEventos.PublicadorEventos;
import tiposEventos.TipoJugadorMVC;
import tiposEventos.TipoLobbyMVC;
import tiposLogicos.TipoLogicaLobby;
import utilities.BuilderEventoSuscripcion;
import utilities.DirectorSuscripcion;

/**
 *
 * @author luisa M
 */
public class ManejadorCuenta extends ObservadorLobbyLocal implements Suscriptor<EventoMVC>{
    private Cuenta jugador;
    private ICliente cliente;
    private Partida partida;
    private PublicadorEventos publicador;
    private DirectorJugador directorEventos;
    private DirectorSuscripcion directorSuscripciones;
    private Map<Enum<?>, Consumer<EventoMVC>> consumersMVC;
    private final List<Enum<?>> eventosMVC = new ArrayList<>(
            List.of(
                    TipoError.ERROR_LOGICO,
                    TipoJugadorMVC.ABANDONAR_PARTIDA,
                    TipoJugadorMVC.CAMBIAR_AVATAR,
                    TipoJugadorMVC.CAMBIAR_USERNAME,
                    TipoJugadorMVC.CREAR_PARTIDA,
                    TipoJugadorMVC.UNIRSE_PARTIDA,
                    TipoJugadorMVC.JUGADOR_LISTO
            ));
    
    public ManejadorCuenta(){
        consumers = new ConcurrentHashMap<>();
        consumersMVC = new HashMap<>();
    }
    
    public List<Enum<?>> getEventosMVC(){
        return eventosMVC;
    }
    
    public Cuenta getCuenta(){
        return jugador;
    }
    
    private void setConsumersMVC(){
        consumersMVC.putIfAbsent(TipoError.ERROR_LOGICO, this::manejarError);
        consumersMVC.putIfAbsent(TipoJugadorMVC.ABANDONAR_PARTIDA, this::abandonarPartida);
        consumersMVC.putIfAbsent(TipoJugadorMVC.CAMBIAR_AVATAR, this::actualizarJugadores);
        consumersMVC.putIfAbsent(TipoJugadorMVC.CAMBIAR_USERNAME, this::cambiarUsername);
        consumersMVC.putIfAbsent(TipoJugadorMVC.CREAR_PARTIDA, this::crearPartida);
        consumersMVC.putIfAbsent(TipoJugadorMVC.JUGADOR_LISTO, this::actualizarJugadores);
        consumersMVC.putIfAbsent(TipoJugadorMVC.UNIRSE_PARTIDA, this::unirsePartida);
    }
    
    public void init(ICliente cliente, PublicadorEventos publicador){
        this.cliente = cliente;
        this.publicador = publicador;
        cliente.establecerSuscripciones(eventos);
        setConsumers();
        setConsumersMVC();
    }

    public void setClientId(int id){
        this.jugador = new Cuenta(id);
        directorEventos = new DirectorJugador(new BuilderEventoJugador(), jugador);
        directorSuscripciones = new DirectorSuscripcion(new BuilderEventoSuscripcion(), id);
    }
    
    private boolean validarCambioUsername(CuentaDTO cuentaDTO){
        for (Cuenta j : partida.getJugadores()) {
            if(j.getUsername().equals(cuentaDTO.getUsername())){
                return false;
            }
        }
        return true;
    }
    
    private void abandonarPartida(EventoMVC eventoMVC){        
         EventoJugador evento = directorEventos.crearEventoAbandonarPartida();
         cliente.enviarEvento(evento);
         directorEventos.setPartida(null);
         partida = null;
    }
    
    private void unirsePartida(EventoMVC eventoMVC){
        EventoMVCJugador eventoMVCJ = (EventoMVCJugador)eventoMVC;
        PartidaDTO partidaDTO = eventoMVCJ.getPartida();
        CuentaDTO publicadorDTO = eventoMVCJ.getPublicador();

        jugador.setUsername(publicadorDTO.getUsername());
        
        EventoJugador evento = directorEventos.crearEventoUnirsePartida(new Partida(partidaDTO.getCodigoPartida()));
        cliente.enviarEvento(evento);

        suscribirEvento();
    }

    private void cambiarUsername(EventoMVC eventoMVC){
        EventoMVCJugador eventoJMVC = (EventoMVCJugador)eventoMVC;
        CuentaDTO jugadorActualizado = eventoJMVC.getPublicador();
        
        if(validarCambioUsername(jugadorActualizado)){
            jugador.setUsername(jugadorActualizado.getUsername());
            
            EventoJugador evento = directorEventos.crearEventoCambiarUsername(jugador);
            cliente.enviarEvento(evento);
            
            EventoMVCLobby eventoMVCL = new EventoMVCLobby(TipoLobbyMVC.ACTUALIZAR_USERNAME);
            eventoMVCL.agregarContexto(jugadorActualizado);
            publicador.publicarEvento(eventoMVCL.getTipo(), eventoMVCL);
        }
    }
    
    private void suscribirEvento(){
        EventoSuscripcion evento = directorSuscripciones.
                crearEventoSuscribirse(TipoLogicaLobby.PARTIDA_ENCONTRADA);
        
        cliente.agregarSuscripcion(evento,this);
        agregarEvento(evento.getInfo(), this::recibirPartida);
    }
    
    private void removerSuscripcion(){
        EventoSuscripcion evento = directorSuscripciones.
                crearEventoDesuscribirse(TipoLogicaLobby.PARTIDA_ENCONTRADA);
        
        cliente.enviarEvento(evento);
        removerEvento(evento.getInfo());
    }

    private void crearPartida(EventoMVC evento){
        EventoMVCJugador eventoMVC = (EventoMVCJugador)evento;
        
        PartidaDTO partidaDTO = eventoMVC.getPartida();
        CuentaDTO cuentaDTO = eventoMVC.getPublicador();
        
        jugador.setUsername(cuentaDTO.getUsername());
        partida = new Partida(new ArrayList<>(List.of(jugador)), partidaDTO.getFichasPorJugador());
        directorEventos.setPartida(partida);
        
        EventoJugador eventoJ = directorEventos.crearEventoCrearPartida(partida);
        
        cliente.enviarEvento(eventoJ);
    }
    
    @Override
    public void recibirPartida(Evento evento){
        EventoLobby ev = (EventoLobby)evento;
        partida = ev.obtenerPartida();
        directorEventos.setPartida(partida);
        removerSuscripcion();
        
        EventoMVCLobby eventoMVC = new EventoMVCLobby(TipoLobbyMVC.PARTIDA_OBTENIDA);
        PartidaDTO partidaOb = new PartidaDTO();
        partidaOb.setCodigoPartida(partida.getCodigoPartida());
        
        List<JugadorDTO> jugadoresDTO = new ArrayList<>();
        
        CuentaDTO cuentaDTO;
        JugadorDTO jugadorDTO;
        
        for (Cuenta jugadorP : partida.getJugadores()) {
            cuentaDTO = new CuentaDTO();
            cuentaDTO.setUsername(jugadorP.getUsername());
            jugadorDTO = new JugadorDTO(cuentaDTO);
            jugadoresDTO.add(jugadorDTO);
        }
        
        partidaOb.setJugadores(jugadoresDTO);
        eventoMVC.setPartida(partidaOb);
        publicador.publicarEvento(eventoMVC.getTipo(), eventoMVC);
        System.out.println("partida encotnrada: "+partida.getCodigoPartida());
        System.out.println("jugadores: "+partida.getJugadores());
    }
    
    @Override
    public void actualizarJugadoresListos(Evento evento){
        EventoLobby ev = (EventoLobby)evento;
        Cuenta jugadorListo = ev.getInfo();
        
        EventoMVCLobby eventoMVC = new EventoMVCLobby(TipoLobbyMVC.ACTUALIZAR_JUGADORES_LISTOS);
        
        CuentaDTO jugadorDTO = new CuentaDTO();
        jugadorDTO.setUsername(jugadorListo.getUsername());
        
        eventoMVC.setPublicador(jugadorDTO);
        publicador.publicarEvento(eventoMVC.getTipo(), eventoMVC);
    }
    
    @Override
    public void actualizarJugadores(Evento evento){
        EventoLobby ev = (EventoLobby)evento;
        Cuenta jugadorNuevo = ev.getInfo();
        partida.agregarJugador(jugadorNuevo);
        System.out.println("un jugador nuevo: "+jugadorNuevo.getId());
        
        CuentaDTO jugadorNuevoDTO = new CuentaDTO();
        jugadorNuevoDTO.setUsername(jugadorNuevo.getUsername());
        
        EventoMVCLobby eventoMVC = new EventoMVCLobby(TipoLobbyMVC.JUGADOR_NUEVO);
        eventoMVC.setPublicador(jugadorNuevoDTO);
        publicador.publicarEvento(eventoMVC.getTipo(), eventoMVC);
    }
    

    @Override
    public void actualizarAvatares(Evento evento) {
        EventoLobby ev = (EventoLobby)evento;
        Cuenta jugador = ev.getInfo();
        CuentaDTO jugadorDTO = new CuentaDTO();
        jugadorDTO.setId(jugador.getId());
        jugadorDTO.setAvatarUrl(jugador.getAvatarUrl());
        
        EventoMVCLobby evMVC = new EventoMVCLobby(TipoLobbyMVC.ACTUALIZAR_AVATARES);
        evMVC.agregarContexto(jugadorDTO);
    }

    @Override
    public void actualizarUsernames(Evento evento) {
        EventoLobby ev = (EventoLobby)evento;
        Cuenta jugadorEv = ev.getInfo();
        CuentaDTO jugadorDTO = new CuentaDTO();
        jugadorDTO.setId(jugadorEv.getId());
        jugadorDTO.setUsername(jugadorEv.getUsername());
        
        EventoMVCLobby evMVC = new EventoMVCLobby(TipoLobbyMVC.ACTUALIZAR_USERNAME);
        evMVC.agregarContexto(jugadorDTO);
    }

    @Override
    public void manejarError(Evento evento) {
        System.out.println("ps un evento de error xd");
    }

    @Override
    public void recibirEvento(EventoMVC evento) {
        Consumer<EventoMVC> cons = consumersMVC.get(evento.getTipo());
        if (cons != null) {
            cons.accept(evento);
        }
    }
}
