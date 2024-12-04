package manejadorPartida;

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
import tiposLogicos.TipoLogicaLobby;
import tiposLogicos.TipoLogicaTurno;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public abstract class IControlPartida implements Observer<Evento> {
    protected ICliente cliente;
    protected BlockingQueue<Evento> colaEventos;
    protected Map<Enum, Consumer<Evento>> consumers;
    protected final List<Enum> eventos = new ArrayList<>(
            List.of(
                    TipoLogicaTurno.FIN_JUEGO,
                    TipoError.ERROR_DE_SERVIDOR,
                    TipoLogicaLobby.PREPARAR_PARTIDA,
                    TipoLogicaPozo.REPARTIR_FICHAS,
                    TipoJugadorFicha.JUGADA_REALIZADA,
                    TipoLogicaTurno.TURNOS_DESIGNADOS,
                    TipoLogicaTurno.TURNO_ACTUAL,
                    TipoLogicaTurno.PASAR_TURNO,
                    TipoLogicaPozo.POZO_VACIO
            ));
    
    protected IControlPartida(){
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
    
    protected void setConsumers(){
        consumers.putIfAbsent(TipoError.ERROR_DE_SERVIDOR, this::manejarError);
        consumers.putIfAbsent(TipoLogicaTurno.FIN_JUEGO, this::finJuegoSinMovimientos);
        consumers.putIfAbsent(TipoLogicaLobby.PREPARAR_PARTIDA, this::prepararPartida);
        consumers.putIfAbsent(TipoLogicaPozo.REPARTIR_FICHAS, this::entregarFichaJugadores);
        consumers.putIfAbsent(TipoJugadorFicha.JUGADA_REALIZADA, this::quitarFicha);
        consumers.putIfAbsent(TipoLogicaTurno.TURNO_ACTUAL, this::evaluarJugador);
        consumers.putIfAbsent(TipoLogicaTurno.PASAR_TURNO, this::evaluarJugador);
        consumers.putIfAbsent(TipoLogicaTurno.TURNOS_DESIGNADOS, this::iniciarPartida);
        consumers.putIfAbsent(TipoLogicaPozo.POZO_VACIO, this::pozoVacio);
//        consumers.putIfAbsent(TipoLogicaTablero.OBTENER_JUGADA, this::asignarJugadaNueva);
        
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
    public abstract void finJuegoSinMovimientos(Evento evento);
    public abstract void prepararPartida(Evento evento);
    public abstract void entregarFichaJugadores(Evento evento);
    public abstract void quitarFicha(Evento evento);
    public abstract void evaluarJugador(Evento evento);
    public abstract void iniciarPartida(Evento evento);
    public abstract void asignarJugadaNueva(Evento evento);
    public abstract void pozoVacio(Evento evento);
}
