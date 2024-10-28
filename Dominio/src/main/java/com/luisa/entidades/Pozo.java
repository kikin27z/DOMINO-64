/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luisa.entidades;

import com.luisa.excepcionesDominio.DominioException;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author luisa M
 */
public class Pozo {
    private Stack<Ficha> fichas;

    public Pozo() {
        fichas = new Stack<>();
    }

    public void llenarPozo(List<Ficha> fichas){
        this.fichas.addAll(fichas);
    }
    
    public boolean tieneFichas(){
        return !fichas.isEmpty();
    }
    
    /**
     * Obtiene una ficha del pozo.
     * Se remueve la ficha que se encuentra hasta el tope
     * del stack, la cual es la que se obtiene con este metodo
     * @return la ficha en el top del stack
     * @throws DominioException si no hay fichas en el pozo
     */
    public Ficha jalarFicha()throws DominioException{
        if(tieneFichas()){
            Ficha ficha = fichas.pop();
            return ficha;
        }
        throw new DominioException("Pozo vacio");
    }
    
    public Stack<Ficha> getFichas() {
        return fichas;
    }
    
}
