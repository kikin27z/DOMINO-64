package inicio;

import presentacion_utilities.NotificadorEvento;

/**
 * La clase InicioModel representa el modelo en el patrón MVC (Modelo-Vista-Controlador) 
 * para la pantalla de inicio del juego de dominó. Su función es gestionar la lógica 
 * del juego cuando se selecciona uno de los modos (Offline u Online).
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class InicioModel{
    private final NotificadorEvento notificador;
    public InicioModel() {
        notificador = NotificadorEvento.getInstance();
    }


    public void avisarModoJugar() {
        notificador.avisarMostrarOpcionesPartida();
    }
    public void avisarMostrarCreditos(){
        
    }
}
