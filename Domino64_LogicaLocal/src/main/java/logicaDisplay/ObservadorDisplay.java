/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDisplay;

import domino64.eventos.base.Evento;
import domino64.eventos.base.error.TipoError;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import observer.Observer;
import tiposLogicos.TipoLogicaLobby;
import tiposLogicos.TipoLogicaPartida;

/**
 *
 * @author luisa M
 */
public abstract class ObservadorDisplay implements Observer<Evento> {
    protected Map<Enum<?>, Consumer<Evento>> consumers;
    protected final List<Enum<?>> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_LOGICO,
                    TipoError.ERROR_DE_SERVIDOR,
                    TipoLogicaLobby.ACTUALIZAR_JUGADORES_LISTO,
                    TipoLogicaLobby.JUGADOR_NUEVO,
                    TipoLogicaLobby.JUGADOR_SALIO,
                    TipoLogicaLobby.ACTUALIZAR_AVATARES,
                    TipoLogicaLobby.ACTUALIZAR_USERNAME,
                    TipoLogicaPartida.INICIO_PARTIDA,
                    TipoLogicaPartida.JUGADOR_GANO,
                    TipoLogicaPartida.JUGADOR_SALIO,
                    TipoLogicaPartida.PETICION_RENDIRSE,
                    TipoLogicaPartida.TERMINO_PARTIDA
            ));
    
    @Override
    public void update(Evento evento) {
        Consumer<Evento> cons = consumers.get(evento.getTipo());
        if(cons != null){
            cons.accept(evento);
        }
    }
    
    public List<Enum<?>> getEventos(){
        return eventos;
    }
    
    public void agregarEvento(Enum<?> evento, Consumer<Evento> consumer){
        this.eventos.add(evento);
        consumers.putIfAbsent(evento, consumer);
    }
    
    public void removerEvento(Enum<?> evento){
        this.eventos.remove(evento);
        consumers.remove(evento);
    }
    
    protected void setConsumers(){
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::mostrarError);
        consumers.putIfAbsent(TipoError.ERROR_LOGICO, this::mostrarError);
        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_NUEVO, this::actualizarJugadores);
        consumers.putIfAbsent(TipoLogicaLobby.ACTUALIZAR_JUGADORES_LISTO, this::actualizarJugadoresListos);
        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_SALIO, this::actualizarJugadores);
        consumers.putIfAbsent(TipoLogicaLobby.ACTUALIZAR_AVATARES, this::actualizarAvatares);
        consumers.putIfAbsent(TipoLogicaLobby.ACTUALIZAR_USERNAME, this::actualizarUsername);
    }
    
    public abstract void recibirPartida(Evento evento);
    
    public abstract void actualizarJugadoresListos(Evento evento);
    
    public abstract void actualizarJugadores(Evento evento);
    
    public abstract void actualizarAvatares(Evento evento);
    
    public abstract void actualizarUsername(Evento evento);
    
    public abstract void mostrarError(Evento evento);
    
}
