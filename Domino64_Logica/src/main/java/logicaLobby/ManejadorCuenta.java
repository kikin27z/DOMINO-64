package logicaLobby;

import abstraccion.ICliente;
import domino64.eventos.base.Evento;
import entidades.Cuenta;
import entidades.Partida;
import entidadesDTO.LobbyDTO;
import entidadesDTO.UnirseDTO;
import eventos.EventoJugador;
import eventos.EventoLobby;
import manejadores.Control;
import manejadores.ManejadorDisplay;
import presentacion_utilities.MediadorModelos;
import presentacion_utilities.NotificadorEvento;
import tiposLogicos.TipoLogicaLobby;
import utilities.BuilderEventoJugador;
import utilities.DirectorJugador;
import utilities.DirectorSuscripcion;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ManejadorCuenta  extends ObservadorLobbyLocal{
    private NotificadorEvento notificador;
    private MediadorModelos mediador;
    private ICliente cliente;
    private DirectorJugador directorEventos;
    private DirectorSuscripcion directorSuscripciones;
    private ManejadorDisplay manejadorDisplay;
    private Cuenta cuenta;

    public ManejadorCuenta() {
        cuenta = new Cuenta();
        cliente = Control.obtenerCliente();
        notificador = NotificadorEvento.getInstance();
        mediador = MediadorModelos.getInstance();
        directorEventos = new DirectorJugador(new BuilderEventoJugador());
        manejadorDisplay = Control.obtenerManejadorDisplay();
    }
    
    public void contruisr(String codigo){
        EventoJugador enviarMensaje = directorEventos.crearEventoUnirsePartida(new Partida(codigo));
        cliente.enviarEvento(enviarMensaje);
        mediador.respuesta(codigo);
    }
    
    public void crearPartida(){
        
        
    }
    
    public void buscarPartida(UnirseDTO unirseDTO){
        
    }

    @Override
    public void recibirPartida(Evento evento) {
        LobbyDTO lobby = new LobbyDTO("");
        Enum<?> tipo = evento.getTipo();
        if(tipo.equals(TipoLogicaLobby.PARTIDA_ENCONTRADA)){
            EventoLobby eventoLobby = (EventoLobby) evento;
            
            
            lobby.asignarIdJugadorActual(cuenta.getIdCadena());
            manejadorDisplay.avisarMostrarLobby(null);
        }
    }

    @Override
    public void actualizarJugadoresListos(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarJugadores(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarAvatares(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarUsernames(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void manejarError(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
