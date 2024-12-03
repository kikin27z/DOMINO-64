package observer;

import eventoBase.Evento;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface Observer<T extends Evento> {
    public void update(T observable);
}
