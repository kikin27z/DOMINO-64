package entidades;

/**
 * Enum que representa la orientación de una ficha en el juego de dominó.
 * Puede ser vertical o horizontal, y cada orientación tiene un valor numérico 
 * asociado para facilitar su manejo.
 * 
 * - VERTICAL: Representa una orientación vertical de la ficha.
 * - HORIZONTAL: Representa una orientación horizontal de la ficha.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public enum Orientacion {
    VERTICAL(0),
    HORIZONTAL(1);

    private final int valor;

    /**
     * Constructor que asigna un valor a cada orientación.
     * 
     * @param valor Valor asociado a la orientación (0 para vertical, 1 para horizontal).
     */
    Orientacion(int valor) {
        this.valor = valor;
    }

    /**
     * Obtiene el valor numérico asociado a la orientación.
     * 
     * @return Valor numérico de la orientación.
     */
    public int getValor() {
        return valor;
    }
}
