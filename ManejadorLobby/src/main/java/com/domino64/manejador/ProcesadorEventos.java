/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.domino64.manejador;

import abstraccion.ICliente;
import static com.domino64.manejador.ObservadorLobby.eventos;
import implementacion.Client;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import lobbyBuilder.BuilderEventoLobby;
import lobbyBuilder.DirectorLobby;

/**
 *
 * @author luisa M
 */
public class ProcesadorEventos {
    private ExecutorService ejecutorEventos;
    private static AtomicBoolean isRunning;

    public ProcesadorEventos() {
        ejecutorEventos = Executors.newFixedThreadPool(4);
        isRunning = new AtomicBoolean(true);
    }

    public void start(ManejadorLobby m) {
        ejecutorEventos.submit(m);
//            
//            for (int i = 0; i < 4; i++) {
//                
//                ejecutorEventos.submit(new ManejadorLobby(isRunning));
//            }
    }
    
    public static void main(String args[]){
//        ProcesadorEventos procesador = new ProcesadorEventos();
//        Client c = Client.getClient(5000);
//        ManejadorLobby manejador = new ManejadorLobby(isRunning);
//
//        for (Enum<?> suscripcion : eventos) {
//            c.addObserver(suscripcion, manejador);
//        }
//
//        //cliente = c;
//        manejador.setCliente(c);
//
//        c.iniciar();
//
//        manejador.setIdManejador(c.getClientId());
//
//        DirectorLobby director = new DirectorLobby(new BuilderEventoLobby(), manejador.getIdManejador());
//        manejador.setDirector(director);
//        
//        procesador.start(manejador);
    }
}
