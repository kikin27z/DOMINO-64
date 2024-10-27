/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverClient;

import eventBus.BusCore;
import eventBus.Event;
import eventBus.Publisher;
import eventBus.Subscriber;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisa M
 */
public class ClientHandler extends Publisher implements Runnable, Subscriber,Comparable<ClientHandler>{
    private final Socket clientSocket;
    private DataInputStream input;
    private DataOutputStream output;
    private String clientName;
    private final int clientId;
    
    public ClientHandler(BusCore bus, Socket clientSocket, int id){
        super(bus);
        this.clientSocket = clientSocket;
        clientId = id;
        initiStream();
    }
    
    private void initiStream(){
        try {
            input = new DataInputStream(clientSocket.getInputStream());
            output = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void run() {
        try {
            output.writeUTF("hola desde el server");
            output.flush();
            
            String clientMsg;
            clientName = input.readUTF();
            
            this.sendEvent(new NewClientEvent(this));
            
            while((clientMsg = input.readUTF()) != null){
                this.sendEvent(new NewMessageEvent(this, clientMsg));
            }

        } catch (IOException ex) {
            closeClientSocket();
        }
    }
    
    private void closeClientSocket(){
        try {
            clientSocket.close();
            System.out.println("cliente desconectado");
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

    @Override
    public void catchEvent(Event event) {
        String eventInfo = (String)event.getInfo();
        try {
            output.writeUTF(eventInfo);
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("-----------------------");
        System.out.println("Desde cliente "+clientName);
        System.out.println("evento recibido: "+eventInfo);
        System.out.println("-----------------------");
    }

    @Override
    public String getName() {
        return this.clientName;
    }

    @Override
    public int compareTo(ClientHandler handler) {
        return Integer.compare(this.clientId, handler.clientId);
    }

}
