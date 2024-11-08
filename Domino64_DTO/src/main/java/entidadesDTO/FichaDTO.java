package entidadesDTO;

/**
 *
 * @author luisa M
 */
public class FichaDTO {
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
        sb.append("izquierda=").append(izquierda);
        sb.append(", derecha=").append(derecha);
        sb.append('}');
        return sb.toString();
    }
    
    
}
