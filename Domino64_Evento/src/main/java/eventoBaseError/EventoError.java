package eventoBaseError;

import eventoBase.Evento;

/**
 * Clase que representa los eventos de error que se den. Se puede ver como un
 * reemplazo de las excepciones, pero tienen el objetivo de enviar los mensajes
 * de error por la red
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class EventoError implements Evento<TipoError> {

    private TipoError tipo;
    private String mensaje;
    private int idPublicador;
    private int idContexto;

    public EventoError(TipoError tipo, String mensaje) {
        this.tipo = tipo;
        this.mensaje = mensaje;
    }

    public void setTipo(TipoError tipo) {
        this.tipo = tipo;
    }

    public void setMensajeError(String msj) {
        this.mensaje = msj;
    }

    public void setIdContexto(int id) {
        this.idContexto = id;
    }

    @Override
    public TipoError getTipo() {
        return tipo;
    }

    @Override
    public int getIdContexto() {
        return idContexto;
    }

    @Override
    public int getIdPublicador() {
        return idPublicador;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setIdPublicador(int id) {
        this.idPublicador = id;
    }
}
