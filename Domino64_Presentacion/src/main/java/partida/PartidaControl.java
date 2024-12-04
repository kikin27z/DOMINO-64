package partida;

import manejadorTablero.ManejadorTablero;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.PosibleJugadaDTO;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import presentacion_dibujo.DibujoJugada;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaControl {

    private final PartidaView view;
    private final PartidaModel modelo;
    private boolean pruebaUsada = false;
    private ManejadorTablero tablero = new ManejadorTablero();

    public PartidaControl(PartidaView view, PartidaModel modelo) {
        this.view = view;
        this.modelo = modelo;
        cargarEventos();
    }

    //-------------------Eventos-------------------
    private void cargarEventos() {
        view.evaluarFicha(this::evaluarFicha);
        view.procesarJugada(this::procesarFicha);
        view.clicPausa(this::mostrarPausa);
        view.clicRendirse(this::rendirse);
        view.clicPozo(this::jalarPozo);
        view.asignarProcesarJugada();
    }

    private void mostrarPausa(MouseEvent e) {
        modelo.actualizarProximaJugada(null);
    }

    private void jalarPozo(MouseEvent e) {
        if (!modelo.esMiTurno()) {
            return;
        }
        modelo.avisarJalarFichaPozo();
    }

    private void rendirse(MouseEvent e) {
        modelo.avisarPeticionRendirse();
    }

    private void procesarFicha(MouseEvent e) {
        DibujoJugada dibujoJugada = (DibujoJugada) e.getSource();
        JugadaRealizadaDTO jugada = modelo.crearJugadaRealizada(dibujoJugada);
        view.quitarFichaMazo();
        modelo.quitarMapeoFichas();
        view.limpiarJugadas();
        modelo.avisarJugadaRealizada(jugada);
        
//        //Simulacion de que se guarda la ficha en el tablero y obtiene una nueva jugada
//        aquiNoVaEsto(jugada);
    }

    private void evaluarFicha(MouseEvent e) {
        if (!modelo.esMiTurno()) {
            System.out.println("no es tu turnooo");
            return;
        }
        Canvas dibujo = (Canvas) e.getSource();
        FichaDTO ficha = modelo.obtenerFicha(dibujo);
        if (!modelo.esPrimeraJugadaHecha()) {
            evaluarPrimeraJugada(ficha);
            return;
        }
        PosibleJugadaDTO posibleJugada = modelo.obtenerPosibleJugada(ficha);
        System.out.println("posibleJugada = "+posibleJugada);
        view.dibujarJugada(ficha, posibleJugada);
    }
    
    private void evaluarPrimeraJugada(FichaDTO ficha) {
        if (modelo.esLaMulaAlta(ficha)) {
            view.dibujarJugada(ficha, null);
        }
    }
//    private void aquiNoVaEsto(JugadaRealizadaDTO jugada){
//        tablero.colocarFicha(jugada);
//        JugadaDTO jugadaProxima = tablero.obtenerProximaJugada();
//        modelo.actualizarProximaJugada(jugadaProxima);
//    }

}
