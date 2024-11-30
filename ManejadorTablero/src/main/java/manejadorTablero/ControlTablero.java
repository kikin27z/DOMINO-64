package manejadorTablero;

import abstraccion.ICliente;
import domino64.eventos.base.Evento;
import implementacion.Client;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author karim
 */
public class ControlTablero extends IControlTablero {
    private ICliente cliente;
    private int id;
    private AtomicBoolean running;
    private static ExecutorService ejecutorEventos;
    
    
    
    @Override
    public void colocarFicha(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void manejarError(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void vincularCliente(Client _cliente) {
        this.cliente = _cliente;
        cliente.establecerSuscripciones(eventos);
        _cliente.iniciar();
//        id = _cliente.getClientId();
//        director = new DirectorLobby(new BuilderEventoLobby(), id);
//        ejecutorEventos.submit(this);
    }
    
}
