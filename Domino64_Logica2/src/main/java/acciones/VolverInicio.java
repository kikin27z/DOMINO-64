/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acciones;

import logica.GameHandler;

/**
 *
 * @author luisa M
 */
public class VolverInicio extends AccionBase{

    private void reiniciar(){
        GameHandler.getPartida().setModo(0);
    }
    
    
    @Override
    public void ejecutarAccion() {
        reiniciar();
        display.irInicio();
    }
    
}
