package entidadesDTO;

import java.io.Serializable;

/**
 *
 * @author karim
 */
public class JugadaDTO implements Serializable {
    private int izquierda;
    private int derecha;

    public JugadaDTO() {
    }
    
    public JugadaDTO(int izquierda, int derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }
    
    public PosibleJugadaDTO determinarJugada(FichaDTO ficha){
        PosibleJugadaDTO jugada;
        if(sePuedeJugarAmbosExtremo(ficha)){
            jugada = PosibleJugadaDTO.AMBOS_EXTREMOS;
        }else if(sePuedeJugarIzquierda(ficha)){
            jugada = PosibleJugadaDTO.SOLO_IZQUIERDA;
        }else if(sePuedeJugarDerecha(ficha)){
            jugada = PosibleJugadaDTO.SOLO_DERECHA;
        }else{
            jugada = PosibleJugadaDTO.NINGUNA;
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

    public void imprimirJugada(FichaDTO ficha, PosibleJugadaDTO jugada){
        System.out.print(this);
        System.out.println(" - " + ficha + ", jugada = " + jugada);
    }
    
}
