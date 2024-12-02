package logicaPartida;

import abstraccion.ICliente;
import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import domino64.eventos.base.error.TipoError;
import domino64.eventos.base.suscripcion.EventoSuscripcion;
import entidades.Cuenta;
import entidades.Jugador;
import entidades.Partida;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.JugadaValidaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaIniciadaDTO;
import entidadesDTO.PosicionDTO;
import eventos.EventoJugador;
import eventos.EventoPartida;
import eventos.EventoPozo;
import eventos.EventoTablero;
import eventos.EventoTurno;
import eventoss.EventoMVCJugador;
import implementacion.Client;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import listener.ControlEventos;
import tiposLogicos.TipoLogicaPozo;
import utilities.BuilderEventoJugador;
import utilities.BuilderEventoSuscripcion;
import utilities.DirectorSuscripcion;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ManejadorJugador extends ObservadorPartidaLocal{
    private DirectorEventosPartida directorEventos;
    private DirectorSuscripcion directorSuscripciones;
    private ICliente cliente;
    private Jugador jugador;
    private Cuenta cuenta;
    private Partida partida;
    private AdaptadorDTO adaptadorDTO;
    private AdaptadorEntidad adaptadorEntidad;
    private int extremoDer=-1, extremoIzq=-1;

    public ManejadorJugador(){
        adaptadorDTO = new AdaptadorDTO();
        adaptadorEntidad = new AdaptadorEntidad();
        
    }
    
    public void setJugador(JugadorDTO jugador){
        this.jugador = adaptadorDTO.adaptarJugadorDTO(jugador);
        this.cuenta = this.jugador.getCuenta();
    }
    
    public void init(Client cliente) {
        cliente.establecerSuscripciones(eventos);
        this.cliente = cliente;
//        directorEventos = new DirectorEventosPartida(new BuilderEventoJugador(),
//                adaptadorEntidad.adaptarEntidadCuenta(jugador.getCuenta()));
        setConsumers();
    }
    
    public void setClientId(int id) {
        directorSuscripciones = new DirectorSuscripcion(new BuilderEventoSuscripcion(), id);
    }
    
    @Override
    public void manejarError(Evento evento) {
        EventoError error = (EventoError)evento;
        String tipo;
        if(error.getTipo().equals(TipoError.ERROR_DE_SERVIDOR))
            tipo = "servidor";
        else
            tipo="logico";
        System.out.println("-------------------");
        System.out.println("Error de tipo "+tipo+":" + error.getMensaje());
        System.out.println("-------------------");
    }

    @Override
    public void buscarMula(Evento evento) {
        EventoPartida buscarMula =(EventoPartida)evento;
        ControlEventos.enviarEventoAPresentacion(buscarMula);
        System.out.println("buscar mula");
    }

    @Override
    public void entrarPartida(Evento evento) {
        directorEventos = new DirectorEventosPartida(new BuilderEventoJugador(),
                adaptadorEntidad.adaptarEntidadCuenta(jugador.getCuenta()),evento.getIdContexto());
        EventoPartida inicioPartida = (EventoPartida)evento;
        PartidaIniciadaDTO partidaDTO = inicioPartida.getPartida();
        
        JugadorDTO jugadorDTO = partidaDTO.getJugador(cuenta.getIdCadena());
        jugador.setFichas(adaptadorDTO.adaptarFichaDTO(jugadorDTO.getFichas()));
//        
//        System.out.println("-------------------");
//        System.out.println("Inicio la partida\nTus fichas son:");
//        System.out.println(jugadorDTO.getFichas());
//        
        inicioPartida.setJugador(jugadorDTO);
        ControlEventos.enviarEventoAPresentacion(inicioPartida);
        //MediadorManejadores.enviarADisplay(inicioPartida);
    }

    @Override
    public void mostrarGanador(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removerJugador(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void procesarPeticion(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void salirPartida(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void evaluarCambioTurno(Evento evento) {
        EventoTurno evTurno =(EventoTurno)evento;
        JugadorDTO jugadorTurno = evTurno.getJugador();
        String idJugador = jugadorTurno.getCuenta().getIdCadena();
        jugador.setEnTurno(idJugador.equals(jugador.getCuenta().getIdCadena()));
        
        if(jugador.isEnTurno()){
            jugadorTurno.setFichas(obtenerFichasJugables());
        }else{
            jugadorTurno.setFichas(null);
        }
        evTurno.agregarJugador(jugadorTurno);
        
        ControlEventos.enviarEventoAPresentacion(evTurno);
        
   }
    
    @Override
    public void actualizarTablero(Evento evento) {
        EventoTablero eventoTablero = (EventoTablero)evento;
        
        JugadaRealizadaDTO jugada = eventoTablero.getJugada();
        FichaDTO fichaColocada = jugada.getFicha();
        if(extremoDer == 0){
            extremoDer = fichaColocada.getDerecha();
            extremoIzq = fichaColocada.getIzquierda();
        }else{
            PosicionDTO posicion = jugada.getPosicion();
            actualizarExtremos(posicion, fichaColocada);
        }
        
        ControlEventos.enviarEventoAPresentacion(eventoTablero);
    }
    
    private void actualizarExtremos(PosicionDTO posicion, FichaDTO ficha){
        if(posicion.equals(PosicionDTO.DERECHA)){
            extremoDer = ficha.getDerecha();
        }else
            extremoIzq = ficha.getIzquierda();
    }
    
    private List<FichaDTO> obtenerFichasJugables() {
        Map<FichaDTO, JugadaValidaDTO> jugadas = obtenerJugadasValidas();
        if (!jugadas.isEmpty()) {
            List<FichaDTO> fichasJugables = new ArrayList<>();
            for (Map.Entry<FichaDTO, JugadaValidaDTO> entry : jugadas.entrySet()) {
                if (!entry.getValue().equals(JugadaValidaDTO.NINGUNA)) {
                    fichasJugables.add(entry.getKey());
                }
            }
            return fichasJugables;
        }
        return new ArrayList<>();
    }
    
    private Map<FichaDTO,JugadaValidaDTO> obtenerJugadasValidas(){
        List<FichaDTO> fichas = adaptadorEntidad.adaptarEntidadesFicha(jugador.getFichas());
        JugadaValidaDTO jugadaPosible;
        Map<FichaDTO, JugadaValidaDTO> jugadasValidas = new HashMap<>();
        for (FichaDTO ficha : fichas) {
            jugadaPosible = obtenerJugadaPosible(ficha);
            if (jugadaPosible != JugadaValidaDTO.NINGUNA) {
                jugadasValidas.put(ficha, obtenerJugadaPosible(ficha));
            }
        }
        return jugadasValidas;
    }
    
    private PosicionDTO obtenerPosicion(JugadaValidaDTO jugadaPosible){
        PosicionDTO posicion = switch (jugadaPosible) {
            case AMBOS_EXTREMOS ->
                ControlEventos.mensajeElegirExtremo();
            case SOLO_DERECHA ->
                PosicionDTO.DERECHA;
            default ->
                PosicionDTO.IZQUIERDA;
        };
        return posicion;
    }
    
    public void colocarFicha(EventoMVCJugador evento){
        JugadaRealizadaDTO jugada;
        FichaDTO fichaColocada;
        if(extremoDer == -1){
            jugada = evento.getJugada();
            fichaColocada = jugada.getFicha();
        }else{
            fichaColocada = ControlEventos.mensajeColocarFicha(obtenerFichasJugables());
            PosicionDTO posicion = obtenerPosicion(obtenerJugadaPosible(fichaColocada));
            jugada = new JugadaRealizadaDTO();
            jugada.setPosicion(posicion);
            jugada.setFicha(fichaColocada);
        }
        jugador.removerFicha(adaptadorDTO.adaptarFichaDTO(fichaColocada));
        
        EventoJugador eventoFicha = directorEventos.crearEventoColocarFicha(jugada);
        cliente.enviarEvento(eventoFicha);
        
        ControlEventos.enviarEventoAPresentacion(eventoFicha);
    }
    
    private JugadaValidaDTO obtenerJugadaPosible(FichaDTO ficha){
        int der = ficha.getDerecha(), izq=ficha.getIzquierda();
        JugadaValidaDTO jugadaValida;
        boolean coincideDerecha = der == extremoDer || izq == extremoDer;
        boolean coincideIzquierda = der == extremoIzq || izq == extremoIzq;
        
        if(coincideDerecha && coincideIzquierda){
            jugadaValida = JugadaValidaDTO.AMBOS_EXTREMOS;
        }else if(coincideIzquierda){
            jugadaValida = JugadaValidaDTO.SOLO_IZQUIERDA;
        }else if(coincideDerecha){
            jugadaValida = JugadaValidaDTO.SOLO_IZQUIERDA;
        }else
            jugadaValida = JugadaValidaDTO.NINGUNA;
        
        return jugadaValida;
    }
    
    public void abandonarPartida(EventoMVCJugador evento){
        
    }
    
    public void peticionRendirse(EventoMVCJugador evento){
        
    }
    
    public void pasarTurno(EventoMVCJugador evento){
        
    }
    
    public void jalarFicha(EventoMVCJugador evento){
        EventoJugador jalarFicha = directorEventos.crearEventoJalarFicha();
        cliente.enviarEvento(jalarFicha);
        EventoSuscripcion suscripcion = directorSuscripciones.crearEventoSuscribirse(TipoLogicaPozo.FICHA_OBTENIDA);
        cliente.agregarSuscripcion(suscripcion, this);
        agregarEvento(suscripcion.getEventoSuscripcion(), this::recibirFicha);
    }

    private void recibirFicha(Evento evento){
        EventoSuscripcion suscripcion = directorSuscripciones.crearEventoDesuscribirse(TipoLogicaPozo.FICHA_OBTENIDA);
        cliente.removerSuscripcion(suscripcion, this);
        removerEvento(suscripcion.getEventoSuscripcion());
        EventoPozo eventoPozo =(EventoPozo)evento;
        FichaDTO fichaObtenida = eventoPozo.getFicha();
        jugador.agregarFicha(adaptadorDTO.adaptarFichaDTO(fichaObtenida));
        
    }
    
}
