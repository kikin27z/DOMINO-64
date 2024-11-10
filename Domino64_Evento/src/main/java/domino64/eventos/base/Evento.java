/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package domino64.eventos.base;

import java.io.Serializable;

/**
 * Interfaz base para todos los eventos que se usen en el proyecto.
 * Cualquier evento que implemente esta interfaz sera un objeto serializable,
 * por lo tanto se podra enviar por la red.
 * Todo evento debe especificar el tipo de evento espeficico que es.
 * Se le debe asignar el id del Publicador, es decir, el id de quien esta creando
 * el evento; el cual debe ser un objeto que implemente de Publicador.
 * La informacion que maneje este evento se debe de definir en el tipo generico
 * de cada clase concreta de evento
 * @author luisa M
 * @author karim F
 * @param <T> Tipo de informacion que va a manejar este evento. Puede ser tanto una entidad
 * como un dto
 */
public interface Evento<T> extends Serializable{
    /**
     * Tipo de evento especifico que es.
     * Todo evento debe tener un enum que define los
     * posibles tipos que puede ser un evento
     * @return El enum indicando el tipo de evento
     */
    public Enum<?> getTipo() ;
    /**
     * Obtiene el id del publicador del evento
     * @return un entero representando el id
     */
    public int getIdPublicador();
    /**
     * obtiene la informacion del contexto del evento
     * @return El contexto representado por el tipo generico especificado
     * en la clase de evento concreta
     */
    public T getInfo();
    
}
