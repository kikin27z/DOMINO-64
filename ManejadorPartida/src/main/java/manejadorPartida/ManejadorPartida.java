package manejadorPartida;

import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Cuenta;
import entidades.Ficha;
import entidades.Jugador;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
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

    public void agregarJugador(JugadorDTO jugador){
        Jugador j = adaptadorDTO.adaptarJugadorDTO(jugador);
    }
    
    
    public void crearPartida(List<CuentaDTO> cuentasDTO){
        List<Cuenta> cuentas = adaptadorDTO.adaptarCuentasDTO(cuentasDTO);
        
        List<Jugador> jugadores = new ArrayList<>();
        for (Cuenta c : cuentas) {
            Jugador jugador = new Jugador(c);
            jugadores.add(jugador);
        }

       partida.setJugadores(jugadores);
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
