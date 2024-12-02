/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaPartida;

import domino64.eventos.base.Evento;
import domino64.eventos.base.error.TipoError;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import observer.Observer;
import tiposLogicos.TipoLogicaPartida;
import tiposLogicos.TipoLogicaTurno;

/**
 *
 * @author luisa M
 */
public abstract class ObservadorPartidaLocal implements Observer<Evento>{
    protected Map<Enum, Consumer<Evento>> consumers;
    protected final List<Enum> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_LOGICO,
                    TipoError.ERROR_DE_SERVIDOR,
                    TipoLogicaPartida.INICIO_PARTIDA,
                    TipoLogicaPartida.BUSCAR_PRIMERA_MULA,
                    TipoLogicaPartida.JUGADOR_GANO,
                    TipoLogicaPartida.PETICION_RENDIRSE,
                    TipoLogicaPartida.JUGADOR_SALIO,
                    TipoLogicaTurno.CAMBIO_TURNO
            ));

    public ObservadorPartidaLocal() {
        this.consumers = new ConcurrentHashMap<>();
    }

    @Override
    public void update(Evento evento) {
        Consumer<Evento> cons = consumers.get(evento.getTipo());
        if (cons != null) {
            cons.accept(evento);
        }
    }

    public List<Enum> getEventos() {
        return eventos;
    }

    public void agregarEvento(Enum evento, Consumer<Evento> consumer) {
        this.eventos.add(evento);
        consumers.putIfAbsent(evento, consumer);
    }

    public void removerEvento(Enum evento) {
        System.out.println("evento a borrar: " + evento);
        this.eventos.remove(evento);
        consumers.remove(evento);
    }
    
    protected void setConsumers(){
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
        consumers.putIfAbsent(TipoError.ERROR_LOGICO, this::manejarError);
        consumers.putIfAbsent(TipoLogicaPartida.BUSCAR_PRIMERA_MULA, this::buscarMula);
        consumers.putIfAbsent(TipoLogicaPartida.INICIO_PARTIDA, this::entrarPartida);
        consumers.putIfAbsent(TipoLogicaPartida.JUGADOR_GANO, this::mostrarGanador);
        consumers.putIfAbsent(TipoLogicaPartida.JUGADOR_SALIO, this::removerJugador);
        consumers.putIfAbsent(TipoLogicaPartida.PETICION_RENDIRSE, this::procesarPeticion);
        consumers.putIfAbsent(TipoLogicaPartida.TERMINO_PARTIDA, this::salirPartida);
        consumers.putIfAbsent(TipoLogicaTurno.CAMBIO_TURNO, this::evaluarCambioTurno);
    }
    
    public abstract void manejarError(Evento evento);
    public abstract void buscarMula(Evento evento);
    public abstract void entrarPartida(Evento evento);
    public abstract void mostrarGanador(Evento evento);
    public abstract void removerJugador(Evento evento);
    public abstract void procesarPeticion(Evento evento);
    public abstract void salirPartida(Evento evento);
    public abstract void evaluarCambioTurno(Evento evento);
    
}
