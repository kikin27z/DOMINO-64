/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesDTO;

/**
 *
 * @author luisa M
 */
public class FichaDTO {
    public final int ORIENTACION_VERTICAL = 0;
    public final int ORIENTACION_HORIZONTAL = 1;
    private int orientacion;
    private int izquierda;
    private int derecha;

    public int getOrientacion() {
        return orientacion;
    }
    
    public boolean esMula() {
        return izquierda == derecha;
    }
    
    public void girarFicha() {
        int aux = izquierda;
        izquierda = derecha;
        derecha = aux;
    }

    public void setOrientacion(int orientacion) {
        this.orientacion = orientacion;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ficha{");
        sb.append("orientacion=").append(orientacion);
        sb.append(", izquierda=").append(izquierda);
        sb.append(", derecha=").append(derecha);
        sb.append('}');
        return sb.toString();
    }
    
    
}
