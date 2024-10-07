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
        modelo.setMapeoFichas(view.addTile(getEventHandler()));
        colocarPrimeraMula();
    }

    private void colocarPrimeraMula(){
        if (modelo.obtenerPrimeraMulaTablero() != null) {
            Ficha mula = modelo.obtenerPrimeraMulaTablero();
            Canvas mulaDibujo = view.dibujarPrimeraMula(mula.getIzquierda(), mula.getDerecha(), 500, 600);
            Map.Entry<Canvas, Ficha> entry = Map.entry(mulaDibujo, mula);
            modelo.actualizarMapeoFichasJugadas(entry);
        }
    }
    
    private void colocarFicha(ActionEvent e){
        modelo.colocarFicha();
    }
    
    private void saludar(ActionEvent e){
        //view.addTile();
        //view.agregarDominoMazo(3, 3, null);
    }
    
    public void seleccionFicha(MouseEvent event) {
        System.out.println("Botón clickeado: " + event.getSource());
        
    }
    
    private EventHandler<MouseEvent> getEventHandler(){
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Canvas canvas = (Canvas)t.getSource();
                Ficha fichaSeleccionada = modelo.getFichaSeleccionada(canvas);
                modelo.getJugador().setFichaSeleccionada(fichaSeleccionada);
            }
        };
        return event;
    }
    
    private void dibujarTablero(){
        
    }
}
