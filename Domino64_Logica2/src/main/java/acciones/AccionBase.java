package acciones;

import patrones.command.Accion;
import com.mycompany.starter.Display;
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
    static Display display;
    
    public void setDisplay(Display displayy){
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

    public Display getDisplay() {
        return display;
    }
    
}
