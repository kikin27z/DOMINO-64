package cliente_suscripciones;

import domino64.eventos.base.Evento;

/**
 *
 * @author luisa M
 * @param <T>
 */
public interface ObserverCliente<T extends Evento> {
    public void update(T observable);
}
