package eventos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import tiposLogicos.TiposJugador;


/**
 * Clase que representa los eventos que puede generar el jugador.
 * La entidad que maneja es la entidad Ficha, debido a que
 * el contexto que puede dar un jugador es la ficha que coloco.
 * A este evento se le puede agregar la partida en la cual
 * esta el contexto (la partida donde esta el jugador). 
 * Asi como se le puede agregar el jugador que genero el evento
 * Este evento puede ser de cualquier tipo definido en el
 * enum TiposJugador
 * @author luisa M
 * @author karim F
 */
public class EventoJugador extends EventoLogico{
    private Object context;
    private TiposJugador tipo;
    private LobbyDTO lobby;
    private CuentaDTO cuenta;
    private PartidaDTO partida;
    
    public EventoJugador(){}
    
    public EventoJugador(TiposJugador tipo){
        this.tipo = tipo;
    }
    
    public void setTipo(TiposJugador tipo){
        this.tipo = tipo;
    }
    
    public CuentaDTO getJugador(){
        return cuenta;
    }
    
    public void setJugador(CuentaDTO jugador){
        this.cuenta = jugador;
    }
    
    public LobbyDTO getLobby() {
        return lobby;
    }

    public void setLobby(LobbyDTO lobby) {
        this.lobby = lobby;
    }
    
    @Override
    public void agregarInfo(Object contexto) {
        this.context = contexto;
    }

    @Override
    public TiposJugador getTipo() {
        return tipo;
    }

    @Override
    public Object getInfo() {
        return context;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{contexto= ").append(context);
        sb.append(", jugador= ").append(cuenta);
        sb.append(", tipo= ").append(tipo.toString());
        sb.append('}');
        return sb.toString();
    }    
}