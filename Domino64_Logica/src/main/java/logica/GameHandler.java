/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import entidades.Ficha;
import entidades.Jugador;
import entidades.Partida;
import entidades.Tablero;
import exceptions.LogicException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.ActivityHandler;

/**
 *
 * @author luisa M
 */
public class GameHandler extends ActivityHandler implements Runnable{
    private Partida partida;
    private TileHandler tileManager;
    private TurnHandler turnManager;
    private Jugador jugador;
    
    public GameHandler(Partida partida, Jugador jugador){
        this.partida = partida;
        this.jugador = jugador;
        tileManager = new TileHandler(partida.getPozo(),partida.getTablero());
        turnManager = new TurnHandler(partida.getJugadores());
        this.setNextHandler(tileManager);
        tileManager.setNextHandler(turnManager);
    }

    public void init(){
        System.out.println("Bienvenidos a la partida");
        System.out.println("se van a repartir las fichas");
        
        try {
            handleRequest(DISRTIBUTE_TILES,partida.getFichasPorJugador(), partida.getJugadores());
            
            System.out.println("fichas de cada jugador");
            for (Jugador j : partida.getJugadores()) {
                System.out.println("fichas del jugador " + j.getUsername() + " :");
                System.out.println(j.getFichas());
            }
            
            System.out.println("fichas en el pozo:");
            for (Ficha f : tileManager.getFichas()) {
                System.out.println(f);
            }
            
            handleRequest(DESIGNATE_FIRST_TURN);
            if(turnManager.getFirstTurn() == null){
                handleRequest(FIRST_DOUBLE,partida.getJugadores()); 
            }
            
            System.out.println("turnos designados: ");
            for (Jugador j : partida.getJugadores()) {
                System.out.println(j);
            }
        } catch (LogicException ex) {
            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        System.out.println("hello");
        Tablero tablero = partida.getTablero();
        boolean flag = false;
        try{
            while (!flag) {
                if (!tablero.tableroVacio()) {
                    System.out.println("ficha en tablero: " + tablero.getExtremo1());
                    handleRequest(PUT_TILE);
                    flag = true;
                }
            }
            System.out.println("bye");
        }catch(LogicException ex){
            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
//        
//        
//        try{
//            while (true) {
//                handleRequest(CHECK_TURN, jugador);
//                if (turnManager.isOnTurn()) {
//                    
//                }
//            }
//        }catch(LogicException ex){
//            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    

    @Override
    public void handleRequest(int activityType, Object ... context) throws LogicException{
        switch (activityType) {
            case REMOVE_PLAYER -> {
            }
            case FINISH_GAME -> {
            }
            default -> this.nextHandler.handleRequest(activityType, context);
        }
    }
    
}
