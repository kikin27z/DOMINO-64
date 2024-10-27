/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.luisa.manejador;

import com.luisa.entidades.Cuenta;
import com.luisa.entidades.Partida;

/**
 *
 * @author luisa M
 */
public class ManejadorPartida {

    private Partida partida;
    
    public ManejadorPartida(Partida partida){
        this.partida = partida;
    }
    
    public boolean agregarJugador(Cuenta jugador){
        return partida.agregarJugador(jugador);
    }
    
    public void removerJugador(Cuenta jugador){
        partida.removerJugador(jugador);
    }
}
