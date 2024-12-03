package eventos;


import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.ReglasDTO;
import tiposLogicos.TipoLogicaLobby;

/**
 * Clase que representa los eventos que puede generar el Lobby.
 * La entidad que maneja es la entidad Cuenta, debido a que
 * en el lobby solo se conocen las cuentas de los jugadores.
 * A este evento se le puede agregar la lobby en la cual
 * esta el contexto (la partida donde esta el jugador)
 * Este evento puede ser de cualquier tipo definido en el
 * enum TipoLogicaLobby
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class EventoLobby extends EventoLogico {
    private LobbyDTO lobby;
    private TipoLogicaLobby tipo;
    private Object contexto;
    private CuentaDTO publicador;
    private ReglasDTO reglas;
    
    public EventoLobby(){}
    
    public EventoLobby(TipoLogicaLobby tipo) {
        super();
        this.tipo = tipo;
    }

    public ReglasDTO getReglas() {
        return reglas;
    }

    public void setReglas(ReglasDTO reglas) {
        this.reglas = reglas;
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
    
//    @Override
//    public void agregarInfo(Object info) {
//        contexto = info;
//    }

    @Override
    public TipoLogicaLobby getTipo() {
        return tipo;
    }

    public void setTipo(TipoLogicaLobby tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EventoLobby{");
        sb.append("lobby=").append(lobby);
        sb.append(", tipo=").append(tipo);
        sb.append(", idPublicador=").append(idPublicador);
        sb.append(", idDestinatario=").append(idDestinatario);
        sb.append(", cuenta=").append(publicador);
        sb.append('}');
        return sb.toString();
    }

    
}
