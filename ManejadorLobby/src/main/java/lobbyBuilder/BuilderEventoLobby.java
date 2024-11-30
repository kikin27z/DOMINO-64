package lobbyBuilder;

import builder.EventBuilder;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import eventos.EventoLobby;
import tiposLogicos.TipoLogicaLobby;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class BuilderEventoLobby implements EventBuilder<TipoLogicaLobby>{
    private EventoLobby evento;
    
    public BuilderEventoLobby(){
        evento = new EventoLobby();
    }
    
    @Override
    public void setTipo(TipoLogicaLobby tipo) {
        this.evento.setTipo(tipo);
    }

    @Override
    public void setIdPublicador(int idPublicador) {
        this.evento.setIdPublicador(idPublicador);
    }

    @Override
    public void setIdContexto(int idContexto) {
        this.evento.setIdContexto(idContexto);
    }

    public void setCuenta(CuentaDTO info) {
        this.evento.setPublicador(info);
    }

    public void agregarLobby(LobbyDTO lobby){
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
