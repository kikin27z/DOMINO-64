/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;


/**
 *
 * @author luisa M
 */
public abstract class ObservadorGameHandler {
    public abstract void inicializarPartida(JugadorDTO jugador, PartidaDTO partida);
    public abstract void seleccionarFicha(FichaDTO ficha);
    public abstract void seleccionarExtremoTrenFichas(FichaDTO ficha);
    public abstract void quitarJugadorPartida(JugadorDTO jugador);
    public abstract void agregarJugadorPartida(JugadorDTO jugador);
    public abstract void iniciarPartida(PartidaDTO partida);
    
}
