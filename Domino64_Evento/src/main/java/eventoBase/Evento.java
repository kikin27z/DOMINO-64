package eventoBase;

import java.io.Serializable;

/**
 * Interfaz base para todos los eventos que se usen en el proyecto. Cualquier
 * evento que implemente esta interfaz sera un objeto serializable, por lo tanto
 * se podra enviar por la red. Todo evento debe especificar el tipo de evento
 * espeficico que es. Se le debe asignar el id del Publicador, es decir, el id
 * de quien esta creando el evento; el cual debe ser un objeto que implemente de
 * Publicador. La informacion que maneje este evento se debe de definir en el
 * tipo generico de cada clase concreta de evento
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 *
 * @param <T> Tipo de informacion que va a manejar este evento. Puede ser tanto
 * una entidad como un dto
 */
public interface Evento<T extends Enum> extends Serializable {

    /**
     * Tipo de evento especifico que es. Todo evento debe tener un enum que
     * define los posibles tipos que puede ser un evento
     *
     * @return El enum indicando el tipo de evento
     */
    public T getTipo();

    /**
     * Obtiene el id del publicador del evento
     *
     * @return un entero representando el id
     */
    public int getIdPublicador();


    /**
     * Obtiene el id del contexto del evento. Los eventos deben tener un
     * identificador del contexto al que pertenece el evento. Por ejemplo,
     * ciertos grupos de suscriptores pueden estar esperando el mismo tipo de
     * evento, pero los grupos son de contextos diferentes. El id del contexto
     * tiene el objetivo de distinguir entre un contexto y otro.
     *
     * @return
     */
    public int getIdContexto();
}
