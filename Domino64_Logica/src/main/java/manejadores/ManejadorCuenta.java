package manejadores;


import abstraccion.ICliente;
import entidades.Partida;
import eventos.EventoJugador;
import presentacion_utilities.MediadorModelos;
import presentacion_utilities.NotificadorEvento;
import utilities.BuilderEventoJugador;
import utilities.DirectorJugador;
import utilities.DirectorSuscripcion;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ManejadorCuenta  {

    
    private NotificadorEvento notificador;
    private MediadorModelos mediador;
    private ICliente cliente;
    private DirectorJugador directorEventos;
    private DirectorSuscripcion directorSuscripciones;
    

    public ManejadorCuenta() {
        notificador = NotificadorEvento.getInstance();
        mediador = MediadorModelos.getInstance();
        directorEventos = new DirectorJugador(new BuilderEventoJugador());
    }
    
    public void contruisr(String codigo){
        EventoJugador enviarMensaje = directorEventos.crearEventoUnirsePartida(new Partida(codigo));
        cliente.enviarEvento(enviarMensaje);
        mediador.respuesta(codigo);
    }
}
