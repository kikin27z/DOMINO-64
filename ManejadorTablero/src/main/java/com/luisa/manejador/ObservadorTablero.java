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
import tiposLogicos.TiposJugador;

/**
 *
 * @author luisa M
 */
public abstract class ObservadorTablero implements Observer<Evento> {
    protected static BlockingQueue<Evento> colaEventos;
    protected Map<Enum, Consumer<Evento>> consumers;
    protected static final List<Enum> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_DE_SERVIDOR,
                    TiposJugador.COLOCAR_FICHA
            ));
    
    protected ObservadorTablero() {
        consumers = new ConcurrentHashMap<>();
        colaEventos = new LinkedBlockingDeque();
    }

    @Override
    public void update(Evento observable) {
        colaEventos.offer(observable);
    }

    protected void setConsumers() {
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
        consumers.putIfAbsent(TiposJugador.COLOCAR_FICHA, this::colocarFicha);
    }

    protected void agregarEvento(Enum evento, Consumer<Evento> consumer) {
        eventos.add(evento);
        consumers.putIfAbsent(evento, consumer);
    }
    
    protected abstract void manejarError(Evento evento);
    protected abstract void colocarFicha(Evento evento);
}
