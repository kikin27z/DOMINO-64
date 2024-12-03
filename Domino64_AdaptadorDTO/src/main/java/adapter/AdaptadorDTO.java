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
 * Clase encargada de convertir objetos DTO (Data Transfer Objects) a sus correspondientes entidades.
 * Permite adaptar los datos de transferencia para que puedan ser utilizados en el modelo de negocio.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class AdaptadorDTO {

    /**
     * Convierte un objeto de tipo LobbyDTO a su entidad correspondiente.
     * 
     * @param lobbyDTO el objeto DTO a convertir.
     * @return un objeto de tipo Lobby.
     */
    public Lobby adaptarLobbyDTO(LobbyDTO lobbyDTO){
        Lobby lobby = new Lobby(lobbyDTO.getCodigo());
        
        List<Cuenta> cuentas = adaptarCuentasDTO(lobbyDTO.getCuentas());
        lobby.agregarCuentas(cuentas);
        return lobby;
    }
    
    /**
     * Convierte una lista de objetos JugadorDTO a una lista de entidades Jugador.
     * 
     * @param jugadores la lista de objetos DTO a convertir.
     * @return una lista de entidades Jugador.
     */
    public List<Jugador> adaptarJugadoresDTO(List<JugadorDTO> jugadores) {
        List<Jugador> entidades = new ArrayList<>();
        for (JugadorDTO jugador : jugadores) {
            entidades.add(adaptarJugadorDTO(jugador));
        }
        return entidades;
    }
    
    /**
     * Convierte un objeto JugadorDTO a su entidad correspondiente.
     * 
     * @param jugador el objeto DTO a convertir.
     * @return un objeto de tipo Jugador.
     */
    public Jugador adaptarJugadorDTO(JugadorDTO jugador) {
        Jugador entidad ;
        
        if(jugador.getCuenta() != null){
            entidad = new Jugador(adaptarCuentaDTO(jugador.getCuenta()));
        }else{
            entidad = new Jugador();
        }

        if (jugador.getFichas() != null) {
            entidad.setFichas(adaptarFichaDTO(jugador.getFichas()));
        }

        return entidad;
    }
    
    /**
     * Convierte una lista de objetos CuentaDTO a una lista de entidades Cuenta.
     * 
     * @param cuentas la lista de objetos DTO a convertir.
     * @return una lista de entidades Cuenta.
     */
    public List<Cuenta> adaptarCuentasDTO(List<CuentaDTO> cuentas) {
        List<Cuenta> entidades = new ArrayList<>();
        for (CuentaDTO cuenta : cuentas) {
            entidades.add(adaptarCuentaDTO(cuenta));
        }
        return entidades;
    }
    
    /**
     * Convierte una lista de objetos FichaDTO a una lista de entidades Ficha.
     * 
     * @param fichasDTO la lista de objetos DTO a convertir.
     * @return una lista de entidades Ficha.
     */
    public List<Ficha> adaptarFichaDTO(List<FichaDTO> fichasDTO) {
        List<Ficha> fichas = new ArrayList<>();
        for (FichaDTO ficha : fichasDTO) {
            fichas.add(adaptarFichaDTO(ficha));
        }
        return fichas;
    }
    
    /**
     * Convierte un objeto FichaDTO a su entidad correspondiente.
     * 
     * @param ficha el objeto DTO a convertir.
     * @return un objeto de tipo Ficha.
     */
    public Ficha adaptarFichaDTO(FichaDTO ficha) {
        return new Ficha(ficha.getIzquierda(), ficha.getDerecha());
    }

    /**
     * Convierte un objeto CuentaDTO a su entidad correspondiente.
     * 
     * @param cuentaDTO el objeto DTO a convertir.
     * @return un objeto de tipo Cuenta.
     */
    public Cuenta adaptarCuentaDTO(CuentaDTO cuentaDTO) {
        Cuenta entidad = new Cuenta();
        if (cuentaDTO.getAvatar() != null) {
            entidad.setAvatar(adaptarAvatarDTO(cuentaDTO.getAvatar()));
        }
        entidad.setAdmin(cuentaDTO.esAdmin());
        entidad.setIdCadena(cuentaDTO.getIdCadena());
        return entidad;
    }

    /**
     * Convierte un objeto AvatarDTO a su entidad correspondiente.
     * 
     * @param avatarDTO el objeto DTO a convertir.
     * @return un objeto de tipo Avatar.
     */
    public Avatar adaptarAvatarDTO(AvatarDTO avatarDTO) {
        Avatar avatar = null;
        switch (avatarDTO) {
            case AVE -> avatar = Avatar.AVE;
            case GATO -> avatar = Avatar.GATO;
            case JAGUAR -> avatar = Avatar.JAGUAR;
            case KIWI -> avatar = Avatar.KIWI;
            case MARIPOSA -> avatar = Avatar.MARIPOSA;
            case PANDA -> avatar = Avatar.PANDA;
            case SERPIENTE -> avatar = Avatar.SERPIENTE;
            case TORTUGA -> avatar = Avatar.TORTUGA;
            case VENADO -> avatar = Avatar.VENADO;
        }
        return avatar;
    }
}
