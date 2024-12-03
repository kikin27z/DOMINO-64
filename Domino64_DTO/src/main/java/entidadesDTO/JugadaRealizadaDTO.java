package entidadesDTO;

import java.io.Serializable;

/**
 * Clase que representa una jugada realizada en el juego.
 * Contiene información sobre la posición de la jugada, si la jugada se realizó en
 * el extremo izquierdo, las coordenadas X y Y de la jugada, y la ficha jugada.
 * Implementa la interfaz Serializable para permitir la serialización del objeto.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class JugadaRealizadaDTO implements Serializable {
    private PosicionDTO posicion;
    private boolean extremoIzq;
    private double coordenadaX;
    private double coordenadaY;
    private FichaDTO ficha;

    /**
     * Constructor de la clase JugadaRealizadaDTO con posición y si es en el extremo izquierdo.
     * 
     * @param posicion La posición de la jugada en el tablero
     * @param izquierda true si la jugada es en el extremo izquierdo, false en caso contrario
     */
    public JugadaRealizadaDTO(PosicionDTO posicion, boolean izquierda) {
        this.posicion = posicion;
        this.extremoIzq = izquierda;
    }

    /**
     * Constructor completo de la clase JugadaRealizadaDTO, con todos los atributos.
     * 
     * @param posicion La posición de la jugada
     * @param extremoIzq true si la jugada es en el extremo izquierdo
     * @param coordenadaX Coordenada X de la jugada
     * @param coordenadaY Coordenada Y de la jugada
     * @param ficha La ficha jugada
     */
    public JugadaRealizadaDTO(PosicionDTO posicion, boolean extremoIzq, double coordenadaX, double coordenadaY, FichaDTO ficha) {
        this.posicion = posicion;
        this.extremoIzq = extremoIzq;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.ficha = ficha;
    }

    /**
     * Constructor vacío para la clase JugadaRealizadaDTO.
     */
    public JugadaRealizadaDTO() {
    }

    /**
     * Obtiene la posición de la jugada en el tablero.
     * 
     * @return La posición de la jugada
     */
    public PosicionDTO getPosicion() {
        return posicion;
    }

    /**
     * Establece la posición de la jugada en el tablero.
     * 
     * @param posicion La posición a establecer
     */
    public void setPosicion(PosicionDTO posicion) {
        this.posicion = posicion;
    }

    /**
     * Verifica si la jugada se realizó en el extremo izquierdo del tablero.
     * 
     * @return true si la jugada fue en el extremo izquierdo, false en caso contrario
     */
    public boolean isExtremoIzq() {
        return extremoIzq;
    }

    /**
     * Establece si la jugada se realizó en el extremo izquierdo del tablero.
     * 
     * @param extremoIzq true si la jugada es en el extremo izquierdo
     */
    public void setExtremoIzq(boolean extremoIzq) {
        this.extremoIzq = extremoIzq;
    }

    /**
     * Obtiene la coordenada X de la jugada.
     * 
     * @return La coordenada X de la jugada
     */
    public double getCoordenadaX() {
        return coordenadaX;
    }

    /**
     * Establece la coordenada X de la jugada.
     * 
     * @param coordenadaX La coordenada X a establecer
     */
    public void setCoordenadaX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    /**
     * Obtiene la coordenada Y de la jugada.
     * 
     * @return La coordenada Y de la jugada
     */
    public double getCoordenadaY() {
        return coordenadaY;
    }

    /**
     * Establece la coordenada Y de la jugada.
     * 
     * @param coordenadaY La coordenada Y a establecer
     */
    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    /**
     * Obtiene la ficha jugada en la jugada.
     * 
     * @return La ficha jugada
     */
    public FichaDTO getFicha() {
        return ficha;
    }

    /**
     * Establece la ficha jugada en la jugada.
     * 
     * @param ficha La ficha a asociar con la jugada
     */
    public void setFicha(FichaDTO ficha) {
        this.ficha = ficha;
    }
}
