package partida;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DominoDraw {

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    public static Canvas dibujarFicha(int izquierda, int derecha, double layoutX, double layoutY, Orientation orientation) {
        int width = orientation == Orientation.HORIZONTAL ? 106 : 60;
        int height = orientation == Orientation.HORIZONTAL ? 60 : 106;
        Canvas ficha = new Canvas(width, height);
        GraphicsContext gc = ficha.getGraphicsContext2D();

        // Dibujar el fondo de la ficha
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(0, 0, width, height, 10, 10); // Ficha con bordes redondeados

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

        ficha.setLayoutX(layoutX);
        ficha.setLayoutY(layoutY);
        return ficha;
    }
    public static Canvas dibujarFicha(int izquierda, int derecha, Orientation orientation) {
        int width = orientation == Orientation.HORIZONTAL ? 106 : 60;
        int height = orientation == Orientation.HORIZONTAL ? 60 : 106;
        Canvas ficha = new Canvas(width, height);
        GraphicsContext gc = ficha.getGraphicsContext2D();

        // Dibujar el fondo de la ficha
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(0, 0, width, height, 10, 10); // Ficha con bordes redondeados

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

    private static void dibujarPuntos(GraphicsContext gc, int valor, int offsetX, int offsetY, Orientation orientation) {
        gc.setFill(Color.BLACK);
        int grosorBolita = 10;
        int grosorBola1 = 14;
        int grosorBola2 = 12;

        int ancho = orientation == Orientation.HORIZONTAL ? 53 : 60;
        int alto = orientation == Orientation.HORIZONTAL ? 60 : 53;

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
