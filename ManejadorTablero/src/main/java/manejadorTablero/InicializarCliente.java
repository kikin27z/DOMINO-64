package manejadorTablero;

import implementacion.Client;
import static manejadorTablero.IControlTablero.eventos;
/**
 *
 * @author karim
 */
public class InicializarCliente {
    public static void main(String[] args) {
        Client c = Client.iniciarComunicacion();
        IControlTablero manejador = new ControlTablero();

        for (Enum suscripcion : eventos) {
            c.addObserver(suscripcion, manejador);
        }
        
        manejador.vincularCliente(c);
        
    }
}
