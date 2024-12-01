/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package abstraccion;

import domino64.eventos.base.Evento;
import java.util.List;
import observer.Observer;

/**
 *
 * @author luisa M
 */
public interface ICliente {
    public void enviarEvento(Evento evento);
    public void establecerSuscripciones(List<Enum> suscripciones);
    public void agregarSuscripcion(Evento evento, Observer ob);
    public void removerSuscripcion(Evento evento, Observer ob);
    //public void enviarTiposEventos(List<Enum<?>> tiposEvento);
    //public void agregarTipoEvento(Enum<?> tipo);
}
