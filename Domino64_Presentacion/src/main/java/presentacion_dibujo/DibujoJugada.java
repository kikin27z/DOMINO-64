package presentacion_dibujo;

import entidadesDTO.PosicionDTO;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author karim
 */
public class DibujoJugada extends Canvas{
    private boolean extremoIzq;
    private double width;
    private double height;
    private PosicionDTO posicion;

    public DibujoJugada() {
    }
     
    public static void dibujarJugadaHorizontal(DibujoJugada canvas) {
        canvas.setWidth(60);
        canvas.setHeight(30);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setId("jugada");
        // Dibujar el borde azul
        dibujarCanvas(gc, canvas.getWidth(), canvas.getHeight());

        // Cambiar el cursor al pasar el mouse
        canvas.setOnMouseEntered(event -> canvas.setCursor(Cursor.HAND));
        canvas.setOnMouseExited(event -> canvas.setCursor(Cursor.DEFAULT));
    }

     public static void  dibujarJugadaVertical(DibujoJugada canvas) {
        canvas.setWidth(30);
        canvas.setHeight(60);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setId("jugada");

        // Dibujar el borde azul
        dibujarCanvas(gc, canvas.getWidth(), canvas.getHeight());

        // Cambiar el cursor al pasar el mouse
        canvas.setOnMouseEntered(event -> canvas.setCursor(Cursor.HAND));
        canvas.setOnMouseExited(event -> canvas.setCursor(Cursor.DEFAULT));
    }

    private static void dibujarCanvas(GraphicsContext gc, double width, double height) {
        // Establecer color del borde
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5); // Grosor del borde
        // Dibujar el borde
        gc.strokeRect(0, 0, width, height);
    }

    public boolean isExtremoIzq() {
        return extremoIzq;
    }

    public void setExtremoIzq(boolean extremoIzq) {
        this.extremoIzq = extremoIzq;
    }

    public PosicionDTO getPosicion() {
        return posicion;
    }

    public void setPosicion(PosicionDTO posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "DibujoJugada{" + "extremoIzq=" + extremoIzq + ", width=" + width + ", height=" + height + ", posicion=" + posicion + '}';
    }
    
    
}
