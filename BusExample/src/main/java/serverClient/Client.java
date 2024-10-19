/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisa M
 */
public class Client extends observer.Observable{
    private String host;
    private final int port;
    private final String clientName;
    private String message;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;

    /**
     * para conexion en equipos diferentes (diferentes ip)
     *
     * @param host
     * @param port
     * @param clientName
     * @param message
     */
    public Client(String host, int port, String clientName, String message) {
        this.host = host;
        this.port = port;
        this.clientName = clientName;
        this.message = message;
    }

    /**
     * para conexion en el mismo equipo
     *
     * @param port
     * @param clientName
     * @param message
     */
    public Client(int port, String clientName, String message) {
        this.port = port;
        this.clientName = clientName;
        this.message = message;
    }

    
    public void initClient() {
        final String HOST="127.0.0.1";
        
        try {
            socket = new Socket("localhost", port);
            
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            
            System.out.println("Mensaje del server: "+input.readUTF());
            
            output.writeUTF(clientName);
            output.flush();
            listenForMessage();
//            while (true) {
//                output.writeUTF(message);//envia msj al servidor
//                output.flush();
//                Thread.sleep(8000);
//            }
            
        } catch (IOException ex) {
            closeClient();
        }
    }

    public void sendMessage(String msg){
        try {
            output.writeUTF(msg);//envia msj al servidor
            output.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (!socket.isClosed()) {
                    try {
                        message = input.readUTF();
                        System.out.println("mensaje recibido " + message);
                        notifyObservers(message);
                    } catch (IOException e) {
                        closeClient();
                    }
                }

            }
        }).start();
    }
    
    public String getRecentMessage(){
        return this.message;
    }
    
    private void closeClient() {
        try {
            if(socket!=null){
                socket.close();
                System.out.println("se cerro el cliente");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }
    
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("ingresa tu nombre");
        String name = in.nextLine();
        System.out.println("ingresa el mensaje a enviar");
        String msg = in.nextLine();
        Client client= new Client(5000,name, msg);
        client.initClient();
    }
}
