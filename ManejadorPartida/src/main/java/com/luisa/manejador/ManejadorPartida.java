package com.luisa.manejador;

import entidades.Cuenta;
import entidades.Partida;
import eventos.EventoPartida;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 *
 * @author luisa M
 */
public class ManejadorPartida {
    private Map<Enum<?>,Consumer<EventoPartida>> eventos;
    private Partida partida;
    
    public ManejadorPartida(Partida partida){
        this.partida = partida;
        eventos = new ConcurrentHashMap<>();
    }
    
    private void manejarInicioPartida(EventoPartida evento){
        
    }
    
    public boolean agregarJugador(Cuenta jugador){
        return partida.agregarJugador(jugador);
    }
    
    public void removerJugador(Cuenta jugador){
        partida.removerJugador(jugador);
    }
}
