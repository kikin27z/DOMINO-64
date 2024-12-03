package manejador;

import abstraccion.ICliente;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.TipoError;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;
import tiposLogicos.TiposJugador;
import observer.Observer;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public abstract class IControlLobby implements Observer<Evento> {
    protected ICliente cliente;
    protected static BlockingQueue<Evento> colaEventos;
    protected Map<Enum, Consumer<Evento>> consumers;
    protected static final List<Enum> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_DE_SERVIDOR,
                    TiposJugador.ABANDONAR_LOBBY,
                    TiposJugador.CAMBIAR_AVATAR,
                    TiposJugador.CREAR_PARTIDA,
                    TiposJugador.UNIRSE_PARTIDA,
                    TiposJugador.CUENTA_LISTA,
                    TiposJugador.CUENTA_NO_LISTA,
                    TiposJugador.CAMBIAR_CONFIG_PARTIDA
            ));
    
    protected IControlLobby(){
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
        consumers.putIfAbsent(TiposJugador.ABANDONAR_LOBBY, this::abandonoCuenta);
        consumers.putIfAbsent(TiposJugador.UNIRSE_PARTIDA, this::unirsePartida);
        consumers.putIfAbsent(TiposJugador.CREAR_PARTIDA, this::crearPartida);
        consumers.putIfAbsent(TiposJugador.CAMBIAR_AVATAR, this::cambiarAvatar);
        consumers.putIfAbsent(TiposJugador.CUENTA_LISTA, this::cuentaLista);
        consumers.putIfAbsent(TiposJugador.CUENTA_NO_LISTA, this::cuentaNoLista);
        consumers.putIfAbsent(TiposJugador.CAMBIAR_CONFIG_PARTIDA, this::cambiarReglas);
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
    }
    
    public void agregarEvento(Enum evento, Consumer<Evento> consumer){
        eventos.add(evento);
        consumers.putIfAbsent(evento, consumer);
    }
  
    public List<Enum> obtenerEventosSuscrito() {
        return eventos;
    }
    
    public abstract int devolverIdCliente();
    public abstract void abandonoCuenta(Evento evento);
    public abstract void unirsePartida(Evento evento);
    public abstract void crearPartida(Evento evento);
    public abstract void cambiarAvatar(Evento evento);
    public abstract void cambiarReglas(Evento evento);
    public abstract void manejarError(Evento evento);
    
    public abstract void cuentaLista(Evento evento);
    public abstract void cuentaNoLista(Evento evento);
    
    
}
