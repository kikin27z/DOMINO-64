package manejadorTurnos;

import abstraccion.ICliente;
import eventoBase.Evento;
import eventoBaseError.TipoError;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;
import observer.Observer;
import tiposLogicos.TipoJugadorFicha;
import tiposLogicos.TipoLogicaPartida;
import tiposLogicos.TipoLogicaPozo;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public abstract class IControlTurnos implements Observer<Evento> {
    protected ICliente cliente;
    protected BlockingQueue<Evento> colaEventos;
    protected Map<Enum, Consumer<Evento>> consumers;
    protected final List<Enum> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_DE_SERVIDOR,
                    TipoLogicaPozo.REPARTIR_FICHAS,
//                    TipoJugadorFicha.JUGADA_REALIZADA,
                    TipoLogicaTablero.OBTENER_JUGADA,
                    TipoLogicaPartida.SIGUIENTE_TURNO
            ));
    
    protected IControlTurnos(){
        consumers = new ConcurrentHashMap<>();
        colaEventos = new LinkedBlockingDeque();
    }
    
    @Override
    public void update(Evento observable) {
        colaEventos.offer(observable); 
   }
    
    public List<Enum> getEventos() {
        return eventos;
    }
    
     public void enviarEvento(Evento evento) {
        cliente.enviarEvento(evento);
    }
    protected void setConsumers(){
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
        consumers.putIfAbsent(TipoLogicaPozo.REPARTIR_FICHAS, this::asignarOrden);
//        consumers.putIfAbsent(TipoJugadorFicha.JUGADA_REALIZADA, this::evaluarJugada);
        consumers.putIfAbsent(TipoLogicaTablero.OBTENER_JUGADA, this::cambiarTurno);
        consumers.putIfAbsent(TipoLogicaPartida.SIGUIENTE_TURNO, this::pasarTurno);
    }
    
    public void agregarEvento(Enum evento, Consumer<Evento> consumer){
        eventos.add(evento);
        consumers.putIfAbsent(evento, consumer);
    }
  
    public List<Enum> obtenerEventosSuscrito() {
        return eventos;
    }
    public abstract void iniciaConexion();
    public abstract void manejarError(Evento evento);
    public abstract void asignarOrden(Evento evento);
    public abstract void cambiarTurno(Evento evento);
    public abstract void pasarTurno(Evento evento);
    public abstract void removerJugador(Evento evento);
    public abstract void evaluarJugada(Evento evento);
    
    
}
