package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class Jugador {
    private String username;
    private List<Ficha> fichas;
    
    public Jugador(String username){
        this.fichas = new ArrayList<>();
        this.username = username;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public void removerFicha(Ficha ficha){
        fichas.remove(ficha);
    }
    
    public void agregarFicha(Ficha ficha){
        fichas.add(ficha);
    }

    public List<Ficha> getFichas(){
        return fichas;
    }
    
    /**
     * encuentra la mula mas grande en la lista de fichas proporcionada
     *
     * @param tiles Lista de fichas de donde se buscara la mula
     * @return la mula mas grande. Null si no hay mulas
     */
    public Ficha getHigherDouble() {
        Ficha higherDouble = null;
        int higherValue = 0;
        for (Ficha f : fichas) {
            if (f.esMula()) {
                int actualValue = f.getIzquierda()+ f.getDerecha();
                if (actualValue > higherValue) {
                    higherValue = actualValue;
                    higherDouble = f;
                    //System.out.println("higher double: " + higherDouble);
                }
            }
        }
        return higherDouble;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.username);
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
        final Jugador other = (Jugador) obj;
        return Objects.equals(this.username, other.username);
    }

    @Override
    public String toString() {
        return "Jugador{" + "username=" + username + '}';
    }
    
    
    
}
