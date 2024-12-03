package manejadorPozo;

import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Ficha;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.MazosDTO;
import entidadesDTO.ReglasDTO;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author karim
 */
public class ManejadorPozo {
    private final Deque<Ficha> pozo;
    private final AdaptadorDTO adaptadorDTO;
    private final AdaptadorEntidad adaptador;

    public ManejadorPozo() {
        adaptador = new AdaptadorEntidad();
        adaptadorDTO = new AdaptadorDTO();
        pozo = new ArrayDeque<>();
    }

    public FichaDTO jalarPozo() {
        if (pozo.isEmpty()) {
            return null;
        }
        Ficha aux = pozo.removeFirst();
        FichaDTO ficha = adaptador.adaptarEntidadFicha(aux);
        return ficha;
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
        for (FichaDTO ficha : fichas) {
            Ficha aux = adaptadorDTO.adaptarFichaDTO(ficha);
            pozo.addFirst(aux);
        }
    }
    
    public void imprimirFichasRestantes(){
        System.out.println(pozo);
    }

    private void guardarFichasSobrantes(List<Ficha> fichas) {
        for (Ficha ficha : fichas) {
            pozo.addFirst(ficha);
        }
    }

    public MazosDTO repartirFichas(ReglasDTO reglas) {
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
