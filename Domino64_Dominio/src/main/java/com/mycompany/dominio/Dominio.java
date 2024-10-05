/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author luiis
 */
public class Dominio {

    public static void main(String[] args) {
        
//        List<String> personas = new ArrayList<>();
//        personas.add("luisa");
//        personas.add("martha");
//        personas.add("karina");
//        personas.add("sofia");
//        
////        for (String persona : personas) {
////            System.out.println(persona);
////        }
////        
//        Collections.shuffle(personas);
//        System.out.println();
//        for (String persona : personas) {
//            System.out.println(persona);
//        }
        
        Stack<Integer> stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        
        for(Integer element: stack){
            System.out.println(element);
        }
        
        System.out.println("peak: "+stack.peek());
        System.out.println("first: "+stack.firstElement());
        System.out.println("pop: "+stack.pop());
        System.out.println("peak: "+stack.peek());
        System.out.println("pop: "+stack.pop());
        System.out.println("pop: "+stack.pop());
    }
}
