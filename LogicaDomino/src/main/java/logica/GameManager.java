/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

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
        turnManager = new TurnManager();
    }

    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bienvenidos a la partida");
        System.out.println("se van a repartir las fichas");
        try {
            distributeTiles();
        } catch (LogicException ex) {
            Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("fichas de cada jugador");
        for(Jugador j:partida.getJugadores()){
            System.out.println("fichas del jugador " + j.getUsername()+" :");
            System.out.println(j.getFichas());
        }
    }
    
    private void distributeTiles()throws LogicException{
        List<Jugador> jugadores = partida.getJugadores();
        
        for (int i = 0; i < partida.getFichasPorJugador(); i++) {
            for (Jugador j : jugadores) {
                j.agregarFicha(tileManager.giveTile());
            }
        }
            
    }
    
    
}
