/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.luisa.manejador;

import com.luisa.entidades.Ficha;
import com.luisa.entidades.Tablero;

/**
 *
 * @author luisa M
 */
public class ManejadorTablero {

    private Tablero tablero;
    private int extremoIzq, extremoDer;
    
    public ManejadorTablero(){
        this.tablero = new Tablero();
    }
    
    /**
     * agrega una ficha al tren de fichas del tablero. 
     * @param ficha ficha a colocar
     * @param extremo extremo en el que se va a colocar la ficha
     */
    public void colocarFicha(Ficha ficha, int extremo){
        tablero.agregarFicha(ficha, extremo);
        actualizarExtremo(ficha, extremo);
    }
    
    /**
     * actualiza el valor del extremo especificado en el tren de fichas
     * del tablero cuando se agrega una nueva ficha. Si la ficha se 
     * coloco en el lado izquierdo, el extremo izquiero se actualiza al valor
     * del lado izquierdo de la ficha. Lo mismo cuando se coloca una ficha del
     * extremo derecho.
     * @param ficha La ficha que se acaba de agregar al tablero
     * @param extremo Extremo al cual se agrego la ficha
     */
    private void actualizarExtremo(Ficha ficha, int extremo){
        if(extremo == tablero.DERECHA)
            extremoIzq = ficha.getIzquierda();
        else
            extremoDer = ficha.getDerecha();
    }
    
    /**
     * metodo que valida en que extremo se puede colocar la ficha, si
     * es que se puede colocar.
     * @param ficha la ficha a validar el tipo de jugada posible
     * @return la jugada que se puede hacer con la ficha
     */
    public JugadaPosible validarFicha(Ficha ficha){
        JugadaPosible jugada;
        //si se puede poner en la derecha
        if(jugablePorDerecha(ficha)){
            //y tambien en la izquierda
            if(jugablePorIzquierda(ficha)){
                //es jugable por ambos lados
                jugada = JugadaPosible.AMBOS_LADOS;
            }else//si no
                //solo se puede en derecha
                jugada = JugadaPosible.POR_DERECHA;
        }else if(jugablePorIzquierda(ficha)){//si se puede poner en la izquierda
            //solo se puede por este lado
            jugada = JugadaPosible.POR_IZQUIERDA;
        }else
            //si tampoco se puede en izquierda, no es ficha jugable
            jugada=JugadaPosible.NINGUNA;
        return jugada;
    }
    
    /**
     * valida si se puede poner en el extremo derecho
     * @param ficha ficha a validar
     * @return true si se puede colocar, false en caso contrario
     */
    private boolean jugablePorDerecha(Ficha ficha){
        return extremoDer == ficha.getDerecha() || extremoDer == ficha.getIzquierda();
    }
    
    /**
     * valida si se puede poner en el extremo izquierdo
     * @param ficha ficha a validar
     * @return true si se puede colocar, false en caso contrario
     */
    private boolean jugablePorIzquierda(Ficha ficha){
        return extremoIzq == ficha.getDerecha() || extremoIzq == ficha.getIzquierda();
    }
}
