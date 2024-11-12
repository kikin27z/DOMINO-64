package eventosInicio;

/**
 * Interfaz que define los métodos que deben implementar los observadores de la vista de inicio.
 * Esta interfaz permite que los observadores reaccionen a las acciones realizadas en la vista de inicio,
 * como el inicio de un juego o la visualización de los créditos.
 * 
 * Los observadores que implementen esta interfaz recibirán las notificaciones relacionadas
 * con el modo de juego y los créditos para ejecutar las acciones correspondientes en el modelo o en la vista.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObserverInicio {

    /**
     * Notifica que el usuario ha elegido iniciar el modo de juego.
     * Este método se invoca cuando el usuario decide comenzar una partida.
     */
    void avisarModoJugar();

    /**
     * Notifica que el usuario ha elegido mostrar los créditos.
     * Este método se invoca cuando el usuario desea ver la información de créditos del juego.
     */
    void avisarMostrarCreditos();
}
