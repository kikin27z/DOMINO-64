/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.domino64.base;

import domino64.eventos.base.Evento;

/**
 * Interfaz para representar a un Publicador de eventos
 * de cualquier tipo.
 * @author luisa M
 * @author karim F
 * @param <T> Tipo generico que va a representar cualquier tipo 
 * especifico de evento
 */
public interface Publicador<T extends Evento>{
    public void suscribir(Enum tipoEvento, Suscriptor suscriptor);
    public void desuscribir(Enum tipoEvento, Suscriptor suscriptor);
    public void publicarEvento(Enum tipoEvento, T evento);
}
