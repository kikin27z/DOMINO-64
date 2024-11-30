package manejadorTablero;

import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Ficha;
import entidades.Tablero;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadaRealizadaDTO;

/**
 *
 * @author luisa M
 */
public class ManejadorTablero {

    private final Tablero tablero;
    private final AdaptadorDTO adaptadorDTO;
    
    
    public ManejadorTablero(){
        this.tablero = new Tablero();
        this.adaptadorDTO = new AdaptadorDTO();
    }
    

    public void colocarFicha(JugadaRealizadaDTO jugada){
        FichaDTO fichaDTO = jugada.getFicha();
        Ficha ficha = adaptadorDTO.adaptarFichaDTO(fichaDTO);
        tablero.agregarFicha(ficha, jugada.isExtremoIzq());
    }
    
    public JugadaDTO obtenerProximaJugada(){
        JugadaDTO jugada = new JugadaDTO();
        jugada.setIzquierda(tablero.obtenerExtremoIzq());
        jugada.setDerecha(tablero.obtenerExtremoDer());
        return jugada;
    }

}
