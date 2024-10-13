package logica;

import entidades.Jugador;
import entidades.Partida;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import presentacion_utilities.INavegacion;
import presentacion_utilities.Navegacion;
import utilities.Adapter;
import utilities.ViewAdapter;

/**
 *
 * @author luisa M
 */
public class Starter {
    
    static Scanner scan;
    private static ViewAdapter adapter;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        adapter = new Adapter();
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jugador = new Jugador("Luisa");
        jugadores.add(jugador);
        jugadores.add(new Jugador("Maria"));
        
        Partida partida = new Partida("123", jugadores, 4, 2);
        GameHandler handler = newGame(partida, jugador);
        Thread thread = new Thread(handler);
        thread.start();
        
        INavegacion nav = Navegacion.getInstance();
        nav.cambiarInicio();
        //handler.runOffline();
//        adapter.pintarJuego(partida, jugador);
//        adapter.iniciarJuego();
//        scan = new Scanner(System.in);
//        System.out.println("elige el tipo de partida:");
//        System.out.println("1)nueva partida");
//        System.out.println("2)unirme a partida");
//        int type = scan.nextInt();
//        if(type==1){
//            newGame();
//        }
    }
    
    
    private static GameHandler newGame(Partida partida, Jugador jugadorInicial){
        //Partida partida = new Partida(codigo, jugadores, cantidadFichas, cantidadJugadores);
        GameHandler handler = new GameHandler(partida, jugadorInicial);
        handler.init();
        return handler;
    }
    
}
