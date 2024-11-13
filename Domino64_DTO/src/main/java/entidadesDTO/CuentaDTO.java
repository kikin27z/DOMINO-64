package entidadesDTO;

/**
 *
 * @author luisa M
 */
public class CuentaDTO {
    private int id;
    private String username;
    private AvatarDTO avatar;
    private String idCadena;

    public CuentaDTO() {
    }

    public CuentaDTO(int id, String username, AvatarDTO avatar) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AvatarDTO getAvatar() {
        return avatar;
    }

    public void setAvatar(AvatarDTO avatar) {
        this.avatar = avatar;
    }

    public String getIdCadena() {
        return idCadena;
    }

    public void setIdCadena(String idCadena) {
        this.idCadena = idCadena;
    }
    
    
    
}
