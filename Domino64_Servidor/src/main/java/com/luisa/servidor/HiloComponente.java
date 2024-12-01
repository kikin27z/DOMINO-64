/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luisa.servidor;

import com.domino64.base.Publicador;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.TipoError;
import eventBus.Subscriber;
import eventos.EventoJugador;
import eventos.EventoLobby;
import eventos.EventoLogico;
import eventos.EventoTurno;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import tiposLogicos.TipoLogicaLobby;
import tiposLogicos.TipoLogicaTurno;
import tiposLogicos.TipoSuscripcion;
import tiposLogicos.TiposJugador;

/**
 * Esta clase representa un hilo se encarga de manejar la comunicacion mediante 
 * el socket con el cliente de cada componente de la logica compartida.
 * Esta clase es una suscriptora, lo que quiere decir que va a poder estar
 * suscrita a eventos especificos en el bus. Cuando en el bus se publique un evento 
 * al cual esta clase este suscrita, el evento se va a recibir aqui con el 
 * metodo recibirEvento de la interfaz Subscriber. Cada vez que reciba el evento
 * del bus, se lo va a enviar al cliente del componente mediante el socket.
 * Como un componente tambien puede publicar eventos en el bus, esta clase tiene una
 * instancia de un Publicador. Para publicar un evento, el componente le debe enviar
 * el evento por el cliente; dicho evento lo va a recibir esta clase en el metodo run.
 * Una vez que recibe el evento que le envio el cliente (el componente), esta clase
 * usa la instancia del publicador para publicar el evento en el bus.
 * 
 * @author luisa M
 */
public class HiloComponente  implements Runnable, Subscriber{
    private final Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private final int id;
    private final int idContexto = 0;
    private Publicador publicador;
    private List<Enum> suscripciones;
    private AtomicBoolean running;
    private final BlockingQueue<Evento> colaEventos;
    
