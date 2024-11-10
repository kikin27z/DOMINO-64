/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.domino64.base;

import domino64.eventos.base.Evento;

/**
 * Interfaz para representar a los suscriptores de cualquier tipo de evento.
 * Estos suscriptores pueden estar recibiendo diferentes tipos de eventos,
 * dependiendo de los eventos a los que se les haya suscrito
 * Esta interfaz cuenta con un solo metodo para recibir los eventos que 
 * el publicador notifique (publique)
 * @author luisa M
 * @author karim F
 * @param <T> Tipo generico que puede representar cualquier tipo de evento especifico
 */
public interface Suscriptor<T extends Evento> {
    public void recibirEvento(T evento);
}
