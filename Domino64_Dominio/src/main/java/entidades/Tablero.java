package entidades;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Clase Tablero que representa el tablero de un juego de dominó. El tablero
 * utiliza una estructura de datos Deque para gestionar las fichas. Las fichas
 * se agregan a los extremos del tablero, y se validan para asegurarse de que
 * encajan correctamente.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class Tablero {

    private Deque<Ficha> trenFichas; // Cola doble para representar las fichas en el tablero

    /**
     * Constructor que inicializa el tablero con un tren vacío de fichas.
     */
    public Tablero() {
        trenFichas = new ArrayDeque<>(); // Inicializar la estructura de datos de fichas
    }

    /**
     * Obtiene el valor del extremo izquierdo del tablero.
     *
     * @return El valor del extremo izquierdo.
     */
    public int obtenerExtremoIzq() {
        Ficha ficha = trenFichas.peekFirst(); // Obtener la ficha en el extremo izquierdo
        return ficha.getIzquierda(); // Retornar el valor de la parte izquierda de la ficha
    }

    /**
     * Obtiene el valor del extremo derecho del tablero.
     *
     * @return El valor del extremo derecho.
     */
    public int obtenerExtremoDer() {
        Ficha ficha = trenFichas.peekLast(); // Obtener la ficha en el extremo derecho
        int valor = ficha.getDerecha(); // Obtener el valor del extremo derecho de la ficha
        return valor;
    }

    /**
     * Verifica si el tablero está vacío.
     *
     * @return true si el tablero está vacío, false en caso contrario.
     */
    private boolean estaVacio() {
        return trenFichas.isEmpty(); // Retorna verdadero si no hay fichas en el tablero
    }

    /**
     * Agrega una ficha al tablero, ya sea al extremo izquierdo o derecho según
     * el parámetro.
     *
     * @param ficha La ficha a agregar al tablero.
     * @param extremoIzq true para agregar al extremo izquierdo, false para
     * agregar al derecho.
     */
    public void agregarFicha(Ficha ficha, boolean extremoIzq) {
        // Si el tablero está vacío, se agrega la ficha directamente
        if (estaVacio()) {
            trenFichas.offer(ficha); // Agregar la ficha al tablero
            imprimirJugada(ficha); // Imprimir la jugada realizada
            return;
        }

        // Si no está vacío, se valida y agrega la ficha al extremo correspondiente
        if (extremoIzq) {
            agregarFichaIzquierda(ficha); // Si es al extremo izquierdo
        } else {
            agregarFichaDerecha(ficha); // Si es al extremo derecho
        }
    }

    /**
     * Valida que la ficha pueda agregarse al extremo izquierdo del tablero. Si
     * la ficha no encaja, se gira.
     *
     * @param ficha La ficha a validar y agregar.
     */
    private void validarExtremoIzquierdo(Ficha ficha) {
        Ficha validar = trenFichas.peekFirst(); // Obtener la ficha en el extremo izquierdo
        // Si la parte izquierda de la ficha no coincide con el extremo izquierdo, se gira
        if (validar.getIzquierda() != ficha.getDerecha()) {
            ficha.girarFicha(); // Girar la ficha para que encaje
        }
    }

    /**
     * Valida que la ficha pueda agregarse al extremo derecho del tablero. Si la
     * ficha no encaja, se gira.
     *
     * @param ficha La ficha a validar y agregar.
     */
    private void validarExtremoDerecho(Ficha ficha) {
        Ficha validar = trenFichas.peekLast();
        if (validar.getDerecha() != ficha.getIzquierda()) {
            ficha.girarFicha();
        }
    }

    /**
     * Agrega la ficha al extremo derecho del tablero. Antes de agregarla,
     * valida que encaje con el extremo derecho.
     *
     * @param ficha La ficha a agregar al extremo derecho.
     */
    private void agregarFichaDerecha(Ficha ficha) {
        validarExtremoDerecho(ficha);
        trenFichas.offerLast(ficha);
    }

    /**
     * Agrega la ficha al extremo izquierdo del tablero. Antes de agregarla,
     * valida que encaje con el extremo izquierdo.
     *
     * @param ficha La ficha a agregar al extremo izquierdo.
     */
    private void agregarFichaIzquierda(Ficha ficha) {
        validarExtremoIzquierdo(ficha);
        trenFichas.offerFirst(ficha);
    }

    /**
     * Imprime la jugada realizada, mostrando el estado del tablero después de
     * agregar una ficha.
     *
     * @param ficha La ficha que se acaba de agregar.
     */
    private void imprimirJugada(Ficha ficha) {
        System.out.println("Tablero se insertó " + ficha);
        System.out.println("Jugada nueva: " + obtenerExtremoIzq() + "-" + obtenerExtremoDer());
    }
}
