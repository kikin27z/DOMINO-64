package partidaBuilder;

import builder.EventBuilder;
import entidadesDTO.CuentaDTO;
import entidadesDTO.ReglasDTO;
import eventos.EventoPartida;
import tiposLogicos.TipoLogicaPartida;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class BuilderEventoPartida implements EventBuilder<TipoLogicaPartida>{
    private EventoPartida evento;

    public BuilderEventoPartida() {
        this.evento = new EventoPartida();
    }
    
    public void setCuenta(CuentaDTO cuenta){
        evento.setCuenta(cuenta);
    }
    
    public void setReglas(ReglasDTO reglas){
        evento.setReglas(reglas);
    }
    
    @Override
    public void setTipo(TipoLogicaPartida tipo) {
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

    @Override
    public EventoPartida construirEvento() {
        EventoPartida resultado = evento;
        reiniciar();
        return resultado;
    }

    @Override
    public void reiniciar() {
        evento = new EventoPartida();
    }
    
}
