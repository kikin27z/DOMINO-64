package entidadesDTO;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa una cuenta de usuario en el sistema de juego.
 * Contiene información sobre el nombre de usuario, el avatar asociado,
 * el ID único de la cuenta y si la cuenta tiene privilegios de administrador.
 * Implementa la interfaz Serializable para permitir la serialización del objeto.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class CuentaDTO implements Serializable {
    private String username;
    private AvatarDTO avatar;
    private String idCadena;
    private boolean admin;

    /**
     * Constructor vacío para la clase CuentaDTO.
     */
    public CuentaDTO() {
    }

    /**
     * Constructor que inicializa el nombre de usuario y el avatar de la cuenta.
     * 
     * @param username El nombre de usuario de la cuenta
     * @param avatar El avatar asociado a la cuenta
     */
    public CuentaDTO(String username, AvatarDTO avatar) {
        this.username = username;
        this.avatar = avatar;
    }

    /**
     * Obtiene el nombre de usuario de la cuenta.
     * 
     * @return El nombre de usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario de la cuenta.
     * 
     * @param username El nombre de usuario a establecer
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene el avatar asociado a la cuenta.
     * 
     * @return El avatar de la cuenta
     */
    public AvatarDTO getAvatar() {
        return avatar;
    }

    /**
     * Establece el avatar de la cuenta y actualiza el nombre de usuario
     * en base al nombre del avatar.
     * 
     * @param avatar El avatar a asociar con la cuenta
     */
    public void setAvatar(AvatarDTO avatar) {
        this.avatar = avatar;
        this.username = avatar.getNombre();  // El nombre del avatar se usa como nombre de usuario
    }

    /**
     * Obtiene el ID único de la cuenta.
     * 
     * @return El ID único de la cuenta
     */
    public String getIdCadena() {
        return idCadena;
    }

    /**
     * Establece el ID único de la cuenta.
     * 
     * @param idCadena El ID único a establecer
     */
    public void setIdCadena(String idCadena) {
        this.idCadena = idCadena;
    }

    /**
     * Verifica si la cuenta tiene privilegios de administrador.
     * 
     * @return true si la cuenta es de administrador, false si no lo es
     */
    public boolean esAdmin() {
        return admin;
    }

    /**
     * Establece si la cuenta tiene privilegios de administrador.
     * 
     * @param admin true si la cuenta debe ser administradora, false en caso contrario
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Retorna una representación en cadena del objeto CuentaDTO.
     * 
     * @return Una cadena con los detalles de la cuenta
     */
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

    /**
     * Calcula el código hash de la cuenta basado en el ID único.
     * 
     * @return El valor hash del objeto CuentaDTO
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.idCadena);
        return hash;
    }

    /**
     * Compara si dos objetos CuentaDTO son iguales basándose en el ID único.
     * 
     * @param obj El objeto a comparar
     * @return true si los objetos son iguales, false en caso contrario
     */
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
