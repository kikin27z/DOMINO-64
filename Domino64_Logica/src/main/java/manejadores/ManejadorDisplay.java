package manejadores;

import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion_utilities.DistribuidorEventosModelo;
import presentacion_utilities.INavegacion;
import presentacion_utilities.Navegacion;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public final class ManejadorDisplay {
    private INavegacion navegacion;
    private DistribuidorEventosModelo distribuidor;
    
    public ManejadorDisplay() {
    }

    public void iniciarJuego(){
        navegacion = Navegacion.getInstance();
        navegacion.iniciarApp();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ManejadorDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //------------------------Eventos de cambiar pantallas--------------------
    public void mostrarInicio(){
        navegacion.cambiarOpcionesPartida();
    }
    public void mostrarOpcionesPartida(){
        navegacion.cambiarOpcionesPartida();
    }
    
    public void mostrarLobby(CuentaDTO cuenta, LobbyDTO lobby){
        navegacion.cambiarLobby(cuenta, lobby);
    }
}
