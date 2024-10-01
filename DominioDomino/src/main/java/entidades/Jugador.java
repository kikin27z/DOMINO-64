/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisa
 */
public class Jugador {
    private String username;
    private List<Ficha> fichas;
    
    public Jugador(String username){
        this.fichas = new ArrayList<>();
        this.username = username;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public void removerFicha(Ficha ficha){
        fichas.remove(ficha);
    }
    
    public void agregarFicha(Ficha ficha){
        fichas.add(ficha);
    }
}
