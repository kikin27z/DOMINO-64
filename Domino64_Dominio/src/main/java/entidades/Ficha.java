/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;

/**
 *
 * @author luisa M
 */
public class Ficha implements Comparable<Ficha>, Serializable{
    private int izquierda, derecha;
    private final int valor;

    public Ficha(int izquierda, int derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
        valor = izquierda+derecha;
    }

    public boolean esMula(){
        return valor%2==0;
    }
    
    public int getValor() {
        return valor;
    }

    public int getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(int izquierda) {
        this.izquierda = izquierda;
    }

    public int getDerecha() {
        return derecha;
    }

    public void setDerecha(int derecha) {
        this.derecha = derecha;
    }
    
    public void girarFicha() {
        int aux = izquierda;
        izquierda = derecha;
        derecha = aux;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.valor;
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
        final Ficha other = (Ficha) obj;
        return this.valor == other.valor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ficha{");
        sb.append("izquierda=").append(izquierda);
        sb.append(", derecha=").append(derecha);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Ficha ficha) {
        return Integer.compare(valor, ficha.getValor());
    }
    
}
