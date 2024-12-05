package entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class Partida {

    private List<Jugador> jugadores;
    private int jugadoresRendidos;
    private List<String> idJugadoresRendidos;

    public Partida(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.jugadoresRendidos = 0;
        this.idJugadoresRendidos = new ArrayList<>();
    }

    public Partida() {
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Ficha> abandonoJugador(Jugador jugador) {
        List<Ficha> fichas = null;
        for (Jugador j : jugadores) {
            if (j.obtenerIdJugador().equals(jugador.obtenerIdJugador())) {
                quitarListaRendidos(j.obtenerIdJugador());
                fichas = j.getFichas();
                jugadores.remove(j);
                break;
            }
        }
        return fichas;
    }

    private void quitarListaRendidos(String id){
        if(idJugadoresRendidos.contains(id)){
            idJugadoresRendidos.remove(id);
            jugadoresRendidos--;
        }
        
    }
    
    public void seRindioJugador(Jugador jugador) {
        // Incrementar el contador de jugadores rendidos
        idJugadoresRendidos.add(jugador.obtenerIdJugador());
        this.jugadoresRendidos++;
    }
    
    public int obtenerNumJugadoresRendidos(){
        return jugadoresRendidos;
    } 
}
