package lobbyBuilder;

import builder.EventBuilder;
import entidades.Cuenta;
import entidades.Partida;
import eventos.EventoLobby;
import tiposLogicos.TipoLogicaLobby;

/**
 *
 * @author luisa M
 */
public class BuilderEventoLobby implements EventBuilder<Cuenta>{
    private EventoLobby evento;
    
    @Override
    public void setTipo(Enum<?> tipo) {
        this.evento.setTipo((TipoLogicaLobby)tipo);
    }

    @Override
    public void setIdPublicador(int idPublicador) {
        this.evento.setIdPublicador(idPublicador);
    }

    @Override
    public void setInfo(Cuenta info) {
        this.evento.agregarInfo(info);
    }

    public void agregarPartida(Partida partida){
        this.evento.agregarPartida(partida);
    }
    
    public void setPublicador(Cuenta publicador){
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
