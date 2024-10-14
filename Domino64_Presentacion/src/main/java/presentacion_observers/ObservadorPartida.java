package presentacion_observers;

import entidadesDTO.FichaDTO;
import java.util.List;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author karim
 */
public interface ObservadorPartida {
    
    public void agregarFichaMazo(FichaDTO ficha);//Lo envia logica
    public void quitarFichaMazo(Canvas ficha);//Lo envia logica
    public void actualizarPozo();//Lo envia logica
    public void agregarFichaTablero(FichaDTO ficha, boolean queLado);//Lo envia logica
    public void inhabilitarJugador();//Lo envia logica
    public void actualizarNumFichasJugador();//Lo envia logica
    public void agregarFichasMazo(List<FichaDTO> fichasDTO);
    public void activarEventoFicha();
    public void desactivarEventoFicha();
}
