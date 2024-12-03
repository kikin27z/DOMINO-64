package manejadorTablero;

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

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public abstract class IControlTablero implements Observer<Evento> {
    protected ICliente cliente;
    protected BlockingQueue<Evento> colaEventos;
    protected Map<Enum, Consumer<Evento>> consumers;
    protected final List<Enum> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_DE_SERVIDOR,
                    TipoJugadorFicha.JUGADA_REALIZADA
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
        consumers.putIfAbsent(TipoJugadorFicha.JUGADA_REALIZADA, this::colocarFicha);
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
    }
    
    public void agregarEvento(Enum evento, Consumer<Evento> consumer){
        eventos.add(evento);
        consumers.putIfAbsent(evento, consumer);
    }
    
    public abstract void iniciaConexion();
    public abstract void colocarFicha(Evento evento);
    public abstract void manejarError(Evento evento);
}