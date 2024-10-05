package partida;

import javafx.event.ActionEvent;
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
        
        view.btnEjemploEvento(this::saludar);
    }

    @Override
    public void accionarEvento(ActionEvent e) {
        
    }
    
    private void saludar(ActionEvent e){
        System.out.println("Hola");
    }
}
