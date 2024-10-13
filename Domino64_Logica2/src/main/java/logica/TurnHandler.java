/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import com.mycompany.patrones.observer.Observer;
import com.mycompany.starter.NotificadorLogica;
import entidades.Ficha;
import entidades.Jugador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import presentacion_utilities.NotificadorPresentacion;

/**
 *
 * @author luisa M
 */
public class TurnHandler implements Observer<NotificadorPresentacion> {

    private List<Jugador> jugadores;
    private static Jugador jugadorEnTurno;
    private boolean enTurno;
    private Ficha mulaMasAlta;
    
    public void setJugadores(List<Jugador> jugadores){
        this.jugadores = jugadores;
    }
    
    public void setEnTurno(boolean flag) {
        this.enTurno = flag;
    }

    public boolean estaEnTurno(){
        return enTurno;
    }
    
    @Override
    public void update(NotificadorPresentacion observable, Object ... context) {
        
    }

    public void designarPrimerTurno(Jugador primerTurno){
        jugadorEnTurno = primerTurno;
    }
    
    public Ficha getMulaMasAlta(){
        return mulaMasAlta;
    }
    
    public Jugador getJugadorEnTurno(){
        return jugadorEnTurno;
    }
    
    /**
     * metodo para cambiar el turno del jugador.
     */
    public void cambiarTurno() {
        int index = jugadores.indexOf(jugadorEnTurno);
        //si el jugador con el turno actual es
        //el jugador con el ultimo turno
        if (index == jugadores.size() - 1) {
            //inicia otra ronda de turnos, es decir
            //que ahora el siguiente jugador es el primero en la lista
            index = 0;
        } else {
            index++;
        }
        jugadorEnTurno = jugadores.get(index);
//        Ficha selectedTile = turnPlayer.getFichas().get(selectedTileIndex);
    }
    
    public boolean designarPrimerTurno() {
        TileComparator comparator = new TileComparator();
        System.out.println("setFirstTurn");
        for (Jugador player : jugadores) {
            Ficha mulaJugador = player.getHigherDouble();
            if (mulaJugador != null) {
                if (mulaMasAlta == null) {
                    mulaMasAlta = mulaJugador;
                    jugadorEnTurno = player;
                } else if (comparator.compare(mulaJugador, mulaMasAlta) > 0) {
                    mulaMasAlta = mulaJugador;
                    jugadorEnTurno = player;
                }
            }
        }
        return jugadorEnTurno!=null;
    }
    
    public void designarOtrosTurnos() {
        if (jugadorEnTurno != null) {
            jugadores.remove(jugadorEnTurno);
            Collections.shuffle(jugadores);
            jugadores.addFirst(jugadorEnTurno);
            System.out.println("se designaron los demas turnos");
        } else {
            System.out.println("turn player null");
        }
    }
    
}

/**
 * Clase comparadora de fichas. Solo implementa el metodo 'compare', cuyo
 * propósito es evaluar dos fichas para determinar cual es de mayor valor.
 *
 * @author luisa M
 */
class TileComparator implements Comparator<Ficha> {

    /**
     * Evalua las fichas de los parámetros para determinar cual es de mayor
     * valor.
     *
     * @param tile1 primera ficha a comparar
     * @param tile2 segunda ficha a comparar
     * @return 0 si las fichas tienen el mismo valor; un numero mayor a 0 si el
     * valor de tile1 es mayor que el de tile2; un numero menor a 0 si el valor
     * de tile1 es menor que el valor de que tile2
     */
    @Override
    public int compare(Ficha tile1, Ficha tile2) {
        int tileValue1 = tile1.getIzquierda() + tile1.getDerecha();
        int tileValue2 = tile2.getIzquierda() + tile2.getDerecha();
        return Integer.compare(tileValue1, tileValue2);
    }

}
