package listener;

import domino64.eventos.base.Evento;
import entidadesDTO.AvatarDTO;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import eventoss.EventoMVCJugador;
import eventoss.TipoJugadorMVC;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author karim
 */
public class ControlEventos {
    private static final BlockingQueue<Evento> colaEventosAPresentacion = new LinkedBlockingQueue<>();
    private static final BlockingQueue<EventoMVCJugador> colaEventosALogica = new LinkedBlockingQueue<>();
    private static final Map<TipoJugadorMVC, Consumer<EventoMVCJugador>> consumersLogica = new ConcurrentHashMap<>();
    private static final Map<Enum, Consumer<Evento>> consumersPresentacion = new ConcurrentHashMap<>();
    private static final ExecutorService executor =Executors.newFixedThreadPool(3);
    private static final Scanner scan = new Scanner(System.in);
    
    public static void mensajesInicio(){
        System.out.println("------------------------");
        System.out.println("¨Presiona enter para iniciar el juego");
        scan.nextLine();
        System.out.println("------------------------");
    }
    
    public static void mensajesOpcionesPartida(){
        System.out.println("------------------------");
        int opcion;
        do {
            System.out.println("Presiona '1' para crear una partida");
            System.out.println("Presiona '2' para unirse a una partida");
            opcion = scan.nextInt();
            scan.nextLine();
        } while (!(opcion == 1 || opcion == 2));
        System.out.println("------------------------");
        
        EventoMVCJugador evento = new EventoMVCJugador();
        
        if (opcion == 1) {
            evento.setTipo(TipoJugadorMVC.CREAR_PARTIDA);
        } else {
            System.out.println("Ingresa el codigo de la partida que buscas:");
            String codigo = scan.nextLine();
            evento.setTipo(TipoJugadorMVC.UNIRSE_PARTIDA);
            LobbyDTO lobby = new LobbyDTO(codigo);
            evento.setLobby(lobby);
        }
        
        enviarEventoALogica(evento);
    }
    
    public static int mensajesLobby(boolean listoActualmente) {
        int opcion;
        String msjListo;
        if(listoActualmente)
            msjListo="no estas";
        else
            msjListo="estas";
        System.out.println("------------------------");
        do {
            System.out.println("Presiona 1 para abandonar la partida");
            System.out.println("Presiona 2 para indicar que "+ msjListo+ " listo");
            System.out.println("Presiona 3 para cambiar de avatar");
            opcion = scan.nextInt();
            scan.nextLine();
        } while (opcion > 3 || opcion == 0);

        EventoMVCJugador evento = new EventoMVCJugador();
        
        switch (opcion) {
            case 1 -> {
                evento.setTipo(TipoJugadorMVC.ABANDONAR_LOBBY);
            }
            case 2 -> {
                if(listoActualmente)
                    evento.setTipo(TipoJugadorMVC.JUGADOR_NO_LISTO);
                else
                    evento.setTipo(TipoJugadorMVC.JUGADOR_LISTO);
            }
            case 3 -> {
                evento.setTipo(TipoJugadorMVC.CAMBIAR_AVATAR);
            }
        }
        enviarEventoALogica(evento);
        return opcion;
    }
    
    public static int mensajesSeleccionAvatar(List<AvatarDTO> avataresLibres){
        List<String> nombresAvatares = new ArrayList<>();
        int opcion;
        do{
            for (AvatarDTO avatar : avataresLibres) {
                nombresAvatares.add(avatar.getAnimal());
            }
            System.out.println("------------------------");
            System.out.println("Presiona el numero indicando el avatar que quieres usar");
            imprimirAvatares(nombresAvatares);
            System.out.println("------------------------");
            
            opcion = scan.nextInt();
        }while(opcion==0 || opcion>nombresAvatares.size());
        
        return opcion;
    }
    
    public static void imprimirAvatares(List<String> nombresAvatares){
        String[] mensajes = nombresAvatares.toArray(String[]::new);

        for (int i = 0; i < mensajes.length; i++) {
            System.out.printf("%-15s", mensajes[i] + "(" + (i + 1) + ")");

            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
    }
    
    
    public static void procesarEventosAPresentacion() {
        executor.submit(() -> {
            while (true) {
                try {
                    Evento mensaje = colaEventosAPresentacion.take();
                    if (!consumersPresentacion.isEmpty()) {
                        Consumer<Evento> consumer = consumersPresentacion.get(mensaje.getTipo());
                        if (consumer != null) {
                            consumer.accept(mensaje);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public static void procesarEventosALogica() {
        executor.submit(() -> {
            while (true) {
                try {
                    EventoMVCJugador mensaje = colaEventosALogica.take();
                    System.out.println("after take: " + mensaje.getTipo());
                    if (!consumersLogica.isEmpty()) {
                        Consumer<EventoMVCJugador> consumer = consumersLogica.get(mensaje.getTipo());
                        if (consumer != null) {
                            consumer.accept(mensaje);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public static void enviarEventoALogica(EventoMVCJugador evento) {
        try {
            colaEventosALogica.put(evento);
        } catch (InterruptedException e) {
            Logger.getLogger(ControlEventos.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public static void enviarEventoAPresentacion(Evento evento) {
        try {
            colaEventosAPresentacion.put(evento);
        } catch (InterruptedException e) {
            Logger.getLogger(ControlEventos.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public static void agregarConsumerLogica(TipoJugadorMVC tipo, Consumer<EventoMVCJugador> consumer) {
        consumersLogica.putIfAbsent(tipo, consumer);
    }

    public static void removerConsumerLogica(TipoJugadorMVC tipo) {
        consumersLogica.remove(tipo);
    }
    
    public static void agregarConsumerPresentacion(Enum tipo, Consumer<Evento> consumer) {
        consumersPresentacion.putIfAbsent(tipo, consumer);
    }

    public static void removerConsumerPresentacion(Enum tipo) {
        consumersPresentacion.remove(tipo);
    }
}
