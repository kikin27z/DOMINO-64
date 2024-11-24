package entidades;

import excepciones.DominioException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;


/**
 *
 * @author luisa M
 */
public class Pozo implements Serializable{
    private Queue<Ficha> fichas;

    public Pozo() {
        fichas = new ArrayDeque<>(28);
    }

    public void llenarPozo(List<Ficha> fichas){
        guardarFicha(fichas);
    }
    
    public boolean tieneFichas(){
        return !fichas.isEmpty();
    }
    
    private void guardarFicha(List<Ficha> fichasNuevas){
        for (Ficha ficha : fichasNuevas) {
            this.fichas.offer(ficha);
        }
    }
    
    public void devolverFichas(List<Ficha> fichas){
        guardarFicha(fichas);
    }
    
    /**
     * Obtiene una ficha del pozo.
     * Se remueve la ficha que se encuentra hasta el tope
     * del stack, la cual es la que se obtiene con este metodo
     * @return la ficha en el top del stack
     */
    public Ficha jalarFicha(){
        if(tieneFichas()){
            Ficha ficha = fichas.poll();
            return ficha;
        }
        return null;
    }
    
    public Queue<Ficha> getFichasRestantes() {
        return fichas;
    }
    
}
