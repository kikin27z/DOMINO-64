/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica;

import entidades.Ficha;
import java.util.List;

/**
 *
 * @author karim
 */
public interface IGameplay {
    public void insertarMazo(List<Ficha> fichas);
    public void insertarMazo(Ficha ficha);
    public void quitarFicha();
    
}
