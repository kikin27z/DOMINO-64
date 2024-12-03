package adapter;

import entidades.Avatar;
import entidades.Cuenta;
import entidades.Ficha;
import entidades.Jugador;
import entidades.Lobby;
import entidadesDTO.AvatarDTO;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.LobbyDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de convertir entidades del modelo de negocio a objetos DTO (Data Transfer Objects).
 * Facilita la transferencia de datos entre capas sin exponer directamente las entidades del negocio.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class AdaptadorEntidad { 
    /**
     * Convierte una entidad Lobby a su correspondiente DTO.
     * 
     * @param lobby la entidad Lobby a convertir.
     * @return un objeto de tipo LobbyDTO.
     */
    public LobbyDTO adaptarEntidadLobby(Lobby lobby){
        LobbyDTO dto = new LobbyDTO();
        List<CuentaDTO> cuentas = adaptarCuentas(lobby.obtenerCuentas());
        dto.setCuentas(cuentas);
        dto.setCodigo(lobby.getCodigoPartida());
        return dto;
    }
    
    /**
     * Convierte una lista de entidades Jugador a una lista de objetos JugadorDTO.
     * 
     * @param jugadores la lista de entidades Jugador a convertir.
     * @return una lista de objetos JugadorDTO.
     */
    public List<JugadorDTO> adaptarJugadores(List<Jugador> jugadores){
        List<JugadorDTO> dtos = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            dtos.add(adaptarEntidadJugador(jugador));
        }
        return dtos;
    }
    
    /**
     * Convierte una lista de entidades Cuenta a una lista de objetos CuentaDTO.
     * 
     * @param cuentas la lista de entidades Cuenta a convertir.
     * @return una lista de objetos CuentaDTO.
     */
    public List<CuentaDTO> adaptarCuentas(List<Cuenta> cuentas){
        List<CuentaDTO> dtos = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            dtos.add(adaptarEntidadCuenta(cuenta));
        }
        return dtos;
    }
    
    /**
     * Convierte una entidad Ficha a su correspondiente DTO.
     * 
     * @param ficha la entidad Ficha a convertir.
     * @return un objeto de tipo FichaDTO.
     */
    public FichaDTO adaptarEntidadFicha(Ficha ficha){
        return new FichaDTO(ficha.getIzquierda(), ficha.getDerecha());
    }
    
    /**
     * Convierte una lista de entidades Ficha a una lista de objetos FichaDTO.
     * 
     * @param fichas la lista de entidades Ficha a convertir.
     * @return una lista de objetos FichaDTO.
     */
    public List<FichaDTO> adaptarEntidadesFicha(List<Ficha> fichas){
        List<FichaDTO> fichasDTO = new ArrayList<>();
        for (Ficha ficha : fichas) {
            fichasDTO.add(adaptarEntidadFicha(ficha));
        }
        return fichasDTO;
    }
    
    /**
     * Convierte una entidad Jugador a su correspondiente DTO.
     * 
     * @param jugador la entidad Jugador a convertir.
     * @return un objeto de tipo JugadorDTO.
     */
    public JugadorDTO adaptarEntidadJugador(Jugador jugador){
        JugadorDTO dto;
        if(jugador.getCuenta() != null){
            dto = new JugadorDTO(adaptarEntidadCuenta(jugador.getCuenta()));
        }else{
            dto = new JugadorDTO();
        }
        
        if(jugador.getFichas() != null){
            dto.setFichas(adaptarEntidadesFicha(jugador.getFichas()));
        }
        
        return dto;
    } 
    
    /**
     * Convierte una entidad Cuenta a su correspondiente DTO.
     * 
     * @param cuenta la entidad Cuenta a convertir.
     * @return un objeto de tipo CuentaDTO.
     */
    public CuentaDTO adaptarEntidadCuenta(Cuenta cuenta){
        CuentaDTO dto = new CuentaDTO();
        
        if(cuenta.getAvatar() != null){
            dto.setAvatar(adaptarAvatar(cuenta.getAvatar()));
        }
        dto.setAdmin(cuenta.esAdmin());
        dto.setIdCadena(cuenta.getIdCadena());
        
        return dto;
    } 
    
    /**
     * Convierte una entidad Avatar a su correspondiente DTO.
     * 
     * @param avatar la entidad Avatar a convertir.
     * @return un objeto de tipo AvatarDTO.
     */
    public AvatarDTO adaptarAvatar(Avatar avatar){
        AvatarDTO avatarDTO = null;
        switch(avatar){
            case Avatar.AVE -> avatarDTO = AvatarDTO.AVE;
            case Avatar.GATO -> avatarDTO = AvatarDTO.GATO;
            case Avatar.JAGUAR -> avatarDTO = AvatarDTO.JAGUAR;
            case Avatar.KIWI -> avatarDTO = AvatarDTO.KIWI;
            case Avatar.MARIPOSA -> avatarDTO = AvatarDTO.MARIPOSA;
            case Avatar.PANDA -> avatarDTO = AvatarDTO.PANDA;
            case Avatar.SERPIENTE -> avatarDTO = AvatarDTO.SERPIENTE;
            case Avatar.TORTUGA -> avatarDTO = AvatarDTO.TORTUGA;
            case Avatar.VENADO -> avatarDTO = AvatarDTO.VENADO;
        }
        return avatarDTO;
    }   
}
