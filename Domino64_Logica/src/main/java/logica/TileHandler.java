/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import entidades.Ficha;
import entidades.Jugador;
import entidades.Pozo;
import entidades.Tablero;
//import exceptions.DominioException;
import exceptions.LogicException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.ActivityHandler;

/**
 *
 * @author luisa M
 */
public class TileHandler extends ActivityHandler {
    private final int[][] valoresFichas ={
            {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6},
            {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6},
            {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6},
            {3, 3}, {3, 4}, {3, 5}, {3, 6},
            {4, 4}, {4, 5}, {4, 6},
            {5, 5}, {5, 6},
            {6, 6}
        };
    
    
    private String getTileImageURL(int tileNumber1, int tileNumber2){
        StringBuilder url = new StringBuilder("/dominos/");
        url.append(String.valueOf(tileNumber1));
        url.append("-");
        url.append(String.valueOf(tileNumber2));
        url.append(".png");
        return url.toString();
    }
    
    private final Pozo pozo;
    private final Tablero tablero;
    
    public TileHandler(Pozo pozo, Tablero tablero){
        this.pozo = pozo;
        this.tablero = tablero;
        fillPool();
    }
    
    /**
     * metodo para crear una lista de fichas con los valores
     * almacenados en el array valoresFichas
     * @return la lista con las fichas
     */
    private List<Ficha> buildTiles(){
        List<Ficha> fichas = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            int value1 = valoresFichas[i][0];
            int value2 = valoresFichas[i][1];
            String url = getTileImageURL(value1, value2);
            fichas.add(new Ficha(value1,value2,url));
        }
        return fichas;
    }
    
    /**
     * metodo para llenar el pozo con las fichas creadas
     */
    private void fillPool(){
        List<Ficha> fichas = buildTiles();//crea las fichas
        Collections.shuffle(fichas);//revuelve la lista para cambiar el orden
        Stack<Ficha> stackFichas = new Stack<>();
        stackFichas.addAll(fichas);//agrega al stack las fichas revueltas
        pozo.llenarPozo(stackFichas);//llena el pozo con el stack
    }
   
    /**
     * Jala una ficha del pozo
     * @return la ficha obtenida
     * @throws LogicException en caso de que el pozo este vacio
     */
    public Ficha giveTile()throws LogicException{
        try {
            Ficha ficha = pozo.jalarFicha();
            return ficha;
        } catch (Exception ex) {
            throw new LogicException(ex.getMessage());
        }
    }
    
    /**
     * 
     * @param tilesNumber
     * @return
     * @throws LogicException 
     */
    private List<Ficha> giveTiles(int tilesNumber)throws LogicException{
        List<Ficha> fichas = new ArrayList<>();
        try {
            for (int i = 0; i < tilesNumber; i++) {
                fichas.add(pozo.jalarFicha());
            }
            return fichas;
        } catch (Exception ex) {
            throw new LogicException(ex.getMessage());
        }
    }
   
    /**
     * Reparte la cantidad de fichas indicada por el parametro
     * a los jugadores en la lista del parametro
     * @param players quienes recibiran las fichas
     * @param tilesPerPlayer cantidad de fichas a repartir a cada jugador
     * @throws LogicException 
     */
    public void distributeTiles(List<Jugador> players, int tilesPerPlayer) throws LogicException {
        for (int i = 0; i < tilesPerPlayer; i++) {
            for (Jugador j : players) {
                j.agregarFicha(giveTile());
            }
        }
    }
    
    /**
     * Jala fichas del pozo hasta que se obtiene una
     * que sea una mula. 
     * Los jugadores en la lista van recibiendo una ficha
     * del pozo hasta que salga una ficha mula. La primera
     * mula que salga sera la primera en ponerse en el tablero.
     * El jugador que haya jalado dicha mula sera el jugador
     * con el primer turno.
     * @param players a recibir fichas
     * @return una dupla de la mula encontrada y el jugador que la obtuvo.
     * El primer elemento de la dupla guarda la mula encontrada, la cual
     * es la primera ficha a colocar en la partida; el segundo elemento es
     * el jugador.
     * @throws LogicException en caso de que se acaben las fichas en el pozo
     */
    public Object[] getFirstDouble(List<Jugador> players) throws LogicException {
        Object[] duple = new Object[2];
        for (Jugador j : players) {
            Ficha tile = giveTile();
            if (tile.getIzquierda()== tile.getDerecha()) {
                duple[0] = tile;
                duple[1] = j;
                break;
            }
            j.agregarFicha(tile);
        }
        
        return duple;
    }
    
    private List<Ficha> getValidTiles(){
        List<Ficha> validTiles = new ArrayList<>();
        if(!tablero.tableroVacio()){
            Ficha tile1 = tablero.obtenerFichaIzq();
            Ficha tile2 = tablero.obtenerFichaDer();
            validTiles.add(tile1);
            validTiles.add(tile2);
        }
        return validTiles;
    }
    
    public Stack<Ficha> getFichas(){
        return pozo.getFichas();
    }

    /**
     * Metodo para manejar la solicitud de repartir
     * las fichas a los jugadores
     * @param context parametros necesarios para manejar la solicitud.
     * Para esta actividad el contexto debe incluir: 
     * -Un entero con la cantidad de fichas a repartir a cada jugador
     * -Una lista de jugadores a los cuales se les repartiran las fichas
     * @throws LogicException 
     */
    private void handleDistriubteTilesRequest(Object ... context) throws LogicException{
        List<Jugador> players = null;
        int tilesPerPlayer = 0;
        for (Object object : context) {
            if (object.getClass() == Integer.class) {
                tilesPerPlayer = (int) object;
            } else {
                players = (List<Jugador>) object;
            }
        }
        distributeTiles(players, tilesPerPlayer);
    }
    
    private void handlePutTileRequest(Object ... context){
        
    }
    
    /**
     * Maneja la solicitud de designar el primer turno
     * al jugador que obtenga la primera mula del pozo.
     * @param activityType indicador del tipo de actividad a manejar.
     * Esta actividad debe ser de tipo FIRST_DOUBLE
     * @param context parametros necesarios para manejar la solicitud. 
     * Para esta actividad el contexto debe contener una lista de jugadores
     * @throws LogicException 
     */
    private void handleFirstDoubleRequest(int activityType,Object ... context) throws LogicException{
        
        List<Jugador> players = null;
        for (Object obj : context) {
            players = (List<Jugador>)obj;
        }
        
        Object[] duple = getFirstDouble(players);
        Jugador firstPlayer = (Jugador)duple[1];
        this.nextHandler.handleRequest(activityType, firstPlayer);
        
        Ficha firstDouble = (Ficha)duple[0];
        putFirstDouble(firstDouble);
        
    }
    
    private void paintTile(Ficha tile){
        
    }
    
    /**
     * coloca la primera mula en el tablero
     * @param firstDouble a colocar en el tablero
     * @throws LogicException si ya coloco una ficha anteriormente
     */
    private void putFirstDouble(Ficha firstDouble)throws LogicException{
        try {
            this.tablero.insertarFicha(firstDouble);
            System.out.println("se coloco la ficha " + firstDouble + " en el tablero");
        } catch (Exception ex) {
            throw new LogicException(ex.getMessage());
        }
    }
    
    @Override
    public void handleRequest(int activityType, Object ... context) throws LogicException {
        switch (activityType) {
            case DISRTIBUTE_TILES -> handleDistriubteTilesRequest(context);
            case FIRST_DOUBLE -> handleFirstDoubleRequest(activityType,context);
            case PUT_TILE -> handlePutTileRequest(context);
            default -> this.nextHandler.handleRequest(activityType, context);
        }
    }
}
