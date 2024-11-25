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
public class AdaptadorDTO {

    public Lobby adaptarLobbyDTO(LobbyDTO lobbyDTO){
        Lobby lobby = new Lobby(lobbyDTO.getCodigoPartida());
        
        List<Cuenta> cuentas = adaptarCuentasDTO(lobbyDTO.getCuentas());
        lobby.agregarCuentas(cuentas);
        return lobby;
    }
    
    public Partida adaptarPartidaDTO(PartidaDTO partida){
        Partida entidad = new Partida(partida.getCodigoPartida());
        if (partida.getFichasPorJugador() > 0) {
            entidad.setFichasPorJugador(partida.getFichasPorJugador());
        }
        if (partida.getJugadores() != null) {
            entidad.setJugadores(adaptarJugadoresDTO(partida.getJugadores()));
        }

        return entidad;
    }
    
    public List<Jugador> adaptarJugadoresDTO(List<JugadorDTO> jugadores) {
        List<Jugador> entidades = new ArrayList<>();
        for (JugadorDTO jugador : jugadores) {
            entidades.add(adaptarJugadorDTO(jugador));
        }
        return entidades;
    }
    
    public Jugador adaptarJugadorDTO(JugadorDTO jugador) {
        Jugador entidad = new Jugador(adaptarCuentaDTO(jugador.getCuenta()));

        if (jugador.getFichas() != null) {
            entidad.setFichas(adaptarFichaDTO(jugador.getFichas()));
        }

        return entidad;
    }

    
    public List<Cuenta> adaptarCuentasDTO(List<CuentaDTO> cuentas) {
        List<Cuenta> entidades = new ArrayList<>();
        for (CuentaDTO cuenta : cuentas) {
            entidades.add(adaptarCuentaDTO(cuenta));
        }
        return entidades;
    }
    
    public List<Ficha> adaptarFichaDTO(List<FichaDTO> fichasDTO) {
        List<Ficha> fichas = new ArrayList<>();
        for (FichaDTO ficha : fichasDTO) {
            fichas.add(adaptarFichaDTO(ficha));
        }
        return fichas;
    }
    
    public Ficha adaptarFichaDTO(FichaDTO ficha) {
        return new Ficha(ficha.getIzquierda(), ficha.getDerecha());
    }

    public Cuenta adaptarCuentaDTO(CuentaDTO cuentaDTO) {
        System.out.println("en adaptar entidad cuenta");
        Cuenta entidad = new Cuenta();

        entidad.setId(cuentaDTO.getId());
        if (cuentaDTO.getAvatar() != null) {
            entidad.setAvatar(adaptarAvatarDTO(cuentaDTO.getAvatar()));
        }
        entidad.setIdCadena(cuentaDTO.getIdCadena());
        System.out.println("entidad: "+entidad);
        return entidad;
    }

    public Avatar adaptarAvatarDTO(AvatarDTO avatarDTO) {
        Avatar avatar = null;
        switch (avatarDTO) {
            case AVE ->
                avatar = Avatar.AVE;
            case GATO ->
                avatar = Avatar.GATO;
            case JAGUAR ->
                avatar = Avatar.JAGUAR;
            case KIWI ->
                avatar = Avatar.KIWI;
            case MARIPOSA ->
                avatar = Avatar.MARIPOSA;
            case PANDA ->
                avatar = Avatar.PANDA;
            case SERPIENTE ->
                avatar = Avatar.SERPIENTE;
            case TORTUGA ->
                avatar = Avatar.TORTUGA;
            case VENADO ->
                avatar = Avatar.VENADO;
        }
        return avatar;
    }
}
