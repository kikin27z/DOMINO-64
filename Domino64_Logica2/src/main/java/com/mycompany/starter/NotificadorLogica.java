/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.starter;

import acciones.CambiarPantalla;
import patrones.command.Accion;
import patrones.observer.Observable;
import patrones.observer.Observer;
import presentacion_utilities.NotificadorPresentacion;

/**
 *
 * @author luisa M
 */
public class NotificadorLogica extends Observable<NotificadorLogica> 
        implements Observer<NotificadorPresentacion> {
    private static NotificadorLogica notificador;
    private Accion accion;
    
    private NotificadorLogica(){
        
    };
    
    public static synchronized NotificadorLogica getInstance() {
        if (notificador == null) {
            notificador = new NotificadorLogica();
        }
        return notificador;
    }
    
    @Override
    public void update(NotificadorPresentacion observable, Object ... context) {
        int tipoAccion = (int)context[0];
        System.out.println("tipo accion "+tipoAccion);
        if(tipoAccion == observable.CAMBIAR_PANTALLA){
            //CambiarPantalla ac= new CambiarPantalla();
//            String destinoStr =(String)context[1];
//            System.out.println("destino: "+destinoStr);
//            if(destinoStr.equals("lobby")){
//                int destino = ac.IR_LOBBY;
//                ac.setDestino(destino);
//            }
//            this.notifyObservers(this, accion);
        }else 
            System.out.println("quien sabe");
    }
    
}
