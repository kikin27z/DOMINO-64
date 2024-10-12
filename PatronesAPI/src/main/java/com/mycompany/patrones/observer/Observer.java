/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.patrones.observer;

/**
 *
 * @author luisa M
 */
public interface Observer<T> {
    public void update(T observable, Object ... context);
}
