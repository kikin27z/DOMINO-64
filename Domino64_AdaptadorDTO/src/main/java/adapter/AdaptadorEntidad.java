/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter;

import entidades.Cuenta;
import entidades.Ficha;
import entidades.Jugador;
import entidades.Partida;
import entidadesDTO.AvatarDTO;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class AdaptadorEntidad {
    
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
        if(cuenta.getAvatarUrl() != null){
            dto.setAvatar(adaptarAvatar(cuenta.getAvatarUrl()));
        }
        dto.setIdCadena(cuenta.getIdCadena());
        dto.setUsername(cuenta.getUsername());
        
        return dto;
    } 
    
    public AvatarDTO adaptarAvatar(String nombreAvatar){
        AvatarDTO avatar = null;
        switch(nombreAvatar){
            case "AVE"-> avatar = AvatarDTO.AVE;
            case "GATO"-> avatar = AvatarDTO.GATO;
            case "JAGUAR"-> avatar = AvatarDTO.JAGUAR;
            case "KIWI"-> avatar = AvatarDTO.KIWI;
            case "MARIPOSA"-> avatar = AvatarDTO.MARIPOSA;
            case "PANDA"-> avatar = AvatarDTO.PANDA;
            case "SERPIENTE"-> avatar = AvatarDTO.SERPIENTE;
            case "TORTUGA"-> avatar = AvatarDTO.TORTUGA;
            case "VENADO"-> avatar = AvatarDTO.VENADO;
        }
        return avatar;
    }
    
}
