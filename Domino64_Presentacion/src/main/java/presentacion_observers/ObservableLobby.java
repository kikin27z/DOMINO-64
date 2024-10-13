package presentacion_observers;

import dtos.cuenta.CuentaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author karim
 */
public abstract class ObservableLobby {

    private List<ObservadorLobby> observadores;

    public ObservableLobby() {
        this.observadores = new ArrayList<>();
    }

    public void addObserver(ObservadorLobby observador) {
        observadores.add(observador);
    }

    public void removeObserver(ObservadorLobby o) {
        this.observadores.remove(o);
    }

    public void notificarActualizarEncabezado() {
        for (ObservadorLobby observador : observadores) {
            observador.actualizarEncabezado();
        }
    }

    public void notificarActualizarMensaje() {
        for (ObservadorLobby observador : observadores) {
            observador.actualizarMensajeAviso();
        }
    }

    public void notificarAgregarOtroJugador(CuentaDTO cuenta) {
        for (ObservadorLobby observador : observadores) {
            observador.agregarOtroJugador(cuenta);
        }
    }

    public void notificarQuitarOtroJugador(CuentaDTO cuenta) {
        for (ObservadorLobby observador : observadores) {
            observador.quitarOtroJugador(cuenta);
        }
    }

    public void notificarPonerListoJugador(CuentaDTO cuenta) {
        for (ObservadorLobby observador : observadores) {
            observador.ponerListoJugador(cuenta);
        }
    }

    public void notificarQuitarListoJugador(CuentaDTO cuenta) {
        for (ObservadorLobby observador : observadores) {
            observador.quitarListoJugador(cuenta);
        }
    }

    public void notificarActualizarAvatarJugador(CuentaDTO cuenta) {
        for (ObservadorLobby observador : observadores) {
            observador.actualizarAvatarJugador(cuenta);
        }
    }

    public void notificarActualizarNombreOtroJugador(CuentaDTO cuenta) {
        for (ObservadorLobby observador : observadores) {
            observador.actualizarNombreOtroJugador(cuenta);
        }
    }
}
