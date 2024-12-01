/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventBus;

import com.domino64.base.Publicador;
import com.domino64.base.Suscriptor;
import domino64.eventos.base.Evento;

/**
 * Clase para representar a quienes pueden publicar eventos
 * en el bus.
 * Cada publicador tiene un id que lo identifica, este id
 * se le va a asignar a los eventos que publique
 * @author luisa M
 * @author karim F
 */
public class Publisher implements Publicador{
    private int idPublicador;
    
    public Publisher(){
    }
    
    public void setId(int id){
        this.idPublicador = id;
    }
    
    public int getIdPublicador(){
        return idPublicador;
    }

    @Override
    public void suscribir(Enum tipoEvento, Suscriptor suscriptor) {
        BusCore.getInstance().addSub((Subscriber)suscriptor, tipoEvento);
        System.out.println("se suscribio a evento: "+tipoEvento.toString());
    }

    @Override
    public void desuscribir(Enum tipoEvento, Suscriptor suscriptor) {
        BusCore.getInstance().removeSub((Subscriber)suscriptor, tipoEvento);
    }

    @Override
    public void publicarEvento(Enum tipoEvento, Evento evento) {
        System.out.println("en publicar Evento");
        BusCore.getInstance().postEvent(tipoEvento, evento);
        System.out.println("se publico evento: "+evento.getInfo());
    }
}
