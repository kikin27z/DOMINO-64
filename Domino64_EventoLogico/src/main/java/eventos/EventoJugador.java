package eventos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import entidadesDTO.ReglasDTO;
import entidadesDTO.UnirseDTO;
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
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class EventoJugador extends EventoLogico{
    private TiposJugador tipo;
    private LobbyDTO lobby;
    private JugadorDTO jugador;
    private CuentaDTO cuenta;
    private UnirseDTO unirse;
    private ReglasDTO reglas;
    
    public EventoJugador(){
        this.jugador = new JugadorDTO();
    }
    
    public EventoJugador(TiposJugador tipo){
        this.tipo = tipo;
    }
    
    public void setTipo(TiposJugador tipo){
        this.tipo = tipo;
    }
    
    public JugadorDTO getJugador(){
        return jugador;
    }
    
    public void setJugador(JugadorDTO jugador){
        this.jugador = jugador;
    }
    
    public CuentaDTO getCuenta(){
        return cuenta;
    }
    
    public void setCuenta(CuentaDTO cuenta){
        this.cuenta = cuenta;
    }
    
    public LobbyDTO getLobby() {
        return lobby;
    }

    public void setLobby(LobbyDTO lobby) {
        this.lobby = lobby;
    }

    public UnirseDTO getUnirse() {
        return unirse;
    }

    public void setUnirse(UnirseDTO unirse) {
        this.unirse = unirse;
    }

    public ReglasDTO getReglas() {
        return reglas;
    }

    public void setReglas(ReglasDTO reglas) {
        this.reglas = reglas;
    }
    
    @Override
    public TiposJugador getTipo() {
        return tipo;
    }


    @Override
    public String toString() {
        return "EventoJugador{" + "tipo=" + tipo + ", lobby=" + lobby + ", cuenta=" + cuenta + '}';
    }

    
}
