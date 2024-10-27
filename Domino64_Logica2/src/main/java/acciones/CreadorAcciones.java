/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acciones;

import com.mycompany.patrones.command.Accion;
import entidades.ModoPartida;
import java.util.ArrayList;
import java.util.List;
import logica.DisplayHandler;
import logica.GameHandler;
import logica.TileHandler;
import logica.TurnHandler;

/**
 *
 * @author luisa M
 */
public class CreadorAcciones {
    private AccionBase accion;
    private DisplayHandler displayHandler;
    private final GameHandler gameHandler;
    private final TileHandler tileHandler;
    private final TurnHandler turnHandler;
    
    public CreadorAcciones(DisplayHandler display){
        displayHandler = display;
        gameHandler = new GameHandler();
        tileHandler = new TileHandler();
        turnHandler = new TurnHandler();
    }
    
    public List<Accion> crearAccionesLobby(){
        List<Accion> accionesLobby = new ArrayList<>();
        accionesLobby.add(accionCambiarPantalla(CambiarPantalla.IR_PARTIDA));
        accionesLobby.add(accionIniciarPartida());
        return accionesLobby;
    }
    
    public Accion accionCambiarPantalla(int destino){
        accion = new CambiarPantalla(displayHandler, destino);
        return accion;
    }
    
    public Accion accionCrearPartida(int modoPartida){
        accion = new CrearPartida(modoPartida, gameHandler);
        return accion;
    }
    
    public Accion accionIniciarPartida(){
        accion = new IniciarPartida();
        return accion;
    }
    
    public Accion accionUnirsePartida(){
        accion = new UnirsePartida();
        return accion;
    }
}
