/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import com.luisa.entidades.Cuenta;

/**
 *
 * @author luisa M
 */
public class EventoLobby extends Event<Cuenta> {
    private Cuenta jugador;

    public EventoLobby(Cuenta publicador, Enum<?> tipo) {
        super(publicador, tipo);
    }
    
    @Override
    public void agregarInfo(Cuenta info) {
        jugador = info;
    }

    @Override
    public Cuenta getInfo() {
        return jugador;
    }
    
}
