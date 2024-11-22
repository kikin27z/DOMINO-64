package logicaLobby;

import abstraccion.ICliente;
import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import domino64.eventos.base.Evento;
import entidades.Cuenta;
import entidades.Lobby;
import entidadesDTO.AvatarDTO;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.UnirseDTO;
import eventos.EventoJugador;
import eventos.EventoLobby;
import eventos.EventoSuscripcion;
import eventoss.EventoMVCDisplay;
import eventoss.EventoMVCJugador;
import eventoss.TipoDisplayMVC;
import eventoss.TipoJugadorMVC;
import implementacion.Client;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import manejadores.Control;
import manejadores.ManejadorDisplay;
import manejadores.MediadorManejadores;
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
    private ICliente cliente;
    private DirectorJugador directorEventos;
    private DirectorSuscripcion directorSuscripciones;
    private AdaptadorEntidad adapterEntidad;
    private AdaptadorDTO adapterDTO;
    private ManejadorDisplay manejadorDisplay;
    private Cuenta cuenta;
    private LobbyDTO lobbyDTO;
    private Map<CuentaDTO, Boolean> jugadoresListos;
    private List<CuentaDTO> jugadoresLobby;
    
    public ManejadorCuenta() {
        super();
        cuenta = new Cuenta();
        cliente = Control.obtenerCliente();
        notificador = NotificadorEvento.getInstance();
        adapterEntidad = new AdaptadorEntidad();
        adapterDTO = new AdaptadorDTO();
        directorEventos = new DirectorJugador(new BuilderEventoJugador());
        manejadorDisplay = Control.obtenerManejadorDisplay();
        jugadoresLobby = new ArrayList<>();
        jugadoresListos = new HashMap<>();
        setConsumersMVC();
    }

    private void setConsumersMVC(){
        Control.agregarConsumer(TipoJugadorMVC.CREAR_PARTIDA, this::crearPartida);
        Control.agregarConsumer(TipoJugadorMVC.UNIRSE_PARTIDA, this::buscarPartida);
        Control.agregarConsumer(TipoJugadorMVC.ABANDONAR_PARTIDA, this::abandonarPartida);
        Control.agregarConsumer(TipoJugadorMVC.JUGADOR_LISTO, this::actualizarJugadorListo);
        Control.agregarConsumer(TipoJugadorMVC.JUGADOR_NO_LISTO, this::actualizarJugadorListo);
        Control.agregarConsumer(TipoJugadorMVC.CAMBIAR_AVATAR, this::actualizarAvatar);
    }
    
    private void actualizarAvatar(EventoMVCJugador evento){
        CuentaDTO cuentaDTO = evento.getPublicador();
        if(validarCambioAvatar(cuentaDTO.getAvatar())){
            cuenta.setAvatar(adapterDTO.adaptarAvatar(cuentaDTO.getAvatar()));
            EventoJugador cambioAv = directorEventos.crearEventoCambiarAvatar(lobbyDTO, cuentaDTO);
            
            MediadorManejadores.enviarADisplay(cambioAv);
            
            cliente.enviarEvento(cambioAv);
        }
        
    }
    
    private boolean validarCambioAvatar(AvatarDTO avatarElegido){
        for (CuentaDTO cuentaDTO : jugadoresLobby) {
            if(cuentaDTO.getAvatar().equals(avatarElegido))
                return false;
        }
        return true;
    }
    
    private void crearPartida(EventoMVCJugador evento){
        CuentaDTO cuentaDTO = adapterEntidad.adaptarEntidadCuenta(cuenta);
        EventoJugador crear = directorEventos.crearEventoCrearPartida(cuentaDTO);
        cliente.enviarEvento(crear);
        
        agregarSuscripcion(TipoLogicaLobby.PARTIDA_ENCONTRADA, this::recibirPartida);
    }

    private void buscarPartida(EventoMVCJugador evento) {
        LobbyDTO lobbyAux = evento.getLobby();
        cliente.enviarEvento(directorEventos.crearEventoUnirsePartida(
                        lobbyAux, adapterEntidad.adaptarEntidadCuenta(cuenta)));
        
        agregarSuscripcion(TipoLogicaLobby.PARTIDA_ENCONTRADA, this::recibirPartida);
    }

    private void abandonarPartida(EventoMVCJugador evento){
        MediadorManejadores.enviarADisplay(new EventoJugador(TiposJugador.ABANDONAR_PARTIDA));
        CuentaDTO cuentaDTO = adapterEntidad.adaptarEntidadCuenta(cuenta);
        EventoJugador eventoJ = directorEventos.crearEventoAbandonarPartida(lobbyDTO, cuentaDTO);
        cliente.enviarEvento(eventoJ);
    }
    
    private void actualizarJugadorListo(EventoMVCJugador evento){
        CuentaDTO cuentaDTO = adapterEntidad.adaptarEntidadCuenta(cuenta);
        
        boolean flag =evento.getTipo().equals(TipoJugadorMVC.JUGADOR_LISTO);
        EventoJugador ev = directorEventos.crearEventoActualizarJugadorListo(lobbyDTO,cuentaDTO, flag);
        
        MediadorManejadores.enviarADisplay(ev);

        cliente.enviarEvento(ev);
    }
    
    @Override
    public void recibirPartida(Evento evento) {
        System.out.println("partida recibida");
        Enum<?> tipo = evento.getTipo();
        if (tipo.equals(TipoLogicaLobby.PARTIDA_ENCONTRADA)) {
            EventoLobby eventoLobby = (EventoLobby) evento;
            System.out.println("evento: "+eventoLobby);
            
            lobbyDTO = eventoLobby.obtenerLobby();
            cuenta = adapterDTO.adaptarEntidadCuenta(lobbyDTO.getCuentaActual());
            
            jugadoresLobby = lobbyDTO.getCuentas();
            
            jugadoresListos = lobbyDTO.getMapaJugadoresListos();
            System.out.println("mapa en manejador C; " + jugadoresListos);
            //establecerJugadoresListos();
            
            System.out.println("lobbyDTO: "+lobbyDTO);
            MediadorManejadores.enviarADisplay(eventoLobby);
            
            removerSuscripcion(TipoLogicaLobby.PARTIDA_ENCONTRADA);
            //lobbyDTO.asignarIdJugadorActual(cuenta.getIdCadena());
//            manejadorDisplay.avisarMostrarLobby(lobbyDTO);
        }
    }

    private void establecerJugadoresListos(){
        List<CuentaDTO> jL = lobbyDTO.getJugadoresListos();
        if (!jL.isEmpty()) {
            for (CuentaDTO jugador : jugadoresLobby) {
                jugadoresListos.put(jugador, jL.contains(jugador));
            }
        } else {
            for (CuentaDTO jugador : jugadoresLobby) {
                jugadoresListos.put(jugador, false);
            }
        }
    }
    
    private void agregarSuscripcion(Enum<?> tipoEvento, Consumer<Evento> consumer){
        EventoSuscripcion suscripcion = directorSuscripciones.crearEventoSuscribirse(tipoEvento);
        cliente.agregarSuscripcion(suscripcion, this);
        agregarEvento(tipoEvento, consumer);
    }
    
    private void removerSuscripcion(Enum<?> tipoEvento){
        EventoSuscripcion desuscripcion = directorSuscripciones.crearEventoDesuscribirse(tipoEvento);
        cliente.removerSuscripcion(desuscripcion, this);
        removerEvento(tipoEvento);
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
        EventoLobby ev = esEventoDeEsteLobby(evento);
        if(ev != null){
            CuentaDTO jugadorEvento = ev.getPublicador();
            if(ev.getTipo().equals(TipoLogicaLobby.JUGADOR_LISTO)){
                jugadoresListos.compute(jugadorEvento, (j,b) -> b = true);
            }else
                jugadoresListos.compute(jugadorEvento, (j,b) -> b = false);
        }
        
        MediadorManejadores.enviarADisplay(ev);
    }

    @Override
    public void actualizarJugadores(Evento evento) {
        EventoLobby ev = esEventoDeEsteLobby(evento);
        if(ev != null){
            CuentaDTO jugadorEvento = ev.getPublicador();
            
            TipoLogicaLobby tipo = ev.getTipo();
            
            if (tipo.equals(TipoLogicaLobby.JUGADOR_SALIO)){
                removerJugador(jugadorEvento);
            }else if (tipo.equals(TipoLogicaLobby.JUGADOR_NUEVO)){
                agregarJugador(jugadorEvento);
            }
            
            MediadorManejadores.enviarADisplay(ev);
        }
    }

    private void agregarJugador(CuentaDTO jugadorNuevo) {
        jugadoresLobby.add(jugadorNuevo);
        System.out.println("un jugador nuevo: " + jugadorNuevo.getUsername());
    }

    private void removerJugador(CuentaDTO exjugador) {
        jugadoresLobby.remove(exjugador);
        System.out.println("salio un jugador: " + exjugador.getUsername());
        
    }
    
    @Override
    public void actualizarAvatares(Evento evento) {
        EventoLobby ev = esEventoDeEsteLobby(evento);
        if( ev != null){
            CuentaDTO jugadorEvento = ev.getPublicador();
            
            for (CuentaDTO cuentaDTO : jugadoresLobby) {
                if(cuentaDTO.equals(jugadorEvento)){
                    cuentaDTO.setAvatar(jugadorEvento.getAvatar());
                    MediadorManejadores.enviarADisplay(ev);
                    break;
                }
            }
        }
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

    private EventoLobby esEventoDeEsteLobby(Evento evento){
        EventoLobby ev = (EventoLobby) evento;
        LobbyDTO lDTO = ev.obtenerLobby();
        if(lDTO.getCodigoPartida().equals(this.lobbyDTO.getCodigoPartida()))
            return ev;
        return null;
    }
    
}
