package entidades;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author luisa M
 */
public class Tablero {
    private Deque<Ficha> trenFichas;

    public Tablero() {
        trenFichas = new ArrayDeque<>();
    }

    public int obtenerExtremoIzq() {
        Ficha ficha = trenFichas.peekFirst();
        return ficha.getIzquierda();
    }

    public int obtenerExtremoDer() {
        Ficha ficha = trenFichas.peekLast();
        int valor = ficha.getDerecha();
        return valor;
    }

    private boolean estaVacio() {
        return trenFichas.isEmpty();
    }

    public void agregarFicha(Ficha ficha, boolean extremoIzq) {
        if (estaVacio()) {
            trenFichas.offer(ficha);
            imprimirJugada(ficha);
            return;
        } 
        if (extremoIzq) 
            agregarFichaIzquierda(ficha);
         else 
            agregarFichaDerecha(ficha);
        
        
//        imprimirJugada(ficha);
    }

    private void validarExtremoIzquierdo(Ficha ficha) {
        Ficha validar = trenFichas.peekFirst();
        if (validar.getIzquierda() != ficha.getDerecha()) {
            ficha.girarFicha();
        }
    }

    private void validarExtremoDerecho(Ficha ficha) {
        Ficha validar = trenFichas.peekLast();
        if (validar.getDerecha() != ficha.getIzquierda()) {
            ficha.girarFicha();
        }
    }

    private void agregarFichaDerecha(Ficha ficha) {
        validarExtremoDerecho(ficha);
        trenFichas.offerLast(ficha);
    }

    private void agregarFichaIzquierda(Ficha ficha) {
        validarExtremoIzquierdo(ficha);
        trenFichas.offerFirst(ficha);
    }
    
    private void imprimirJugada(Ficha ficha){
        System.out.println("Tablero se inserto " + ficha);
        System.out.println("Jugada nueva: " + obtenerExtremoIzq() + "-" + obtenerExtremoDer());
    }
}
