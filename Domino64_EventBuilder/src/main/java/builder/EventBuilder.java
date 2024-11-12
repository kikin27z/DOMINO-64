package builder;

import domino64.eventos.base.Evento;

/**
 *
 * @author luisa M
 */
public interface EventBuilder<T> {
    public abstract void setTipo(Enum<?> tipo);
    
    public abstract void setIdPublicador(int idPublicador);
    
    public abstract void setInfo(T info);
    
    public abstract Evento construirEvento();
    
    public abstract void reiniciar();
}
