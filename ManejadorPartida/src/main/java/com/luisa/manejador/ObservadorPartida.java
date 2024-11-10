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
import java.util.function.Consumer;
import observer.Observer;
import tiposLogicos.TiposJugador;

/**
 *
 * @author luisa M
 */
public class ObservadorPartida implements Observer<Evento> {
    protected Map<Enum<?>, Consumer<Evento>> consumers;
    protected static final List<Enum<?>> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_LOGICO,
                    TipoError.ERROR_DE_SERVIDOR,
                    TiposJugador.ABANDONAR_PARTIDA,
                    TiposJugador.PETICION_RENDIRSE
            ));

    protected ObservadorPartida() {
        setConsumers();
    }

    @Override
    public void update(Evento observable) {
        Consumer<Evento> cons = consumers.get(observable.getTipo());
        if (cons != null) {
            cons.accept(observable);
        }
    }

    private void setConsumers() {
////        consumers.putIfAbsent(TiposJugador.ABANDONAR_PARTIDA, this::removerJugador);
////        consumers.putIfAbsent(TiposJugador.UNIRSE_PARTIDA, this::unirsePartida);
////        consumers.putIfAbsent(TiposJugador.CREAR_PARTIDA, this::crearPartida);
    }

    public void agregarEvento(Enum<?> evento, Consumer<Evento> consumer) {
        eventos.add(evento);
        consumers.putIfAbsent(evento, consumer);
    }

}
