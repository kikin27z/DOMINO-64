/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.luisa.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class Dominio {

    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>();
        numeros.add(7);
        numeros.add(5);
        numeros.add(12);
        numeros.add(8);
        numeros.add(2);
        numeros.add(19);
        
        insertionSort(numeros);
        //printNumeros(numeros);
    }
    
    public static List<Integer> insertionSort(List<Integer> numeros){
        for (int i = 0; i < numeros.size(); i++) {
            int key = numeros.get(i);
            int j = i - 1;

            while (j >= 0 && numeros.get(j)> key) {
                numeros.set(j + 1, numeros.get(j));
                j = j - 1;
            }

            numeros.set(j + 1, key);
            printNumeros(numeros);
            System.out.println("");

        }
        return numeros;
    }
    
     private static void printNumeros(List<Integer> numeros){
        for (Integer num : numeros) {
            System.out.print("numero: "+num+", ");
        }
    }
}
