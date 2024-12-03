package manejador;

import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Cuenta;
import entidades.Jugador;
import entidades.Lobby;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import entidadesDTO.UnirseDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author karim
 */
public class ManejadorLobby {
    private final AdaptadorEntidad adaptador;
    private final AdaptadorDTO adaptadorDTO;
    private Lobby lobby;
    private Partida partida;
    
    public ManejadorLobby() {
        adaptador = new AdaptadorEntidad();
        adaptadorDTO = new AdaptadorDTO();
    }
    
    public boolean validarCambioAvatar(CuentaDTO cuenta){
        return true;
    }
    
    public CuentaDTO unirCuenta(CuentaDTO cuentaDTO){
        Cuenta cuenta = adaptadorDTO.adaptarCuentaDTO(cuentaDTO);
        cuenta = lobby.agregarCuenta(cuenta);
        lobby.removerJugadorListo(cuenta);
        CuentaDTO aux = adaptador.adaptarEntidadCuenta(cuenta);
        return aux;
    }
    public void iniciarLobby(){
        this.lobby =  new Lobby();
        this.partida = new Partida(lobby.getCodigoPartida());
    }

    public LobbyDTO devolverLobby(){
        LobbyDTO dto = adaptador.adaptarEntidadLobby(lobby);
        return dto;
    }
    
    public String verificacionCodigo(UnirseDTO unirse){
        String mensaje = null; 
        System.out.println("Verificacion de codigos");
        System.out.println(lobby.getCodigoPartida());
        System.out.println(unirse.getCodigoPartida());
        
        if(lobby == null){
            return "No hay partidas activas";
        }
        if(!unirse.getCodigoPartida().equals(this.lobby.getCodigoPartida())){
            mensaje = "El codigo de partida es incorrecto";
        }
        
        if(!lobbyLleno()){
            mensaje = "La partida ya esta llena";
        }
        return mensaje;
    }
    
    
    
    private boolean lobbyLleno(){
        return lobby.obtenerCuentas().size() < 4;
    }
    
    public void abandonoAdmin(){
        this.lobby = null;
    }
    
    public void abandonoCuenta(CuentaDTO cuentaDTO){
        Cuenta cuenta = adaptadorDTO.adaptarCuentaDTO(cuentaDTO);
        lobby.removerCuenta(cuenta);
    }
    
    public void agregarJugadorListo(CuentaDTO cuenta){
        lobby.agregarJugadorListo(adaptadorDTO.adaptarCuentaDTO(cuenta));
    }
    public void removerJugadorListo(CuentaDTO cuenta){
        lobby.removerJugadorListo(adaptadorDTO.adaptarCuentaDTO(cuenta));
    }
    
    public boolean todosListos(){
        return lobby.todosListos();
    }
    
    public int iniciarPartida(){
        List<Cuenta> cuentas = lobby.obtenerCuentas();
        List<Jugador> jugadores = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            jugadores.add(new Jugador(cuenta));
        }
        partida.setJugadores(jugadores);
        int idContexto = generarIdContextoPartida(partida);
        return idContexto;
    }
    
    public int generarIdContextoPartida(Partida partida) {
        String codigoPartida = partida.getCodigoPartida();
        String digitos = codigoPartida.replaceFirst("-", "");
        int idContextoPartida = Integer.parseInt(digitos);
        return idContextoPartida;
    }
}
