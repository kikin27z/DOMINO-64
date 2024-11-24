/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino64.eventos.base.error;

import domino64.eventos.base.Evento;

/**
 * Clase que representa los eventos de error que
 * se den. 
 * Se puede ver como un reemplazo de las excepciones,
 * pero tienen el objetivo de enviar los mensajes de error
 * por la red
 * @author luisa M
 * @author karim F
 */
public class EventoError implements Evento<String> {
    private TipoError tipo;
    private String mensaje;
    private int idPublicador;
    private int idContexto;
    
    public EventoError(TipoError tipo, String mensaje){
        this.tipo = tipo;
        this.mensaje = mensaje;
    }
    
    public void setTipo(TipoError tipo){
        this.tipo = tipo;
    }
    
    public void setMensajeError(String msj){
        this.mensaje = msj;
    }
    
    public void setIdContexto(int id){
        this.idContexto = id;
    }
    
    @Override
    public TipoError getTipo(){
        return tipo;
    }
    
    @Override
    public int getIdContexto(){
        return idContexto;
    }

    @Override
    public int getIdPublicador() {
        return idPublicador;
    }

    @Override
    public String getInfo() {
        return mensaje;
    }

    public void setIdPublicador(int id) {
        this.idPublicador = id;
    }
}
