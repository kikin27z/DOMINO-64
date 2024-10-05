/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.logicadomino;

import entidades.Ficha;
import entidades.Jugador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author luiis
 */
public class main {

    public static void main(String[] args) {
//        int[][] valoresFichas ={
//            {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6},
//            {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6},
//            {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6},
//            {3, 3}, {3, 4}, {3, 5}, {3, 6},
//            {4, 4}, {4, 5}, {4, 6},
//            {5, 5}, {5, 6},
//            {6, 6}
//        };
//        
//        List<Ficha> fichas = new ArrayList<>();
//        for (int i = 0; i < 28; i++) {
//            fichas.add(new Ficha(valoresFichas[i][0],valoresFichas[i][1]));
//        }
//        
//        fichas.forEach(f->{
//            System.out.println(f);
//        });
//        
//        Collections.shuffle(fichas);
//        
//        System.out.println("fichas para el pozo:");
//        
//        
//        Stack<Ficha> stackFichas = new Stack<>();
//        stackFichas.addAll(fichas);
//        
//        stackFichas.forEach(f->{
//            System.out.println(f);
//        });
        
        List<Jugador> players = new ArrayList<>();
        
        players.add(new Jugador("luisa"));
        players.add(new Jugador("fernanda"));
        players.add(new Jugador("marcela"));
        players.add(new Jugador("ximena"));
        
        System.out.println("jugadores sin mezclar:");
        for(Jugador j: players){
            System.out.println(j);
        }
        
        System.out.println();
        Jugador primerJugador = players.remove(0);
        Collections.shuffle(players);
        System.out.println("jugadores mezclados:");
        
        for(Jugador j: players){
            System.out.println(j);
        }
        System.out.println();
        players.addFirst(primerJugador);
        System.out.println("jugadores mezclados con el primer turno designado:");
        for(Jugador j: players){
            System.out.println(j);
        }
        
        
    }
}
