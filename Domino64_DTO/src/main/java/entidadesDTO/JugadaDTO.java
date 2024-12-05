package entidadesDTO;

import java.io.Serializable;

/**
 * Representa una jugada en el juego de dominó, con los extremos actuales del tablero.
 * Proporciona métodos para determinar las posibles jugadas que un jugador puede realizar
 * con una ficha específica.
 * 
 * <p>Los extremos del tablero se representan mediante los atributos {@code izquierda} 
 * y {@code derecha}.</p>
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class JugadaDTO implements Serializable {

    private int izquierda;
    private int derecha;

    /**
     * Constructor por defecto.
     */
    public JugadaDTO() {
    }

    /**
     * Constructor que inicializa los extremos de la jugada.
     * 
     * @param izquierda Valor del extremo izquierdo.
     * @param derecha Valor del extremo derecho.
     */
    public JugadaDTO(int izquierda, int derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    /**
     * Determina la jugada posible con una ficha específica.
     * 
     * @param ficha La ficha a evaluar.
     * @return Un valor de {@code PosibleJugadaDTO} que indica dónde se puede jugar la ficha.
     */
    public PosibleJugadaDTO determinarJugada(FichaDTO ficha) {
        if (sePuedeJugarAmbosExtremos(ficha)) {
            return PosibleJugadaDTO.AMBOS_EXTREMOS;
        } else if (sePuedeJugarIzquierda(ficha)) {
            return PosibleJugadaDTO.SOLO_IZQUIERDA;
        } else if (sePuedeJugarDerecha(ficha)) {
            return PosibleJugadaDTO.SOLO_DERECHA;
        } else {
            return PosibleJugadaDTO.NINGUNA;
        }
    }

    /**
     * Verifica si una ficha puede jugarse en ambos extremos del tablero.
     * 
     * @param ficha La ficha a evaluar.
     * @return {@code true} si la ficha puede jugarse en ambos extremos, {@code false} en caso contrario.
     */
    private boolean sePuedeJugarAmbosExtremos(FichaDTO ficha) {
        return sePuedeJugarIzquierda(ficha) && sePuedeJugarDerecha(ficha);
    }

    /**
     * Verifica si una ficha puede jugarse en el extremo izquierdo del tablero.
     * 
     * @param ficha La ficha a evaluar.
     * @return {@code true} si la ficha puede jugarse en el extremo izquierdo, {@code false} en caso contrario.
     */
    private boolean sePuedeJugarIzquierda(FichaDTO ficha) {
        return this.izquierda == ficha.getIzquierda() || this.izquierda == ficha.getDerecha();
    }

    /**
     * Verifica si una ficha puede jugarse en el extremo derecho del tablero.
     * 
     * @param ficha La ficha a evaluar.
     * @return {@code true} si la ficha puede jugarse en el extremo derecho, {@code false} en caso contrario.
     */
    private boolean sePuedeJugarDerecha(FichaDTO ficha) {
        return this.derecha == ficha.getIzquierda() || this.derecha == ficha.getDerecha();
    }

    /**
     * Establece el valor del extremo izquierdo.
     * 
     * @param izquierda Nuevo valor del extremo izquierdo.
     */
    public void setIzquierda(int izquierda) {
        this.izquierda = izquierda;
    }

    /**
     * Establece el valor del extremo derecho.
     * 
     * @param derecha Nuevo valor del extremo derecho.
     */
    public void setDerecha(int derecha) {
        this.derecha = derecha;
    }

    @Override
    public String toString() {
        return "JugadaDTO{" + "extremoIzquierdo=" + izquierda + ", extremoDerecho=" + derecha + '}';
    }

    /**
     * Imprime los detalles de la jugada, la ficha y la posible jugada.
     * 
     * @param ficha La ficha evaluada.
     * @param jugada El resultado de la evaluación de la jugada.
     */
    public void imprimirJugada(FichaDTO ficha, PosibleJugadaDTO jugada) {
        System.out.print(this);
        System.out.println(" - " + ficha + ", jugada = " + jugada);
    }
}
