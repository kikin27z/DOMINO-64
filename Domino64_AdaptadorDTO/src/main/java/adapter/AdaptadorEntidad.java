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
        LobbyDTO dto = new LobbyDTO();
        List<CuentaDTO> cuentas = adaptarCuentas(lobby.obtenerCuentas());
        dto.setCuentas(cuentas);
        dto.setCodigo(lobby.getCodigoPartida());
        return dto;
    }
    
    public PartidaDTO adaptarEntidadPartida(Partida partida){
        PartidaDTO dto = new PartidaDTO();
        dto.setCodigoPartida(partida.getCodigoPartida());
        if(partida.getFichasPorJugador() > 0){
            dto.setFichasPorJugador(partida.getFichasPorJugador());
        }
        if(partida.getJugadores() != null){
            dto.setJugadores(adaptarJugadores(partida.getJugadores()));
        }
        
        return dto;
    }
    
    public List<JugadorDTO> adaptarJugadores(List<Jugador> jugadores){
        List<JugadorDTO> dtos = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            dtos.add(adaptarEntidadJugador(jugador));
        }
        return dtos;
    }
    
    public List<CuentaDTO> adaptarCuentas(List<Cuenta> cuentas){
        List<CuentaDTO> dtos = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            dtos.add(adaptarEntidadCuenta(cuenta));
        }
        return dtos;
    }
    
    public FichaDTO adaptarEntidadFicha(Ficha ficha){
        return new FichaDTO(ficha.getIzquierda(), ficha.getDerecha());
    }
    
    public List<FichaDTO> adaptarEntidadesFicha(List<Ficha> fichas){
        List<FichaDTO> fichasDTO = new ArrayList<>();
        for (Ficha ficha : fichas) {
            fichasDTO.add(adaptarEntidadFicha(ficha));
        }
        return fichasDTO;
    }
    
    public JugadorDTO adaptarEntidadJugador(Jugador jugador){
        JugadorDTO dto = new JugadorDTO(adaptarEntidadCuenta(jugador.getCuenta()));
        
        if(jugador.getFichas() != null){
            dto.setFichas(adaptarEntidadesFicha(jugador.getFichas()));
        }
        
        return dto;
    } 
    
    
    public CuentaDTO adaptarEntidadCuenta(Cuenta cuenta){
        CuentaDTO dto = new CuentaDTO();
        
        if(cuenta.getAvatar() != null){
            dto.setAvatar(adaptarAvatar(cuenta.getAvatar()));
        }
        if(cuenta.esAdmin()){
            dto.setAdmin(true);
        }else{
            dto.setAdmin(false);
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
