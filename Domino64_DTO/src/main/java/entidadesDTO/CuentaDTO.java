package entidadesDTO;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author luisa M
 */
public class CuentaDTO implements Serializable{
    private String username;
    private AvatarDTO avatar;
    private String idCadena;
    private boolean admin;

    public CuentaDTO() {
    }

    public CuentaDTO(String username, AvatarDTO avatar) {
        this.username = username;
        this.avatar = avatar;
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
        this.username = avatar.getNombre();
    }

    public String getIdCadena() {
        return idCadena;
    }

    public void setIdCadena(String idCadena) {
        this.idCadena = idCadena;
    }

    public boolean esAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CuentaDTO{");
        sb.append("username=").append(username);
        sb.append(", avatar=").append(avatar);
        sb.append(", idCadena=").append(idCadena);
        sb.append(", admin=").append(admin);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.idCadena);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CuentaDTO other = (CuentaDTO) obj;
        return Objects.equals(this.idCadena, other.idCadena);
    }


   
}
