/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion_utilities;

import observer.Observable;

/**
 *
 * @author luisa M
 */
public class NotificadorPresentacion {
    private static NotificadorPresentacion notificador;
//    private LobbyModel lobbyModel;
//    private InicioModel inicioModel;
//    private PartidaModel partidaModel;
    
    private NotificadorPresentacion(){
        
    }
    
    public static synchronized NotificadorPresentacion getInstance(){
        if(notificador == null)
            notificador = new NotificadorPresentacion();
        return notificador;
    }
    
//    public void notificarEvento(EventoMVC evento){
//        this.notifyObservers(evento.getTipo(), evento);
//       //this.notifyObservers(this);
//    }
}
