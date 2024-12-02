package eventos;


import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import java.util.List;
import tiposLogicos.TipoLogicaLobby;

/**
 * Clase que representa los eventos que puede generar el Lobby.
 * La entidad que maneja es la entidad Cuenta, debido a que
 * en el lobby solo se conocen las cuentas de los jugadores.
 * A este evento se le puede agregar la lobby en la cual
 * esta el contexto (la partida donde esta el jugador)
 * Este evento puede ser de cualquier tipo definido en el
 * enum TipoLogicaLobby
 * @author luisa M
 * @author karim F
 */
public class EventoLobby extends EventoLogico {
    private LobbyDTO lobby;
    private TipoLogicaLobby tipo;
    private CuentaDTO publicador;
    private List<CuentaDTO> jugadores;
    
    public EventoLobby(){}
    
    public EventoLobby(TipoLogicaLobby tipo) {
        super();
        this.tipo = tipo;
    }
    
    public void agregarLobby(LobbyDTO lobby){
        this.lobby = lobby;
    }
    
    public LobbyDTO obtenerLobby(){
        return lobby;
    }
    
    public void setPublicador(CuentaDTO cuenta){
        publicador = cuenta;
    }
    
    public CuentaDTO getPublicador(){
        return publicador;
    }
    
    public void setJugadores(List<CuentaDTO> jugadores){
        this.jugadores = jugadores;
    }
    
    public List<CuentaDTO> getJugadores() {
        return jugadores;
    }

    @Override
    public TipoLogicaLobby getTipo() {
        return tipo;
    }

    public void setTipo(TipoLogicaLobby tipo) {
        this.tipo = tipo;
    }

}
