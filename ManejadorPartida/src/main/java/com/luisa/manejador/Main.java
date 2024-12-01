package com.luisa.manejador;

import implementacion.Client;
import java.util.Scanner;

/**
 *
 * @author luisa M
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client c = Client.iniciarComunicacion();
        ManejadorPartida manejador = new ManejadorPartida();

        for (Enum<?> suscripcion : ManejadorPartida.eventos) {
            c.addObserver(suscripcion, manejador);
        }

        manejador.vincularCliente(c);
    }

    private static String pedirIP() {
        Scanner input = new Scanner(System.in);
        System.out.print("Escribe la ip del servidor: ");
        String ip = input.nextLine();

        if (ip.isBlank()) {
            return "localhost";
        }
        return ip;

    }
}
