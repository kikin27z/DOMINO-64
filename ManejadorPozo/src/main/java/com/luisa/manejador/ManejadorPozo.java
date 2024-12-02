package com.luisa.manejador;

import abstraccion.ICliente;
import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import entidades.Cuenta;
import entidades.Ficha;
import entidades.Jugador;
import entidades.Partida;
import entidades.Pozo;
import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import eventos.EventoJugador;
import eventos.EventoLobby;
import eventos.EventoPartida;
import eventos.EventoPozo;
import implementacion.Client;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import tiposLogicos.TipoLogicaPozo;

/**
 *
 * @author luisa M
 */
public class ManejadorPozo extends ObservadorPozo implements Runnable {

    private AdaptadorDTO adaptadorDTO;
    private AdaptadorEntidad adaptadorEntidad;
    private int id;
    private ExecutorService ejecutorEventos;
    private ICliente cliente;
    private Map<Integer, Pozo> pozos;
    private AtomicBoolean running;
    private final int[][] valoresFichas = {
        {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6},
        {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6},
        {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6},
        {3, 3}, {3, 4}, {3, 5}, {3, 6},
        {4, 4}, {4, 5}, {4, 6},
        {5, 5}, {5, 6},
        {6, 6}
    };

    public ManejadorPozo() {
        pozos = new ConcurrentHashMap<>();
        adaptadorDTO = new AdaptadorDTO();
        adaptadorEntidad = new AdaptadorEntidad();
        ejecutorEventos = Executors.newSingleThreadExecutor();
        running = new AtomicBoolean(true);
        setConsumers();
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
                Logger.getLogger(ManejadorPozo.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
                break;
            }
        }
    }

    public void vincularCliente(Client _cliente) {
        this.cliente = _cliente;
        cliente.establecerSuscripciones(eventos);
        _cliente.iniciar();
        id = _cliente.getClientId();
        ejecutorEventos.submit(this);
    }

    /**
     * metodo para crear una lista de fichas con los valores almacenados en el
     * array valoresFichas
     *
     * @return la lista con las fichas
     */
    private List<Ficha> crearFichas() {
        List<Ficha> fichas = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            int value1 = valoresFichas[i][0];
            int value2 = valoresFichas[i][1];
            fichas.add(new Ficha(value1, value2));
        }
        Collections.shuffle(fichas);//revuelve la lista para cambiar el orden
        return fichas;
    }

    /**
     * Saca una ficha del pozo
     *
     * @param pozo
     * @return la ficha obtenida
     */
    private Ficha jalarFicha(Pozo pozo) {
        Ficha ficha;
        ficha = pozo.jalarFicha();
        return ficha;
    }

    /**
     * verifica si hay fichas en el pozo
     *
     * @param pozo
     * @return true si tiene fichas, false si esta vacio
     */
    private boolean hayFichas(Pozo pozo) {
        return pozo.tieneFichas();
    }

    /**
     * Saca fichas del pozo en la cantidad especificada por el parametro. El
     * metodo se usa al inicio de la partida para darle las fichas a cada
     * jugador
     *
     * @param cantidadFichas numero de fichas a repartir por jugador
     * @param pozo
     * @return una lista con las fichas para un jugador
     */
    public List<Ficha> repartirFichas(int cantidadFichas, Pozo pozo) {
        List<Ficha> fichas = new ArrayList<>();
        for (int i = 0; i < cantidadFichas; i++) {
            fichas.add(jalarFicha(pozo));
        }
        return fichas;
    }

    @Override
    public void guardarFichas(Evento evento) {
        EventoPartida eventoPartida = (EventoPartida) evento;
        JugadorDTO jugadorDTO = eventoPartida.getJugador();
        Jugador jugador = adaptadorDTO.adaptarJugadorDTO(jugadorDTO);

        pozos.get(eventoPartida.getIdContexto()).devolverFichas(jugador.getFichas());
    }

    @Override
    public void sacarFicha(Evento evento) {
        EventoJugador eventoJugador = (EventoJugador) evento;
        int codigoPartida = eventoJugador.getIdContexto();
        EventoPozo eventoPozo;

        if (hayFichas(pozos.get(codigoPartida))) {
            Ficha ficha = jalarFicha(pozos.get(codigoPartida));

            eventoPozo = new EventoPozo(TipoLogicaPozo.FICHA_OBTENIDA);
            eventoPozo.agregarFicha(adaptadorEntidad.adaptarEntidadFicha(ficha));
            eventoPozo.setIdContexto(codigoPartida);
            eventoPozo.setIdPublicador(id);

        } else {
            eventoPozo = new EventoPozo(TipoLogicaPozo.POZO_VACIO);
            eventoPozo.setIdContexto(codigoPartida);
            eventoPozo.setIdPublicador(id);
        }

        cliente.enviarEvento(eventoPozo);
    }

    @Override
    public void prepararFichas(Evento evento) {
        EventoLobby eventoLobby = (EventoLobby) evento;
        LobbyDTO lobby = eventoLobby.obtenerLobby();

        List<Jugador> jugadoresConFichas = darFichasJugadores(lobby.getCuentas(), lobby.getCantidadFichas(), eventoLobby.getIdContexto());

        EventoPozo fichasRepartidas = new EventoPozo(TipoLogicaPozo.FICHAS_REPARTIDAS);
        fichasRepartidas.setJugadoresConFichas(adaptadorEntidad.adaptarJugadores(jugadoresConFichas));
        fichasRepartidas.setIdContexto(eventoLobby.getIdContexto());
        fichasRepartidas.setIdPublicador(id);

        cliente.enviarEvento(fichasRepartidas);
    }

    private List<Jugador> darFichasJugadores(List<CuentaDTO> cuentasDTO, int cantidadFichas, int codigoPartida) {
        List<Cuenta> cuentas = adaptadorDTO.adaptarCuentasDTO(cuentasDTO);

        List<Jugador> jugadores = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            jugadores.add(new Jugador(cuenta));
        }

        Pozo pozo = new Pozo();
        pozo.llenarPozo(crearFichas());

        for (Jugador jugador : jugadores) {
            jugador.setFichas(repartirFichas(cantidadFichas, pozo));
        }

        pozos.put(codigoPartida, pozo);

        return jugadores;
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
