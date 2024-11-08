package entidadesDTO;

/**
 *
 * @author karim
 */
public class JugadaRealizadaDTO {
    private PosicionDTO posicion;
    private boolean izquierda;

    public JugadaRealizadaDTO(PosicionDTO posicion, boolean izquierda) {
        this.posicion = posicion;
        this.izquierda = izquierda;
    }

    public JugadaRealizadaDTO() {
    }
    
    

    public PosicionDTO getPosicion() {
        return posicion;
    }

    public void setPosicion(PosicionDTO posicion) {
        this.posicion = posicion;
    }

    public boolean isIzquierda() {
        return izquierda;
    }

    public void setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
    }
}
