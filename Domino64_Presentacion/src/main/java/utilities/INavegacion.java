package utilities;

import partida.PartidaModel;

/**
 * Interfaz que define los métodos para la navegación entre diferentes vistas
 * de la aplicación. Esta interfaz establece un contrato para las clases que
 * implementan la navegación, asegurando que todas las vistas tendrán métodos
 * para iniciar la aplicación y cambiar entre diferentes escenas.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public interface INavegacion {
    
    /**
     * Inicia la aplicación lanzando la interfaz gráfica.
     */
    public void iniciarApp();

    /**
     * Cambia la vista a la pantalla de inicio.
     */
    public void cambiarInicio();

    /**
     * Cambia la vista a la pantalla del lobby.
     */
    public void cambiarLobby();

    /**
     * Cambia la vista a la pantalla de partida.
     */
    public void cambiarPartida();
    
    /**
     * Actualiza la vista de la partida.
     */
    public void actualizarPartida(PartidaModel modelo);
    
    //public void quitarFicha()
}
