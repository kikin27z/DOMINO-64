package main;

import manejadorLobby.ControlLobby;
import manejadorLobby.IControlLobby;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class MainLobby {

    public static void main(String[] args) {
        IControlLobby manejador = new ControlLobby();
        manejador.iniciaConexion();
    }

}
