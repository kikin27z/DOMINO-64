package entidades;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author luisa M
 */
public final class Cuenta implements Serializable{
    private String username;
    private Avatar avatar;
    private String idCadena;
    private boolean esAdmin;

    public Cuenta() {
        idCadena = crearId();
    }

    
    public String getNombre() {
        return username;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
        username = avatar.getNombre();
    }

    public Avatar getAvatar() {
        return avatar;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "nombre=" + username + ", avatar=" + avatar + ", idCadena=" + idCadena + '}';
    }

    public  String crearId() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(letters.length());
            result.append(letters.charAt(index));
        }

        return result.toString();
    }

    public String getIdCadena() {
        return idCadena;
    }

    public void setIdCadena(String idCadena) {
        this.idCadena = idCadena;
    }

    public boolean esAdmin() {
        return esAdmin;
    }

    public void setAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }
    
}
