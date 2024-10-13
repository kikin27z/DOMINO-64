package presentacion_utilities;

import com.mycompany.patrones.command.Accion;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import inicio.InicioControl;
import inicio.InicioModel;
import inicio.InicioView;
import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.scene.image.Image;
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
    private PartidaModel modeloPartida;
    private InicioModel modeloInicio;
    private LobbyModel modeloLobby;

    // Constructor privado para evitar instanciación externa
    private Navegacion() {
        modeloInicio = new InicioModel();
        modeloLobby = new LobbyModel(NotificadorPresentacion.getInstance());
        modeloPartida = new PartidaModel(NotificadorPresentacion.getInstance());
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
     * Actualiza los modelos, estableciendoles las acciones a ejecutar en cada boton
     * que tenga la vista asociada a dicho modelo
     * @param acciones Lista con todas las acciones a establecer en cada modelo,
     * cada elemnto es una lista con las acciones correspondientes a un modelo en especifico.
     * La primera lista contiene las acciones a asignarle al modeloInicio,
     * la segunda lista contiene las acciones a asignarle al modeloLobby,
     * y la tercera lista contiene las acciones a asignarle al modeloPartida
     */
    public void setAcciones(List<List<Accion>> acciones){
        List<Accion> accionesInicio = acciones.get(0);//acciones para el modeloInicio
        List<Accion> accionesLobby = acciones.get(1);//acciones para el modeloLobby
        //List<Accion> accionesPartida = acciones.get(2);
        
        //actualizando los modelos, estableciendoles las acciones especificas
        modeloInicio.setAcciones(accionesInicio);
        modeloLobby.setAcciones(accionesLobby);
    }

    /**
     * Inicia la aplicación lanzando la clase principal de JavaFX.
     */
    @Override
    public void iniciarApp() {
        Application.launch(App.class); // Inicia la aplicación
//        Application.launch(Navegacion.class); // Inicia la aplicación
//        Image icon = new Image(getClass().getResourceAsStream("/images/logo.png"));
//        fondo.getIcons().add(icon);
//        fondo.setTitle("Domino 64");
//        cambiarInicio();
    }
    
    /**
     * Cambia la vista a la vista de inicio.
     */
    @Override
    public void cambiarInicio() {
        try {
            InicioView view = new InicioView(modeloInicio); // Instancia la vista de inicio
            view.initComponents(fondo); // Inicia la escena de inicio
            InicioControl control = new InicioControl(modeloInicio, view);
        } catch (Exception ex) {
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
            LobbyView view = new LobbyView(modeloLobby); // Instancia la vista de la partida
            view.initComponents(fondo); // Inicia la escena de la partida
            LobbyControl control = new LobbyControl(view, modeloLobby);
        } catch (Exception ex) {
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
            PartidaView partida = new PartidaView(modeloPartida); // Instancia la vista de la partida
            modeloPartida.addObserver(partida);
            partida.iniciarEscena(fondo); // Inicia la escena de la partida
            new PartidaControl(partida, modeloPartida);
        } catch (IOException ex) {
            ex.printStackTrace(); // Maneja la excepción imprimiendo el stack trace
        }
    }

    @Override
    public void actualizarPartida(PartidaDTO partidaActualizada) {
        this.modeloPartida.setGame(partidaActualizada);
        
    }

    @Override
    public void actualizarJugador(JugadorDTO jugadorActualizado) {
        this.modeloPartida.setJugador(jugadorActualizado);
    }
}
