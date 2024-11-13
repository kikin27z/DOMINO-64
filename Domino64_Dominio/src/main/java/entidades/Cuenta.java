package entidades;

import java.util.Objects;
import java.util.Random;

/**
 *
 * @author luisa M
 */
public class Cuenta {
    private String username;
    private String avatarUrl;
    private int id;
    private String idCadena;
    private Jugador jugador;

    public Cuenta(int id) {
        this.id = id;
    }

    public Cuenta() {
        idCadena = generateLetterString();
    }
    
    

    public int getId(){
        return id;
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

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.username);
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
        return Objects.equals(this.username, other.username);
    }
    
    public  String generateLetterString() {
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
    
    
}
