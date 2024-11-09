/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package implementacion;

import abstraccion.ICliente;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import observer.Observable;
import observer.Observer;

/**
 *
 * @author luisa M
 */
public class Client extends Observable<Evento> implements ICliente{
    private static Client cliente;
    private String host;
    private final int port;
    private int clientId;
    private final ScheduledExecutorService ejecutorReconexion;
    private final ExecutorService ejecutorEventos;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private volatile boolean running;
    private volatile boolean connected;
    private List<Enum<?>> suscripcionesEventos;

    /**
     * para conexion en equipos diferentes (diferentes ip)
     *
     * @param host
     * @param port
     */
    private Client(String host, int port) {
        this.host = host;
        this.port = port;
        
        this.ejecutorReconexion = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r);
            return t;
        });
        
        this.ejecutorEventos = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            return t;
        });
        
        suscripcionesEventos = new ArrayList<>();
    }

    /**
     * para conexion en el mismo equipo
     *
     * @param port
     */
    private Client(int port) {
        this.port = port;
        
        this.ejecutorReconexion = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r);
            return t;
        });
        
        this.ejecutorEventos = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            return t;
        });
        
        suscripcionesEventos = new ArrayList<>();
        
    }
    
    public static synchronized Client getClient(int port){
        if(cliente == null)
            cliente = new Client(port);
        return cliente;
    }
    
    public static synchronized Client getClient(String host, int port){
        if(cliente == null)
            cliente = new Client(host, port);
        return cliente;
    }
    
    public int getClientId(){
        return clientId;
    }
    
    public void iniciar(){
        running = true;
        conectarCliente();
        
        ejecutorEventos.submit(this::listenForEvent);
    }
    
    private void conectarCliente(){
        try {
            socket = new Socket("localhost", port);
            System.out.println("en el run");
            
            output = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("se inicio");
            
            input = new ObjectInputStream(socket.getInputStream());
            
            connected = true;
            
            //recibir el id del cliente
            clientId = input.readInt();
            System.out.println("id cliente: "+clientId);
            
            enviarSuscripciones();
            
//            listenForEvent();
            
        }catch (IOException ex){
            System.out.println("Error al conectar cliente "+clientId);
            manejarDesconexion();
        }
    }
    
    private void reconexionProgramada(){
        if(running && !connected){ 
            ejecutorReconexion.schedule(() -> {
                System.out.println("intentando reconectar...");
                conectarCliente();
            }, 5, TimeUnit.SECONDS);
        }
    }
    
    private void enviarSuscripciones(){
        if(suscripcionesEventos != null && !suscripcionesEventos.isEmpty()){
            if(connected){
                try {
                    synchronized (output) {
                        output.writeObject(suscripcionesEventos);
                        output.flush();
                        System.out.println("se enviaron las subs");
                    }
                } catch (IOException e) {
                    System.out.println("error al enviar suscripciones: " + e.getMessage());
                    manejarDesconexion();
                }
            }
        }
    }
    
    @Override
    public void enviarEvento(Evento event) {
        if(connected){
            try {
                synchronized (output) {
                    output.writeObject(event);//envia msj al servidor
                    output.flush();
                }
            } catch (IOException ex) {
                System.out.println("error al enviar evento: " + ex.getMessage());
                manejarDesconexion();
            }
        }
    }

    @Override
    public void establecerSuscripciones(List<Enum<?>> subs){
        for (Enum<?> sub : subs) {
            suscripcionesEventos.add(sub);
        }
    }
    
    /**
     * escucha eventos que le llegan del servidor
     * y se los notifica al componente observador
     */
    private void listenForEvent() {
        while(running){
            if(connected && input != null){
                Evento evento;
                try {
                    System.out.println("en el listen for events");
                    evento = (Evento)input.readObject();
                    manejarEvento(evento);
                } catch (IOException e) {
                    manejarDesconexion();
                }catch (ClassNotFoundException ex){
                    System.err.println("Error al recibir un evento: "+ex.getMessage());
                }
            }else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    
    private void manejarEvento(Evento evento){
//        if(evento instanceof EventoError eventoError){
//            evento = eventoError;
//        }else if( evento instanceof EventoLogico eventoLogico)
//            evento = eventoLogico;
        System.out.println("mensaje recibido " + evento);
        
        notifyObservers(evento.getTipo(), evento);
        System.out.println("se notifico a observers");
    }
    
    private void manejarDesconexion(){
        connected = false;
        try {
            if (socket != null)socket.close();
            if(input != null)input.close();
            if(output != null)output.close();
            System.out.println("cliente desconectado");
        } catch (IOException e) {}
        
        //reconexionProgramada();
    }
    
//    private void closeClient() {
//        try {
//            if (socket != null) {
//                socket.close();
//                System.out.println("se cerro el cliente");
//            }
//
//        } catch (IOException ex) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, ex.getMessage());
//        }
//    }

    @Override
    public void agregarSuscripcion(Evento evento, Observer ob) {
        suscripcionesEventos.add((Enum<?>)evento.getInfo());
        addObserver((Enum<?>)evento.getInfo(), ob);
        enviarEvento(evento);
    }

}
