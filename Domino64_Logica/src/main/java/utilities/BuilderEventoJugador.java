package utilities;

import builder.EventBuilder;
import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.ReglasDTO;
import entidadesDTO.UnirseDTO;
import eventos.EventoJugador;
import tiposLogicos.TiposJugador;

/**
 * Clase constructora de eventos de jugador. Este builder lo va a usar el
 * director del manejador de cuenta para facilitar y encapsular la logica de
 * construccion de eventos del jugador
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class BuilderEventoJugador implements EventBuilder<TiposJugador> {

    private EventoJugador evento;

    public BuilderEventoJugador() {
        evento = new EventoJugador();
    }

    public void setLobbyDTO(LobbyDTO lobby){
        evento.setLobby(lobby);
    }

    public void setPublicador(CuentaDTO cuenta) {
        evento.setCuenta(cuenta);
    }
    public void agregarCuenta(CuentaDTO cuenta){
        evento.setCuenta(cuenta);
    }
    
    public void agregarUnirse(UnirseDTO unirse){
        evento.setUnirse(unirse);
    }
    
    public void agregarReglas(ReglasDTO reglas){
        evento.setReglas(reglas);
    }

    @Override
    public void setTipo(TiposJugador tipo) {
        evento.setTipo(tipo);
    }
    
    

    @Override
    public void setIdPublicador(int idPublicador) {
        evento.setIdPublicador(idPublicador);
    }
    @Override
    public void setIdContexto(int idContexto) {
        evento.setIdContexto(idContexto);
    }


    @Override
    public EventoJugador construirEvento() {
        EventoJugador resultado = evento;
        reiniciar();
        return resultado;
    }

    @Override
    public void reiniciar() {
        evento = new EventoJugador();
    }

    @Override
    public void setIdDestinatario(int idDestinatario) {
        evento.setIdDestinatario(idDestinatario);
    }

}
