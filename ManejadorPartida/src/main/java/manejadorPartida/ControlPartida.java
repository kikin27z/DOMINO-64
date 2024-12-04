package manejadorPartida;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.MazosDTO;
import eventoBase.Evento;
import entidadesDTO.ReglasDTO;
import entidadesDTO.TurnosDTO;
import eventos.EventoJugadorFicha;
import eventos.EventoLobby;
import eventos.EventoPartida;
import eventos.EventoPozo;
import eventos.EventoTablero;
import eventos.EventoTurno;
import implementacion.Client;
import java.util.Map;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void finJuegoSinMovimientos(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public void entregarFichaJugadores(Evento evento) {
        EventoPozo er = (EventoPozo) evento;
        MazosDTO mazos = er.getMazos();
        manejador.repartirFichas(mazos);
    }

    @Override
    public void quitarFicha(Evento evento) {
        EventoJugadorFicha er = (EventoJugadorFicha) evento;
        JugadaRealizadaDTO jugada = er.getJugada();
        FichaDTO ficha = jugada.getFicha();
        
        if(ficha == null){
            CuentaDTO cuenta = er.getCuenta();
            manejador.quitarFicha(cuenta, ficha);
        }
    }

    @Override
    public void evaluarJugador(Evento evento) {
        EventoTurno er = (EventoTurno) evento;
        JugadaDTO jugada = er.getJugada();
        CuentaDTO cuenta = er.getCuenta();
        EventoPartida eventoEnviar;
        if(manejador.tieneJugada(cuenta)){
            eventoEnviar = director.crearEventoTuTurno(jugada, cuenta);
        }else{
            eventoEnviar = director.crearEventoSolicitarSiguienteTurno();
        }
        cliente.enviarEvento(eventoEnviar);
        
    }

    @Override
    public void iniciarPartida(Evento evento) {
        EventoTurno er = (EventoTurno) evento;
        TurnosDTO turnos = er.getTurnos();
        
        EventoPartida eventoEnviar = director.crearEventoPartida(turnos);
        cliente.enviarEvento(eventoEnviar);
    }

    @Override
    public void asignarJugadaNueva(Evento evento) {
        EventoTablero er =(EventoTablero)evento;
        JugadaDTO jugada = er.getJugada();
        manejador.agregarJugadaActual(jugada);
    }

}
