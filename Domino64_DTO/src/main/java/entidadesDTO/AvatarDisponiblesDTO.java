package entidadesDTO;

import java.util.List;

/**
 *
 * @author karim
 */
public class AvatarDisponiblesDTO {
    private List<AvatarDTO> avatares;

    public AvatarDisponiblesDTO(List<AvatarDTO> avatares) {
        this.avatares = avatares;
    }

    public AvatarDisponiblesDTO() {
    }

    public List<AvatarDTO> getAvatares() {
        return avatares;
    }

    public void setAvatares(List<AvatarDTO> avatares) {
        this.avatares = avatares;
    }
}
