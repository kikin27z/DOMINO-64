/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;
import java.util.Random;

/**
 *
 * @author luisa M
 */
public class Partida {
    private List<Cuenta> jugadores;
    private int fichasPorJugador;
    private String codigoPartida;

    public Partida(List<Cuenta> jugadores, int fichasPorJugador) {
        this.jugadores = jugadores;
        this.fichasPorJugador = fichasPorJugador;
        setCodigo();
    }

    private void setCodigo(){
        Random rnd = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if(i==3)
                builder.append('-');
            builder.append(rnd.nextInt(10));
        }
        codigoPartida = builder.toString();
    }
    
    public List<Cuenta> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Cuenta> jugadores) {
        this.jugadores = jugadores;
    }
    
    public boolean agregarJugador(Cuenta jugador){
        if(!jugadores.contains(jugador))
            return jugadores.add(jugador);
        return false;
    }

    public void removerJugador(Cuenta jugador){
        jugadores.remove(jugador);
    }
    
    public int getFichasPorJugador() {
        return fichasPorJugador;
    }

    public void setFichasPorJugador(int fichasPorJugador) {
        this.fichasPorJugador = fichasPorJugador;
    }

    public String getCodigoPartida() {
        return codigoPartida;
    }
    
    
}
