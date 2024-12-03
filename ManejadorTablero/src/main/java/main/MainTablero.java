package main;

import manejadorTablero.ControlTablero;
import manejadorTablero.IControlTablero;


/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class MainTablero {

    public static void main(String[] args) {
        IControlTablero tablero = new ControlTablero();
        tablero.iniciaConexion();
    }
}
