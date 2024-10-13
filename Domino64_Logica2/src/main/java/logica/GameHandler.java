/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import patrones.observer.Observer;
import entidades.Ficha;
import entidades.Jugador;
import entidades.Partida;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import exceptions.DominioException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion_utilities.NotificadorPresentacion;


/**
 *
 * @author luisa M
 */
public class GameHandler implements Observer<NotificadorPresentacion> {
    //private List<Jugador> jugadores;
    private static Jugador jugador;
    private static Partida partida;

    public static Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugadorNuevo) {
        jugador = jugadorNuevo;
    }

    public static Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partidaNueva) {
        partida = partidaNueva;
    }
    
    private void inicializarPartida(JugadorDTO jugadorDTO){
        System.out.println("inicializando partida");
        //JugadorDTO jugadorDTO = observable.getJugador();
        jugador = new Jugador(jugadorDTO.getUsername());
        partida.inicializarPartida("123", jugador, 4, 3);//falta adaptarlo para que no este hardcodeado
        try {
            partida.agregarJugador(new Jugador("Maria"));
            partida.agregarJugador(new Jugador("Fernanda"));
        } catch (DominioException ex) {
            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("se inicializo");
    }
    
    private void setFichaSeleccionada(FichaDTO fichaDTO){
        System.out.println("llego la ficha seleccionada");
        List<Ficha> fichas = jugador.getFichas();
        for (Ficha ficha : fichas) {
            if (ficha.equals(new Ficha(fichaDTO.getIzquierda(), fichaDTO.getDerecha()))) {
                jugador.setFichaSeleccionada(ficha);
                break;
            }
        }
    }
    
    @Override
    public void update(NotificadorPresentacion observable, Object ... context) {
        int tipoAccion = (int)context[0];
        if(tipoAccion == NotificadorPresentacion.INICIALIZAR_PARITDA){
//            inicializarPartida(observable.getJugador());
        }else if(tipoAccion == NotificadorPresentacion.FICHA_SELECCIONADA){
            setFichaSeleccionada(observable.getFichaSeleccionada());
        }
    }
}
