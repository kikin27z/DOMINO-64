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
import tiposLogicos.TipoLogicaPartida;
import tiposLogicos.TipoLogicaPozo;
import tiposLogicos.TiposJugador;

/**
 *
 * @author luisa M
 */
public abstract class ObservadorTurno implements Observer<Evento> {
    protected static BlockingQueue<Evento> colaEventos;
    protected Map<Enum, Consumer<Evento>> consumers;
    protected static final List<Enum> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_DE_SERVIDOR,
                    TipoLogicaPozo.FICHAS_REPARTIDAS,
                    TiposJugador.PASAR_TURNO,
                    TiposJugador.COLOCAR_FICHA,
                    TipoLogicaPartida.JUGADOR_SALIO
            ));

    protected ObservadorTurno() {
        consumers = new ConcurrentHashMap<>();
        colaEventos = new LinkedBlockingDeque();
    }

    @Override
    public void update(Evento observable) {
        colaEventos.offer(observable);
    }

    protected void setConsumers() {
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
        consumers.putIfAbsent(TipoLogicaPozo.FICHAS_REPARTIDAS, this::designarTurnos);
        consumers.putIfAbsent(TiposJugador.PASAR_TURNO, this::cambiarTurno);
        consumers.putIfAbsent(TiposJugador.COLOCAR_FICHA, this::cambiarTurno);
        consumers.putIfAbsent(TipoLogicaPartida.JUGADOR_SALIO, this::reacomodarTurnos);
    }

    public void agregarEvento(Enum evento, Consumer<Evento> consumer) {
        eventos.add(evento);
        consumers.putIfAbsent(evento, consumer);
    }
    
    public void removerEvento(Enum evento) {
        eventos.remove(evento);
        consumers.remove(evento);
    }
    
    public abstract void manejarError(Evento evento);
    public abstract void designarTurnos(Evento evento);
    public abstract void cambiarTurno(Evento evento);
    public abstract void reacomodarTurnos(Evento evento);
    
}
