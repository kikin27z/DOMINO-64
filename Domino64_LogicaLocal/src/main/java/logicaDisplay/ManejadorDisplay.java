/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDisplay;

import presentacion_utilities.FachadaNavegador;

/**
 *
 * @author luisa M
 */
public class ManejadorDisplay {
    private FachadaNavegador nav;
    
    public ManejadorDisplay(){
        nav = FachadaNavegador.getInstance();
    }
    
    public void cambiarPantalla(int pantallaDestino){
        nav.cambiarPantalla(pantallaDestino);
    }
    
    public void actualizarPantalla(int pantalla){
        
    }
    
}
