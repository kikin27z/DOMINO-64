/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acciones;

import entidades.ModoPartida;
import entidades.Partida;
import logica.GameHandler;

/**
 *
 * @author luisa M
 */
public class CrearPartida extends AccionBase{
    
    private ModoPartida modo;
    
    public CrearPartida(ModoPartida modoPartida, GameHandler handler){
        gameHandler = handler;
        modo = modoPartida;
    }
    
    private void crearPartida(){
        Partida partida = new Partida(modo);
        gameHandler.setPartida(partida);
    }
    
    
    @Override
    public void ejecutarAccion() {
        crearPartida();
    }
    
}
