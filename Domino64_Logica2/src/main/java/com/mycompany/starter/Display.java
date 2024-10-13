/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.starter;

import acciones.CambiarPantalla;
import acciones.CrearPartida;
import acciones.IniciarPartida;
import patrones.command.Accion;
import patrones.observer.Observer;
import entidades.Ficha;
import entidades.Jugador;
import entidades.ModoPartida;
import entidades.Partida;
import entidades.Pozo;
import entidades.Tablero;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import entidadesDTO.PozoDTO;
import entidadesDTO.TableroDTO;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion_utilities.FachadaNavegador;
import presentacion_utilities.NotificadorPresentacion;
import logica.GameHandler;
import logica.TileHandler;
import logica.TurnHandler;
/**
 *
 * @author luisa M
 */
public class Display<T> implements Observer<NotificadorPresentacion>, Runnable{
    private final FachadaNavegador fachada;
    private final NotificadorPresentacion notificador;
    private PartidaDTO partidaDTO;
    private JugadorDTO jugadorDTO;
    
    protected Display(NotificadorPresentacion notificador){
        this.notificador = notificador;
        fachada = FachadaNavegador.getInstance();
        this.partidaDTO = new PartidaDTO();
        this.jugadorDTO = new JugadorDTO();
    }
    
    protected void iniciarJuego(){
        if(fachada != null){
            List<List<Accion>> acciones = new ArrayList<>();
            acciones.add(crearAccionesInicio());
            acciones.add(crearAccionesLobby());
            fachada.iniciarApp(acciones);
            notificador.addObserver(this);
        }
    }
    
    private List<Accion> crearAccionesInicio(){
        List<Accion> accionesInicio = new ArrayList<>();
        accionesInicio.add(getAccionCrearPartidaCPU());
        accionesInicio.add(getAccionCambiarPantalla(CambiarPantalla.IR_LOBBY));
        return accionesInicio;
    }
    
    private List<Accion> crearAccionesLobby(){
        List<Accion> accionesLobby = new ArrayList<>();
        accionesLobby.add(getAccionCambiarPantalla(CambiarPantalla.IR_PARTIDA));
        accionesLobby.add(getAccionIniciarPartida());
        return accionesLobby;
    }
    
    private Accion getAccionCambiarPantalla(int destino){
        CambiarPantalla cambiarPantalla = new CambiarPantalla(this, destino);
        System.out.println("se creo el cambiar pantalla");
        System.out.println("dirigido a "+ cambiarPantalla);
        return cambiarPantalla;
    }
    
    private Accion getAccionCrearPartidaCPU(){
        GameHandler gameHandler = new GameHandler();
        CrearPartida accion = new CrearPartida(ModoPartida.VS_CPU,gameHandler);
        
        notificador.addObserver(accion.getGameHandler());
        
        return accion;
    }
    
    private Accion getAccionIniciarPartida(){
        TileHandler tileHandler = new TileHandler();
        TurnHandler turnHandler = new TurnHandler();
        
        IniciarPartida iniciarPartida = new IniciarPartida();
        iniciarPartida.setTileHandler(tileHandler);
        iniciarPartida.setTurnHandler(turnHandler);
        
        notificador.addObserver(iniciarPartida.getTileHandler());
        notificador.addObserver(iniciarPartida.getTurnHandler());
        
        return iniciarPartida;
    }
    
    public FachadaNavegador getFachada(){
        return this.fachada;
    }

    public void irInicio(){
        this.fachada.irInicio();
    }
    
    public void irLobby(){
        this.fachada.irLobby();
    }
    
    public void irPartida(){
        this.fachada.irPartida();
    }
    
    private void actualizarJugador(Jugador jugadorActualizado){
        jugadorDTO = new JugadorDTO(jugadorActualizado.getUsername());
        jugadorDTO.setFichas(crearFichasDTO(jugadorActualizado.getFichas()));
    }
    
    private void actualizarPartida(Partida partidaActualizada){
        partidaDTO.setCantidadJugadores(partidaActualizada.getMaxCantidadJugadores());
        partidaDTO.setCodigoPartida(partidaActualizada.getCodigoPartida());
        partidaDTO.setFichasPorJugador(partidaActualizada.getFichasPorJugador());
        partidaDTO.setJugadores(crearJugadoresDTO(partidaActualizada.getJugadores()));
        partidaDTO.setTablero(crearTableroDTO(partidaActualizada.getTablero()));
        partidaDTO.setPozo(crearPozoDTO(partidaActualizada.getPozo()));
    }
    
    private PozoDTO crearPozoDTO(Pozo pozo){
        PozoDTO pozoDTO = new PozoDTO();
        if(pozo.hayFichas()){
            List<FichaDTO> fichas = crearFichasDTO(pozo.getFichas());
            Stack<FichaDTO> stack = new Stack<>();
            stack.addAll(fichas);
            pozoDTO.setPozo(stack);
        }
        return pozoDTO;
    }
    
    private TableroDTO crearTableroDTO(Tablero tablero){
        TableroDTO tableroDTO = new TableroDTO();
        if(!tablero.tableroVacio()){
            List<FichaDTO> fichas = crearFichasDTO(tablero.obtenerFichas());
            tableroDTO.setTren(fichas);
        }
        return tableroDTO;
    }
    
    private FichaDTO crearFichaDTO(Ficha ficha){
        FichaDTO fichaDTO = new FichaDTO();
        fichaDTO.setDerecha(ficha.getDerecha());
        fichaDTO.setIzquierda(ficha.getIzquierda());
        fichaDTO.setOrientacion(ficha.getOrientacion().getValor());
        return fichaDTO;
    }
    
    private List<FichaDTO> crearFichasDTO(List<Ficha> fichas){
        List<FichaDTO> fichasDTO = new ArrayList<>();
        for (Ficha ficha : fichas) {
            fichasDTO.add(crearFichaDTO(ficha));
        }
        return fichasDTO;
    }
    
    private List<JugadorDTO> crearJugadoresDTO(List<Jugador> jugadores){
        List<JugadorDTO> jugadoresDTO = new ArrayList<>();
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            jugadoresDTO.add(new JugadorDTO(jugador.getUsername()));
        }
        return jugadoresDTO;
    }
    
 
    @Override
    public void update(NotificadorPresentacion observable, Object ... context) {
        
    }

    public void enviarJugadorActualizado(){
        actualizarJugador(GameHandler.getJugador());
        System.out.println(jugadorDTO);
        this.fachada.actualizarJugador(jugadorDTO);
    }
    
    public void enviarPartidaActualizada(){
        actualizarPartida(GameHandler.getPartida());
        System.out.println(partidaDTO);
        this.fachada.actualizarPartida(partidaDTO);
    }
    
    @Override
    public void run() {
    }
}
