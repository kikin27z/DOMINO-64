/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import com.luisa.entidades.Cuenta;
import com.luisa.entidades.Jugador;

/**
 *
 * @author luisa M
 */
public class EventoPartida extends Event<Jugador> {
    private Jugador jugador;
    
    public EventoPartida(Cuenta publicador, Enum<?> tipo){
        super(publicador, tipo);
    }
    
    @Override
    public void agregarInfo(Jugador info) {
        jugador = info;
    }

    @Override
    public Jugador getInfo() {
        return jugador;
    }
    
}
