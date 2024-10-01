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
 * @author luiis
 */
public class Pozo {
    Stack<Ficha> fichas;
    
    public Pozo(Stack<Ficha> fichas){
        this.fichas=fichas;
    }
    
    public Ficha jalarFicha()throws DominioException{
        if(hayFichas()){
            Ficha ficha = fichas.pop();
            return ficha;
        }
        throw new DominioException("Pozo vacio");
    }
    
    public boolean hayFichas(){
        return fichas.isEmpty();
    }
}
