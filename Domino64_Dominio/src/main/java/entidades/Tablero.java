package entidades;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * La clase Tablero representa el tren de fichas del juego, permitiendo la
 * gestión de las jugadas y la inserción de fichas en los extremos del tren. Se
 * encarga de orientar las fichas correctamente y girarlas si es necesario para
 * que puedan coincidir con los extremos de la fila de fichas.
 *
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class Tablero {
    private Deque<Ficha> trenFichas;

    /**
     * Constructor que inicializa el tren de fichas para la partida.
     */
    public Tablero() {
        trenFichas = new ArrayDeque<>();
    }

    public List<Ficha> obtenerFichas(){
        List<Ficha> fichas = new ArrayList<>(trenFichas);
        return fichas;
    }
    
    public boolean tableroVacio(){
        return trenFichas.isEmpty();
    }
    
    public String imprimirTren(){
        StringBuilder str = new StringBuilder();
        if(!trenFichas.isEmpty()){
            for (Ficha ficha : trenFichas) {
                str.append(ficha.getIzquierda());
                str.append("|");
                str.append(ficha.getDerecha());
                str.append(" ");
            }
        }
        return str.toString();
    }
    
    /**
     * Valida si una ficha puede ser colocada en el tablero, comparando sus
     * extremos con los extremos del tren de fichas actual. La ficha puede
     * coincidir con uno o ambos extremos, o no coincidir con ninguno.
     *
     * @param ficha Ficha a validar.
     * @return JugadaPosible, que indica en qué extremo(s) es posible insertar
     * la ficha: 
     * - AMBAS: La ficha puede ser insertada en ambos extremos.
     * - SOLOXDERECHA: La ficha solo puede ser insertada en el extremo derecho. 
     * - SOLOXIZQUIERDA: La ficha solo puede ser insertada en el extremo
     * izquierdo. 
     * - NINGUNA: La ficha no coincide con ninguno de los extremos y
     * no puede ser insertada.
     */
    public JugadaPosible validarFicha(Ficha ficha) {
        boolean derecha = (obtenerExtremoDer() == ficha.getIzquierda() || obtenerExtremoDer() == ficha.getDerecha());
        boolean izquierda = (obtenerExtremoIzq() == ficha.getDerecha() || obtenerExtremoIzq() == ficha.getIzquierda());

        if (derecha && izquierda) {
            return JugadaPosible.AMBAS;
        } else if (derecha) {
            return JugadaPosible.SOLOXDERECHA;
        } else if (izquierda) {
            return JugadaPosible.SOLOXIZQUIERDA;
        }
        return JugadaPosible.NINGUNA;
    }

    /**
     * Método que sirve para insertar la ficha automaticamente en alguno de los
     * extremos, pensado mas al uso de jugar solo y que la computadora juegue.
     *
     * @param ficha Ficha a insertar.
     */
    public void insertarFicha(Ficha ficha) {
        if (trenFichas.isEmpty()) {
            ficha.setOrientacion(Orientacion.VERTICAL);
            trenFichas.offer(ficha);
            return;
        }

        if (obtenerExtremoIzq() == ficha.getDerecha() || obtenerExtremoIzq() == ficha.getIzquierda()) {
            sentidoFichaIzq(ficha);
            orientarFicha(ficha, obtenerFichaIzq());
            trenFichas.offerFirst(ficha);
        } else {
            sentidoFichaDer(ficha);
            orientarFicha(ficha, obtenerFichaDer());
            trenFichas.offerLast(ficha);
        }
    }

    /**
     * Método que inserta la ficha del extremo izquierdo del juego.
     *
     * @param ficha Ficha a insertar
     */
    public void insertarFichaIzq(Ficha ficha) {
        sentidoFichaIzq(ficha);
        orientarFicha(ficha, obtenerFichaIzq());
        trenFichas.offerFirst(ficha);
    }

    /**
     * Método que inserta la ficha del extremo derecho del juego.
     *
     * @param ficha Ficha a insertar
     */
    public void insertarFichaDer(Ficha ficha) {
        sentidoFichaDer(ficha);
        orientarFicha(ficha, obtenerFichaDer());
        trenFichas.offerLast(ficha);
    }

    /**
     * Método que orienta la ficha ya sea horizontalmente o verticalmente
     * dependiendo la jugada de la partida, por ejemplo en caso de ser una ficha
     * mula se debe cambiar la orientación, de lo contrario se queda igual al
     * extremo.
     *
     * @param ficha Ficha a orientar
     * @param extremo Ficha del extremo con la cual se compara la orientación.
     */
    private void orientarFicha(Ficha ficha, Ficha extremo) {
        if (ficha.esMula()) {
            if (extremo.getOrientacion() == Orientacion.HORIZONTAL) {
                ficha.setOrientacion(Orientacion.VERTICAL);
            } else {
                ficha.setOrientacion(Orientacion.HORIZONTAL);
            }
        } else {
            ficha.setOrientacion(extremo.getOrientacion());
        }
    }

    /**
     * Método que gira la ficha en caso de no coincidir con el extremo izquierdo
     * de la partida.
     *
     * @param ficha Ficha a girar.
     */
    private void sentidoFichaIzq(Ficha ficha) {
        if (obtenerExtremoIzq() != ficha.getDerecha()) {
            ficha.girarFicha();
        }
    }

    /**
     * Gira la ficha si no coincide con el valor del extremo derecho del tren de
     * fichas.
     *
     * @param ficha Ficha que se evaluará y posiblemente se girará.
     */
    private void sentidoFichaDer(Ficha ficha) {
        if (obtenerExtremoDer() != ficha.getIzquierda()) {
            ficha.girarFicha();
        }
    }

    /**
     * Obtiene el valor del extremo izquierdo del tren de fichas.
     *
     * @return Valor del extremo izquierdo del tren.
     */
    public int obtenerExtremoIzq() {
        return trenFichas.peekFirst().getIzquierda();
    }

    /**
     * Obtiene el valor del extremo derecho del tren de fichas.
     *
     * @return Valor del extremo derecho del tren.
     */
    public int obtenerExtremoDer() {
        return trenFichas.peekLast().getDerecha();
    }

    /**
     * Obtiene la ficha ubicada en el extremo izquierdo del tren de fichas.
     *
     * @return Ficha ubicada en el extremo izquierdo.
     */
    public Ficha obtenerFichaIzq() {
        return trenFichas.peekFirst();
    }

    /**
     * Obtiene la ficha ubicada en el extremo derecho del tren de fichas.
     *
     * @return Ficha ubicada en el extremo derecho.
     */
    public Ficha obtenerFichaDer() {
        return trenFichas.peekLast();
    }
}
