package manejadorTablero;

import implementacion.Client;
import java.util.Scanner;
import static manejadorTablero.IControlTablero.eventos;
/**
 *
 * @author karim
 */
public class InicializarCliente {
    public static void main(String[] args) {
        String ip = pedirIP();
        Client c = Client.getClient(ip, 5000);
        IControlTablero manejador = new ControlTablero();

        for (Enum<?> suscripcion : eventos) {
            c.addObserver(suscripcion, manejador);
        }
        
        manejador.vincularCliente(c);
        
    }
    
     private static String pedirIP() {
        Scanner input = new Scanner(System.in);
        System.out.print("Escribe la ip del servidor: ");
        String ip = input.nextLine();
        
        if(ip.isBlank()){
            return "localhost";
        }
        return ip;

    }
}
