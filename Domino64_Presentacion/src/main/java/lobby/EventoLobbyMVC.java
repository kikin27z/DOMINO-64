/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lobby;

import eventoBase.Evento;
import javafx.scene.Node;


/**
 *
 * @author Luisa Morales
 */
public class EventoLobbyMVC implements Evento<TipoLobbyMVC>{
    private TipoLobbyMVC tipo;
    private Node elemento;
    
    public EventoLobbyMVC(TipoLobbyMVC tipo){
        this.tipo = tipo;
    }
    
    public void setTipo(TipoLobbyMVC tipo){
        this.tipo = tipo;
    }
    
    public void setNodo(Node nodo){
        this.elemento = nodo;
    }
    
    @Override
    public TipoLobbyMVC getTipo() {
        return tipo;
    }

    public Node getElemento() {
        return elemento;
    }

    @Override
    public int getIdPublicador() {
        return 0;
    }


    @Override
    public int getIdContexto() {
        return 0;
    }
    
}