    public HiloComponente(Publicador publicador, Socket socket, int id){
        this.publicador = publicador;
        this.id = id;
        
        this.socket = socket;
        colaEventos = new LinkedBlockingQueue();
        running = new AtomicBoolean(true);
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
                Logger.getLogger(HiloComponente.class.getName()).log(Level.SEVERE, null, ex);
                Servidor.desconectarComponente(id);
            }
        }
    }
    
    /**
     * metodo que se ejecuta cada vez que recibe un evento
     * por parte del cliente.
     * Obtiene el tipo de evento especifico, y a partir del tipo,
     * se determina la accion correspondiente.
     * Si es un evento para suscribirse o desuscribirse a otro tipo de evento,
     * ejecuta el metodo para suscribir o desuscribir, respectivamente.
     * En caso de que sea un metodo de cualquier otro tipo, lo publica en el bus.
     * 
     * @param evento Evento a manejar
     */
    private void manejarEvento(Evento evento) {
        System.out.println("------");
        System.out.println("mensaje recibido: "+evento.getInfo());
        System.out.println("componente: "+id);
        System.out.println("------");
        Enum tipo = evento.getTipo();
        System.out.println("tipo: " + tipo);
        if (tipo.equals(TipoSuscripcion.SUSCRIBIR)) {
            suscribirEvento(tipo);
        } else if (tipo.equals(TipoSuscripcion.DESUSCRIBIR)) {
            removerSuscripcion(tipo);
        } else {
            publicador.publicarEvento(tipo, evento);
        }
    }
        
    /**
     * tarea principal que se va a estar ejecutando durante toda la 
     * vida de la clase. 
     * Este hilo va a estar recibiendo eventos del cliente hasta que 
     * ocurra un error de I/O, ya sea porque el componente se cerro
     * inesperadamente, hubo un error del lado del servidor, o porque ocurrio
     * un error al intentar leer el evento recibido.
     * En caso de que ocurra un error, se va a cerrar el socket y se van 
     * a remover las suscripciones del bus.
     */
    @Override
    public void run() {
        try {
            //enviar el id asignado al cliente
            output.writeInt(id);
            output.flush();
            
            recibirSuscripciones();
            Thread t = new Thread(this::sendEvent);
            t.setName("hilo 2");
            t.start();
            System.out.println("¡???¡¡¡");
            Evento evento;
            while((evento = (Evento)input.readObject()) != null){
                manejarEvento(evento);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(HiloComponente.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
            removerSuscripciones();
            Servidor.desconectarComponente(id);
        }
    }
    
    /**
     * Este metodo recibe la lista de suscripciones del componente.
     * Son todos los tipos de eventos que le interesa recibir al componente,
     * por lo tanto, al obtener la lista, esta clase se suscribe a dichos eventos
     * para recibir los eventos del bus y despues enviarselos al componente 
     * mediante el socket.
     * 
     * @throws IOException En caso de que ocurra un error al leer las suscripciones recibidas
     * @throws ClassNotFoundException En caso de que el objeto recibido no sea
     * una lista de enum y genere un error de casteo
     */
    private void recibirSuscripciones() throws IOException, ClassNotFoundException{
        suscripciones = null;
        suscripciones = (List<Enum>)input.readObject();
        
        if(suscripciones != null){
            for (Enum suscripcion : suscripciones) {
                suscribirEvento(suscripcion);
            }
        }
    }
    /**
     * remueve las suscripciones de esta clase de todos los 
     * eventos en el bus
     */
    private void removerSuscripciones(){
        for (Enum suscripcion : suscripciones) {
            removerSuscripcion(suscripcion);
        }
    }
    
    /**
     * suscribe esta clase al evento especificado en 
     * el parametro
     * 
     * @param tipoEvento Tipo de evento al cual se va a suscribir
     */
    private void suscribirEvento(Enum tipoEvento) {
        publicador.suscribir(tipoEvento, this);
        //servidor.suscribirComponente(id, tipoEvento);
    }

    /**
     * desuscribe esta clase del evento especificado en 
     * el parametro
     * 
     * @param tipoEvento Tipo de evento al cual se va a desuscribir
     */
    private void removerSuscripcion(Enum tipoEvento) {
        publicador.desuscribir(tipoEvento, this);
        //servidor.removerSuscripcionComponente(id, tipoEvento);
    }
    
    /**
     * Metodo usado para enviarle al cliente (al componente) los eventos 
     * recibidos del bus.
     * 
     * @param evento Evento recibido y que debe enviar al componente
     */
    private void agregarEventoACola(Evento evento) {
        colaEventos.offer(evento);
    }

    private void sendEvent(){
        try {
            //output = new ObjectOutputStream(socket.getOutputStream());
            while (running.get()) {
                Evento evento = colaEventos.take();
                synchronized (output) {
                    System.out.println("evento recibido del bus en el HiloCom " + id + ": " + evento.getInfo());
                    output.reset();
                    output.writeObject(evento);
                    output.flush();
                }
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(HiloComponente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            running = new AtomicBoolean(false);
        }
        
    }
    
    @Override
    public int getIdContexto(){
        return idContexto;
    }
    
    @Override
    public int getSubscriberId() {
        return id;
    }

    /**
     * metodo para recibir los eventos que lleguen del bus.
     * Una vez que recibe el evento, se lo envia al componente
     * mediante el socket.
     * En caso de que el evento sea uno de tipo error de servidor, se
     * va a cerrar la conexion con el servidor
     * @param evento Evento recibido del bus
     */
    @Override
    public void recibirEvento(Evento evento) {
        agregarEventoACola(evento);
        if(evento.getTipo().equals(TipoError.ERROR_DE_SERVIDOR)){
            removerSuscripciones();
            Servidor.desconectarComponente(id);
        }
    }

    /**
     * Metodo para comparar este suscriptor con otro.
     * El criterio de comparacion son los id de cada suscriptor.
     * @param other El suscriptor con el cual se va a comparar esta clase suscriptora
     * @return 0 si este suscriptor tiene el mismo id que el suscriptor del parametro,
     * -1 si el id de este suscriptor es menor que el id del suscriptor del parametro,
     * y 1 si el id de este suscriptor es mayor que el id del suscriptor del parametro
     */
    @Override
    public int compareTo(Subscriber other) {
        return Integer.compare(id, other.getSubscriberId());
    }

}
