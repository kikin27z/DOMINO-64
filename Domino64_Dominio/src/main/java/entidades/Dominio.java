package entidades;

/**
 *
 * @author luisa M
 */
public class Dominio {

    public static void main(String[] args) {
        Ficha ficha1 = new Ficha(2, 3);
        Ficha ficha2 = new Ficha(6, 3);
        Ficha ficha3 = new Ficha(1, 6);
        Ficha ficha4 = new Ficha(1, 0);
        Ficha ficha5 = new Ficha(0, 5);
        
        Tablero tablero = new Tablero();
        tablero.agregarFicha(ficha3, false);
        tablero.agregarFicha(ficha4, true);
        tablero.agregarFicha(ficha2, false);
        tablero.agregarFicha(ficha1, false);
        tablero.agregarFicha(ficha5, true);
        
        
    }
}
