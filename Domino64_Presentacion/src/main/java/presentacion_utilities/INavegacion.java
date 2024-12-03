package presentacion_utilities;

import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;

/**
 * Interfaz que define los métodos para la navegación entre diferentes vistas
 * de la aplicación. Esta interfaz establece un contrato para las clases que
 * implementan la navegación, asegurando que todas las vistas tendrán métodos
 * para iniciar la aplicación y cambiar entre diferentes escenas.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface INavegacion {

    /**
     * Método para iniciar la aplicación.
     * Este método debería configurar la escena inicial y mostrar la vista principal.
     */
    public void iniciarApp();
    public void iniciarAppPruebas();

    /**
     * Método para cambiar la vista a la pantalla de inicio.
     * Este método debería gestionar la transición de la vista actual a la vista de inicio.
     */
    public void cambiarInicio();

    /**
     * Método para cambiar la vista a la pantalla de lobby.
     * Este método debería gestionar la transición de la vista actual a la vista del lobby.
     * @param cuenta
     * @param lobby
     */
    public void cambiarLobby(CuentaDTO cuenta, LobbyDTO lobby);

    /**
     * Método para cambiar la vista a la pantalla de partida.
     * Este método debería gestionar la transición de la vista actual a la vista de partida.
     */
    public void cambiarPartida();
    
    /**
     * Método para cambiar la vista a la pantalla de opciones de partida.
     * Este método debería gestionar la transición de la vista actual a la vista de opciones de partida.
     */
    public void cambiarOpcionesPartida();
    
    public void cambiarCreditos();
}
