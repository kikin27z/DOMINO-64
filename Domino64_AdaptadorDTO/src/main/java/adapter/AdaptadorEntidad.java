/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter;

import entidades.Avatar;
import entidades.Cuenta;
import entidades.Ficha;
import entidades.Jugador;
import entidades.Lobby;
import entidades.Partida;
import entidadesDTO.AvatarDTO;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class AdaptadorEntidad {
    
    public LobbyDTO adaptarEntidadLobby(Lobby lobby){
        LobbyDTO dto = new LobbyDTO(lobby.getCodigoPartida());
        List<CuentaDTO> cuentas = adaptarCuentas(lobby.obtenerCuentas());
        dto.setCuentas(cuentas);
        return dto;
    }
    
    public PartidaDTO adaptarEntidadPartida(Partida partida){
        PartidaDTO dto = new PartidaDTO();
        dto.setCodigoPartida(partida.getCodigoPartida());
        if(partida.getFichasPorJugador() > 0){
            dto.setFichasPorJugador(partida.getFichasPorJugador());
        }
        if(partida.getJugadores() != null){
            List<JugadorDTO> jugadoresDTO = new ArrayList<>();
            for (CuentaDTO cuenta : adaptarCuentas(partida.getJugadores())) {
                jugadoresDTO.add(new JugadorDTO(cuenta));
            }
            dto.setJugadores(jugadoresDTO);
        }
        
        return dto;
    }
    
    public List<CuentaDTO> adaptarCuentas(List<Cuenta> cuentas){
        List<CuentaDTO> dtos = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            dtos.add(adaptarEntidadCuenta(cuenta));
        }
        return dtos;
    }
    
    public FichaDTO adaptarEntidadFicha(Ficha ficha){
        return new FichaDTO(ficha.getDerecha(), ficha.getIzquierda());
    }
    
    public CuentaDTO adaptarEntidadCuenta(Cuenta cuenta){
        CuentaDTO dto = new CuentaDTO();
        
        dto.setId(cuenta.getId());
        if(cuenta.getAvatar() != null){
            dto.setAvatar(adaptarAvatar(cuenta.getAvatar()));
        }
        dto.setIdCadena(cuenta.getIdCadena());
        
        return dto;
    } 
    
    public AvatarDTO adaptarAvatar(Avatar avatar){
        AvatarDTO avatarDTO = null;
        switch(avatar){
            case Avatar.AVE-> avatarDTO = AvatarDTO.AVE;
            case Avatar.GATO-> avatarDTO = AvatarDTO.GATO;
            case Avatar.JAGUAR-> avatarDTO = AvatarDTO.JAGUAR;
            case Avatar.KIWI-> avatarDTO = AvatarDTO.KIWI;
            case Avatar.MARIPOSA-> avatarDTO = AvatarDTO.MARIPOSA;
            case Avatar.PANDA-> avatarDTO = AvatarDTO.PANDA;
            case Avatar.SERPIENTE-> avatarDTO = AvatarDTO.SERPIENTE;
            case Avatar.TORTUGA-> avatarDTO = AvatarDTO.TORTUGA;
            case Avatar.VENADO-> avatarDTO = AvatarDTO.VENADO;
        }
        return avatarDTO;
    }
    
}
