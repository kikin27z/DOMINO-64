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
 * @param <T> Tipo de entidad que va a tener como contexto este evento
 */
public abstract class EventoLogico<T> implements Evento{
    private int idPublicador;
    private int idContexto;
    
    public EventoLogico(){
    }
    /**
     * este metodo es abstracto pensando en la posibilidad
     * de que un evento puede incluir en su informacion
     * una lista de la entidad que esta manejando. 
     * Por ejemplo, un evento puede tener como informacion 
     * una lista de jugadores, o una lista de fichas,
     * y no un solo objeto de dicha entidad.
     * @param info Contexto que se va a agregar al evento
     */
    public abstract void agregarInfo(T info);

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
