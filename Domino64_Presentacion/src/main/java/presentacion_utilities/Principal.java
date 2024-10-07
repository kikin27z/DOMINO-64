package presentacion_utilities;

import partida.PartidaControl;
import partida.PartidaModel;
import partida.PartidaView;

/**
 *
 * @author karim
 */
public class Principal {
    public static void main(String[] args) {
        
        PartidaModel modelo = new PartidaModel();
        INavegacion navegacion = Navegacion.getInstance();
        navegacion.iniciarApp();
        
    }
}
