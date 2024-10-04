/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import entidades.Ficha;
import entidades.Pozo;
import entidades.Tablero;
import exceptions.DominioException;
import exceptions.LogicException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisa M
 */
public class TileManager {
    private final int[][] valoresFichas ={
            {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6},
            {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6},
            {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6},
            {3, 3}, {3, 4}, {3, 5}, {3, 6},
            {4, 4}, {4, 5}, {4, 6},
            {5, 5}, {5, 6},
            {6, 6}
        };
    private final Pozo pozo;
    private final Tablero tablero;
    
    public TileManager(Pozo pozo, Tablero tablero){
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
            fichas.add(new Ficha(valoresFichas[i][0],valoresFichas[i][1]));
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
    
    public Ficha giveTile()throws LogicException{
        try {
            Ficha ficha = pozo.jalarFicha();
            return ficha;
        } catch (DominioException ex) {
            throw new LogicException(ex.getMessage());
        }
    }
    
    public List<Ficha> giveTiles(int tilesNumber)throws LogicException{
        List<Ficha> fichas = new ArrayList<>();
        try {
            for (int i = 0; i < tilesNumber; i++) {
                fichas.add(pozo.jalarFicha());
            }
            return fichas;
        } catch (DominioException ex) {
            throw new LogicException(ex.getMessage());
        }
    }
}
