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
import tiposLogicos.TipoLogicaPartida;
import tiposLogicos.TiposJugador;

/**
 *
 * @author luisa M
 */
public abstract class ObservadorPozo implements Observer<Evento> {
    protected static BlockingQueue<Evento> colaEventos;
    protected Map<Enum<?>, Consumer<Evento>> consumers;
    protected static final List<Enum<?>> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_DE_SERVIDOR,
                    TipoLogicaLobby.PREPARAR_PARTIDA,
                    TiposJugador.JALAR_FICHA,
                    TipoLogicaPartida.JUGADOR_SALIO
            ));

    protected ObservadorPozo() {
        consumers = new ConcurrentHashMap<>();
        colaEventos = new LinkedBlockingDeque();
    }

    @Override
    public void update(Evento observable) {
        colaEventos.offer(observable);
    }

    protected void setConsumers() {
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
        consumers.putIfAbsent(TipoLogicaLobby.PREPARAR_PARTIDA, this::prepararFichas);
        consumers.putIfAbsent(TiposJugador.JALAR_FICHA, this::sacarFicha);
        consumers.putIfAbsent(TipoLogicaPartida.JUGADOR_SALIO, this::guardarFichas);
    }

    public void agregarEvento(Enum<?> evento, Consumer<Evento> consumer) {
        eventos.add(evento);
        consumers.putIfAbsent(evento, consumer);
    }
    
    public abstract void guardarFichas(Evento evento);
    public abstract void sacarFicha(Evento evento);

    public abstract void prepararFichas(Evento evento);

    public abstract void manejarError(Evento evento);
}
