package entidadesDTO;

import java.io.Serializable;

/**
 * Enumeración que representa las posibles jugadas que un jugador puede realizar
 * dependiendo de la disposición de las fichas en el tablero.
 * 
 * <ul>
 * <li>{@code NINGUNA}: No hay jugadas disponibles.</li>
 * <li>{@code SOLO_IZQUIERDA}: Sólo se puede jugar en el extremo izquierdo.</li>
 * <li>{@code SOLO_DERECHA}: Sólo se puede jugar en el extremo derecho.</li>
 * <li>{@code AMBOS_EXTREMOS}: Se puede jugar en ambos extremos del tablero.</li>
 * </ul>
 * 
 * Esta clase es serializable, permitiendo que las instancias puedan ser enviadas
 * entre diferentes componentes o almacenadas en sistemas que requieran
 * serialización.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public enum PosibleJugadaDTO  implements Serializable{
    NINGUNA,
    SOLO_IZQUIERDA,
    SOLO_DERECHA,
    AMBOS_EXTREMOS
}
