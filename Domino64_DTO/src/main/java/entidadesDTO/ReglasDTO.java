package entidadesDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que representa las reglas de un juego.
 * Contiene la cantidad de fichas y una lista de cuentas de jugadores.
 * Implementa la interfaz Serializable para permitir la serialización del objeto.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ReglasDTO implements Serializable {
    private int cantidadFichas;
    private List<CuentaDTO> cuentas;

    /**
     * Constructor que recibe la cantidad de fichas.
     * 
     * @param cantidadFichas La cantidad de fichas en el juego
     */
    public ReglasDTO(int cantidadFichas) {
        this.cantidadFichas = cantidadFichas;
    }

    /**
     * Constructor vacío, utilizado para crear una instancia sin inicializar los atributos.
     */
    public ReglasDTO() {
    }

    /**
     * Obtiene la cantidad de fichas en el juego.
     * 
     * @return La cantidad de fichas
     */
    public int getCantidadFichas() {
        return cantidadFichas;
    }

    /**
     * Establece la cantidad de fichas en el juego.
     * 
     * @param cantidadFichas La cantidad de fichas a establecer
     */
    public void setCantidadFichas(int cantidadFichas) {
        this.cantidadFichas = cantidadFichas;
    }

    /**
     * Obtiene la cantidad de jugadores, que corresponde al tamaño de la lista de cuentas.
     * 
     * @return La cantidad de jugadores
     */
    public int getCantidadJugadores() {
        return cuentas.size();
    }

    /**
     * Obtiene la lista de cuentas de los jugadores.
     * 
     * @return La lista de cuentas de los jugadores
     */
    public List<CuentaDTO> getCuentas() {
        return cuentas;
    }

    /**
     * Establece la lista de cuentas de los jugadores.
     * 
     * @param cuentas La lista de cuentas de los jugadores
     */
    public void setCuentas(List<CuentaDTO> cuentas) {
        this.cuentas = cuentas;
    }
}
