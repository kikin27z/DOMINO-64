package manejadorPozo;

import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Ficha;
import entidades.Jugador;
import entidades.Pozo;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.MazosDTO;
import entidadesDTO.ReglasDTO;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ManejadorPozo {
    private Pozo pozoE;
    private final Deque<Ficha> pozo;
    private final AdaptadorDTO adaptadorDTO;
    private final AdaptadorEntidad adaptador;
    private final int[][] valoresFichas = {
        {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6},
        {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6},
        {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6},
        {3, 3}, {3, 4}, {3, 5}, {3, 6},
        {4, 4}, {4, 5}, {4, 6},
        {5, 5}, {5, 6},
        {6, 6}
    };
    
    public ManejadorPozo() {
        adaptador = new AdaptadorEntidad();
        adaptadorDTO = new AdaptadorDTO();
        pozo = new ArrayDeque<>();
        pozoE = new Pozo();
        pozoE.llenarPozo(crearFichas());
        
    }

    public FichaDTO jalarPozo() {
        Ficha fichaJ = pozoE.jalarFicha();
        FichaDTO dto = null;
        if(fichaJ != null){
            dto = adaptador.adaptarEntidadFicha(fichaJ);
        }
        return dto;
//        if (pozo.isEmpty()) {
//            return null;
//        }
//        Ficha aux = pozo.removeFirst();
//        FichaDTO ficha = adaptador.adaptarEntidadFicha(aux);
//        return ficha;
    }
    
    private Ficha jalarFicha(){
        return pozoE.jalarFicha();
    }
    
    private List<Ficha> crearFichas(){
        List<Ficha> fichas = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            int value1 = valoresFichas[i][0];
            int value2 = valoresFichas[i][1];
            fichas.add(new Ficha(value1, value2));
        }
        Collections.shuffle(fichas);//revuelve la lista para cambiar el orden
        return fichas;
    }
    
    /**
     * Saca fichas del pozo en la cantidad especificada por el parametro. El
     * metodo se usa al inicio de la partida para darle las fichas a cada
     * jugador
     *
     * @param cantidadFichas numero de fichas a repartir por jugador
     * @return una lista con las fichas para un jugador
     */
    public List<Ficha> repartirFichas(int cantidadFichas){
        List<Ficha> fichas = new ArrayList<>();
        for (int i = 0; i < cantidadFichas; i++) {
            Ficha ficha = jalarFicha();
            if(ficha!=null)
                fichas.add(ficha);
        }
        return fichas;
    }
    
    private Ficha tomarPozo() {
        if (pozo.isEmpty()) {
            return null;
        }
        return pozo.removeFirst();
    }

    public void guardarFichaPozo(FichaDTO ficha) {
        Ficha aux = adaptadorDTO.adaptarFichaDTO(ficha);
        pozo.addFirst(aux);
    }

    public void guardarFichasPozo(List<FichaDTO> fichas) {
        pozoE.devolverFichas(adaptadorDTO.adaptarFichaDTO(fichas));
        
//        for (FichaDTO ficha : fichas) {
//            Ficha aux = adaptadorDTO.adaptarFichaDTO(ficha);
//            pozo.addFirst(aux);
//        }
    }
    
    public void imprimirFichasRestantes(){
        System.out.println(pozo);
    }

    private void guardarFichasSobrantes(List<Ficha> fichas) {
        for (Ficha ficha : fichas) {
            pozo.addFirst(ficha);
        }
    }
    
    private boolean jugadoresConMulas(List<Jugador> jugadores){
        for (Jugador jugador : jugadores) {
            if(jugador.tieneMulas())
                return true;
        }
        return false;
    }
    
    private List<Jugador> darFichaExtra(List<Jugador> jugadores){
        for (Jugador jugador : jugadores) {
            jugador.agregarFicha(jalarFicha());
        }
        return jugadores;
    }
    
    
    public List<JugadorDTO> repartirFichas2(List<JugadorDTO> jugadoresDTO, ReglasDTO reglas){
        List<Jugador> jugadores = adaptadorDTO.adaptarJugadoresDTO(jugadoresDTO);
        for (Jugador jugador : jugadores) {
            List<Ficha> fichas = repartirFichas(reglas.getCantidadFichas());
            jugador.setFichas(fichas);
        }
        boolean flag = false;
        do {
            if (jugadoresConMulas(jugadores)) {
                flag = true;
            } else {
                jugadores = darFichaExtra(jugadores);
            }
        } while (!flag);
        return adaptador.adaptarJugadores(jugadores);
    }
    
    public MazosDTO repartirFichas(ReglasDTO reglas, List<JugadorDTO> jugadoresDTO) {
        List<List<Ficha>> mazos = crearMazos(reglas);

        while (!hayMulas(mazos)) {
            darUnaFichaExtra(mazos);
        }
        MazosDTO mazosDTO = new MazosDTO();
        mazosDTO.setCuentas(reglas.getCuentas());
        for (List<Ficha> mazo : mazos) {
            List<FichaDTO> fichasDTO = adaptador.adaptarEntidadesFicha(mazo);
            mazosDTO.agregarUnMazo(fichasDTO);
        }

        return mazosDTO;
    }

    private void darUnaFichaExtra(List<List<Ficha>> mazos) {
        for (List<Ficha> mazo : mazos) {
            mazo.add(tomarPozo());
        }
    }

    private List<Ficha> generarFichasDomino() {
        List<Ficha> fichas = new ArrayList<>();
        for (int l1 = 0; l1 <= 6; l1++) {
            for (int l2 = l1; l2 <= 6; l2++) {
                fichas.add(new Ficha(l1, l2));
            }
        }
        return fichas;
    }

    private List<List<Ficha>> crearMazos(ReglasDTO reglas) {
        // Barajar las fichas
        List<Ficha> fichas = generarFichasDomino();

        Collections.shuffle(fichas);

        // Crear lista de manos de jugadores
        List<List<Ficha>> manos = new ArrayList<>();

        // Distribuir fichas
        for (int i = 0; i < reglas.getCantidadJugadores(); i++) {
            List<Ficha> mano = new ArrayList<>();
            for (int j = 0; j < reglas.getCantidadFichas(); j++) {
                mano.add(fichas.remove(0));
            }
            manos.add(mano);
        }
        guardarFichasSobrantes(fichas);
        return manos;
    }

    private boolean hayMulas(List<List<Ficha>> mazos) {
        return mazos.stream()
                .anyMatch(mazo -> mazo.stream().anyMatch(Ficha::esMula));
    }
}
