package lobbyBuilder;

import builder.EventBuilder;
import entidades.Cuenta;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.PartidaDTO;
import eventos.EventoLobby;
import tiposLogicos.TipoLogicaLobby;

/**
 *
 * @author luisa M
 */
public class BuilderEventoLobby implements EventBuilder<CuentaDTO>{
    private EventoLobby evento;
    
    public BuilderEventoLobby(){
        evento = new EventoLobby();
    }
    
    @Override
    public void setTipo(Enum<?> tipo) {
        this.evento.setTipo((TipoLogicaLobby)tipo);
    }

    @Override
    public void setIdPublicador(int idPublicador) {
        this.evento.setIdPublicador(idPublicador);
    }

    @Override
    public void setContexto(CuentaDTO info) {
        this.evento.agregarInfo(info);
    }

    public void agregarPartida(PartidaDTO partida){
        this.evento.agregarPartida(partida);
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
