package partida;

//import entidades.Ficha;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaControl {

    private PartidaView view;
    private PartidaModel modelo;

    public PartidaControl(PartidaView view, PartidaModel modelo) {
        this.view = view;
        this.modelo = modelo;
        cargarEventos();
        JugadorDTO jugador = new JugadorDTO();
        List<FichaDTO> fichas = new ArrayList<>();
        fichas.add(new FichaDTO(6,6,0));
        fichas.add(new FichaDTO(1,6,1));
        fichas.add(new FichaDTO(1,1,0));
        fichas.add(new FichaDTO(0,1,0));
        fichas.add(new FichaDTO(3,0,0));
        fichas.add(new FichaDTO(3,3,1));
        fichas.add(new FichaDTO(2,3,1));
        
        jugador.setFichas(fichas);
        modelo.setJugador(jugador);
        modelo.agregarFichasUsuarioActual(fichas);
    }
    
    //-------------------Eventos-------------------
    private void cargarEventos(){
        view.btnEjemploEvento(this::mostrarFichasMazo);
        view.eventoFicha(this::fichaDominoValidar);
        
    }
    
    public void fichaDominoValidar(MouseEvent e){
        
        Canvas dibujoFicha = (Canvas) e.getSource();
        FichaDTO ficha = modelo.obtenerFichaExacta(dibujoFicha);
        modelo.agregarFichaAlTablero(ficha, modelo.getTablero(), true);
    }
    
    public void mostrarFichasMazo(MouseEvent e){
        modelo.agregarFichaUsuarioActual(new FichaDTO(3,3));
    }
}
