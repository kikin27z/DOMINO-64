package presentacion_dibujo;

import entidadesDTO.FichaDTO;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BuilderFichaTablero extends BuilderFicha<DibujoFicha> {
    private DibujoFicha dibujoFicha;

    public BuilderFichaTablero() {
    }

    @Override
    public void reiniciar() {
        dibujoFicha = new DibujoFicha();
    }

    @Override
    public void construirVertical(FichaDTO fichaDTO) {
        reiniciar();
        this.dibujoFicha = dibujarFicha(fichaDTO, BuilderFicha.Orientation.VERTICAL);
    }

    @Override
    public void construirHorizontal(FichaDTO fichaDTO) {
        reiniciar();
        this.dibujoFicha = dibujarFicha(fichaDTO, BuilderFicha.Orientation.HORIZONTAL);
    }

    @Override
    public DibujoFicha resultado() {
        return this.dibujoFicha;
    }
    
    public DibujoFicha dibujarFicha(FichaDTO ficha, BuilderFicha.Orientation orientation) {
        int width = orientation == BuilderFicha.Orientation.HORIZONTAL ? 60 : 30;
        int height = orientation == BuilderFicha.Orientation.HORIZONTAL ? 30 : 60;
        int derecha =  ficha.getDerecha();
        int izquierda = ficha.getIzquierda();
        // Create new DibujoFicha with the proper dimensions
        dibujoFicha.definirAncho(width);
        dibujoFicha.definirLargo(height);
        
        GraphicsContext gc = dibujoFicha.getGraphicsContext2D();

        // Dibujar el fondo de la ficha
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(0, 0, width, height, 6, 6); // Reducido el radio de las esquinas a 6

        // Dibujar la línea divisoria
        gc.setStroke(Color.BLACK);
        if (orientation == BuilderFicha.Orientation.HORIZONTAL) {
            gc.strokeLine(width / 2, 0, width / 2, height);
        } else {
            gc.strokeLine(0, height / 2, width, height / 2);
        }

        // Dibujar los puntos en cada lado
        if (orientation == BuilderFicha.Orientation.HORIZONTAL) {
            dibujarPuntos(gc, izquierda, 0, 0, orientation);
            dibujarPuntos(gc, derecha, width / 2, 0, orientation);
        } else {
            dibujarPuntos(gc, izquierda, 0, 0, orientation);
            dibujarPuntos(gc, derecha, 0, height / 2, orientation); //Derecha es abajo
        }

        return dibujoFicha;
    }

    private void dibujarPuntos(GraphicsContext gc, int valor, int offsetX, int offsetY, BuilderFicha.Orientation orientation) {
        gc.setFill(Color.BLACK);
        int grosorBolita = 4;  // Reducido para mantener proporción
        int grosorBola1 = 6;   // Reducido para mantener proporción
        int grosorBola2 = 5;   // Reducido para mantener proporción

        int ancho = orientation == BuilderFicha.Orientation.HORIZONTAL ? 30 : 30;  // Mitad del ancho total para fichas horizontales
        int alto = orientation == BuilderFicha.Orientation.HORIZONTAL ? 30 : 30;   // Mitad del alto total para fichas verticales

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