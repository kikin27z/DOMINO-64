/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author luisa M
 */
public class Tablero implements Serializable{
    private Deque<Ficha> trenFichas;
    public final int IZQUIERDA = 0;
    public final int DERECHA = 1;
    
    public Tablero() {
        trenFichas = new ArrayDeque<>();
    }
    
    public int obtenerExtremo(int extremo){
        Ficha ficha;
        int valor;
        if(extremo == IZQUIERDA){
            ficha = trenFichas.peekFirst();
            valor = ficha.getIzquierda();
        }else {
            ficha = trenFichas.peekLast();
            valor = ficha.getDerecha();
        }
        return valor;
    }
    
    public boolean estaVacio(){
        return trenFichas.isEmpty();
    }
    
    public void agregarFicha(Ficha ficha, int extremo){
        if(estaVacio()){
            trenFichas.offer(ficha);
        }else{
            if(extremo == IZQUIERDA)
                trenFichas.offerFirst(ficha);
            else
                trenFichas.offerLast(ficha);
        }
    }
    
    public void agregarFichaDerecha(Ficha ficha){
        trenFichas.offerLast(ficha);
    }
    
    public void agregarFichaIzquierda(Ficha ficha){
        trenFichas.offerFirst(ficha);
    }
}
