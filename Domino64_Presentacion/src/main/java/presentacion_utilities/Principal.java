package presentacion_utilities;

import entidadesDTO.AvatarDTO;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author karim
 */
public class Principal {

    public static void main(String[] args) {
        System.out.println("Iniciado juego en presentación");

        INavegacion navegacion = Navegacion.getInstance();
        navegacion.iniciarAppPruebas();

        
        
    }
}
