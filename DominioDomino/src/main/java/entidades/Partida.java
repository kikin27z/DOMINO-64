/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author luisa
 */
public class Partida {
    private Pozo pozo;
    private Tablero tablero;
    private List<Jugador> jugadores;
    private String codigoPartida;
    
    public Partida(String codigoPartida){
        this.jugadores = new ArrayList<>();
        this.codigoPartida=codigoPartida;
        //this.pozo = new Pozo();
        this.tablero = new Tablero();
    }
    
    public void agregarJugador(Jugador jugador){
        this.jugadores.add(jugador);
    }
    
    public void eliminarJugador(Jugador jugador){
        this.jugadores.remove(jugador);
    }
    
    public void designarTurnos(){
        Collections.shuffle(jugadores);
    }
    
    public String getCodigoPartida(){
        return this.codigoPartida;
    }
    
}
