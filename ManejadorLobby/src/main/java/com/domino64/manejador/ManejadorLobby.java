package com.domino64.manejador;

import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Cuenta;
import entidades.Lobby;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.UnirseDTO;

/**
 *
 * @author karim
 */
public class ManejadorLobby {
    private final AdaptadorEntidad adaptador;
    private final AdaptadorDTO adaptadorDTO;
    private Lobby lobby;
    
    public ManejadorLobby() {
        adaptador = new AdaptadorEntidad();
        adaptadorDTO = new AdaptadorDTO();
    }
    
    public CuentaDTO unirCuenta(CuentaDTO cuentaDTO){
        Cuenta cuenta = adaptadorDTO.adaptarCuentaDTO(cuentaDTO);
        cuenta = lobby.agregarCuenta(cuenta);
        CuentaDTO aux = adaptador.adaptarEntidadCuenta(cuenta);
        return aux;
    }
    public void iniciarLobby(){
        this.lobby =  new Lobby();
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
}
