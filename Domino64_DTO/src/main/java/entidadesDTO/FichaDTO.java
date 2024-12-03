package entidadesDTO;

import java.io.Serializable;

/**
 *
 * @author luisa M
 */
public class FichaDTO implements Serializable {

    private int izquierda;
    private int derecha;

    public FichaDTO(int izquierda, int derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public boolean esMula() {
        return izquierda == derecha;
    }

    public void girarFicha() {
        int aux = izquierda;
        izquierda = derecha;
        derecha = aux;
    }

    public int sumaPuntos() {
        return izquierda + derecha;
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

    public boolean esLaMismaFicha(FichaDTO ficha) {
        return ficha.getDerecha() == this.derecha && ficha.getIzquierda() == this.izquierda
                || ficha.getDerecha() == this.izquierda && ficha.getIzquierda() == this.derecha;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FichaDTO[");
        sb.append(izquierda);
        sb.append("-").append(derecha);
        sb.append(']');
        return sb.toString();
    }





}
