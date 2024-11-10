package inicio;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import presentacion_utilities.INavegacion;
import presentacion_utilities.Navegacion;
import presentacion_utilities.NotificadorPresentacion;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class InicioControl {
    
    private final NotificadorPresentacion notificador;
    private InicioModel modelo;
    private InicioView vista;
    
    public InicioControl(InicioModel modelo, InicioView vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.notificador = NotificadorPresentacion.getInstance();
        this.vista.setOnActionSolo(getHandler());//falta el setOnAction de cuando se juega en linea
    }

    public void irLobby(ActionEvent e) {
        System.out.println("metodo ir lobby");
    }
    /*
    crea el handler para el boton de jugar solo (contra la maquina)
    */
    public EventHandler<ActionEvent> getHandler() {
        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //accion asociada al boton de jugar solo
//                modelo.getAcciones().forEach(action->{
//                    action.ejecutarAccion();
//                });
            }
        };
        return handler;
    }
//    
}
