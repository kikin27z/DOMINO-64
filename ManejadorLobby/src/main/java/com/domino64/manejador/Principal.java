package com.domino64.manejador;

import static com.domino64.manejador.ObservadorLobby.eventos;
import implementacion.Client;

/**
 *
 * @author karim
 */
public class Principal {

    public static void main(String[] args) {
        Client c = Client.iniciarComunicacion();
        ManejadorLobby manejador = new ManejadorLobby();

        for (Enum<?> suscripcion : eventos) {
            c.addObserver(suscripcion, manejador);
        }

        manejador.vincularCliente(c);

    }

}
