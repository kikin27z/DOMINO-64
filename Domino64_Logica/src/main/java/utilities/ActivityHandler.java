/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import exceptions.LogicException;

/**
 *
 * @author luisa M
 */
public abstract class ActivityHandler {
    protected ActivityHandler nextHandler;
    public final int DISRTIBUTE_TILES = 1;
    public final int SELECT_TILE = 2;
    public final int CHECK_TURN = 3;
    public final int CHANGE_TURN = 4;
    public final int PUT_TILE = 5;
    public final int DESIGNATE_FIRST_TURN = 6;
    public final int FIRST_DOUBLE = 7;
    public final int REMOVE_PLAYER = 8;
    public final int FINISH_GAME = 9;
    public final int CHECK_VALID_TILES = 10;
    public final int MAKE_AUTO_MOVE = 11;
    public final int PUT_FIRST_DOUBLE = 12;
    public final int DESIGNATE_OTHER_TURNS = 13;
    

    /**
     * Metodo para agregar un manejador a la cadena
     * @param nextHandler Siguiente manejador en la cadena
     */
    public void setNextHandler(ActivityHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * Metodo para manejar solicitudes
     * @param activityType indicador del tipo de actividad a manejar.
     * Los posibles tipos de actividad estan determinados en esta clase
     * @param context parametros necesarios para procesar la actividad.
     * El contexto puede variar segun el manejador que este procesando
     * la actividad dentro de la cadena.
     * @throws LogicException en caso de que ocurra un error en la logica
     * de algun manejador en la cadena al momento de procesar la solicitud.
     */
    public abstract void handleRequest(int activityType, Object ... context) throws LogicException;
}
