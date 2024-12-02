package builder;

import domino64.eventos.base.Evento;

/**
 *
 * @author luisa M
 * @param <T>
 */
public interface EventBuilder<T extends Enum> {
    public abstract void setTipo(T tipo);
    
    public abstract void setIdPublicador(int idPublicador);
    
    public abstract void setIdContexto(int idContexto);
    
    public abstract Evento construirEvento();
    
    public abstract void reiniciar();
}
