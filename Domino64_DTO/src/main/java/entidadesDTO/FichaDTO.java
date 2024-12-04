package entidadesDTO;

import java.io.Serializable;

/**
 * La clase {@code FichaDTO} representa una ficha de dominó utilizada en el
 * juego. Cada ficha tiene dos valores numéricos (izquierda y derecha) que
 * definen sus caras.
 *
 * <p>
 * Esta clase implementa la interfaz {@link Serializable}, lo que permite que
 * las instancias sean serializables, facilitando su uso en sistemas
 * distribuidos.</p>
 *
 * <p>
 * Proporciona métodos para determinar características de la ficha, como si es
 * una mula (ambos lados iguales), realizar operaciones como girar la ficha,
 * calcular la suma de sus valores, y comparar si dos fichas son equivalentes
 * independientemente de su orientación.</p>
 *
 * <p>
 * Esta clase también sobrescribe el método {@link #toString()} para ofrecer una
 * representación textual de la ficha.</p>
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class FichaDTO implements Serializable {

    private int izquierda;
    private int derecha;

    /**
     * Constructor que inicializa los valores de las caras de la ficha.
     *
     * @param izquierda el valor de la cara izquierda
     * @param derecha el valor de la cara derecha
     */
    public FichaDTO(int izquierda, int derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    /**
     * Determina si la ficha es una mula. Una ficha es considerada mula si ambos
     * lados tienen el mismo valor.
     *
     * @return {@code true} si ambos lados son iguales, {@code false} en caso
     * contrario
     */
    public boolean esMula() {
        return izquierda == derecha;
    }

    /**
     * Invierte los valores de las caras izquierda y derecha de la ficha.
     */
    public void girarFicha() {
        int aux = izquierda;
        izquierda = derecha;
        derecha = aux;
    }

    /**
     * Calcula la suma de los valores de ambas caras de la ficha.
     *
     * @return la suma de los valores de la cara izquierda y derecha
     */
    public int sumaPuntos() {
        return izquierda + derecha;
    }

    /**
     * Obtiene el valor de la cara izquierda de la ficha.
     *
     * @return el valor de la cara izquierda
     */
    public int getIzquierda() {
        return izquierda;
    }

    /**
     * Establece el valor de la cara izquierda de la ficha.
     *
     * @param izquierda el nuevo valor de la cara izquierda
     */
    public void setIzquierda(int izquierda) {
        this.izquierda = izquierda;
    }

    /**
     * Obtiene el valor de la cara derecha de la ficha.
     *
     * @return el valor de la cara derecha
     */
    public int getDerecha() {
        return derecha;
    }

    /**
     * Establece el valor de la cara derecha de la ficha.
     *
     * @param derecha el nuevo valor de la cara derecha
     */
    public void setDerecha(int derecha) {
        this.derecha = derecha;
    }

    /**
     * Compara si otra ficha es equivalente a esta, independientemente de su
     * orientación (izquierda/derecha). Las fichas son consideradas iguales si
     * sus valores coinciden en ambas caras, sin importar el orden.
     *
     * @param ficha la ficha a comparar
     * @return {@code true} si ambas fichas son equivalentes, {@code false} en
     * caso contrario
     */
    public boolean esLaMismaFicha(FichaDTO ficha) {
        return (ficha.getDerecha() == this.derecha && ficha.getIzquierda() == this.izquierda)
                || (ficha.getDerecha() == this.izquierda && ficha.getIzquierda() == this.derecha);
    }

    /**
     * Proporciona una representación textual de la ficha en el formato
     * {@code FichaDTO[izquierda-derecha]}.
     *
     * @return una cadena representando la ficha
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FichaDTO[");
        sb.append(izquierda);
        sb.append("-").append(derecha);
        sb.append(']');
        return sb.toString();
    }
}
