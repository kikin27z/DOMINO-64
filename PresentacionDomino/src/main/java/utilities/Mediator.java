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
public class Mediator implements IMediator{
    private StartModel startModel;
    private LobbyModel chooseMatchModel; 
    
    public Mediator(StartModel startModel, LobbyModel chooseMatchModel){
        this.startModel = startModel;
        this.chooseMatchModel = chooseMatchModel;
    }
    
    @Override
    public void changeView(String newView) {
        
    }
    
}
