package presentacion_utilities;

import entidadesDTO.AvatarDTO;

/**
 *
 * @author karim
 */
public class Principal {
    public static void main(String[] args) {
        INavegacion navegacion = Navegacion.getInstance();
        navegacion.iniciarApp();
                
    }
}
