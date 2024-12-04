package manejadorTablero;

import abstraccion.ICliente;
import entidadesDTO.JugadaDTO;
import eventoBase.Evento;
import eventoBaseError.EventoError;
import entidadesDTO.JugadaRealizadaDTO;
import eventos.EventoJugadorFicha;
import eventos.EventoTablero;
import implementacion.Client;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import tableroBuilder.BuilderEventoTablero;
import tableroBuilder.DirectorTablero;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ControlTablero extends IControlTablero implements Runnable {
    private int id;
    private DirectorTablero director;
    private final AtomicBoolean running;
    private final ExecutorService ejecutorEventos;
    private final ManejadorTablero manejador;

    public ControlTablero() {
        this.manejador = new ManejadorTablero();
        setConsumers();
        ejecutorEventos = Executors.newSingleThreadExecutor();
        running = new AtomicBoolean(true);
    }

    private void vincularCliente(Client _cliente) {
        this.cliente = _cliente;
        cliente.establecerSuscripciones(eventos);
        _cliente.iniciar();
        id = _cliente.getClientId();
        director = new DirectorTablero(new BuilderEventoTablero(), id);
        ejecutorEventos.submit(this);
    }

    @Override
    public void iniciaConexion() {
        Client c = Client.iniciarComunicacion();

        for (Enum<?> suscripcion : eventos) {
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
                Logger.getLogger(ControlTablero.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
                break;
            }
        }
    }
    
    
     @Override
    public void colocarFicha(Evento evento) {
        EventoJugadorFicha _evento = (EventoJugadorFicha) evento;
        JugadaRealizadaDTO jugada = _evento.getJugada();
        manejador.colocarFicha(jugada);
        JugadaDTO proximaJugada = manejador.obtenerProximaJugada();
        
        EventoTablero eventoEnviar = director.crearEventoProximaJugada(proximaJugada);
        cliente.enviarEvento(evento);
        
    }

    @Override
    public void manejarError(Evento evento) {
        EventoError error = (EventoError) evento;
        System.out.println("Ocurrio un error: " + error.getMensaje());
        running.set(false);
        ejecutorEventos.shutdown();

        try {
            if (!ejecutorEventos.awaitTermination(5, TimeUnit.SECONDS)) {
                ejecutorEventos.shutdownNow();
            }
        } catch (InterruptedException e) {
            ejecutorEventos.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
