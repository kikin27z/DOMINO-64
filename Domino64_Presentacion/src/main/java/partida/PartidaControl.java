package partida;

import manejadorTablero.ManejadorTablero;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.PosibleJugadaDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import presentacion_dibujo.BuilderFichaMazo;
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
//        CuentaDTO cuenta = new CuentaDTO(0, "/avatar/tortuga.png", "Karim"); //Quitar esto es harcodeo----------------------------------------------
//        view.insertarMesaAba(cuenta);//Quitar esto es harcodeo--------------------------------------------------------------------------------------
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
        modelo.actualizarTurno(null);
    }

    private void jalarPozo(MouseEvent e) {
        List<FichaDTO> fichas = new ArrayList<>();
        fichas.add(new FichaDTO(6, 6));
        fichas.add(new FichaDTO(6, 0));
        fichas.add(new FichaDTO(3, 0));
        
        fichas.add(new FichaDTO(6, 2));
        fichas.add(new FichaDTO(3, 2));
        fichas.add(new FichaDTO(3, 3));
        fichas.add(new FichaDTO(3, 4));
        fichas.add(new FichaDTO(5, 4));
        fichas.add(new FichaDTO(5, 1));
        fichas.add(new FichaDTO(1, 1));
        fichas.add(new FichaDTO(1, 4));
        fichas.add(new FichaDTO(2, 4));
        
        modelo.actualizarDarFichas(fichas);
    }

    private void rendirse(MouseEvent e) {
        
    }

    private void procesarFicha(MouseEvent e) {
        DibujoJugada dibujoJugada = (DibujoJugada) e.getSource();
        JugadaRealizadaDTO jugada = modelo.crearJugadaRealizada(dibujoJugada);
        view.dibujarFicha(jugada);
        view.quitarFichaMazo();
        
        //Simulacion de que se guarda la ficha en el tablero y obtiene una nueva jugada
        aquiNoVaEsto(jugada);
    }

    private void evaluarFicha(MouseEvent e) {
        if (!modelo.esMiTurno()) {
            return;
        }
        Canvas dibujo = (Canvas) e.getSource();
        FichaDTO ficha = modelo.obtenerFicha(dibujo);
        if (modelo.esPrimeraJugadaHecha()) {
            evaluarPrimeraJugada(ficha);
            return;
        }
        PosibleJugadaDTO posibleJugada = modelo.obtenerPosibleJugada(ficha);
        view.dibujarJugada(ficha, posibleJugada);
    }
    
    private void evaluarPrimeraJugada(FichaDTO ficha) {
        if (modelo.esLaMulaAlta(ficha)) {
            view.dibujarJugada(ficha, null);
        }
    }
    private void aquiNoVaEsto(JugadaRealizadaDTO jugada){
        tablero.colocarFicha(jugada);
        JugadaDTO jugadaProxima = tablero.obtenerProximaJugada();
        modelo.actualizarTurno(jugadaProxima);
    }

}
