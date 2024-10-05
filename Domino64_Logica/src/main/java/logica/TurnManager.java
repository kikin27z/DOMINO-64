package logica;

import entidades.Ficha;
import entidades.Jugador;
import exceptions.LogicException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class TurnManager {
    private List<Jugador> players = new ArrayList<>();
    private Jugador turnPlayer;
    
    public TurnManager(List<Jugador> players){
        this.players = players;
    }
    
    /**
     * determina el jugador que tendra el primer turno.
     * Para determinarlo se busca el jugador que tenga
     * la mula mas grande
     * @return el jugador con la mula mas grande. Null
     * en caso de que ningun jugador tenga una mula en sus fichas
     */
    public Jugador getFirstTurn(){
        Ficha higherTile=null;
        TileComparator comparator= new TileComparator();
        
        for(Jugador j:players){
            List<Ficha> playersTiles = j.getFichas();
            Ficha actualTile = getHigherTile(playersTiles);
            
            if(actualTile != null){
                System.out.println("actual higher double: "+actualTile);
                if (higherTile == null) {
                    higherTile = actualTile;
                    turnPlayer = j;
                } else if (comparator.compare(actualTile, higherTile) > 0) {
                    higherTile = actualTile;
                    turnPlayer = j;
                }
                
            }
        }
        if(turnPlayer!=null)
            System.out.println(turnPlayer.getUsername());
        return turnPlayer;
    }
    
    public void setFirstTurn(Jugador turnPlayer){
        this.turnPlayer = turnPlayer;
    }
    
    /**
     * encuentra la mula mas grande en la lista
     * de fichas proporcionada
     * @param tiles Lista de fichas de donde se buscara
     * la mula
     * @return la mula mas grande. Null si no hay mulas 
     */
    private Ficha getHigherTile(List<Ficha> tiles){
        int higherValue=0;
        Ficha higherTile=null;
        for(Ficha f: tiles){
            if(f.getLado1()==f.getLado2()){
                int actualValue = f.getLado1()+f.getLado2();
                if(actualValue>higherValue){
                    higherValue=actualValue;
                    higherTile = f;
                    System.out.println("higher double: "+higherTile);
                }
            }
        }
        return higherTile;
    }
    
    public void designateOtherTurns(){
        if(turnPlayer != null){
            players.remove(turnPlayer);
            Collections.shuffle(players);
            players.addFirst(turnPlayer);
        }
    }
    
    public Ficha getSelectedTile(){
        Scanner scan = new Scanner(System.in);
        String numeros = null;
        int selectedTileIndex = 0;
        boolean flag = false;
        do{
            System.out.println("ingresa la ficha que deseas colocar");
            System.out.println("los numeros de la ficha deben seguir el"
                    + "siguiente formato-> 3:5)");
            System.out.println("las fichas que puedes poner son:");
            System.out.println(turnPlayer.getFichas());
            numeros = scan.findInLine("\\d:\\d");
            
            if(numeros !=null){
                selectedTileIndex = tileIndex(numeros);
                if(selectedTileIndex >= 0)
                    flag = true;
                else
                    System.out.println("no tienes esa ficha");
            }else{
                System.out.println("se ingreso un formato inadecuado para los numeros de la ficha");
            }
        }while(!flag);
        
        Ficha selectedTile = turnPlayer.getFichas().get(selectedTileIndex);
        return selectedTile;
    }
    
    private int tileIndex(String tileNumbers){
        int side1 = tileNumbers.charAt(0);
        int side2 = tileNumbers.charAt(2);
        Ficha tile = new Ficha(side1, side2);
        int index = turnPlayer.getFichas().indexOf(tile);
        return index;
    }
    
    public void putTile(Ficha ficha){
        
    }
    
}

/**
 * Clase comparadora de fichas.
 * Solo implementa el metodo 'compare',
 * cuyo propósito es evaluar dos fichas para
 * determinar cual es de mayor valor.
 * @author luisa M
 */
class TileComparator implements Comparator<Ficha> {
  
    
    
    /**
     * Evalua las fichas de los parámetros para determinar
     * cual es de mayor valor.
     * @param tile1 primera ficha a comparar
     * @param tile2 segunda ficha a comparar
     * @return 0 si las fichas tienen el mismo valor; un
     * numero mayor a 0 si el valor de tile1 es mayor
     * que el de tile2; un numero menor a 0 si el valor
     * de tile1 es menor que el valor de que tile2
     */
    @Override
    public int compare(Ficha tile1, Ficha tile2) {
        int tileValue1 = tile1.getLado1() + tile1.getLado2();
        int tileValue2 = tile2.getLado1() + tile2.getLado2();
        return Integer.compare(tileValue1, tileValue2);
    }
    
    }