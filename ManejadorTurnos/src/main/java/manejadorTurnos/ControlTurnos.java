package manejadorTurnos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadaDTO;
import eventoBase.Evento;
import entidadesDTO.MazosDTO;
import entidadesDTO.TurnosDTO;
import eventos.EventoPozo;
import eventos.EventoTablero;
import eventos.EventoTurno;
import implementacion.Client;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import turnosBuilder.BuilderEventoTurnos;
import turnosBuilder.DirectorTurnos;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ControlTurnos extends IControlTurnos implements Runnable{
    private int id;
    private DirectorTurnos director;
    private final AtomicBoolean running;
    private final ManejadorTurnos manejador;
    private final ExecutorService ejecutorEventos;

    public ControlTurnos() {
        this.manejador = new ManejadorTurnos();
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
                Logger.getLogger(ControlTurnos.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
                break;
            }
        }
    }
    
    private void vincularCliente(Client _cliente) {
        this.cliente = _cliente;
        cliente.establecerSuscripciones(eventos);
        _cliente.iniciar();
        id = _cliente.getClientId();
        director = new DirectorTurnos(new BuilderEventoTurnos(), id);
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
    public void manejarError(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void asignarOrden(Evento evento) {
        EventoPozo eventoRecibido = (EventoPozo) evento;
        MazosDTO mazos = eventoRecibido.getMazos();
        
        TurnosDTO turnos = manejador.determinarOrden(mazos);
        EventoTurno eventoEnviar = director.crearEventoTurnoDesignados(turnos);
        cliente.enviarEvento(eventoEnviar);
        
    }

    @Override
    public void cambiarTurno(Evento evento) {
        EventoTablero er = (EventoTablero) evento;
        JugadaDTO jugada = er.getJugada();
        CuentaDTO cuenta =  manejador.rotarSiguienteTurno();
        
        EventoTurno  eventoEnviar;
        if(cuenta != null){
            eventoEnviar = director.crearEventoTurnoActual(cuenta);
        }else{
            eventoEnviar = director.crearEventoFinJuego();
        }
        cliente.enviarEvento(eventoEnviar);
    }
}
