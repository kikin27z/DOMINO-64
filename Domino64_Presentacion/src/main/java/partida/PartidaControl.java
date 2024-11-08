package partida;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadaValidaDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import presentacion_dibujo.DibujoJugada;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaControl {

    private final PartidaView view;
    private final PartidaModel modelo;
    private boolean pruebaUsada = false;

    public PartidaControl(PartidaView view, PartidaModel modelo) {
        this.view = view;
        this.modelo = modelo;

        CuentaDTO cuenta = new CuentaDTO(0, "/avatar/tortuga.png", "Karim"); //Quitar esto es harcodeo----------------------------------------------
        view.insertarMesaAba(cuenta);//Quitar esto es harcodeo--------------------------------------------------------------------------------------
        cargarEventos();
    }

    //-------------------Eventos-------------------
    private void cargarEventos() {
        view.btnEjemplo(this::btnEjemplo);
        view.evaluarFicha(this::evaluarFicha);
    }

    private void btnEjemplo(MouseEvent e) {
        List<FichaDTO> fichas = new ArrayList<>();
        fichas.add(new FichaDTO(6, 6));
        fichas.add(new FichaDTO(4, 6));
        fichas.add(new FichaDTO(4, 1));
        fichas.add(new FichaDTO(0, 1));
        fichas.add(new FichaDTO(3, 0));
        fichas.add(new FichaDTO(3, 6));
        fichas.add(new FichaDTO(4, 3));
        modelo.avisarDarFichas(fichas);
    }

    private void evaluarFicha(MouseEvent e) {
        Canvas dibujo = (Canvas) e.getSource();
        FichaDTO ficha = modelo.obtenerFicha(dibujo);

        JugadaDTO jugada = modelo.getJugada();
        JugadaValidaDTO valido = jugada.determinarJugada(ficha);
        System.out.println(valido);
        modelo.jugadaValida = valido;
    }



    public void evaluarJugada() {
        Canvas dibujo = null;
        FichaDTO ficha = modelo.obtenerFicha(dibujo);

        System.out.println(ficha);
        if (modelo.primeraJugadaHecha) {
            JugadaDTO jugada = modelo.getJugada();
            JugadaValidaDTO valido = jugada.determinarJugada(ficha);
            System.out.println(valido);
            modelo.jugadaValida = valido;
            view.dibujarJugadas(valido, ficha);

        } else {
            if (ficha.esMula()) {
//                view.colocarPrimeraMula(ficha);
                modelo.primeraJugadaHecha = true;
//                modelo.quitarFichaMazo(dibujo);
                modelo.actualizarJugada(ficha.getDerecha(), 0);
            }
        }
    }

    private void colocarFicha() {
        DibujoJugada canva = null;
//        view.colocarFicha(modelo.fichaSeleccionada, canva);
    }


}
