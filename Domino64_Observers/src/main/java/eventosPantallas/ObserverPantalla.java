package eventosPantallas;

import entidadesDTO.LobbyDTO;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObserverPantalla {
    public void avisarMostrarInicio();
    public void avisarMostrarCreditos();
    public void avisarMostrarPartida();
    public void avisarMostrarLobby(LobbyDTO partida);
    public void avisarMostrarFinJuego();
    public void avisarMostrarOpcionesPartida();
}
