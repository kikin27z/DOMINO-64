package presentacion_utilities;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import patrones.command.Accion;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import entidadesDTO.PartidaOfflineDTO;
import inicio.InicioControl;
import inicio.InicioModel;
import inicio.InicioView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        Application.launch(App.class);
    }
    
    /**
     * Cambia la vista a la vista de inicio.
     *
     * @throws IOException Si ocurre un error al cargar la vista de inicio.
     */
    @Override
    public void cambiarInicio() {
        try {
            InicioView view = new InicioView(modeloInicio); // Instancia la vista de inicio
            view.iniciarEscena(fondo); // Inicia la escena de inicio
            InicioControl control = new InicioControl(view, modeloInicio);
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
            view.iniciarEscena(fondo); // Inicia la escena de la partida
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

    @Override
    public void cambiarOpcionesPartida() {
        try {
            OpcionesPartidaModel modelo = new OpcionesPartidaModel();
            OpcionesPartidaView view = new OpcionesPartidaView(modelo); // Instancia la vista de la partida
            view.iniciarEscena(fondo); // Inicia la escena de la partida
            OpcionesPartidaControl control = new OpcionesPartidaControl(view, modelo);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void cambiarPartidaModoAnterior() {
        try {
            PartidaModel modelo  = new PartidaModel();
            PartidaView partida = new PartidaView(modelo); // Instancia la vista de la partida
            partida.iniciarEscena(fondo); // Inicia la escena de la partida
            new PartidaControl(partida, modelo);
            
//            CuentaDTO cuentaActual = new CuentaDTO(0, "/avatar/kiwi.png","Karim" );
//            CuentaDTO cpu = new CuentaDTO(1, "/avatar/tortuga.png","Karim" );
//            List<FichaDTO> fichas = new ArrayList<>();
//            fichas.add(new FichaDTO(6,6,0));
//            fichas.add(new FichaDTO(1,6,1));
//            fichas.add(new FichaDTO(1,1,0));
//            fichas.add(new FichaDTO(0,1,0));
//            fichas.add(new FichaDTO(3,0,0));
//            fichas.add(new FichaDTO(3,3,1));
//            fichas.add(new FichaDTO(2,3,1));
//            PartidaOfflineDTO partidaDTO = new PartidaOfflineDTO();
//            JugadorDTO jugador = new JugadorDTO();
//            jugador.setFichas(fichas);
//            cuentaActual.setJugador(new JugadorDTO());
//            cpu.setJugador(new JugadorDTO());
//            
//            partidaDTO.setCpu(cpu);
//            partidaDTO.setCuentaActual(cuentaActual);
//            modelo.iniciarPartida(partidaDTO);
            
        } catch (IOException ex) {
            ex.printStackTrace(); // Maneja la excepción imprimiendo el stack trace
        }
    }
}
