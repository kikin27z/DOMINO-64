package entidades;

/**
 *
 * @author karim
 */
public enum EstadoPartida {
    EN_JUEGO(0),
    GANO_ALGUIEN(2),
    SE_RINDIERON(3);

    private final int modo;

    /**
     * Constructor para asociar la URL con cada avatar
     * @param url Url del avatar.
     */
    EstadoPartida(int url) {
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
