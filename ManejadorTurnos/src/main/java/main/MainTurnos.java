package main;

import manejadorTurnos.ControlTurnos;
import manejadorTurnos.IControlTurnos;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class MainTurnos {
    public static void main(String[] args) {
        IControlTurnos turnos = new ControlTurnos();
        turnos.iniciaConexion();
    }
}
