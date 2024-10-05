/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import lobbyMVC.LobbyModel;
import startMVC.StartModel;

/**
 *
 * @author luiis
 */
public class Mediator  {
    private static StartModel startModel;
    private static LobbyModel lobbyModel; 
    
    public Mediator(StartModel startModel, LobbyModel lobbyModel){
        startModel = startModel;
        lobbyModel = lobbyModel;
    }
    
    public static void changeToLobbyView() {
        
    }

    public static void changeToStartView() {
        startModel.changeView(true);
    }
    
}
