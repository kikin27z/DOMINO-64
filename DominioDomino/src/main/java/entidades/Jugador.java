/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<Ficha> getFichas(){
        return fichas;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        return Objects.equals(this.username, other.username);
    }
    
    
}
