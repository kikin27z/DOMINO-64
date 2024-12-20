package manejadorTurnos;

import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Cuenta;
import entidades.Ficha;
import entidades.Jugador;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.MazosDTO;
import entidadesDTO.TurnosDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ManejadorTurnos {

    private LinkedList<String> orden = new LinkedList<>();
    private final AdaptadorEntidad adaptador;
    private final AdaptadorDTO adaptadorDTO;
    private Cuenta ultimoQueRealizoJugada;

    public ManejadorTurnos() {
        adaptador = new AdaptadorEntidad();
        adaptadorDTO = new AdaptadorDTO();
    }

    public CuentaDTO rotarSiguienteTurno() {
        String actual = orden.removeFirst();
        orden.addLast(actual);
        String nuevoTurno = orden.getFirst();
        System.out.println("nuevo turno: "+nuevoTurno);
//        String turnoActual = orden.getFirst();
//        
//        String aux = orden.removeFirst();
//        orden.addLast(aux);

        CuentaDTO cuenta = new CuentaDTO();
        cuenta.setIdCadena(nuevoTurno);

        return cuenta;
    }

    public Map<String, Jugador> entregarFichaJugadores(List<Cuenta> cuentas, List<List<Ficha>> fichas) {
        Map<String, Jugador> mazos = new HashMap<>();
        for (int i = 0; i < cuentas.size(); i++) {
            String idCuenta = cuentas.get(i).getIdCadena();
            Jugador jugador = new Jugador();
            jugador.setFichas(fichas.get(i));

            mazos.put(idCuenta, jugador);
        }
        return mazos;
    }

    private String determinarPrimerJugador(Map<String, Jugador> mazos) {
        String idJugador = null;
        Ficha mulaAlta = new Ficha(-1, -1);
        for (Map.Entry<String, Jugador> entry : mazos.entrySet()) {
            Jugador jugador = entry.getValue();
            Ficha ficha = jugador.obtenerMayorMula();
            if (ficha != null) {
                if (ficha.getValor() > mulaAlta.getValor()) {
                    idJugador = entry.getKey();
                }
            }
        }
        return idJugador;
    }

    private LinkedList<String> crearTurnos(String idPrimerJugador, Map<String, Jugador> mazos) {
        LinkedList<String> or = new LinkedList<>();
        or.addFirst(idPrimerJugador);
        Jugador quitado = mazos.remove(idPrimerJugador);

        List<String> ids = new ArrayList<>();

        for (Map.Entry<String, Jugador> entry : mazos.entrySet()) {
            ids.add(entry.getKey());
        }
        Collections.shuffle(ids);
        or.addAll(1, ids);

        mazos.put(idPrimerJugador, quitado);
        return or;
    }

    private TurnosDTO crearTurnoDTO(Map<String, Jugador> mazosJugadores, LinkedList<String> orden) {
        TurnosDTO turnos = new TurnosDTO();
        Map<String, JugadorDTO> mazosTurno = new HashMap<>();
        for (Map.Entry<String, Jugador> entry : mazosJugadores.entrySet()) {
            mazosTurno.put(entry.getKey(), adaptador.adaptarEntidadJugador(entry.getValue()));
        }
        turnos.setMazos(mazosTurno);

        turnos.setOrden(orden);
        return turnos;
    }

    public TurnosDTO determinarOrden(MazosDTO mazos) {
        List<Cuenta> cuentas = adaptadorDTO.adaptarCuentasDTO(mazos.getCuentas());

        List<List<Ficha>> fichas = devolverMazos(mazos.getMazos());

        Map<String, Jugador> mapeoMazos = entregarFichaJugadores(cuentas, fichas);
        String primerJugador = determinarPrimerJugador(mapeoMazos);
        orden = crearTurnos(primerJugador, mapeoMazos);
        TurnosDTO turnos = crearTurnoDTO(mapeoMazos, orden);

        imprimirTurnos(orden);
        System.out.println(mapeoMazos);
        
        return turnos;
    }

    private void imprimirTurnos(LinkedList<String> orden) {
        for (int i = 0; i < orden.size(); i++) {
            System.out.println("Jugador #" + (1 + i) + " es " + orden.get(i));
        }
    }

    public void quitarJugador(CuentaDTO cuenta) {
        for (String id : orden) {
            if (cuenta.getIdCadena().equals(id)) {
                orden.remove(id);
                break;
            }
        }
    }

    private List<List<Ficha>> devolverMazos(List<List<FichaDTO>> mazosDTO) {
        List<List<Ficha>> mazos = new ArrayList<>();
        for (List<FichaDTO> mazo : mazosDTO) {
            List<Ficha> fichas = adaptadorDTO.adaptarFichaDTO(mazo);
            mazos.add(fichas);

        }
        return mazos;
    }
}
