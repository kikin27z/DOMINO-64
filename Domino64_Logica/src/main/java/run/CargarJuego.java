package run;

import entidades.Jugador;
import entidades.Partida;
import java.util.ArrayList;
import java.util.List;
import logica.GameHandler;
//import presentacion_utilities.
import presentacion_utilities.Navegacion;
import utilities.Adapter;
import utilities.ViewAdapter;

/**
 * Esta clase carga el juego de Domino 64
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class CargarJuego {

    private static ViewAdapter adapter;

    public static void main(String[] args) {
        adapter = new Adapter();
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jugador = new Jugador("Luisa");
        jugadores.add(jugador);
        jugadores.add(new Jugador("Maria"));
        Partida partida = new Partida("123", jugadores, 7, 2);

        GameHandler handler = new GameHandler(partida, jugador);
        handler.runOffline();
        adapter.pintarJuego(partida, jugador);
        adapter.iniciarJuego();
    }
}
