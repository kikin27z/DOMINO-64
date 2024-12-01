/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicaLobby;

import domino64.eventos.base.Evento;
import domino64.eventos.base.error.TipoError;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import observer.Observer;
import tiposLogicos.TipoLogicaLobby;

/**
 * Clase que representa una implementacion de un observador. 
 * Este observador concreto define los metodos que van a manejar
 * los distintos eventos que le interesa recibir a este observador.
 * La lista de enum define los eventos que va a observar.
 * Tiene un mapeo de consumers por cada tipo de evento, esto quiere decir
 * que por cada tipo de evento que va a recibir, le asigna un metodo
 * que se va a ejecutar al recibir el evento especifico.
 * Este observador solo recibe eventos que genera el jugador.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public abstract class ObservadorLobbyLocal implements Observer<Evento>{
    protected Map<Enum<?>, Consumer<Evento>> consumers;
    protected final List<Enum<?>> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_LOGICO,
                    TipoError.ERROR_DE_SERVIDOR,
                    TipoLogicaLobby.JUGADOR_NO_LISTO,
                    TipoLogicaLobby.JUGADOR_LISTO,
                    TipoLogicaLobby.JUGADOR_NUEVO,
                    TipoLogicaLobby.JUGADOR_SALIO,
                    TipoLogicaLobby.ACTUALIZAR_AVATARES,
                    TipoLogicaLobby.ACTUALIZAR_USERNAME
            ));

    public ObservadorLobbyLocal() {
        this.consumers = new ConcurrentHashMap<>();
    }
    
    @Override
    public void update(Evento evento){
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
        System.out.println("evento a borrar: "+evento);
        this.eventos.remove(evento);
        consumers.remove(evento);
    }
    
    protected void setConsumers(){
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
        consumers.putIfAbsent(TipoError.ERROR_LOGICO, this::manejarError);
        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_NUEVO, this::actualizarJugadores);
        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_NO_LISTO, this::actualizarJugadoresListos);
        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_LISTO, this::actualizarJugadoresListos);
        consumers.putIfAbsent(TipoLogicaLobby.JUGADOR_SALIO, this::actualizarJugadores);
        consumers.putIfAbsent(TipoLogicaLobby.ACTUALIZAR_AVATARES, this::actualizarAvatares);
        consumers.putIfAbsent(TipoLogicaLobby.ACTUALIZAR_USERNAME, this::actualizarUsernames);
    }
    
    public abstract void recibirPartida(Evento evento);
    
    public abstract void actualizarJugadoresListos(Evento evento);
    
    public abstract void actualizarJugadores(Evento evento);
    
    public abstract void actualizarAvatares(Evento evento);
    
    public abstract void actualizarUsernames(Evento evento);
    
    public abstract void manejarError(Evento evento);
    
}
