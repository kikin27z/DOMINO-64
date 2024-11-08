package presentacion_dibujo;

import entidadesDTO.FichaDTO;

/**
 *
 * @author karim
 */
public abstract class BuilderFicha<T> {


    
    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }
    public abstract void reiniciar();
    public abstract void construirVertical(FichaDTO ficha);
    public abstract void construirHorizontal(FichaDTO ficha);
    public abstract T resultado();
}
