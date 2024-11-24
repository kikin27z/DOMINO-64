/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventoss;

import domino64.eventos.base.Evento;
import entidadesDTO.CuentaDTO;

/**
 * Clase base que representa todos los eventos que puede
 * manejar la capa de presentacion y la capa de logica.
 * En el caso de la logica, unicamente puede crear estos eventos para
 * enviarlos y recibirlos de la presentacion.
 * Este evento maneja tipos genericos al igual que EventoLogico,
 * pero los eventos de mvc van a manejar cualquier tipo de dto
 * @author luisa M
 * @author karim F
 * @param <T> Tipo de DTO especifico que va a manejar el evento
 */
public abstract class EventoMVC<T> implements Evento{
    private CuentaDTO publicador;
    private int idContexto ;
    
    public void setPublicador(CuentaDTO cuenta){
        publicador = cuenta;
        idContexto = 0;
    }
    
    public void setIdContexto(int id){
        this.idContexto = id;
    }
    
    public CuentaDTO getPublicador(){
        return publicador;
    }
    
    @Override
    public int getIdContexto(){
        return idContexto;
    }
    
    @Override
    public int getIdPublicador(){
        return publicador.getId();
    }
    
   @Override
    public abstract Enum<?> getTipo();
    
    public abstract void agregarContexto(T contexto);
    
}
