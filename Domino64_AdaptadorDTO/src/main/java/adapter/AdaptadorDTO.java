/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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
public class AdaptadorDTO {

    public Partida adaptarEntidadPartida(PartidaDTO partida){
        Partida entidad = new Partida(partida.getCodigoPartida());
        if (partida.getFichasPorJugador() > 0) {
            entidad.setFichasPorJugador(partida.getFichasPorJugador());
        }
        if (partida.getJugadores() != null) {
            List<Cuenta> cuentas = new ArrayList<>();
            for (Cuenta cuenta : adaptarCuentas(partida.getJugadores())) {
                cuentas.add(cuenta);
            }
            entidad.setJugadores(cuentas);
        }

        return entidad;
    }
    
    public List<Cuenta> adaptarCuentas(List<JugadorDTO> cuentas) {
        List<Cuenta> entidades = new ArrayList<>();
        for (JugadorDTO cuenta : cuentas) {
            entidades.add(adaptarEntidadCuenta(cuenta.getCuenta()));
        }
        return entidades;
    }

    public FichaDTO adaptarEntidadFicha(Ficha ficha) {
        return new FichaDTO(ficha.getDerecha(), ficha.getIzquierda());
    }

    public Cuenta adaptarEntidadCuenta(CuentaDTO cuenta) {
        Cuenta entidad = new Cuenta();

        entidad.setId(cuenta.getId());
        if (cuenta.getAvatar() != null) {
            entidad.setAvatarUrl(adaptarAvatar(cuenta.getAvatar()));
        }
        entidad.setIdCadena(cuenta.getIdCadena());
        if(cuenta.getUsername() != null){
            entidad.setUsername(cuenta.getUsername());
        }

        return entidad;
    }

    public String adaptarAvatar(AvatarDTO avatarDTO) {
        String avatar = null;
        switch (avatarDTO) {
            case AVE ->
                avatar = "AVE";
            case GATO ->
                avatar = "GATO";
            case JAGUAR ->
                avatar = "JAGUAR";
            case KIWI ->
                avatar = "KIWI";
            case MARIPOSA ->
                avatar = "MARIPOSA";
            case PANDA ->
                avatar = "PANDA";
            case SERPIENTE ->
                avatar = "SERPIENTE";
            case TORTUGA ->
                avatar = "TORTUGA";
            case VENADO ->
                avatar = "VENADO";
        }
        return avatar;
    }
}
