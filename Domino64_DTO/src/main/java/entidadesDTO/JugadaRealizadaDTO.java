package entidadesDTO;

/**
 *
 * @author karim
 */
public class JugadaRealizadaDTO {
    private PosicionDTO posicion;
    private boolean extremoIzq;
    private int coordenaX;
    private int coordenaY;
    private FichaDTO ficha;

    public JugadaRealizadaDTO(PosicionDTO posicion, boolean izquierda) {
        this.posicion = posicion;
        this.extremoIzq = izquierda;
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

    public int getCoordenaX() {
        return coordenaX;
    }

    public void setCoordenaX(int coordenaX) {
        this.coordenaX = coordenaX;
    }

    public int getCoordenaY() {
        return coordenaY;
    }

    public void setCoordenaY(int coordenaY) {
        this.coordenaY = coordenaY;
    }

    public FichaDTO getFicha() {
        return ficha;
    }

    public void setFicha(FichaDTO ficha) {
        this.ficha = ficha;
    }
    
    
}
