/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import com.luisa.entidades.Partida;
import event.Event;

/**
 *
 * @author luisa M
 */
public class EventoUnirsePartida extends Event<Partida> {
    
    public EventoUnirsePartida(String idPublicador, Partida partida){
        this.setPublisherName(idPublicador);
        this.setInfo(partida);
    }
    
}
