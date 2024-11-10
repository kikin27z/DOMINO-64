/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicaPartida;

import domino64.eventos.base.Evento;
import domino64.eventos.base.error.TipoError;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import observer.Observer;
import tiposLogicos.TipoLogicaPartida;
import tiposLogicos.TipoLogicaTurno;

/**
 * Clase que representa una implementacion de un observador. 
 * Este observador concreto define los metodos que van a manejar
 * los distintos eventos que le interesa recibir a este observador.
 * La lista de enum define los eventos que va a observar.
 * Tiene un mapeo de consumers por cada tipo de evento, esto quiere decir
 * que por cada tipo de evento que va a recibir, le asigna un metodo
 * que se va a ejecutar al recibir el evento especifico.
 * Este observador solo recibe eventos relacionados con lo que pasa en la partida
 * @author luisa M
 */
public abstract class ObservadorPartidaLocal implements Observer<Evento>{
    protected Map<Enum<?>, Consumer<Evento>> consumers;
    protected final List<Enum<?>> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_LOGICO,
                    TipoError.ERROR_DE_SERVIDOR,
                    TipoLogicaPartida.INICIO_PARTIDA,
                    TipoLogicaPartida.JUGADOR_SALIO,
                    TipoLogicaPartida.JUGADOR_GANO,
                    TipoLogicaPartida.PETICION_RENDIRSE,
                    TipoLogicaPartida.TERMINO_PARTIDA,
                    TipoLogicaTurno.CAMBIO_TURNO
            ));
    
    public List<Enum<?>> getEventos(){
        return eventos;
    }
    
    protected ObservadorPartidaLocal(){
        setConsumers();
    }
    
    @Override
    public void update(Evento evento){
        Consumer<Evento> cons = consumers.get(evento.getTipo());
        if(cons != null){
            cons.accept(evento);
        }
    }
    
    private void setConsumers(){
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
        consumers.putIfAbsent(TipoError.ERROR_LOGICO, this::manejarError);
        consumers.putIfAbsent(TipoLogicaPartida.INICIO_PARTIDA, this::iniciarPartida);
        consumers.putIfAbsent(TipoLogicaPartida.TERMINO_PARTIDA, this::terminarPartida);
        consumers.putIfAbsent(TipoLogicaPartida.JUGADOR_GANO, this::anunciarGanador);
        consumers.putIfAbsent(TipoLogicaPartida.JUGADOR_SALIO, this::removerJugador);
        consumers.putIfAbsent(TipoLogicaPartida.TERMINO_PARTIDA, this::terminarPartida);
        consumers.putIfAbsent(TipoLogicaTurno.CAMBIO_TURNO, this::cambiarTurno);
    }
    
    public abstract void manejarError(Evento evento);

    public abstract void iniciarPartida(Evento evento);
    public abstract void removerJugador(Evento evento);
    public abstract void notificarPeticion(Evento evento);
    public abstract void anunciarGanador(Evento evento);
    public abstract void terminarPartida(Evento evento);
    public abstract void cambiarTurno(Evento evento);
}
