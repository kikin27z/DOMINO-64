/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class Jugador {
    private List<Ficha> fichas;
    private Cuenta cuenta;
    private boolean enTurno;

    public Jugador(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public boolean isEnTurno() {
        return enTurno;
    }

    public void setEnTurno(boolean enTurno) {
        this.enTurno = enTurno;
    }

    public void agregarFicha(Ficha ficha){
        this.fichas.add(ficha);
    }
    
    public void removerFicha(Ficha ficha){
        this.fichas.remove(ficha);
    }
    
    public Ficha obtenerMayorMula(){
        List<Ficha> mulas = obtenerMulas();
        if(!mulas.isEmpty()){
            return Collections.max(mulas);
        }
        return null;
    }
    
    private List<Ficha> obtenerMulas(){
        List<Ficha> mulas = new ArrayList<>();
        for (Ficha ficha : fichas) {
            if(ficha.esMula())
                mulas.add(ficha);
        }
        return mulas;
    }
    
}
