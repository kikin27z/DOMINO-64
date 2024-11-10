package presentacion_dibujo;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.JugadaValidaDTO;
import entidadesDTO.PosicionDTO;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;



public class DibujoTablero2 extends AnchorPane {
    
    private static final int ANCHO_PANTALLA = 720;
    private static final int ALTO_PANTALLA = 480;
    private static final int FICHA_ANCHO = 60;
    private static final int FICHA_ALTO = 30;
    private static final double CELDA_SIZE = 7.5;
    
    private final Deque<DibujoFicha> trenFichas;
    private boolean[][] matriz;
    private List<JugadaRealizadaDTO> jugadasRealizadas;
    private final EventHandler<MouseEvent> opcionJugada;
    // ... (mantener las constantes y variables anteriores) ...

    public DibujoTablero2(EventHandler<MouseEvent> opcionJugada) {
        super(); // Llamar al constructor de AnchorPane
        this.matriz = new boolean[64][96]; // Alto x Ancho en unidades de 7.5px
        this.jugadasRealizadas = new ArrayList<>();
        this.trenFichas = new ArrayDeque<>();
        this.opcionJugada = opcionJugada;
        
        // Configurar el tamaño del AnchorPane
        this.setPrefSize(ANCHO_PANTALLA, ALTO_PANTALLA);
        this.setMinSize(ANCHO_PANTALLA, ALTO_PANTALLA);
        this.setMaxSize(ANCHO_PANTALLA, ALTO_PANTALLA);
        
        // Opcional: Agregar un borde o color de fondo para visualizar el área de juego
        this.setStyle("-fx-background-color: #B5CB99; -fx-border-color: #cccccc;");
    }

    
    public void mostrarPosiblesJugadas(FichaDTO ficha, JugadaValidaDTO tipoJugada) {
        // Limpiar jugadas anteriores
        getChildren().removeIf(node -> node instanceof DibujoJugada);
        
        if (jugadasRealizadas.isEmpty()) {
            primeraFicha();
            return;
        }

        JugadaRealizadaDTO ultimaJugada = jugadasRealizadas.get(jugadasRealizadas.size() - 1);
        
        // Mostrar jugadas según el tipo
        if (tipoJugada == JugadaValidaDTO.AMBOS_EXTREMOS || 
            tipoJugada == JugadaValidaDTO.SOLO_IZQUIERDA) {
            mostrarJugadaExtremo(true);
        }
        
        if (tipoJugada == JugadaValidaDTO.AMBOS_EXTREMOS || 
            tipoJugada == JugadaValidaDTO.SOLO_DERECHA) {
            mostrarJugadaExtremo(false);
        }
    }
    
    private void primeraFicha(){
        // Primera jugada siempre va en el centro horizontal
            DibujoJugada jugada = new DibujoJugada();
            DibujoJugada.dibujarJugadaHorizontal(jugada);
            
            // Posicionar en el centro
            double centroX = (ANCHO_PANTALLA - jugada.getWidth()) / 2;
            double centroY = (ALTO_PANTALLA - jugada.getHeight()) / 2;
            
            AnchorPane.setTopAnchor(jugada, centroY);
            AnchorPane.setLeftAnchor(jugada, centroX);
            
            configurarEventosJugada(jugada, PosicionDTO.DERECHA, true);
            getChildren().add(jugada);
    }

    private void mostrarJugadaExtremo(boolean esIzquierda) {
        // Obtener la posición del extremo actual
        int[] posExtremo = obtenerPosicionExtremo(esIzquierda);
        
        // Crear jugadas posibles en cada dirección
        crearJugadaDireccion(posExtremo[0], posExtremo[1], PosicionDTO.DERECHA, esIzquierda);
        crearJugadaDireccion(posExtremo[0], posExtremo[1], PosicionDTO.IZQUIERDA, esIzquierda);
        crearJugadaDireccion(posExtremo[0], posExtremo[1], PosicionDTO.ARRIBA, esIzquierda);
        crearJugadaDireccion(posExtremo[0], posExtremo[1], PosicionDTO.ABAJO, esIzquierda);
    }

