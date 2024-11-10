/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import tiposLogicos.TiposJugador;


/**
 * Clase que representa los eventos que puede generar el jugador.
 * La entidad que maneja es la entidad Ficha, debido a que
 * el contexto que puede dar un jugador es la ficha que coloco.
 * A este evento se le puede agregar la partida en la cual
 * esta el contexto (la partida donde esta el jugador). 
 * Asi como se le puede agregar el jugador que genero el evento
 * Este evento puede ser de cualquier tipo definido en el
 * enum TiposJugador
 * @author luisa M
 * @author karim F
 */
public class EventoJugador{}/* extends EventoLogico<Ficha>{
    private Ficha context;
    private TiposJugador tipo;
    private Cuenta jugador;
    private Partida partida;
    
    public EventoJugador(){}
    
    public EventoJugador(TiposJugador tipo){
        this.tipo = tipo;
    }
    
    public void setTipo(TiposJugador tipo){
        this.tipo = tipo;
    }
    
    public Cuenta getJugador(){
        return jugador;
    }
    
    public void setJugador(Cuenta jugador){
        this.jugador = jugador;
    }
    
    public void setPartida(Partida partida){
        this.partida = partida;
    }
    
    public Partida getPartida(){
        return partida;
    }
    
    @Override
    public void agregarInfo(Ficha info) {
        this.context = info;
    }

    @Override
    public TiposJugador getTipo() {
        return tipo;
    }

    @Override
    public Ficha getInfo() {
        return context;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{contexto= ").append(context);
        sb.append(", tipo= ").append(tipo.toString());
        sb.append('}');
        return sb.toString();
    }    
}*/
