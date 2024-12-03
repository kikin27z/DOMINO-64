package eventBus;

import publicadorSuscriptor.Suscriptor;

/**
 * Interfaz para representar a los suscriptores del bus. Hereda de la interfaz
 * Suscriptor, donde se define el metodo para recibir eventos. Tambien es una
 * interfaz comparable, esto quiere decir que los suscriptores se van a poder
 * comparar para fines de ordenamiento en las colecciones (listas, mapeos) de
 * suscriptores. El criterio de comparacion se define mediante el id del
 * suscriptor. Esta interfaz inlcuye solo un metodo para obtener el id del
 * suscriptor en cuestion
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface Subscriber extends Suscriptor, Comparable<Subscriber> {

    /**
     * Obtiene el id del suscriptor
     *
     * @return Un string representando el id del suscriptor
     */
    public int getSubscriberId();

    public int getIdContexto();

}
