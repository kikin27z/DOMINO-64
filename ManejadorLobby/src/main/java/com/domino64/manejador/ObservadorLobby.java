/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.domino64.manejador;

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
public abstract class ObservadorLobby implements Observer<Evento> {
    protected Map<Enum<?>, Consumer<Evento>> consumers;
    protected static final List<Enum<?>> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_DE_SERVIDOR,
                    TiposJugador.ABANDONAR_PARTIDA,
                    TiposJugador.CAMBIAR_AVATAR,
                    TiposJugador.CAMBIAR_USERNAME,
                    TiposJugador.CREAR_PARTIDA,
                    TiposJugador.UNIRSE_PARTIDA,
                    TiposJugador.JUGADOR_LISTO
            ));
    
   
    @Override
    public void update(Evento observable) {
        Consumer<Evento> cons = consumers.get(observable.getTipo());
        if(cons != null){
            cons.accept(observable);
        }
    }
    
    protected void setConsumers(){
        consumers.putIfAbsent(TiposJugador.ABANDONAR_PARTIDA, this::removerJugador);
        consumers.putIfAbsent(TiposJugador.UNIRSE_PARTIDA, this::unirsePartida);
        consumers.putIfAbsent(TiposJugador.CREAR_PARTIDA, this::crearPartida);
        consumers.putIfAbsent(TiposJugador.CAMBIAR_USERNAME, this::cambiarUsername);
        consumers.putIfAbsent(TiposJugador.CAMBIAR_AVATAR, this::cambiarAvatar);
        consumers.putIfAbsent(TiposJugador.JUGADOR_LISTO, this::jugadorListo);
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
    }
    
    public void agregarEvento(Enum<?> evento, Consumer<Evento> consumer){
        eventos.add(evento);
        consumers.putIfAbsent(evento, consumer);
    }
    
    public abstract void removerJugador(Evento evento);
    public abstract void unirsePartida(Evento evento);
    public abstract void crearPartida(Evento evento);
    public abstract void cambiarUsername(Evento evento);
    public abstract void cambiarAvatar(Evento evento);
    public abstract void jugadorListo(Evento evento);
    public abstract void manejarError(Evento evento);
}
