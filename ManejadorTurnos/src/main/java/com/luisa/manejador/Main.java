/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
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
        String ip = pedirIP();
        Client c = Client.getClient(ip, 5000);
        ManejadorTurnos manejador = new ManejadorTurnos();

        for (Enum<?> suscripcion : ManejadorTurnos.eventos) {
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
