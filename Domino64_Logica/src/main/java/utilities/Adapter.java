/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import entidades.Jugador;
import entidades.Partida;
import partida.PartidaModel;

/**
 *
 * @author luisa M
 */
public class Adapter implements ViewAdapter{
//    private INavegacion navegacion;

    public Adapter(){
//        navegacion = Navegacion.getInstance();
    }
    
    @Override
    public void pintarJuego(Partida partida, Jugador jugador) {
        PartidaModel model = new PartidaModel();
        model.setGame(partida);
        model.setJugador(jugador);
//        navegacion.actualizarPartida(model);
    }

    @Override
    public void iniciarJuego() {
//        navegacion.iniciarApp();
    }
    
    
}
