package entidades;

/**
 * Enum que representa las posibles jugadas que se pueden realizar en un juego
 * de dominó, con un valor asociado a cada jugada.
 * 
 * - NINGUNA: No se puede colocar la ficha en ningún extremo.
 * - SOLOXIZQUIERDA: La ficha solo puede ser colocada en el extremo izquierdo.
 * - SOLOXDERECHA: La ficha solo puede ser colocada en el extremo derecho.
 * - AMBAS: La ficha puede ser colocada en ambos extremos.
 * 
 * Cada jugada tiene un valor numérico asociado para facilitar su manejo.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public enum JugadaPosible {
    NINGUNA(0),
    SOLOXIZQUIERDA(1),
    SOLOXDERECHA(2),
    AMBAS(3);

    private final int valor;

    /**
     * Constructor que asigna un valor a cada jugada posible.
     * 
     * @param valor Valor asociado a la jugada posible.
     */
    JugadaPosible(int valor) {
        this.valor = valor;
    }

    /**
     * Obtiene el valor numérico asociado a la jugada posible.
     * 
     * @return Valor numérico de la jugada.
     */
    public int getValor() {
        return valor;
    }
}
