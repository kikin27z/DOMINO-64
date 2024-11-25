package presentacion_dibujo;

import entidadesDTO.PosicionDTO;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author karim
 */
public class DibujoFicha extends Canvas {
    private boolean mula;
    private boolean extremoIzq;
    private int extremo;
    private PosicionDTO posicion;

    public DibujoFicha(double d, double d1) {
        super(d, d1);
    }

    public DibujoFicha() {
    }


    public void setMula(boolean mula) {
        this.mula = mula;
    }

    public boolean esMula() {
        return mula;
    }

    public PosicionDTO getPosicion() {
        return posicion;
    }

    public void setPosicion(PosicionDTO posicion) {
        this.posicion = posicion;
    }

    public int getExtremo() {
        return extremo;
    }

    public void setExtremo(int extremo) {
        this.extremo = extremo;
    }

    public void definirLargo(double valor){
        this.setHeight(valor);
    }
    
    public void definirAncho(double valor){
        this.setWidth(valor);
    }

    public boolean sePusoEnLaIzquierda() {
        return extremoIzq;
    }

    public void setExtremoIzq(boolean extremoIzq) {
        this.extremoIzq = extremoIzq;
    }

    public boolean isExtremoIzq() {
        return extremoIzq;
    }
    

    @Override
    public String toString() {
        return "DibujoFicha{" + "mula=" + mula + ", extremoIzq=" + extremoIzq + ", extremo=" + extremo + ", posicion=" + posicion + '}';
    }
    
    
}
