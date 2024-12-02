package com.luisa.manejador;

import abstraccion.ICliente;
import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import entidades.Ficha;
import entidades.Tablero;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.PosicionDTO;
import eventos.EventoJugador;
import eventos.EventoTablero;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import tiposLogicos.TipoLogicaTablero;

/**
 *
 * @author luisa M
 */
public class ManejadorTablero extends ObservadorTablero implements Runnable{
    private ICliente cliente;
    private static ExecutorService ejecutorEventos;
    private AtomicBoolean running;
    private static int id;
    private AdaptadorEntidad adaptador;
    private AdaptadorDTO adaptadorDTO;
    private Tablero tablero;
    private int extremoIzq, extremoDer;
    
    public ManejadorTablero(){
        this.tablero = new Tablero();
        adaptador = new AdaptadorEntidad();
        adaptadorDTO = new AdaptadorDTO();
        ejecutorEventos = Executors.newFixedThreadPool(2);
        running = new AtomicBoolean(true);
        setConsumers();
    }
    
    protected void setIdManejador(int idManejador) {
        id = idManejador;
    }
    
    @Override
    public void run() {
        while (running.get()) {
            try {
                Evento nextEvent = colaEventos.take();
                Consumer<Evento> cons = consumers.get(nextEvent.getTipo());
                if (cons != null) {
                    cons.accept(nextEvent);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                Logger.getLogger(ManejadorTablero.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
                break;
            }
        }
    }
    
    /**
     * agrega una ficha al tren de fichas del tablero. 
     * @param ficha ficha a colocar
     * @param extremo extremo en el que se va a colocar la ficha
     */
    private void colocarFicha(Ficha ficha, int extremo){
        tablero.agregarFicha(ficha, extremo);
        actualizarExtremo(ficha, extremo);
    }
    
    /**
     * actualiza el valor del extremo especificado en el tren de fichas
     * del tablero cuando se agrega una nueva ficha. Si la ficha se 
     * coloco en el lado izquierdo, el extremo izquiero se actualiza al valor
     * del lado izquierdo de la ficha. Lo mismo cuando se coloca una ficha del
     * extremo derecho.
     * @param ficha La ficha que se acaba de agregar al tablero
     * @param extremo Extremo al cual se agrego la ficha
     */
    private void actualizarExtremo(Ficha ficha, int extremo){
        if(extremo == tablero.DERECHA)
            extremoIzq = ficha.getIzquierda();
        else
            extremoDer = ficha.getDerecha();
    }
    
    /**
     * metodo que valida en que extremo se puede colocar la ficha, si
     * es que se puede colocar.
     * @param ficha la ficha a validar el tipo de jugada posible
     * @return la jugada que se puede hacer con la ficha
     */
    public JugadaPosible validarFicha(Ficha ficha){
        JugadaPosible jugada;
        //si se puede poner en la derecha
        if(jugablePorDerecha(ficha)){
            //y tambien en la izquierda
            if(jugablePorIzquierda(ficha)){
                //es jugable por ambos lados
                jugada = JugadaPosible.AMBOS_LADOS;
            }else//si no
                //solo se puede en derecha
                jugada = JugadaPosible.POR_DERECHA;
        }else if(jugablePorIzquierda(ficha)){//si se puede poner en la izquierda
            //solo se puede por este lado
            jugada = JugadaPosible.POR_IZQUIERDA;
        }else
            //si tampoco se puede en izquierda, no es ficha jugable
            jugada=JugadaPosible.NINGUNA;
        return jugada;
    }
    
    /**
     * valida si se puede poner en el extremo derecho
     * @param ficha ficha a validar
     * @return true si se puede colocar, false en caso contrario
     */
    private boolean jugablePorDerecha(Ficha ficha){
        return extremoDer == ficha.getDerecha() || extremoDer == ficha.getIzquierda();
    }
    
    /**
     * valida si se puede poner en el extremo izquierdo
     * @param ficha ficha a validar
     * @return true si se puede colocar, false en caso contrario
     */
    private boolean jugablePorIzquierda(Ficha ficha){
        return extremoIzq == ficha.getDerecha() || extremoIzq == ficha.getIzquierda();
    }

    @Override
    protected void manejarError(Evento evento) {
        EventoError error =(EventoError)evento;
        System.out.println("Ocurrio un error: "+error.getMensaje());
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void colocarFicha(Evento evento) {
        EventoJugador evJugador = (EventoJugador)evento;
        JugadaRealizadaDTO jugada = evJugador.getJugada();
        Ficha fichaColocada = adaptadorDTO.adaptarFichaDTO(jugada.getFicha());
        
        int extremo;
        //si es la primera ficha a colocar
        if(tablero.estaVacio()){
            extremo = tablero.DERECHA;
        }else{
            PosicionDTO posicion = jugada.getPosicion();
            extremo = obtenerExtremo(posicion);
        }

        colocarFicha(fichaColocada, extremo);
        
        EventoTablero eventoTablero = new EventoTablero(TipoLogicaTablero.FICHA_COLOCADA);
        eventoTablero.setIdPublicador(id);
        eventoTablero.setIdContexto(evJugador.getIdContexto());
        eventoTablero.setJugada(jugada);
    }
    
    private int obtenerExtremo(PosicionDTO posicion){
        if(posicion == PosicionDTO.DERECHA)
            return tablero.DERECHA;
        return tablero.IZQUIERDA;
    }
}
