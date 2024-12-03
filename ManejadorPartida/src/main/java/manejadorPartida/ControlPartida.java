package manejadorPartida;

//import domino64.eventos.base.Evento;
//import domino64.eventos.base.error.EventoError;
import entidadesDTO.PartidaIniciadaDTO;
import entidadesDTO.TurnosDTO;
import eventos.EventoTurno;
import eventoBase.Evento;
import entidadesDTO.ReglasDTO;
import eventos.EventoLobby;
import eventos.EventoPartida;
import implementacion.Client;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import partidaBuilder.BuilderEventoPartida;
import partidaBuilder.DirectorPartida;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ControlPartida extends IControlPartida implements Runnable {
    private int id;
    private DirectorPartida director;
    private final AtomicBoolean running;
    private final ManejadorPartida manejador;
    private final ExecutorService ejecutorEventos;

    public ControlPartida() {
        this.manejador = new ManejadorPartida();
        setConsumers();
        ejecutorEventos = Executors.newSingleThreadExecutor();
        running = new AtomicBoolean(true);
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
                Logger.getLogger(ControlPartida.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
                break;
            }
        }
    }

    private void vincularCliente(Client _cliente) {
        this.cliente = _cliente;
        cliente.establecerSuscripciones(eventos);
        _cliente.iniciar();
        id = _cliente.getClientId();
        director = new DirectorPartida(new BuilderEventoPartida(), id);
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
    public void manejarError(Evento evento) {
        EventoError error = (EventoError)evento;
        System.out.println("Hubo un error: "+error.getMensaje());
    }

    @Override
    public void finJuegoSinMovimientos(Evento evento) {
        System.out.println("Los jugadres se quedaron sin movimientos");
        System.out.println("Fin del juego jejejeje");
       // EventoPartida fin = director.crearEventoInicioPartida();
    }

    @Override
    public void prepararPartida(Evento evento) {
        EventoLobby er = (EventoLobby) evento;
        System.out.println("Evento recibido " + er);
        ReglasDTO reglas = er.getReglas();
        manejador.crearPartida(reglas.getCuentas());
        EventoPartida eventoEnviar = director.crearEventoRepartirFichas(reglas);
        cliente.enviarEvento(eventoEnviar);
        
    }

    @Override
    public void iniciarPartida(Evento evento) {
        EventoTurno turnosDesignados = (EventoTurno)evento;
        
        TurnosDTO turnos = turnosDesignados.getTurnos();
        PartidaIniciadaDTO partida = manejador.iniciarPartida(turnos);
        
        EventoPartida inicioPartida = director.crearEventoInicioPartida(partida);
        cliente.enviarEvento(inicioPartida);
    }

}
