/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lobbyMVC;

import utilities.Observable;

/**
 *
 * @author luiis
 */
public class LobbyModel extends Observable{
    private boolean visible;
    
    public void setVisible(boolean flag){
        visible = flag;
        this.notifyObservers(flag);
    }
}
