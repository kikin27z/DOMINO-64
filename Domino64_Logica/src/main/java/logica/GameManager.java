/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import entidades.Ficha;
import entidades.Jugador;
import entidades.Partida;
import exceptions.LogicException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisa M
 */
public class GameManager implements Runnable{
    private Partida partida;
    private TileManager tileManager;
    private TurnManager turnManager;
    
    public GameManager(Partida partida){
        this.partida = partida;
        tileManager = new TileManager(partida.getPozo(),partida.getTablero());
        turnManager = new TurnManager(partida.getJugadores());
    }

    @Override
    public void run() {
        System.out.println("Bienvenidos a la partida");
        System.out.println("se van a repartir las fichas");
        
        distributeTiles();
        
        System.out.println("fichas de cada jugador");
        for(Jugador j:partida.getJugadores()){
            System.out.println("fichas del jugador " + j.getUsername()+" :");
            System.out.println(j.getFichas());
        }
        
        System.out.println("fichas en el pozo:");
        for(Ficha f:tileManager.getFichas()){
            System.out.println(f);
        }
        
        setFirstTurn();
        
        turnManager.designateOtherTurns();
        
        System.out.println("turnos designados: ");
        for(Jugador j : partida.getJugadores()){
            System.out.println(j);
        }
    }
    
    private void distributeTiles(){
        try {
            tileManager.distributeTiles(partida.getJugadores(),partida.getFichasPorJugador());
        } catch (LogicException ex) {
            Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void setFirstTurn(){
        Jugador firstTurn = turnManager.getFirstTurn();
        if(firstTurn == null){
            try {
                Object[] duple = tileManager.getFirstDouble(partida.getJugadores());
                firstTurn = (Jugador)duple[1];
                Ficha doubleToPut = (Ficha)duple[0];
                System.out.println("firstTurn by first double: "+ firstTurn);
                System.out.println("double to put: "+ doubleToPut);
            } catch (LogicException ex) {
                System.out.println(ex.getMessage());
            }
            turnManager.setFirstTurn(firstTurn);
        }
        System.out.println("primer jugador:" + firstTurn);
        
    }
}
