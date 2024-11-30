package cliente_conexion;

import domino64.eventos.base.Evento;
import java.util.List;
import cliente_suscripciones.ObserverCliente;

/**
 *
 * @author luisa M
 */
public interface ICliente2 {
    public void enviarEvento(Evento evento);
    public void establecerSuscripciones(List<Enum<?>> suscripciones);
    public void agregarSuscripcion(Evento evento, ObserverCliente ob);
    public void removerSuscripcion(Evento evento, ObserverCliente ob);
    //public void enviarTiposEventos(List<Enum<?>> tiposEvento);
    //public void agregarTipoEvento(Enum<?> tipo);
}
