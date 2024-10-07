package entidades;

/**
 *
 * @author karim
 */
public enum ModoPartida {
    VS_CPU(0),
    DOS_JUGADORES(2),
    TRES_JUGADORES(3),
    CUATRO_JUGADORES(4);

    private final int modo;

    /**
     * Constructor para asociar la URL con cada avatar
     * @param url Url del avatar.
     */
    ModoPartida(int url) {
        this.modo = url;
    }

    /**
     * Método para obtener la URL asociada al avatar.
     * 
     * @return URL del avatar.
     */
    public int getModo() {
        return modo;
    }
}
