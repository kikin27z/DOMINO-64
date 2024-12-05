package utilities;

import builder.EventBuilder;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import eventos.EventoJugadorFicha;
import tiposLogicos.TipoJugadorFicha;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class BuilderEventoJugadorFicha implements EventBuilder<TipoJugadorFicha> {
    private EventoJugadorFicha evento;

    @Override
    public void setTipo(TipoJugadorFicha tipo) {
        evento.setTipo(tipo);
    }

    @Override
    public void setIdPublicador(int idPublicador) {
        evento.setIdPublicador(idPublicador);
    }

    @Override
    public void setIdDestinatario(int idDestinatario) {
        evento.setIdDestinatario(idDestinatario);
    }

    @Override
    public void setIdContexto(int idContexto) {
        evento.setIdContexto(idContexto);
    }
    
    public void asignarJugadaRealizada(JugadaRealizadaDTO jugada){
        evento.setJugada(jugada);
    }

    
    public void setCuenta(CuentaDTO cuenta){
        evento.setCuenta(cuenta);
    }
    
    public void setFicha(FichaDTO ficha){
        evento.setFicha(ficha);
    }
    @Override
    public EventoJugadorFicha construirEvento() {
        EventoJugadorFicha resultado = evento;
        reiniciar();
        return resultado;
    }

    @Override
    public void reiniciar() {
        evento = new EventoJugadorFicha();
    }
}
