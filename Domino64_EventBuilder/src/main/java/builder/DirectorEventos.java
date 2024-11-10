/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

/**
 *
 * @author luisa M
 * @param <T>
 */
public abstract class DirectorEventos<T extends EventBuilder> {
    public final T builder;
    
    public DirectorEventos(T builder){
        this.builder = builder;
    }

}
