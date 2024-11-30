package tablero_notificador;

import builder.EventBuilder;
import domino64.eventos.base.Evento;
import entidadesDTO.JugadaRealizadaDTO;
import eventos.EventoTablero;
import tiposLogicos.TipoLogicaTablero;

/**
 *
 * @author karim
 */
public class BuilderEventoTablero implements EventBuilder<TipoLogicaTablero,JugadaRealizadaDTO>{
    private EventoTablero evento;

    public BuilderEventoTablero(EventoTablero evento) {
        this.evento = evento;
    }

    @Override
    public void setTipo(TipoLogicaTablero tipo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setIdPublicador(int idPublicador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setIdContexto(int idContexto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setContexto(JugadaRealizadaDTO contexto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EventoTablero construirEvento() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void reiniciar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
