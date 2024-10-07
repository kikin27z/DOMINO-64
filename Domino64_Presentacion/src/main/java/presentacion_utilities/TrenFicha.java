package presentacion_utilities;

import javafx.scene.canvas.Canvas;
import java.util.ArrayDeque;
import java.util.Deque;

// Definir un enum para el atributo extra
enum Orientacion {
    VERTICAL,
    HORIZONTAL
}

// Clase Elemento que contendrá un Canvas y el atributo extra de tipo enum
class FichaJugada {
    private Canvas ficha;
    private Orientacion orientacion;  // Atributo de tipo enum

    public FichaJugada(Canvas ficha, Orientacion orientacion) {
        this.ficha = ficha;
        this.orientacion = orientacion;
    }

    public Canvas getFicha() {
        return ficha;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    public void setAtributoExtra(Orientacion orientacion) {
        this.orientacion = orientacion;
    }

    @Override
    public String toString() {
        return "Canvas: " + ficha + ", Atributo Extra (Estado): " + orientacion;
    }
}

// Clase personalizada que envuelve un Deque con elementos que tienen un Canvas y un atributo extra
class TrenFicha {
    private Deque<FichaJugada> deque;

    public TrenFicha() {
        this.deque = new ArrayDeque<>();
    }

    // Método para añadir al inicio (equivalente a addFirst en Deque)
    public void addFirst(Canvas ficha, Orientacion orientacion) {
        deque.addFirst(new FichaJugada(ficha, orientacion));
    }

    // Método para añadir al final (equivalente a addLast en Deque)
    public void addLast(Canvas ficha, Orientacion orientacion) {
        deque.addLast(new FichaJugada(ficha, orientacion));
    }

    public FichaJugada pollFirst() {
        return deque.pollFirst();
    }

    public FichaJugada pollLast() {
        return deque.pollLast();
    }

    public FichaJugada peekFirst() {
        return deque.peekFirst();
    }

    public FichaJugada peekLast() {
        return deque.peekLast();
    }

    public int size() {
        return deque.size();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }
}
