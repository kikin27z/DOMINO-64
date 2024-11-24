/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.domino64.manejador;

/**
 *
 * @author luisa M
 */
public class ProcesadorEventos {
    
    public static void main(String args[]){
        String codigoPartida = "123-456";
        String digitos = codigoPartida.replaceFirst("-", "");
//        String primerosDigitos = codigoPartida.substring(0, 3);
//        String ultimosDigitos = codigoPartida.substring(4);
        int idContextoPartida = Integer.parseInt(digitos);
        System.out.println("resultado = "+idContextoPartida);
        System.out.println("resultado = "+(idContextoPartida+1));
    }
}
