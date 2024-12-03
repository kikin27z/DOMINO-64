package main;

import manejadorPartida.ControlPartida;
import manejadorPartida.IControlPartida;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class MainPartida {
    public static void main(String[] args) {
        IControlPartida partida = new ControlPartida();
        partida.iniciaConexion();
    }
}
