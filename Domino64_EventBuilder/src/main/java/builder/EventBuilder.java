/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
