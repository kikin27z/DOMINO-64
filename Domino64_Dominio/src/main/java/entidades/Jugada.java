package entidades;

import java.util.List;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class Jugada {
    private int extremoIzq;
    private int extremoDer;

    public int getExtremoIzq() {
        return extremoIzq;
    }

    public void setExtremoIzq(int extremoIzq) {
        this.extremoIzq = extremoIzq;
    }

    public int getExtremoDer() {
        return extremoDer;
    }

    public void setExtremoDer(int extremoDer) {
        this.extremoDer = extremoDer;
    }
    
    private boolean esValida(Ficha ficha){
        return validaPorUnLado(ficha.getDerecha()) || validaPorUnLado(ficha.getIzquierda());
    }
    
    private boolean validaPorUnLado(int valor){
        return extremoDer == valor || valor  == extremoIzq;
    }
    
    
    public boolean puedeJugar(List<Ficha> fichas){
        for (Ficha ficha : fichas) {
            if(esValida(ficha)){
                return true;
            }
        }
        return false;
    }
    
}
