package presentacion_utilities;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.LobbyDTO;
import eventosLobby.ObservableLobbyMVC;
import eventosOpcionesPartida.ObservableOpcionesMVC;
import eventosPartida.ObservablePartidaMVC;
import java.util.List;
import javafx.application.Platform;

/**
 *
 * @author karim
 */
public class DistribuidorEventosModelo {

    private static DistribuidorEventosModelo distribuidor;
    private ObservableLobbyMVC lobbyMVC;
    private ObservableOpcionesMVC opcionesMVC;
    private ObservablePartidaMVC partidaMVC;

    private DistribuidorEventosModelo() {
    }

    public static DistribuidorEventosModelo getInstance() {
        if (distribuidor == null) {
            distribuidor = new DistribuidorEventosModelo();
        }
        return distribuidor;
    }

    public void setLobbyMVC(ObservableLobbyMVC lobbyMVC) {
        this.lobbyMVC = lobbyMVC;
    }

    public void setOpcionesMVC(ObservableOpcionesMVC opcionesMVC) {
        this.opcionesMVC = opcionesMVC;
    }

    public void setPartidaMVC(ObservablePartidaMVC partidaMVC) {
        this.partidaMVC = partidaMVC;
    }
    //----------------------------------Mensaje de error-----------------------------------
    
    public void mostrarMensajeError(String msj){
        System.out.println(msj);
    }
    
    //----------------------------------Eventos Lobby-----------------------------------
    public void inicializarLobby(LobbyDTO lobby) {
        Platform.runLater(() -> {
            lobbyMVC.inicializarLobby(lobby);
        });
        
    }

    public void actualizarNuevoJugador(CuentaDTO cuenta) {
        Platform.runLater(() -> {
            lobbyMVC.actualizarNuevoJugador(cuenta);
        });
    }

    public void actualizarQuitarCuenta(CuentaDTO cuenta) {
        Platform.runLater(() -> {
            lobbyMVC.actualizarQuitarCuenta(cuenta);
        });
    }

    public void actualizarAvatarCuenta(CuentaDTO cuenta) {
        Platform.runLater(() -> {
            lobbyMVC.actualizarAvatarCuenta(cuenta);
        });
    }

    public void actualizarCuentaLista(CuentaDTO cuenta) {
        Platform.runLater(() -> {
            lobbyMVC.actualizarCuentaLista(cuenta);
        });
    }

    public void actualizarCuentaNoLista(CuentaDTO cuenta) {
        Platform.runLater(() -> {
            lobbyMVC.actualizarCuentaNoLista(cuenta);
        });
    }
    //----------------------------------Eventos OpcionesPartida-----------------------------------
    public void actualizarMensajeAviso(String mensaje) {
        Platform.runLater(() -> {
            opcionesMVC.actualizarMensajeAviso(mensaje);
        });
        
    }
    //----------------------------------Eventos Partida-----------------------------------
    public void actualizarDarFichas(List<FichaDTO> fichas) {
        Platform.runLater(() -> {
            partidaMVC.actualizarDarFichas(fichas);
        });
    }

    public void actualizarDarFicha(FichaDTO ficha) {
        Platform.runLater(() -> {
            partidaMVC.actualizarDarFicha(ficha);
        });
    }

    public void actualizarTurno(JugadaDTO jugada) {
        Platform.runLater(() -> {
            partidaMVC.actualizarTurno(jugada);
        });
    }
    
    public void actualizarJugadorAbandono(CuentaDTO cuenta){
        Platform.runLater(() -> {
            partidaMVC.actualizarJugadorAbandono(cuenta);
        });
        
    }
    
    public void actualizarJugadorSeRindio(CuentaDTO cuenta){
        Platform.runLater(() -> {
            partidaMVC.actualizarJugadorSeRindio(cuenta);
        });
    }



}
