package partida;

import entidades.Ficha;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utilities.IEventoConcreto;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaControl implements IEventoConcreto {
    private PartidaView view;
    private PartidaModel modelo;

    public PartidaControl(PartidaView view, PartidaModel modelo) {
        this.view = view;
        this.modelo = modelo;
        //agrega las fichas, pasandole un eventHandler que se encargara
        //de manejar los eventos de cuando la ficha sea seleccionada
        Map<Ficha,ImageView> mapeo=view.addTile(establecerManejador());
        modelo.actualizarMapeo(mapeo);
        view.btnEjemploEvento(this::saludar);
    }

    private void colocarFicha(ActionEvent e){
        modelo.colocarFicha();
    }
    
    
    private EventHandler<MouseEvent> establecerManejador(){
        EventHandler<MouseEvent> handler = 
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                System.out.println("source: " + (ImageView) t.getSource());
                ImageView imgViewSeleccionado = (ImageView) t.getSource();
                Ficha ficha = modelo.getFichaSeleccionada(imgViewSeleccionado);
                modelo.getJugador().setFichaSeleccionada(ficha);
                System.out.println("ficha seleccionada : " + ficha);
                
            }
        };
        return handler;
    }
    
    @Override
    public void accionarEvento(ActionEvent e) {
        
    }
    
    private void saludar(ActionEvent e){
        //view.addTile();
        EventHandler<MouseEvent> clic = seleccionFicha();
        view.agregarDominoMazo(3, 3, clic);
    }
    
    private EventHandler<MouseEvent> seleccionFicha(){
        return (MouseEvent event) -> {
            //Se verifica que sea turno del jugador
            //Valida que se pueda jugar
            
            //Se dibuja en el tablero
//            modelo.ponerFichaEnTablero(ficha);
        };
    }
    
    private void dibujarTablero(){
        
    }
}
