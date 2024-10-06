/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import exceptions.DominioException;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author luisa
 */
public class Pozo {
    private Stack<Ficha> fichas;
    
    public Pozo(){
        this.fichas= new Stack<>();
    }
    
    /**
     * Obtiene una ficha del pozo.
     * Se remueve la ficha que se encuentra hasta el tope
     * del stack, la cual es la que se obtiene con este metodo
     * @return la ficha en el top del stack
     * @throws DominioException si no hay fichas en el pozo
     */
    public Ficha jalarFicha()throws Exception{
        if(hayFichas()){
            Ficha ficha = fichas.pop();
            return ficha;
        }
        throw new Exception("Pozo vacio");
    }
    
    public boolean hayFichas(){
        return !fichas.isEmpty();
    }
    
    public void llenarPozo(Stack<Ficha> fichas){
        this.fichas = fichas;
    }
    
    public Stack<Ficha> getFichas(){
        return fichas;
    }
}
