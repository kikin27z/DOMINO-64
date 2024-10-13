package partida;

//import entidades.Ficha;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import java.util.Map;
import javafx.application.Platform;
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
        System.out.println("modelo en control");
        System.out.println(modelo.getJugador());
        System.out.println(modelo.getPartida().getJugadores());
        modelo.setMapeoFichas(view.addTile(modelo.getEventHandler()));
        System.out.println("se pusiseron las fichas");
        colocarPrimeraMula();
    }

    private void colocarPrimeraMula(){
        if (modelo.obtenerPrimeraMulaTablero() != null) {
            FichaDTO mula = modelo.obtenerPrimeraMulaTablero();
            System.out.println("primera mula: "+mula);
            Canvas mulaDibujo = view.dibujarPrimeraMula(mula.getIzquierda(), mula.getDerecha(), 500, 600);
            Map.Entry<Canvas, FichaDTO> entry = Map.entry(mulaDibujo, mula);
            modelo.actualizarMapeoFichasJugadas(entry);
            System.out.println("fichas jugadas: "+modelo.getMapeoFichasJugadas());
        }
    }
    
    private EventHandler<MouseEvent> getEventHandler() {
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Canvas canvas = (Canvas) t.getSource();
                FichaDTO fichaSeleccionada = modelo.getFichaSeleccionada(canvas);
                System.out.println("ficha selec: "+fichaSeleccionada);
                modelo.setFichaSeleccionada(fichaSeleccionada);
//                Platform.runLater(()->{
//                    view.iluminarFicha(canvas);
//                });
            }
        };
        return event;
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
    
}
