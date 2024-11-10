package entidadesDTO;

/**
 *
 * @author luisa M
 */
public class CuentaDTO {
    private int id;
    private String username;
    private String avatarUrl;

    public CuentaDTO(int id, String username, String avatarUrl) {
        this.id = id;
        this.username = username;
        this.avatarUrl = avatarUrl;
    }

    public CuentaDTO() {
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CuentaDTO{");
        sb.append("username=").append(username);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append('}');
        return sb.toString();
    }
}
