package lobby;

import entidades.Partida;

/**
 *
 * @author karim
 */
public class LobbyModel {
    private Partida partida;

    public LobbyModel() {
    }
   
    
    
    public void cargarDatos(){
        partida = new Partida("", null, 7, 2);
    }
    
    
}
