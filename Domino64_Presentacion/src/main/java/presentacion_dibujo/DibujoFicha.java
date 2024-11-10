package presentacion_dibujo;

import entidadesDTO.PosicionDTO;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author karim
 */
public class DibujoFicha extends Canvas {
    private boolean mula;
    private boolean izquierda;
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

    public boolean isMula() {
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
        return izquierda;
    }

    public void setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
    }
    
    
}
