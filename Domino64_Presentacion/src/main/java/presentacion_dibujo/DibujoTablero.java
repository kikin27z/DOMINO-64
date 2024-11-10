package presentacion_dibujo;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaValidaDTO;
import entidadesDTO.PosicionDTO;
import java.util.ArrayDeque;
import java.util.Deque;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author karim
 */
public class DibujoTablero extends AnchorPane {

    private final Deque<DibujoFicha> trenFichas;
    private final EventHandler<MouseEvent> opcionJugada;
    private final double height;
    private final double width;
    public static int num=0;

    public DibujoTablero(EventHandler<MouseEvent> opcionJugada, double width, double height) {
        this.opcionJugada = opcionJugada;
        this.height = height;
        this.width = width;
        this.setPrefSize(width, height);
        this.trenFichas = new ArrayDeque<>();
    }


    public void dibujarPrimeraFicha(FichaDTO ficha) {
        BuilderFichaTablero plantilla = new BuilderFichaTablero();
        plantilla.construirHorizontal(ficha);
        DibujoFicha dibujo = plantilla.resultado();

        double layoutY = (height / 2) - 20;
        double layoutX = (width / 2) - 36;
        dibujo.setLayoutX(layoutX);
        dibujo.setLayoutY(layoutY);
        dibujo.setMula(true);
        dibujo.setPosicion(PosicionDTO.CENTRO);
        dibujo.setExtremo(ficha.getDerecha());
        trenFichas.add(dibujo);
        this.getChildren().add(dibujo);
    }

    public void colocarJugadas(JugadaValidaDTO jugada, FichaDTO ficha) {
        switch (jugada) {
            case JugadaValidaDTO.AMBOS_EXTREMOS -> {
                dibujarJugadaIzq(ficha);
                dibujarJugadaDer(ficha);
            }
            case JugadaValidaDTO.SOLO_IZQUIERDA -> {
                dibujarJugadaIzq(ficha);
            }
            case JugadaValidaDTO.SOLO_DERECHA -> {
                dibujarJugadaDer(ficha);
            }
            case JugadaValidaDTO.NINGUNA -> {
                limpiarJugadas();
            }
        }
    }

    public DibujoFicha colocarFicha(FichaDTO ficha, DibujoJugada dibujoJugada) {
        DibujoFicha dibujoFicha = posicionarFicha(ficha, dibujoJugada);
        limpiarJugadas();
        this.getChildren().add(dibujoFicha);
        return dibujoFicha;
    }

    private DibujoFicha posicionarFicha(FichaDTO ficha, DibujoJugada jugada) {
        BuilderFichaTablero plantilla = new BuilderFichaTablero();
        coincideExtremos(ficha, jugada);
        if (jugada.getPosicion() == PosicionDTO.IZQUIERDA || jugada.getPosicion() == PosicionDTO.DERECHA) {
            plantilla.construirHorizontal(ficha);
        } else {
            plantilla.construirVertical(ficha);
        }

        DibujoFicha dibujoFicha = plantilla.resultado();
        double layoutY = jugada.getLayoutY();
        double layoutX = jugada.getLayoutX();
        dibujoFicha.setLayoutX(layoutX);
        dibujoFicha.setLayoutY(layoutY);
        dibujoFicha.setMula(true);
        dibujoFicha.setIzquierda(jugada.isExtremo());
        dibujoFicha.setPosicion(jugada.getPosicion());
        return dibujoFicha;
    }

    private void coincideExtremos(FichaDTO ficha, DibujoJugada jugada) {
        DibujoFicha dibujoFicha;
        if (jugada.isExtremo()) {
            dibujoFicha = trenFichas.getFirst();
        } else {
            dibujoFicha = trenFichas.getLast();
        }
        int num = dibujoFicha.getExtremo();
        switch (jugada.getPosicion()) {
            case IZQUIERDA:
                if (ficha.getDerecha() != num) {
                    ficha.girarFicha();
                }
                break;
            case DERECHA:
                if (ficha.getIzquierda() != num) {
                    ficha.girarFicha();
                }
                break;
            case ARRIBA:
                if (ficha.getDerecha() != num) {
                    ficha.girarFicha();
                }
                break;
            default:
                if (ficha.getIzquierda() != num) {
                    ficha.girarFicha();
                }
                break;
        }
    }

