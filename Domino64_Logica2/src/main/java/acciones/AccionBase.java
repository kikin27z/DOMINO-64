/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acciones;

import com.mycompany.patrones.command.Accion;
import logica.DisplayHandler;
import logica.GameHandler;
import logica.TileHandler;
import logica.TurnHandler;

/**
 *
 * @author luisa M
 */
public abstract class AccionBase implements Accion{
    public static GameHandler gameHandler;
    static TileHandler tileHandler;
    static TurnHandler turnHandler;
    static DisplayHandler display;
    
    public void setDisplay(DisplayHandler displayy){
        display = displayy;
    }

    public void setGameHandler(GameHandler gameHandlerr) {
        gameHandler = gameHandlerr;
    }

    public void setTileHandler(TileHandler tileHandlerr) {
        tileHandler = tileHandlerr;
    }

    public void setTurnHandler(TurnHandler turnHandlerr) {
        turnHandler = turnHandlerr;
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public TileHandler getTileHandler() {
        return tileHandler;
    }

    public TurnHandler getTurnHandler() {
        return turnHandler;
    }

    public DisplayHandler getDisplay() {
        return display;
    }
    
}
