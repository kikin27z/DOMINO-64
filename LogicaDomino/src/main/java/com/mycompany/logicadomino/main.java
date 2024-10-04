/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.logicadomino;

import entidades.Ficha;
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
        int[][] valoresFichas ={
            {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6},
            {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6},
            {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6},
            {3, 3}, {3, 4}, {3, 5}, {3, 6},
            {4, 4}, {4, 5}, {4, 6},
            {5, 5}, {5, 6},
            {6, 6}
        };
        
        List<Ficha> fichas = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            fichas.add(new Ficha(valoresFichas[i][0],valoresFichas[i][1]));
        }
        
        fichas.forEach(f->{
            System.out.println(f);
        });
        
        Collections.shuffle(fichas);
        
        System.out.println("fichas para el pozo:");
        
        
        Stack<Ficha> stackFichas = new Stack<>();
        stackFichas.addAll(fichas);
        
        stackFichas.forEach(f->{
            System.out.println(f);
        });
    }
}
