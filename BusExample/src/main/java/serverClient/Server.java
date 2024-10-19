/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverClient;

import eventBus.BusCore;
import eventBus.EventType;
import eventBus.Publisher;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisa M
 */
public class Server{
    private static BusCore bus;
    private final int port;
    private ServerSocket servidor;
    private int clientId = 0;
    
    public Server(int port) {
        this.port = port;
        this.bus = BusCore.getInstance();
    }

    public void initServer() {
        
        Socket socketCliente;
        try {
            servidor = new ServerSocket(port);
            System.out.println("Servidor iniciado");

            while (true) {
                socketCliente = servidor.accept();
                System.out.println("Cliente conectado");
                
                /**
                 * los datainput y dataoutput funcionan como puente entre los
                 * sockets, son para su comunicacion. El inputStream es para del
                 * cliente al servidor, y el outputStream es para ir del
                 * servidor al cliente
                 */
                clientId++;
                ClientHandler handler = new ClientHandler(bus,socketCliente,clientId);
                Thread thread = new Thread(handler);
                
                bus.addSub(handler, EventType.NEW_CLIENT);
                bus.addSub(handler, EventType.NEW_MESSAGE);
                
                thread.start();
            }
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
            closeServer();
        }
    }

    private void closeServer(){
        try {
            servidor.close();
            System.out.println("se cerro el server");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
