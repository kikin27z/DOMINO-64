package utilities;

import inicio.InicioView;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import lobby.LobbyView;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class Navegacion implements INavegacion{
    private Stage fondo;
    private static Navegacion navegacion;

    private Navegacion() {
    }

    public static Navegacion getInstance() {
        if (navegacion == null) {
            navegacion = new Navegacion();
        }
        return navegacion;
    }
    
    @Override
    public void iniciarApp() {
        Application.launch(App.class);
    }

     @Override
    public void cambiarInicio() {
        try {
            InicioView inicio = new InicioView();
            inicio.iniciarEscena(fondo);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void cambiarLobby() {
        try {
            LobbyView lobby = new LobbyView();
            lobby.iniciarEscena(fondo);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setFondo(Stage fondo) {
        this.fondo = fondo;
    }

   
}
