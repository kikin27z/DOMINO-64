package utilities;

import builder.EventBuilder;
import entidades.Cuenta;
import entidades.Ficha;
import entidadesDTO.PartidaDTO;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import eventos.EventoJugador;
import tiposLogicos.TiposJugador;

/**
 * Clase constructora de eventos de jugador. Este builder lo va a usar el
 * director del manejador de cuenta para facilitar y encapsular la logica de
 * construccion de eventos del jugador
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class BuilderEventoJugador implements EventBuilder {

    private EventoJugador evento;

    public BuilderEventoJugador() {
        evento = new EventoJugador();
    }

    public void setPartida(PartidaDTO partida) {
        evento.setPartida(partida);
    }
    
    public void setLobbyDTO(LobbyDTO lobby){
        evento.setLobby(lobby);
    }

    public void setPublicador(CuentaDTO jugador) {
        evento.setJugador(jugador);
    }

    @Override
    public void setTipo(Enum tipo) {
        evento.setTipo((TiposJugador) tipo);
    }

    @Override
    public void setIdPublicador(int idPublicador) {
        evento.setIdPublicador(idPublicador);
    }

    @Override
    public void setContexto(Object info) {
        evento.agregarInfo(info);
    }

    @Override
    public EventoJugador construirEvento() {
        EventoJugador resultado = evento;
        reiniciar();
        return resultado;
    }

    @Override
    public void reiniciar() {
        evento = new EventoJugador();
    }

}
