package entidades;

import excepciones.DominioException;
import java.util.List;
import java.util.Stack;


/**
 *
 * @author luisa M
 */
public class Pozo {
    private Stack<Ficha> fichas;

    public Pozo() {
        fichas = new Stack<>();
    }

    public void llenarPozo(List<Ficha> fichas){
        this.fichas.addAll(fichas);
    }
    
    public boolean tieneFichas(){
        return !fichas.isEmpty();
    }
    
    /**
     * Obtiene una ficha del pozo.
     * Se remueve la ficha que se encuentra hasta el tope
     * del stack, la cual es la que se obtiene con este metodo
     * @return la ficha en el top del stack
     * @throws DominioException si no hay fichas en el pozo
     */
    public Ficha jalarFicha(){
        if(tieneFichas()){
            Ficha ficha = fichas.pop();
            return ficha;
        }
        return null;
    }
    
    public Stack<Ficha> getFichas() {
        return fichas;
    }
    
}