    private void crearJugadaDireccion(int x, int y, PosicionDTO posicion, boolean extremoIzq) {
        // Calcular nueva posición según la dirección
        int[] nuevaPos = calcularNuevaPosicion(x, y, posicion);
        boolean horizontal = (posicion == PosicionDTO.DERECHA || posicion == PosicionDTO.IZQUIERDA);
        
        // Verificar si hay espacio disponible
        if (verificarEspacio(nuevaPos[1], nuevaPos[0], horizontal)) {
            DibujoJugada jugada = new DibujoJugada();
            if (horizontal) {
                DibujoJugada.dibujarJugadaHorizontal(jugada);
            } else {
                DibujoJugada.dibujarJugadaVertical(jugada);
            }
            
            // Posicionar la jugada
            AnchorPane.setTopAnchor(jugada, (double)nuevaPos[1]);
            AnchorPane.setLeftAnchor(jugada, (double)nuevaPos[0]);
            
            configurarEventosJugada(jugada, posicion, extremoIzq);
            getChildren().add(jugada);
        }
    }

    private boolean verificarEspacio(int fila, int columna, boolean horizontal) {
        return true;
    }
    
    private void configurarEventosJugada(DibujoJugada jugada, PosicionDTO posicion, boolean extremoIzq) {
        jugada.setPosicion(posicion);
        jugada.setExtremo(extremoIzq);
        
        jugada.setOnMouseClicked(opcionJugada);
//        jugada.setOnMouseClicked(event -> {
//            // Aquí puedes agregar el código para manejar el clic en la jugada
//            System.out.println("Jugada seleccionada: " + posicion + " - Extremo: " + extremoIzq);
//        });
    }

    public void dibujarFicha(FichaDTO ficha, double x, double y, PosicionDTO posicion) {
        BuilderFichaTablero builder = new BuilderFichaTablero();
        builder.construirHorizontal(ficha);
        
        DibujoFicha dibujo = builder.resultado();
        dibujo.setLayoutX(x);
        dibujo.setLayoutY(y);
        dibujo.setPosicion(PosicionDTO.ARRIBA);
        calcularEspacios(dibujo, (int) x,(int) y);
        this.getChildren().add(dibujo);
    }
    
    private void calcularEspacios(DibujoFicha dibujoFicha, int x, int y){
        int indexX = x / 15;
            int indexY = y / 15;
        if (dibujoFicha.getPosicion() == PosicionDTO.IZQUIERDA || dibujoFicha.getPosicion() == PosicionDTO.DERECHA) {
            matriz[indexY-1][indexX-1] = true;
            matriz[indexY][indexX-1] = true;
            matriz[indexY-1][indexX] = true;
            matriz[indexY][indexX] = true;
            matriz[indexY-1][indexX+1] = true;
            matriz[indexY][indexX+1] = true;
            matriz[indexY-1][indexX+2] = true;
            matriz[indexY][indexX+2] = true;
            
            
        } else {
            matriz[indexY-1][indexX-1] = true;
            matriz[indexY][indexX-1] = true;
            matriz[indexY+1][indexX-1] = true;
            matriz[indexY+2][indexX-1] = true;
            matriz[indexY-1][indexX] = true;
            matriz[indexY][indexX] = true;
            matriz[indexY+1][indexX] = true;
            matriz[indexY+2][indexX] = true;
        }
        imprimirMatriz();
    }
    
    private  void imprimirMatriz() {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        // Imprimir línea superior de la cuadrícula
        System.out.print("   ");
        for (int c = 0; c < columnas; c++) {
            System.out.print((c % 10) + " ");
        }
        System.out.println();

        // Imprimir cada fila de la matriz
        for (int i = 0; i < filas; i++) {
            // Imprimir número de fila
            System.out.printf("%02d ", i+1);

            // Imprimir contenido de la fila
            for (int j = 0; j < columnas; j++) {
                System.out.print("|" + (matriz[i][j] ? "*" : " "));
            }
            System.out.println("|"); // Cierre de la fila
        }

        // Imprimir línea inferior de la cuadrícula
        System.out.print("   ");
        for (int c = 0; c < columnas; c++) {
            System.out.print("--");
        }
        System.out.println();
    }

    private int[] obtenerPosicionExtremo(boolean esIzquierda) {
        return null;
    }

    private int[] calcularNuevaPosicion(int x, int y, PosicionDTO posicion) {
        return null;
    }

    public void limpiarTablero() {
        getChildren().clear();
    }
}