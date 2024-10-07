package logica;

import entidades.Ficha;
import entidades.Jugador;
import entidades.Partida;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utilities.Adapter;
import utilities.INavegacion;
import utilities.Navegacion;
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
//        jugador.agregarFicha(new Ficha(0,5,"/dominos/0-5.png"));
//        jugador.agregarFicha(new Ficha(0,3,"/dominos/0-3.png"));
//        jugador.agregarFicha(new Ficha(0,2,"/dominos/0-2.png"));
        jugadores.add(jugador);
        jugadores.add(new Jugador("Fer"));
        jugadores.add(new Jugador("Maria"));
        Partida partida = new Partida("123", jugadores, 4, 3);
        GameHandler handler = newGame(partida, jugador);
        Thread thread = new Thread(handler);
        thread.start();
        adapter.pintarJuego(partida, jugador);
        adapter.iniciarJuego();
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
//        System.out.println("ingresa tu username:");
//        String username = scan.next();
//        
//        Jugador jugador = new Jugador(username);
//        List<Jugador> jugadores = new ArrayList<>();
//        jugadores.add(jugador);
//        
//        System.out.println("ingresa el codigo de la partida nueva:");
//        String codigo = scan.next();
//        
//        System.out.println("ingresa la cantidad de jugadores permitidos (minimo 2, maximo 4):");
//        int cantidadJugadores = scan.nextInt();
//        
//        System.out.println("ingresa la cantidad de fichas a repartir por jugador (minimo 2, maximo 7):");
//        int cantidadFichas = scan.nextInt();
//        
//        while(jugadores.size()<cantidadJugadores){
//            System.out.println("La partida se configuro para "+ cantidadJugadores+" jugadores");
//            System.out.println("¿Desea agregar otro jugador? Si/No");
//            String resp = scan.next();
//            if(resp.equalsIgnoreCase("si")){
//                System.out.println("Ingresa el username del nuevo jugador:");
//                String userNuevo = scan.next();
//                Jugador jugadorNuevo = new Jugador(userNuevo);
//                jugadores.add(jugadorNuevo);
//            }
//        }
        
        //Partida partida = new Partida(codigo, jugadores, cantidadFichas, cantidadJugadores);
        GameHandler handler = new GameHandler(partida, jugadorInicial);
        handler.init();
        return handler;
    }
    
}
