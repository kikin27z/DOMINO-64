/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import entidades.Jugador;
import entidades.Partida;
import java.util.ArrayList;
import java.util.List;
import logica.GameHandler;
import partida.PartidaModel;
import presentacion_utilities.INavegacion;
import presentacion_utilities.Navegacion;

/**
 *
 * @author luisa M
 */
public class Adapter implements ViewAdapter{
    private INavegacion navegacion;

    public Adapter(){
        navegacion = Navegacion.getInstance();
    }
    
    @Override
    public void pintarJuego(Partida partida, Jugador jugador) {
        PartidaModel model = new PartidaModel();
        model.setGame(partida);
        model.setJugador(jugador);
        navegacion.actualizarPartida(model);
    }

    
    
    @Override
    public void iniciarJuego() {
        navegacion.iniciarApp();
    }

    @Override
    public void cargarPartida() {
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jugador = new Jugador("Luisa");
        jugadores.add(jugador);
        jugadores.add(new Jugador("Maria"));
        
        Partida partida = new Partida("123", jugadores, 7, 2);
        GameHandler handler = new GameHandler(partida, jugador);
        Thread thread = new Thread(handler);
        thread.start();
        
    }
}
