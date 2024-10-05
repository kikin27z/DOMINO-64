/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import exceptions.DominioException;

/**
 *
 * @author luisa
 */
public class Tablero {
    private Ficha extremo1;
    private Ficha extremo2;
    
    /**
     * Inicializa el tablero sin fichas
     */
    public Tablero(){
        this.extremo1=null;
        this.extremo2=null;
    }

    
    /**
     * Establece la primera ficha en juego
     * @param ficha a colocar en el tablero
     */
    public void setPrimeraFicha(Ficha ficha)throws DominioException{
        if(tableroVacio()){
            extremo1 = ficha;
            extremo2 = ficha; 
        }else
            throw new DominioException("Ya se coloco una ficha anteriormente");
    }
    
    public Ficha getExtremo1() {
        return extremo1;
    }

    public void setExtremo1(Ficha extremo1) {
        this.extremo1 = extremo1;
    }

    public Ficha getExtremo2() {
        return extremo2;
    }

    public void setExtremo2(Ficha extremo2) {
        this.extremo2 = extremo2;
    }
    
    /**
     * Valida si hay fichas en el tablero.
     * @return true si cualquiera de los extremos es nulo
     */
    public boolean tableroVacio(){
        return extremo1==null || extremo2==null;
    }
}
