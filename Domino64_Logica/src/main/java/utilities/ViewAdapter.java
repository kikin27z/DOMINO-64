/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package utilities;

import entidades.Jugador;
import entidades.Partida;

/**
 *
 * @author luisa M
 */
public interface ViewAdapter {
    public void iniciarJuego();
    public void pintarJuego(Partida partida, Jugador jugador);
    public void cargarPartida();
}
