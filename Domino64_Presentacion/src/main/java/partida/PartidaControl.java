package partida;

import entidades.Ficha;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaControl  {
    private PartidaView view;
    private PartidaModel modelo;

    public PartidaControl(PartidaView view, PartidaModel modelo) {
        this.view = view;
        this.modelo = modelo;
        //agrega las fichas, pasandole un eventHandler que se encargara
        //de manejar los eventos de cuando la ficha sea seleccionada
        modelo.setMapeoFichas(view.addTile(this::seleccionFicha));
    }

    private void colocarFicha(ActionEvent e){
        modelo.colocarFicha();
    }
    
    private void saludar(ActionEvent e){
        //view.addTile();
        view.agregarDominoMazo(3, 3, null);
    }
    
    public void seleccionFicha(MouseEvent event) {
        System.out.println("Botón clickeado: " + event.getSource());
    }
    

    
    private void dibujarTablero(){
        
    }
}
