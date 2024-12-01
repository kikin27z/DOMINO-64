package logicaPartida;

import abstraccion.ICliente;
import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import domino64.eventos.base.Evento;
import domino64.eventos.base.error.EventoError;
import domino64.eventos.base.error.TipoError;
import entidades.Jugador;
import entidades.Partida;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaIniciadaDTO;
import eventos.EventoPartida;
import eventoss.EventoMVCJugador;
import implementacion.Client;
import manejadores.MediadorManejadores;
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
    private Partida partida;
    private AdaptadorDTO adaptadorDTO;
    private AdaptadorEntidad adaptadorEntidad;

    public ManejadorJugador(){
        adaptadorDTO = new AdaptadorDTO();
        adaptadorEntidad = new AdaptadorEntidad();
        
    }
    
    public void setJugador(JugadorDTO jugador){
        this.jugador = adaptadorDTO.adaptarJugadorDTO(jugador);
    }
    
    public void init(Client cliente) {
        cliente.establecerSuscripciones(eventos);
        this.cliente = cliente;
        directorEventos = new DirectorEventosPartida(new BuilderEventoJugador(),
                adaptadorEntidad.adaptarEntidadCuenta(jugador.getCuenta()));
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
        System.out.println("Error de tipo "+tipo+":" + error.getInfo());
        System.out.println("-------------------");
    }

    @Override
    public void buscarMula(Evento evento) {
    }

    @Override
    public void entrarPartida(Evento evento) {
        EventoPartida inicioPartida = (EventoPartida)evento;
        PartidaIniciadaDTO partidaDTO = (PartidaIniciadaDTO)inicioPartida.getInfo();
        partida = new Partida();
        JugadorDTO jugadorDTO = partidaDTO.getJugador(jugador.getCuenta().getIdCadena());
        jugador = adaptadorDTO.adaptarJugadorDTO(jugadorDTO);
        partida.agregarJugador(jugador);
        
        inicioPartida.setJugador(jugadorDTO);
        
        System.out.println("-------------------");
        System.out.println("Inicio la partida\nTus fichas son:");
        System.out.println(jugadorDTO.getFichas());
        System.out.println("-------------------");
        
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
    
    
    public void colocarFicha(EventoMVCJugador evento){
        
    }
    
    public void abandonarPartida(EventoMVCJugador evento){
        
    }
    
    public void peticionRendirse(EventoMVCJugador evento){
        
    }
    
    public void pasarTurno(EventoMVCJugador evento){
        
    }
    
}
