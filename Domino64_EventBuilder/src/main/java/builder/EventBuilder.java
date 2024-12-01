package builder;

import domino64.eventos.base.Evento;

/**
 * Interfaz para construir eventos personalizados en el sistema.
 * 
 * @param <T> Tipo genérico que representa el tipo de evento a construir.
 * 
 * <p>
 * Esta interfaz define los métodos necesarios para configurar y construir un evento,
 * incluyendo el tipo, el identificador del publicador (el componente que envía el evento),
 * y el contexto (que representa la partida específica entre varias disponibles en el servidor).
 * </p>
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface EventBuilder<T> {

    /**
     * Establece el tipo del evento a construir.
     * 
     * @param tipo el tipo del evento que se emitirá.
     */
    public abstract void setTipo(T tipo);

    /**
     * Establece el identificador del publicador del evento.
     * 
     * @param idPublicador el ID del componente que envía el evento.
     */
    public abstract void setIdPublicador(int idPublicador);
    public abstract void setIdDestinatario(int idDestinatario);

    /**
     * Establece el identificador del contexto del evento.
     * 
     * @param idContexto el ID que distingue una partida de entre las múltiples partidas disponibles en el servidor.
     */
    public abstract void setIdContexto(int idContexto);

    // public abstract void setContexto(T contexto);

    /**
     * Construye y retorna el evento configurado.
     * 
     * @return el evento configurado, de tipo {@link Evento}.
     */
    public abstract Evento construirEvento();

    /**
     * Reinicia el estado interno del constructor para permitir la configuración de un nuevo evento.
     */
    public abstract void reiniciar();
}
