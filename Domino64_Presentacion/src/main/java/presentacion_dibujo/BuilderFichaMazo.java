package presentacion_dibujo;

import entidadesDTO.FichaDTO;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BuilderFichaMazo extends BuilderFicha {
    private Canvas ficha;

    public BuilderFichaMazo() {
    }

    @Override
    public void reiniciar() {
        ficha = new Canvas();
    }

    @Override
    public void construirVertical(FichaDTO fichaDTO) {
        reiniciar();
        this.ficha = dibujarFicha(fichaDTO.getIzquierda(), fichaDTO.getDerecha(), Orientation.VERTICAL);
    }

    @Override
    public void construirHorizontal(FichaDTO fichaDTO) {
        reiniciar();
        this.ficha = dibujarFicha(fichaDTO.getIzquierda(), fichaDTO.getDerecha(), Orientation.HORIZONTAL);
        System.out.println(ficha);
    }

    @Override
    public Canvas resultado() {
        return this.ficha;
    }

    private Canvas dibujarFicha(int izquierda, int derecha, Orientation orientation) {
        int width = orientation == Orientation.HORIZONTAL ? 90 : 50;
        int height = orientation == Orientation.HORIZONTAL ? 50 : 90;
        Canvas ficha = new Canvas(width, height);
        GraphicsContext gc = ficha.getGraphicsContext2D();

        // Dibujar el fondo de la ficha
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(0, 0, width, height, 8, 8); // Ficha con bordes redondeados

        // Dibujar la línea divisoria
        gc.setStroke(Color.BLACK);
        if (orientation == Orientation.HORIZONTAL) {
            gc.strokeLine(width / 2, 0, width / 2, height);
        } else {
            gc.strokeLine(0, height / 2, width, height / 2);
        }

        // Dibujar los puntos en cada lado
        if (orientation == Orientation.HORIZONTAL) {
            dibujarPuntos(gc, izquierda, 0, 0, orientation);
            dibujarPuntos(gc, derecha, width / 2, 0, orientation);
        } else {
            dibujarPuntos(gc, izquierda, 0, 0, orientation);
            dibujarPuntos(gc, derecha, 0, height / 2, orientation);
        }

        return ficha;
    }

    private void dibujarPuntos(GraphicsContext gc, int valor, int offsetX, int offsetY, Orientation orientation) {
        gc.setFill(Color.BLACK);
        int grosorBolita = 7;
        int grosorBola1 = 10;
        int grosorBola2 = 9;

        int ancho = orientation == Orientation.HORIZONTAL ? 45 : 50;
        int alto = orientation == Orientation.HORIZONTAL ? 50 : 45;

        switch (valor) {
            case 0 -> {
            }
            case 1 -> gc.fillOval(offsetX + ancho / 2 - grosorBola1 / 2, offsetY + alto / 2 - grosorBola1 / 2, grosorBola1, grosorBola1);
            case 2 -> {
                gc.fillOval(offsetX + ancho / 4 - grosorBola2 / 2, offsetY + alto / 4 - grosorBola2 / 2, grosorBola2, grosorBola2);
                gc.fillOval(offsetX + 3 * ancho / 4 - grosorBola2 / 2, offsetY + 3 * alto / 4 - grosorBola2 / 2, grosorBola2, grosorBola2);
            }
            case 3 -> {
                gc.fillOval(offsetX + ancho / 6 - grosorBolita / 2, offsetY + alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + ancho / 2 - grosorBolita / 2, offsetY + alto / 2 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + 5 * ancho / 6 - grosorBolita / 2, offsetY + 5 * alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
            }
            case 4 -> {
                gc.fillOval(offsetX + ancho / 6 - grosorBolita / 2, offsetY + alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + ancho / 6 - grosorBolita / 2, offsetY + 5 * alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + 5 * ancho / 6 - grosorBolita / 2, offsetY + alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + 5 * ancho / 6 - grosorBolita / 2, offsetY + 5 * alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
            }
            case 5 -> {
                gc.fillOval(offsetX + ancho / 6 - grosorBolita / 2, offsetY + alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + ancho / 6 - grosorBolita / 2, offsetY + 5 * alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + 5 * ancho / 6 - grosorBolita / 2, offsetY + alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + 5 * ancho / 6 - grosorBolita / 2, offsetY + 5 * alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + ancho / 2 - grosorBolita / 2, offsetY + alto / 2 - grosorBolita / 2, grosorBolita, grosorBolita);
            }
            case 6 -> {
                gc.fillOval(offsetX + ancho / 6 - grosorBolita / 2, offsetY + alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + ancho / 6 - grosorBolita / 2, offsetY + alto / 2 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + ancho / 6 - grosorBolita / 2, offsetY + 5 * alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + 5 * ancho / 6 - grosorBolita / 2, offsetY + alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + 5 * ancho / 6 - grosorBolita / 2, offsetY + alto / 2 - grosorBolita / 2, grosorBolita, grosorBolita);
                gc.fillOval(offsetX + 5 * ancho / 6 - grosorBolita / 2, offsetY + 5 * alto / 6 - grosorBolita / 2, grosorBolita, grosorBolita);
            }
        }
    }
}