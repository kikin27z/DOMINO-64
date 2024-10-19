/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eventBus;

/**
 *
 * @author luisa M
 */
public interface Subscriber {
    public void catchEvent(Event event);
    public String getName();
}
