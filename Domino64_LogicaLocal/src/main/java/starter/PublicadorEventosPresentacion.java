/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package starter;

import com.domino64.base.Publicador;
import com.domino64.base.Suscriptor;
import eventos.EventoMVC;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Esta clase seria la encargada de mandar (publicar) los eventos desde la presentacion
 * a la logica.
 * Los suscriptores que tendria serian los manejadores en la logica local
 * @author luisa M
 */
public class PublicadorEventosPresentacion implements Publicador<EventoMVC> {
    private static PublicadorEventosPresentacion publicador;
    private final Map<Enum<?>, Set<Suscriptor>> suscriptores;

    private PublicadorEventosPresentacion() {
        this.suscriptores = new HashMap<>();
    }
    
    public static synchronized PublicadorEventosPresentacion getInstance(){
        if(publicador == null){
            publicador = new PublicadorEventosPresentacion();
        }
        return publicador;
    }
    /**
     * Agrega un nuevo suscriptor para el tipo de evento especificado por
     * el parametro.
     * Este metodo actualiza el mapa de suscriptores. Si no existe un mapeo para
     * el tipo de evento especificado, crea uno nuevo; crea un HashSet incluyendo al 
     * suscriptor del parametro. Si ya existia el mapeo, solamente obtiene el set 
     * y agrega el suscriptor al conjunto.
     * 
     * @param tipoEvento Tipo de evento al cual se va a suscribir
     * @param suscriptor Nuevo suscriptor para el evento
     */
    @Override
    public void suscribir(Enum tipoEvento, Suscriptor suscriptor) {
        suscriptores.merge(tipoEvento, new HashSet<>(List.of(suscriptor)),
                (set, newSet) ->{
                    set.add(suscriptor);
                    return set;
                });
    }

    /**
     * Remueve un suscriptor del conjunto de suscriptores del evento
     * del parametro.
     * Este metodo actualiza el mapa de suscriptores, solo si el tipo
     * de evento ya ha sido mapeado. Si existe el mapeo, obtiene el
     * conjunto de suscriptores para el tipo de evento del parametro
     * y lo remueve del conjunto (si el suscriptor existe en el conjunto).
     * 
     * @param tipoEvento Tipo de evento del cual se quiere remover el suscriptor
     * @param suscriptor Suscriptor a remover
     */
    @Override
    public void desuscribir(Enum tipoEvento, Suscriptor suscriptor) {
        suscriptores.computeIfPresent(tipoEvento,
                (tipo, subs) -> {
                    subs.remove(suscriptor);
                    return subs;
                });
    }

    /**
     * Publica un evento del tipo especificado en el parametro, notificando
     * a los suscriptores del conjunto mapeado al tipo de evento.
     * Este metodo primero evalua si el tipo de evento esta registrado en
     * el mapeo. Si si esta, obtiene el conjunto de suscriptores y le notifica
     * el evento a cada suscriptor del conjunto.
     * 
     * @param tipoEvento Tipo de evento a publicar
     * @param evento El evento a publicar
     */
    @Override
    public void publicarEvento(Enum tipoEvento, EventoMVC evento) {
        if(suscriptores.containsKey(tipoEvento)){
            suscriptores.get(tipoEvento).forEach(sub -> sub.recibirEvento(evento));
        }
    }
    
}
