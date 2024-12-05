/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadores;

import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Jugador;
import entidadesDTO.JugadorDTO;

/**
 *
 * @author Luisa Morales
 */
public class ManejadorJugador {
    private Jugador jugador;
    private final AdaptadorEntidad adapterEntidad;
    private final AdaptadorDTO adapterDTO;
    
    public ManejadorJugador() {
        jugador = new Jugador();
        adapterEntidad = new AdaptadorEntidad();
        adapterDTO = new AdaptadorDTO();
    }
    
    public void setJugador(JugadorDTO jugadorDTO){
        this.jugador = adapterDTO.adaptarJugadorDTO(jugadorDTO);
    }
    
    public void abandonarPartida(){
        this.jugador = null;
    }
}
