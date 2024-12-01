package comunicadores_logica;

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
import manejadores.ManejadorCuenta;
import observer.Observer;
import tiposLogicos.TipoLogicaLobby;

/**
 * Clase que representa una implementacion de un observador. Este observador
 * concreto define los metodos que van a manejar los distintos eventos que le
 * interesa recibir a este observador. La lista de enum define los eventos que
 * va a observar. Tiene un mapeo de consumers por cada tipo de evento, esto
 * quiere decir que por cada tipo de evento que va a recibir, le asigna un
 * metodo que se va a ejecutar al recibir el evento especifico. Este observador
 * solo recibe eventos que genera el jugador.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public abstract class IReceptorEventosLogica implements Observer<Evento> {
    protected ICliente cliente;
    protected static BlockingQueue<Evento> colaEventos;
    protected Map<Enum<?>, Consumer<Evento>> consumers;
    protected final List<Enum<?>> eventos = new ArrayList<>(
            List.of(
                    TipoError.ERROR_LOGICO,
                    TipoError.ERROR_DE_SERVIDOR,
                    TipoLogicaLobby.CUENTA_LISTO,
                    TipoLogicaLobby.CUENTA_NO_LISTO,
                    TipoLogicaLobby.ABANDONO_ADMIN,
                    TipoLogicaLobby.CUENTA_ABANDONO,
                    TipoLogicaLobby.AVATAR_ACTUALIZADO,
                    TipoLogicaLobby.PARTIDA_ENCONTRADA,
                    TipoLogicaLobby.PARTIDA_CREADA
            ));

    public IReceptorEventosLogica() {
        this.consumers = new ConcurrentHashMap<>();
        this.colaEventos = new LinkedBlockingDeque<>();
    }

    @Override
    public void update(Evento observable) {
        colaEventos.offer(observable); 
    }

    public List<Enum<?>> getEventos() {
        return eventos;
    }
    
    public void enviarEvento(Evento evento) {
        cliente.enviarEvento(evento);
    }
    
    public abstract int devolverIdCliente();

    public void agregarEvento(Enum<?> evento, Consumer<Evento> consumer) {
        this.eventos.add(evento);
        consumers.putIfAbsent(evento, consumer);
    }

    protected void setConsumers() {
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
        consumers.putIfAbsent(TipoError.ERROR_LOGICO, this::manejarError);
        consumers.putIfAbsent(TipoLogicaLobby.CUENTA_NO_LISTO, this::cuentaNoLista);
        consumers.putIfAbsent(TipoLogicaLobby.CUENTA_LISTO, this::cuentaLista);
        consumers.putIfAbsent(TipoLogicaLobby.CUENTA_ABANDONO, this::cuentaAbandono);
        consumers.putIfAbsent(TipoLogicaLobby.ABANDONO_ADMIN, this::adminAbandono);
        consumers.putIfAbsent(TipoLogicaLobby.AVATAR_ACTUALIZADO, this::actualizarAvatares);
        consumers.putIfAbsent(TipoLogicaLobby.PARTIDA_ENCONTRADA, this::partidaEncontrada);
        consumers.putIfAbsent(TipoLogicaLobby.PARTIDA_CREADA, this::partidaCreada);
        consumers.putIfAbsent(TipoLogicaLobby.NO_SE_PUDO_UNIR, this::errorUnirse);
    }

    public List<Enum<?>> obtenerEventosSuscrito() {
        return eventos;
    }
    
    public abstract void vincularCuenta();

    public abstract void vincularDisplay();
    public abstract void iniciaConexion();
    public abstract void recibirPartida(Evento evento);

    public abstract void adminAbandono(Evento evento);
    public abstract void cuentaAbandono(Evento evento);
    public abstract void cuentaLista(Evento evento);
    public abstract void cuentaNoLista(Evento evento);


    public abstract void actualizarAvatares(Evento evento);


    public abstract void manejarError(Evento evento);
    
    public abstract void partidaEncontrada(Evento evento);
    public abstract void partidaCreada(Evento evento);
    public abstract void errorUnirse(Evento evento);
}
