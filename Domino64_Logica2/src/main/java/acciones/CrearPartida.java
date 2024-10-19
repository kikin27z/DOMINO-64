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
    private int modo;
    
    public CrearPartida(int modoPartida, GameHandler handler){
        if(gameHandler == null)
            gameHandler = handler;
        modo = modoPartida;
    }
    
    private void crearPartida(){
        if(GameHandler.getPartida() == null){
            Partida partida = new Partida(modo);
            gameHandler.setPartida(partida);
        }
    }
    
    
    @Override
    public void ejecutarAccion() {
        crearPartida();
    }
    
}
