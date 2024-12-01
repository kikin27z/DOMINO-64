package observer;

import domino64.eventos.base.Evento;

/**
 *
 * @author luisa M
 * @param <T>
 */
public interface Observer<T extends Evento> {
    public void update(T observable);
}
