package presentacion_utilities;

import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
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
 * @author José Karim Franco Valencia - 00000245138
 */
public class Navegacion implements INavegacion {
    private Stage fondo;
    private static Navegacion navegacion;
    private Thread hiloApp;
    private final INotificadorEvento notificador;
    private final MediadorModelos mediador;

    // Constructor privado para evitar instanciación externa
    private Navegacion() {
        notificador = NotificadorEvento.getInstance();
        mediador = MediadorModelos.getInstance();
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

    @Override
    public void cambiarLobby(LobbyDTO lobby) {
        LobbyModel modeloLobby = new LobbyModel();
        modeloLobby.setLobbyDTO(lobby);
        try {
            notificador.asignarObservableLobby(modeloLobby);
            mediador.setModeloLobby(modeloLobby);
            LobbyView view = new LobbyView(modeloLobby); // Instancia la vista de la partida
            view.iniciarEscena(fondo); // Inicia la escena de la partida
            LobbyControl control = new LobbyControl(view, modeloLobby);
        } catch (IOException ex) {
            ex.printStackTrace(); // Maneja la excepción imprimiendo el stack trace
        }
    }

    public void setFondo(Stage fondo) {
        this.fondo = fondo; // Asigna la ventana principal
    }

    @Override
    public void cambiarPartida() {
        PartidaModel modeloPartida = new PartidaModel();
        notificador.asignarObservablePartida(modeloPartida);
        mediador.setModeloPartida(modeloPartida);
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
            notificador.asignarObservableOpcionesPartida(modelo);
            mediador.setModeloOpciones(modelo);
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
