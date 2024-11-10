/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luisa.servidor;

import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import domino64.eventos.base.error.TipoError;
import eventBus.BusCore;
import eventBus.Publisher;
import eventBus.Subscriber;
import eventos.EventoJugador;
import eventos.EventoLogico;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tiposLogicos.TipoLogicaLobby;
import tiposLogicos.TiposJugador;

/**
 *
 * @author luisa M
 * @deprecated Ya no se usa esta clase
 */
public class ClientHandler extends Publisher implements Subscriber<EventoLogico>{
    private final Socket clientSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private int clientId;
    private String clientName;
    private EventoLogico evento;
    
    public ClientHandler(BusCore bus, Socket clientSocket){
        super(bus);
        this.clientSocket = clientSocket;
    }
    
    private void initiStream(){
        try {
            System.out.println("antes init");
            input = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("mmm");
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            System.out.println("after init");
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void init(){
        try {
            initiStream();
            output.writeUTF("hola desde el server");
            System.out.println("se envio el mensaje ");
            output.flush();

            clientId = input.readInt();

            this.setId(clientId);
            
            recibirEventos();

        } catch (IOException | ClassNotFoundException ex) {
            closeClientSocket();
        }
    }
    
    /**
     * eventos que le envia el cliente
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void recibirEventos() throws IOException, ClassNotFoundException{
        ClientHandler handler = this;
        new Thread(new Runnable() {
            EventoLogico eventoCliente;
            @Override
            public void run() {
                try {
                    while ((eventoCliente = (EventoLogico) input.readObject()) != null) {
                        publicarEvento(eventoCliente.getTipo(),eventoCliente);//envia el evento al bus
                        if (eventoCliente.getTipo().equals(TiposJugador.UNIRSE_PARTIDA)) {
                            /*
                            si el evento que le envio el cliente es para unirse a una partida,
                            se agrega este suscriptor (la clase en si) a la lista de espera
                            a que se publique un evento de tipo unirse partida
                            */
                            //waitEvent(handler, eventoCliente);
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
    
    private void notificarError(TipoError tipo, String msjError){
        EventoError error = new EventoError(tipo, msjError);
    }
    
    /**
     * metodo para enviarle al cliente medianete el socket,
     * los eventos que llegan del bus
     */
//    @Override
    public void run() {
        enviarEvento(this.evento);
    }
    
    private void enviarEvento(Evento evento){
        try {
            output.writeObject(evento);
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
        System.out.println("-----------------------");
        System.out.println("Desde cliente " + clientId);
        System.out.println("evento recibido: " + evento.getInfo());
        System.out.println("-----------------------");
    }
    
    /**
     * cierra el socket en caso de que ocurra un error
     * Normalmente se usa cuando se termina la ejecucion del
     * proyecto
     */
    private void closeClientSocket(){
        try {
            clientSocket.close();
            System.out.println("cliente desconectado");
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

    @Override
    public int compareTo(Subscriber handler) {
        return Integer.compare(clientId, handler.getSubscriberId());
    }

    @Override
    public int getIdPublicador() {
        return clientId;
    }
    
    @Override
    public int getSubscriberId(){
        return clientId;
    }
    
    /**
     * este metodo es llamado por el mensajero del bus para recibir el evento.
     * Con el evento del parametro se inicializa el evento de la clase, el cual
     * se va a enviar al cliente por el socket
     *
     * @param event Evento enviado desde el bus
     */
    @Override
    public void recibirEvento(Evento evento) {
        this.evento = (EventoLogico)evento;
    }

}
