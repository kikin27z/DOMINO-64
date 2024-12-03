package entidadesDTO;

import java.util.List;

/**
 * Representa un contenedor para la lista de avatares disponibles en el juego.
 * Esta clase se utiliza para gestionar los avatares que los jugadores pueden seleccionar.
 * 
 * <p>Contiene métodos para obtener y establecer la lista de avatares.</p>
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class AvatarDisponiblesDTO {
    private List<AvatarDTO> avatares;

    /**
     * Constructor que inicializa la lista de avatares disponibles.
     * 
     * @param avatares Lista de avatares a establecer.
     */
    public AvatarDisponiblesDTO(List<AvatarDTO> avatares) {
        this.avatares = avatares;
    }

    /**
     * Constructor vacío.
     * Utilizado cuando la lista de avatares no está disponible al momento de la creación del objeto.
     */
    public AvatarDisponiblesDTO() {
    }

    /**
     * Obtiene la lista de avatares disponibles.
     * 
     * @return Lista de avatares.
     */
    public List<AvatarDTO> getAvatares() {
        return avatares;
    }

    /**
     * Establece la lista de avatares disponibles.
     * 
     * @param avatares Lista de avatares a establecer.
     */
    public void setAvatares(List<AvatarDTO> avatares) {
        this.avatares = avatares;
    }
}
