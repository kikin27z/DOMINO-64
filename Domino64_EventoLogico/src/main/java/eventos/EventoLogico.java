/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eventos;

import domino64.eventos.base.Evento;

/**
 * Clase que define todos los eventos logicos.
 * Como su nombre lo indica, estos eventos son los que 
 * van a manejar los componentes encargados de la logica del juego.
 * Al igual que Evento, maneja tipos genericos; esto es para que 
 * los eventos logicos concretos puedan definir que entidad va 
 * a ser el contexto del evento. 
 * @author luisa M
 * @author karim F
 */
public abstract class EventoLogico implements Evento{
    private int idPublicador;
    private int idContexto;
    
    public EventoLogico(){
    }

    public void setIdPublicador(int id){
        this.idPublicador = id;
    }

    public void setIdContexto(int id){
        this.idContexto = id;
    }
    
    @Override
    public int getIdContexto(){
        return idContexto;
    }
    
    @Override
    public int getIdPublicador(){
        return idPublicador;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EventoLogico{");
        sb.append(", idPublicador=").append(idPublicador);
        sb.append(", idContexto=").append(idContexto);
        sb.append('}');
        return sb.toString();
    }
    
}
