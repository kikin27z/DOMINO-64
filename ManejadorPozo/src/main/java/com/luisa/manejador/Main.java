package com.luisa.manejador;

import implementacion.Client;

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
        ManejadorPozo manejador = new ManejadorPozo();

        for (Enum<?> suscripcion : ManejadorPozo.eventos) {
            c.addObserver(suscripcion, manejador);
        }

        manejador.vincularCliente(c);
    }


}
