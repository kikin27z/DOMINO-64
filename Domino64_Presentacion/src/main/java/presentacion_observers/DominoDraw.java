package presentacion_observers;

import entidadesDTO.FichaDTO;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author karim
 */
public abstract class DominoDraw {
    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }
    public abstract void reiniciar();
    public abstract void construirVertical(FichaDTO ficha);
    public abstract void construirHorizontal(FichaDTO ficha);
    public abstract Canvas resultado();
}
