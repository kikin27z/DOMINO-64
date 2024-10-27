/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesDTO;

import java.util.Stack;

/**
 *
 * @author luisa M
 */
public class PozoDTO {
    private Stack<FichaDTO> fichas;
    
    public PozoDTO(){
        this.fichas= new Stack<>();
    }
    
    public boolean hayFichas() {
        return !fichas.isEmpty();
    }

    public void setPozo(Stack<FichaDTO> fichas) {
        this.fichas = fichas;
    }

    public Stack<FichaDTO> getFichas() {
        return fichas;
    }
    
}
