package presentacion_utilities;

import creditos.CreditosControl;
import creditos.CreditosModel;
import creditos.CreditosView;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.ResultadosDTO;
import finjuego.FinJuegoControl;
import finjuego.FinJuegoModel;
import finjuego.FinJuegoView;
import inicio.InicioControl;
import inicio.InicioModel;
import inicio.InicioView;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
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
import vinculacionModeloLogica.IMediadorModelo;

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
    private final IMediadorModelo mediador;
    private DistribuidorEventosModelo distribuidor;

    // Constructor privado para evitar instanciación externa
    private Navegacion() {
        mediador = MediadorModelos.getInstance();
        distribuidor = DistribuidorEventosModelo.getInstance();

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
        System.out.println("en el iniciar hilo presentacion");
        new Thread(() -> App.launch(App.class)).start();

    }

    @Override
    public void iniciarAppPruebas() {
        hiloApp = new Thread(() -> App.launch(App.class));
        hiloApp.start();
    }

    @Override
    public void cambiarInicio() {
        Platform.runLater(() -> {
            try {
                InicioModel modelo = new InicioModel();
                mediador.vincularModeloInicio(modelo);
                InicioView view = new InicioView(modelo); // Instancia la vista de inicio
                view.iniciarEscena(fondo); // Inicia la escena de inicio
                InicioControl control = new InicioControl(view, modelo);
            } catch (IOException ex) {
                ex.printStackTrace(); // Maneja la excepción imprimiendo el stack trace
            }
        });
    }

    @Override
    public void cambiarLobby(CuentaDTO cuenta, LobbyDTO lobby) {
        Platform.runLater(() -> {
            try {
                LobbyModel modelo = new LobbyModel(cuenta, lobby);
                mediador.vincularModeloLobby(modelo);
                distribuidor.setLobbyMVC(modelo);
                LobbyView view = new LobbyView(modelo); // Instancia la vista de la partida
                view.iniciarEscena(fondo); // Inicia la escena de la partida
                new LobbyControl(view, modelo);
            } catch (IOException ex) {
                ex.printStackTrace(); // Maneja la excepción imprimiendo el stack trace
            }
        });
    }

    public void setFondo(Stage fondo) {
        this.fondo = fondo; // Asigna la ventana principal
    }

    @Override
    public void cambiarPartida(CuentaDTO cuenta) {
        Platform.runLater(() -> {
            PartidaModel modeloPartida = new PartidaModel(cuenta);
            try {
                mediador.vincularModeloPartida(modeloPartida);
                PartidaView partida = new PartidaView(modeloPartida); // Instancia la vista de la partida
                partida.iniciarEscena(fondo); // Inicia la escena de la partida
                PartidaControl partidaControl = new PartidaControl(partida, modeloPartida);
            } catch (IOException ex) {
                ex.printStackTrace(); // Maneja la excepción imprimiendo el stack trace
            }
        });
    }

    @Override
    public void cambiarOpcionesPartida() {
        Platform.runLater(() -> {
            try {
                OpcionesPartidaModel modelo = new OpcionesPartidaModel();
                mediador.vincularModeloOpciones(modelo);
                OpcionesPartidaView view = new OpcionesPartidaView(modelo); // Instancia la vista de la partida
                view.iniciarEscena(fondo); // Inicia la escena de la partida
                OpcionesPartidaControl control = new OpcionesPartidaControl(view, modelo);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public Thread getHiloApp() {
        return hiloApp;
    }

    @Override
    public void cambiarCreditos() {
        Platform.runLater(() -> {
            CreditosModel modeloCreditos = new CreditosModel();
            try {
                mediador.vincularCreditos(modeloCreditos);
                CreditosView view = new CreditosView(modeloCreditos); // Instancia la vista de la partida
                view.iniciarEscena(fondo); // Inicia la escena de la partida
                CreditosControl control = new CreditosControl(view, modeloCreditos);
            } catch (IOException ex) {
                ex.printStackTrace(); // Maneja la excepción imprimiendo el stack trace
            }
        });
    }

    @Override
    public void cambiarFinJuego() {
        Platform.runLater(() -> {
            FinJuegoModel modeloFin = new FinJuegoModel();
            try {
                mediador.vincularModeloFin(modeloFin);
                FinJuegoView view = new FinJuegoView(modeloFin); // Instancia la vista de la partida
                view.iniciarEscena(fondo); // Inicia la escena de la partida
                FinJuegoControl control = new FinJuegoControl(view, modeloFin);
            } catch (IOException ex) {
                ex.printStackTrace(); // Maneja la excepción imprimiendo el stack trace
            }
        });
    }

}
