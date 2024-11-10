/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
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
