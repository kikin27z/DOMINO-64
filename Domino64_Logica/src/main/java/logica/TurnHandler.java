package logica;

import entidades.Ficha;
import entidades.Jugador;
import exceptions.LogicException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import utilities.ActivityHandler;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class TurnHandler extends ActivityHandler{
    private List<Jugador> players = new ArrayList<>();
    private Jugador turnPlayer;
    private boolean isOnTurn;
    private List<PlayerHandler> playerHandlers = new ArrayList<>();
    //private final int SET_FIRST_TURN = 1;
    
    public TurnHandler(List<Jugador> players){
        this.players = players;
        setHandlersChain();
    }
    
    private void setHandlersChain(){
        PlayerHandler playerHandler = null;
        for (Jugador player : players) {
            if(playerHandler!=null){
                PlayerHandler newHandler = new PlayerHandler(player);
                playerHandler.setNextHandler(newHandler);
                playerHandler = newHandler;
            }else{
                playerHandler = new PlayerHandler(player);
            }
            playerHandlers.add(playerHandler);
        }
        
    }
    
    public Jugador getFirstTurn(){
        return turnPlayer;
    }
    
    /**
     * determina el jugador que tendra el primer turno.
     * Para determinarlo se busca el jugador que tenga
     * la mula mas grande
     */
    public void setFirstTurn(){
        Ficha higherTile = null;
        TileComparator comparator = new TileComparator();
        System.out.println("setFirstTurn");
        for (Jugador player : players) {
            Ficha playerHigherTile = player.getHigherDouble();
            //System.out.println("playerHif dsf:" + playerHigherTile);
            if(playerHigherTile != null){
                if (higherTile == null) {
                    higherTile = playerHigherTile;
                    turnPlayer = player;
                } else if (comparator.compare(playerHigherTile, higherTile) > 0) {
                    higherTile = playerHigherTile;
                    turnPlayer = player;
                }
            }
        }
        if(turnPlayer != null){
            System.out.println("first player: "+turnPlayer);
            System.out.println("higher double: "+ higherTile);
        }
        //return turnPlayer;
    }
    
    public void setFirstTurn(Jugador turnPlayer){
        this.turnPlayer = turnPlayer;
    }
    
   
    private void setIsOnTurn(boolean flag){
        this.isOnTurn = flag;
    }
    
    public boolean isOnTurn(){
        return isOnTurn;
    }
    
    public void designateOtherTurns(){
        if(turnPlayer != null){
            players.remove(turnPlayer);
            Collections.shuffle(players);
            players.addFirst(turnPlayer);
            System.out.println("se designaron los demas turnos");
        }else
            System.out.println("turn player null");
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
    
    /**
     * metodo para cambiar el turno del jugador.
     */
    public void cambiarTurno(){
        int index = players.indexOf(turnPlayer);
        //si el jugador con el turno actual es
        //el jugador con el ultimo turno
        if(index == players.size()-1){
            //inicia otra ronda de turnos, es decir
            //que ahora el siguiente jugador es el primero en la lista
            index = 0;
        }else 
            index++;
        turnPlayer = players.get(index);
//        Ficha selectedTile = turnPlayer.getFichas().get(selectedTileIndex);
    }
    
    public Jugador jugadorEnTurno(){
        return turnPlayer;
    }

    @Override
    public void handleRequest(int activityType, Object ... context) throws LogicException{
        switch (activityType) {
            case DESIGNATE_FIRST_TURN:
                setFirstTurn();
                designateOtherTurns();
                break;
            case FIRST_DOUBLE:
                Jugador firstTurn = null;
                for (Object object : context) {
                    firstTurn = (Jugador) object;
                }
                setFirstTurn(firstTurn);
                designateOtherTurns();
                break;
            case CHECK_TURN:
                Jugador jugador  = null;
                for (Object object : context) {
                    jugador = (Jugador)object;
                }
                setIsOnTurn(turnPlayer.equals(jugador));
                break;
            case CHANGE_TURN:
                cambiarTurno();
                break;
        }
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
        int tileValue1 = tile1.getIzquierda()+ tile1.getDerecha();
        int tileValue2 = tile2.getIzquierda() + tile2.getDerecha();
        return Integer.compare(tileValue1, tileValue2);
    }
    
    }