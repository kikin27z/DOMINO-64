package entidades;

import java.util.List;

/**
 * Representa a un jugador dentro del juego, con su cuenta y sus fichas.
 * Permite agregar y remover fichas, así como obtener la ficha de mayor valor (mula).
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class Jugador {
    private List<Ficha> fichas; // Lista de fichas del jugador
    private Cuenta cuenta; // Cuenta asociada al jugador

    /**
     * Constructor que crea un jugador con una cuenta específica.
     * 
     * @param cuenta La cuenta del jugador.
     */
    public Jugador(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Constructor vacío para crear un jugador sin cuenta inicial.
     */
    public Jugador() {
    }

    /**
     * Obtiene las fichas del jugador.
     * 
     * @return Lista de fichas del jugador.
     */
    public List<Ficha> getFichas() {
        return fichas;
    }

    /**
     * Establece las fichas del jugador.
     * 
     * @param fichas Lista de fichas a establecer para el jugador.
     */
    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }

    /**
     * Obtiene la cuenta asociada al jugador.
     * 
     * @return La cuenta del jugador.
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * Establece la cuenta asociada al jugador.
     * 
     * @param cuenta La cuenta a establecer para el jugador.
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Agrega una ficha a la lista de fichas del jugador.
     * 
     * @param ficha La ficha a agregar.
     */
    public void agregarFicha(Ficha ficha) {
        this.fichas.add(ficha); // Agrega la ficha a la lista
    }

    /**
     * Remueve una ficha de la lista de fichas del jugador.
     * 
     * @param ficha La ficha a remover.
     * @return La ficha removida, o null si no se encuentra.
     */
    public Ficha removerFicha(Ficha ficha) {
        Ficha fichaRemovida = null;
        for (Ficha f : fichas) {
            if(f.esLaMisma(ficha)) { // Compara si la ficha es la misma
                fichaRemovida = f;
                fichas.remove(f); // Elimina la ficha de la lista
                break;
            }
        }
        System.out.println("Se borro " + fichaRemovida + " a " + cuenta);
        return fichaRemovida;
    }

    /**
     * Obtiene la ficha de mayor valor que sea una mula (ficha con el mismo valor en ambos lados).
     * 
     * @return La ficha de mayor valor que sea una mula, o null si no hay ninguna.
     */
    public Ficha obtenerMayorMula() {
        Ficha mayorMula = null;
        int maxValor = -1; // Valor inicial muy bajo para encontrar la mula de mayor valor

        for (Ficha ficha : fichas) {
            if (ficha.esMula() && ficha.getIzquierda() > maxValor) { // Verifica si es mula y su valor es el mayor
                mayorMula = ficha;
                maxValor = ficha.getIzquierda(); // Actualiza el valor máximo
            }
        }

        return mayorMula;
    }

    /**
     * Obtiene el identificador del jugador, que es el ID de su cuenta.
     * 
     * @return El ID del jugador.
     */
    public String obtenerIdJugador(){
        return this.cuenta.getIdCadena(); // Devuelve el ID de la cuenta del jugador
    }
    
    public boolean esCuentaJugador(Cuenta c){
        return cuenta.equals(c);
    }

    /**
     * Representación en forma de cadena del objeto Jugador.
     * 
     * @return Cadena con la información del jugador.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador{");
        sb.append("fichas=").append(fichas); // Muestra las fichas del jugador
        sb.append('}');
        return sb.toString();
    }
}
