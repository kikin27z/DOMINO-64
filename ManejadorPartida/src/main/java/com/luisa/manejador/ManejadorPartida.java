package com.luisa.manejador;

import abstraccion.ICliente;
import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import entidades.Jugador;
import entidades.Partida;
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
import tiposLogicos.TiposJugador;

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
        Thread t = new Thread(this::sendEvent);
        t.setName("hilo 2 manejador partida");
        t.start();
        ejecutorEventos.submit(this);
    }
    
    private void sendEvent(){
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("--------------------");
            System.out.println("Ingresa el tipo de evento a enviar:");
            System.out.println("1-Crear Partida");
            System.out.println("2-Unirse Partida");
            System.out.println("3-Peticion rendirse");
            System.out.println("O ingresa 0 si quieres desconectarte");
            int tipoEvento = s.nextInt();
            
            s.nextLine();
            System.out.println("ingresa la info a agregar al mensaje:");
            String msj = s.nextLine();
            EventoJugador evento = new EventoJugador();
            switch (tipoEvento) {
                case 0 -> {
                    evento.setTipo(TiposJugador.JUGADOR_NO_LISTO);
                    flag = false;
                }
                case 1 ->
                    evento.setTipo(TiposJugador.CREAR_PARTIDA);
                case 2 ->
                    evento.setTipo(TiposJugador.UNIRSE_PARTIDA);
                case 3 ->
                    evento.setTipo(TiposJugador.JUGADOR_LISTO);
            }
            
            evento.agregarInfo(msj);
            evento.setIdPublicador(id);

            System.out.println("Evento creado: " + evento.getTipo());
            System.out.println("Mensaje evento: " + evento.getInfo());
            cliente.enviarEvento(evento);
            System.out.println("enviado :)");
            System.out.println("--------------------");
        }
        
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
                System.out.println("evento tomado: "+nextEvent.getInfo());
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
        terminoPartida.agregarInfo(adaptador.adaptarEntidadPartida(partida));
        
        partidas.remove(partida);
        idsContextos.remove(partida);
    }
    
    @Override
    protected void removerJugador(Evento evento) {
        EventoJugador eventoJ = (EventoJugador)evento;
        System.out.println("ME LLEGO EL EVENTO DE ABANDONAR PARTIDA!!!");
        System.out.println("info: "+eventoJ.getInfo());
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
//                jugadorSalio.agregarInfo(adaptador.adaptarEntidadPartida(partida));
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
        EventoJugador eventoJ = (EventoJugador)evento;
        
        System.out.println("ME LLEGO EL EVENTO DE PETICION RENDIRSE");
        System.out.println("info: "+eventoJ.getInfo());
        
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
        EventoError error = (EventoError)evento;
        System.out.println("error: "+error);
    }

    @Override
    protected void recibirPartida(Evento evento) {
        EventoLobby evLobby = (EventoLobby)evento;
        System.out.println("ME LLEGO EL EVENTO DE PREPARAR PARTIDA");
        System.out.println("info: "+evLobby.getInfo());
//        Partida partida = adaptadorDTO.adaptarPartidaDTO(
//                evLobby.obtenerLobby().getPartida());
//        
//        partidas.put(partida, partida.getJugadores());
//        idsContextos.put(partida, evLobby.getIdContexto());
        
//        EventoPartida inicioPartida = new EventoPartida(TipoLogicaPartida.INICIO_PARTIDA);
//        inicioPartida.setIdContexto(evLobby.getIdContexto());
//        inicioPartida.agregarInfo(adaptador.adaptarEntidadPartida(partida));
    }

    @Override
    protected void recibirJugadores(Evento evento){
        EventoTurno eventoTurno = (EventoTurno)evento;
        Partida partida = adaptadorDTO.adaptarPartidaDTO(eventoTurno.getPartida());
        List<Jugador> jugadores = partida.getJugadores();
        
        partidas.compute(partida, (p,j) -> j = jugadores);
        
        for (Jugador jugador : jugadores) {
            jugador.setFichas(new ArrayList<>());
        }
        
        PartidaIniciadaDTO partidaAEnviar = new PartidaIniciadaDTO();
        partidaAEnviar.setJugadores(adaptador.adaptarJugadores(jugadores));
        
        EventoPartida inicioPartida = new EventoPartida(TipoLogicaPartida.INICIO_PARTIDA);
        inicioPartida.setIdContexto(eventoTurno.getIdContexto());
        inicioPartida.setIdPublicador(id);
        inicioPartida.agregarInfo(partidaAEnviar);
        
        cliente.enviarEvento(inicioPartida);
    }
    
    @Override
    protected void asignarTurnos(Evento evento) {
        recibirJugadores(evento);
        
    }
    
    @Override
    protected void iniciarBusquedaPrimeraMula(Evento evento){
        System.out.println("ME LLEGO EL EVENTO DE PREPARAR PARTIDA");
        System.out.println("info: "+evento.getInfo());
//        recibirJugadores(evento);
//        EventoPartida buscarMula = new EventoPartida(TipoLogicaPartida.BUSCAR_PRIMERA_MULA);
//        buscarMula.setIdContexto(evento.getIdContexto());
//        buscarMula.setIdPublicador(id);
//        cliente.enviarEvento(buscarMula);
    }
}
