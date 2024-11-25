package com.domino64.manejador;

import static com.domino64.manejador.ObservadorLobby.eventos;
import implementacion.Client;
import java.util.Scanner;

/**
 *
 * @author karim
 */
public class Principal {
    public static void main(String[] args) {
        String ip = pedirIP();
        Client c = Client.getClient(ip, 5000);
        ManejadorLobby manejador = new ManejadorLobby();

        for (Enum<?> suscripcion : eventos) {
            c.addObserver(suscripcion, manejador);
        }
        
        manejador.vincularCliente(c);
        
    }
    
     private static String pedirIP() {
        Scanner input = new Scanner(System.in);
        System.out.print("Escribe la ip del servidor: ");
        String ip = input.nextLine();
        
        if(ip.isBlank()){
            return "localhost";
        }
        return ip;

    }
}
