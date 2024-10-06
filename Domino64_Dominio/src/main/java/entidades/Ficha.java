/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.Comparator;

/**
 *
 * @author luisa
 */
public class Ficha {
    private int lado1;
    private int lado2;
    private String imgUrl;

    public Ficha(int lado1, int lado2) {
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public Ficha(int lado1, int lado2, String imgUrl) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getLado1() {
        return lado1;
    }

    public void setLado1(int lado1) {
        this.lado1 = lado1;
    }

    public int getLado2() {
        return lado2;
    }

    public void setLado2(int lado2) {
        this.lado2 = lado2;
    }

    @Override
    public String toString() {
        return "Ficha{" + "lado1=" + lado1 + ", lado2=" + lado2 + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.lado1;
        hash = 89 * hash + this.lado2;
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
        if (this.lado1 != other.lado1) {
            return false;
        }
        return this.lado2 == other.lado2;
    }

    
}
