package eventoBaseSuscripcion;

import eventoBase.Evento;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class EventoSuscripcion implements Evento<TipoSuscripcion> {

    private Enum evento;
    private TipoSuscripcion tipo;
    private int idContexto;
    private int idPublicador;

    public EventoSuscripcion() {
    }

    public EventoSuscripcion(TipoSuscripcion tipo) {
        this.tipo = tipo;
    }

    /**
     * establece el tipo de evento de suscripcion. Indica si el evento es para
     * suscribir o desuscribir de un evento
     *
     * @param tipo Tipo del evento de suscripcion (suscripcion o desuscripcion)
     */
    public void setTipo(TipoSuscripcion tipo) {
        this.tipo = tipo;
    }

    /**
     * establece el tipo de evento al que se quiere suscribir o desuscribir.
     *
     * @param info Enum indicando el tipo de evento especifico del cual se
     * quiere suscribir o desuscribir
     */
    public void agregarEventoSuscripcion(Enum info) {
        evento = info;
    }

    @Override
    public TipoSuscripcion getTipo() {
        return tipo;
    }

    /**
     * obtiene el tipo de evento al que se quiere suscribir o desuscribirse
     *
     * @return El enum con el tipo de evento al cual se quiere suscribir o
     * desuscribir
     */
    // @Override
    public Enum getEventoSuscripcion() {
        return evento;
    }


    @Override
    public int getIdPublicador() {
        return idPublicador;
    }

    @Override
    public int getIdContexto() {
        return idContexto;
    }

    public void setIdContexto(int idContexto) {
        this.idContexto = idContexto;
    }

    public void setIdPublicador(int idPublicador) {
        this.idPublicador = idPublicador;
    }

    @Override
    public String toString() {
        return "EventoSuscripcion{" + "evento=" + evento + ", tipo=" + tipo + ", idPublicador=" + idPublicador + '}';
    }

}
