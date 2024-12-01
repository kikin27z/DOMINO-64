package logicaLobby;

import abstraccion.ICliente;
import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
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

    private ICliente cliente;
    private DirectorEventosLobby directorEventos;
    private DirectorSuscripcion directorSuscripciones;
    private AdaptadorEntidad adapterEntidad;
    private AdaptadorDTO adapterDTO;
    private Cuenta cuenta;
    private LobbyDTO lobbyDTO;
    private Map<CuentaDTO, Boolean> jugadoresListos;
    private List<CuentaDTO> jugadoresLobby;
    
    public ManejadorCuenta() {
        super();
        cuenta = new Cuenta();
        cliente = Control.obtenerCliente();
        adapterEntidad = new AdaptadorEntidad();
        adapterDTO = new AdaptadorDTO();
        directorEventos = new DirectorEventosLobby(new BuilderEventoJugador());
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
        Control.agregarConsumer(TipoJugadorMVC.CAMBIAR_CONFIG_PARTIDA, this::actualizarConfigPartida);
    }
    
    public void actualizarConfigPartida(EventoMVCJugador evento){
        LobbyDTO lobbyAct = evento.getLobby();
        lobbyDTO.setCantidadFichas(lobbyAct.getCantidadFichas());
        EventoJugador cambioConfig = directorEventos.crearEventoActualizarConfigPartida(lobbyDTO);
        cliente.enviarEvento(cambioConfig);
        
    }
    
    public void actualizarAvatar(EventoMVCJugador evento){
        CuentaDTO cuentaDTO = evento.getPublicador();
        if(validarCambioAvatar(cuentaDTO.getAvatar())){
            cuenta.setAvatar(adapterDTO.adaptarAvatarDTO(cuentaDTO.getAvatar()));
            EventoJugador cambioAv = directorEventos.crearEventoCambiarAvatar(lobbyDTO, cuentaDTO);
            
            System.out.println("-------------------");
            System.out.println("Ahora tu avatar es "+cuentaDTO.getAvatar().getAnimal());
            System.out.println("-------------------");
            
            //MediadorManejadores.enviarADisplay(cambioAv);
            
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
    
    public void crearPartida(EventoMVCJugador evento){
        CuentaDTO cuentaDTO = adapterEntidad.adaptarEntidadCuenta(cuenta);
        EventoJugador crear = directorEventos.crearEventoCrearPartida(cuentaDTO);
        cliente.enviarEvento(crear);
        
        agregarSuscripcion(TipoLogicaLobby.PARTIDA_ENCONTRADA, this::recibirPartida);
    }

    public void buscarPartida(EventoMVCJugador evento) {
        LobbyDTO lobbyAux = evento.getLobby();
        cliente.enviarEvento(directorEventos.crearEventoUnirsePartida(
                        lobbyAux, adapterEntidad.adaptarEntidadCuenta(cuenta)));
        
        agregarSuscripcion(TipoLogicaLobby.PARTIDA_ENCONTRADA, this::recibirPartida);
    }

    public void abandonarPartida(EventoMVCJugador evento){
        System.out.println("-------------------");
        System.out.println("Saliste del lobby");
        System.out.println("-------------------");
        //MediadorManejadores.enviarADisplay(new EventoJugador(TiposJugador.ABANDONAR_PARTIDA));
        CuentaDTO cuentaDTO = adapterEntidad.adaptarEntidadCuenta(cuenta);
        EventoJugador eventoJ = directorEventos.crearEventoAbandonarPartida(lobbyDTO, cuentaDTO);
        cliente.enviarEvento(eventoJ);
    }
    
    public void actualizarJugadorListo(EventoMVCJugador evento){
        CuentaDTO cuentaDTO = adapterEntidad.adaptarEntidadCuenta(cuenta);
        
        boolean flag =evento.getTipo().equals(TipoJugadorMVC.JUGADOR_LISTO);
        EventoJugador ev = directorEventos.crearEventoActualizarJugadorListo(lobbyDTO,cuentaDTO, flag);
        
        //MediadorManejadores.enviarADisplay(ev);
        System.out.println("-------------------");
        System.out.println("Jugador "+ cuenta.getNombre()+" listo");
        System.out.println("-------------------");

        cliente.enviarEvento(ev);
    }
    
    private int generarIdContextoPartida(LobbyDTO partida){
        String codigoPartida = partida.getCodigoPartida();
        String digitos = codigoPartida.replaceFirst("-", "");
        int idContextoPartida = Integer.parseInt(digitos);
        return idContextoPartida;
    }
    
    @Override
    public void recibirPartida(Evento evento) {
        Enum tipo = evento.getTipo();
        if (tipo.equals(TipoLogicaLobby.PARTIDA_ENCONTRADA)) {
            EventoLobby eventoLobby = (EventoLobby) evento;
            System.out.println("\nevento: "+eventoLobby);
            
            lobbyDTO = eventoLobby.obtenerLobby();
            cuenta = adapterDTO.adaptarCuentaDTO(lobbyDTO.getCuentaActual());
            
            jugadoresLobby = lobbyDTO.getCuentas();
            
            jugadoresListos = lobbyDTO.getMapaJugadoresListos();
            //establecerJugadoresListos();
            
            
            System.out.println("-------------------");
            System.out.println("Se encontro la partida");
            System.out.println("La partida recibida es\n"+ lobbyDTO);
            System.out.println("-------------------");
            //MediadorManejadores.enviarADisplay(eventoLobby);
            
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
    
    private void agregarSuscripcion(Enum tipoEvento, Consumer<Evento> consumer){
        EventoSuscripcion suscripcion = directorSuscripciones.crearEventoSuscribirse(tipoEvento);
        cliente.agregarSuscripcion(suscripcion, this);
        agregarEvento(tipoEvento, consumer);
    }
    
    private void removerSuscripcion(Enum tipoEvento){
        EventoSuscripcion desuscripcion = directorSuscripciones.crearEventoDesuscribirse(tipoEvento);
        cliente.removerSuscripcion(desuscripcion, this);
        removerEvento(tipoEvento);
    }
    
    public void init(Client cliente) {
        directorEventos = new DirectorEventosLobby(new BuilderEventoJugador());
        cliente.establecerSuscripciones(eventos);
        this.cliente = cliente;
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
            String msj="";
            if (tipo.equals(TipoLogicaLobby.JUGADOR_SALIO)){
                removerJugador(jugadorEvento);
                msj = " salio de ";
            }else if (tipo.equals(TipoLogicaLobby.JUGADOR_NUEVO)){
                agregarJugador(jugadorEvento);
                msj = " entro a ";
            }
            
            System.out.println("-------------------");
            System.out.println("Jugador " + jugadorEvento.getUsername()+ msj+"la partida");
            System.out.println("-------------------");
            
           // MediadorManejadores.enviarADisplay(ev);
        }
    }

    private void agregarJugador(CuentaDTO jugadorNuevo) {
        jugadoresLobby.add(jugadorNuevo);
    }

    private void removerJugador(CuentaDTO exjugador) {
        jugadoresLobby.remove(exjugador);
        
    }
    
    @Override
    public void actualizarAvatares(Evento evento) {
        EventoLobby ev = esEventoDeEsteLobby(evento);
        if( ev != null){
            CuentaDTO jugadorEvento = ev.getPublicador();
            
            for (CuentaDTO cuentaDTO : jugadoresLobby) {
                if(cuentaDTO.equals(jugadorEvento)){
                    System.out.println("-------------------");
                    System.out.println("Jugador " + cuentaDTO.getUsername()+
                            " ahora tiene el avatar de "+cuentaDTO.getAvatar().getAnimal());
                    System.out.println("-------------------");
                    cuentaDTO.setAvatar(jugadorEvento.getAvatar());
                    
                    
                    //MediadorManejadores.enviarADisplay(ev);
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
        EventoError error =(EventoError)evento;
        System.out.println("-------------------");
        System.out.println("Ocurrio un error: "+error.getInfo());
        System.out.println("-------------------");
    }

//    public void setManejadorDisplay(ManejadorDisplay manejadorDisplay) {
//        this.manejadorDisplay = manejadorDisplay;
//    }

    private EventoLobby esEventoDeEsteLobby(Evento evento){
        EventoLobby ev = (EventoLobby) evento;
        LobbyDTO lDTO = ev.obtenerLobby();
        if(lDTO.getCodigoPartida().equals(this.lobbyDTO.getCodigoPartida()))
            return ev;
        return null;
    }
    
}
