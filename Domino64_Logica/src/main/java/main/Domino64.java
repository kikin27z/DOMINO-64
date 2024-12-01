package main;

import entidadesDTO.LobbyDTO;
import eventoss.EventoMVCJugador;
import java.util.Scanner;
import manejadores.Control;
import manejadores.MediadorManejadores;

/**
 *
 * @author karim
 */
public class Domino64 {
    public static void main(String[] args) {
        System.out.println("Iniciado juego en lógica");
        MediadorManejadores.getInstance();
        Control.subscribirManejadores();
        
        Scanner scan = new Scanner(System.in);

        System.out.println("------------------------");
        System.out.println("¨Presiona enter para iniciar el juego");
        scan.nextLine();
        System.out.println("------------------------");
        int opcion;
        do {
            System.out.println("Presiona '1' para crear una partida");
            System.out.println("Presiona '2' para unirse a una partida");
            opcion = scan.nextInt();
            scan.nextLine();
        } while (!(opcion == 1 || opcion == 2));
        System.out.println("------------------------");

        if (opcion == 1) {
            MediadorManejadores.getManejadorCuenta().crearPartida(new EventoMVCJugador());
        } else {
            System.out.println("Ingresa el codigo de la partida que buscas:");
            String codigo = scan.nextLine();
            LobbyDTO lobby = new LobbyDTO(codigo);
            EventoMVCJugador evento = new EventoMVCJugador();
            evento.setLobby(lobby);
            MediadorManejadores.getManejadorCuenta().buscarPartida(evento);
        }
        

//        Control.iniciarJuego();
    }
}
