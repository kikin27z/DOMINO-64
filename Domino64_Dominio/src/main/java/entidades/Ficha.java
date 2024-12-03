package entidades;

import java.io.Serializable;

/**
 *
 * @author luisa M
 */
public class Ficha implements Serializable{
    private int izquierda, derecha;

    public Ficha(int izquierda, int derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public boolean esMula(){
        return derecha==izquierda;
    }
    
    public int getValor() {
        return izquierda+derecha;
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

    public boolean esLaMisma(Ficha ficha){
        boolean posibilidad1 = this.izquierda == ficha.izquierda && this.derecha == ficha.getDerecha();
        boolean posibilidad2 = this.izquierda == ficha.derecha && this.derecha == ficha.getIzquierda();
        return posibilidad1 || posibilidad2;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ficha[");
        sb.append(izquierda);
        sb.append("-").append(derecha);
        sb.append(']');
        return sb.toString();
    }

    
}
