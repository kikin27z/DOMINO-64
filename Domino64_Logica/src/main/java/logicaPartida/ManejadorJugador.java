package logicaPartida;

import abstraccion.ICliente;
import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import domino64.eventos.base.Evento;
import entidades.Jugador;
import entidades.Partida;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaIniciadaDTO;
import eventos.EventoPartida;
import implementacion.Client;
import manejadores.MediadorManejadores;
import utilities.BuilderEventoJugador;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ManejadorJugador extends ObservadorPartidaLocal{
    private DirectorEventosPartida directorEventos;
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
        this.cliente = cliente;
        directorEventos = new DirectorEventosPartida(new BuilderEventoJugador(),
                adaptadorEntidad.adaptarEntidadCuenta(jugador.getCuenta()));
        cliente.establecerSuscripciones(eventos);
        setConsumers();
    }
    
    @Override
    public void manejarError(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void buscarMula(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        MediadorManejadores.enviarADisplay(inicioPartida);
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
    
}
