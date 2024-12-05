package manejadorPozo;

import eventoBase.Evento;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.MazosDTO;
import entidadesDTO.ReglasDTO;
import eventos.EventoJugador;
import eventos.EventoJugadorFicha;
import eventos.EventoLobby;
import eventos.EventoPartida;
import eventos.EventoPozo;
import eventos.EventoTurno;
import implementacion.Client;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import pozoBuilder.BuilderEventoPozo;
import pozoBuilder.DirectorPozo;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ControlPozo extends IControlPozo implements Runnable {

    private int id;
    private DirectorPozo director;
    private final AtomicBoolean running;
    private final ExecutorService ejecutorEventos;
    private final ManejadorPozo manejador;

    public ControlPozo() {
        this.manejador = new ManejadorPozo();
        setConsumers();
        ejecutorEventos = Executors.newSingleThreadExecutor();
        running = new AtomicBoolean(true);
    }

    private void vincularCliente(Client _cliente) {
        this.cliente = _cliente;
        cliente.establecerSuscripciones(eventos);
        _cliente.iniciar();
        id = _cliente.getClientId();
        director = new DirectorPozo(new BuilderEventoPozo(), id);
        ejecutorEventos.submit(this);
    }

    @Override
    public void iniciaConexion() {
        Client c = Client.iniciarComunicacion();

        for (Enum suscripcion : eventos) {
            c.addObserver(suscripcion, this);
        }

        this.vincularCliente(c);
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                Evento nextEvent = colaEventos.take();
                Consumer<Evento> cons = consumers.get(nextEvent.getTipo());
                if (cons != null) {
                    cons.accept(nextEvent);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                Logger.getLogger(ControlPozo.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
                break;
            }
        }
    }

    @Override
    public void manejarError(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void repartirFichas(Evento evento) {
        EventoPartida eventoRecibido = (EventoPartida) evento;
        System.out.println("Evento recibido " + eventoRecibido);
        ReglasDTO reglas = eventoRecibido.getReglas();
        List<JugadorDTO> jugadores = eventoRecibido.getJugadores();
        jugadores = manejador.repartirFichas2(jugadores, reglas);
        
//        MazosDTO mazos = manejador.repartirFichas(reglas, jugadores);
        MazosDTO mazos = new MazosDTO();
        mazos.setJugadores(jugadores);
        EventoPozo eventoEnviar = director.crearEventoFichasRepartidas(mazos);
        cliente.enviarEvento(eventoEnviar); 
    }

    @Override
    public void jugadorAbandono(Evento evento) {
        EventoPartida eventoRecibido = (EventoPartida) evento;
        JugadorDTO jugador = eventoRecibido.getJugador();
        List<FichaDTO> fichas = jugador.getFichas();
        manejador.guardarFichasPozo(fichas);
        
    }

    @Override
    public void jalarFicha(Evento evento) {
        EventoJugador jalarF = (EventoJugador)evento;
        FichaDTO ficha = manejador.jalarPozo();
        EventoPozo eventoEnviar;
        if(ficha != null){
            eventoEnviar = director.crearEventoFichaJalada(ficha, jalarF.getCuenta());
        }else{
            eventoEnviar = director.crearEventoPozoVacio();
        }
        
        cliente.enviarEvento(eventoEnviar);
    }

    @Override
    public void finJuego(Evento evento) {
        EventoTurno finJuego = (EventoTurno)evento;
        
    }

}
