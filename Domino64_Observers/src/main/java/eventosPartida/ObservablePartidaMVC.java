/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eventosPartida;


/**
 *
 * @author karim
 */
public abstract class ObservablePartidaMVC {
    private ObserverPartidaMVC observer;

    public void agregarObserver(ObserverPartidaMVC observer){
        this.observer = observer;
    }
    
    public void notificarJugadorSalio(){
    }
}
