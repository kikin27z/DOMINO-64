package presentacion_dibujo;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaValidaDTO;
import entidadesDTO.PosicionDTO;
import java.util.ArrayDeque;
import java.util.Deque;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author karim
 */
public class DibujoTablero2 extends AnchorPane {

    private  EventHandler<MouseEvent> opcionJugada;
    private double[][] mapa = new double[60][64];
    

    public DibujoTablero2(EventHandler<MouseEvent> opcionJugada) {
    }

}
