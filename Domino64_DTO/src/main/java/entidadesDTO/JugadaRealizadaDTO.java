package entidadesDTO;

import java.io.Serializable;

/**
 *
 * @author karim
 */
public class JugadaRealizadaDTO implements Serializable {
    private PosicionDTO posicion;
    private boolean extremoIzq;
    private double coordenadaX;
    private double coordenadaY;
    private FichaDTO ficha;

    public JugadaRealizadaDTO(PosicionDTO posicion, boolean izquierda) {
        this.posicion = posicion;
        this.extremoIzq = izquierda;
    }

    public JugadaRealizadaDTO(PosicionDTO posicion, boolean extremoIzq, double coordenadaX, double coordenadaY, FichaDTO ficha) {
        this.posicion = posicion;
        this.extremoIzq = extremoIzq;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.ficha = ficha;
    }

    public JugadaRealizadaDTO() {
    }
    
    

    public PosicionDTO getPosicion() {
        return posicion;
    }

    public void setPosicion(PosicionDTO posicion) {
        this.posicion = posicion;
    }

    public boolean isExtremoIzq() {
        return extremoIzq;
    }

    public void setExtremoIzq(boolean extremoIzq) {
        this.extremoIzq = extremoIzq;
    }

    public double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }


    public FichaDTO getFicha() {
        return ficha;
    }

    public void setFicha(FichaDTO ficha) {
        this.ficha = ficha;
    }
    
    
}
