package entidades;

/**
 * Enum que representa distintos tipos de avatares con su respectiva URL.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public enum Avatar {
    AVE("/avatar/ave.png"),
    GATO("/avatar/gato.png"),
    JAGUAR("/avatar/jaguar.png"),
    KIWI("/avatar/kiwi.png"),
    MARIPOSA("/avatar/mariposa.png"),
    PANDA("/avatar/panda.png"),
    SERPIENTE("/avatar/serpiente.png"),
    TORTUGA("/avatar/tortuga.png"),
    VENADO("/avatar/venado.png");

    private final String url;

    /**
     * Constructor para asociar la URL con cada avatar
     * @param url Url del avatar.
     */
    Avatar(String url) {
        this.url = url;
    }

    /**
     * Método para obtener la URL asociada al avatar.
     * 
     * @return URL del avatar.
     */
    public String getUrl() {
        return url;
    }
}
