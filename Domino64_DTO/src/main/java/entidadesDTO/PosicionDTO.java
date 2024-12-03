package entidadesDTO;

import java.io.Serializable;

/**
 * Enumeración que define las posibles posiciones de una ficha en el tablero.
 * Esta enumeración es útil para describir cómo se colocan las fichas en un 
 * juego de dominó. Implementa {@code Serializable} para soportar 
 * la transferencia de datos en sistemas distribuidos.
 * 
 * Las posiciones disponibles son:
 * - IZQUIERDA: Representa una ficha colocada a la izquierda.
 * - DERECHA: Representa una ficha colocada a la derecha.
 * - ABAJO: Representa una ficha colocada hacia abajo.
 * - ARRIBA: Representa una ficha colocada hacia arriba.
 * - MULA_VERTICAL: Representa una ficha tipo "mula" colocada de manera vertical.
 * - MULA_HORIZONTAL: Representa una ficha tipo "mula" colocada de manera horizontal.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public enum PosicionDTO implements Serializable {

    IZQUIERDA,
    DERECHA,
    ABAJO,
    ARRIBA,
    MULA_VERTICAL,
    MULA_HORIZONTAL;
}
