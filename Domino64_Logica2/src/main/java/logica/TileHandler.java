/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import com.mycompany.patrones.observer.Observer;
import com.mycompany.starter.NotificadorLogica;
import entidades.Ficha;
import entidades.JugadaPosible;
import entidades.Jugador;
import entidades.Pozo;
import entidades.Tablero;
import excepciones.LogicException;
import exceptions.DominioException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import presentacion_utilities.NotificadorPresentacion;

/**
 *
 * @author luisa M
 */
public class TileHandler implements Observer<NotificadorPresentacion> {
    private final int[][] valoresFichas ={
            {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6},
            {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6},
            {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6},
            {3, 3}, {3, 4}, {3, 5}, {3, 6},
            {4, 4}, {4, 5}, {4, 6},
            {5, 5}, {5, 6},
            {6, 6}
        };
    private Pozo pozo;
    private Tablero tablero;
    
    
    @Override
    public void update(NotificadorPresentacion observable, Object ... context) {
        
    }

    /**
     * metodo para crear una lista de fichas con los valores almacenados en el
     * array valoresFichas
     *
     * @return la lista con las fichas
     */
    private List<Ficha> crearFichas() {
        List<Ficha> fichas = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            int value1 = valoresFichas[i][0];
            int value2 = valoresFichas[i][1];
            fichas.add(new Ficha(value1, value2));
        }
        return fichas;
    }

    /**
     * metodo para llenar el pozo con las fichas creadas
     */
    private void llenarPozo() {
        List<Ficha> fichas = crearFichas();//crea las fichas
        Collections.shuffle(fichas);//revuelve la lista para cambiar el orden
        Stack<Ficha> stackFichas = new Stack<>();
        stackFichas.addAll(fichas);//agrega al stack las fichas revueltas
        pozo.llenarPozo(stackFichas);//llena el pozo con el stack
    }
    
    public void setPozo(Pozo pozo){
        this.pozo = pozo;
        llenarPozo();
    }
    
    public void setTablero(Tablero tablero){
        this.tablero = tablero;
    }
    
    /**
     *
     * @param fichas
     * @return
     */
    public Map<Ficha, JugadaPosible> verificaFichasValidas(List<Ficha> fichas) {
        Map<Ficha, JugadaPosible> jugadas = new HashMap<>();
        if (!tablero.tableroVacio()) {

            for (Ficha ficha : fichas) {
                JugadaPosible jugada = tablero.validarFicha(ficha);
                if (!jugada.equals(JugadaPosible.NINGUNA)) {
                    jugadas.put(ficha, jugada);
                }
            }
        }
        return jugadas;
    }
    
    /**
     *
     * @param ficha
     * @param extremo
     * @throws LogicException
     */
    public void colocarFicha(Ficha ficha, String extremo) throws LogicException {
        System.out.println("se podia poner de ambos lados");
        StringBuilder msj = new StringBuilder("se coloco la ficha " + ficha + " en el extremo ");
        if (extremo.equals("izquierda")) {
            tablero.insertarFichaIzq(ficha);
            msj.append("izquierdo del tablero");
        } else {
            tablero.insertarFichaDer(ficha);
            msj.append("derecho del tablero");
        }
        System.out.println(msj);
        System.out.println();
        System.out.println("tablero hasta ahora");
        System.out.println(tablero.imprimirTren());
    }
    
    /**
     *
     * @param ficha
     * @param jugada
     * @throws LogicException
     */
    public void colocarFicha(Ficha ficha, JugadaPosible jugada) throws LogicException {
        System.out.println("solo se podia poner de un lado");
        StringBuilder msj = new StringBuilder("se coloco la ficha " + ficha + " en el extremo ");
        if (jugada == JugadaPosible.SOLOXIZQUIERDA) {
            tablero.insertarFichaIzq(ficha);
            msj.append("izquierdo del tablero");
        } else {
            tablero.insertarFichaDer(ficha);
            msj.append("derecho del tablero");
        }
        System.out.println(msj);
        System.out.println("tablero hasta ahora");
        System.out.println(tablero.imprimirTren());
    }
    
    public void repartirFichas(List<Jugador> jugadores, int fichasPorJugador) throws LogicException {
        for (int i = 0; i < fichasPorJugador; i++) {
            for (Jugador j : jugadores) {
                j.agregarFicha(darFicha());
            }
        }
    }
    
    public Ficha darFicha() throws LogicException {
        try {
            Ficha ficha = pozo.jalarFicha();
            return ficha;
        } catch (DominioException ex) {
            throw new LogicException(ex.getMessage());
        }
    }
    
    public Ficha obtenerFichaValida(Jugador jugador) throws LogicException {
        Ficha ficha;
        JugadaPosible jugada;
        do{
            ficha = darFicha();
            jugada = tablero.validarFicha(ficha);
            jugador.agregarFicha(ficha);
        }while(jugada != JugadaPosible.NINGUNA);
        
        return ficha;
    }
    
    /**
     * Jala fichas del pozo hasta que se obtiene una que sea una mula. Los
     * jugadores en la lista van recibiendo una ficha del pozo hasta que salga
     * una ficha mula. La primera mula que salga sera la primera en ponerse en
     * el tablero. El jugador que haya jalado dicha mula sera el jugador con el
     * primer turno.
     *
     * @param players a recibir fichas
     * @return una dupla de la mula encontrada y el jugador que la obtuvo. El
     * primer elemento de la dupla guarda la mula encontrada, la cual es la
     * primera ficha a colocar en la partida; el segundo elemento es el jugador.
     * @throws LogicException en caso de que se acaben las fichas en el pozo
     */
    public Object[] obtenerPrimeraMula(List<Jugador> players) throws LogicException {
        Object[] duple = new Object[2];
        for (Jugador j : players) {
            Ficha tile = darFicha();
            if (tile.esMula()) {
                duple[0] = tile;
                duple[1] = j;
                break;
            }
            j.agregarFicha(tile);
            System.out.println("");
            System.out.println("fichas actuales del jugador:");
            System.out.println(j.getFichas());
        }
        return duple;
    }
    
    /**
     * coloca la primera mula en el tablero
     *
     * @param primeraMula a colocar en el tablero
     * @throws LogicException si ya coloco una ficha anteriormente
     */
    public void colocarPrimeraMula(Ficha primeraMula) throws LogicException {
        try {
            this.tablero.insertarFicha(primeraMula);
            System.out.println("se coloco la ficha " + primeraMula + " en el tablero");
            System.out.println("tablero hasta ahora");
            System.out.println(tablero.imprimirTren());
        } catch (Exception ex) {
            throw new LogicException(ex.getMessage());
        }
    }
}
