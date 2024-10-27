/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package client;

import event.Event;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import observer.Observable;

/**
 *
 * @author luisa M
 */
public class Client extends Observable<Event>{

    private String host;
    private final int port;
    private final String clientName;
    private Event event;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    /**
     * para conexion en equipos diferentes (diferentes ip)
     *
     * @param host
     * @param port
     * @param clientName
     * @param message
     */
    public Client(String host, int port, String clientName) {
        this.host = host;
        this.port = port;
        this.clientName = clientName;
    }

    /**
     * para conexion en el mismo equipo
     *
     * @param port
     * @param clientName
     */
    public Client(int port, String clientName) {
        this.port = port;
        this.clientName = clientName;
    }

    
    public void initClient() {
        final String HOST = "127.0.0.1";

        try {
            socket = new Socket("localhost", port);

            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Mensaje del server: " + input.readUTF());

            output.writeUTF(clientName);
            output.flush();
            listenForEvent();

        } catch (IOException ex) {
            closeClient();
        }
    }
    
    public void sendMessage(Event event) {
        try {
            output.writeObject(event);//envia msj al servidor
            output.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

    private void listenForEvent() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (!socket.isClosed()) {
                    try {
                        event = (Event)input.readObject();
                        System.out.println("mensaje recibido " + event);
                        notifyObservers(event);
                    } catch (ClassNotFoundException | IOException ex) {
                        closeClient();
                    }
                }

            }
        }).start();
    }

//    
//    public String getRecentMessage() {
//        return this.event;
//    }

    private void closeClient() {
        try {
            if (socket != null) {
                socket.close();
                System.out.println("se cerro el cliente");
            }

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
