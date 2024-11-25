package presentacion_dibujo;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.PosibleJugadaDTO;
import entidadesDTO.PosicionDTO;
import static entidadesDTO.PosicionDTO.ABAJO;
import static entidadesDTO.PosicionDTO.ARRIBA;
import static entidadesDTO.PosicionDTO.DERECHA;
import static entidadesDTO.PosicionDTO.IZQUIERDA;
import static entidadesDTO.PosicionDTO.MULA_HORIZONTAL;
import java.util.ArrayDeque;
import java.util.Deque;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Esta clase extiende de AnchorPane y maneja la representación gráfica del
 * tablero para un juego de dominó. Permite dibujar fichas, visualizar posibles
 * jugadas, manejar eventos del ratón y controlar el estado del tablero mediante
 * una matriz de ocupación.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class DibujoTablero extends AnchorPane {

    private static final int ANCHO_PANTALLA = 720;
    private static final int ALTO_PANTALLA = 480;
    private final Deque<DibujoFicha> trenFichas;
    private final boolean[][] matriz;
    private EventHandler<MouseEvent> opcionJugada;
    private boolean primerJugadaDerecha = false;
    private boolean primerJugadaIzq = false;

    /**
     * Constructor de DibujoTablero2
     *
     * Inicializa la matriz, el tren de fichas y configura las propiedades
     * gráficas del tablero, como su tamaño y estilo.
     */
    public DibujoTablero() {
        super(); // Llamar al constructor de AnchorPane
        this.matriz = new boolean[64][96]; // Alto x Ancho en unidades de 7.5px
        this.trenFichas = new ArrayDeque<>();
//        matriz[65][0] = true; ArrayIndexOutOfBoundsException

        // Configurar el tamaño del AnchorPane
        this.setPrefSize(ANCHO_PANTALLA, ALTO_PANTALLA);
        this.setMinSize(ANCHO_PANTALLA, ALTO_PANTALLA);
        this.setMaxSize(ANCHO_PANTALLA, ALTO_PANTALLA);

        // Opcional: Agregar un borde o color de fondo para visualizar el área de juego
        this.setStyle("-fx-background-color: #B5CB99; -fx-border-color: #cccccc;");
    }

    /**
     * Muestra las posibles jugadas en el tablero, basándose en la ficha
     * seleccionada y el tipo de jugada permitida.
     *
     * @param ficha La ficha a jugar.
     * @param tipoJugada El tipo de jugada posible.
     */
    public void mostrarPosiblesJugadas(FichaDTO ficha, PosibleJugadaDTO tipoJugada) {
        limpiarJugadas();

        if (trenFichas.isEmpty()) {
            dibujarPrimeraJugada();
            return;
        }

        // Mostrar jugadas según el tipo
        if (tipoJugada == PosibleJugadaDTO.AMBOS_EXTREMOS
                || tipoJugada == PosibleJugadaDTO.SOLO_IZQUIERDA) {
            procesarJugadasIzquierda(ficha);
        }

        if (tipoJugada == PosibleJugadaDTO.AMBOS_EXTREMOS
                || tipoJugada == PosibleJugadaDTO.SOLO_DERECHA) {
            procesarJugadasDerecha(ficha);
        }
    }

    /**
     * Limpia las jugadas posibles del tablero.
     */
    public void limpiarJugadas() {
        // Limpiar jugadas anteriores
        getChildren().removeIf(node -> node instanceof DibujoJugada);
    }

    /**
     * Dibuja la primera jugada en el centro del tablero.
     */
    private void dibujarPrimeraJugada() {
        // Primera jugada siempre va en el centro horizontal
        DibujoJugada jugada = new DibujoJugada();
        DibujoJugada.dibujarJugadaHorizontal(jugada);

        // Posicionar en el centro
        double centroX = (ANCHO_PANTALLA - jugada.getWidth()) / 2;
        double centroY = (ALTO_PANTALLA - jugada.getHeight()) / 2;

        jugada.setLayoutX(centroX);
        jugada.setLayoutY(centroY);

        configurarEventosJugada(jugada, PosicionDTO.MULA_HORIZONTAL, false);
        getChildren().add(jugada);
    }

    /**
     * Configura los eventos del ratón para una jugada específica.
     *
     * @param jugada La jugada a configurar.
     * @param posicion La posición de la jugada.
     * @param extremoIzq Indica si es el extremo izquierdo.
     */
    private void configurarEventosJugada(DibujoJugada jugada, PosicionDTO posicion, boolean extremoIzq) {
        jugada.setPosicion(posicion);
        jugada.setExtremoIzq(extremoIzq);
        jugada.setOnMouseClicked(opcionJugada);
    }

    /**
     * Dibuja una ficha en el tablero, basada en una jugada realizada.
     *
     * @param jugada La jugada que contiene la ficha y su posición.
     */
    public void dibujarFicha(JugadaRealizadaDTO jugada) {
        limpiarJugadas();
        FichaDTO ficha = jugada.getFicha();
        BuilderFichaTablero builder = new BuilderFichaTablero();
        coincideExtremos(ficha, jugada);

//        System.out.println("\nSe dibujara la ficha: " + ficha);
        switch (jugada.getPosicion()) {
            case ARRIBA,ABAJO,MULA_VERTICAL -> {
                builder.construirVertical(ficha);
            }
            case IZQUIERDA,DERECHA,MULA_HORIZONTAL -> {

                builder.construirHorizontal(ficha);
            }
            default -> {
                System.out.println("Posicion inválida");
            }
        }

        DibujoFicha dibujo = builder.resultado();
        ubicarFicha(dibujo, jugada);
        asignarExtremo(ficha, dibujo);
        ocuparEspacios(jugada);
        insertarAlTablero(dibujo);
        this.getChildren().add(dibujo);
//        System.out.println("dibujo : " + dibujo);
    }

    /**
     * Inserta la ficha en el tablero.
     *
     * Este método se asegura de que el dibujo de la ficha sea añadido
     * correctamente al tablero en la posición especificada.
     *
     * @param dibujo El dibujo de la ficha que será añadido al tablero.
     */
    private void insertarAlTablero(DibujoFicha dibujo) {
        if (trenFichas.isEmpty()) {
            trenFichas.offerFirst(dibujo);
            return;
        }
        if (dibujo.sePusoEnLaIzquierda()) {
            primerJugadaIzq = true;
            trenFichas.addFirst(dibujo);
        } else {
            primerJugadaDerecha = true;
            trenFichas.addLast(dibujo);
        }
    }

    /**
     * Marca como ocupados los espacios de la matriz correspondientes a la ficha
     * colocada.
     *
     * Calcula las celdas de la matriz de ocupación que están cubiertas por la
     * ficha, y las marca como ocupadas para evitar colisiones futuras.
     *
     * @param jugada La jugada realizada que contiene la posición y dimensiones
     * de la ficha.
     */
    private void ocuparEspacios(JugadaRealizadaDTO jugada) {
        int indexX = (int) (jugada.getCoordenadaX() / 15);
        int indexY = (int) (jugada.getCoordenadaY() / 15);

        if (jugada.getPosicion() == PosicionDTO.ARRIBA
                || jugada.getPosicion() == PosicionDTO.ABAJO
                || jugada.getPosicion() == PosicionDTO.MULA_VERTICAL) {
            for (int i = 0; i < 4; i++) {
                matriz[indexY + i][indexX] = true;
                matriz[indexY + i][indexX + 1] = true;
            }
        } else {
            for (int i = 0; i < 4; i++) {
                matriz[indexY][indexX + i] = true;
                matriz[indexY + 1][indexX + i] = true;
            }
        }
    }

    /**
     * Ubica la ficha en las coordenadas correctas del tablero según la jugada.
     *
     * Calcula las coordenadas X e Y donde debe colocarse la ficha basándose en
     * su posición y orientación.
     *
     * @param ficha La ficha que se va a posicionar en el tablero.
     * @param jugada La jugada que contiene la posición y orientación de la
     * ficha.
     */
    private void ubicarFicha(DibujoFicha dibujo, JugadaRealizadaDTO jugada) {
        double layoutX = jugada.getCoordenadaX();
        double layoutY = jugada.getCoordenadaY();
        dibujo.setLayoutX(layoutX);
        dibujo.setLayoutY(layoutY);
        dibujo.setPosicion(jugada.getPosicion());
        dibujo.setExtremoIzq(jugada.isExtremoIzq());
    }

    /**
     * Verifica si los extremos de la ficha coinciden con los valores necesarios
     * para jugar.
     *
     * Si es necesario, rota la ficha para que los valores coincidan con la
     * posición en el tablero donde se colocará.
     *
     * @param ficha La ficha que se va a verificar y posiblemente rotar.
     * @param jugada La jugada que contiene la posición y la orientación
     * deseada.
     */
    private void coincideExtremos(FichaDTO ficha, JugadaRealizadaDTO jugada) {
        if (trenFichas.isEmpty()) {
            return;
        }

        DibujoFicha dibujoFicha;
        if (jugada.isExtremoIzq()) {
            dibujoFicha = trenFichas.getFirst();
        } else {
            dibujoFicha = trenFichas.getLast();
        }
        int num = dibujoFicha.getExtremo();
        switch (jugada.getPosicion()) {
            case ARRIBA -> {
                if (ficha.getDerecha() != num) {
                    ficha.girarFicha();
                }
            }
            case ABAJO -> {
                if (ficha.getIzquierda() != num) {
                    ficha.girarFicha();
                }
            }
            case IZQUIERDA -> {
                if (ficha.getDerecha() != num) {
                    ficha.girarFicha();
                }
            }
            case DERECHA -> {
                if (ficha.getIzquierda() != num) {
                    ficha.girarFicha();
                }
            }
            case MULA_HORIZONTAL -> {
                if (jugada.getPosicion() == PosicionDTO.DERECHA) {
                    if (ficha.getDerecha() != num) {
                        ficha.girarFicha();
                    }
                } else {
                    if (ficha.getIzquierda() != num) {
                        ficha.girarFicha();
                    }
                }
            }
        }
    }

    /**
     * Asigna la ficha a uno de los extremos del tren.
     *
     * Actualiza la referencia de los extremos del tren dependiendo si la ficha
     * se coloca en el extremo izquierdo o derecho.
     *
     * @param ficha La ficha que se colocó en el tablero.
     * @param dibujo El dibujo de la ficha que representa gráficamente la ficha.
     */
    private void asignarExtremo(FichaDTO ficha, DibujoFicha dibujo) {
        switch (dibujo.getPosicion()) {
            case ARRIBA -> {
                DibujoFicha dibujoFicha;
                if (dibujo.isExtremoIzq()) {
                    dibujoFicha = trenFichas.getFirst();
                } else {
                    dibujoFicha = trenFichas.getLast();
                }
                if (dibujoFicha.getExtremo() == ficha.getIzquierda()) {
                    dibujo.setExtremo(ficha.getDerecha());
                } else {
                    dibujo.setExtremo(ficha.getIzquierda());
                }
//                dibujo.setExtremo(ficha.getDerecha());
            }
            case ABAJO ->{
                DibujoFicha dibujoFicha;
                if (dibujo.isExtremoIzq()) {
                    dibujoFicha = trenFichas.getFirst();
                } else {
                    dibujoFicha = trenFichas.getLast();
                }
                if (dibujoFicha.getExtremo() == ficha.getDerecha()) {
                    dibujo.setExtremo(ficha.getIzquierda());
                } else {
                    dibujo.setExtremo(ficha.getDerecha());
                }
//                dibujo.setExtremo(ficha.getIzquierda());
            }
            case IZQUIERDA -> {
                DibujoFicha dibujoFicha;
                if (dibujo.isExtremoIzq()) {
                    dibujoFicha = trenFichas.getFirst();
                } else {
                    dibujoFicha = trenFichas.getLast();
                }
                if (dibujoFicha.getExtremo() == ficha.getDerecha()) {
                    dibujo.setExtremo(ficha.getIzquierda());
                } else {
                    dibujo.setExtremo(ficha.getDerecha());
                }
//                dibujo.setExtremo(ficha.getDerecha());
            }
            case DERECHA -> {
                DibujoFicha dibujoFicha;
                if (dibujo.isExtremoIzq()) {
                    dibujoFicha = trenFichas.getFirst();
                } else {
                    dibujoFicha = trenFichas.getLast();
                }
                if (dibujoFicha.getExtremo() == ficha.getIzquierda()) {
                    dibujo.setExtremo(ficha.getDerecha());
                } else {
                    dibujo.setExtremo(ficha.getIzquierda());
                }
//                dibujo.setExtremo(ficha.getIzquierda());
            }

            default ->
                dibujo.setExtremo(ficha.getIzquierda());
        }

    }

    /**
     * Configura el manejador de eventos para las jugadas posibles.
     *
     * @param opcionJugada El manejador de eventos.
     */
    public void setOpcionJugada(EventHandler<MouseEvent> opcionJugada) {
        this.opcionJugada = opcionJugada;
    }

    private void asignarJugada(DibujoFicha fichaDelTablero, boolean extremoIzq) {
        double posX = fichaDelTablero.getLayoutX();
        double posY = fichaDelTablero.getLayoutY();

        // Verificar la posición de la ficha actual
        switch (fichaDelTablero.getPosicion()) {
            case MULA_VERTICAL -> {
                mostrarOpcionesVerticales(posX, posY, fichaDelTablero, extremoIzq);
            }
            case MULA_HORIZONTAL -> {
                mostrarOpcionesHorizontales(posX, posY, fichaDelTablero, extremoIzq);
            }
            case ARRIBA, ABAJO -> {
                dibujarJugadaArribaOAbajo(fichaDelTablero, extremoIzq);
            }
            default -> {
                dibujarJugadaIzquierdaODerecha(fichaDelTablero, extremoIzq);
            }
        }
    }

    private void dibujarJugadaArriba(DibujoFicha fichaDelTablero, boolean extremoIzq) {

    }

    private void dibujarJugadaArribaOAbajo(DibujoFicha fichaDelTablero, boolean extremoIzq) {
        DibujoJugada dibujoJugada = new DibujoJugada();
        dibujoJugada.setExtremoIzq(extremoIzq);

        double posX = fichaDelTablero.getLayoutX();
        double posY = fichaDelTablero.getLayoutY();

        DibujoJugada.dibujarJugadaVertical(dibujoJugada);
        if (fichaDelTablero.getPosicion() == ARRIBA) {
            dibujoJugada.setLayoutY(posY - dibujoJugada.getHeight());
        } else {
            dibujoJugada.setLayoutY(posY + fichaDelTablero.getHeight());
        }
        dibujoJugada.setLayoutX(posX);
        configurarEventosJugada(dibujoJugada, fichaDelTablero.getPosicion(), extremoIzq);

//        if (esJugadaValida(dibujoJugada)) {
        getChildren().add(dibujoJugada);
//        }
    }

    private void dibujarJugadaIzquierdaODerecha(DibujoFicha fichaDelTablero, boolean extremoIzq) {
        DibujoJugada dibujoJugada = new DibujoJugada();
        dibujoJugada.setExtremoIzq(extremoIzq);

        double posX = fichaDelTablero.getLayoutX();
        double posY = fichaDelTablero.getLayoutY();

        DibujoJugada.dibujarJugadaHorizontal(dibujoJugada);
        if (fichaDelTablero.getPosicion() == IZQUIERDA) {
            dibujoJugada.setLayoutX(posX - dibujoJugada.getWidth());
        } else {
            dibujoJugada.setLayoutX(posX + fichaDelTablero.getWidth());
        }
        dibujoJugada.setLayoutY(posY);
        configurarEventosJugada(dibujoJugada, fichaDelTablero.getPosicion(), extremoIzq);

        // Verificar si la posición está dentro de los límites y no ocupada
//        if (esJugadaValida(dibujoJugada)) {
        getChildren().add(dibujoJugada);
//        }

    }

    private void dibujarJugadaMula(DibujoFicha fichaActual, boolean extremoIzq) {
        double posX = fichaActual.getLayoutX();
        double posY = fichaActual.getLayoutY();
        boolean horizontal;

        DibujoJugada jugadaHorizontal = new DibujoJugada();
        DibujoJugada jugadaVertical = new DibujoJugada();
        if (fichaActual.getPosicion() == PosicionDTO.ABAJO
                || fichaActual.getPosicion() == PosicionDTO.ARRIBA) {
            DibujoJugada.dibujarJugadaHorizontal(jugadaHorizontal);
            horizontal = true;
        } else {
            DibujoJugada.dibujarJugadaVertical(jugadaVertical);
            horizontal = false;
        }

        // Crear jugadas para ambas orientaciones
        // Posicionar jugada vertical
        if (extremoIzq) {
            if (horizontal) {
                if (fichaActual.getPosicion() == PosicionDTO.ARRIBA) {

                    jugadaHorizontal.setLayoutX(posX - 15);
                    jugadaHorizontal.setLayoutY(posY - 30);
                } else {
                    jugadaHorizontal.setLayoutX(posX - 15);
                    jugadaHorizontal.setLayoutY(posY + 60);

                }

            } else {
                jugadaVertical.setLayoutX(posX - 30);
                jugadaVertical.setLayoutY(posY - (jugadaVertical.getWidth() / 2));
            }

        } else {
            if (horizontal) {
                if (fichaActual.getPosicion() == PosicionDTO.ARRIBA) {

                    jugadaHorizontal.setLayoutX(posX - 15);
                    jugadaHorizontal.setLayoutY(posY - 30);
                } else {
                    jugadaHorizontal.setLayoutX(posX - 15);
                    jugadaHorizontal.setLayoutY(posY + 60);

                }
            } else {
                jugadaVertical.setLayoutX(posX + 60);
                jugadaVertical.setLayoutY(posY - (jugadaVertical.getWidth() / 2));
            }
        }

        // Configurar eventos para ambas orientaciones
        configurarEventosJugada(jugadaVertical, PosicionDTO.MULA_VERTICAL, extremoIzq);
        configurarEventosJugada(jugadaHorizontal, PosicionDTO.MULA_HORIZONTAL, extremoIzq);

        // Agregar solo las jugadas válidas
        if (esJugadaValidaMula(jugadaVertical, true) && !horizontal) {
            getChildren().add(jugadaVertical);
        }
        if (esJugadaValidaMula(jugadaHorizontal, false) && horizontal) {
            getChildren().add(jugadaHorizontal);
        }
    }

    private void procesarJugadasDerecha(FichaDTO ficha) {
        DibujoFicha fichaDerecha = trenFichas.getLast();

        if (!primerJugadaDerecha) {
            mostrarOpcionesHorizontales2(fichaDerecha, false);
            return;
        }

        if (ficha.esMula()) {
            dibujarJugadaMula(fichaDerecha, false);
        } else {
            asignarJugada(fichaDerecha, false);
        }
    }

    private void procesarJugadasIzquierda(FichaDTO ficha) {
        DibujoFicha fichaIzquierda = trenFichas.getFirst();

        if (!primerJugadaIzq) {
            mostrarOpcionesHorizontales2(fichaIzquierda, true);
            return;
        }

        if (ficha.esMula()) {
            dibujarJugadaMula(fichaIzquierda, true);
        } else {
            asignarJugada(fichaIzquierda, true);
        }
    }

    private void mostrarOpcionesVerticales(double posX, double posY, DibujoFicha ficha, boolean extremoIzq) {
        DibujoJugada jugadaArriba = new DibujoJugada();
        DibujoJugada jugadaAbajo = new DibujoJugada();

        DibujoJugada.dibujarJugadaVertical(jugadaArriba);
        DibujoJugada.dibujarJugadaVertical(jugadaAbajo);

        jugadaArriba.setLayoutX(posX);
        jugadaAbajo.setLayoutX(posX);

        jugadaArriba.setLayoutY(posY - jugadaArriba.getHeight());
        jugadaAbajo.setLayoutY(posY + ficha.getHeight());

//        if (esJugadaValida(jugadaArriba)) {
        configurarEventosJugada(jugadaArriba, PosicionDTO.ARRIBA, extremoIzq);
        getChildren().add(jugadaArriba);
//        }
//        if (esJugadaValida(jugadaAbajo)) {
        configurarEventosJugada(jugadaAbajo, PosicionDTO.ABAJO, extremoIzq);
        getChildren().add(jugadaAbajo);
//        }
    }

    private void mostrarOpcionesHorizontales2(DibujoFicha ficha, boolean extremoIzq) {
        DibujoJugada jugada = new DibujoJugada();
        DibujoJugada.dibujarJugadaHorizontal(jugada);
        double posX = ficha.getLayoutX();
        double posY = ficha.getLayoutY();

        if (extremoIzq) {
            jugada.setLayoutX(posX - jugada.getWidth());
        } else {
            jugada.setLayoutX(posX + ficha.getWidth());
        }
        jugada.setLayoutY(posY);

//        if (esJugadaValida(jugada)) {
        configurarEventosJugada(jugada, extremoIzq ? PosicionDTO.IZQUIERDA : PosicionDTO.DERECHA, extremoIzq);
        getChildren().add(jugada);
//        }

    }

    private void mostrarOpcionesHorizontales(double posX, double posY, DibujoFicha ficha, boolean extremoIzq) {
        DibujoJugada jugadaIzquierda = new DibujoJugada();
        DibujoJugada jugadaDerecha = new DibujoJugada();

        DibujoJugada.dibujarJugadaHorizontal(jugadaIzquierda);
        DibujoJugada.dibujarJugadaHorizontal(jugadaDerecha);

        jugadaIzquierda.setLayoutX(posX - 60);
        jugadaDerecha.setLayoutX(posX + 60);

        jugadaIzquierda.setLayoutY(posY);
        jugadaDerecha.setLayoutY(posY);

//        if (esJugadaValida(jugadaIzquierda)) {
        configurarEventosJugada(jugadaIzquierda, PosicionDTO.IZQUIERDA, extremoIzq);
        getChildren().add(jugadaIzquierda);
//        }
//        if (esJugadaValida(jugadaDerecha)) {
        configurarEventosJugada(jugadaDerecha, PosicionDTO.DERECHA, extremoIzq);
        getChildren().add(jugadaDerecha);
//        }
    }

    private boolean esJugadaValidaMula(DibujoJugada jugada, boolean esVertical) {
        int indexX = (int) (jugada.getLayoutX() / 7.5);
        int indexY = (int) (jugada.getLayoutY() / 7.5);

        if (esVertical) {
            // Verificar espacio para mula vertical
            if (indexX < 0 || indexY < 0 || indexX + 2 >= matriz[0].length || indexY + 4 >= matriz.length) {
                return false;
            }
            // Verificar colisiones para mula vertical
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    if (matriz[indexY + i][indexX + j]) {
                        return false;
                    }
                }
            }
        } else {
            // Verificar espacio para mula horizontal
            if (indexX < 0 || indexY < 0 || indexX + 4 >= matriz[0].length || indexY + 2 >= matriz.length) {
                return false;
            }
            // Verificar colisiones para mula horizontal
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    if (matriz[indexY + i][indexX + j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean esJugadaValida(DibujoJugada jugada) {
        int indexX = (int) (jugada.getLayoutX() / 7.5);
        int indexY = (int) (jugada.getLayoutY() / 7.5);

        // Verificar orientación de la jugada
        boolean esVertical = jugada.getPosicion() == PosicionDTO.ARRIBA
                || jugada.getPosicion() == PosicionDTO.ABAJO;

        if (esVertical) {
            // Validación para fichas verticales
            if (indexX < 0 || indexY < 0 || indexX + 2 >= matriz[0].length || indexY + 4 >= matriz.length) {
                return false;
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    if (matriz[indexY + i][indexX + j]) {
                        return false;
                    }
                }
            }
        } else {
            // Validación para fichas horizontales
            if (indexX < 0 || indexY < 0 || indexX + 4 >= matriz[0].length || indexY + 2 >= matriz.length) {
                return false;
            }
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    if (matriz[indexY + i][indexX + j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
