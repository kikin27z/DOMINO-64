/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNotificacionEventos;

import com.domino64.base.Publicador;
import com.domino64.base.Suscriptor;
import eventoss.EventoMVC;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author luisa M
 */
public class PublicadorEventos implements Publicador<EventoMVC>{
    private static PublicadorEventos publicador;
    private final Map<Enum<?>, Set<Suscriptor>> suscriptores;
    
    private PublicadorEventos(){
        this.suscriptores = new HashMap<>();
    }
    
    public static synchronized PublicadorEventos getInstance(){
        if(publicador == null){
            publicador = new PublicadorEventos();
        }
        return publicador;
    }
    
    @Override
    public void suscribir(Enum<?> tipoEvento, Suscriptor suscriptor) {
        suscriptores.merge(tipoEvento, new HashSet<>(List.of(suscriptor)),
                (set, newSet) ->{
                    set.add(suscriptor);
                    return set;
                });
    }

    @Override
    public void desuscribir(Enum<?> tipoEvento, Suscriptor suscriptor) {
        suscriptores.computeIfPresent(tipoEvento,
                (tipo, subs) -> {
                    subs.remove(suscriptor);
                    return subs;
                });
    }

    @Override
    public void publicarEvento(Enum<?> tipoEvento, EventoMVC evento) {
        if(suscriptores.containsKey(tipoEvento)){
            suscriptores.get(tipoEvento).forEach(sub -> sub.recibirEvento(evento));
        }
    }
    
}
