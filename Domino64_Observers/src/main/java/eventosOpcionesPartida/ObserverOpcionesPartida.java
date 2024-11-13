package eventosOpcionesPartida;

import entidadesDTO.UnirseDTO;

/**
 * Interfaz que define los métodos que deben implementar los observadores en el contexto de las opciones de la partida.
 * Los objetos que implementan esta interfaz responden a los eventos notificados por un observable sobre la creación o búsqueda de una partida.
 * 
 * Los observadores reaccionan a los eventos, como la creación de una nueva partida o la búsqueda de una partida existente,
 * proporcionando la lógica específica para cada evento según sea necesario.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObserverOpcionesPartida {

    /**
     * Método llamado cuando el observador debe crear una nueva partida.
     * Este método debe contener la lógica para iniciar la creación de una partida.
     */
    public void crearPartida();

    /**
     * Método llamado cuando el observador debe buscar una partida existente.
     * Este método debe contener la lógica para buscar una partida utilizando un código de partida proporcionado.
     * 
     * @param unirse el código único de la partida que se desea buscar.
     */
    public void buscarPartida(UnirseDTO unirse);
}
