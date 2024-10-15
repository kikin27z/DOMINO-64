package presentacion_observers;

import entidadesDTO.FichaDTO;
import entidadesDTO.PartidaOfflineDTO;
import entidadesDTO.PartidaOnlineDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author karim
 */
public abstract class ObservablePartida {
    private List<ObservadorPartida> observadores;

    public ObservablePartida() {
        this.observadores = new ArrayList<>();
    }

    public void addObserver(ObservadorPartida observador) {
        observadores.add(observador);
    }

    public void removeObserver(ObservadorPartida o) {
        this.observadores.remove(o);
    }
    
    public void notificarPartidaOffline(PartidaOfflineDTO partida){
        for (ObservadorPartida observador : observadores) {
            observador.iniciarPartidaOffline(partida);
        }
    }
    public void notificarPartidaOnline(PartidaOnlineDTO partida){
        for (ObservadorPartida observador : observadores) {
            observador.iniciarPartidaOnline(partida);
        }
    }
    
    public void notificarAgregarFichaAlTablero(FichaDTO fichaDTO, boolean queLado){
        for (ObservadorPartida observador : observadores) {
            observador.agregarFichaTablero(fichaDTO, queLado);
        }
    }
    
    public void notificarAgregarFicha(FichaDTO fichaDTO) {
        for (ObservadorPartida observador : observadores) {
            observador.agregarFichaMazo(fichaDTO);
        }
    }
    public void notificarAgregarFichas(List<FichaDTO> fichasDTO) {
        for (ObservadorPartida observador : observadores) {
            observador.agregarFichasMazo(fichasDTO);
        }
    }
}
