package receptorPresentacion;

import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.ReglasDTO;
import eventosLobby.ObserverLobby;
import java.util.concurrent.ExecutorService;
import manejadores.Control;
import manejadores.ManejadorDisplay;
import manejadores.ManejadorNotificador;

/**
 *
 * @author karim
 */
public class ControlEventosLobby extends ControlEventos implements ObserverLobby {

    private final ManejadorNotificador manejadorNotificador;

    public ControlEventosLobby(ExecutorService hiloPrincipal, ManejadorDisplay display) {
        super(hiloPrincipal, display);
        this.manejadorNotificador = Control.obtenerNotificador();
    }

    @Override
    public void avisarCuentaLista() {
        hiloPrincipal.execute(() -> {
            manejadorNotificador.cuentaLista();
        });
    }

    @Override
    public void avisarCuentaNoLista() {
        hiloPrincipal.execute(() -> {
            manejadorNotificador.cuentaNoLista();
        });
    }


    @Override
    public void avisarAbandonar() {
        hiloPrincipal.execute(() -> {
            manejadorNotificador.abandonarPartida();
        });
    }

    @Override
    public void avisarActualizarReglas(ReglasDTO reglas) {
         hiloPrincipal.execute(() -> {
            manejadorNotificador.cambiarReglas(reglas);
        });
    }

    @Override
    public void avisarIniciarPartida() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public void avisarCambioAvatar(CuentaDTO cuentaActualizada) {
    }
}
