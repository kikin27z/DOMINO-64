
package utilities;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author karim
 */
public class DisparadorEventos {
    private List<Node> nodos;
    private ActionEvent e;
    
    
    public void vincularEvento(Node nodo, EventHandler<MouseEvent> eventHandler) {
        nodo.setOnMouseClicked(eventHandler);
        nodos.add(nodo);
    }
    
    public void desvincularEvento(Node nodo){
        nodos.remove(nodo);
    }
    
}
