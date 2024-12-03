package manejadorTablero;

import abstraccion.ICliente;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import entidadesDTO.JugadaRealizadaDTO;
import eventos.EventoJugadorFicha;
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
 * @author karim
 */
public class ControlTablero extends IControlTablero implements Runnable {

    private ICliente cliente;
    private int id;
    private AtomicBoolean running;
    private static ExecutorService ejecutorEventos;
    private DirectorTablero director;
    private ManejadorTablero manejador;

    public ControlTablero() {
        this.manejador = new ManejadorTablero();
        setConsumers();
        ejecutorEventos = Executors.newSingleThreadExecutor();
        running = new AtomicBoolean(true);
    }

    @Override
    public void colocarFicha(Evento evento) {
        EventoJugadorFicha _evento = (EventoJugadorFicha) evento;
        JugadaRealizadaDTO jugada = _evento.getJugada();
        manejador.colocarFicha(jugada);
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

    @Override
    public void vincularCliente(Client _cliente) {
        this.cliente = _cliente;
        cliente.establecerSuscripciones(eventos);
        _cliente.iniciar();
        id = _cliente.getClientId();
        director = new DirectorTablero(new BuilderEventoTablero(), id);
        ejecutorEventos.submit(this);
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

}
