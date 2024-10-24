package entidades;

import exceptions.DominioException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author luisa
 */
public class Partida {
    private Pozo pozo;
    private Tablero tablero;
    private List<Jugador> jugadores;
    private EstadoPartida estado;
    private boolean turnosDesignados;
    private String codigoPartida;
    private Jugador jugadorEnTurno;
    private int fichasPorJugador;
    private int maxCantidadJugadores;
    private ModoPartida modo;
    
    public Partida(ModoPartida modo){
        this.jugadores = new ArrayList<>();
        this.modo = modo;
        this.pozo = new Pozo();
        this.tablero = new Tablero();
        this.turnosDesignados = false;
    }
    
    public ModoPartida getModo(){
        return modo;
    }
    
    public void inicializarPartida(String codigoPartida, Jugador creadorPartida,
            int fichasPorJugador, int cantidadMaxJugadores){
        this.codigoPartida = codigoPartida;
        jugadores.add(creadorPartida);
        this.fichasPorJugador = fichasPorJugador;
        this.maxCantidadJugadores = cantidadMaxJugadores;
    }
    
    public Partida(String codigoPartida, List<Jugador> jugadores, 
            int fichasPorJugador, int maxCantidadJugadores){
        //this.jugadores = jugadores;
        this.codigoPartida=codigoPartida;
//        this.pozo = new Pozo();
//        this.tablero = new Tablero();
        this.fichasPorJugador = fichasPorJugador;
        this.maxCantidadJugadores = maxCantidadJugadores;
        //turnosDesignados = false;
    }
    
    /**
     * agrega un jugador a la lista de jugadores en la partida
     * @param jugador a agregar
     * @throws DominioException si ya hay un jugador con en la lista
     * con el username del jugador del parametro
     */
    public void agregarJugador(Jugador jugador)throws DominioException{
        if(jugadores.contains(jugador))
            throw new DominioException("Ya existe un jugador con ese username");
        this.jugadores.add(jugador);
    }
    
    public void eliminarJugador(Jugador jugador){
        this.jugadores.remove(jugador);
    }
    
    
    /**
     * permuta la lista, designando los turnos aleatoriamente.
     * Esta operacion solo se debe poder hacer una vez, por lo tanto,
     * al realizarse la primera vez se cambia el valor de la variable
     * 'turnosDesignados' a true
     * @throws DominioException si la variable 'turnosDesignados' es true,
     * indicando que ya se asignaron los turnos anteriormente
     */
    public void designarTurnos()throws DominioException{
        //Falta implementar que el jugador con la mula mas alta
        //tenga el primer turno
        if(!turnosDesignados){
            Collections.shuffle(jugadores);
            turnosDesignados = true;
            jugadorEnTurno = jugadores.get(0);
        }
        throw new DominioException("Los turnos ya están designados");
    }
    
    /**
     * actualiza el valor del jugador en turno, pasando al siguiente jugador
     * en la lista
     */
    public void cambiarTurno(){
        int index = jugadores.indexOf(jugadorEnTurno);
        jugadorEnTurno = jugadores.get(index+1);
    }
    
    public Jugador getJugadorEnTurno(){
        return jugadorEnTurno;
    }
    
    public String getCodigoPartida(){
        return this.codigoPartida;
    }
    
    public Pozo getPozo(){
        return pozo;
    }
    
    public Tablero getTablero(){
        return tablero;
    }
    
    public List<Jugador> getJugadores(){
        return jugadores;
    }

    public int getFichasPorJugador() {
        return fichasPorJugador;
    }

    public int getMaxCantidadJugadores() {
        return maxCantidadJugadores;
    }
    
}
