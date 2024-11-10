/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    private boolean extremo;
    private double width;
    private double height;
    private PosicionDTO posicion;

    public DibujoJugada() {
    }
     
    public static void dibujarJugadaHorizontal(DibujoJugada canvas) {
        canvas.setWidth(72);
        canvas.setHeight(40);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setId("jugada");
        // Dibujar el borde azul
        dibujarCanvas(gc, canvas.getWidth(), canvas.getHeight());

        // Cambiar el cursor al pasar el mouse
        canvas.setOnMouseEntered(event -> canvas.setCursor(Cursor.HAND));
        canvas.setOnMouseExited(event -> canvas.setCursor(Cursor.DEFAULT));
    }

     public static void  dibujarJugadaVertical(DibujoJugada canvas) {
        canvas.setWidth(40);
        canvas.setHeight(72);
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

    public boolean isExtremo() {
        return extremo;
    }

    public void setExtremo(boolean extremo) {
        this.extremo = extremo;
    }

    public PosicionDTO getPosicion() {
        return posicion;
    }

    public void setPosicion(PosicionDTO posicion) {
        this.posicion = posicion;
    }
}
