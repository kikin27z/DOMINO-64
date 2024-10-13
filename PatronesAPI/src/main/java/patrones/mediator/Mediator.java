package patrones.mediator;

/**
 *
 * @author luisa M
 */
public interface Mediator<T> {
    public void enviarNotificacion(T notificador);
}
