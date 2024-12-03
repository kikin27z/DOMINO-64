package manejadorTablero;

import implementacion.Client;
import java.util.LinkedList;
import static manejadorTablero.IControlTablero.eventos;
/**
 *
 * @author karim
 */
public class InicializarCliente {
    public static void main(String[] args) {
//        Client c = Client.iniciarComunicacion();
//        IControlTablero manejador = new ControlTablero();
//
//        for (Enum<?> suscripcion : eventos) {
//            c.addObserver(suscripcion, manejador);
//        }
//        
//        manejador.vincularCliente(c);
        LinkedList<String> turnos = new LinkedList<>();
turnos.add("Turno 1");
turnos.add("Turno 2");
turnos.add("Turno 3");

// Recorrer en un ciclo
for (int i = 0; i < 10; i++) {
    String turnoActual = turnos.get(i % turnos.size());
    System.out.println(turnoActual);
}
    }
}
