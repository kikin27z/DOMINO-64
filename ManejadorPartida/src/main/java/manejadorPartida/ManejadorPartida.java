package manejadorPartida;

import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Cuenta;
import entidades.Ficha;
import entidades.Jugada;
import entidades.Jugador;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.MazosDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ManejadorPartida {
    private final Partida partida;
    private Jugada jugadaActual;
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
    
    public void quitarFicha(CuentaDTO c, FichaDTO f){
        Cuenta cuenta = adaptadorDTO.adaptarCuentaDTO(c);
        Ficha ficha = adaptadorDTO.adaptarFichaDTO(f);
        
        Jugador jugador = partida.obtenerJugador(cuenta);
        jugador.removerFicha(ficha);
    }
    
    public void repartirFichas(MazosDTO mazos){
        List<Cuenta> cuentas = adaptadorDTO.adaptarCuentasDTO(mazos.getCuentas());
        List<List<Ficha>> manos = new ArrayList<>();
        
        for(var mazo : mazos.getMazos()){
            List<Ficha> fichas = adaptadorDTO.adaptarFichaDTO(mazo);
            manos.add(fichas);
        }
        
        for (int i = 0; i < cuentas.size(); i++) {
             Jugador jugador = partida.obtenerJugador(cuentas.get(i));
            jugador.setFichas(manos.get(i));
        }
    }
    
    public void agregarJugadaActual(JugadaDTO jugadaDTO){
        this.jugadaActual = adaptadorDTO.adaptarJugadaDTO(jugadaDTO);
    }
    
    public boolean tieneJugada(CuentaDTO cuentaDTO){
        Cuenta aux = adaptadorDTO.adaptarCuentaDTO(cuentaDTO);
        Jugador jugador = partida.obtenerJugador(aux);
        
        return jugadaActual.puedeJugar(jugador.getFichas());
    }
}
