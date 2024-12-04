package manejadorPozo;

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
import tiposLogicos.TiposJugador;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public abstract class IControlPozo implements Observer<Evento> {
    protected ICliente cliente;
    protected BlockingQueue<Evento> colaEventos;
    protected Map<Enum, Consumer<Evento>> consumers;
    protected final List<Enum> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_DE_SERVIDOR,
                    TipoJugadorFicha.JALAR_FICHA,
                    TipoLogicaPartida.JUGADOR_SALIO,
                    TipoLogicaPartida.REPARTIR_FICHAS
            ));
    
    protected IControlPozo(){
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
        consumers.putIfAbsent(TipoJugadorFicha.JALAR_FICHA, this::jalarFicha);
        consumers.putIfAbsent(TipoLogicaPartida.JUGADOR_SALIO, this::jugadorAbandono);
        consumers.putIfAbsent(TipoLogicaPartida.REPARTIR_FICHAS, this::repartirFichas);
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
    public abstract void repartirFichas(Evento evento);
    public abstract void jugadorAbandono(Evento evento);
    public abstract void jalarFicha(Evento evento);
    
    
}
