package entidades;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Clase Pozo que representa el conjunto de fichas disponibles en un juego de dominó.
 * Las fichas se almacenan en una estructura de datos tipo Cola (Queue) y permiten realizar
 * operaciones de agregar fichas, extraer fichas y verificar si el pozo tiene fichas disponibles.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class Pozo implements Serializable{
    private Queue<Ficha> fichas;

    /**
     * Constructor que inicializa el pozo con una cola vacía de fichas.
     */
    public Pozo() {
        fichas = new ArrayDeque<>(28);
    }

    /**
     * Llena el pozo con un conjunto de fichas nuevas.
     *
     * @param fichas Lista de fichas que se agregarán al pozo.
     */
    public void llenarPozo(List<Ficha> fichas){
        guardarFicha(fichas);
    }

    /**
     * Verifica si el pozo contiene fichas disponibles.
     *
     * @return true si el pozo tiene fichas, false si está vacío.
     */
    public boolean tieneFichas(){
        return !fichas.isEmpty();
    }

    /**
     * Método privado para agregar fichas al pozo.
     *
     * @param fichasNuevas Lista de fichas que se van a agregar al pozo.
     */
    private void guardarFicha(List<Ficha> fichasNuevas){
        for (Ficha ficha : fichasNuevas) {
            this.fichas.offer(ficha);
        }
    }

    /**
     * Devuelve fichas al pozo. 
     * Las fichas dadas se agregan nuevamente al pozo.
     *
     * @param fichas Lista de fichas que se devolverán al pozo.
     */
    public void devolverFichas(List<Ficha> fichas){
        guardarFicha(fichas);
    }

    /**
     * Obtiene una ficha del pozo.
     * La ficha es la que se encuentra en la parte superior de la cola y se elimina del pozo.
     *
     * @return La ficha extraída del pozo, o null si el pozo está vacío.
     */
    public Ficha jalarFicha(){
        if(tieneFichas()){
            Ficha ficha = fichas.poll();
            return ficha;
        }
        return null;
    }

    /**
     * Obtiene las fichas restantes en el pozo.
     *
     * @return La cola de fichas que aún están en el pozo.
     */
    public Queue<Ficha> getFichasRestantes() {
        return fichas;
    }
}
