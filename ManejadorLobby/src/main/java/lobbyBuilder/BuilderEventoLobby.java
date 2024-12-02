package lobbyBuilder;

import builder.EventBuilder;
import entidades.Cuenta;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import eventos.EventoLobby;
import java.util.List;
import tiposLogicos.TipoLogicaLobby;

/**
 *
 * @author luisa M
 */
public class BuilderEventoLobby implements EventBuilder{
    private EventoLobby evento;
    
    public BuilderEventoLobby(){
        evento = new EventoLobby();
    }
    
    @Override
    public void setTipo(Enum tipo) {
        this.evento.setTipo((TipoLogicaLobby)tipo);
    }

    @Override
    public void setIdPublicador(int idPublicador) {
        this.evento.setIdPublicador(idPublicador);
    }

    @Override
    public void setIdContexto(int idContexto) {
        this.evento.setIdContexto(idContexto);
    }

    public void setContexto(List<CuentaDTO> jugadores) {
        this.evento.setJugadores(jugadores);
    }

    public void setLobby(LobbyDTO lobby){
        this.evento.agregarLobby(lobby);
    }
    
    public void setPublicador(CuentaDTO publicador){
        this.evento.setPublicador(publicador);
    }
    
    @Override
    public EventoLobby construirEvento() {
        EventoLobby resultado = evento;
        reiniciar();
        return resultado;
    }

    @Override
    public void reiniciar() {
        evento = new EventoLobby();
    }
    
}
