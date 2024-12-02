/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class JugadorDTO implements Serializable {
    private CuentaDTO cuenta;
    private List<FichaDTO> fichas;
    private FichaDTO fichaSeleccionada;

    public JugadorDTO(CuentaDTO cuenta){
        this.cuenta = cuenta;
    }
    public JugadorDTO(){
        fichas = new ArrayList<>();
    }
    
    public List<FichaDTO> getFichas() {
        return fichas;
    }

    public void setFichas(List<FichaDTO> fichas) {
        this.fichas = fichas;
    }

    public FichaDTO getFichaSeleccionada() {
        return fichaSeleccionada;
    }

    public void setFichaSeleccionada(FichaDTO fichaSeleccionada) {
        this.fichaSeleccionada = fichaSeleccionada;
    }
    
    public void removerFicha(FichaDTO ficha){
        this.fichas.remove(ficha);
    }
    
    public CuentaDTO getCuenta(){
        return cuenta;
    }
    
    public void setCuenta(CuentaDTO cuenta){
        this.cuenta = cuenta;
    }
    
    public FichaDTO getMulaMayor(){
        List<Integer> valores = new ArrayList<>();
        for (FichaDTO ficha : fichas) {
            if(ficha.esMula())
                valores.add(ficha.getDerecha()+ficha.getDerecha());
        }
        if(!valores.isEmpty()){
            int index = Collections.max(valores);
            return fichas.get(index);
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador{");
        sb.append("username=").append(cuenta.getUsername());
        sb.append('}');
        return sb.toString();
    }
}
