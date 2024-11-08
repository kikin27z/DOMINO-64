package opciones_partida;

import eventosInicio.ObserverInicio;
import eventosOpcionesPartida.EventosOpcionesPartida;
import eventosOpcionesPartida.ObserverOpcionesPartida;
import java.util.ArrayList;
import java.util.List;
import presentacion_utilities.NotificadorEvento;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class OpcionesPartidaModel implements EventosOpcionesPartida{
    private final NotificadorEvento notificador;
    private final List<ObserverOpcionesPartida> observers;
    private String codigoPartida;
    public OpcionesPartidaModel() {
        notificador = NotificadorEvento.getInstance();
        observers = new ArrayList<>();
    }

    public void avisarMostrarInicio() {
        notificador.avisarMostrarInicio();
    }

    @Override
    public void avisarCrearPartida() {
        for(var observer : observers){
            observer.avisarCrearPartida();
        }
    }

    @Override
    public void avisarBuscarPartida(String codigoPartida) {
        for(var observer : observers){
            observer.avisarBuscarPartida(codigoPartida);
        }
    }

    public String getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }
    
    
    
}
