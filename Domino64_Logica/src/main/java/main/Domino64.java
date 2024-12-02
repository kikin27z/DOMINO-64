package main;

import entidadesDTO.LobbyDTO;
import eventoss.EventoMVCJugador;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import listener.ControlEventos;
import manejadores.Control;
import manejadores.MediadorManejadores;

/**
 *
 * @author karim
 */
public class Domino64 {
    
    public static void main(String[] args) {
        
        System.out.println("Iniciado juego en lógica");
        MediadorManejadores.getInstance();
        Control.subscribirManejadores();
        
        ControlEventos.mensajesInicio();
        ControlEventos.procesarEventosALogica();
        ControlEventos.mensajesOpcionesPartida();
//        Control.iniciarJuego();
    }
    
}
