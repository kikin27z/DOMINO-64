package manejadorTablero;

import domino64.eventos.base.Evento;
import domino64.eventos.base.error.TipoError;
import implementacion.Client;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;
import observer.Observer;
import tiposLogicos.TiposJugador;

/**
 *
 * @author karim
 */
public abstract class IControlTablero implements Observer<Evento> {
    protected static BlockingQueue<Evento> colaEventos;
    protected Map<Enum<?>, Consumer<Evento>> consumers;
    protected static final List<Enum<?>> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_DE_SERVIDOR,
                    TiposJugador.COLOCAR_FICHA
            ));
    
    protected IControlTablero(){
        consumers = new ConcurrentHashMap<>();
        colaEventos = new LinkedBlockingDeque();
    }
    
    @Override
    public void update(Evento observable) {
        colaEventos.offer(observable); 
   }
    
    //
    protected void setConsumers(){
        consumers.putIfAbsent(TiposJugador.COLOCAR_FICHA, this::colocarFicha);
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
    }
    
    public void agregarEvento(Enum<?> evento, Consumer<Evento> consumer){
        eventos.add(evento);
        consumers.putIfAbsent(evento, consumer);
    }
    
    public abstract void colocarFicha(Evento evento);
    public abstract void manejarError(Evento evento);
    public abstract void vincularCliente(Client _cliente);
}