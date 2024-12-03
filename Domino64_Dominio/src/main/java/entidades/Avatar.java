package entidades;

import java.util.List;

/**
 * Enum que representa los avatares disponibles en el juego. Cada avatar tiene
 * un nombre y un identificador único.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public enum Avatar {

    // Definición de los avatares disponibles en el juego.
    AVE("Piolin", 1),
    GATO("Silvestre", 2),
    JAGUAR("Lucchi", 3),
    KIWI("Jenni Rivera", 4),
    MARIPOSA("Victor Encinas", 5),
    PANDA("Po", 6),
    SERPIENTE("Orochimaru", 7),
    TORTUGA("Donatello", 8),
    VENADO("Chopper", 9);

    private final String nombre; // Nombre del avatar
    private final Integer id;    // Identificador único del avatar

    /**
     * Constructor del enum Avatar.
     *
     * @param nombre El nombre del avatar
     * @param id El identificador único del avatar
     */
    Avatar(String nombre, Integer id) {
        this.nombre = nombre;
        this.id = id;
    }

    /**
     * Obtiene el nombre del avatar.
     *
     * @return El nombre del avatar
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el identificador del avatar.
     *
     * @return El identificador único del avatar
     */
    public Integer getId() {
        return id;
    }

    /**
     * Devuelve una lista con todos los avatares definidos en el enum.
     *
     * @return Una lista de avatares
     */
    public static List<Avatar> listaAvatares() {
        return List.of(values());
    }
}
