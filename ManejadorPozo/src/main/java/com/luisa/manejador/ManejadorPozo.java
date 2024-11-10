package com.luisa.manejador;

import entidades.Ficha;
import entidades.Pozo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class ManejadorPozo {

    private Pozo pozo;
    private final int[][] valoresFichas = {
        {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6},
        {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6},
        {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6},
        {3, 3}, {3, 4}, {3, 5}, {3, 6},
        {4, 4}, {4, 5}, {4, 6},
        {5, 5}, {5, 6},
        {6, 6}
    };
    
    public ManejadorPozo(){
        this.pozo = new Pozo();
        pozo.llenarPozo(crearFichas());
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
        Collections.shuffle(fichas);//revuelve la lista para cambiar el orden
        return fichas;
    }
    
    /**
     * Saca una ficha del pozo
     * @return la ficha obtenida
     */
    public Ficha jalarFicha(){
        Ficha ficha;
        ficha = pozo.jalarFicha();
        return ficha;
    }
    
    /**
     * verifica si hay fichas en el pozo
     * @return true si tiene fichas, false si esta vacio
     */
    public boolean hayFichas(){
        return pozo.tieneFichas();
    }
    
    /**
     * Saca fichas del pozo en la cantidad especificada por el parametro.
     * El metodo se usa al inicio de la partida para darle las fichas a cada jugador
     * @param cantidadFichas numero de fichas a repartir por jugador
     * @return una lista con las fichas para un jugador
     */
    public List<Ficha> repartirFichas(int cantidadFichas){
        List<Ficha> fichas = new ArrayList<>();
        for (int i = 0; i < cantidadFichas; i++) {
            fichas.add(jalarFicha());
        }
        return fichas;
    }
}
