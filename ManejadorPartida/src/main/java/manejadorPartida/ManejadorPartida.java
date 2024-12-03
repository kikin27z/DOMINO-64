package manejadorPartida;

import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Ficha;
import entidades.Jugador;
import entidades.Partida;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import java.util.List;

/**
 *
 * @author karim
 */
public class ManejadorPartida {
    private final Partida partida;
    private final AdaptadorEntidad adaptador;
    private final AdaptadorDTO adaptadorDTO;

    public ManejadorPartida(){
        adaptador = new AdaptadorEntidad();
        adaptadorDTO = new AdaptadorDTO();
        partida = new Partida();
    }

    public void peticionRendirseJugador(JugadorDTO jugador){
        Jugador j = adaptadorDTO.adaptarJugadorDTO(jugador);
        partida.seRindioJugador(j);
    }
    
    public List<FichaDTO> jugadorAbandono(JugadorDTO jugador) {
        Jugador j = adaptadorDTO.adaptarJugadorDTO(jugador);
        List<Ficha> fichas = partida.abandonoJugador(j);
        List<FichaDTO> fichasJugadorAbandono = adaptador.adaptarEntidadesFicha(fichas);
        return fichasJugadorAbandono;
    }
}
