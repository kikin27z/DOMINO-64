/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luisa.manejador;

import domino64.eventos.base.Evento;
import domino64.eventos.base.error.TipoError;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;
import observer.Observer;
import tiposLogicos.TipoLogicaLobby;
import tiposLogicos.TipoLogicaPozo;
import tiposLogicos.TipoLogicaTurno;
import tiposLogicos.TiposJugador;

/**
 *
 * @author luisa M
 */
public abstract class ObservadorPartida implements Observer<Evento> {
    protected static BlockingQueue<Evento> colaEventos;
    protected Map<Enum, Consumer<Evento>> consumers;
    protected static final List<Enum> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_DE_SERVIDOR,
                    TiposJugador.ABANDONAR_PARTIDA,
                    TiposJugador.PETICION_RENDIRSE,
                    TipoLogicaLobby.PREPARAR_PARTIDA,
                    TipoLogicaTurno.JUGADORES_SIN_MULAS,
                    TipoLogicaTurno.TURNOS_DESIGNADOS
            ));

    protected ObservadorPartida() {
        consumers = new ConcurrentHashMap<>();
        colaEventos = new LinkedBlockingDeque();
    }

    @Override
    public void update(Evento observable) {
        colaEventos.offer(observable); 
    }

    protected void setConsumers() {
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
        consumers.putIfAbsent(TiposJugador.ABANDONAR_PARTIDA, this::removerJugador);
        consumers.putIfAbsent(TiposJugador.PETICION_RENDIRSE, this::recibirPeticion);
        consumers.putIfAbsent(TipoLogicaLobby.PREPARAR_PARTIDA, this::recibirPartida);
        consumers.putIfAbsent(TipoLogicaTurno.TURNOS_DESIGNADOS, this::iniciarPartida);
        consumers.putIfAbsent(TipoLogicaTurno.JUGADORES_SIN_MULAS, this::iniciarBusquedaPrimeraMula);
    }

    public void agregarEvento(Enum evento, Consumer<Evento> consumer) {
        eventos.add(evento);
        consumers.putIfAbsent(evento, consumer);
    }

    protected abstract void removerJugador(Evento evento);
    protected abstract void recibirPeticion(Evento evento);
    protected abstract void recibirPartida(Evento evento);
    protected abstract void iniciarPartida(Evento evento);
    protected abstract void iniciarBusquedaPrimeraMula(Evento evento);
    protected abstract void manejarError(Evento evento);
    
}
