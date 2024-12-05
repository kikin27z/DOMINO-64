package presentacion_utilities;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.ResultadosDTO;
import entidadesDTO.TurnosDTO;
import eventosCreditos.ObservableCreditosMVC;
import eventosFin.ObservableFinJuegoMVC;
import eventosLobby.ObservableLobbyMVC;
import eventosOpcionesPartida.ObservableOpcionesMVC;
import eventosPartida.ObservablePartidaMVC;
import java.util.List;
import javafx.application.Platform;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class DistribuidorEventosModelo {

    private static DistribuidorEventosModelo distribuidor;
    private ObservableLobbyMVC lobbyMVC;
    private ObservableOpcionesMVC opcionesMVC;
    private ObservablePartidaMVC partidaMVC;
    private ObservableFinJuegoMVC finMVC;
    private ObservableCreditosMVC creditosMVC;

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
    public void setFinJuegoMVC(ObservableFinJuegoMVC finMVC) {
        this.finMVC = finMVC;
    }
    public void setPartidaMVC(ObservableCreditosMVC creditosMVC) {
        this.creditosMVC = creditosMVC;
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

    public void actualizarJalarFicha() {
        Platform.runLater(() -> {
            partidaMVC.actualizarJalarFicha();
        });
    }

    public void actualizarProximaJugada(JugadaDTO jugada) {
        Platform.runLater(() -> {
            partidaMVC.actualizarProximaJugada(jugada);
        });
    }
    
    public void actualizarJugadorEnTurno() {
        Platform.runLater(() -> {
            partidaMVC.actualizarJugadorEnTurno();
        });
    }
    
    public void actualizarJugadorEnTurno() {
        Platform.runLater(() -> {
            partidaMVC.actualizarJugadorEnTurno();
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
    
    public void jugadaRealizada(JugadaRealizadaDTO jugada, CuentaDTO cuenta){
        Platform.runLater(() -> {
            partidaMVC.actualizarTablero(jugada,cuenta);
        });
    }

    public void inicializarPartida(TurnosDTO turnos){
        Platform.runLater(() -> {
            partidaMVC.inicializarPartida(turnos);
        });
    }
    public void inicializarFinJuego(ResultadosDTO resultados){
        Platform.runLater(() -> {
            finMVC.inicializarFinJuego(resultados);
        });
    }

}
