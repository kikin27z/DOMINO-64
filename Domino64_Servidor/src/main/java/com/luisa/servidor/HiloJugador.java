/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luisa.servidor;

import com.domino64.base.Publicador;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.TipoError;
import eventBus.Subscriber;
import eventos.EventoLogico;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tiposLogicos.TipoSuscripcion;

/**
 *
 * @author luisa M
 */
public class HiloJugador implements Runnable, Subscriber{
    private final int id;
    private Socket socket;
    private final Publicador publicador;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private List<Enum<?>> suscripciones;
    
    public HiloJugador(Publicador publicador, Socket socket, int id) {
        this.publicador = publicador;
        this.id = id;
        
        this.socket = socket;
        initStream();
    }

    protected Socket getSocket(){
        return socket;
    }
    
    private void initStream(){
        if(socket!=null){
            try {
                output = new ObjectOutputStream(socket.getOutputStream());
                input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(HiloJugador.class.getName()).log(Level.SEVERE, null, ex);
                Servidor.desconectarJugador(id);
            }
        }
    }
    
    /**
     * Metodo usado para enviarle al cliente (al jugador) los eventos 
     * recibidos del bus.
     * 
     * @param evento Evento recibido y que debe enviar al jugador
     */
    private void enviarEvento(Evento evento){
        try {
            synchronized (output) {
                output.writeObject(evento);
                output.flush();
            }
            
        } catch (IOException e) {
            Logger.getLogger(HiloJugador.class.getName()).log(Level.SEVERE, null, e);
            Servidor.desconectarJugador(id);
        }
    }
    
    /**
     * Este metodo recibe la lista de suscripciones del jugador.
     * Son todos los tipos de eventos que le interesa recibir al jugador,
     * por lo tanto, al obtener la lista, esta clase se suscribe a dichos eventos
     * para recibir los eventos del bus y despues enviarselos al jugador 
     * mediante el socket.
     * 
     * @throws IOException En caso de que ocurra un error al leer las suscripciones recibidas
     * @throws ClassNotFoundException En caso de que el objeto recibido no sea
     * una lista de enum y genere un error de casteo
     */
    private void recibirSuscripciones() throws IOException, ClassNotFoundException{
        suscripciones = null;
        suscripciones = (List<Enum<?>>) input.readObject();

        if (suscripciones != null) {
            for (Enum<?> suscripcion : suscripciones) {
                suscribirEvento(suscripcion);
            }
        }
    }
    
    /**
     * suscribe esta clase al evento especificado en 
     * el parametro
     * 
     * @param tipoEvento Tipo de evento al cual se va a suscribir
     */
    private void suscribirEvento(Enum<?> tipoEvento){
       publicador.suscribir(tipoEvento,this);
    }
    
    /**
     * desuscribe esta clase del evento especificado en 
     * el parametro
     * 
     * @param tipoEvento Tipo de evento al cual se va a desuscribir
     */
    private void removerSuscripcion(Enum<?> tipoEvento){
        publicador.desuscribir(tipoEvento,this);
    }

    /**
     * remueve las suscripciones de esta clase de todos los 
     * eventos en el bus
     */
    private void removerSuscripciones(){
        for (Enum<?> suscripcion : suscripciones) {
            removerSuscripcion(suscripcion);
        }
    }
    
    private void manejarEvento(EventoLogico evento){
        Enum<?> tipo = evento.getTipo();
        System.out.println("tipo en hilo jugador: "+tipo);
        if(tipo.equals(TipoSuscripcion.SUSCRIBIR)){
            suscribirEvento((Enum<?>)evento.getInfo());
        }else if(tipo.equals(TipoSuscripcion.DESUSCRIBIR)){
            removerSuscripcion((Enum<?>)evento.getInfo());
        }else{
            publicador.publicarEvento(tipo, evento);
            System.out.println("se publico desde el hilo del jugador");
        }
    }
    
    @Override
    public void run() {
        try {
            //enviar el id asignado al cliente
            output.writeInt(id);
            output.flush();
            
            recibirSuscripciones();
            
            EventoLogico evento;
            while((evento = (EventoLogico)input.readObject()) != null){
                manejarEvento(evento);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(HiloJugador.class.getName()).log(Level.SEVERE, null, ex);
            removerSuscripciones();
            Servidor.desconectarJugador(id);
        }
    }

    @Override
    public int getSubscriberId() {
        return id;
    }

    /**
     * Metodo para comparar este suscriptor con otro.
     * El criterio de comparacion son los id de cada suscriptor.
     * 
     * @param other El suscriptor con el cual se va a comparar esta clase suscriptora
     * @return 0 si este suscriptor tiene el mismo id que el suscriptor del parametro,
     * -1 si el id de este suscriptor es menor que el id del suscriptor del parametro,
     * y 1 si el id de este suscriptor es mayor que el id del suscriptor del parametro
     */
    @Override
    public int compareTo(Subscriber other) {
        return Integer.compare(id, other.getSubscriberId());
    }

    /**
     * metodo para recibir los eventos que lleguen del bus.
     * Una vez que recibe el evento, se lo envia al componente
     * mediante el socket.
     * En caso de que el evento sea uno de tipo error de servidor, se
     * va a cerrar la conexion con el servidor
     * 
     * @param evento Evento recibido del bus
     */
    @Override
    public void recibirEvento(Evento evento) {
        enviarEvento(evento);
        if(evento.getTipo().equals(TipoError.ERROR_DE_SERVIDOR)){
            removerSuscripciones();
            Servidor.desconectarJugador(id);
        }
    }

}
