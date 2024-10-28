/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesDTO;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class TableroDTO {
    private Deque<FichaDTO> trenFichas;
    
    public TableroDTO() {
        trenFichas = new ArrayDeque<>();
    }

    public void setTren(List<FichaDTO> fichas){
        Deque<FichaDTO> fichasDTO = new ArrayDeque<>(fichas);
        trenFichas = fichasDTO;
    }
    
    public boolean tableroVacio() {
        return trenFichas.isEmpty();
    }
    
    public void insertarFicha(FichaDTO ficha){
        if (trenFichas.isEmpty()) {
            ficha.setOrientacion(ficha.getOrientacion());
            trenFichas.offer(ficha);
            return;
        }

        if (obtenerExtremoIzq() == ficha.getDerecha() || obtenerExtremoIzq() == ficha.getIzquierda()) {
            sentidoFichaIzq(ficha);
            orientarFicha(ficha, obtenerFichaIzq());
            trenFichas.offerFirst(ficha);
        } else {
            sentidoFichaDer(ficha);
            orientarFicha(ficha, obtenerFichaDer());
            trenFichas.offerLast(ficha);
        }
    }

    /**
     * Método que orienta la ficha ya sea horizontalmente o verticalmente
     * dependiendo la jugada de la partida, por ejemplo en caso de ser una ficha
     * mula se debe cambiar la orientación, de lo contrario se queda igual al
     * extremo.
     *
     * @param ficha Ficha a orientar
     * @param extremo Ficha del extremo con la cual se compara la orientación.
     */
    private void orientarFicha(FichaDTO ficha, FichaDTO extremo) {
        if (ficha.esMula()) {
            if (extremo.getOrientacion() == extremo.ORIENTACION_HORIZONTAL) {
                ficha.setOrientacion(ficha.ORIENTACION_VERTICAL);
            } else {
                ficha.setOrientacion(ficha.ORIENTACION_HORIZONTAL);
            }
        } else {
            ficha.setOrientacion(extremo.getOrientacion());
        }
    }
    
    /**
     * Obtiene la ficha ubicada en el extremo izquierdo del tren de fichas.
     *
     * @return Ficha ubicada en el extremo izquierdo.
     */
    public FichaDTO obtenerFichaIzq() {
        return trenFichas.peekFirst();
    }

    /**
     * Obtiene la ficha ubicada en el extremo derecho del tren de fichas.
     *
     * @return Ficha ubicada en el extremo derecho.
     */
    public FichaDTO obtenerFichaDer() {
        return trenFichas.peekLast();
    }
    
    /**
     * Método que gira la ficha en caso de no coincidir con el extremo izquierdo
     * de la partida.
     *
     * @param ficha Ficha a girar.
     */
    private void sentidoFichaIzq(FichaDTO ficha) {
        if (obtenerExtremoIzq() != ficha.getDerecha()) {
            ficha.girarFicha();
        }
    }

    /**
     * Gira la ficha si no coincide con el valor del extremo derecho del tren de
     * fichas.
     *
     * @param ficha Ficha que se evaluará y posiblemente se girará.
     */
    private void sentidoFichaDer(FichaDTO ficha) {
        if (obtenerExtremoDer() != ficha.getIzquierda()) {
            ficha.girarFicha();
        }
    }
    
    public int obtenerExtremoIzq() {
        return trenFichas.peekFirst().getIzquierda();
    }

    public int obtenerExtremoDer() {
        return trenFichas.peekLast().getDerecha();
    }
    
    public void insertarFichaLadoDerecho(FichaDTO ficha){
        trenFichas.addLast(ficha);
    }
    
    public void insertarFichaLadoIzquierdo(FichaDTO ficha){
        trenFichas.addFirst(ficha);
    }
}
