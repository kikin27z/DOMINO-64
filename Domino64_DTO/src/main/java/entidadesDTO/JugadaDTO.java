package entidadesDTO;

import java.io.Serializable;

/**
 *
 * @author karim
 */
public class JugadaDTO implements Serializable {
    private int izquierda;
    private int derecha;
    private FichaDTO ficha;

    public JugadaDTO() {
    }
    
    public JugadaDTO(int izquierda, int derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }
    
    public JugadaValidaDTO determinarJugada(FichaDTO ficha){
        JugadaValidaDTO jugada;
        if(sePuedeJugarAmbosExtremo(ficha)){
            jugada = JugadaValidaDTO.AMBOS_EXTREMOS;
        }else if(sePuedeJugarIzquierda(ficha)){
            jugada = JugadaValidaDTO.SOLO_IZQUIERDA;
        }else if(sePuedeJugarDerecha(ficha)){
            jugada = JugadaValidaDTO.SOLO_DERECHA;
        }else{
            jugada = JugadaValidaDTO.NINGUNA;
        }
        return jugada;
    }
    
    private boolean sePuedeJugarAmbosExtremo(FichaDTO ficha){
        return sePuedeJugarIzquierda(ficha) && sePuedeJugarDerecha(ficha);
    }
    
    private boolean sePuedeJugarIzquierda(FichaDTO ficha){
        return this.izquierda == ficha.getIzquierda() || this.izquierda == ficha.getDerecha();
    }
    private boolean sePuedeJugarDerecha(FichaDTO ficha){
        return this.derecha == ficha.getIzquierda() || this.derecha == ficha.getDerecha();
    }

    public FichaDTO getFicha() {
        return ficha;
    }

    public void setFicha(FichaDTO ficha) {
        this.ficha = ficha;
    }

    public void setIzquierda(int izquierda) {
        this.izquierda = izquierda;
    }

    public void setDerecha(int derecha) {
        this.derecha = derecha;
    }
    
    
    
    
    @Override
    public String toString() {
        return "JugadaDTO{" + "extremoIzquierdo=" + izquierda + ", extremoDerecho=" + derecha + '}';
    }

    public void imprimirJugada(FichaDTO ficha,JugadaValidaDTO jugada){
        System.out.print(this);
        System.out.println(" - " + ficha + ", jugada = " + jugada);
    }
    
}
