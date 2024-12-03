package domino64.eventos.base.suscripcion;

/**
 * Enumeradores de eventos que le interesan a varios suscriptores. Este tipo de
 * eventos puede ser asignado a cualquier evento logico.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public enum TipoSuscripcion {
    SUSCRIBIR,
    DESUSCRIBIR,
    ESTABLECER_ID_CONTEXTO,
    REMOVER_ID_CONTEXTO;
}
