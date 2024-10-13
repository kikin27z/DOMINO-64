package dtos.cuenta;

/**
 * Clase que representa una cuenta de jugador en el sistema.
 * Contiene información básica sobre el jugador, incluyendo su
 * identificador, avatar y nombre.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class CuentaDTO {
    private Integer id;       
    private String avatar;
    private String nombre;

    public CuentaDTO() {
    }

    /**
     * Constructor de la clase CuentaDTO que permite inicializar
     * todos los atributos de la cuenta.
     * 
     * @param id el identificador único de la cuenta.
     * @param avatar la ruta del avatar del jugador.
     * @param nombre el nombre del jugador.
     */
    public CuentaDTO(int id, String avatar, String nombre) {
        this.id = id;
        this.avatar = avatar;
        this.nombre = nombre;
    }
    
    /**
     * Obtiene la ruta del avatar del jugador.
     * 
     * @return la ruta del avatar.
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Establece la ruta del avatar del jugador.
     * 
     * @param avatar la nueva ruta del avatar.
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Obtiene el nombre del jugador.
     * 
     * @return el nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del jugador.
     * 
     * @param nombre el nuevo nombre del jugador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador único de la cuenta.
     * 
     * @return el identificador de la cuenta.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único de la cuenta.
     * 
     * @param id el nuevo identificador de la cuenta.
     */
    public void setId(int id) {
        this.id = id;
    }
}
