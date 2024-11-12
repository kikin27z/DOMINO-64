package eventosOpcionesPartida;

/**
 * Interfaz que define el método que debe implementar cualquier clase que desee ser un observador de las opciones en el MVC (Modelo-Vista-Controlador).
 * Los observadores son notificados sobre los cambios en las opciones de la partida, como la actualización de mensajes de aviso.
 * 
 * Este tipo de observador se utiliza para reaccionar a cambios en los componentes del sistema que gestionan las opciones de la partida,
 * como un mensaje que necesita ser mostrado o actualizado en la interfaz de usuario.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObserverOpcionesMVC {

    /**
     * Actualiza el mensaje de aviso en el observador.
     * Este método es llamado cuando el objeto observable desea notificar al observador
     * sobre la actualización de un mensaje relacionado con las opciones de la partida.
     * 
     * @param mensaje el mensaje de aviso que debe ser mostrado o actualizado.
     */
    void actualizarMensajeAviso(String mensaje);
}
