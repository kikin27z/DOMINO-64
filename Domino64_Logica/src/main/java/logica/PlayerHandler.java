/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import entidades.Ficha;
import entidades.Jugador;
import exceptions.LogicException;
import java.util.List;
import utilities.ActivityHandler;

/**
 *
 * @author luisa M
 */
public class PlayerHandler extends ActivityHandler{

    private Jugador player;
    private Ficha higherDouble;
//    public static final int GET_HIGHER_DOUBLE = 1;
//    public static final int PUT_TILE = 2;
    
    public PlayerHandler(Jugador player){
        this.player = player;
    }
    
    @Override
    public void handleRequest(int activityType, Object ... context) throws LogicException{
        switch (activityType) {
            case SELECT_TILE:
                
                break;
        }

    }
    
    public Jugador getPlayer(){
        return this.player;
    }
    
    public Ficha getHigherDouble(){
        return higherDouble;
    }
    
    private boolean hasValidTiles(){
        return true;
    }
    
    
}
