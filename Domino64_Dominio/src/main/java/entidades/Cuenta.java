package entidades;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author luisa M
 */
public class Cuenta implements Serializable{
    private String nombre;
    private Avatar avatar;
    private int id;
    private String idCadena;
    private Jugador jugador;

    public Cuenta(int id) {
        this.id = id;
    }

    public Cuenta() {
        idCadena = crearId();
    }

    public int getId(){
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
        nombre = avatar.getNombre();
    }

    public Avatar getAvatar() {
        return avatar;
    }

    

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.nombre);
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
        final Cuenta other = (Cuenta) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Cuenta{" + "nombre=" + nombre + ", avatar=" + avatar + ", idCadena=" + idCadena + '}';
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

    public void setId(int id) {
        this.id = id;
    }
    
    
}
