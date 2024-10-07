package presentacion_utilities;

import inicio.InicioView;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import lobby.LobbyControl;
import lobby.LobbyModel;
import lobby.LobbyView;
import partida.PartidaControl;
import partida.PartidaModel;
import partida.PartidaView;

/**
 * Clase que gestiona la navegación entre diferentes vistas de la aplicación.
 * Esta clase implementa el patrón Singleton para asegurar que solo haya
 * una instancia de navegación a lo largo de la aplicación.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class Navegacion implements INavegacion {
    private Stage fondo; // La ventana principal de la aplicación
    private static Navegacion navegacion; // Instancia única de Navegacion

    // Constructor privado para evitar instanciación externa
    private Navegacion() {
    }

    /**
     * Obtiene la instancia única de Navegacion.
     * 
     * @return La instancia de Navegacion.
     */
    public static Navegacion getInstance() {
        if (navegacion == null) {
            navegacion = new Navegacion(); // Crea la instancia si no existe
        }
        return navegacion;
    }

    /**
     * Inicia la aplicación lanzando la clase principal de JavaFX.
     */
    @Override
    public void iniciarApp() {
        Application.launch(App.class); // Inicia la aplicación
    }

    /**
     * Cambia la vista a la vista de inicio.
     * 
     * @throws IOException Si ocurre un error al cargar la vista de inicio.
     */
    @Override
    public void cambiarInicio() {
        try {
            InicioView inicio = new InicioView(); // Instancia la vista de inicio
            inicio.iniciarEscena(fondo); // Inicia la escena de inicio
        } catch (IOException ex) {
            ex.printStackTrace(); // Maneja la excepción imprimiendo el stack trace
        }
    }

    /**
     * Cambia la vista a la vista del lobby.
     * 
     * @throws IOException Si ocurre un error al cargar la vista del lobby.
     */
    @Override
    public void cambiarLobby() {
        try {
            LobbyModel modelo = new LobbyModel();
            LobbyView view = new LobbyView(modelo); // Instancia la vista de la partida
            view.iniciarEscena(fondo); // Inicia la escena de la partida
            LobbyControl control = new LobbyControl(view, modelo);
        } catch (IOException ex) {
            ex.printStackTrace(); // Maneja la excepción imprimiendo el stack trace
        }
    }

    /**
     * Establece la ventana principal de la aplicación.
     * 
     * @param fondo La ventana principal (Stage) donde se mostrará la escena.
     */
    public void setFondo(Stage fondo) {
        this.fondo = fondo; // Asigna la ventana principal
    }

    /**
     * Cambia la vista a la vista de la partida.
     * 
     * @throws IOException Si ocurre un error al cargar la vista de la partida.
     */
    @Override
    public void cambiarPartida() {
        try {
            PartidaModel modelo = new PartidaModel();
            PartidaView partida = new PartidaView(modelo); // Instancia la vista de la partida
            partida.iniciarEscena(fondo); // Inicia la escena de la partida
            PartidaControl control = new PartidaControl(partida, modelo);
        } catch (IOException ex) {
            ex.printStackTrace(); // Maneja la excepción imprimiendo el stack trace
        }
    }

    @Override
    public void actualizarPartida(PartidaModel modelo) {
//        this.partidaModelo=modelo;
    }
}
