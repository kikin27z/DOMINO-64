package eventos;


import entidadesDTO.CuentaDTO;
import entidadesDTO.PartidaDTO;
import tiposLogicos.TipoLogicaLobby;

/**
 * Clase que representa los eventos que puede generar el Lobby.
 * La entidad que maneja es la entidad Cuenta, debido a que
 * en el lobby solo se conocen las cuentas de los jugadores.
 * A este evento se le puede agregar la partida en la cual
 * esta el contexto (la partida donde esta el jugador)
 * Este evento puede ser de cualquier tipo definido en el
 * enum TipoLogicaLobby
 * @author luisa M
 * @author karim F
 */
public class EventoLobby extends EventoLogico {
    private PartidaDTO partida;
    private TipoLogicaLobby tipo;
    private Object contexto;
    private CuentaDTO publicador;
    
    public EventoLobby(){}
    
    public EventoLobby(TipoLogicaLobby tipo) {
        super();
        this.tipo = tipo;
    }
    
    public void agregarPartida(PartidaDTO partida){
        this.partida = partida;
    }
    
    public PartidaDTO obtenerPartida(){
        return partida;
    }
    
    public void setPublicador(CuentaDTO cuenta){
        publicador = cuenta;
    }
    
    public CuentaDTO getPublicador(){
        return publicador;
    }
    
    @Override
    public void agregarInfo(Object info) {
        contexto = info;
    }

    @Override
    public Object getInfo() {
        return contexto;
    }

    @Override
    public TipoLogicaLobby getTipo() {
        return tipo;
    }

    public void setTipo(TipoLogicaLobby tipo) {
        this.tipo = tipo;
    }

}
