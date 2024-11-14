package logicaLobby;

import abstraccion.ICliente;
import adapter.AdaptadorEntidad;
import domino64.eventos.base.Evento;
import entidades.Cuenta;
import entidades.Partida;
import entidadesDTO.AvatarDTO;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.PartidaDTO;
import entidadesDTO.UnirseDTO;
import eventos.EventoJugador;
import eventos.EventoLobby;
import eventos.EventoSuscripcion;
import implementacion.Client;
import java.util.ArrayList;
import java.util.List;
import manejadores.Control;
import manejadores.ManejadorDisplay;
import observer.Observer;
import presentacion_utilities.MediadorModelos;
import presentacion_utilities.NotificadorEvento;
import tiposLogicos.TipoLogicaLobby;
import tiposLogicos.TiposJugador;
import utilities.BuilderEventoJugador;
import utilities.BuilderEventoSuscripcion;
import utilities.DirectorJugador;
import utilities.DirectorSuscripcion;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ManejadorCuenta extends ObservadorLobbyLocal {

    private NotificadorEvento notificador;
    private MediadorModelos mediador;
    private ICliente cliente;
    private DirectorJugador directorEventos;
    private DirectorSuscripcion directorSuscripciones;
    private AdaptadorEntidad adapter;
    private ManejadorDisplay manejadorDisplay;
    private Cuenta cuenta;

    public ManejadorCuenta() {
        super();
        cuenta = new Cuenta();
        cliente = Control.obtenerCliente();
        notificador = NotificadorEvento.getInstance();
        adapter = new AdaptadorEntidad();
        mediador = MediadorModelos.getInstance();
        directorEventos = new DirectorJugador(new BuilderEventoJugador());
        manejadorDisplay = Control.obtenerManejadorDisplay();

    }

    
    public void crearPartida(){
        //Partida partida = new Partida(new ArrayList<Cuenta>(List.of(cuenta)), 4);
        //PartidaDTO partidaDto = adapter.adaptarEntidadPartida(partida);
        CuentaDTO cuentaDTO = adapter.adaptarEntidadCuenta(cuenta);
        EventoJugador crear = directorEventos.crearEventoCrearPartida(cuentaDTO);
        cliente.enviarEvento(crear);
        
        EventoSuscripcion suscripcion = directorSuscripciones.crearEventoSuscribirse(TipoLogicaLobby.PARTIDA_ENCONTRADA);
        cliente.agregarSuscripcion(suscripcion, this);
//        LobbyDTO lobby = new LobbyDTO(partida.getCodigoPartida());
//        CuentaDTO cuenta1 = new CuentaDTO();
//        cuenta1.setIdCadena(cuenta.getIdCadena());
//        lobby.setCuentaActual(cuenta1);
//        lobby.setCuentas(List.of(cuenta1));
//        manejadorDisplay.avisarMostrarLobby(lobby);
    }

//    public void crearPartida2() {
//        CuentaDTO cuenta1 = new CuentaDTO();
//        cuenta1.setIdCadena(cuenta.getIdCadena());
//        cuenta1.setAvatar(AvatarDTO.AVE);
//        LobbyDTO lobby = new LobbyDTO(cuenta1);
//        lobby.setCuentaActual(cuenta1);
//        lobby.setCuentas(List.of(cuenta1));
//        EventoJugador crear = directorEventos.irEventoLobby(lobby, cuenta);
//        cliente.enviarEvento(crear);
//
//        manejadorDisplay.avisarMostrarLobby(lobby);
//    }

    public void buscarPartida(UnirseDTO unirseDTO) {

    }

    @Override
    public void recibirPartida(Evento evento) {
        Enum<?> tipo = evento.getTipo();
        if (tipo.equals(TipoLogicaLobby.PARTIDA_ENCONTRADA)) {
            EventoLobby eventoLobby = (EventoLobby) evento;
            LobbyDTO lobby = (LobbyDTO)eventoLobby.getInfo();
            
            lobby.asignarIdJugadorActual(cuenta.getIdCadena());
            manejadorDisplay.avisarMostrarLobby(lobby);
        }
    }

    public void init(Client cliente) {
        this.cliente = cliente;
        directorEventos = new DirectorJugador(new BuilderEventoJugador());
        cliente.establecerSuscripciones(eventos);
        setConsumers();
    }

    public void setClientId(int id) {
        this.cuenta.setId(id);
        directorSuscripciones = new DirectorSuscripcion(new BuilderEventoSuscripcion(), id);
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

    public void setManejadorDisplay(ManejadorDisplay manejadorDisplay) {
        this.manejadorDisplay = manejadorDisplay;
    }

}