    private void guardarTrenFichas(DibujoFicha dibujo, DibujoJugada jugada) {
        if (dibujo.sePusoEnLaIzquierda()) {
            trenFichas.addFirst(dibujo);
        } else {
            trenFichas.addLast(dibujo);
        }
    }

    private void dibujarJugadaIzq(FichaDTO ficha) {
        DibujoFicha dibujoFicha = trenFichas.getFirst();
        dibujarExtremo(dibujoFicha, ficha, true);
    }

    private void dibujarJugadaDer(FichaDTO ficha) {
        DibujoFicha dibujoFicha = trenFichas.getLast();
        dibujarExtremo(dibujoFicha, ficha, false);
    }

    private void dibujarExtremo(DibujoFicha dibujoFicha, FichaDTO ficha, boolean izquierda) {
        if (dibujoFicha.getPosicion() == PosicionDTO.CENTRO) {
            casoPrimeraFicha(dibujoFicha, izquierda);
        } else if (dibujoFicha.isMula()) {
            casoFichaAnteriorMula(dibujoFicha, izquierda);
        } else if (ficha.esMula()) {
            casoFichaEsMula(dibujoFicha, izquierda);
        } else {
            casoFichaNoEsMula(dibujoFicha, izquierda);
        }
    }

    private void casoFichaEsMula(DibujoFicha dibujoFicha, boolean izquierda) {
        DibujoJugada jugada = new DibujoJugada();
        jugada.setExtremo(izquierda);
        if (dibujoFicha.getPosicion() == PosicionDTO.IZQUIERDA) {
            DibujoJugada.dibujarJugadaVertical(jugada);
            jugada.setPosicion(PosicionDTO.IZQUIERDA);
            jugada.setLayoutX(dibujoFicha.getLayoutX() - 40);
            jugada.setLayoutY(dibujoFicha.getLayoutY() - 16);

        } else if (dibujoFicha.getPosicion() == PosicionDTO.DERECHA) {
            DibujoJugada.dibujarJugadaVertical(jugada);
            jugada.setPosicion(PosicionDTO.DERECHA);
            jugada.setLayoutX(dibujoFicha.getLayoutX() + 72);
            jugada.setLayoutY(dibujoFicha.getLayoutY() - 16);

        } else if (dibujoFicha.getPosicion() == PosicionDTO.ARRIBA) {
            DibujoJugada.dibujarJugadaHorizontal(jugada);
            jugada.setPosicion(PosicionDTO.ARRIBA);
            jugada.setLayoutX(dibujoFicha.getLayoutX() - 16);
            jugada.setLayoutY(dibujoFicha.getLayoutY() - 40);

        } else {
            DibujoJugada.dibujarJugadaHorizontal(jugada);
            jugada.setPosicion(PosicionDTO.ABAJO);
            jugada.setLayoutX(dibujoFicha.getLayoutX() - 16);
            jugada.setLayoutY(dibujoFicha.getLayoutY() + 72);
        }
        this.getChildren().add(jugada);
    }

    private void casoPrimeraFicha(DibujoFicha dibujoFicha, boolean izquierda) {
        DibujoJugada jugada = new DibujoJugada();
        jugada.setExtremo(izquierda);
        if (izquierda) {
            DibujoJugada.dibujarJugadaHorizontal(jugada);
            jugada.setPosicion(PosicionDTO.IZQUIERDA);
            jugada.setLayoutX(dibujoFicha.getLayoutX() - 72);
            jugada.setLayoutY(dibujoFicha.getLayoutY());
        } else {
            DibujoJugada.dibujarJugadaHorizontal(jugada);
            jugada.setPosicion(PosicionDTO.DERECHA);
            jugada.setLayoutX(dibujoFicha.getLayoutX() + 72);
            jugada.setLayoutY(dibujoFicha.getLayoutY());
        }
        jugada.setOnMouseClicked(opcionJugada);
        this.getChildren().add(jugada);
    }

