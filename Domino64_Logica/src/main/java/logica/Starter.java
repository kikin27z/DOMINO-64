package logica;

import entidades.Jugador;
import entidades.Partida;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author luisa M
 */
public class Starter {
    
    static Scanner scan;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        scan = new Scanner(System.in);
        System.out.println("elige el tipo de partida:");
        System.out.println("1)nueva partida");
        System.out.println("2)unirme a partida");
        int type = scan.nextInt();
        if(type==1){
            newGame();
        }
    }
    
    private static void newGame(){
        System.out.println("ingresa tu username:");
        String username = scan.next();
        
        Jugador jugador = new Jugador(username);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        
        System.out.println("ingresa el codigo de la partida nueva:");
        String codigo = scan.next();
        
        System.out.println("ingresa la cantidad de jugadores permitidos (minimo 2, maximo 4):");
        int cantidadJugadores = scan.nextInt();
        
        System.out.println("ingresa la cantidad de fichas a repartir por jugador (minimo 2, maximo 7):");
        int cantidadFichas = scan.nextInt();
        
        while(jugadores.size()<cantidadJugadores){
            System.out.println("La partida se configuro para "+ cantidadJugadores+" jugadores");
            System.out.println("¿Desea agregar otro jugador? Si/No");
            String resp = scan.next();
            if(resp.equalsIgnoreCase("si")){
                System.out.println("Ingresa el username del nuevo jugador:");
                String userNuevo = scan.next();
                Jugador jugadorNuevo = new Jugador(userNuevo);
                jugadores.add(jugadorNuevo);
            }
        }
        
        Partida partida = new Partida(codigo, jugadores, cantidadFichas, cantidadJugadores);
        
        Thread thread = new Thread(new GameManager(partida));
        thread.start();
    }
    
}
