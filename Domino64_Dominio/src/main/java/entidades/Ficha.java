package entidades;

import java.io.Serializable;

/**
 * Representa una ficha de dominó, con dos valores (izquierda y derecha) y métodos
 * para manipularla, como verificar si es una mula, obtener su valor total,
 * girarla, y comparar con otra ficha.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class Ficha implements Serializable,Comparable<Ficha> {
    private int izquierda, derecha;

    /**
     * Constructor que crea una ficha con los valores dados.
     * 
     * @param izquierda Valor en el lado izquierdo de la ficha.
     * @param derecha Valor en el lado derecho de la ficha.
     */
    public Ficha(int izquierda, int derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    /**
     * Determina si la ficha es una mula (cuando ambos lados tienen el mismo valor).
     * 
     * @return true si la ficha es mula, es decir, ambos lados son iguales.
     */
    public boolean esMula() {
        return derecha == izquierda;
    }

    /**
     * Obtiene el valor total de la ficha (suma de los valores en ambos lados).
     * 
     * @return El valor total de la ficha.
     */
    public int getValor() {
        return izquierda + derecha;
    }

    /**
     * Obtiene el valor del lado izquierdo de la ficha.
     * 
     * @return El valor del lado izquierdo.
     */
    public int getIzquierda() {
        return izquierda;
    }

    /**
     * Establece el valor del lado izquierdo de la ficha.
     * 
     * @param izquierda El nuevo valor para el lado izquierdo.
     */
    public void setIzquierda(int izquierda) {
        this.izquierda = izquierda;
    }

    /**
     * Obtiene el valor del lado derecho de la ficha.
     * 
     * @return El valor del lado derecho.
     */
    public int getDerecha() {
        return derecha;
    }

    /**
     * Establece el valor del lado derecho de la ficha.
     * 
     * @param derecha El nuevo valor para el lado derecho.
     */
    public void setDerecha(int derecha) {
        this.derecha = derecha;
    }

    /**
     * Gira la ficha, intercambiando los valores de los lados izquierdo y derecho.
     */
    public void girarFicha() {
        int aux = izquierda;
        izquierda = derecha; // Intercambia los valores de ambos lados
        derecha = aux;
    }

    /**
     * Compara si la ficha actual es la misma que otra ficha.
     * Dos fichas son iguales si tienen los mismos valores en ambos lados, sin importar el orden.
     * 
     * @param ficha La ficha a comparar.
     * @return true si las fichas son iguales, false si no lo son.
     */
    public boolean esLaMisma(Ficha ficha) {
        boolean posibilidad1 = this.izquierda == ficha.izquierda && this.derecha == ficha.getDerecha();
        boolean posibilidad2 = this.izquierda == ficha.derecha && this.derecha == ficha.getIzquierda();
        return posibilidad1 || posibilidad2; // Compara las dos posibles combinaciones de lados
    }

    /**
     * Representación en forma de cadena de la ficha, mostrando los valores en ambos lados.
     * 
     * @return Cadena que representa la ficha.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ficha[");
        sb.append(izquierda);
        sb.append("-").append(derecha);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public int compareTo(Ficha o) {
        return Integer.compare(getValor(), o.getValor());
    }

}