    private void casoFichaNoEsMula(DibujoFicha dibujoFicha, boolean izquierda) {
        DibujoJugada jugada = new DibujoJugada();
        jugada.setExtremo(izquierda);
        switch (dibujoFicha.getPosicion()) {
            case IZQUIERDA:
                DibujoJugada.dibujarJugadaHorizontal(jugada);
                jugada.setPosicion(PosicionDTO.IZQUIERDA);
                jugada.setLayoutX(dibujoFicha.getLayoutX() - 72);
                jugada.setLayoutY(dibujoFicha.getLayoutY());
                break;
            case DERECHA:
                DibujoJugada.dibujarJugadaHorizontal(jugada);
                jugada.setPosicion(PosicionDTO.DERECHA);
                jugada.setLayoutX(dibujoFicha.getLayoutX() + 72);
                jugada.setLayoutY(dibujoFicha.getLayoutY());
                break;
            case ARRIBA:
                DibujoJugada.dibujarJugadaVertical(jugada);
                jugada.setPosicion(PosicionDTO.ARRIBA);
                jugada.setLayoutX(dibujoFicha.getLayoutX());
                jugada.setLayoutY(dibujoFicha.getLayoutY() - 72);
                break;
            default:
                DibujoJugada.dibujarJugadaVertical(jugada);
                jugada.setPosicion(PosicionDTO.ABAJO);
                jugada.setLayoutX(dibujoFicha.getLayoutX());
                jugada.setLayoutY(dibujoFicha.getLayoutY() + 72);
                break;
        }
        this.getChildren().add(jugada);
    }

    private void casoFichaAnteriorMula(DibujoFicha dibujoFicha, boolean izquierda) {
        DibujoJugada jugada1 = new DibujoJugada();
        DibujoJugada jugada2 = new DibujoJugada();
        jugada1.setExtremo(izquierda);
        jugada2.setExtremo(izquierda);
        if (dibujoFicha.getPosicion() == PosicionDTO.IZQUIERDA || dibujoFicha.getPosicion() == PosicionDTO.DERECHA) {
            DibujoJugada.dibujarJugadaHorizontal(jugada1);
            jugada1.setPosicion(PosicionDTO.IZQUIERDA);
            jugada1.setLayoutX(dibujoFicha.getLayoutX() - 72);
            jugada1.setLayoutY(dibujoFicha.getLayoutY());

            DibujoJugada.dibujarJugadaHorizontal(jugada2);
            jugada2.setPosicion(PosicionDTO.DERECHA);
            jugada2.setLayoutX(dibujoFicha.getLayoutX() + 72);
            jugada2.setLayoutY(dibujoFicha.getLayoutY());

        } else {
            DibujoJugada.dibujarJugadaVertical(jugada1);
            jugada1.setPosicion(PosicionDTO.ARRIBA);
            jugada1.setLayoutX(dibujoFicha.getLayoutX());
            jugada1.setLayoutY(dibujoFicha.getLayoutY() - 72);

            DibujoJugada.dibujarJugadaVertical(jugada2);
            jugada2.setPosicion(PosicionDTO.ABAJO);
            jugada2.setLayoutX(dibujoFicha.getLayoutX());
            jugada2.setLayoutY(dibujoFicha.getLayoutY() + 72);
        }

        this.getChildren().addAll(jugada1, jugada2);
    }

    private boolean sePuedePonerDerecha(DibujoFicha extremo, DibujoFicha dibujo) {
        extremo.getLayoutX();
        return false;

    }

    public void limpiarJugadas() {
        this.getChildren().removeIf(node -> "jugada".equals(node.getId()));
    }
}
