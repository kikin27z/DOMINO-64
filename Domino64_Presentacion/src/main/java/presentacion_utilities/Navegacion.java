package presentacion_utilities;

import inicio.InicioControl;
import inicio.InicioModel;
import inicio.InicioView;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import lobby.LobbyControl;
import lobby.LobbyModel;
import lobby.LobbyView;
import opciones_partida.OpcionesPartidaControl;
import opciones_partida.OpcionesPartidaModel;
import opciones_partida.OpcionesPartidaView;
import partida.PartidaControl;
import partida.PartidaModel;
import partida.PartidaView;

/**
 * Clase que gestiona la navegación entre diferentes vistas de la aplicación.
 * Esta clase implementa el patrón Singleton para asegurar que solo haya una
 * instancia de navegación a lo largo de la aplicación.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class Navegacion implements INavegacion {
    private Stage fondo; // La ventana principal de la aplicación
    private static Navegacion navegacion; // Instancia única de Navegacion
    private InicioModel partidaInicio;
    private Thread hiloApp;
    private NotificadorEvento notificador;

    // Constructor privado para evitar instanciación externa
    private Navegacion() {
        notificador = NotificadorEvento.getInstance();
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
        hiloApp = new Thread(() -> Application.launch(App.class));
        hiloApp.start();
    }
    
    /**
     * Cambia la vista a la vista de inicio.
     *
     */
    @Override
    public void cambiarInicio() {
        InicioModel modeloInicio = new InicioModel();
        try {
            InicioView view = new InicioView(modeloInicio); // Instancia la vista de inicio
            view.iniciarEscena(fondo); // Inicia la escena de inicio
            InicioControl control = new InicioControl(view, modeloInicio);
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
        LobbyModel modeloLobby = new LobbyModel();
        try {
            LobbyView view = new LobbyView(modeloLobby); // Instancia la vista de la partida
            view.iniciarEscena(fondo); // Inicia la escena de la partida
            LobbyControl control = new LobbyControl(view, modeloLobby);
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
     */
    @Override
    public void cambiarPartida() {
        PartidaModel modeloPartida = new PartidaModel();
        notificador.setPartida(modeloPartida);
        try {
            PartidaView partida = new PartidaView(modeloPartida); // Instancia la vista de la partida
            partida.iniciarEscena(fondo); // Inicia la escena de la partida
            PartidaControl partidaControl = new PartidaControl(partida, modeloPartida);
        } catch (IOException ex) {
            ex.printStackTrace(); // Maneja la excepción imprimiendo el stack trace
        }
    }

    @Override
    public void cambiarOpcionesPartida() {
        try {
            OpcionesPartidaModel modelo = new OpcionesPartidaModel();
            notificador.setOpciones(modelo);
            OpcionesPartidaView view = new OpcionesPartidaView(modelo); // Instancia la vista de la partida
            view.iniciarEscena(fondo); // Inicia la escena de la partida
            OpcionesPartidaControl control = new OpcionesPartidaControl(view, modelo);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Thread getHiloApp() {
        return hiloApp;
    }
}
