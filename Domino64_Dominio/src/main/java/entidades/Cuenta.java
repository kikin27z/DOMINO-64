package entidades;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

/**
 * Representa una cuenta de usuario con un nombre, un avatar, un identificador único
 * y una propiedad para determinar si es un administrador.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public final class Cuenta implements Serializable {
    private String username;  
    private Avatar avatar;
    private String idCadena;
    private boolean esAdmin;

    /**
     * Constructor sin parámetros. Crea una cuenta con un identificador único.
     */
    public Cuenta() {
        idCadena = crearId(); 
    }

    /**
     * Obtiene el nombre de usuario.
     * 
     * @return El nombre de usuario.
     */
    public String getNombre() {
        return username;
    }

    /**
     * Establece el avatar asociado a la cuenta y actualiza el nombre de usuario con el nombre del avatar.
     * 
     * @param avatar El avatar que se asigna a la cuenta.
     */
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
        username = avatar.getNombre(); // El nombre del avatar se convierte en el nombre del usuario
    }

    /**
     * Obtiene el avatar de la cuenta.
     * 
     * @return El avatar asociado a la cuenta.
     */
    public Avatar getAvatar() {
        return avatar;
    }

    /**
     * Devuelve una representación en cadena de la cuenta.
     * 
     * @return La cadena que representa la cuenta.
     */
    @Override
    public String toString() {
        return "Cuenta{" + "nombre=" + username + ", avatar=" + avatar + ", idCadena=" + idCadena + '}';
    }

    /**
     * Genera un identificador único de 5 caracteres al azar.
     * Los caracteres posibles son letras mayúsculas de la A a la Z.
     * 
     * @return Un identificador único de 5 letras aleatorias.
     */
    public String crearId() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(letters.length());
            result.append(letters.charAt(index));  // Elige una letra al azar
        }

        return result.toString();
    }

    /**
     * Obtiene el identificador único de la cuenta.
     * 
     * @return El identificador único de la cuenta.
     */
    public String getIdCadena() {
        return idCadena;
    }

    /**
     * Establece el identificador único de la cuenta.
     * 
     * @param idCadena El nuevo identificador único.
     */
    public void setIdCadena(String idCadena) {
        this.idCadena = idCadena;
    }

    /**
     * Verifica si la cuenta corresponde a un administrador.
     * 
     * @return true si la cuenta es de administrador, false si no lo es.
     */
    public boolean esAdmin() {
        return esAdmin;
    }

    /**
     * Establece si la cuenta corresponde a un administrador.
     * 
     * @param esAdmin true si la cuenta es de administrador, false si no lo es.
     */
    public void setAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    /**
     * Genera un código hash para la cuenta. Se usa el identificador único para calcular el hash.
     * 
     * @return El código hash basado en el identificador único.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idCadena);
        return hash;
    }

    /**
     * Compara dos cuentas para determinar si son iguales.
     * Dos cuentas se consideran iguales si tienen el mismo identificador único.
     * 
     * @param obj El objeto a comparar con la cuenta actual.
     * @return true si las cuentas son iguales (tienen el mismo idCadena), false en caso contrario.
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
        final Cuenta other = (Cuenta) obj;
        return Objects.equals(this.idCadena, other.idCadena);
    }
}
