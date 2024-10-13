/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.starter;

import presentacion_utilities.NotificadorPresentacion;

/**
 *
 * @author luisa M
 */
public class Starter {

    public static void main(String[] args) {
        //NotificadorLogica notificador = NotificadorLogica.getInstance();
        NotificadorPresentacion notificador= NotificadorPresentacion.getInstance();
        Display display = new Display(notificador);
        display.iniciarJuego();
    }
}
