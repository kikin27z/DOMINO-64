package entidades;

import java.util.List;

/**
 *
 * @author karim
 */
public enum Avatar {
    AVE("Piolin",1),
    GATO("Silvestre",2),
    JAGUAR("Lucchi",3),
    KIWI("Jenni Rivera",4),
    MARIPOSA("Victor Encinas",5),
    PANDA("Po",6),
    SERPIENTE("Orochimaru",7),
    TORTUGA("Donatello",8),
    VENADO("Chopper",9);

    private final String nombre;
    private final Integer id;

    Avatar(String nombre, Integer id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getId() {
        return id;
    }
    
    public static List<Avatar> listaAvatares() {
        return List.of(values());
    }
}
