/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.luisa.manejador;

import entidades.Ficha;
import entidades.Jugador;
import com.luisa.excepcionesLogica.LogicException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class ManejadorTurnos {

    private List<Jugador> jugadores;
    
    public ManejadorTurnos(List<Jugador> jugadores){
        this.jugadores = jugadores;
    }
    
    public void cambiarTurno(){
        
    }
    
    public void designarTurnos()throws LogicException{
        Jugador primero = designarPrimerTurno();
    }
    
    private Jugador designarPrimerTurno()throws LogicException{
        List<Ficha> mulasJugadores = new ArrayList<>();
        List<Jugador> jugadoresConMula = new ArrayList<>();
        for(Jugador jugador: jugadores){
            Ficha mayorMula = jugador.obtenerMayorMula();
            if(mayorMula!=null){
                jugadoresConMula.add(jugador);
                mulasJugadores.add(mayorMula);
            }
        }
        
        if(!mulasJugadores.isEmpty()){
            int index = mulasJugadores.indexOf(Collections.max(mulasJugadores));
            return jugadoresConMula.get(index);
        }
        throw new LogicException();
    }
    
}
