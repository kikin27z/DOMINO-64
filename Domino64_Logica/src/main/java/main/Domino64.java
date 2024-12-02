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
    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        scheduler.schedule(() -> {
            System.out.println("Tarea ejecutada después de 10 segundos usando ScheduledExecutor");
            scheduler.shutdown(); // Importante hacer shutdown cuando ya no se necesite
        }, 5, TimeUnit.SECONDS);
        System.out.println("ingresa un numero");
        int op = scan.nextInt();
        
        System.out.println("op: "+op);
//        System.out.println("Iniciado juego en lógica");
//        MediadorManejadores.getInstance();
//        Control.subscribirManejadores();
//        
//        ControlEventos.mensajesInicio();
//        ControlEventos.procesarEventosALogica();
//        ControlEventos.mensajesOpcionesPartida();
//        Control.iniciarJuego();
    }
    
    private static void leerInt(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                try {
                    return scan.nextInt();
                } catch (Exception e) {
                    return null;
                }
            }
        });
        
    }
}
