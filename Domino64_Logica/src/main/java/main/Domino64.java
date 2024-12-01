package main;

import entidadesDTO.LobbyDTO;
import eventoss.EventoMVCJugador;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
