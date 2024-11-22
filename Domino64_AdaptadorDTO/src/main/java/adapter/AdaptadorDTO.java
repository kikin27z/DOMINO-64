/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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
public class AdaptadorDTO {

    public Lobby adaptarEntidadLobby(LobbyDTO lobbyDTO){
        Lobby lobby = new Lobby(lobbyDTO.getCodigoPartida());
        
        List<Cuenta> cuentas = adaptarCuentas(lobbyDTO.getCuentas());
        lobby.agregarCuentas(cuentas);
        return lobby;
    }
    
    public Partida adaptarEntidadPartida(PartidaDTO partida){
        Partida entidad = new Partida(partida.getCodigoPartida());
        if (partida.getFichasPorJugador() > 0) {
            entidad.setFichasPorJugador(partida.getFichasPorJugador());
        }
        if (partida.getJugadores() != null) {
            List<Cuenta> cuentas = new ArrayList<>();
            for (Cuenta cuenta : adaptarJugadores(partida.getJugadores())) {
                cuentas.add(cuenta);
            }
            entidad.setJugadores(cuentas);
        }

        return entidad;
    }
    
    public List<Cuenta> adaptarCuentas(List<CuentaDTO> cuentas) {
        List<Cuenta> entidades = new ArrayList<>();
        for (CuentaDTO cuenta : cuentas) {
            entidades.add(adaptarEntidadCuenta(cuenta));
        }
        return entidades;
    }
    
    public List<Cuenta> adaptarJugadores(List<JugadorDTO> cuentas) {
        List<Cuenta> entidades = new ArrayList<>();
        for (JugadorDTO cuenta : cuentas) {
            entidades.add(adaptarEntidadCuenta(cuenta.getCuenta()));
        }
        return entidades;
    }

    public FichaDTO adaptarEntidadFicha(Ficha ficha) {
        return new FichaDTO(ficha.getDerecha(), ficha.getIzquierda());
    }

    public Cuenta adaptarEntidadCuenta(CuentaDTO cuentaDTO) {
        System.out.println("en adaptar entidad cuenta");
        Cuenta entidad = new Cuenta();

        entidad.setId(cuentaDTO.getId());
        if (cuentaDTO.getAvatar() != null) {
            entidad.setAvatar(adaptarAvatar(cuentaDTO.getAvatar()));
        }
        entidad.setIdCadena(cuentaDTO.getIdCadena());
        System.out.println("entidad: "+entidad);
        return entidad;
    }

    public Avatar adaptarAvatar(AvatarDTO avatarDTO) {
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
