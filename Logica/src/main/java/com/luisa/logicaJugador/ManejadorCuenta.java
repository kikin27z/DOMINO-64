/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luisa.logicaJugador;

//import client.Client;
import com.luisa.entidades.Cuenta;
import com.luisa.entidades.Partida;
import event.Event;
import event.EventType;
import eventos.EventoUnirsePartida;
import java.util.Random;
import observer.Observer;

/**
 *
 * @author luisa M
 */
public class ManejadorCuenta implements Observer<Event>{
    private Cuenta jugador;
//    private Client cliente;
    
    public ManejadorCuenta(){
        this.jugador = new Cuenta(generarIdCuenta());
//        cliente = new Client(5000,jugador.getId());
//        cliente.addObserver(this);
//        cliente.initClient();
    }
    
    private String generarIdCuenta(){
        Random rnd = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(rnd.nextInt(10));
        }
        return builder.toString();
    }
    
    public void unirmePartida(Partida partida){
//        cliente.sendMessage(new EventoUnirsePartida(jugador.getId(),partida));
    }

    @Override
    public void update(Event event) {
        EventType type = event.getType();
        switch(type){
            case NEW_CLIENT:
        }
    }
}
